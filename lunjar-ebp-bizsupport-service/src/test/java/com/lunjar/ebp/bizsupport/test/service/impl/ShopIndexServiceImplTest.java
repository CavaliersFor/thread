package com.lunjar.ebp.bizsupport.test.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.ShopIndexDto;
import com.lunjar.ebp.bizsupport.mappers.SelfDeliveryUserMapper;
import com.lunjar.ebp.bizsupport.model.SelfDeliveryUser;
import com.lunjar.ebp.bizsupport.query.ShopIndexQuery;
import com.lunjar.ebp.bizsupport.service.FileUploadService;
import com.lunjar.ebp.bizsupport.service.ShopIndexService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class ShopIndexServiceImplTest extends BizTestSupport {

	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private ShopIndexService shopIndexService;
	@Autowired
	private SelfDeliveryUserMapper selfDeliveryUserMapper;

	@Test
	public void addShopIndex() {
		String path = "G:\\工作文档\\商品图_压缩\\波力卷";
		File file = new File(path);
		File[] tempList = file.listFiles();
		System.out.println("该目录下对象个数：" + tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				isFile(tempList[i]);
			}
			if (tempList[i].isDirectory()) {
				File[] list = tempList[i].listFiles();
				isDirectory(list);
			}
		}
	}

	public void isFile(File file) {
		String path = file.getPath();

		InputStream is = null;
		try {
			is = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long id = 1l;
		try {
			String filePath = fileUploadService.uploadFile(id, is);

			SelfDeliveryUser s = new SelfDeliveryUser();
			s.setUserName(path);
			s.setPassWord(filePath);
			s.setGmtCreate(new Date());
			s.setStatus(1);
			s.setCollectPlaceId(1L);
			selfDeliveryUserMapper.insert(s);

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

	public void isDirectory(File[] tempList) {
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				isFile(tempList[i]);
			}
			if (tempList[i].isDirectory()) {
				File[] list = tempList[i].listFiles();
				isDirectory(list);
			}
		}
	}

	@Test
	public void getShopIndex() throws ServiceException {
		Long shopId = 1l;
		List<ShopIndexDto> list = shopIndexService.getShopIndex(shopId);
		for (ShopIndexDto sid: list) {
			System.out.print("type is ==" + sid.getType());
			if (sid.getType() == 5 || sid.getType() == 3) {
				System.out.println(sid.getProList().get(0).getCategoryName());
			}else {
				System.out.println(sid.getList().get(0).getUrl());
			}
		}
	}
	
	@Test
	public void testQueryList(){
		ShopIndexQuery query = new ShopIndexQuery();
		Integer[] array = new Integer[2];
		array[0] = 3;
		array[1] = 5;
		query.setTypeArray(array);
		shopIndexService.queryList(query);
	}
}
