package com.lunjar.ebp.bizsupport.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.cache.VerCodeCache;
import com.lunjar.ebp.bizsupport.constants.SendMessageConstants;
import com.lunjar.ebp.bizsupport.dto.SendMessageDto;
import com.lunjar.ebp.bizsupport.dto.VerCodeDto;
import com.lunjar.ebp.bizsupport.service.CreateVerCodeService;
import com.lunjar.ebp.bizsupport.service.SendMessageService;
import com.lunjar.ebp.bizsupport.utils.CreateVerCode;

/**
 * 创建code
 * @author Administrator
 *
 */
@Service(value="createVerCodeService")
public class CreateVerCodeServiceImpl implements CreateVerCodeService {
	
	 
	@Autowired
	private VerCodeCache  verCodeCache;
	
	@Autowired
	private SendMessageService sendMessageService;
	
	/**
	 * 获取验证码
	 * @param num
	 * @return
	 */
	public VerCodeDto createVerCode(Integer num,String key) {
		
		if(num==null){
			num = 4;
		}
		VerCodeDto  dto = (VerCodeDto)verCodeCache.get(key);
		if(dto==null){
			// 获取验证码
			String verCode = CreateVerCode.getRandNum(num);
			dto = new VerCodeDto();
			dto.setVerCode(verCode);
			dto.setTimeNum(new Date().getTime());
		}else{
			
			Long lastSendTime = dto.getTimeNum();
			Long nowTimeNum = new Date().getTime();
			if(nowTimeNum-lastSendTime<60000){
				//不需要发送短信接口，没有超过60秒
				dto.setMessage("请勿频繁发送验证码，两次验证码发送时间不能小于60秒！");
			}else{
				String verCode = CreateVerCode.getRandNum(num);
				//发送短信
				Map<String,String> content = new HashMap<String,String>();
				content.put(SendMessageConstants.RegisterCode.NUMBER,verCode);
				SendMessageDto sendMessageDto = new SendMessageDto();
				sendMessageDto.setTplId(SendMessageConstants.RegisterCode.TPID);
				sendMessageDto.setPhoneNum(key);
				sendMessageDto.setContent(content);
			/*	boolean isSuccess = sendMessageService.sendCodeByTpl(sendMessageDto);
				if(!isSuccess){
					//短信发送失败
					dto.setMessage("短信发送失败，请重试！");
				}*/
				dto = new VerCodeDto();
				dto.setVerCode(verCode);
				dto.setTimeNum(new Date().getTime());
			}
		}
		
		verCodeCache.put(key, dto);
		return dto;
	}

	@Override
	public String getVerCode(String key) {
		VerCodeDto  dto = (VerCodeDto)verCodeCache.get(key);
		if(dto!=null){
			return dto.getVerCode();
		}
		return null;
	}
	
}
