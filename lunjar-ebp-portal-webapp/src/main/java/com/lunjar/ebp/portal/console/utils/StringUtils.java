package com.lunjar.ebp.portal.console.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * velocity转换数据字典字段工具类
 *
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/4/21 20:34
 */
@Component
public class StringUtils {
    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    public static String getShopId(String path) {
		String shopId = "";
		if (org.apache.commons.lang3.StringUtils.isBlank(path)) {
			return null;
		}
		int index1 = path.lastIndexOf("/");
		int index2 = path.indexOf("-");
		if (index2 < 0) {
			index2 = path.indexOf("?");
		}
		if (index1 < 0) {
			return null;
		}
		if (index2 > 0) {
			shopId = path.substring(index1 + 1, index2);
		} else {
			if (!path.contains(".")) {
				shopId = path.substring(index1 + 1);
			}else {
				return null;
			}
			
		}
		return shopId;
	}

}
