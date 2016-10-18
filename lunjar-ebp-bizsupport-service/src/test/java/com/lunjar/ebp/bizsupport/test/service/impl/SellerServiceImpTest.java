package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.enums.EnumSellerCheckStatus;
import com.lunjar.ebp.bizsupport.enums.EnumSellerStatus;
import com.lunjar.ebp.bizsupport.enums.EnumSellerType;
import com.lunjar.ebp.bizsupport.exception.BizSupportException;
import com.lunjar.ebp.bizsupport.model.Seller;
import com.lunjar.ebp.bizsupport.query.SellerQuery;
import com.lunjar.ebp.bizsupport.service.SellerService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;

public class SellerServiceImpTest extends BizTestSupport {
	protected static final Logger logger = LoggerFactory.getLogger(SellerServiceImpTest.class);

	@Autowired
	private SellerService sellerService;

	@Test
	public void add() throws BizSupportException {
		Seller seller = new Seller();
		seller.setRegisterName("lixuan");
		seller.setMobile("18768187358");
		seller.setStatus(EnumSellerStatus.NORMAL.getValue());
		seller.setUserType(EnumSellerType.PERSONAL.getValue());
		seller.setLoginCount(0);
		seller.setCheckStatus(EnumSellerCheckStatus.AUDITED.getValue());
		seller.setGmtCreate(new Date());
		seller.setGmtModify(new Date());
		Long id = sellerService.add(seller);
		logger.info("卖家id为：：：：" + id);
	}

	@Test
	public void update() throws BizSupportException {
		Seller seller = new Seller();
		seller.setId(1l);
		seller.setLoginCount(1);
		sellerService.update(seller);
	}

	@Test
	public void getList() throws BizSupportException {
		SellerQuery query = new SellerQuery();
		Long[] array = { 1l, 2l };
		query.setIdArray(array);
		List<Seller> list = sellerService.queryList(query);
		if (list != null && list.iterator().hasNext()) {
			for (Seller s : list) {
				System.out.println(s.getId() + "@@@@@" + s.getMobile());
			}
		}
	}

	@Test
	public void load() throws BizSupportException {
		Seller seller = sellerService.load(1l);
		logger.info(seller.toString());
	}

}
