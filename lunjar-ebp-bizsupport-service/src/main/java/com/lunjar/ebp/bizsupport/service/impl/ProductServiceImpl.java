package com.lunjar.ebp.bizsupport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.cache.ProductCache;
import com.lunjar.ebp.bizsupport.dto.CartDto;
import com.lunjar.ebp.bizsupport.dto.CollectPlaceDto;
import com.lunjar.ebp.bizsupport.dto.CollectTimeDto;
import com.lunjar.ebp.bizsupport.dto.CombinDto;
import com.lunjar.ebp.bizsupport.dto.DiscountDto;
import com.lunjar.ebp.bizsupport.dto.EnterpriseProModel;
import com.lunjar.ebp.bizsupport.dto.ProductDto;
import com.lunjar.ebp.bizsupport.dto.ShopIndexParams;
import com.lunjar.ebp.bizsupport.dto.ShopIndexPicUrlDto;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.mappers.CollectPlaceMapper;
import com.lunjar.ebp.bizsupport.mappers.CombinationProductMapper;
import com.lunjar.ebp.bizsupport.mappers.EnterpriseMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductShopMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductSkuMapper;
import com.lunjar.ebp.bizsupport.model.Cart;
import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.model.ProductShop;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.model.ShopIndex;
import com.lunjar.ebp.bizsupport.query.CartQuery;
import com.lunjar.ebp.bizsupport.query.CollectPlaceQuery;
import com.lunjar.ebp.bizsupport.query.CombinationProductQuery;
import com.lunjar.ebp.bizsupport.query.ProductQuery;
import com.lunjar.ebp.bizsupport.query.ProductShopQuery;
import com.lunjar.ebp.bizsupport.query.ProductSkuQuery;
import com.lunjar.ebp.bizsupport.query.ShopIndexQuery;
import com.lunjar.ebp.bizsupport.service.CartService;
import com.lunjar.ebp.bizsupport.service.CollectPlaceService;
import com.lunjar.ebp.bizsupport.service.CombinationProductService;
import com.lunjar.ebp.bizsupport.service.DiscountService;
import com.lunjar.ebp.bizsupport.service.ProductPropImgsService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.ebp.bizsupport.service.ProductShopService;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;
import com.lunjar.ebp.bizsupport.service.ShopIndexService;
import com.lunjar.ebp.bizsupport.utils.GenerateUtil;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;

