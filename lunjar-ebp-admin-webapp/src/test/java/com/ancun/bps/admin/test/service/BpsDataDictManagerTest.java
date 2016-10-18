package com.ancun.bps.admin.test.service;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ancun.bps.core.datadict.impl.BaseDataExistsException;
import com.ancun.bps.core.datadict.impl.BpsDataDictRemoteService;
import com.lunjar.ebp.admin.biz.service.BpsDataDictManager;
import com.lunjar.ebp.admin.domain.enums.EnumDataDictStatus;
import com.lunjar.products.core.datadict.model.PubDataDictionaryEntity;
import com.lunjar.products.core.datadict.model.PubDataDictionaryQuery;

public class BpsDataDictManagerTest extends TestSupport {

	@Autowired
	private BpsDataDictManager bpsDataDictManager;
	@Autowired
	private BpsDataDictRemoteService bpsDataDictRemoteService;

	@Test
	public void testGetGroup() {
		List<PubDataDictionaryEntity> list = bpsDataDictManager.getGroup();
		System.out.println(list);
	}

	@Test
	public void testQueryForList() {
		PubDataDictionaryQuery query =new PubDataDictionaryQuery();
		query.setDictGroup("aospIndustryType");
		query.setParentValue("1");
//		query.setDictValue("4");
		System.out.println("^^^^^^^^^^^^^^^^^^"+bpsDataDictManager.queryForList(query));
		if(null==bpsDataDictManager.queryForList(query))
			fail("Not yet implemented");
	}

	@Test
	public void testGetByPk() {
		if(null==bpsDataDictRemoteService.load("aospIndustryType","jr1"))
			fail("Not yet implemented");
//		if(null==bpsDataDictManager.getByPk("1", "aospIndustryType"))
//			fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		PubDataDictionaryEntity o = bpsDataDictManager.getByPk("1", "aospIndustryType");
		o.setGmtModify(new Date());
		bpsDataDictManager.update(o);
//		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		PubDataDictionaryEntity o = new PubDataDictionaryEntity();
		o.setDictValue("1");
		o.setDictText("测试类型");
		o.setDictGroup("testGroup");
		o.setStatus(EnumDataDictStatus.NORMAL.getValue());
		o.setSortNum(1);
		try {
			bpsDataDictManager.add(o);
		} catch (BaseDataExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		fail("Not yet implemented");
	}

	@Test
	public void testUpdateStatus() {
		PubDataDictionaryEntity o = new PubDataDictionaryEntity();
		o.setDictValue("1");
		o.setDictText("测试类型");
		o.setDictGroup("testGroup");
		o.setStatus(EnumDataDictStatus.DEPARTURE.getValue());
		bpsDataDictManager.updateStatus(o);
//		fail("Not yet implemented");
	}

	@Test
	public void testGetByGroup() {
		if(null==bpsDataDictManager.getByGroup("aospIndustryType"))
			fail("Not yet implemented");	
	}

	@Test
	public void testGetByGroupAndParentValue() {
		if(null==bpsDataDictManager.getByGroupAndParentValue("aospIndustryType", "aospIndustryType"));
			fail("Not yet implemented");
	}

	@Test
	public void testGetNewSortNumOnMove() {
//		bpsDataDictManager.getNewSortNumOnMove(childrenFoDdToUpdate, index)
//		fail("Not yet implemented");
	}

	@Test
	public void testMove() {
////		bpsDataDictManager.move(ddMove, dictGroup, newParentValue, newSortNum, maxSortNumInChildrenOfNewParent);
//		fail("Not yet implemented");
	}

}
