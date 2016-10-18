package com.lunjar.ebp.bizsupport.test.support;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		// "classpath*:spring/bizsupport-dubbo-provider.xml", //
		"classpath*:spring/store-bean.xml", //
		"classpath*:spring/bizsupport-service-bean.xml", //
		"classpath*:spring/bps-service-core-bean.xml", "classpath:spring/jms-bean.xml", //
})
// @TransactionConfiguration(transactionManager = "transactionManager",
// defaultRollback = true)
// @Transactional
public class BizTestSupport {
	protected static final Logger logger = LoggerFactory.getLogger(BizTestSupport.class);

}