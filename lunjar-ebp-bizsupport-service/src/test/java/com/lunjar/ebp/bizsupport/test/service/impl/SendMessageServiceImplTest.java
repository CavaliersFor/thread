package com.lunjar.ebp.bizsupport.test.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.constants.SendMessageConstants;
import com.lunjar.ebp.bizsupport.dto.SendMessageDto;
import com.lunjar.ebp.bizsupport.service.SendMessageService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;

public class SendMessageServiceImplTest extends BizTestSupport {

	@Autowired
	private SendMessageService sendMessageService;

	@Test
	public void testSendCode() throws UnsupportedEncodingException {
		// 【趣易购】您好，您的订单#tradeno#将于#time#到达自提点#address#，提货码#code#，请于约定时间段提取。
		// 您好，您的订单1234567890将于12日到达自提点省站一号售票口旁，提货码0987654321，请于约定时间段提取。【趣易购】
		
		Map<String,String> content = new HashMap<String,String>();
		content.put(SendMessageConstants.SendPickUpGoods.CODE, "0987654321");
		content.put(SendMessageConstants.SendPickUpGoods.TRADENO, "1234567890");
		content.put(SendMessageConstants.SendPickUpGoods.ADDRESS, "省站一号售票口旁");
		content.put(SendMessageConstants.SendPickUpGoods.TIME, "12日");
		SendMessageDto sendMessageDto = new SendMessageDto();
		sendMessageDto.setTplId(SendMessageConstants.SendPickUpGoods.TPID);
		sendMessageDto.setPhoneNum("18768187358");
		sendMessageDto.setContent(content);
		boolean isSuccess = sendMessageService.sendCodeByTpl(sendMessageDto);
		System.out.println(isSuccess);
	}
	
	
	@Test
	public void testSendCode2() throws UnsupportedEncodingException {
		// 【趣易购】欢迎进入趣易购注册平台，您的手机验证码是：1234，请在十分钟内完成验证。
		
		Map<String,String> content = new HashMap<String,String>();
		content.put(SendMessageConstants.RegisterCode.NUMBER,"123456");
		SendMessageDto sendMessageDto = new SendMessageDto();
		sendMessageDto.setTplId(SendMessageConstants.RegisterCode.TPID);
		sendMessageDto.setPhoneNum("18922247810");
		sendMessageDto.setContent(content);
		boolean isSuccess = sendMessageService.sendCodeByTpl(sendMessageDto);
		System.out.println(isSuccess);
	}
}
