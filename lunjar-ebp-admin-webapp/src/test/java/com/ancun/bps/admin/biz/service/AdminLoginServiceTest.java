package com.ancun.bps.admin.biz.service;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ancun.bps.core.log.dto.LoginDto;
import com.lunjar.ebp.admin.biz.service.AdminLoginService;
import com.lunjar.ebp.admin.biz.service.AdminUserService;
import com.lunjar.ebp.admin.domain.AdminServiceResultCode;
import com.lunjar.ebp.admin.domain.enums.EnumAdminUserStatus;
import com.lunjar.ebp.admin.domain.model.AdminAgent;
import com.lunjar.ebp.admin.domain.model.AdminUser;
import com.lunjar.products.core.service.ServiceResult;
import com.lunjar.products.core.utils.DigestsUtils;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

/**
 * 登录测试用例
 *
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/4/6 10:24
 */
public class AdminLoginServiceTest extends  ServiceTestNGSupport{

    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private AdminUserService adminUserService;
 
    
    @AfterClass
    public void tearDown() throws Exception {
        AdminUser zxf5=new AdminUser();
        zxf5.setId(35L);
        zxf5.setLoginErrorCount(2);
        zxf5.setStatus(EnumAdminUserStatus.NORMAL.getValue());
        adminUserService.update(zxf5);


        AdminUser zxf3=new AdminUser();
        zxf3.setId(35L);
        zxf3.setLoginErrorCount(1);
        zxf3.setStatus(EnumAdminUserStatus.NORMAL.getValue());
        adminUserService.update(zxf3);
    }

    @DataProvider(name = "Account")
    public static Object[][] Account() {
        return new Object[][]{
                {"zxf", "123456", LunjarApiResponseCode._1000000.getCode()}  // 正确的用户名密码
                , {"zxf1", "", AdminServiceResultCode._2060504.getCode()} // 初始化登录:空密码
                , {"zxf2", AdminLoginService.DEFAULT_PASSWD, AdminServiceResultCode._2060504.getCode()} // 初始化登录:默认密码
                , {"zxf3", "222222", AdminServiceResultCode._2060501.getCode()} // 用户名密码错误:错误的密码
                , {"nouser", "222222", AdminServiceResultCode._2060501.getCode()} // 用户名密码错误:用户不存在
                , {"zxf4", "222222", AdminServiceResultCode._2060502.getCode()} // 账户登录失败5次被锁定
                , {"zxf5", "222222", AdminServiceResultCode._2060506.getCode()} // 账户登录失败3次弹出验证码
        };

    }

    @Test(dataProvider = "Account")
    public void testLoginSubmit(String account,String pwd,int resCode) throws Exception {
        print(account,pwd,resCode);

        LoginDto dto = new LoginDto();

        String random = "12asd3423256";
        String s1 = account + DigestsUtils.sha1Hex(pwd.getBytes());
        String s2 = DigestsUtils.md5Hex(s1.getBytes());
        String sign = DigestsUtils.md5Hex((s2 + random).getBytes());

        dto.setAccount(account);
        dto.setRandom(random);
        dto.setSign(sign);

        ServiceResult<AdminAgent> result = adminLoginService.login(dto);

        print(result);

        assertEquals(resCode, result.getCode());
    }

 
}
