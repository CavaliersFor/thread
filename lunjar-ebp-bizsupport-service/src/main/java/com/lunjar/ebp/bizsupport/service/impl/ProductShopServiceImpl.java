package com.lunjar.ebp.bizsupport.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Null;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.dto.ProductShopDto;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.enums.EnumProductShopStatus;
import com.lunjar.ebp.bizsupport.mappers.CategoryMapper;
import com.lunjar.ebp.bizsupport.mappers.CombinationProductMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductShopMapper;
import com.lunjar.ebp.bizsupport.model.Category;
import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductShop;
import com.lunjar.ebp.bizsupport.query.CategoryQuery;
import com.lunjar.ebp.bizsupport.query.CombinationProductQuery;
import com.lunjar.ebp.bizsupport.query.ProductQuery;
import com.lunjar.ebp.bizsupport.query.ProductShopQuery;
import com.lunjar.ebp.bizsupport.service.ProductShopService;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 商品服务实现类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月10日下午3:27:14
 */
@Service("productShopService")
public class ProductShopServiceImpl implements ProductShopService {
	private static final Logger logger = LoggerFactory.getLogger(ProductShopServiceImpl.class);

	@Value("${store.server.url}")
	private String storeUrl;
	@Autowired
	ProductShopMapper productShopMapper;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	CombinationProductMapper combinationProductMapper;

	@Override
	public Long add(ProductShop productShop) throws ServiceException {
		Assert.notNull(productShop, "object productShop is null");
		if (productShop.getCategoryId() == null) {
			logger.warn(BizSupportCode._2001014.getMsg());
			throw new ServiceException(BizSupportCode._2001014.getCode(), BizSupportCode._2001014.getMsg());
		}
		if (productShop.getProductId() == null) {
			logger.warn(BizSupportCode._2001015.getMsg());
			throw new ServiceException(BizSupportCode._2001015.getCode(), BizSupportCode._2001015.getMsg());
		}
		if (productShop.getShopId() == null) {
			logger.warn(BizSupportCode._2001016.getMsg());
			throw new ServiceException(BizSupportCode._2001016.getCode(), BizSupportCode._2001016.getMsg());
		}
		if (productShop.getType() == null) {
			logger.warn(BizSupportCode._2001025.getMsg());
			throw new ServiceException(BizSupportCode._2001025.getCode(), BizSupportCode._2001025.getMsg());
		}
		productShop.setGmtCreate(new Date());
		productShop.setGmtModify(new Date());
		productShop.setStatus(EnumProductShopStatus.SALE.getValue());
		productShopMapper.insert(productShop);
		return productShop.getId();
	}

	@Override
	public void update(ProductShop productShop) throws ServiceException {
		Assert.notNull(productShop, "object productShop is null");
		Assert.notNull(productShop.getId(), "required productShop id is null");
		if (productShop.getShopId() == null) {
			logger.warn(BizSupportCode._2001016.getMsg());
			throw new ServiceException(BizSupportCode._2001016.getCode(), BizSupportCode._2001016.getMsg());
		}
		productShopMapper.update(productShop);
	}
	
	@Override
	public List<ProductShop> queryLists(ProductShopQuery query){
		Assert.notNull(query, "object query is null");
		List<ProductShop> productShops = productShopMapper.queryList(query);
		return productShops;
	}

	@Override
	public List<ProductShopDto> queryList(ProductShopQuery query) throws ServiceException {
		Assert.notNull(query, "object query is null");
		if (query.getShopId() == null) {
			logger.warn(BizSupportCode._2001016.getMsg());
			throw new ServiceException(BizSupportCode._2001016.getCode(), BizSupportCode._2001016.getMsg());
		}
		List<ProductShopDto> list = new ArrayList<>();
//		query.setType(1);// 单商品
//		query.setStatus(EnumProductShopStatus.SALE.getValue());
		List<ProductShop> productShops = productShopMapper.queryList(query);
		if (CollectionUtils.isNotEmpty(productShops)) {
			int len = productShops.size();
			ProductShop productShop = null;
			ProductShopDto dto = null;
			Long[] productIds = new Long[len];// 单商品
			Long[] cids = new Long[len];
			for (int i = 0; i < len; i++) {
				dto = new ProductShopDto();
				productShop = productShops.get(i);
				productIds[i] = productShop.getProductId();
				cids[i] = productShop.getCategoryId();
				dto.setCategoryId(productShop.getCategoryId());
				dto.setGmtCreate(productShop.getGmtCreate());
				dto.setGmtModify(productShop.getGmtModify());
				dto.setId(productShop.getId());
				dto.setShopId(productShop.getShopId());
				dto.setStatus(productShop.getStatus());
				dto.setType(productShop.getType());
				dto.setProductId(productShop.getProductId());
				dto.setCategoryId(productShop.getCategoryId());
				list.add(dto);
			}
			// 商品区分单商品与组合商品，用type来判断 type=1为单商品 type=2为组合商品
			ProductQuery productQuery = new ProductQuery();
			productQuery.setIdArray(productIds);
			productQuery.setStatus(1);
			List<Product> products = productMapper.queryList(productQuery);
			Map<Long, Object> productMap = new HashMap<>();
			if (CollectionUtils.isNotEmpty(products)) {
				for (Product p : products) {
					productMap.put(p.getId(), p);
				}
			}
			CategoryQuery categoryQuery = new CategoryQuery();
			categoryQuery.setIdArray(cids);
			List<Category> categories = categoryMapper.queryList(categoryQuery);
			Map<Long, Object> catMap = new HashMap<>();
			if (CollectionUtils.isNotEmpty(categories)) {
				for (Category c : categories) {
					catMap.put(c.getId(), c);
				}
			}
			if (CollectionUtils.isNotEmpty(list)) {
				Product product = null;
				Category category = null;
				for (ProductShopDto psd : list) {
					product = (Product) productMap.get(psd.getProductId());
					if (product != null) {
						psd.setPrice(product.getPrice());
						psd.setProductName(product.getName());
						psd.setProductPicPath(storeUrl + product.getPathUrl());
						psd.setRealPrice(product.getSalePrice());
					}
					category = (Category) catMap.get(psd.getCategoryId());
					if (category != null) {
						psd.setCategoryName(category.getName());
						psd.setCategorySort(category.getSortNum());
					}
				}
			}
		}

		return list;
	}

	@Override
	public int queryCount(ProductShopQuery query) throws ServiceException {
		Assert.notNull(query, "object query is null");
		if (query.getShopId() == null) {
			logger.warn(BizSupportCode._2001016.getMsg());
			throw new ServiceException(BizSupportCode._2001016.getCode(), BizSupportCode._2001016.getMsg());
		}
		return productShopMapper.queryCount(query);
	}

	@Override
	public ProductShop load(Long id) {
		Assert.notNull(id, "object id is null");
		return productShopMapper.load(id);
	}

	@Override
	public void updateByCondition(ProductShop productShop) {
		productShopMapper.updateByCondition(productShop);
	}

	@Override
	public void deleteProductShop(ProductShop productShop) {
		Assert.notNull(productShop, "object productShop is null");
		Assert.notNull(productShop.getId(), "object productShop id is null");
		Assert.notNull(productShop.getShopId(), "object productShop shopId is null");
		productShopMapper.deleteProductShop(productShop);
	}
}
