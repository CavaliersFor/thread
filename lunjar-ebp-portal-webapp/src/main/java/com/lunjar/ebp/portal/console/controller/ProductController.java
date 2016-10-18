package com.lunjar.ebp.portal.console.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.bizsupport.dto.CartDto;
import com.lunjar.ebp.bizsupport.dto.CategoryDto;
import com.lunjar.ebp.bizsupport.dto.CombinationProductDto;
import com.lunjar.ebp.bizsupport.dto.EnterpriseProModel;
import com.lunjar.ebp.bizsupport.dto.ProductDto;
import com.lunjar.ebp.bizsupport.dto.ProductShopDto;
import com.lunjar.ebp.bizsupport.model.Cart;
import com.lunjar.ebp.bizsupport.model.Category;
import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.model.Discount;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.query.CartQuery;
import com.lunjar.ebp.bizsupport.query.CategoryQuery;
import com.lunjar.ebp.bizsupport.query.CombinationProductQuery;
import com.lunjar.ebp.bizsupport.query.DiscountQuery;
import com.lunjar.ebp.bizsupport.query.ProductPropImgsQuery;
import com.lunjar.ebp.bizsupport.query.ProductQuery;
import com.lunjar.ebp.bizsupport.query.ProductShopQuery;
import com.lunjar.ebp.bizsupport.query.ProductSkuQuery;
import com.lunjar.ebp.bizsupport.service.CartService;
import com.lunjar.ebp.bizsupport.service.CategoryService;
import com.lunjar.ebp.bizsupport.service.CombinationProductService;
import com.lunjar.ebp.bizsupport.service.DiscountService;
import com.lunjar.ebp.bizsupport.service.EnterpriseService;
import com.lunjar.ebp.bizsupport.service.ExpressService;
import com.lunjar.ebp.bizsupport.service.ProductPropImgsService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.ebp.bizsupport.service.ProductShopService;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;
import com.lunjar.ebp.portal.console.model.PortalAgent;
import com.lunjar.ebp.portal.console.session.PortalAgentSession;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.utils.JsonUtils;
import com.lunjar.products.core.web.annotation.QueryPage;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

/**
 * 产品控制器
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月10日下午6:25:54
 */
