package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.model.BuyerAddress;
import com.lunjar.ebp.bizsupport.query.BuyerAddressQuery;
import com.lunjar.ebp.bizsupport.service.BuyerAddressService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class BuyerAddressServiceImplTest extends BizTestSupport {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BuyerAddressServiceImplTest.class);

	@Autowired
	private BuyerAddressService buyerAddressService;

	/**
	 * 新增买家详细地址
	 * 
	 * @param buyerAddress
	 * @return
	 * @throws ServiceException
	 */
	@Test
	public void add() throws ServiceException {
		for (int i = 0; i < 10; i++) {
			BuyerAddress buyerAddress = new BuyerAddress();
			buyerAddress.setGmtCreate(new Date());// 创建时间
			buyerAddress.setBuyerId(1234566L);// 买家id
			buyerAddress.setProvince("广东");// 买家省份
			buyerAddress.setCity("广州");
			;// 买家城市
			buyerAddress.setRegion("天河");// 买家区县
			buyerAddress.setStatus(1);// 状态1：正常2：停止
			buyerAddress.setBuyerName("李四");// 收货人姓名
			buyerAddress.setBuyerPhone("1234565678");// 收货人手机号码
			buyerAddress.setBuyerAddress("天河北路");// 收货人详细地址
			buyerAddress.setBuyerPostCode("mai.com");// 收货人邮编
			logger.info("添加的id是{}", buyerAddressService.add(buyerAddress));
		}
	}

	/**
	 * 更新买家详细地址
	 * 
	 * @param buyerAddress
	 * @return
	 */
	@Test
	public void update() {
		BuyerAddress buyerAddress = new BuyerAddress();
		buyerAddress.setGmtModify(new Date());// 修改时间
		buyerAddress.setBuyerPhone("123100000");// 收货人手机号码
		buyerAddress.setId(4L);
		buyerAddressService.update(buyerAddress);
	}

	/**
	 * 查询买家详细地址信息和根据查询条件获得总数
	 * 
	 * @param buyerAddress
	 * @return
	 */
	@Test
	public void queryList() {
		BuyerAddressQuery buyerAddressQuery = new BuyerAddressQuery();
		// 排序
		buyerAddressQuery.setSort("GMT_CREATE asc");
		buyerAddressQuery.setBuyerId(1234566L);
		buyerAddressQuery.setColumns("buyer_id", "buyer_name");// 设置查询列，主键不需要设置。默认查询
		buyerAddressQuery.setBuyerName("李");
		buyerAddressQuery.setPageSize(2); // 每页显示记录数
		buyerAddressQuery.setPageNo(2); // 第几页
		List<BuyerAddress> listBuyerAddress = buyerAddressService.queryList(buyerAddressQuery);
		int count = buyerAddressService.queryCount(buyerAddressQuery);
		if (listBuyerAddress != null && listBuyerAddress.size() > 0) {
			for (BuyerAddress b : listBuyerAddress) {
				System.out.println(b);
			}
		}
	}

	/**
	 * 根据地址id查询具体地址信息
	 * 
	 * @param id
	 * @return
	 */
	@Test
	public void load() {
		BuyerAddress address = buyerAddressService.load(4L);
		System.out.println(address);
	}

	/**
	 * 根据id删除具体地址
	 * 
	 * @param id
	 */
	@Test
	public void deleteMethod() {
		buyerAddressService.delete(3L);
	}

	/****
	 * 通过id和buyerId删除
	 * 
	 * @param buyerAddress
	 */
	@Test
	public void deleteByIdAndBuyerId() {
		BuyerAddress buyerAddress = new BuyerAddress();
		buyerAddress.setId(4L);
		buyerAddress.setBuyerId(1234566L);
		int count = buyerAddressService.deleteByIdAndBuyerId(buyerAddress);
		System.out.println("删除记录数:" + count);
	}
}
