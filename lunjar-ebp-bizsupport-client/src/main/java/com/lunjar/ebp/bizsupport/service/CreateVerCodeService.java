package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.dto.VerCodeDto;

public interface CreateVerCodeService {
	/**
	 * 获取验证码
	 * @param num
	 * @return
	 */
	public String getVerCode(String key);
	
	
	/**
	 * 获取验证码
	 * @param num
	 * @return
	 */
	public VerCodeDto createVerCode(Integer num,String key);
}
