package com.ancun.bps.admin.web.controller;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.lunjar.products.core.webapi.LunjarApiClient;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

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
        "classpath*:spring/admin-bean.xml",//
        "classpath*:bizsupport-client.xml",
        "classpath:spring/jms-bean.xml",
        "classpath*:usercenter-client-bean.xml",
        "classpath*:preserve-client-bean.xml",
})
public class ApiTestNGSupport extends TestNGSupport {

    @Autowired
    protected LunjarApiClient ancunApiClient;

    public void printResult(LunjarApiResponse res) {
        assertEquals(LunjarApiResponseCode._1000000.getCode(), res.getCode());
        String json = com.alibaba.fastjson.JSON.toJSONString(res, true);
        logger.info("\n" + json);
    }

}
