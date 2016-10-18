package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;

public class SendMessageResponse implements Serializable{
	private String code;//0表示成功,其他都表示失败
    private String msg;// 发送成功。响应信息
    private Integer count; //1,	//成功发送的短信计费条数
    private String fee;// 0.05,	//扣费条数，70个字一条，超出70个字时按每67字一条计
    private String unit;//: "RMB",	// 计费单位
    private String mobile;//"13200000000", // 发送手机号
    private String sid;//: 3310228982	// 短信ID
    private String detail;//"参数 apikey 必须传入" 详细描述信息
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
    
}
