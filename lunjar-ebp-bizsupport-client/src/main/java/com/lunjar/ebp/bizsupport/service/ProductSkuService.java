package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.query.ProductSkuQuery;

/**
 * 商品sku服务类
 * @author Administrator
 *
 */
public interface ProductSkuService extends CommonService<ProductSku, ProductSkuQuery>{
	/**
	 * 通过条件删除
	 * @param productSku
	 */
	void deleteByCondition(ProductSku productSku);
}
