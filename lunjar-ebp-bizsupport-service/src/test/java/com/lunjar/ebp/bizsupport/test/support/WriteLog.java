package com.lunjar.ebp.bizsupport.test.support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WriteLog {
	protected static final Log logger = LogFactory.getLog(WriteLog.class);

	public static void main(String[] args) throws InterruptedException {
		Object object = null;
		// TODO Auto-generated method stub
		while (true) {
			// 每隔两秒log输出一下当前系统时间戳
			// logger.info(new Date().getTime());
			Thread.sleep(2000);
			// throw new Exception("exception msg");
			// object.equals("a");
			try {
				InputStream inputStrem = new FileInputStream("c:\\a.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}

}
