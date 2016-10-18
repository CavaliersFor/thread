package com.lunjar.ebp.bizsupport.service;

import java.io.InputStream;
import java.util.Map;

import com.lunjar.products.core.exception.ServiceException;

/**
 * 文件上传服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月16日上午10:34:08
 */
public interface FileUploadService {

	/**
	 * 上传附件
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月16日上午10:40:55
	 * @param is 文件流
	 * @param id 商家id或者商铺id
	 * @return
	 * @throws ServiceException
	 */
	String uploadFile(Long id,InputStream is) throws ServiceException;
	
	/**
	 * 返回是一个Map 一个是相对路径  一个是绝对路径
	 * @param id
	 * @param is
	 * @return
	 * @throws ServiceException
	 */
	public Map<String,String> uploadFileReturnMap(Long id,InputStream is) throws ServiceException;

}
