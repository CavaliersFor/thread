package com.lunjar.ebp.bizsupport.test.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.CartDto;
import com.lunjar.ebp.bizsupport.dto.CombinDto;
import com.lunjar.ebp.bizsupport.dto.EnterpriseProModel;
import com.lunjar.ebp.bizsupport.dto.ProductDto;
import com.lunjar.ebp.bizsupport.enums.EnumPostProduct;
import com.lunjar.ebp.bizsupport.enums.EnumProductDistributionMode;
import com.lunjar.ebp.bizsupport.enums.EnumProductPropImgsStatus;
import com.lunjar.ebp.bizsupport.enums.EnumProductPropImgsType;
import com.lunjar.ebp.bizsupport.enums.EnumProductStatus;
import com.lunjar.ebp.bizsupport.exception.BizSupportException;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.query.CartQuery;
import com.lunjar.ebp.bizsupport.query.ProductQuery;
import com.lunjar.ebp.bizsupport.service.CartService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;

public class ProductServiceImpTest extends BizTestSupport {
	protected static final Logger logger = LoggerFactory.getLogger(ProductShopServiceImpTest.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;
	
	@Test
	public void add() throws ServiceException {
		Product product = new Product();
		product.setEnterpriseId(1l);// 商家id
		product.setName("测试产品1");
		product.setPrice(new BigDecimal(100.00));
		product.setSalePrice(new BigDecimal(85.5));
		product.setProductDesc("这是第一个产品描述，非常好！");
		product.setGmtCreate(new Date());
		product.setGmtModify(new Date());
		product.setSaleNum(12);
		product.setCid(1);
		product.setCatName("美女");
		product.setStatus(EnumProductStatus.SALE.getValue());
		product.setProductNum(119);
		product.setPropsName("p1:身高;p2:体重;p3:三维");
		product.setPropsAlias("p1:v1:168cm;p2:v2:50kg;p3:v3:33,33,33");
		product.setType(2);
		product.setIsPost(EnumPostProduct.POST.getValue());
		product.setExpressId(1L);
		product.setPathUrl("http://i0.hexunimg.cn/2016-08-15/185512502.jpg");
		product.setVolume(new BigDecimal(56.5));
		product.setWeight(new BigDecimal(66.43));
		product.setDistributionMode(EnumProductDistributionMode.EXPRESS.getValue());
		ProductDto dto = new ProductDto();
		dto.setProduct(product);
		List<ProductPropImgs> list = new ArrayList<>();
		ProductPropImgs ppi1 = new ProductPropImgs();
		ppi1.setGmtCreate(new Date());
		ppi1.setGmtModify(new Date());
		ppi1.setStatus(EnumProductPropImgsStatus.SHOW.getValue());
		ppi1.setType(EnumProductPropImgsType.CAROUSEL.getValue());
		ppi1.setSortNum(1);
		ppi1.setPicPath("http://i0.hexunimg.cn/2016-08-15/185512502.jpg");
		list.add(ppi1);
		ProductPropImgs ppi2 = new ProductPropImgs();
		ppi2.setGmtCreate(new Date());
		ppi2.setGmtModify(new Date());
		ppi2.setStatus(EnumProductPropImgsStatus.SHOW.getValue());
		ppi2.setType(EnumProductPropImgsType.DETAIL.getValue());
		ppi2.setSortNum(2);
		ppi2.setPicPath("http://i0.hexunimg.cn/2016-08-15/185512502.jpg");
		list.add(ppi2);
		//dto.setList(list);
		Long id = productService.add(dto);
		logger.info("商品id为：：：：" + id);
	}

	// @Test
	// public void delete() throws BizSupportException{
	// Long productId = 13l;
	// productService.delete(productId);
	// }

	@Test
	public void update() throws BizSupportException {
		Product product = new Product();
		product.setId(1l);
		product.setName("修改的名称");
		productService.update(product);
	}

	@Test
	public void getList() throws BizSupportException {
		ProductQuery query = new ProductQuery();
		/*Long[] array = { 1l, 2l };
		query.setIdArray(array);*/
		Map<String,Object> properties = new HashMap<String,Object>();
		properties.put("productName", "月饼");
		query.setProperties(properties);
		List<Product> list = productService.queryList(query);
		if (list != null && list.iterator().hasNext()) {
			for (Product p : list) {
				System.out.println(p.getId() + "@@@@@" + p.getName());
			}
		}
	}

	@Test
	public void load() throws BizSupportException {
		Product product = productService.load(1l);
		logger.info(product.toString());
	}

	
	@Test
	public void testGetCartList() throws ServiceException{
		CartQuery query = new CartQuery();
		query.setBuyerId(12313L);
		List<CartDto> listCart = cartService.queryList(query);
		List<EnterpriseProModel> list = productService.getCartList(listCart,"中山");
	}
	
	@Test
	public void testGetCombin(){
		Long id = 1L;
		List<CombinDto> list = new ArrayList<>();
		CombinDto c1 = new CombinDto();
		c1.setProductId(1l);
		c1.setSkuId(1l);
		c1.setNum(1);
		CombinDto c2 = new CombinDto();
		c2.setProductId(2l);
		c2.setNum(1);
		
		list.add(c1);
		list.add(c2);
		productService.getCombin(id, list, null, null, null);
	}
	
	@Test
	public void getProductInfo() {
		Long id = 10l;
		ProductDto dto = productService.getProductInfo(id);
		System.out.println(dto.toString());
	}
	
	@Test
	public void testQueryListAndSku(){
		ProductQuery query = new ProductQuery();
		query.setEnterpriseId(1L);
		PageResult<ProductDto> list = productService.queryListAndSku(query);
		System.out.println(list.getRecordCount());
	}
	
	@Test
	public void updateProductShelves(){
		Long[] arr = {29L,30L};
		productService.updateProductShelves(arr, "2");
	}
}
