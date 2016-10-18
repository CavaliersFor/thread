package com.lunjar.ebp.bizsupport.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.taskdefs.Length;

import com.lunjar.ebp.bizsupport.dto.ProductShopDto;
import com.lunjar.ebp.bizsupport.dto.ShopIndexParams;
import com.lunjar.products.core.utils.MD5Util;

public class GenerateUtil {

	/**
	 * 系统生成唯一的accessKey
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日上午11:26:37
	 * @return 接入者key
	 */
	public static String gennerateAccessKey() {
		String uuidString = UUID.randomUUID().toString();
		return MD5Util.generateCommonMD5(uuidString);
	}

	/**
	 * 系统生成唯一的secretKey
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日上午11:26:22
	 * @return 密钥
	 */
	public static String gennerateSecretKey() {
		String uuidString = UUID.randomUUID().toString();
		return shaEncode(uuidString);
	}

	public static String shaEncode(String inStr) {
		MessageDigest sha = null;
		byte[] byteArray = null;
		try {
			sha = MessageDigest.getInstance("SHA");
			byteArray = inStr.getBytes("UTF-8");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] md5Bytes = sha.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 根据商家快递费用信息与收货人城市获取快递费用
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月19日上午11:12:04
	 * @param price
	 *            价格信息字符串
	 * @param city
	 *            收货城市
	 * @param num
	 *            数量
	 * @param valuation
	 *            计费方式：1：数量2：重量3：体积
	 * @return
	 */
	public static BigDecimal getPostFee(String price, String city, Integer num, Double v) {
		// TODO Auto-generated method stub
		String[] pf = price.split(";");
		String _price = "";
		String p = "";
		String add = "";
		BigDecimal _p = null;
		if (StringUtils.isBlank(city)) {
			_price = pf[0].split(":")[1];
		} else {
			int pfLen = pf.length;
			Map<String, String> map = new HashMap<>();
			for (int i = 1; i < pfLen; i++) {
				int index = pf[i].indexOf(":");
				String citys = pf[i].substring(0, index);
				String prices = pf[i].substring(index + 1);
				String[] c = citys.split(",");
				int len = c.length;
				for (int j = 0; j < len; j++) {
					map.put(c[j], prices);
				}
			}
			_price = map.get(city);
		}
		if (StringUtils.isBlank(_price)) {
			_price = pf[0].split(":")[1];
		}
		p = _price.split(",")[0];
		add = _price.split(",")[1];
		_p = new BigDecimal(p);
		// v不为空则是根据体积或者重量来计算
		if (v != null) {
			_p = _p.multiply(new BigDecimal(v));
		}
		for (int k = 1; k < num; k++) {
			_p = _p.add(new BigDecimal(add));
		}
		return _p;
	}

	/**
	 * 根据传入的图片信息生成list
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月24日上午9:33:04
	 * @param picUrls
	 * @param url
	 *            访问根地址
	 * @return
	 */
	public static List<ShopIndexParams> createShopIndexList(String picUrls, String url) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(picUrls)) {
			return null;
		}
		List<ShopIndexParams> list = new ArrayList<>();
		String[] params = picUrls.split(";");
		int len = params.length;
		ShopIndexParams param = null;
		String s = "";
		for (int i = 0; i < len; i++) {
			s = params[i];
			String[] ss = s.split(",");
			if (StringUtils.isNoneBlank(s)) {
				param = new ShopIndexParams();
				if (StringUtils.isNotBlank(url)) {
					ss[0] = url + ss[0];
				}
				param.setUrl(ss[0]);
				// param.setProductId(Long.parseLong(ss[1]));
				if (ss.length > 1) {
					param.setSortNum(Integer.parseInt(ss[1]));
				}
				if (3 == ss.length) {
					param.setSkipUrl(s.split(",")[2]);
				}
			}
			list.add(param);
		}
		Collections.sort(list);
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, ArrayList> sort(List<ProductShopDto> pShops) {
		TreeMap tm = new TreeMap();
		for (int i = 0; i < pShops.size(); i++) {
			ProductShopDto p = (ProductShopDto) pShops.get(i);
			if (tm.containsKey(p.getCategorySort())) {//
				ArrayList psdList = (ArrayList) tm.get(p.getCategorySort());
				psdList.add(p);
			} else {
				ArrayList tem = new ArrayList();
				tem.add(p);
				tm.put(p.getCategorySort(), tem);
			}
		}
		return tm;
	}

	// public static void main(String[] args) {
	// String price = "默认:5,2;中山,东莞,顺德:5,3;长沙,厦门:10,0";
	// String city = "顺德33";
	// Integer num = 1;
	// Double v = 1d;
	// BigDecimal bd = getPostFee(price, city, num, v);
	// System.out.println(bd.toString());
	// }

	/**
	 * 不够位数的在前面补0，保留num的长度位数字
	 * 
	 * @param code
	 * @return
	 */
	public static String autoGenericCode(String code, int num) {
		String result = "";
		// 保留num的位数
		result = String.format("%0" + num + "d", Integer.parseInt(code));

		return result;
	}

	/**
	 * 不够位数的在前面补0，保留num的长度位数字
	 * 
	 * @param code
	 * @return
	 */
	public static String autoGenericCodeEnd(String code, int num) {
		String result = "";
		// 保留num的位数
		result = code + String.format("%1$0" + (num - code.length()) + "d", 0);
		return result;
	}

	// public static void main(String[] args) {
	// String code = "1";
	// int num = 6;
	// String result = autoGenericCodeEnd(code, num);
	// System.out.println(result);
	// }

	public static Long[] getProductIds(String picUrls) {
		// TODO Auto-generated method stub
		if (!StringUtils.isBlank(picUrls)) {
			String[] s = picUrls.split(";");
			int len = s.length;
			Long[] ids = new Long[len];
			TreeMap<Integer, Long> map = new TreeMap<>();
			for (int i = 0; i < len; i++) {
				if (StringUtils.isNotBlank(s[i])) {
					String id = s[i].split(",")[0];
					String sort = s[i].split(",")[1];
					if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(sort)) {
						map.put(Integer.parseInt(sort), Long.parseLong(id));
					}
				}
			}
			List<Long> list = new ArrayList<Long>(map.values());
			int l = list.size();
			for (int j = 0; j < l; j++) {
				ids[j] = list.get(j);
			}
			return ids;
		} else {
			return null;
		}
	}
}
