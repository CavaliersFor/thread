package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.dto.CartDto;
import com.lunjar.ebp.bizsupport.dto.CombinDto;
import com.lunjar.ebp.bizsupport.dto.EnterpriseProModel;
import com.lunjar.ebp.bizsupport.dto.ProductDto;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.query.ProductQuery;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;

/**
 * 产品服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月10日下午3:07:50
 */
public interface ProductService {

	/**
	 * 新增产品
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月10日下午3:30:05
	 * @param product
	 * @return
	 */
	 Long add(Product product);

	 /**
	  * 更新产品信息
	  *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	  *2016年8月10日下午3:31:50
	  * @param product
	  */
	void update(Product product);

	/**
	 * 查询商品信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月10日下午3:32:55
	 * @param query 查询条件
	 * @return
	 */
	List<Product> queryList(ProductQuery query);

	/**
	 * 根据查询条件获得总数
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月10日下午3:33:35
	 * @param query
	 * @return
	 */
	int queryCount(ProductQuery query);

	/**
	 * 根据产品id查询具体产品
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月10日下午3:34:40
	 * @param id
	 * @return
	 */
	Product load(Long id);

	/**
	 * 新增产品：图片是以流的方式传入、并且ProductPropImgs 信息也一起新增
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月12日下午3:29:49
	 * @param productDto
	 * @return
	 * @throws ServiceException 
	 */
	Long add(ProductDto productDto) throws ServiceException;
	
	/**
	 * 通过店铺id查询推荐商品
	 * @return
	 */
	public List<Product> getGroomProduct(Long shopId);
	 
	/**
	 * 获得购物车中的数据
	 * @param ids
	 * @param buyerId
	 * @return
	 */
	public List<EnterpriseProModel> getCartList(String ids,Long buyerId,String city) throws ServiceException;
	
	/**
	 * 获得购物车中的数据
	 * @param listCart
	 * @return
	 */
	public List<EnterpriseProModel> getCartList(List<CartDto> listCart,String city)throws ServiceException;
	
	
	/**
	 * 
	 * @param id 组合id
	 * @param list 组合商品
	 * @return
	 */
	public EnterpriseProModel getCombin(Long id,List<CombinDto> list,Long shopId,Long buyerId,String city);

	/**
	 * 获取商品信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月1日下午3:21:54
	 * @param id
	 * @return
	 */
	ProductDto getProductInfo(Long id);
	
	/**
	 * 查询商品和商品的sku的信息
	 * @param query
	 * @return
	 */
	public PageResult<ProductDto> queryListAndSku(ProductQuery query);
	
	/**
	 * 商品上下架
	 * @param ids
	 * @param type
	 */
	public void updateProductShelves(Long[] ids,String type);
	
	/**
	 * 删除商品，物理删除
	 * @param ids
	 */
	public void deleteProduct(Long[] ids);
	/**
	 * 通过id删除
	 * @param id
	 */
	void delete(Long id);
	
	
	/**
	 * 更新商品信息和sku信息
	 * @param productId 商品id
	 * @param skuIds skuid数组
	 * @param productName 商品名称
	 * @param prices 价格
	 */
	public void updateProductAndSku(Long productId,Long[] skuIds,String productName,String[] prices);
	
	/**
	 * 保存商品方法
	 * @param p
	 * @param listSku
	 * @param listImages
	 */
	public void saveProduct(Product p,List<ProductSku> listSku,List<ProductPropImgs> listImages) throws ServiceException;
}
