package com.lunjar.ebp.bizsupport.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ancun.bps.core.CommonServiceResultCode;
import com.ancun.bps.core.store.BpsFileStoreException;

/**
 * 文件处理类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月12日下午4:02:17
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 获取目标文件全路径
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月12日下午4:09:57
	 * @param storeRootDir
	 * @param targetPath
	 * @return
	 */
	public static String getFileFullPath(String storeRootDir, String targetPath) {
		String fileFullPath;
		if (targetPath.startsWith("/")) {
			fileFullPath = storeRootDir + targetPath;
			targetPath = targetPath.substring(1);
		} else {
			fileFullPath = storeRootDir + "/" + targetPath;
		}
		logger.info("Save Object,fileFullPath:[{}]", fileFullPath);
		return fileFullPath;
	}

	/**
	 * 检查文件路径是否为空
	 * 
	 * @param filePath
	 * @throws BpsFileStoreException
	 *             <p>
	 *             author: <a href="mailto:caozhenfei@ancun.com">CaoZhenfei</a>
	 *             <br>
	 *             create at: 2016年4月9日 下午2:01:39
	 */
	public static void checkFilePath(String filePath) throws BpsFileStoreException {
		if (StringUtils.isBlank(filePath)) {
			throw new BpsFileStoreException(CommonServiceResultCode._1600001);
		}
	}

	/**
	 * 生成产品属性图片相对路径
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月12日下午7:18:32
	 * @param enterpriseId
	 *            商家id
	 * @param productId
	 *            商品id
	 * @param type
	 *            图片类型
	 * @return
	 */
	public static String getPropPath(Long id) {
		// TODO Auto-generated method stub
		String path = "";
		int a = (int) (id / 100);
		path += a + "/" + id + "/" + a + "/" + System.currentTimeMillis() + ".jpg";
		return path;
	}

	/**
	 * 读取文件夹中的文件名称
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月16日上午9:37:35
	 * @param args
	 */
	public static List<String> getFileNames(String filePath) {
		File file = new File(filePath);
		// get the folder list
		File[] array = file.listFiles();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				// only take file name
				// System.out.println("^^^^^" + array[i].getName());
				// // take file path and name
				// System.out.println("#####" + array[i]);
				// // take file path and name
				// System.out.println("*****" + array[i].getPath());
				list.add(array[i].getName());
			} else if (array[i].isDirectory()) {
				getFileNames(array[i].getPath());
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String path1 = "D:/tomcat/tomcat1/webapps/lunjar-ebp-portal-webapp/WEB-INF/lib";
		String path2 = "C:/Users/lenovo/Desktop/lib/lib";
		List<String> list1 = getFileNames(path2);
		List<String> list2 = getFileNames(path1);
		for (String s : list2) {
			if (list1.contains(s)) {
				System.out.println(s);
			} else {
				System.out.println(s);
			}
		}
	}
}
