package com.lunjar.ebp.bizsupport.test.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.query.ProductSkuQuery;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class ProductSkuServiceImplTest extends BizTestSupport {

	Long id = 1L;

	@Autowired
	private ProductSkuService productSkuService;

	@Test
	public void add() throws ServiceException {
		ProductSku p = new ProductSku();
		p.setProductId(1L);
		p.setProperties("p1:v1;");
		p.setPropertiesname("颜色:红色;尺码:xl");
		p.setQuantity(1000);
		p.setPrice(new BigDecimal("20.12"));
		p.setGmtCreate(new Date());
		p.setStatus(1);
		p.setEnterpriseProductNo("12313123");
		Long id = productSkuService.add(p);

		System.out.println("添加的id=" + id);
	}

	@Test
	public void load() {
		System.out.println(productSkuService.load(id));
	}

	@Test
	public void update() {
		ProductSku p = new ProductSku();
		p.setId(id);
		p.setPrice(new BigDecimal("123.1234"));
		int count = productSkuService.update(p);
		System.out.println("更新数量：" + count);
	}

	@Test
	public void queryListAndQueryNum() {
		ProductSkuQuery q = new ProductSkuQuery();
		q.setIdArray(1L);
		List<ProductSku> list = productSkuService.queryList(q);
		if (list != null && list.size() > 0) {
			for (ProductSku ps : list) {
				System.out.println("查询记录：" + ps);
			}
		}
		System.out.println("查询记录数" + productSkuService.queryCount(q));
	}

	@Test
	public void delete() {
		productSkuService.delete(id);
	}
	
	@Test
	public void deleteByCondition(){
		ProductSku productSku = new ProductSku();
		productSku.setId(1L);
		productSkuService.deleteByCondition(productSku);
	}
}
