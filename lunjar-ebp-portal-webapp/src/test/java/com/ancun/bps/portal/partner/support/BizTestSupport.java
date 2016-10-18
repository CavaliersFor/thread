package com.ancun.bps.portal.partner.support;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version Date: 2010-9-13
 * @since
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/core-bean.xml", //
		"classpath*:spring/cache-bean.xml", //
		"classpath*:spring/jms-bean.xml",
		"classpath*:bizsupport-client.xml",
		"classpath*:spring/bps-service-core-bean.xml",
		"classpath*:spring/web-bean.xml",
		"classpath*:usercenter-client-bean.xml", //
		"classpath*:spring/portal-bean.xml", //
		"classpath*:preserve-client-bean.xml",
})
// @TransactionConfiguration(transactionManager = "transactionManager",
// defaultRollback = true)
// @Transactional
public class BizTestSupport {
}