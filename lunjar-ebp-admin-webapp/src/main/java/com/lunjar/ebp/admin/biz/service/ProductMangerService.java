package com.lunjar.ebp.admin.biz.service;
//package com.lunjar.demo.biz.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ancun.bps.bizsupport.dto.ProductDto;
//import com.ancun.bps.bizsupport.model.PartnerProduct;
//import com.ancun.bps.bizsupport.model.Product;
//import com.ancun.bps.bizsupport.model.ProductKeys;
//import com.ancun.bps.bizsupport.model.ProductKeysApi;
//import com.ancun.bps.bizsupport.query.PartnerQuery;
//import com.ancun.bps.bizsupport.query.ProductKeysQuery;
//import com.ancun.bps.bizsupport.query.ProductQuery;
//import com.ancun.bps.bizsupport.service.PartnerService;
//import com.ancun.bps.bizsupport.service.ProductKeysApiService;
//import com.ancun.bps.bizsupport.service.ProductKeysService;
//import com.ancun.bps.bizsupport.service.ProductService;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.utils.StringUtils;
//
///**
// * 产品管理service
// * 
// * <p>
// * create at 2016年4月19日 上午11:01:55
// * @author <a href="mailto:qiande@ancun.com">QianDe</a>
// * @version %I%, %G%
// * @see
// */
//@Service("productMangerService")
//public class ProductMangerService  {
//
//	
//	@Autowired
//    private ProductKeysService productKeysService;
//	
//	@Autowired
//    private ProductKeysApiService productKeysApiService;
//	@Autowired
//	private PartnerService partnerService;
//	@Autowired
//	private ProductService productService;
//	
//	/**
//	 * 获取接入者服务类型
//	* @param productId
//	* <p>
//	* author: <a href="mailto:qiande@ancun.com">QianDe</a><br>
//	* create at: 2016年4月19日 上午11:02:33
//	 */
//	public String getApiTypes(Integer productId){
//		ProductKeysQuery productKeysQuery = new ProductKeysQuery();
//		productKeysQuery.setProductId(productId);
//		List<ProductKeys> keyList = productKeysService.getList(productKeysQuery);
//		if(null == keyList || keyList.size() == 0){
//			return null;
//		}
//		Integer keyId = keyList.get(0).getId();
//		ProductKeysApi productKeysApi = productKeysApiService.load(keyId);
//		if(null == productKeysApi){
//			return null;
//		}
//		return productKeysApi.getApiTypes();
//	}
//	
//	/**
//	 * 对产品授权服务
//	* @param productId 产品id
//	* @param apiTypes 服务类型
//	* <p>
//	* author: <a href="mailto:qiande@ancun.com">QianDe</a><br>
//	* create at: 2016年4月27日 上午11:06:32
//	 * @param apiTypes
//	 */
//	public void grant(Integer productId, Integer[] apiTypes){
//		ProductKeysQuery productKeysQuery = new ProductKeysQuery();
//		productKeysQuery.setProductId(productId);
//		List<ProductKeys> keyList = productKeysService.getList(productKeysQuery);
//		if(null ==keyList || keyList.size() == 0) {
//			return;
//		}
//		Integer[] keyIds = new Integer[keyList.size()];
//		for (int i = 0; i < keyList.size(); i++) {
//			keyIds[i] = keyList.get(i).getId();
//		}
//		
//		Integer apiType = 0;
//		for (Integer type : apiTypes) {
//			apiType += type;
//		}
//		
//		productKeysApiService.updates(keyIds, fullType(apiType));//批量更新产品服务
//	}
//	
//	//补零 000000
//	private String fullType(Integer apiType){
//		if(apiType == 0){
//			return "000000";
//		}else{
//			String type = apiType + "";
//			int len = type.length();
//			if(len != 6){
//				for (int i = 0; i < 6-len; i++) {
//					type = "0" + type;
//				}
//				return type;
//			}else{
//				return type;
//			}
//		}
//	}
//
//	public PageResult<Product> getListPage(ProductQuery query) {
//		if (StringUtils.isNotBlank(query.getPartnerName())) {
//				PartnerQuery partnerQuery = new PartnerQuery();
//				partnerQuery.setPartnerName(query.getPartnerName());
//				List<PartnerProduct> partnerProductList = partnerService.queryPartnerProductList(partnerQuery);
//				Integer[] idArray = new Integer[partnerProductList.size()];
//				int length = 0;
//				if(partnerProductList ==null || partnerProductList.isEmpty()){
//					return null;
//				}
//				for (PartnerProduct partnerProduct : partnerProductList) {
//					idArray[length++] = partnerProduct.getProductId();
//				}
//				query.setIdArray(idArray);
//		}
//		List<Product> data = productService.getProductList(query);
//		int recordCount = productService.getCount(query);
//		return PageResult.create(query, data, recordCount);
//	}
//
//	public Map<Integer, String> getPartnerMap(List<Product> list){
//		Map<Integer, String> map = new HashMap<>();
//		List<Integer> productIds = new ArrayList<>();
//		if(null !=list && list.size()>0){
//			for(Product p: list) {
//				productIds.add(p.getId());
//			}
//			map = partnerService.getPartnerMap(productIds.toArray());
//		}
//		return map;
//	}
//
//	public void addIpBlack(Integer id, String ipBlack){
//		
//		ProductDto productDto = productService.load(id);
//		if(null !=productDto && null != productDto.getProduct()){
//			productDto.getProduct().setIpBlack(productDto.getProduct().getIpBlack()+ ',' + ipBlack);
//			productService.update(productDto);
//		}
//	}
//}
