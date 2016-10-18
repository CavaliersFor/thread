package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.dto.EnterpriseDto;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.query.EnterpriseQuery;
import com.lunjar.products.core.exception.ServiceException;
/**
 * 商家服务类
 * @author Administrator
 *
 */
public interface EnterpriseService extends CommonService<Enterprise, EnterpriseQuery>{
	/***
	 * 登陆方法
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Enterprise login(EnterpriseDto dto) throws ServiceException;
	
	
	/**
	 * 通过id查询，返回的图片的路径是绝对路径和相对路径
	 * @param id
	 * @return
	 */
	public Enterprise getById(Long id);
}
