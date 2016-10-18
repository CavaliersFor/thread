package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.model.Buyer;
import com.lunjar.ebp.bizsupport.query.BuyerQuery;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 买家详细地址服务类
 * @author Administrator
 *
 */
public interface BuyerService extends CommonService<Buyer, BuyerQuery>{
	/**
	 * 根据缓存key获取买家信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月25日下午5:08:20
	 * @param key 缓存key
	 * @param openId 
	 * @param appKey
	 * @return
	 */
	Buyer getBuyerInfo(String key, String openId, String appKey)throws ServiceException;

	/**
	 * 删除缓存
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月25日下午5:09:04
	 * @param key
	 */
	void removeBuyerInfo(String key);

	/**
	 * 根据合作方，用户在合作方的openId获取用户信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月25日下午6:07:03
	 * @param openId
	 * @param partnerId
	 * @return
	 */
	Buyer getBuyerByOpenIdAndPartnerId(String openId, Long partnerId);
}
