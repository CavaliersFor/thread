package com.ancun.bps.admin.test.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/core-bean.xml", //
		"classpath*:spring/cache-bean.xml", //
		"classpath*:spring/bps-service-core-bean.xml", //
		"classpath*:spring/web-bean.xml", //
		"classpath*:spring/admin-bean.xml",//
		"classpath*:spring/jms-bean.xml",
		"classpath*:bizsupport-client.xml"
})
public class TestSupport {

}
