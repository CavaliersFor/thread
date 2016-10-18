package com.lunjar.ebp.bizsupport.test.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.CollectTimeDto;
import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.query.CollectPlaceQuery;
import com.lunjar.ebp.bizsupport.service.CollectPlaceService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class CollectPlaceServiceImplTest extends BizTestSupport {

	@Autowired
	private CollectPlaceService collectPlaceService;

	/**
	 * 根据自取点id查询
	 * 
	 * @param id
	 * @return
	 */
	@Test
	public void load() {
		System.out.println(collectPlaceService.load(8569L));
	}

	/**
	 * 新增自取点信息
	 * 
	 * @param discount
	 * @return
	 * @throws ServiceException
	 */
	@Test
	public void add() throws ServiceException {

		CollectPlace collectPlace = new CollectPlace();
		collectPlace.setGmtCreate(new Date());// 创建时间
		collectPlace.setClpStatus(1);// 状态1：使用中 2：停止使用
		collectPlace.setClpName("天河自取点2");// 领取点名称
		collectPlace.setClpAddress("广州市天河区XXX路XXX号");// 领取点详细地址
		collectPlace.setClpTelephone("1675443251");// 领取点联系电话
		collectPlace.setEnterpriseId(128765L);// 商家id
		collectPlace.setCollectTime(45);// 多少时间能取货单位小时，0就是可以及时取货
		collectPlace.setFee(new BigDecimal("5"));
		collectPlace.setMaxDepositDays(7);//保存时间
		Long id = collectPlaceService.add(collectPlace);
		System.out.println("添加纪录的id=" + id);

	}

	/**
	 * 更新自取点信息
	 * 
	 * @param discount
	 */
	@Test
	public void update() {
		CollectPlace collectPlace = new CollectPlace();
		collectPlace.setId(8569L);
		collectPlace.setClpTelephone("1009765567");
		collectPlace.setClpAddress("白云区123123");
		int num = collectPlaceService.update(collectPlace);
		System.out.println("更新记录数为:" + num);
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 */
	@Test
	public void delete() {
		collectPlaceService.delete(8568L);
	}

	/**
	 * 查询自取点信息
	 * 
	 * @param discountQuery
	 * @return
	 */
	@Test
	public void queryList() {

		long starTime = System.currentTimeMillis();

		CollectPlaceQuery collectPlaceQuery = new CollectPlaceQuery();
		List<CollectPlace> list = collectPlaceService.queryList(collectPlaceQuery);
		if (list != null && list.size() > 0) {
			for (CollectPlace c : list) {
				System.out.println(c);
			}
		}
		long endTime = System.currentTimeMillis();

		System.out.println("执行时间:" + (endTime - starTime));
	}

	/**
	 * 根据条件获得总数
	 * 
	 * @param discountQuery
	 * @return
	 */
	@Test
	public void queryCount() {
		CollectPlaceQuery collectPlaceQuery = new CollectPlaceQuery();
		collectPlaceQuery.setClpStatus(1);
		int count = collectPlaceService.queryCount(collectPlaceQuery);
		System.out.println("查询条件记录数：" + count);
	}

	/**
	 * 通过id和EnterpriseId(商家id)进行删除
	 * 
	 * @param discount
	 * @return
	 */
	@Test
	public void deleteByIdAndEnterpriseId() {
		CollectPlace collectPlace = new CollectPlace();
		collectPlace.setId(8569L);
		collectPlace.setEnterpriseId(128765L);
		int num = collectPlaceService.deleteByIdAndEnterpriseId(collectPlace);
		System.out.println("删除记录数:" + num);
	}
	
	@Test
	public void getCollectTime() {
		List<CollectTimeDto> list = new ArrayList<>();
		CollectTimeDto dto1 = new CollectTimeDto();
//		dto1.setEnterpriseId(1l);
		dto1.setTradeId(2l);
//		dto1.setCollectPlaceId(6l);
		list.add(dto1);
		list = collectPlaceService.getCollectTimeList(list);
		for(CollectTimeDto c: list) {
			System.out.print(c.getEndTime());
		}
	}
}
