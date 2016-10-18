package com.lunjar.ebp.admin.web.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.lunjar.ebp.admin.domain.dto.ProductPName;
import com.lunjar.ebp.admin.domain.dto.ProductPValue;
import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.ebp.admin.web.session.EnterpriseAgentSession;
import com.lunjar.ebp.bizsupport.dto.ProductDto;
import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.model.Item;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.query.ExpressQuery;
import com.lunjar.ebp.bizsupport.query.ItemQuery;
import com.lunjar.ebp.bizsupport.query.ProductPropImgsQuery;
import com.lunjar.ebp.bizsupport.query.ProductQuery;
import com.lunjar.ebp.bizsupport.query.ProductSkuQuery;
import com.lunjar.ebp.bizsupport.service.ExpressService;
import com.lunjar.ebp.bizsupport.service.FileUploadService;
import com.lunjar.ebp.bizsupport.service.ItemService;
import com.lunjar.ebp.bizsupport.service.ProductPropImgsService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.utils.JsonUtils;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

@Controller
@RequestMapping(value = "product")
public class ProductController extends ControllerSupport {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Resource(name = "enterpriseAgentSession")
	private EnterpriseAgentSession enterpriseAgentSession;

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private ProductPropImgsService productPropImgsService;
	@Autowired
	private ProductSkuService productSkuService;

	@Autowired
	private ExpressService expressService;

	@Autowired
	private ItemService itemService;
	
	/**
	 * 获取商家id
	 * 
	 * @return
	 */
	public Long getId(HttpServletRequest request) {
		// 登陆信息
		EnterpriseAgent agent = enterpriseAgentSession.get(request);
		return agent.getId();
	}

	/**
	 * 图片的类型
	 * 
	 * @return
	 */
	public static String getImageType() {
		StringBuilder sb = new StringBuilder();
		sb.append("image/png");
		sb.append("image/jpeg");
		sb.append("image/bmp");
		return sb.toString();
	}

	/**
	 * 商品列表
	 * 
	 * @param tradeQuery
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String productList(ProductQuery query, String productName, Model model,HttpServletRequest request) {

		if (productName != null && !"".equals(productName.trim())) {
			Map<String, Object> properties = new HashMap<>();
			properties.put("productName", productName);
			query.setProperties(properties);
		}
		if (query.getStatus() == null) {
			// 默认只查询在售的
			query.setStatus(1);
		}
		query.setEnterpriseId(getId(request));
		query.setPageSize(10);
		PageResult<ProductDto> list = productService.queryListAndSku(query);
		model.addAttribute("ps", list);
		model.addAttribute("title", "商品列表");
		// 是否选中
		model.addAttribute("selectType", "2");
		model.addAttribute("productName", productName);
		model.addAttribute("query", query);
		return "/pages/productlist";
	}

	/**
	 * 商品详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "detail/{productId}")
	public String productDetail(@PathVariable("productId") String productId, Model model,HttpServletRequest request) {
		ProductQuery query = new ProductQuery();
		query.setEnterpriseId(getId(request));
		query.setIdArray(new Long(productId));
		List<Product> list = productService.queryList(query);
		if (list != null && list.size() > 0) {
			model.addAttribute("p", list.get(0));
		}
		model.addAttribute("title", productId + "详情");
		// 是否选中
		model.addAttribute("selectType", "2");
		return "/pages/productdetail";
	}

	/**
	 * 商品上下架
	 * 
	 * @return
	 */
	@RequestMapping(value = "productShelves")
	@ResponseBody
	public LunjarApiResponse productShelves(String products, String type,HttpServletRequest request) {
		if (products == null || "".equals(products)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "商品编号为空!");
		}
		// 提示信息
		String msg = "";
		try {
			List<Long> ids = new ArrayList<>();
			if (products.contains(",")) {
				String[] idArr = products.split(",");
				for (String s : idArr) {
					ids.add(new Long(s));
				}
			} else {
				ids.add(new Long(products));
			}
			int size = ids.size();

			ProductQuery query = new ProductQuery();
			query.setIdArray(ids.toArray(new Long[size]));
			if ("1".equals(type)) {
				// type1:表示是上架还是下架 1是上架 2 是下架
				msg = "上架失败";
				query.setStatus(2);
			} else if ("2".equals(type)) {
				msg = "下架失败";
				// type1:表示是上架还是下架 1是上架 2 是下架
				query.setStatus(1);
			} else {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "商品操作有误!");
			}

