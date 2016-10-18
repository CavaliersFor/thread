package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.util.Map;

public class SendMessageDto implements Serializable{
	private Long tplId;//发送的模板id
	private String phoneNum;//发送的手机号
	private Map<String,String> content;//模板变量对应模板值
	public Long getTplId() {
		return tplId;
	}
	public void setTplId(Long tplId) {
		this.tplId = tplId;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Map<String, String> getContent() {
		return content;
	}
	public void setContent(Map<String, String> content) {
		this.content = content;
	}
}
