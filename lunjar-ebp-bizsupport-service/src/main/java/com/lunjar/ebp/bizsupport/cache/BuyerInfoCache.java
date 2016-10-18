package com.lunjar.ebp.bizsupport.cache;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.bizsupport.dto.ShopInfo;
import com.lunjar.ebp.bizsupport.enums.EnumBuyerStatus;
import com.lunjar.ebp.bizsupport.mappers.BuyerMapper;
import com.lunjar.ebp.bizsupport.mappers.SellerMapper;
import com.lunjar.ebp.bizsupport.mappers.ShopMapper;
import com.lunjar.ebp.bizsupport.model.Buyer;
import com.lunjar.ebp.bizsupport.model.Seller;
import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.ebp.bizsupport.service.BuyerService;
import com.lunjar.ebp.bizsupport.service.ShopService;
import com.lunjar.products.core.cache.CacheSupport;
import com.lunjar.products.core.cache.EHCacheUtil;
import com.lunjar.products.core.exception.ServiceException;

import net.sf.ehcache.CacheManager;

/**
 * 买家信息缓存类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日下午2:20:57
 */
@Component
public class BuyerInfoCache extends CacheSupport<Buyer> {
	public static final String CACHE_ID = BuyerInfoCache.class.getSimpleName();

	@Autowired
	@Qualifier("ehCacheManagerBizsupport")
	private CacheManager ehCacheManager;

	@Autowired
	BuyerService buyerService;
	@Autowired
	ShopService shopService;

	@Override
	public String getCacheId() {
		return CACHE_ID;
	}

	@PostConstruct
	public void init() {
		ehCacheUtil = new EHCacheUtil(ehCacheManager, CACHE_ID);
	}

	/**
	 * 根据key获取买家信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月25日下午4:55:12
	 * @param key 缓存key
	 * @param openId 用户openid
	 * @return
	 * @throws ServiceException 
	 */
	public Buyer load(String key, String openId, String appKey) throws ServiceException {
		Buyer buyer = super.get(key);
		if (null == buyer && StringUtils.isNotBlank(openId)){
			Shop shop = shopService.getShopByAccessKey(appKey);
			buyer = buyerService.getBuyerByOpenIdAndPartnerId(openId, shop.getPartnerId());
			if (null == buyer) {
				buyer = new Buyer();
				buyer.setGmtCreate(new Date());
				buyer.setGmtModify(new Date());
				buyer.setPartnerId(shop.getPartnerId());
				buyer.setStatus(EnumBuyerStatus.NORMAL.getValue());
				buyer.setBuyerId(openId);
				buyerService.add(buyer);
			}
			put(key, buyer);
		}
		return buyer;
	}

	/**
	 * 根据accessKey修改商铺对象缓存
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日下午2:59:37
	 * @param accessKey
	 * @return
	 */
//	public Buyer update(String key) {
//		ShopInfo shopInfo = null;
//		Shop shop = shopMapper.getShopByAccessKey(accessKey);
//		if (null != shop) {
//			shopInfo = new ShopInfo();
//			Seller seller = sellerMapper.load(shop.getSaleId());
//			initShopInfo(seller, shop, shopInfo);
//			ehCacheUtil.put(accessKey, shopInfo);
//		}
//		return shopInfo;
//	}

	/**
	 * 根据key删除用户信息缓存
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日下午3:00:17
	 * @param accessKey
	 */
	public void remove(String key) {
		ehCacheUtil.remove(key);
	}
}