			query.setEnterpriseId(getId(request));

			int count = productService.queryCount(query);
			if (size == count) {
				// 数据正确
				productService.updateProductShelves(ids.toArray(new Long[size]), type);
			} else {
				// 有商品不是该商家的
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "商品数据有误!");
			}

			return LunjarApiResponse.create();
		} catch (Exception e) {
			logger.error(msg, e);
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), msg);
		}
	}

	/**
	 * 添加商品
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/add")
	public String addProduct(Long id, Model model,HttpServletRequest request) throws ServiceException {
		if (id != null) {
			ProductQuery query = new ProductQuery();
			query.setIdArray(id);
			query.setEnterpriseId(getId(request));
			List<Product> list = productService.queryList(query);
			if (list == null || list.size() == 0) {
				// 该商品不是该商户的
				throw new ServiceException("该商品有误");
			}
			Product p = list.get(0);
			p.setRelativePath(p.getRelativePath() + ",1,1");
			model.addAttribute("p", p);

			// 查询分类
			Map<String, String> names = itemService.getItemNameAndParentName(new Long(p.getCid()));
			if (!names.isEmpty()) {
				p.setCatName(names.get("first") + "/" + names.get("second") + "/" + names.get("three"));
			}

			// 商品相关的图片
			ProductPropImgsQuery ppiQuery = new ProductPropImgsQuery();
			ppiQuery.setProductId(id);
			// 顺序排列
			ppiQuery.setSort("sort_num asc");
			List<ProductPropImgs> listPPi = productPropImgsService.queryList(ppiQuery);
			if (listPPi != null && listPPi.size() > 0) {
				for (ProductPropImgs ppi : listPPi) {
					ppi.setRelativePath(ppi.getRelativePath() + ",2," + ppi.getSortNum());
				}
			}
			model.addAttribute("listPPi", listPPi);
			// 商品相关的sku信息
			ProductSkuQuery psQuery = new ProductSkuQuery();
			psQuery.setProductId(id);
			List<ProductSku> listPs = productSkuService.queryList(psQuery);
			if (list != null && listPs.size() > 0) {
				model.addAttribute("listPs", JsonUtils.beanToJSON(listPs));
			}

			if (p != null) {
				List<ProductPName> pNameList = formtProPV(p.getPropsName(), p.getPropsAlias());
				model.addAttribute("pNames", pNameList);
				model.addAttribute("listSkus", formatSku(listPs));
			}
		}

		// 分类
		ItemQuery itemQuery = new ItemQuery();
		itemQuery.setStatus(1);
		itemQuery.setLevel(1);
		itemQuery.setSort("GMT_CREATE desc");
		List<Item> firstItem = itemService.queryList(itemQuery);
		if (firstItem != null && firstItem.size() > 0) {
			for (Item i : firstItem) {
				List<Item> childs = itemService.getChildItemByParentId(i.getId());
				i.setItems(childs);
			}
		}
		model.addAttribute("firstItems", firstItem);

		// 查询运费模版
		ExpressQuery expressQuery = new ExpressQuery();
		expressQuery.setEnterpriseId(getId(request));
		expressQuery.setEcStatus(1);
		List<Express> listExpress = expressService.queryList(expressQuery);
		model.addAttribute("listExpress", listExpress);

		return "/pages/addproduct";
	}

	/**
	 * 格式化sku
	 * 
	 * @param listSKus
	 * @return
	 */
	private List<ProductSku> formatSku(List<ProductSku> listSKus) {
		if (listSKus != null && listSKus.size() > 0) {
			for (ProductSku ps : listSKus) {
				String propertiesname = ps.getPropertiesname();
				if (propertiesname != null && !"".equals(propertiesname)) {
					List<String> proNames = new ArrayList<>();
					if (propertiesname.contains(";")) {

						for (String proName : propertiesname.split(";")) {
							if (proName.contains(":")) {
								proNames.add(proName.split(":")[1]);
							}
						}

					} else {

						if (propertiesname.contains(":")) {
							proNames.add(propertiesname.split(":")[1]);
						}

					}
					if (proNames.size() > 0) {
						ps.setProNames(proNames);
					}
				}
			}
		}
		return listSKus;
	}

	/**
	 * 格式化商品的sku属性
	 * 
	 * @param propsName
	 * @param propsAlias
	 * @return
	 */
	private List<ProductPName> formtProPV(String propsName, String propsAlias) {

		List<ProductPName> list = new ArrayList<>();

		// propsName--->p1:颜色;p2:尺码
		// propsAlias--->p1:v1:卡奇色;p1:v2:黑色;p2:v1:L;p2:v2:XL;p2:v3:XXL
		if (propsName != null && !"".equals(propsName) && propsAlias != null && !"".equals(propsAlias)) {
			if (propsName.contains(";")) {
				for (String str : propsName.split(";")) {
					String name = str.split(":")[1];// 颜色
					String value = str.split(":")[0];// p1
					ProductPName pName = new ProductPName();
					pName.setName(name);
					pName.setValue(value);

					List<ProductPValue> listPPValue = new ArrayList<>();

					for (String vStr : propsAlias.split(";")) {
						// pValue: 'p1', vValue: 'v1', vName: '卡奇色'
						String pValue = vStr.split(":")[0];// p1
						String vValue = vStr.split(":")[1];// v1
						String vName = vStr.split(":")[2];// 卡奇色
						if (value.equals(pValue)) {
							// 表示是同一组的
							ProductPValue ppValue = new ProductPValue(pValue, vValue, vName);
							listPPValue.add(ppValue);
						}
					}
					if (listPPValue.size() > 0) {
						pName.setList(listPPValue);
					}

					list.add(pName);
				}
			} else if (propsName.contains(":")) {

				String name = propsName.split(":")[1];// 颜色
				String value = propsName.split(":")[0];// p1
				ProductPName pName = new ProductPName();
				pName.setName(name);
				pName.setValue(value);

				List<ProductPValue> listPPValue = new ArrayList<>();

				if (propsAlias.contains(";")) {
					for (String vStr : propsAlias.split(";")) {
						String pValue = vStr.split(":")[0];// p1
						String vValue = vStr.split(":")[1];// v1
						String vName = vStr.split(":")[2];// 卡奇色
						if (value.equals(pValue)) {
							// 表示是同一组的
							ProductPValue ppValue = new ProductPValue(pValue, vValue, vName);
							listPPValue.add(ppValue);
						}
					}
				} else if (propsAlias.contains(":")) {
					String pValue = propsAlias.split(":")[0];// p1
					String vValue = propsAlias.split(":")[1];// v1
					String vName = propsAlias.split(":")[2];// 卡奇色
					if (value.equals(pValue)) {
						// 表示是同一组的
						ProductPValue ppValue = new ProductPValue(pValue, vValue, vName);
						listPPValue.add(ppValue);
					}
				}
				if (listPPValue.size() > 0) {
					pName.setList(listPPValue);
				}
				list.add(pName);
			}
		}

		return list;
	}

	/**
	 * 删除商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public LunjarApiResponse deleteProduct(String products,HttpServletRequest request) {

		if (products == null || "".equals(products)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "商品编号为空!");
		}
		try {
			List<Long> ids = new ArrayList<>();
			if (products.contains(",")) {
				String[] idArr = products.split(",");
				for (String s : idArr) {
					ids.add(new Long(s));
				}
			} else {
				ids.add(new Long(products));
			}
			int size = ids.size();

			ProductQuery query = new ProductQuery();
			query.setIdArray(ids.toArray(new Long[size]));

			query.setEnterpriseId(getId(request));

			int count = productService.queryCount(query);
			if (size == count) {
				// 数据正确
				productService.deleteProduct(ids.toArray(new Long[size]));
			} else {
				// 有商品不是该商家的
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "商品数据有误!");
			}

			return LunjarApiResponse.create();
		} catch (Exception e) {
			logger.error("删除商品失败", e);
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "删除商品失败!");
		}
	}

	/**
	 * 更新商品和sku的价格和商品名称 productId:商品id skuId：skuId productName：商品名称
	 */
	@RequestMapping(value = "updateProductAndSku")
	@ResponseBody
	public LunjarApiResponse updateProductAndSku(Long productId, Long[] skuIds, String productName, String[] prices) {
		if (productId == null && skuIds == null && productName == null && prices == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据不完整!");
		}

		if (productId != null) {
			if ((productName == null || "".equals(productName.trim())) && (prices == null || prices.length != 1)) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据不完整!");
			}
		}

		if (skuIds != null) {
			if (prices == null || skuIds.length != prices.length) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据不完整!");
			}
		}

		try {

			productService.updateProductAndSku(productId, skuIds, productName, prices);
			return LunjarApiResponse.create();
		} catch (Exception e) {
			logger.error("更新商品\\sku信息错误", e);
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "更新错误!");
		}
	}

	/**
	 * 图片上传
	 * 
	 * @param files
	 * @param type
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "fileUpload")
	@ResponseBody
	public LunjarApiResponse fileUpload(@RequestParam("file") MultipartFile[] files, String type,
			HttpServletResponse response,HttpServletRequest request) throws IOException, ServiceException {

		if (files == null || files.length <= 0) {
			// 没有上传文件
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请选择文件!");
		}
		// 文件类型
		String contentType = files[0].getContentType();
		// 文件大小
		long fileSize = files[0].getSize();

		if (!validateContentType(contentType)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请上传图片!");
		}

		Map<String, String> map = fileUploadService.uploadFileReturnMap(getId(request), files[0].getInputStream());
		map.put("type", type);
		return LunjarApiResponse.create(map);
	}

	/**
	 * 校验图片类型
	 * 
	 * @param currentType
	 * @return
	 */
	public static boolean validateContentType(String currentType) {
		return getImageType().contains(currentType);
	}

	/**
	 * 保存商品
	 * 
	 * @param picUrls
	 * @return
	 */
	@RequestMapping(value = "save")
	public String saveProduct(String picUrls, Product product, String skuValue,HttpServletRequest request) {

		try {

			if (picUrls == null || "".equals(picUrls.trim())) {
				// 没有图片上传
				throw new ServiceException("请上传图片");
			}

			if (product.getCid() == null) {
				// 没有设置分类
				throw new ServiceException("请设置分类");
			}

			// if (picUrls.contains(";")) {
			// 要保存的商品
			Product p = product;
			// 保存商品对象的sku信息
			List<ProductSku> listPs = null;
			List<ProductPropImgs> listProductPropImgs = new ArrayList<>();
			if (skuValue != null && !"".equals(skuValue.trim())) {
				try {
					listPs = JSONArray.parseArray(skuValue, ProductSku.class);
				} catch (Exception e) {
					logger.error("JSON转换错误", e);
					throw new ServiceException("商品数据错误");
				}
			}
			if (picUrls.contains(";")) {
				// 商品详情页轮播的图片

				String[] picArray = picUrls.split(";");
				for (String s : picArray) {
					if (s != null && !"".equals(s.trim())) {
						String picUrl = s.split(",")[0];
						String type = s.split(",")[1];
						String sort = s.split(",")[2];

						if ("1".equals(type)) {
							// 表示主图
							p.setPathUrl(picUrl);
							continue;
						}

						if ("2".equals(type)) {
							ProductPropImgs ppi = new ProductPropImgs();
							ppi.setGmtCreate(new Date());
							ppi.setGmtModify(new Date());
							ppi.setPicPath(picUrl);
							ppi.setType(2);
							ppi.setStatus(1);
							ppi.setSortNum(new Integer(sort));
							// 表示轮播图
							listProductPropImgs.add(ppi);
							continue;
						}

						if ("3".equals(type)) {
							// 商品详情的图片，0/1/0/1473321005339.jpg,1;0/1/0/1473321005515.jpg,2;0/1/0/1473321005550.jpg,3
							p.setPathUrl(picUrl);
							continue;
						}
					}
				}

			} else {
				if (picUrls.contains(",")) {
					String picUrl = picUrls.split(",")[0];
					String type = picUrls.split(",")[1];
					String sort = picUrls.split(",")[2];

					if ("1".equals(type)) {
						// 表示主图
						p.setPathUrl(picUrl);
					}

					if ("2".equals(type)) {
						ProductPropImgs ppi = new ProductPropImgs();
						ppi.setGmtCreate(new Date());
						ppi.setGmtModify(new Date());
						ppi.setPicPath(picUrl);
						ppi.setType(2);
						ppi.setStatus(1);
						ppi.setSortNum(new Integer(sort));
						// 表示轮播图
						listProductPropImgs.add(ppi);
					}
				}
			}

			// 设置分类
			Item i = itemService.load(new Long(p.getCid()));
			if (i != null) {
				p.setCatName(i.getName());
			}

			p.setEnterpriseId(getId(request));
			p.setGmtCreate(new Date());
			p.setGmtModify(new Date());
			p.setSaleNum(0);
			// 销售价格和实际价格一样
			p.setPrice(p.getSalePrice());
			productService.saveProduct(p, listPs, listProductPropImgs);
			// }
		} catch (Exception e) {
			logger.error("保存商品失败", e);
		}

		return "redirect:/product/list";
	}

	/**
	 * 获取item
	 * 
	 * @param files
	 * @param type
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "getChildItem")
	@ResponseBody
	public LunjarApiResponse getChildItem(Long id) {

		if (id == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}

		List<Item> childs = itemService.getChildItemByParentId(id);

		return LunjarApiResponse.create(childs);
	}

	/**
	 * 通过id获取父类的名称
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getItemNameAndParentName")
	@ResponseBody
	public LunjarApiResponse getItemNameAndParentName(Long id) {
		Map<String, String> names = itemService.getItemNameAndParentName(id);
		return LunjarApiResponse.create(names);
	}

	/**
	 * 通过id获取父类的名称
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getItemByName")
	@ResponseBody
	public LunjarApiResponse getItemByName(String name) {

		if (name != null && !"".equals(name.trim())) {
			ItemQuery query = new ItemQuery();
			query.setStatus(1);
			query.setName(name);
			query.setLevel(2);
			query.setSort("GMT_CREATE desc");
			List<Item> sencondList = itemService.queryList(query);
			Map<String,Object> map = new HashMap<>();
			map.put("type", "1");
			map.put("data", sencondList);
			return LunjarApiResponse.create(map);
		}else{
			// 分类
			ItemQuery itemQuery = new ItemQuery();
			itemQuery.setStatus(1);
			itemQuery.setLevel(1);
			itemQuery.setSort("GMT_CREATE desc");
			List<Item> firstItem = itemService.queryList(itemQuery);
			if (firstItem != null && firstItem.size() > 0) {
				for (Item i : firstItem) {
					List<Item> childs = itemService.getChildItemByParentId(i.getId());
					i.setItems(childs);
				}
			}
			
			Map<String,Object> map = new HashMap<>();
			map.put("type", "2");
			map.put("data", firstItem);
			
			return LunjarApiResponse.create(map);
		}

	}

}
