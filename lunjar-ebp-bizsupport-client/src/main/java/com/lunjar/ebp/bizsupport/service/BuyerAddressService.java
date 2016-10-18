package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.model.BuyerAddress;
import com.lunjar.ebp.bizsupport.query.BuyerAddressQuery;

/**
 * 买家详细地址服务类
 * @author Administrator
 *
 */
public interface BuyerAddressService extends CommonService<BuyerAddress, BuyerAddressQuery>{
	/****
	 * 通过id和buyerId删除
	 * @param buyerAddress
	 */
	int deleteByIdAndBuyerId(BuyerAddress buyerAddress);
}
