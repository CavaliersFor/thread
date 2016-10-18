package com.ancun.bps.admin.web.controller;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.alibaba.dubbo.common.json.JSON;
import com.lunjar.products.core.webapi.LunjarApiClient;

/**
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/4/1 13:24
 */
public class TestNGSupport extends AbstractTestNGSpringContextTests {

    protected static final Logger logger = LoggerFactory.getLogger(TestNGSupport.class);

    @Autowired
    protected LunjarApiClient ancunApiClient;

    private String convertString(Object o) {
        String msg;
        if (o == null) {
            msg = "null";
        } else {
            try {
                msg = JSON.json(o);
            } catch (IOException e) {
                msg = e.getMessage();
            }
        }

        return msg;
    }

    private String[] convertString(Object[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        String[] strings = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            strings[i] = convertString(array[i]);
        }
        return strings;
    }

    protected void print(Object... objects) {
        if (objects.length==1){
            String json = com.alibaba.fastjson.JSON.toJSONString(objects, true);
            logger.info("\n"+ json);
            return;
        }

        String[] array=convertString(objects);
        logger.info("\n"+ Arrays.toString(array) );
    }

}
