package com.lunjar.ebp.bizsupport.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.mappers.ProductMapper;
import com.lunjar.ebp.bizsupport.service.FileUploadService;
import com.lunjar.ebp.bizsupport.service.ProductPropImgsService;
import com.lunjar.ebp.bizsupport.utils.FileUtil;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.utils.FileUtils;

/**
 * 文件上传服务类实现
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月16日上午10:34:41
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

	@Autowired
	ProductMapper productMapper;

	@Autowired
	ProductPropImgsService productPropImgsService;

	@Value("${store.dir}")
	private String storeRootDir;

	@Override
	public String uploadFile(Long id,InputStream is) throws ServiceException {
		if (is == null) {
			logger.warn(BizSupportCode._2001013.getMsg());
			throw new ServiceException(BizSupportCode._2001013.getCode(), BizSupportCode._2001013.getMsg());
		}
		if (id == null) {
			logger.warn(BizSupportCode._2001020.getMsg());
			throw new ServiceException(BizSupportCode._2001020.getCode(), BizSupportCode._2001020.getMsg());
		}
		String path = FileUtil.getPropPath(id);
		String fulPpath = FileUtil.getFileFullPath(storeRootDir, path);
		try {
			FileUtils.writeFile(is, fulPpath);
		} catch (IOException e) {
			logger.warn(BizSupportCode._2001001.getMsg(), e);
			throw new ServiceException(BizSupportCode._2001001.getCode(), BizSupportCode._2001001.getMsg());
		}
		return path;
	}
	
	
	public Map<String,String> uploadFileReturnMap(Long id,InputStream is) throws ServiceException {
		
		if (is == null) {
			logger.warn(BizSupportCode._2001013.getMsg());
			throw new ServiceException(BizSupportCode._2001013.getCode(), BizSupportCode._2001013.getMsg());
		}
		if (id == null) {
			logger.warn(BizSupportCode._2001020.getMsg());
			throw new ServiceException(BizSupportCode._2001020.getCode(), BizSupportCode._2001020.getMsg());
		}
		String path = FileUtil.getPropPath(id);
		String fulPpath = FileUtil.getFileFullPath(storeRootDir, path);
		try {
			FileUtils.writeFile(is, fulPpath);
		} catch (IOException e) {
			logger.warn(BizSupportCode._2001001.getMsg(), e);
			throw new ServiceException(BizSupportCode._2001001.getCode(), BizSupportCode._2001001.getMsg());
		}
		Map<String,String> pathMap = new HashMap<>();
		pathMap.put("path", path);
		pathMap.put("fulPpath", fulPpath);
		return pathMap;
	}
}
