package com.lunjar.ebp.bizsupport.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.bizsupport.dto.ShopInfo;
import com.lunjar.ebp.bizsupport.mappers.SellerMapper;
import com.lunjar.ebp.bizsupport.mappers.ShopMapper;
import com.lunjar.ebp.bizsupport.model.Seller;
import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.products.core.cache.CacheSupport;
import com.lunjar.products.core.cache.EHCacheUtil;

import net.sf.ehcache.CacheManager;

/**
 * 商铺缓存类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日下午2:20:57
 */
@Component
public class ShopInfoCache extends CacheSupport<ShopInfo> {
	public static final String CACHE_ID = ShopInfoCache.class.getSimpleName();

	@Autowired
	@Qualifier("ehCacheManagerBizsupport")
	private CacheManager ehCacheManager;

	@Autowired
	ShopMapper shopMapper;
	@Autowired
	SellerMapper sellerMapper;

	@Override
	public String getCacheId() {
		return CACHE_ID;
	}

	@PostConstruct
	public void init() {
		ehCacheUtil = new EHCacheUtil(ehCacheManager, CACHE_ID);
	}

	/**
	 * 根据accessKey获取商店信息
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日下午2:54:14
	 * @param accessKey
	 *            商店的accessKey
	 * @return
	 */
	public ShopInfo load(String accessKey) {
		ShopInfo shopInfo = super.get(accessKey);
		if (null == shopInfo) {
			Shop shop = shopMapper.getShopByAccessKey(accessKey);
			if (null != shop) {
				Seller seller = sellerMapper.load(shop.getSaleId());
				shopInfo = new ShopInfo();
				initShopInfo(seller, shop, shopInfo);
				put(accessKey, shopInfo);
			}
		}
		return shopInfo;
	}

	private void initShopInfo(Seller seller, Shop shop, ShopInfo shopInfo) {
		// TODO Auto-generated method stub
		shopInfo.setAccessKey(shop.getAccessKey());
		shopInfo.setId(shop.getId());
		shopInfo.setMobile(seller.getMobile());
		shopInfo.setPartnerId(shop.getPartnerId());
		shopInfo.setRegisterName(seller.getRegisterName());
		shopInfo.setSecretKey(shop.getSecretKey());
		shopInfo.setSellerId(seller.getId());
		shopInfo.setShopName(shop.getShopName());
		shopInfo.setStatus(shop.getStatus());
		shopInfo.setUrl(shop.getUrl());
	}

	/**
	 * 根据accessKey修改商铺对象缓存
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日下午2:59:37
	 * @param accessKey
	 * @return
	 */
	public ShopInfo update(String accessKey) {
		ShopInfo shopInfo = null;
		Shop shop = shopMapper.getShopByAccessKey(accessKey);
		if (null != shop) {
			shopInfo = new ShopInfo();
			Seller seller = sellerMapper.load(shop.getSaleId());
			initShopInfo(seller, shop, shopInfo);
			ehCacheUtil.put(accessKey, shopInfo);
		}
		return shopInfo;
	}

	/**
	 * 根据accessKey删除商品信息缓存
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日下午3:00:17
	 * @param accessKey
	 */
	public void remove(String accessKey) {
		ehCacheUtil.remove(accessKey);
	}
}
