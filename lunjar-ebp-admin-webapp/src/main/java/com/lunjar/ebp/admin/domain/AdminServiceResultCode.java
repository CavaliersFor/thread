package com.lunjar.ebp.admin.domain;

import com.lunjar.products.core.service.ServiceResultCodeEnum;
import com.lunjar.products.core.webapi.LunjarApiResponse;

/**
 * 返回信息枚举(2060001-2069999)
 * <p>
 * create at 2014年10月28日 下午5:15:54
 *
 * @author <a href="mailto:caozhenfei@ancun.com">Dim.Cao</a>
 * @version %I%, %G%
 * @see
 */
public enum AdminServiceResultCode implements ServiceResultCodeEnum {
	/** 用户名密码错误 */
	_2060501("登录失败，用户名密码错误")
	/** 账户被锁定 */
    ,_2060502("登录失败，账户被锁定")
	/** 账户被冻结 */
	,_2060503("登录失败，账户被冻结")
    /** 初始登录，请重置密码 */
	,_2060504("初始登录，请重置密码")
    /** 验证码错误 */
	,_2060505("验证码错误")
    /** 登录失败3次,前台开始显示验证码,并提示用户失败信息 */
	,_2060506("登录失败，用户名密码错误")
    /** 用户不存在 */
	,_2060507("用户不存在")
	;

    public static LunjarApiResponse create(AdminServiceResultCode result) {
        return LunjarApiResponse.create(result.getCode(), result.getMsg());
    }

    private int code;
	private String msg;

	private AdminServiceResultCode(String msg) {
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
