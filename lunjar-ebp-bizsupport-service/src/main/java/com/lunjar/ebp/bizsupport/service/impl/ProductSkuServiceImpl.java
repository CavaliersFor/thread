package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.mappers.ProductSkuMapper;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.query.ProductSkuQuery;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;

@Service(value = "productSkuService")
public class ProductSkuServiceImpl implements ProductSkuService {

	@Autowired
	private ProductSkuMapper productSkuMapper;

	@Override
	public ProductSku load(Long id) {
		return productSkuMapper.load(id);
	}

	@Override
	public Long add(ProductSku t) {
		productSkuMapper.insert(t);
		return t.getId();
	}

	@Override
	public int update(ProductSku t) {
		return productSkuMapper.update(t);
	}

	@Override
	public void delete(Long id) {
		productSkuMapper.delete(id);
	}

	@Override
	public List<ProductSku> queryList(ProductSkuQuery q) {
		return productSkuMapper.queryList(q);
	}

	@Override
	public int queryCount(ProductSkuQuery q) {
		return productSkuMapper.queryCount(q);
	}

	@Override
	public void deleteByCondition(ProductSku productSku) {
		productSkuMapper.deleteByCondition(productSku);
	}

}
