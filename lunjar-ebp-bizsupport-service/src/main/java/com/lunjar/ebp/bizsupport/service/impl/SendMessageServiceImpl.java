package com.lunjar.ebp.bizsupport.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lunjar.ebp.bizsupport.dto.SendMessageDto;
import com.lunjar.ebp.bizsupport.model.SendMessageResponse;
import com.lunjar.ebp.bizsupport.service.SendMessageService;
import com.lunjar.ebp.bizsupport.utils.SendCodeUtil;
import com.lunjar.products.core.utils.JsonUtils;

@Service(value = "sendMessageService")
public class SendMessageServiceImpl implements SendMessageService {
	
	
	@Value(value = "${apikey}")
	private String apikey;

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SendMessageServiceImpl.class);

	@Override
	public boolean sendCode(String mobile, String text) {
		try {
			String json = SendCodeUtil.sendSms(apikey, text, mobile);
			logger.error("手机号码{}发送结果{}", mobile, json);
			SendMessageResponse s = JsonUtils.jsonToBean(json, SendMessageResponse.class);
			if (s != null) {
				if ("0".equals(s.getCode())) {
					// 表示发送成功
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (IOException e) {
			logger.error("手机号码" + mobile + "发送短信失败", e);
		}
		return false;
	}

	@Override
	public boolean sendCodeByTpl(SendMessageDto sendMessageDto) {
		String mobile = "";

		if (sendMessageDto == null) {
			logger.error("发送短信对象为空");
			return false;
		}
		
		if (sendMessageDto.getTplId() == null) {
			logger.error("发送短信模板为空");
			return false;
		}
		
		if (sendMessageDto.getPhoneNum()==null || "".equals(sendMessageDto.getPhoneNum())) {
			logger.error("接收短信手机为空");
			return false;
		}
		if (sendMessageDto.getContent()==null || sendMessageDto.getContent().isEmpty()) {
			logger.error("发送短信内容为空");
			return false;
		}
		try {
			Map<String, String> contentMap = sendMessageDto.getContent();

			Set<String> keySet = contentMap.keySet();
			String[] strs = new String[keySet.size()];
			strs = keySet.toArray(strs);
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < strs.length; i++) {
				String value = contentMap.get(strs[i]);
				String key = strs[i];
				if (i == (strs.length - 1)) {
					// 最后一个
					sb.append(URLEncoder.encode("#" + key + "#", "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8"));
				} else {
					sb.append(URLEncoder.encode("#" + key + "#", "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8")
							+ "&");
				}
			}

			/*
			 * String tplvalue = URLEncoder.encode("#tradeno#", "UTF-8") + "=" +
			 * URLEncoder.encode("1234567890", "UTF-8") + "&" +
			 * URLEncoder.encode("#time#", "UTF-8") + "=" +
			 * URLEncoder.encode("12日", "UTF-8")+ "&" +
			 * URLEncoder.encode("#address#", "UTF-8") + "=" +
			 * URLEncoder.encode("省站一号售票口旁", "UTF-8")+ "&" +
			 * URLEncoder.encode("#code#", "UTF-8") + "=" +
			 * URLEncoder.encode("0987654321", "UTF-8");
			 */

			String tplvalue = sb.toString();
			mobile = sendMessageDto.getPhoneNum();
			Long tplId = sendMessageDto.getTplId();

			String json = SendCodeUtil.tplSendSms(apikey, tplId, tplvalue, mobile);
			logger.error("手机号码{}发送结果{}", mobile, json);
			SendMessageResponse s = JsonUtils.jsonToBean(json, SendMessageResponse.class);
			if (s != null) {
				if ("0".equals(s.getCode())) {
					// 表示发送成功
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (IOException e) {
			logger.error("手机号码" + mobile + "发送短信失败", e);
		}
		return false;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

}
