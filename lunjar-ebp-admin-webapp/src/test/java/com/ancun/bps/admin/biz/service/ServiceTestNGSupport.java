package com.ancun.bps.admin.biz.service;

import static org.junit.Assert.assertNotNull;

import org.springframework.test.context.ContextConfiguration;

import com.ancun.bps.admin.web.controller.TestNGSupport;
import com.lunjar.products.core.service.ServiceResult;

/**
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/4/1 13:24
 */
@ContextConfiguration(locations = {
        "classpath*:spring/core-bean.xml",
        "classpath*:spring/cache-bean.xml", //
        "classpath*:spring/bps-service-core-bean.xml", //
        "classpath*:spring/web-bean.xml", //
        "classpath*:spring/admin-bean.xml"//
})
public class ServiceTestNGSupport extends TestNGSupport {

    public void print(ServiceResult res) {
        assertNotNull(res);

        String json = com.alibaba.fastjson.JSON.toJSONString(res, true);
        logger.info("\n" + json);
    }

}
