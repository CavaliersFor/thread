package com.lunjar.ebp.admin.web.controller;
//package com.lunjar.demo.web.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ancun.bps.bizsupport.dto.ProductDto;
//import com.ancun.bps.bizsupport.exception.BizSupportException;
//import com.ancun.bps.bizsupport.model.Product;
//import com.ancun.bps.bizsupport.model.ProductItem;
//import com.ancun.bps.bizsupport.model.ProductItemFlow;
//import com.ancun.bps.bizsupport.model.ProductKeys;
//import com.ancun.bps.bizsupport.model.Template;
//import com.ancun.bps.bizsupport.query.ProductKeysQuery;
//import com.ancun.bps.bizsupport.query.ProductQuery;
//import com.ancun.bps.bizsupport.query.TemplateQuery;
//import com.ancun.bps.bizsupport.service.ProductItemFlowService;
//import com.ancun.bps.bizsupport.service.ProductItemService;
//import com.ancun.bps.bizsupport.service.ProductKeysService;
//import com.ancun.bps.bizsupport.service.ProductService;
//import com.ancun.bps.bizsupport.service.TemplateService;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.web.annotation.QueryPage;
//import com.ancun.products.core.web.exception.WebException;
//import com.ancun.products.core.webapi.AncunApiResponse;
//import com.lunjar.demo.biz.service.ProductMangerService;
//
//@Controller
//@RequestMapping(value = "product")
//public class ProductController {
//    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private ProductItemService productItemService;
//    @Autowired
//    private ProductMangerService productMangerService;
//    @Autowired
//    private ProductItemFlowService productItemFlowService;
//    @Autowired
//    private ProductKeysService productKeysService;
//    @Autowired
//    private TemplateService templateService;
//
//    @RequestMapping(value = "/list")
//    public String list(Model model, @QueryPage(defaultPageSize = 10) ProductQuery productQuery) throws WebException, BizSupportException{
//    	return getViewAndPageData(model, productQuery);
//    }
//
//    /** 查询获取列表数据并渲染 
//     * @throws BizSupportException */
//	private String getViewAndPageData(Model model, ProductQuery query) throws BizSupportException {
//		PageResult<Product> page = productMangerService.getListPage(query);
//		model.addAttribute("page", page);
//		model.addAttribute("query", query);
//		initModelProductList(model, page.getData());
//		return "product/product-list";
//	}
//
//	private void initModelProductList(Model model, List<Product> list) throws BizSupportException {
//	
//		Map<Integer, String> partnerMap = productMangerService.getPartnerMap(list);
//		model.addAttribute("partnerMap", partnerMap);
//	}
//
//	@RequestMapping(value = "/detail")
//    public String detail(Model model, @RequestParam("id")Integer id) throws WebException{
//        ProductDto productDto = productService.load(id);
//		List<ProductItem> productItemList = productItemService.getList(id);
//		List<ProductItemFlow> allProductItemFlowList = new ArrayList<>();
//		List<Template>allTemplateList = new ArrayList<>();
//		ProductKeysQuery productKeysQuery = new ProductKeysQuery();
//		productKeysQuery.setProductId(id);
//		List<ProductKeys> productKeysList = productKeysService.getList(productKeysQuery);
//
//		List<ProductKeys> formalProductKeysList = new ArrayList<>();
//		List<ProductKeys> sandboxProductKeysList = new ArrayList<>();
//
//		for(ProductKeys productKeys : productKeysList){
//		    if(productKeys.getKeyType() != 2){
//		        formalProductKeysList.add(productKeys);
//		    }else if(productKeys.getKeyType() != 1){
//		        sandboxProductKeysList.add(productKeys);
//		    }
//		}
//		List<Template> templateList = null;
//		TemplateQuery templateQuery = null;
//		for(ProductItem productItem : productItemList){
//		    List<ProductItemFlow> productItemFlowList = productItemFlowService.getList(productItem.getId());
//		    templateQuery = new TemplateQuery();
//		    templateQuery.setItemId(productItem.getId());
//		    templateList = templateService.list(templateQuery);
//		    allProductItemFlowList.addAll(productItemFlowList);
//		    allTemplateList.addAll(templateList);
//		}
//		model.addAttribute("id", id);
//		model.addAttribute("productDto", productDto);
//		model.addAttribute("templateList", allTemplateList);
//		model.addAttribute("productItemList", productItemList);
//		model.addAttribute("productKeysList", productKeysList);
//		model.addAttribute("formalProductKeysList", formalProductKeysList);
//		model.addAttribute("sandboxProductKeysList", sandboxProductKeysList);
//		model.addAttribute("productItemFlowList", allProductItemFlowList);
//        return "product/product-detail";
//    }
//	 
//	@RequestMapping(value = "/update", method = RequestMethod.GET)
//    public String update(Model model, @RequestParam("id")Integer id) throws WebException{
//        ProductDto productDto = productService.load(id);
//		List<ProductItem> productItemList = productItemService.getList(id);
//		List<ProductItemFlow> allProductItemFlowList = new ArrayList<>();
//		List<Template>allTemplateList = new ArrayList<>();
//		ProductKeysQuery productKeysQuery = new ProductKeysQuery();
//		productKeysQuery.setProductId(id);
//		List<ProductKeys> productKeysList = productKeysService.getList(productKeysQuery);
//
//		List<ProductKeys> formalProductKeysList = new ArrayList<>();
//		List<ProductKeys> sandboxProductKeysList = new ArrayList<>();
//
//		for(ProductKeys productKeys : productKeysList){
//		    if(productKeys.getApiType() != 1){
//		        formalProductKeysList.add(productKeys);
//		    }else if(productKeys.getApiType() != 2){
//		        sandboxProductKeysList.add(productKeys);
//		    }
//		}
//
//		List<Template> templateList = null;
//		TemplateQuery templateQuery = null;
//		for(ProductItem productItem : productItemList){
//		    List<ProductItemFlow> productItemFlowList = productItemFlowService.getList(productItem.getId());
//		    templateQuery = new TemplateQuery();
//		    templateQuery.setItemId(productItem.getId());
//		    templateList = templateService.list(templateQuery);
//		    allProductItemFlowList.addAll(productItemFlowList);
//		    allTemplateList.addAll(templateList);
//		}
//		model.addAttribute("id", id);
//		model.addAttribute("productDto", productDto); 
//		model.addAttribute("templateList", allTemplateList);
//		model.addAttribute("productItemList", productItemList);
//		model.addAttribute("productKeysList", productKeysList);
//		model.addAttribute("formalProductKeysList", formalProductKeysList);
//		model.addAttribute("sandboxProductKeysList", sandboxProductKeysList);
//		model.addAttribute("productItemFlowList", allProductItemFlowList);
//        return "product/product-update";
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    @ResponseBody
//    public AncunApiResponse update(Model model, ProductDto productDto) throws WebException{
//        productService.update(productDto);
//        return AncunApiResponse.create(productDto.getProduct().getId());
//    }
//
//    @RequestMapping(value = "/update-status", method = RequestMethod.POST)
//    @ResponseBody
//    public AncunApiResponse updateStatus(@RequestParam("id")Integer id, @RequestParam("status")Integer status, @RequestParam("type")Integer type) throws WebException{
//        ProductDto productDto = new ProductDto();
//        Product product = new Product();
//        product.setId(id);
//        if (type == 2) { //公司章变更操作
//			product.setIsOpenCompanySeal(status);
//		}else {
//			product.setStatus(status);
//		}
//        productDto.setProduct(product);
//        productService.update(productDto);
//        return AncunApiResponse.create(productDto.getProduct().getId());
//    }
//    
//    
//    
//	/**
//	 * 获取接入者服务类型
//	* @param partnerId
//	* @param request
//	* @throws WebException
//	* <p>
//	* author: <a href="mailto:qiande@ancun.com">QianDe</a><br>
//	* create at: 2016年4月26日 下午5:37:03
//	 * @throws BizSupportException 
//	 */
//	@RequestMapping(value = "/getApiTypes", method = RequestMethod.GET)
//	@ResponseBody
//	public AncunApiResponse getApiTypes(Integer productId) throws WebException, BizSupportException{
//		return AncunApiResponse.create(productMangerService.getApiTypes(productId));
//	}
//	
//	
//	/**
//	 * 授权服务
//	* @param apiTypes 选择的服务项
//	* @param request
//	* @throws WebException
//	* <p>
//	* author: <a href="mailto:qiande@ancun.com">QianDe</a><br>
//	* create at: 2016年4月26日 下午4:52:03
//	 * @throws BizSupportException 
//	 */
//	@RequestMapping(value = "/grant", method = RequestMethod.POST)
//	public String grant(Integer[] apiTypes,Integer productId) throws WebException, BizSupportException{
//		productMangerService.grant(productId,apiTypes);
//		return "redirect:/product/list";
//	}
//}
//
