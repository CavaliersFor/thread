package com.lunjar.ebp.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lunjar.products.core.web.model.UploadedFile;
import com.lunjar.products.core.web.utils.UploadUtils;

@Controller
public class SwfUploadFileController {

	@Autowired
	private UploadUtils uploadUtils;

	@RequestMapping(value = "uploadfile")
	@ResponseBody
	public String upload(@RequestParam("Filedata") MultipartFile uploadFile) {
		UploadedFile uploadedFile = null;
		uploadedFile = uploadUtils.uploadFileTempDir(uploadFile);
		System.out.println(uploadedFile.getUploadFullPath());
		return uploadedFile.getUploadFullPath();
	}
}