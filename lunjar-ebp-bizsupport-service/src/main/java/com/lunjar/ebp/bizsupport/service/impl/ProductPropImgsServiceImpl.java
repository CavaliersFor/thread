package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.mappers.ProductPropImgsMapper;
import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.query.ProductPropImgsQuery;
import com.lunjar.ebp.bizsupport.service.ProductPropImgsService;

/**
 * 产品图片服务实现类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月12日上午9:30:43
 */
@Service("productPropImgsService")
public class ProductPropImgsServiceImpl implements ProductPropImgsService {
	private static final Logger log = LoggerFactory.getLogger(ProductPropImgsServiceImpl.class);

	@Value("${store.server.url}")
	private String storeUrl;
	
	@Autowired
	ProductPropImgsMapper productPropImgsMapper;

	@Override
	public Long add(ProductPropImgs productPropImgs) {
		Assert.notNull(productPropImgs, "object productPropImgs is null");
		productPropImgsMapper.insert(productPropImgs);
		return productPropImgs.getId();

	}

	@Override
	public void update(ProductPropImgs productPropImgs) {
		Assert.notNull(productPropImgs, "object productPropImgs is null");
		Assert.notNull(productPropImgs.getId(), "required productPropImgs id is null");
		productPropImgsMapper.update(productPropImgs);
	}

	@Override
	public List<ProductPropImgs> queryList(ProductPropImgsQuery query) {
		List<ProductPropImgs> list =  productPropImgsMapper.queryList(query);
		if (CollectionUtils.isNotEmpty(list)) {
			for(ProductPropImgs ppi: list) {
				ppi.setRelativePath(ppi.getPicPath());
				ppi.setPicPath(storeUrl + ppi.getPicPath());
			}
		}
		return list;
	}

	@Override
	public int queryCount(ProductPropImgsQuery query) {
		return productPropImgsMapper.queryCount(query);
	}

	@Override
	public ProductPropImgs load(Long id) {
		Assert.notNull(id, "object id is null");
		return productPropImgsMapper.load(id);
	}

	@Override
	public void deleteByCondition(ProductPropImgs productPropImgs) {
		productPropImgsMapper.deleteByCondition(productPropImgs);
	}

}
