package com.lunjar.ebp.admin.web.controller.template;
//package com.lunjar.demo.web.controller.template;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ancun.bps.bizsupport.dto.ProductDto;
//import com.ancun.bps.bizsupport.dto.TemplateDto;
//import com.ancun.bps.bizsupport.model.Partner;
//import com.ancun.bps.bizsupport.model.Product;
//import com.ancun.bps.bizsupport.model.ProductItem;
//import com.ancun.bps.bizsupport.model.ProductItemFlow;
//import com.ancun.bps.bizsupport.model.Template;
//import com.ancun.bps.bizsupport.query.PartnerProductQuery;
//import com.ancun.bps.bizsupport.query.PartnerQuery;
//import com.ancun.bps.bizsupport.query.ProductItemFlowQuery;
//import com.ancun.bps.bizsupport.query.ProductItemQuery;
//import com.ancun.bps.bizsupport.query.ProductQuery;
//import com.ancun.bps.bizsupport.query.TemplateQuery;
//import com.ancun.bps.bizsupport.service.PartnerService;
//import com.ancun.bps.bizsupport.service.ProductItemFlowService;
//import com.ancun.bps.bizsupport.service.ProductItemService;
//import com.ancun.bps.bizsupport.service.ProductService;
//import com.ancun.bps.bizsupport.service.TemplateService;
//import com.ancun.bps.core.utils.BpsHttpUtils;
//import com.ancun.products.core.exception.ServiceException;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.utils.DateUtils;
//import com.ancun.products.core.web.annotation.QueryPage;
//import com.ancun.products.core.webapi.AncunApiResponse;
//import com.lunjar.demo.biz.authority.AdminAuthority;
//
///**
// * 模版管理 Controller
// * 
// * <p>
// * create at 2016年4月28日 上午9:32:51
// * 
// * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
// * @version %I%, %G%
// * @see
// */
//@Controller
//@RequestMapping(value = "template")
//@AdminAuthority("template")
//public class TemplateController {
//
//	@Autowired
//	private TemplateService templateService;
//	@Autowired
//	private ProductService productService;
//	@Autowired
//	private ProductItemService productItemService;
//	@Autowired
//	private ProductItemFlowService productItemFlowService;
//	@Autowired
//	private PartnerService partnerService;
//
//	/**
//	 * 模版列表首页
//	 */
//	@SuppressWarnings("static-access")
//	@RequestMapping(value = "list")
//	@AdminAuthority("template_list")
//	public String index(Model model, @QueryPage TemplateQuery templateQuery,String searchType,String searchText,HttpServletRequest request){
//		if(searchType!=null&&searchText!=null){
//			if(searchType.equals("1")&&searchText!=""){//接入者
//				PartnerQuery partnerQuery=new PartnerQuery();
//				partnerQuery.setPartnerName(searchText);
//				List<Partner> pList = partnerService.list(partnerQuery);
//				if(pList!=null&&pList.size()>0&&pList.get(0)!=null){
//					templateQuery.setPartnerId(pList.get(0).getId());
//				}
//			}else if(searchType.equals("2")&&searchText!=""){//产品查询
//				ProductQuery productQuery=new ProductQuery();
//				productQuery.setProductName(searchText);
//				List<Product> pList = productService.getProductList(productQuery);
//				if(pList!=null&&pList.size()>0&&pList.get(0)!=null){
//					templateQuery.setProductId(pList.get(0).getId());
//				}
//			}else if(searchType.equals("3")&&searchText!=""){//产品查询
//				ProductItemQuery productItemQuery=new ProductItemQuery();
//				productItemQuery.setItemName(searchText);
//				List<ProductItem> pList = productItemService.queryItemList(productItemQuery);
//				if(pList!=null&&pList.size()>0&&pList.get(0)!=null){
//					templateQuery.setItemId(pList.get(0).getId());
//				}
//			}
//			model.addAttribute("searchType",searchType);
//			model.addAttribute("searchText",searchText);
//		}
//		
//		templateQuery.setSort("partner_id desc,version desc");
//		List<Template> list = templateService.list(templateQuery);// 获取模版分页集合
//		int count = templateService.queryCount(templateQuery);// 获取统计模版数量
//		if (count > 0) {
//			initModelTemplateList(model, list);
//			PageResult<Template> page = null;
//			page = page.create(templateQuery, list, count);
//			model.addAttribute("page", page);
//		}
//		model.addAttribute("query", templateQuery);
//		return "template/template-list";
//	}
//
//	/**
//	 * 获取产品列表
//	 * 
//	 * @param productType
//	 * @return
//	 *         <p>
//	 *         author: <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
//	 *         <br>
//	 *         create at: 2016年5月11日 下午1:39:01
//	 */
//	@RequestMapping(value = "getProductList", method = RequestMethod.POST)
//	@ResponseBody
//	@AdminAuthority({ "template_list", "template_upload" })
//	public List<Product> getProductList(String productType) {
//		if (StringUtils.isBlank(productType)) {
//			return null;
//		}
//		ProductQuery productQuery = new ProductQuery();
//		productQuery.setProductType(productType);
//		return productService.getProductList(productQuery);
//	}
//
//	@RequestMapping(value = "getItemList", method = RequestMethod.POST)
//	@ResponseBody
//	@AdminAuthority({ "template_list", "template_upload" })
//	public List<ProductItem> getItemList(Integer productId){
//		if (productId == null) {
//			return null;
//		}
//		ProductItemQuery productItemQuery = new ProductItemQuery();
//		productItemQuery.setProductId(productId);
//		return productItemService.queryItemList(productItemQuery);
//	}
//
//	@RequestMapping(value = "getFlowList", method = RequestMethod.POST)
//	@ResponseBody
//	@AdminAuthority({ "template_list", "template_upload" })
//	public List<ProductItemFlow> getFlowList(Integer itemId){
//		if (itemId == null) {
//			return null;
//		}
//		ProductItemFlowQuery productItemFlowQuery = new ProductItemFlowQuery();
//		productItemFlowQuery.setItemId(itemId);
//		return productItemFlowService.queryItemFlowList(productItemFlowQuery);
//	}
//
//	@RequestMapping(value = "getVersion")
//	@ResponseBody
//	@AdminAuthority({ "template_list", "template_upload" })
//	public String getVersion(Template template){
//		int version = templateService.getNextVersion(template);
//		String versionStr = "V" + String.valueOf(version) + ".0";
//		return versionStr;
//	}
//
//	@RequestMapping(value = "upload", method = RequestMethod.POST)
//	@ResponseBody
//	@AdminAuthority({ "template_upload" })
//	public AncunApiResponse upload(@RequestParam("productId") Integer productId,
//			@RequestParam("itemId") Integer itemId, @RequestParam("path") String path,
//			@RequestParam(value = "flowId", required = false) Integer flowId) {
//		InputStream is = null;
//		try {
//			if (productId != null && itemId != null && path != null) {
//				TemplateDto templateDto = new TemplateDto();
//				templateDto.setProductId(productId);
//				ProductDto productDto = productService.load(productId);
//				templateDto.setPartnerId(productDto.getPartnerId());
//				templateDto.setItemId(itemId);
//				if (flowId != null) {
//					templateDto.setFlowId(flowId);
//				}
//				is = BpsHttpUtils.getInputStream2(path);
//				int id = templateService.addTemplate(templateDto, is);
//				return AncunApiResponse.create(id);
//			} else {
//				return AncunApiResponse.create("参数不能为空");
//			}
//		} catch (ServiceException e) {
//			return AncunApiResponse.create(e.getMessage());
//		} finally {
//			try {
//				is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 模版编辑
//	 * 
//	 * @param id
//	 * @param gmtStart
//	 * @return
//	 *         <p>
//	 *         author: <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
//	 *         <br>
//	 *         create at: 2016年5月11日 下午9:24:00
//	 */
//	@RequestMapping(value = "edit")
//	@ResponseBody
//	@AdminAuthority({ "template_enableAndDisable" })
//	public AncunApiResponse edit(Integer id, String gmtStart) {
//		Date dateGmtStart = DateUtils.parse(gmtStart, DateUtils.FORMAT_LONG);
//		templateService.enableTemplate(id, dateGmtStart);
//		return AncunApiResponse.create();
//	}
//
//	/**
//	 * 模版删除
//	 * 
//	 * @param id
//	 * @return
//	 *         <p>
//	 *         author: <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
//	 *         <br>
//	 *         create at: 2016年5月11日 下午9:23:54
//	 */
//	@RequestMapping(value = "delete")
//	@ResponseBody
//	@AdminAuthority({ "template_delete" })
//	public AncunApiResponse delete(Integer id) {
//		try {
//			templateService.deleteTemplate(id);
//		} catch (ServiceException e) {
//			return AncunApiResponse.create(e.getMessage());
//		}
//		return AncunApiResponse.create();
//	}
//
//	/**
//	 * 处理templateList扩展的产品信息
//	 */
//	void initModelTemplateList(Model model, List<Template> list){
//		if (list != null && list.iterator().hasNext()) {// 判断模版集合非空
//			List<Integer> productIds = new ArrayList<Integer>();
//			List<Integer> itemIds = new ArrayList<Integer>();
//			List<Integer> flowIds = new ArrayList<Integer>();
//			List<Integer> partnerIds = new ArrayList<Integer>();
//			for (Template t : list) {
//				if (!productIds.contains(t.getProductId())) {
//					productIds.add(t.getProductId());
//				}
//				if (!itemIds.contains(t.getItemId())) {
//					itemIds.add(t.getItemId());
//				}
//				if (!flowIds.contains(t.getFlowId())) {
//					flowIds.add(t.getFlowId());
//				}
//				if (!partnerIds.contains(t.getPartnerId())) {
//					partnerIds.add(t.getPartnerId());
//				}
//			}
//			PartnerProductQuery partnerProductQuery = new PartnerProductQuery();
//			partnerProductQuery.setIdArray((Integer[]) productIds.toArray(new Integer[0]));
//			List<Product> productList = productService.getList(partnerProductQuery);// 获取产品
//			if (productList != null && productList.iterator().hasNext()) {// 判断产品集合非空
//				Map<Integer, String> productMap = new HashMap<Integer, String>();
//				for (Product p : productList) {
//					productMap.put(p.getId(), p.getProductName());
//				}
//				model.addAttribute("productMap", productMap);// 产品Map
//			}
//			ProductItemQuery productItemQuery = new ProductItemQuery();
//
//			productItemQuery.setIdArray((Integer[]) itemIds.toArray(new Integer[0]));
//			List<ProductItem> itemList = productItemService.queryItemList(productItemQuery);// 获取业务
//			if (itemList != null && itemList.iterator().hasNext()) {// 判断业务集合非空
//				Map<Integer, String> itemMap = new HashMap<Integer, String>();
//				for (ProductItem pi : itemList) {
//					itemMap.put(pi.getId(), pi.getItemName());
//				}
//				model.addAttribute("itemMap", itemMap);// 业务流程Map
//			}
//			ProductItemFlowQuery productItemFlowQuery = new ProductItemFlowQuery();
//			productItemFlowQuery.setIdArray((Integer[]) flowIds.toArray(new Integer[0]));
//			List<ProductItemFlow> flowList = productItemFlowService.queryItemFlowList(productItemFlowQuery);// 获取业务流程
//			if (flowList != null && flowList.iterator().hasNext()) {// 判断流程集合非空
//				Map<Integer, String> flowMap = new HashMap<Integer, String>();
//				for (ProductItemFlow pif : flowList) {
//					flowMap.put(pif.getId(), pif.getFlowName());
//				}
//				model.addAttribute("flowMap", flowMap);// 业务流程Map
//			}
//			PartnerQuery partnerQuery = new PartnerQuery();
//			partnerQuery.setIdArray((Integer[]) partnerIds.toArray(new Integer[partnerIds.size()]));
//			List<Partner> partnerList = partnerService.list(partnerQuery);// 获取接入者
//			if (partnerList != null && partnerList.iterator().hasNext()) {// 判断接入者集合非空
//				Map<Integer, String> partnerMap = new HashMap<Integer, String>();
//				for (Partner p : partnerList) {
//					partnerMap.put(p.getId(), p.getPartnerName());
//				}
//				model.addAttribute("partnerMap", partnerMap);// 接入者Map
//			}
//		}
//	}
//}