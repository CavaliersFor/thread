package com.lunjar.ebp.bizsupport.enums;


import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;
import com.lunjar.products.core.service.ServiceResultCodeEnum;

/**
 * 管理员状态1：正常
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月25日下午4:59:41
 */
public enum EnumSelfDeliveryResultStatus implements ServiceResultCodeEnum{
	/** 用户名密码错误 */
	_2060501("登录失败，用户名密码错误")
	/** 账户被锁定 */
    ,_2060502("登录失败，账户被锁定")
	/** 账户已停用 */
	,_2060503("登陆失败，账户已停用")
    /** 初始登录，请重置密码 */
	,_2060504("初始登录，请重置密码")
    /** 验证码错误 */
	,_2060505("验证码错误")
    /** 登录失败3次,前台开始显示验证码,并提示用户失败信息 */
	,_2060506("登录失败，用户名密码错误")
    /** 用户不存在 */
	,_2060507("用户不存在")
	/** 请输入验证码再登陆 */
	,_2060508("请输入验证码再登陆")
	;

    private int code;
	private String msg;
	
	private EnumSelfDeliveryResultStatus(String msg) {
		this.msg = msg;
		this.code = Integer.valueOf(this.name().substring(1));
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
