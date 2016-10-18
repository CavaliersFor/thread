package com.ancun.bps.admin.web.controller;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ancun.bps.core.log.dto.LoginDto;
import com.lunjar.ebp.admin.biz.service.AdminLoginService;
import com.lunjar.ebp.admin.domain.AdminServiceResultCode;
import com.lunjar.products.core.utils.DigestsUtils;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

/**
 * 用户登录测试
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/4/5 16:49
 */
public class AdminLoginControllerTest extends ApiTestNGSupport {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @DataProvider(name = "Account")
    public static Object[][] Account() {
        return new Object[][]{
                {"zxf", "123456", LunjarApiResponseCode._1000000.getCode()}  // 正确的用户名密码
                , {"zxf1", "", AdminServiceResultCode._2060504.getCode()} // 初始化登录:空密码
                , {"zxf2", AdminLoginService.DEFAULT_PASSWD, AdminServiceResultCode._2060504.getCode()} // 初始化登录:默认密码
                , {"zxf3", "222222", AdminServiceResultCode._2060501.getCode()} // 用户名密码错误:错误的密码
                , {"zxf4", "222222", AdminServiceResultCode._2060502.getCode()} // 账户登录失败5次被锁定
                , {"zxf5", "222222", AdminServiceResultCode._2060502.getCode()} // 账户登录失败3次弹出验证码
                , {"nouser", "222222", AdminServiceResultCode._2060501.getCode()} // 用户名密码错误:用户不存在
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

        ancunApiClient.postForm("http://localhost:28080/login",dto);
    }

}