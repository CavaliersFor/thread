package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.model.Region;
import com.lunjar.ebp.bizsupport.service.RegionService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class RegionServiceImpTest extends BizTestSupport {
	protected static final Logger logger = LoggerFactory.getLogger(RegionServiceImpTest.class);

	@Autowired
	private RegionService regionService;

	@Test
	public void getAllProvince() {
		List<Region> list = regionService.getAllProvince();
		for (Region p : list) {
			System.out.println(p.getRegionName());
		}
	}

	@Test
	public void getByParentCode() throws ServiceException {
		List<Region> list = regionService.getByParentCode("440000");
		for (Region p : list) {
			System.out.println(p.getRegionName());
		}
	}

	/**
	 * 根据根code获取第几级城市列表 1：国家2：省份3：城市4：区县
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月18日上午10:27:01
	 * @throws ServiceException
	 */
	@Test
	public void getByParentCodeForTree() throws ServiceException {
		List<Region> list = regionService.getByParentCodeForTree("440100", 4);
		for (Region p : list) {
			System.out.println(p.getRegionName());
		}
	}

	@Test
	public void getParentCode() throws ServiceException {
		String parentCode = regionService.getParentCode("440100");
		System.out.println(parentCode);
	}
}