@Controller
@RequestMapping("product")
public class ProductController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	// private static final String URI_PREFIX = "product/product-";

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductPropImgsService productPropImgsService;

	@Autowired
	private ProductSkuService productSkuService;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductShopService productShopService;

	@Autowired
	private ExpressService expressService;

	@Autowired
	private DiscountService discountService;

	@Autowired
	private CombinationProductService combinationProductService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PortalAgentSession portalAgentSession;

	@Autowired
	private EnterpriseService enterpriseService;

	/**
	 * 获取买方id
	 * 
	 * @return
	 */
	// TODO 获取买方id
	// public Long getBuyerId() {
	// return new Long(12313);
	// }

	/**
	 * 获取商铺id
	 * 
	 * @return
	 */
	// TODO 获取商铺id
	// public Long getShopId() {
	// return new Long(1);
	// }

	/**
	 * 商品详情页
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "getInfo/{shopId}-{id}", method = RequestMethod.GET)
	public String getInfo(@PathVariable("id") Long id, Model model, @PathVariable("shopId") String shopId,
			HttpServletRequest request) throws ServiceException {
		Product p = productService.load(id);
		ProductDto productDto = productService.getProductInfo(id);

		model.addAttribute("productDto", productDto);

		PortalAgent agent = portalAgentSession.get(request);
		// 查询商品图片
		ProductPropImgsQuery productPropImgsQuery = new ProductPropImgsQuery();
		productPropImgsQuery.setProductId(id);
		productPropImgsQuery.setStatus(1);
		productPropImgsQuery.setSort("sort_num asc");
		List<ProductPropImgs> listPropImgs = productPropImgsService.queryList(productPropImgsQuery);

		// 查询商品sku属性
		ProductSkuQuery productSkuQuery = new ProductSkuQuery();
		productSkuQuery.setProductId(id);
		List<ProductSku> listProductSku = productSkuService.queryList(productSkuQuery);

		// 查询推荐商品：
		/**
		 * 通过shopId和商品状态来进行查询
		 */
		model.addAttribute("groomShops", productService.getGroomProduct(agent.getShopId()));
		if (p.getExpressId() != null) {
			// 查询运费模板
			Express express = expressService.load(p.getExpressId());
			model.addAttribute("express", express);
		}
		// 优惠信息
		/*
		 * Discount discount = discountService.load(p.getDiscountId());
		 * model.addAttribute("discount", discount);
		 */

		// 查询组合商品
		CombinationProductQuery combinationProductQuery = new CombinationProductQuery();
		combinationProductQuery.setProduct1Id(id);
		// 在售的
		combinationProductQuery.setCpStatus(1);
		List<CombinationProduct> listCP = combinationProductService.queryByProductId(combinationProductQuery,
				agent.getShopId());

		if (listCP != null && listCP.size() > 0) {
			model.addAttribute("combinationProduct", listCP.get(0));
		}

		model.addAttribute("product", p);
		model.addAttribute("propImgs", listPropImgs);
		if (listProductSku != null && listProductSku.size() > 0) {
			model.addAttribute("productSkus", JsonUtils.beanToJSON(listProductSku));
		}

		// model.addAttribute("listSkuNameAndValue", listSkuNameAndValue);

		model.addAttribute("title", p.getName());
		// 店铺名称
		model.addAttribute("shopName", agent.getShopName());
		// 店铺地址
		model.addAttribute("shopUrl", agent.getUrl());

		model.addAttribute("shopId", shopId);

		return "pages/itemdetail";
	}

	/**
	 * 组合商品详情页
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "combinationProdDetail/{shopId}-{id}", method = RequestMethod.GET)
	public String combinationProductDetail(@PathVariable("id") Long id, @PathVariable("shopId") String shopId,
			Model model, HttpServletRequest request) throws ServiceException {
		PortalAgent agent = portalAgentSession.get(request);
		CombinationProductQuery combinationProductQuery = new CombinationProductQuery();
		combinationProductQuery.setProduct1Id(id);
		// 在售的
		combinationProductQuery.setCpStatus(1);
		// 查询组合商品
		List<CombinationProduct> listCP = combinationProductService.queryByProductId(combinationProductQuery,
				agent.getShopId());
		List<CombinationProductDto> listCPD = new ArrayList<>();
		if (listCP != null && listCP.size() > 0) {
			ProductSkuQuery psq = new ProductSkuQuery();
			for (CombinationProduct c : listCP) {

				CombinationProductDto dto = new CombinationProductDto(c);

				if (dto.getProduct1Id() != null) {
					psq.setProductId(dto.getProduct1Id());
					dto.setProduct1Skus(productSkuService.queryList(psq));
				}
				if (dto.getProduct2Id() != null) {
					psq.setProductId(dto.getProduct2Id());
					dto.setProduct2Skus(productSkuService.queryList(psq));
				}
				if (dto.getProduct3Id() != null) {
					psq.setProductId(dto.getProduct3Id());
					dto.setProduct3Skus(productSkuService.queryList(psq));
				}
				if (dto.getProduct4Id() != null) {
					psq.setProductId(dto.getProduct4Id());
					dto.setProduct4Skus(productSkuService.queryList(psq));
				}
				if (dto.getProduct5Id() != null) {
					psq.setProductId(dto.getProduct5Id());
					dto.setProduct5Skus(productSkuService.queryList(psq));
				}

				listCPD.add(dto);
			}
		}

		if (listCPD != null && listCPD.size() > 0) {
			model.addAttribute("combinationProducts", JsonUtils.beanToJSON(listCPD));
		}
		model.addAttribute("title", "自由搭配");
		model.addAttribute("shopId", shopId);
		return "pages/classicalmix";
	}

	/**
	 * 查询商品列表
	 * 
	 * @param productShopQuery
	 * @param request
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "list/{shopId}")
	public String list(@QueryPage(defaultPageSize = 10) ProductShopQuery productShopQuery,
			@PathVariable("shopId") String shopId, HttpServletRequest request, Model model) throws ServiceException {
		PortalAgent agent = portalAgentSession.get(request);
		PageResult<ProductShopDto> list = productList(productShopQuery, agent.getShopId());
		model.addAttribute("page", list);
		if (productShopQuery != null && productShopQuery.getCategoryId() != null) {
			Category category = categoryService.load(productShopQuery.getCategoryId());
			if (category != null) {
				model.addAttribute("title", category.getName());
			} else {
				model.addAttribute("title", "商品列表");
			}
		} else {
			model.addAttribute("title", "商品列表");
		}

		// 查询商品轮播图
		CategoryQuery categoryQuery = new CategoryQuery();
		categoryQuery.setShopId(agent.getShopId());
		if (productShopQuery != null && productShopQuery.getCategoryId() != null) {
			categoryQuery.setIdArray(productShopQuery.getCategoryId());
			List<CategoryDto> listS = categoryService.getCategoryList(categoryQuery);
			if (listS != null && listS.size() > 0) {
				model.addAttribute("categoryDto", listS.get(0));
			}
		} else {
			List<CategoryDto> listS = categoryService.getCategoryList(categoryQuery);
			if (listS != null && listS.size() > 0) {
				model.addAttribute("categoryDto", listS.get(0));
			}
		}

		// 底部菜单查询
		CategoryQuery query = new CategoryQuery();
		query.setShopId(agent.getShopId());
		query.setSort("sort_num asc");
		// 查询使用中的
		query.setStatus(1);
		List<Category> listCategory = categoryService.queryList(query);
		model.addAttribute("categorys", listCategory);
		// 返回查询条件
		model.addAttribute("categoryId", productShopQuery.getCategoryId());
		// 店铺名称
		model.addAttribute("shopName", agent.getShopName());
		// 店铺地址
		model.addAttribute("shopUrl", agent.getUrl());
		
		model.addAttribute("shopId", shopId);
		
		return "pages/itemlist";
	}

	/**
	 * 商品列表查询公共方法
	 * 
	 * @param productQuery
	 * @return
	 * @throws ServiceException
	 */
	private PageResult<ProductShopDto> productList(ProductShopQuery productShopQuery, Long shopId)
			throws ServiceException {
		productShopQuery.setStatusArray(1);
		productShopQuery.setSort("GMT_CREATE DESC");
		productShopQuery.setShopId(shopId);
		List<ProductShopDto> list = productShopService.queryList(productShopQuery);
		int count = productShopService.queryCount(productShopQuery);
		return PageResult.create(productShopQuery, list, count);
	}

	/**
	 * 查询商品列表返回json
	 * 
	 * @param productQuery
	 * @param request
	 * @param model
	 * @throws ServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "listJson/{shopId}")
	public LunjarApiResponse listJson(@QueryPage(defaultPageSize = 10) ProductShopQuery productShopQuery,
			HttpServletRequest request, Model model) throws ServiceException {
		PortalAgent agent = portalAgentSession.get(request);
		try {
			return LunjarApiResponse.create(productList(productShopQuery, agent.getShopId()));
		} catch (Exception e) {
			logger.error("查询错误商品列表失败", e);
			return LunjarApiResponse.create(LunjarApiResponseCode._1000002.getCode(),
					LunjarApiResponseCode._1000002.getMsg());
		}

	}

	/**
	 * 查询sku价格
	 * 
	 * @param productSkuQuery
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getSkuPrice/{shopId}")
	public LunjarApiResponse skuPrice(ProductSkuQuery productSkuQuery, HttpServletRequest request) {
		if (productSkuQuery.getProductId() == null || productSkuQuery.getProductId().equals("")) {
			logger.error("---skuPrice 商品id为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000002.getCode(), "商品编号为空");
		}
		if (StringUtils.isEmpty(productSkuQuery.getProperties())) {
			logger.error("---skuPrice 属性为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000002.getCode(), "商品属性为空");
		}
		productSkuQuery.setStatus(1);
		List<ProductSku> list = productSkuService.queryList(productSkuQuery);
		return LunjarApiResponse.create(list);
	}

	/**
	 * 添加购物车
	 * 
	 * @throws ServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "addCart/{shopId}")
	public LunjarApiResponse addCart(Cart cart, HttpServletRequest request) throws ServiceException {
		PortalAgent agent = portalAgentSession.get(request);
		CartQuery cartQuery = new CartQuery();
		cartQuery.setBuyerId(agent.getBuyerId());
		cartQuery.setProductId(cart.getProductId());

		if (cart.getSkuId() != null && !"".equals(cart.getSkuId())) {
			cartQuery.setSkuId(cart.getSkuId());
		}
		List<CartDto> listCartDto = cartService.queryList(cartQuery);
		Long id = null;
		if (listCartDto != null && listCartDto.size() > 0) {
			// 说明已经存在记录，只更新数量
			cart.setBuyerId(agent.getBuyerId());
			cart.setId(listCartDto.get(0).getId());
			cart.setNum(listCartDto.get(0).getNum() + cart.getNum());
			cartService.update(cart);
			id = listCartDto.get(0).getId();
		} else {
			cart.setBuyerId(agent.getBuyerId());
			cart.setShopId(agent.getShopId());
			id = cartService.add(cart);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("msg", "添加购物车成功");
		return LunjarApiResponse.create(map);
	}

	/**
	 * 购物车列表
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "listCart/{shopId}")
	public String listCart(Model model, HttpServletRequest request,@PathVariable("shopId") String shopId) throws ServiceException {
		PortalAgent agent = portalAgentSession.get(request);
		CartQuery query = new CartQuery();
		query.setBuyerId(agent.getBuyerId());
		List<CartDto> listCart = cartService.queryList(query);
		List<EnterpriseProModel> list = productService.getCartList(listCart, null);
		// 查询商户的优惠信息，一个商户会有多条优惠信息
		if (list != null && list.size() > 0) {
			DiscountQuery dq = new DiscountQuery();
			for (EnterpriseProModel epm : list) {
				dq.setEnterpriseId(epm.getEnterpriseId());
				List<Discount> discounts = discountService.queryList(dq);
				// 转换成JSON形式
				if (discounts != null && discounts.size() > 0) {
					epm.setDiscounts(JsonUtils.beanToJSON(discounts));
				}
				Enterprise ese = enterpriseService.load(epm.getEnterpriseId());
				if (ese != null) {
					epm.setHeadPicUrl(ese.getHeadPicUrl());
				}
			}
		}

		model.addAttribute("listCart", list);
		
		model.addAttribute("shopId", shopId
				);
		model.addAttribute("title", "购物车");
		return "pages/shopcart";
	}

	/**
	 * 购物编辑
	 * 
	 * @param cart
	 * @return
	 */
	@RequestMapping(value = "editCart/{shopId}")
	@ResponseBody
	public LunjarApiResponse editCart(Cart cart, String flag, HttpServletRequest request) {
		PortalAgent agent = portalAgentSession.get(request);
		if ("1".equals(flag)) {
			if (cart == null) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据不正确");
			}

			if (cart.getNum() < 1) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "购物车数量必须大于0");
			}
			cart.setBuyerId(agent.getBuyerId());
			cartService.update(cart);
		} else {
			Long id = cart.getId();
			cartService.delete(id);
		}

		return LunjarApiResponse.create();
	}

	/**
	 * 通过商品id查询商品信息和sku信息通过json返回
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getProdInfoReJson/{shopId}")
	public LunjarApiResponse getProdInfoReJson(Long id) {

		ProductQuery productQuery = new ProductQuery();
		productQuery.setIdArray(id);
		productQuery.setStatus(1);
		List<Product> listProduct = productService.queryList(productQuery);
		Map<String, Object> map = new HashMap<>();
		if (listProduct != null && listProduct.size() > 0) {
			map.put("product", listProduct.get(0));
		} else {
			logger.error("id{}查询商品为空", id);
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "商品编号不存在");
		}

		ProductSkuQuery productSkuQuery = new ProductSkuQuery();
		productSkuQuery.setProductId(listProduct.get(0).getId());
		productSkuQuery.setStatus(1);
		List<ProductSku> listProductSku = productSkuService.queryList(productSkuQuery);
		map.put("productSkus", listProductSku);
		return LunjarApiResponse.create(map);
	}

	/**
	 * 获取购物车信息，返回json
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listCartJson/{shopId}")
	public LunjarApiResponse getCartJson(HttpServletRequest request) {
		PortalAgent agent = portalAgentSession.get(request);
		CartQuery query = new CartQuery();
		query.setBuyerId(agent.getBuyerId());
		List<CartDto> listCart = cartService.queryList(query);
		Map<String, Object> map = new HashMap<>();
		map.put("carts", listCart);
		if (listCart != null && listCart.size() > 0) {
			map.put("size", listCart.size());
		} else {
			map.put("size", 0);
		}
		return LunjarApiResponse.create(map);
	}
}