/**
 * 商品服务实现类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月10日下午3:27:14
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	private static final String product_cache_key = "product_";
	@Autowired
	private ProductSkuService productSkuService;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductPropImgsService productPropImgsService;
	@Autowired
	ProductCache productCache;
	
	@Value("${store.server.url}")
	private String storeUrl;

	@Autowired
	private ProductShopService productShopService;

	@Autowired
	private ProductShopMapper productShopMapper;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductSkuMapper productSkuMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private CollectPlaceMapper collectPlaceMapper;

	@Autowired
	private DiscountService discountService;

	@Autowired
	private CombinationProductMapper combinationProductMapper;
	@Autowired
	private CollectPlaceService collectPlaceService;

	@Autowired
	private ShopIndexService shopIndexService;
	
	
	@Autowired
	private CombinationProductService combinationProductService;
	
	@Override
	public Long add(Product product) {
		Assert.notNull(product, "object product is null");
		productMapper.insert(product);
		return product.getId();

	}

	@Override
	public void update(Product product) {
		Assert.notNull(product, "object product is null");
		Assert.notNull(product.getId(), "required product id is null");
		productMapper.update(product);
		productCache.remove(product_cache_key + product.getId());
	}

	@Override
	public List<Product> queryList(ProductQuery query) {
		List<Product> list = productMapper.queryList(query);
		if (CollectionUtils.isNotEmpty(list)) {
			for (Product p : list) {
				p.setRelativePath(p.getPathUrl());
				p.setPathUrl(storeUrl + p.getPathUrl());
			}
		}
		return list;
	}

	@Override
	public PageResult<ProductDto> queryListAndSku(ProductQuery query) {
		List<ProductDto> listProductDto = new ArrayList<>();
		List<Product> list = productMapper.queryList(query);
		if (CollectionUtils.isNotEmpty(list)) {
			for (Product p : list) {
				p.setPathUrl(storeUrl + p.getPathUrl());
				ProductDto pt = new ProductDto();
				pt.setProduct(p);
				ProductSkuQuery productSkuQuery = new ProductSkuQuery();
				productSkuQuery.setProductId(p.getId());
				List<ProductSku> skuS = productSkuMapper.queryList(productSkuQuery);
				pt.setSkus(skuS);
				listProductDto.add(pt);
			}
		}
		return PageResult.create(query, listProductDto, queryCount(query));
	}

	@Override
	public int queryCount(ProductQuery query) {
		return productMapper.queryCount(query);
	}

	@Override
	public Product load(Long id) {
		Assert.notNull(id, "object id is null");
		Product product =productCache.get(product_cache_key + id);
		if (product == null) {
			product =  productMapper.load(id);
			if (product != null) {
				productCache.put(product_cache_key + product.getId(), product);
			}
		}
		return product;
	}

	@Override
	public Long add(ProductDto productDto) throws ServiceException {
		Assert.notNull(productDto, "productDto is null");
		Assert.notNull(productDto.getProduct().getEnterpriseId(), BizSupportCode._2001002.getMsg());
		Assert.notNull(productDto.getProduct().getPrice(), BizSupportCode._2001003.getMsg());
		Assert.notNull(productDto.getProduct().getSaleNum(), BizSupportCode._2001004.getMsg());
		Assert.notNull(productDto.getProduct().getCid(), BizSupportCode._2001005.getMsg());
		Assert.notNull(productDto.getProduct().getCatName(), BizSupportCode._2001006.getMsg());
		Assert.notNull(productDto.getProduct().getStatus(), BizSupportCode._2001007.getMsg());
		Assert.notNull(productDto.getProduct().getProductNum(), BizSupportCode._2001008.getMsg());
		Assert.notNull(productDto.getProduct().getSalePrice(), BizSupportCode._2001012.getMsg());
		Assert.notNull(productDto.getProduct().getPathUrl(), BizSupportCode._2001013.getMsg());
		Assert.notNull(productDto.getProduct().getType(), BizSupportCode._2001025.getMsg());
		add(productDto.getProduct());
		// 产品sku
		// TODO

		// // 产品属性图片
		// if (CollectionUtils.isNotEmpty(productDto.getList())) {
		// for (ProductPropImgs ppi : productDto.getList()) {
		// Assert.notNull(ppi.getStatus(), BizSupportCode._2001009.getMsg());
		// Assert.notNull(ppi.getSortNum(), BizSupportCode._2001010.getMsg());
		// Assert.notNull(ppi.getType(), BizSupportCode._2001011.getMsg());
		// Assert.notNull(ppi.getPicPath(), BizSupportCode._2001021.getMsg());
		// ppi.setProductId(productDto.getProduct().getId());
		// // TODO 插入图片属性表
		// productPropImgsService.add(ppi);
		// }
		// }
		return productDto.getProduct().getId();
	}

	@Override
	public List<Product> getGroomProduct(Long shopId) {
		/**
		 * 查逻辑 首先通过店铺id查询到商品id 再通过商品id和类型是推荐的商品查询
		 */
		ProductShopQuery productShopQuery = new ProductShopQuery();
		productShopQuery.setShopId(shopId);
		// 正常的
		productShopQuery.setStatus(1);
		// 普通商品
		productShopQuery.setType(1);
		List<ProductShop> listProductShop = productShopMapper.queryList(productShopQuery);
		if (listProductShop != null && listProductShop.size() > 0) {
			List<Long> list = new ArrayList<>();
			for (ProductShop ps : listProductShop) {
				list.add(ps.getProductId());
			}
			Long[] arrLong = new Long[list.size()];

			ProductQuery productQuery = new ProductQuery();
			productQuery.setIdArray(list.toArray(arrLong));
			// 推荐商品
			productQuery.setType(2);
			// 查询六条记录
			productQuery.setPageSize(6);
			List<Product> listProduct = productMapper.queryList(productQuery);
			return listProduct;
		}
		return null;
	}

	@Override
	public List<EnterpriseProModel> getCartList(String ids, Long buyerId, String city) throws ServiceException {

		if (ids != null && !"".equals(ids)) {
			List<Long> listIds = new ArrayList<>();
			if (ids.contains(",")) {
				String[] strs = ids.split(",");
				for (String s : strs) {
					listIds.add(new Long(s));
				}
			} else {
				listIds.add(new Long(ids));
			}
			Long[] arrLong = new Long[listIds.size()];
			// 商品信息和商铺信息
			CartQuery cartQuery = new CartQuery();
			cartQuery.setIdArray(listIds.toArray(arrLong));
			cartQuery.setBuyerId(buyerId);
			List<CartDto> listCart = cartService.queryList(cartQuery);
			return getCartList(listCart, city);
		}
		return null;

	}

	public List<EnterpriseProModel> getCartList(List<CartDto> listCarto, String city) throws ServiceException {

		List<EnterpriseProModel> listEnterpriseProModel = new ArrayList<>();

		Map<Long, List<CartDto>> listMap = new HashMap<>();

		if (listCarto != null && listCarto.size() > 0) {
			for (CartDto c : listCarto) {

				if (c.getSkuId() != null) {
					// sku可能为空
					ProductSku p = productSkuMapper.load(c.getSkuId());
					c.setProductSku(p);
				}
				Product p = productMapper.load(c.getProductId());
				if (p != null) {
					Enterprise es = enterpriseMapper.load(p.getEnterpriseId());
					if (es != null) {
						// 商家名称
						c.setEnterpiseName(es.getEnterpiseName());
						c.setEnterpiseId(es.getId());
					}
				}

				List<CartDto> listCartDtoTmp = new ArrayList<>();

				if (listMap.get(c.getEnterpiseId()) == null) {
					listCartDtoTmp.add(c);
					listMap.put(c.getEnterpiseId(), listCartDtoTmp);
				} else {
					listCartDtoTmp = listMap.get(c.getEnterpiseId());
					listCartDtoTmp.add(c);
					listMap.put(c.getEnterpiseId(), listCartDtoTmp);
				}
			}
		}
		// Map的结构：enterpiseId:listCart
		if (!listMap.isEmpty()) {
			for (Long key : listMap.keySet()) {
				EnterpriseProModel enterpriseProModel = new EnterpriseProModel();
				List<CartDto> listS = listMap.get(key);

				enterpriseProModel.setListCartDto(listS);
				enterpriseProModel.setEnterpriseName(listS.get(0).getEnterpiseName());
				enterpriseProModel.setEnterpriseId(listS.get(0).getEnterpiseId());
				listEnterpriseProModel.add(enterpriseProModel);
			}
		}
		// 计算费用和数量
		if (listEnterpriseProModel != null && listEnterpriseProModel.size() > 0) {
			for (EnterpriseProModel e : listEnterpriseProModel) {
				// 该商户下的商品总量
				int num = 0;
				List<CartDto> listCartDto = e.getListCartDto();
				List<Cart> listCart = new ArrayList<>();
				if (listCartDto != null && listCartDto.size() > 0) {
					for (CartDto c : listCartDto) {
						Cart cart = new Cart();
						cart.setBuyerId(c.getBuyerId());
						cart.setId(c.getId());
						cart.setNum(c.getNum());
						cart.setProductId(c.getProductId());
						cart.setSkuId(c.getSkuId());
						cart.setShopId(c.getShopId());
						listCart.add(cart);
						num = c.getNum() + num;
					}
				}
				// 查询商户信息
				Enterprise es = enterpriseMapper.load(e.getEnterpriseId());
				// 计算金额和运费
				DiscountDto dto = new DiscountDto();
				dto.setEnterpriseId(e.getEnterpriseId());
				dto.setList(listCart);
				dto.setDistributionMode(es.getDistributionMode());
				dto.setCity(city);
				discountService.doDiscount(dto);
				// 设置快递方式
				e.setDistributionMode(es.getDistributionMode());
				e.setDiscountDto(dto);

				// 查询自提点 1：邮递 2：自提
				if (2 == es.getDistributionMode()) {
					// 表示自提，查询自提点
					CollectPlaceQuery collectPlaceQuery = new CollectPlaceQuery();
					collectPlaceQuery.setEnterpriseId(es.getId());
					e.setCollectPlaces(getCollectPlaceDtos(collectPlaceMapper.queryList(collectPlaceQuery)));
				}
				// 计算每个商户下面的商品总量
				e.setNum(num);
			}
		}

		return listEnterpriseProModel;
	}

	List<CollectPlaceDto> getCollectPlaceDtos(List<CollectPlace> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<CollectPlaceDto> dtos = new ArrayList<>();
		CollectPlaceDto cpDto = null;
		CollectTimeDto ctDto = null;
		for (CollectPlace cp : list) {
			cpDto = new CollectPlaceDto();
			cpDto.setClpAddress(cp.getClpAddress());
			cpDto.setClpName(cp.getClpName());
			cpDto.setClpStatus(cp.getClpStatus());
			cpDto.setClpTelephone(cp.getClpTelephone());
			cpDto.setCollectTime(cp.getCollectTime());
			cpDto.setEndTime(cp.getEndTime());
			cpDto.setEnterpriseId(cp.getEnterpriseId());
			cpDto.setFee(cp.getFee());
			cpDto.setGmtCreate(cp.getGmtCreate());
			cpDto.setGmtModify(cp.getGmtModify());
			cpDto.setId(cp.getId());
			cpDto.setMaxDepositDays(cp.getMaxDepositDays());
			cpDto.setStartTime(cp.getStartTime());
			ctDto = collectPlaceService.getCollectTime(cp.getId());
			if (ctDto != null) {
				cpDto.setCollectStartTime(ctDto.getStartTime());
				cpDto.setCollectEndTime(ctDto.getEndTime());
			}
			dtos.add(cpDto);
		}
		return dtos;
	}

	/**
	 * 
	 * @param id
	 *            组合id
	 * @param list
	 *            组合商品
	 * @return
	 */
	public EnterpriseProModel getCombin(Long id, List<CombinDto> list, Long shopId, Long buyerId, String city) {

		CombinationProduct c = combinationProductMapper.load(id);

		EnterpriseProModel enterpriseProModel = new EnterpriseProModel();

		List<CartDto> cartDtos = new ArrayList<>();

		// 封装成cartDto对象
		for (CombinDto cd : list) {
			CartDto cartDto = new CartDto();
			Long productId = new Long(cd.getProductId());
			Product p = productMapper.load(productId);

			// 如果已经赋值就不需要查询，因为这里的商品都属于同一个商户
			if (enterpriseProModel.getEnterpriseId() == null) {
				// 查询商户信息
				Enterprise enterprise = enterpriseMapper.load(p.getEnterpriseId());
				if (enterprise != null) {
					enterpriseProModel.setEnterpriseName(enterprise.getEnterpiseName());
					enterpriseProModel.setEnterpriseId(enterprise.getId());
					enterpriseProModel.setDistributionMode(enterprise.getDistributionMode());
					// 查询自提信息
					if (enterprise.getDistributionMode().equals(new Integer(2))) {
						CollectPlaceQuery collectPlaceQuery = new CollectPlaceQuery();
						collectPlaceQuery.setEnterpriseId(enterprise.getId());
						enterpriseProModel
								.setCollectPlaces(getCollectPlaceDtos(collectPlaceMapper.queryList(collectPlaceQuery)));
					}
				}
			}

			if (cd.getSkuId() != null) {
				Long skuId = new Long(cd.getSkuId());
				ProductSku ps = productSkuMapper.load(skuId);
				cartDto.setSkuId(skuId);
				cartDto.setProperties(ps.getProperties());
				cartDto.setPropertiesname(ps.getPropertiesname());
			}

			cartDto.setProductId(productId);
			cartDto.setNum(cd.getNum());
			cartDto.setProductName(p.getName());
			cartDto.setPicPath(storeUrl + p.getPathUrl());

			if (c.getProduct1Id() != null && c.getProduct1Id().equals(productId)) {
				cartDto.setProductRealPrice(c.getProduct1RealPrice());
			}

			if (c.getProduct2Id() != null && c.getProduct2Id().equals(productId)) {
				cartDto.setProductRealPrice(c.getProduct2RealPrice());
			}

			if (c.getProduct3Id() != null && c.getProduct3Id().equals(productId)) {
				cartDto.setProductRealPrice(c.getProduct3RealPrice());
			}

			if (c.getProduct4Id() != null && c.getProduct4Id().equals(productId)) {
				cartDto.setProductRealPrice(c.getProduct4RealPrice());
			}

			if (c.getProduct5Id() != null && c.getProduct5Id().equals(productId)) {
				cartDto.setProductRealPrice(c.getProduct5RealPrice());
			}
			cartDtos.add(cartDto);
		}

		enterpriseProModel.setListCartDto(cartDtos);

		return enterpriseProModel;
	}

	@Override
	public ProductDto getProductInfo(Long id) {
		ProductDto productDto = null;
		Assert.notNull(id, "object id is null");
		Product product = productMapper.load(id);
		if (product != null) {
			productDto = new ProductDto();
			productDto.setProduct(product);
			List<ShopIndexParams> list = GenerateUtil.createShopIndexList(product.getProductDesc(), storeUrl);
			productDto.setList(list);
		}
		return productDto;
	}

	/**
	 * 下架
	 */
	private void updateShelvesOff(Long[] ids) {
		// type = 5 和3
		Integer[] array = { 3, 5 };
		ShopIndexQuery shopIndexQuery = new ShopIndexQuery();
		shopIndexQuery.setStatus(1);
		shopIndexQuery.setTypeArray(array);
		List<ShopIndex> list = shopIndexService.queryList(shopIndexQuery);

		for (Long id : ids) {

			Product p = new Product();
			p.setId(id);
			// 先将商品状态改为2
			p.setStatus(2);
			// 更新商品状态
			update(p);

			// 更新表product_shop
			ProductShop ps = new ProductShop();
			ps.setProductId(id);
			ps.setStatus(2);
			productShopService.updateByCondition(ps);
			// 更新表shop_index
		}

		if (list != null && list.size() > 0) {
			for (ShopIndex index : list) {

				String picUrls = index.getPicUrls();
				if (picUrls != null && !"".equals(picUrls)) {
					// productId,sort;productId,sort;这样排列的

					if (picUrls.contains(";")) {
						String[] picUrlArray = picUrls.split(";");
						List<ShopIndexPicUrlDto> listSIP = ShopIndexPicUrlDto.getShopIndexPicUrlDto(picUrlArray);
						updateShopIndex(listSIP, ids, index, picUrls);
					} else {
						// 如果是一个的话是这样的格式 productId,sort
						String[] picUrlArray = { picUrls };
						List<ShopIndexPicUrlDto> listSIP = ShopIndexPicUrlDto.getShopIndexPicUrlDto(picUrlArray);
						updateShopIndex(listSIP, ids, index, picUrls);
					}
				}
			}
		}
	}

	private void updateShopIndex(List<ShopIndexPicUrlDto> listSIP, Long[] ids, ShopIndex index, String picUrls) {
		if (listSIP != null && listSIP.size() > 0) {
			List<ShopIndexPicUrlDto> listSIPTmp = new ArrayList<>();
			for (ShopIndexPicUrlDto sip : listSIP) {
				for (Long id : ids) {
					// 如果商品id相同且没有标记为可以移除的话
					if ((sip.getProductId().equals(id.toString())) && !sip.getIsRemove()) {
						sip.setIsRemove(true);
					}
				}
			}

			for (ShopIndexPicUrlDto dto : listSIP) {
				if (!dto.getIsRemove()) {
					listSIPTmp.add(dto);
				}

			}

			if (listSIPTmp.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < listSIPTmp.size(); i++) {
					String productId = listSIPTmp.get(i).getProductId();
					String sort = listSIPTmp.get(i).getSort();
					if ((i + 1) == listSIPTmp.size()) {
						// 最后一个
						sb.append(productId + "," + sort);
					} else {
						sb.append(productId + "," + sort + ";");
					}
				}
				logger.debug("排序的id{}", index.getId());
				logger.debug("排序之前的picUrls:{}", picUrls);
				logger.debug("排序之后的picUrls:{}", sb.toString());
				if (!picUrls.equals(sb.toString())) {
					logger.debug("排序前和排序后不想等");
					ShopIndex si = new ShopIndex();
					si.setId(index.getId());
					si.setPicUrls(sb.toString());
					shopIndexService.update(si);
				}
			} else {
				logger.debug("排序的id{}", index.getId());
				logger.debug("排序之前的picUrls:{}", picUrls);
				logger.debug("排序之后的picUrls:null");
				shopIndexService.delete(index.getId());
			}
		}
	}
	
	/**
	 * 下架更新组合商品
	 * @param ids 商品id集合
	 */
	private void updateCombinProdOff(Long[] ids){
		
		for(Long id : ids){
			CombinationProductQuery c = new CombinationProductQuery();
			c.setCpStatus(1);
			c.setProduct1Id(id);
			List<CombinationProduct> list = combinationProductService.queryByProductId(c);
			if(list!=null && list.size()>0){
				for(CombinationProduct tmpC:list){
					CombinationProduct updateC = new CombinationProduct();
					updateC.setId(tmpC.getId());
					updateC.setCpStatus(2);
					combinationProductService.update(updateC);
				}
			}
		}
	}
	
	/**
	 * 上架更新组合商品
	 * @param ids 商品id集合
	 */
	private void updateCombinProdOn(Long[] ids){
		
		for(Long id : ids){
			CombinationProductQuery c = new CombinationProductQuery();
			c.setCpStatus(2);
			c.setProduct1Id(id);
			List<CombinationProduct> list = combinationProductService.queryByProductId(c);
			if(list!=null && list.size()>0){
				for(CombinationProduct tmpC:list){
						boolean flag = false;
					if (tmpC != null && tmpC.getProduct1Id() != null) {
						Product p = this.load(tmpC.getProduct1Id());
						if(p!=null && p.getStatus()==1){
							flag =true;
						}else{
							flag =false;
						}
					}
					if (tmpC != null && tmpC.getProduct2Id() != null) {
						Product p = this.load(tmpC.getProduct1Id());
						if(p!=null && p.getStatus()==1){
							flag =true;
						}else{
							flag =false;
						}
					}

					if (tmpC != null && tmpC.getProduct3Id() != null) {
						Product p = this.load(tmpC.getProduct1Id());
						if(p!=null && p.getStatus()==1){
							flag =true;
						}else{
							flag =false;
						}
					}

					if (tmpC != null && tmpC.getProduct4Id() != null) {
						Product p = this.load(tmpC.getProduct1Id());
						if(p!=null && p.getStatus()==1){
							flag =true;
						}else{
							flag =false;
						}
					}

					if (tmpC != null && tmpC.getProduct5Id() != null) {
						Product p = this.load(tmpC.getProduct1Id());
						if(p!=null && p.getStatus()==1){
							flag =true;
						}else{
							flag =false;
						}
					}
					
					if(flag){
						CombinationProduct updateC = new CombinationProduct();
						updateC.setId(tmpC.getId());
						updateC.setCpStatus(1);
						combinationProductService.update(updateC);
					}
				}
			}
		}
	}
	
	
	@Override
	public void updateProductShelves(Long[] ids, String type) {
		// type1:表示是上架还是下架 1是上架 2 是下架
		if ("2".equals(type)) {// 下架
			updateShelvesOff(ids);
			//更新组合商品为失效
			updateCombinProdOff(ids);
		} else {
			// 表示上架
			for (Long id : ids) {
				Product p = new Product();
				p.setId(id);
				p.setStatus(1);
				this.update(p);
			}
			updateCombinProdOn(ids);
			
		}
	}

	@Override
	public void deleteProduct(Long[] ids) {
		// 删除商品和商品相关的sku
		for (Long id : ids) {
			delete(id);
			ProductSku p = new ProductSku();
			p.setProductId(id);
			productSkuService.deleteByCondition(p);
		}
	}

	@Override
	public void delete(Long id) {
		productMapper.delete(id);
	}

	/**
	 * 更新商品信息和sku信息
	 * 
	 * @param productId
	 *            商品id
	 * @param skuIds
	 *            skuid数组
	 * @param productName
	 *            商品名称
	 * @param prices
	 *            价格
	 */
	public void updateProductAndSku(Long productId, Long[] skuIds, String productName, String[] prices) {

		if (skuIds != null && skuIds.length > 0) {
			for (int i = 0; i < skuIds.length; i++) {
				ProductSku ps = new ProductSku();
				ps.setId(skuIds[i]);
				ps.setPrice(new BigDecimal(prices[i]));
				productSkuService.update(ps);
			}
		}

		if (productId != null) {
			Product p = new Product();
			p.setId(productId);

			if (productName != null && !"".equals(productName.trim())) {
				p.setName(productName);
			}

			if (prices != null && prices.length == 1) {
				p.setSalePrice(new BigDecimal(prices[0]));
			}

			update(p);
		}
	}

	@Override
	public void saveProduct(Product p, List<ProductSku> listSku, List<ProductPropImgs> listImages)
			throws ServiceException {
		Long pid = null;
		if (p.getId() != null) {
			// 表示更新
			this.update(p);
			pid = p.getId();
			ProductSku ps = new ProductSku();
			ps.setProductId(pid);
			productSkuService.deleteByCondition(ps);
			
			
			ProductPropImgs ppi = new ProductPropImgs();
			ppi.setProductId(pid);
			productPropImgsService.deleteByCondition(ppi);
		} else {
			pid = this.add(p);
		}
		if (listSku != null && listSku.size() > 0) {
			for (ProductSku ps : listSku) {
				ps.setProductId(pid);
				ps.setGmtCreate(new Date());
				ps.setGmtModify(new Date());
				ps.setStatus(1);
				productSkuService.add(ps);
			}
		}
		if (listImages != null && listImages.size() > 0) {
			for (ProductPropImgs ppi : listImages) {
				ppi.setProductId(pid);
				productPropImgsService.add(ppi);
			}
		}
	}
}
