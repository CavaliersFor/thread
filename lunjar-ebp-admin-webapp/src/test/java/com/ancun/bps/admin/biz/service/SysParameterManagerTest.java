package com.ancun.bps.admin.biz.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lunjar.ebp.admin.biz.service.SysParameterManager;
import com.lunjar.products.core.config.model.SysParameter;
import com.lunjar.products.core.config.model.SysParameterQuery;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 系统参数测试
 *
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/4/6 16:30
 */
public class SysParameterManagerTest extends ServiceTestNGSupport {
    @Autowired
    private SysParameterManager sysParameterManager;

    private String paramKey="test.zxf.param1";
    private String paramValue="zxf";
    private String newParamValue="zhangxufeng";

    @BeforeClass
    public void setUp() throws Exception {

    }

    @AfterClass
    public void tearDown() throws Exception {
        SysParameter pubParameter=new SysParameter();
        pubParameter.setParamKey(paramKey);
        pubParameter.setParamValue(paramValue);

        try{
            sysParameterManager.update(pubParameter);
        }catch (ServiceException e){
            if (!"修改失败,新旧值相同!".equals(e.getMessage())){
                throw e;
            }
        }
    }

     /**
      * 查询系统参数
      * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/6 17:09
      * @version %I%,%G%
      * @see
      */
    @Test
    public void testQueryForList() throws Exception {
        List<SysParameter> list= sysParameterManager.queryForList(new SysParameterQuery());
        print(list);
    }

     /**
      * 测试修改后值是否立即改变
      * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/6 17:04
      * @version %I%,%G%
      * @see
      */
    @Test
    public void testUpdate() throws Exception {
        SysParameter pubParameter=new SysParameter();
        pubParameter.setParamKey(paramKey);
        pubParameter.setParamValue(newParamValue);
        sysParameterManager.update(pubParameter);

        print("pubParameter={}",true,pubParameter);

        SysParameter newPubParameter=sysParameterManager.getByKey(paramKey);

        assertNotNull(newPubParameter);
        assertNotSame(newPubParameter.getParamValue(),pubParameter.getParamValue());

        print("newPubParameter={}",true,newPubParameter);
    }

    @Test
    public void testGetByKey() throws Exception {
        SysParameter pubParameter=new SysParameter();
        pubParameter.setParamKey(paramKey);

        SysParameter sysParameter=sysParameterManager.getByKey(paramKey);
        assertNotNull(sysParameter);

        print(sysParameter);
    }
}