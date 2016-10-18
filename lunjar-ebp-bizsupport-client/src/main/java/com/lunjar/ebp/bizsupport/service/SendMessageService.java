package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.dto.SendMessageDto;

/**
 * 发送信息服务
 * @author Administrator
 *
 */
public interface SendMessageService {
	/**
	 * 
	 * @param mobile 接受手机号
	 * @param text 短信内容
	 * @return
	 */
	public boolean sendCode(String mobile,String text);
	
	/**
	 * 通过模板发送信息
	 * @param mobile 接受手机号
	 * @param text 短信内容
	 * @param tplId 模板id
	 * @return
	 */
	public boolean sendCodeByTpl(SendMessageDto sendMessageDto);
}
