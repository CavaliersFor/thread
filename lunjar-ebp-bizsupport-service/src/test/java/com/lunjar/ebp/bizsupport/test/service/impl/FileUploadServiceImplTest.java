package com.lunjar.ebp.bizsupport.test.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.service.FileUploadService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class FileUploadServiceImplTest extends BizTestSupport {

	@Autowired
	private FileUploadService fileUploadService;

	@Test
	public void uploadFile() throws ServiceException {
		InputStream is = null;
		try {
			is = new FileInputStream("G:/工作文档/项目图片2016-09-07/红酒.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long id = 1l;
		String filePath = fileUploadService.uploadFile(id, is);
		System.out.println("图片路径为=" + filePath);
	}

}
