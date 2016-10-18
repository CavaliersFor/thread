package com.ancun.bps.portal.partner.service;

import com.ancun.bps.portal.partner.support.BizTestSupport;
import com.lunjar.ebp.bizsupport.service.FileUploadService;
import com.lunjar.products.core.exception.ServiceException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/5/3 17:39
 */
public class PortalPartnerServiceTest extends BizTestSupport {
	
	
	@Autowired
	private FileUploadService fileUploadService;
	
    @Test
    public void testModifyEnterprisePartner() throws Exception {
        System.out.println("11111111111111111111");
        
    }
    
    @Test
	public void uploadFile() throws ServiceException {
		InputStream is = null;
		try {
			is = new FileInputStream("F:/奥利奥390g 零食巧克力夹心饼干oreo草莓原味.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long id = 1l;
		String filePath = fileUploadService.uploadFile(id, is);
		System.out.println("图片路径为=" + filePath);
	}
}