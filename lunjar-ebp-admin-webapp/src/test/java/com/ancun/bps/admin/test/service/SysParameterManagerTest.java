package com.ancun.bps.admin.test.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.admin.biz.service.SysParameterManager;
import com.lunjar.products.core.config.model.SysParameter;
import com.lunjar.products.core.config.model.SysParameterQuery;

public class SysParameterManagerTest extends TestSupport {

	@Autowired
	private SysParameterManager sysParameterManager;
	
	@Test
	public void testQueryForList() {
		SysParameterQuery query = new SysParameterQuery();
		List<SysParameter> list = sysParameterManager.queryForList(query);
		System.out.println(list);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByKey() {
		fail("Not yet implemented");
	}

}
