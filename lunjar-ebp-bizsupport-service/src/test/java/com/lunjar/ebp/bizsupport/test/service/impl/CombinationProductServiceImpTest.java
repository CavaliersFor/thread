package com.lunjar.ebp.bizsupport.test.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.DiscountDto;
import com.lunjar.ebp.bizsupport.enums.EnumCombinationProductStatus;
import com.lunjar.ebp.bizsupport.exception.BizSupportException;
import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.query.CombinationProductQuery;
import com.lunjar.ebp.bizsupport.service.CombinationProductService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class CombinationProductServiceImpTest extends BizTestSupport {
	protected static final Logger logger = LoggerFactory.getLogger(ProductShopServiceImpTest.class);

	@Autowired
	private CombinationProductService combinationProductService;

	@Test
	public void add() throws ServiceException {
		CombinationProduct cProduct = new CombinationProduct();
		cProduct.setCpName("组合商品1");
		cProduct.setCpDesc("第一个组合产品测试");
		cProduct.setCpPicPath("http://d.youth.cn/shrgch/201608/W020160817480853228138.jpg");
		cProduct.setEnterpriseId(1l);
		cProduct.setGmtCreate(new Date());
		cProduct.setGmtModify(new Date());
		cProduct.setCpStatus(EnumCombinationProductStatus.ON_SALE.getValue());
		cProduct.setCpPrice(new BigDecimal("234.32"));
		cProduct.setProduct1Id(1l);
		cProduct.setProduct1RealPrice(new BigDecimal("33.33"));
		cProduct.setProduct2Id(2l);
		cProduct.setProduct2RealPrice(new BigDecimal("200.22"));
		cProduct.setProduct3Id(3l);
		cProduct.setProduct3RealPrice(new BigDecimal("2"));
		Long id = combinationProductService.add(cProduct);
		logger.info("组合商品id为：：：：" + id);
	}

	// @Test
	// public void delete() throws BizSupportException{
	// Long productId = 13l;
	// productService.delete(productId);
	// }

	@Test
	public void update() throws BizSupportException {
		CombinationProduct comProduct = new CombinationProduct();
		comProduct.setId(1l);
		comProduct.setCpName("修改组合商品的名称");
		combinationProductService.update(comProduct);
	}

	@Test
	public void getList() throws BizSupportException {
		CombinationProductQuery query = new CombinationProductQuery();
		Long[] array = { 1l, 2l };
		query.setIdArray(array);
		List<CombinationProduct> list = combinationProductService.queryList(query);
		if (list != null && list.iterator().hasNext()) {
			for (CombinationProduct p : list) {
				System.out.println(p.getId() + "@@@@@" + p.getCpName());
			}
		}
	}

	@Test
	public void load() throws BizSupportException {
		CombinationProduct product = combinationProductService.load(1l);
		logger.info(product.toString());
	}
	
	
	@Test
	public void doCombinationDiscountTest() throws ServiceException{
		DiscountDto dto = new DiscountDto();
		dto.setEnterpriseId(2L);
		combinationProductService.doCombinationDiscount(2L, dto);
		System.out.println(dto);
	}
	
	@Test
	public void updateNoDecide(){
		CombinationProduct product = new CombinationProduct();
		product.setId(36L);
		combinationProductService.updateNoDecide(product);
	}
}
