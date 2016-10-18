package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.mappers.SellerMapper;
import com.lunjar.ebp.bizsupport.model.Seller;
import com.lunjar.ebp.bizsupport.query.SellerQuery;
import com.lunjar.ebp.bizsupport.service.SellerService;

/**
 * 卖家服务实现类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日上午10:05:25
 */
@Service("sellerService")
public class SellerServiceImpl implements SellerService {
	private static final Logger log = LoggerFactory.getLogger(SellerServiceImpl.class);

	@Autowired
	SellerMapper sellerMapper;

	@Override
	public Long add(Seller seller) {
		Assert.notNull(seller, "object seller is null");
		sellerMapper.insert(seller);
		return seller.getId();

	}

	@Override
	public void update(Seller seller) {
		Assert.notNull(seller, "object seller is null");
		Assert.notNull(seller.getId(), "required seller id is null");
		sellerMapper.update(seller);
	}

	@Override
	public List<Seller> queryList(SellerQuery query) {
		return sellerMapper.queryList(query);
	}

	@Override
	public int queryCount(SellerQuery query) {
		return sellerMapper.queryCount(query);
	}

	@Override
	public Seller load(Long id) {
		Assert.notNull(id, "object id is null");
		return sellerMapper.load(id);
	}

}
