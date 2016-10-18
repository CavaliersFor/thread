package com.lunjar.ebp.bizsupport.enums;


public enum EnterpriseServiceResultEnum {
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


    private int code;
	private String msg;

	private EnterpriseServiceResultEnum(String msg) {
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
