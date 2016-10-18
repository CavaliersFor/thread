package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.org.eclipse.jdt.internal.core.BatchInitializationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.mappers.BuyerAddressMapper;
import com.lunjar.ebp.bizsupport.model.BuyerAddress;
import com.lunjar.ebp.bizsupport.query.BuyerAddressQuery;
import com.lunjar.ebp.bizsupport.service.BuyerAddressService;
import com.lunjar.products.core.exception.ServiceException;

/***
 * 买家详细地址服务实现类
 * 
 * @author Administrator
 *
 */
@Service(value = "buyerAddressService")
public class BuyerAddressServiceImpl implements BuyerAddressService {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BuyerAddressServiceImpl.class);

	@Autowired
	private BuyerAddressMapper buyerAddressMapper;

	@Override
	public Long add(BuyerAddress buyerAddress) throws ServiceException {
		Assert.notNull(buyerAddress, "object buyerAddress is null");
		if (buyerAddress.getBuyerId() == null) {
			logger.warn(BizSupportCode._3000002.getMsg());
			throw new ServiceException(BizSupportCode._3000002.getCode(), BizSupportCode._3000002.getMsg());
		}
		if (StringUtils.isBlank(buyerAddress.getBuyerName())) {
			logger.warn(BizSupportCode._3000022.getMsg());
			throw new ServiceException(BizSupportCode._3000022.getCode(), BizSupportCode._3000022.getMsg());
		}
		if (StringUtils.isBlank(buyerAddress.getBuyerPhone())) {
			logger.warn(BizSupportCode._3000023.getMsg());
			throw new ServiceException(BizSupportCode._3000023.getCode(), BizSupportCode._3000023.getMsg());
		}
		if (buyerAddress.getStatus() == null) {
			logger.warn(BizSupportCode._5000001.getMsg());
			throw new ServiceException(BizSupportCode._5000001.getCode(), BizSupportCode._5000001.getMsg());
		}
		if (buyerAddress.getIsDefault() == null) {
			logger.warn(BizSupportCode._5000002.getMsg());
			throw new ServiceException(BizSupportCode._5000002.getCode(), BizSupportCode._5000002.getMsg());
		}
		if (StringUtils.isBlank(buyerAddress.getProvince())) {
			logger.warn(BizSupportCode._3000003.getMsg());
			throw new ServiceException(BizSupportCode._3000003.getCode(), BizSupportCode._3000003.getMsg());
		}
		if (StringUtils.isBlank(buyerAddress.getCity())) {
			logger.warn(BizSupportCode._3000004.getMsg());
			throw new ServiceException(BizSupportCode._3000004.getCode(), BizSupportCode._3000004.getMsg());
		}
//		if (StringUtils.isBlank(buyerAddress.getRegion())) {
//			logger.warn(BizSupportCode._3000004.getMsg());
//			throw new ServiceException(BizSupportCode._3000004.getCode(), BizSupportCode._3000004.getMsg());
//		}
		buyerAddressMapper.insert(buyerAddress);
		return buyerAddress.getId();
	}

	@Override
	public int update(BuyerAddress buyerAddress) {
		Assert.notNull(buyerAddress, "object buyerAddress is null");
		Assert.notNull(buyerAddress.getId(), "required buyerAddress id is null");
		return buyerAddressMapper.update(buyerAddress);
	}

	@Override
	public List<BuyerAddress> queryList(BuyerAddressQuery buyerAddressQuery) {
		return buyerAddressMapper.queryList(buyerAddressQuery);
	}

	@Override
	public int queryCount(BuyerAddressQuery buyerAddressQuery) {
		return buyerAddressMapper.queryCount(buyerAddressQuery);
	}

	@Override
	public BuyerAddress load(Long id) {
		return buyerAddressMapper.load(id);
	}

	@Override
	public void delete(Long id) {
		buyerAddressMapper.delete(id);
	}

	@Override
	public int deleteByIdAndBuyerId(BuyerAddress buyerAddress) {
		Assert.notNull(buyerAddress, "object buyerAddress is null");
		Assert.notNull(buyerAddress.getId(), "required buyerAddress id is null");
		Assert.notNull(buyerAddress.getBuyerId(), "required buyerAddress buyerId is null");
		return buyerAddressMapper.deleteByIdAndBuyerId(buyerAddress);
	}

}
