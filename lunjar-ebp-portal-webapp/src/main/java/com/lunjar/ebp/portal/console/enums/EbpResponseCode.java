package com.lunjar.ebp.portal.console.enums;

public enum EbpResponseCode {
	
	/**连续三次登录错误*/
	_2050001(2050001,"连续三次登录错误"),
	/** 接入者不存在 */
	_2050002(2050002,"接入者不存在"),
	/**用户名密码错误*/
	_2050003(2050003,"用户名密码错误"),
	;
	
	private int code;
	private String msg;
	
	private EbpResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static EbpResponseCode valueOf(int code){
		EbpResponseCode result = null ;  
		EbpResponseCode[] ts = EbpResponseCode.class.getEnumConstants();
		for(EbpResponseCode aps:ts){
			
			if(aps.getCode()==code){
				result =aps;
				break;
			}
		}
		return result;
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
