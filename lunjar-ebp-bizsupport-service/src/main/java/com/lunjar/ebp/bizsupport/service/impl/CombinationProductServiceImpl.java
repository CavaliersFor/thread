package com.lunjar.ebp.bizsupport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.dto.DiscountDto;
import com.lunjar.ebp.bizsupport.dto.ProductShopDto;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.mappers.CollectPlaceMapper;
import com.lunjar.ebp.bizsupport.mappers.CombinationProductMapper;
import com.lunjar.ebp.bizsupport.mappers.EnterpriseMapper;
import com.lunjar.ebp.bizsupport.mappers.ExpressMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductMapper;
import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.query.CombinationProductQuery;
import com.lunjar.ebp.bizsupport.query.ProductShopQuery;
import com.lunjar.ebp.bizsupport.service.CombinationProductService;
import com.lunjar.ebp.bizsupport.service.ProductShopService;
import com.lunjar.ebp.bizsupport.utils.GenerateUtil;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;

/**
 * 组合商品服务实现类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月17日下午2:50:18
 */
@Service("combinationProductService")
public class CombinationProductServiceImpl implements CombinationProductService {
	private static final Logger log = LoggerFactory.getLogger(CombinationProductServiceImpl.class);

	@Autowired
	CombinationProductMapper combinationProductMapper;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	private ProductShopService productShopService;
	@Autowired
	ExpressMapper expressMapper;
	@Autowired
	CollectPlaceMapper collectPlaceMapper;
	@Autowired
	EnterpriseMapper enterpriseMapper;

	@Value("${store.server.url}")
	private String storeUrl;

	@Override
	public CombinationProduct load(Long id) {
		// TODO Auto-generated method stub
		Assert.notNull(id, "id is null");
		return combinationProductMapper.load(id);
	}

	@Override
	public Long add(CombinationProduct t) throws ServiceException {
		// TODO Auto-generated method stub
		validateParams(t);
		Product product1 = productMapper.load(t.getProduct1Id());
		if (product1 == null) {
			log.warn(BizSupportCode._2001017.getMsg());
			throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
		}
		t.setProduct1Name(product1.getName());
		t.setProduct1PicPath(product1.getPathUrl());
		t.setProduct1Price(product1.getPrice());
		Product product2 = productMapper.load(t.getProduct2Id());
		if (product2 == null) {
			log.warn(BizSupportCode._2001017.getMsg());
			throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
		}
		t.setProduct2Name(product2.getName());
		t.setProduct2PicPath(product2.getPathUrl());
		t.setProduct2Price(product2.getPrice());

		if (t.getProduct3Id() != null) {
			if (t.getProduct3RealPrice() == null) {
				log.warn(BizSupportCode._2002005.getMsg());
				throw new ServiceException(BizSupportCode._2002005.getCode(), BizSupportCode._2002005.getMsg());
			}
			Product product3 = productMapper.load(t.getProduct3Id());
			if (product3 == null) {
				log.warn(BizSupportCode._2001017.getMsg());
				throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
			}
			t.setProduct3Name(product3.getName());
			t.setProduct3PicPath(product3.getPathUrl());
			t.setProduct3Price(product3.getPrice());
		}

		if (t.getProduct4Id() != null) {
			if (t.getProduct4RealPrice() == null) {
				log.warn(BizSupportCode._2002006.getMsg());
				throw new ServiceException(BizSupportCode._2002006.getCode(), BizSupportCode._2002006.getMsg());
			}
			Product product4 = productMapper.load(t.getProduct4Id());
			if (product4 == null) {
				log.warn(BizSupportCode._2001017.getMsg());
				throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
			}
			t.setProduct4Name(product4.getName());
			t.setProduct4PicPath(product4.getPathUrl());
			t.setProduct4Price(product4.getPrice());
		}

		if (t.getProduct5Id() != null) {
			if (t.getProduct5RealPrice() == null) {
				log.warn(BizSupportCode._2002007.getMsg());
				throw new ServiceException(BizSupportCode._2002007.getCode(), BizSupportCode._2002007.getMsg());
			}
			Product product5 = productMapper.load(t.getProduct5Id());
			if (product5 == null) {
				log.warn(BizSupportCode._2001017.getMsg());
				throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
			}
			t.setProduct5Name(product5.getName());
			t.setProduct5PicPath(product5.getPathUrl());
			t.setProduct5Price(product5.getPrice());
		}
		combinationProductMapper.insert(t);
		return t.getId();
	}

	/**
	 * 校验参数方法
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月17日下午3:40:12
	 * @param t
	 * @throws ServiceException
	 */
	private void validateParams(CombinationProduct t) throws ServiceException {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(t.getCpName())) {
			log.warn(BizSupportCode._2002000.getMsg());
			throw new ServiceException(BizSupportCode._2002000.getCode(), BizSupportCode._2002000.getMsg());
		}
		if (StringUtils.isBlank(t.getCpPicPath())) {
			log.warn(BizSupportCode._2002001.getMsg());
			throw new ServiceException(BizSupportCode._2002001.getCode(), BizSupportCode._2002001.getMsg());
		}
		if (t.getCpPrice() == null) {
			log.warn(BizSupportCode._2002002.getMsg());
			throw new ServiceException(BizSupportCode._2002002.getCode(), BizSupportCode._2002002.getMsg());
		}
		if (t.getEnterpriseId() == null) {
			log.warn(BizSupportCode._2001002.getMsg());
			throw new ServiceException(BizSupportCode._2001002.getCode(), BizSupportCode._2001002.getMsg());
		}
		if (t.getProduct1RealPrice() == null) {
			log.warn(BizSupportCode._2002003.getMsg());
			throw new ServiceException(BizSupportCode._2002003.getCode(), BizSupportCode._2002003.getMsg());
		}
		if (t.getProduct2RealPrice() == null) {
			log.warn(BizSupportCode._2002004.getMsg());
			throw new ServiceException(BizSupportCode._2002004.getCode(), BizSupportCode._2002004.getMsg());
		}
	}

	@Override
	public int update(CombinationProduct t) {
		// TODO Auto-generated method stub
		return combinationProductMapper.update(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		combinationProductMapper.delete(id);
	}

	@Override
	public List<CombinationProduct> queryList(CombinationProductQuery q) {
		List<CombinationProduct> data = combinationProductMapper.queryList(q);

		if (data != null && data.size() > 0) {
			for (CombinationProduct c : data) {
				if (c.getProduct1PicPath() != null && !"".equals(c.getProduct1PicPath())) {
					c.setProduct1AbsPicPath(storeUrl + c.getProduct1PicPath());
				}
				if (c.getProduct2PicPath() != null && !"".equals(c.getProduct2PicPath())) {
					c.setProduct2AbsPicPath(storeUrl + c.getProduct2PicPath());
				}
				if (c.getProduct3PicPath() != null && !"".equals(c.getProduct3PicPath())) {
					c.setProduct3AbsPicPath(storeUrl + c.getProduct3PicPath());
				}
				if (c.getProduct4PicPath() != null && !"".equals(c.getProduct4PicPath())) {
					c.setProduct4AbsPicPath(storeUrl + c.getProduct4PicPath());
				}
				if (c.getProduct5PicPath() != null && !"".equals(c.getProduct5PicPath())) {
					c.setProduct1AbsPicPath(storeUrl + c.getProduct5PicPath());
				}
				if (c.getCpPicPath() != null && !"".equals(c.getCpPicPath())) {
					c.setCpAbsPicPath(storeUrl + c.getCpPicPath());
				}
			}
		}
		return data;
	}

	@Override
	public int queryCount(CombinationProductQuery q) {
		// TODO Auto-generated method stub
		return combinationProductMapper.queryCount(q);
	}

	@Override
	public List<CombinationProduct> queryByProductId(CombinationProductQuery combinationProductQuery, Long shopId)
			throws ServiceException {

		List<CombinationProduct> listCombinationProduct = combinationProductMapper
				.queryByProductId(combinationProductQuery);

		// 查询该商品下有多少商品,如果组合商品的某个商品的id不包含在商铺的下的id中，则该组合商品不显示
		ProductShopQuery productShopQuery = new ProductShopQuery();
		productShopQuery.setShopId(shopId);
		productShopQuery.setStatus(1);

		List<ProductShopDto> productShopDtos = productShopService.queryList(productShopQuery);

		List<CombinationProduct> listCP = new ArrayList<>();
		if (productShopDtos != null && productShopDtos.size() > 0 && listCombinationProduct != null
				&& listCombinationProduct.size() > 0) {
			for (CombinationProduct cp : listCombinationProduct) {
				boolean p1 = false;
				boolean p2 = false;
				boolean p3 = false;
				boolean p4 = false;
				boolean p5 = false;
				for (ProductShopDto psd : productShopDtos) {
					if (cp.getProduct1Id() == null) {
						// 可能为空
						p1 = true;
					} else if (cp.getProduct1Id() != null && cp.getProduct1Id().equals(psd.getProductId())) {
						p1 = true;
					}

					if (cp.getProduct2Id() == null) {
						// 可能为空
						p2 = true;
					} else if (cp.getProduct2Id() != null && cp.getProduct2Id().equals(psd.getProductId())) {
						p2 = true;
					}

					if (cp.getProduct3Id() == null) {
						// 可能为空
						p3 = true;
					} else if (cp.getProduct3Id() != null && cp.getProduct3Id().equals(psd.getProductId())) {
						p3 = true;
					}

					if (cp.getProduct4Id() == null) {
						// 可能为空
						p4 = true;
					} else if (cp.getProduct4Id() != null && cp.getProduct4Id().equals(psd.getProductId())) {
						p4 = true;
					}

					if (cp.getProduct5Id() == null) {
						// 可能为空
						p5 = true;
					} else if (cp.getProduct5Id() != null && cp.getProduct5Id().equals(psd.getProductId())) {
						p5 = true;
					}
				}

				// 如果都为true则显示
				if (p1 && p2 && p3 && p4 && p5) {
					listCP.add(cp);
				}
			}
		}
		return listCP;
	}

	@Override
	public DiscountDto doCombinationDiscount(Long combinationId, DiscountDto dto) throws ServiceException {
		CombinationProduct combinationProduct = combinationProductMapper.load(combinationId);
		if (combinationProduct == null) {
			log.warn(BizSupportCode._3000029.getMsg());
			throw new ServiceException(BizSupportCode._3000029.getCode(), BizSupportCode._3000029.getMsg());
		}
		BigDecimal postFee = new BigDecimal("0");
		BigDecimal totalPrice = new BigDecimal("0");
		BigDecimal volume = null;
		BigDecimal weight = null;
		int num = 0;
		totalPrice = combinationProduct.getCpPrice();// 组合商品售价
		if (combinationProduct.getIsFreePost() == 2) {// 不包邮
			Enterprise enterprise = enterpriseMapper.load(dto.getEnterpriseId());
			if (enterprise.getDistributionMode() == 1) {// 邮递
				Product product1 = productMapper.load(combinationProduct.getProduct1Id());
				if (product1 == null) {
					log.warn(BizSupportCode._2001017.getMsg());
					throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
				}
				num = 1;
				volume = product1.getVolume();
				weight = product1.getWeight();
				Product product2 = productMapper.load(combinationProduct.getProduct2Id());
				if (product2 == null) {
					log.warn(BizSupportCode._2001017.getMsg());
					throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
				}
				num += 1;
				volume = volume.add(product2.getVolume());
				weight = weight.add(product2.getWeight());
				if (combinationProduct.getProduct3Id() != null) {
					Product product3 = productMapper.load(combinationProduct.getProduct3Id());
					if (product3 == null) {
						log.warn(BizSupportCode._2001017.getMsg());
						throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
					}
					num += 1;
					volume = volume.add(product3.getVolume());
					weight = weight.add(product3.getWeight());
				}
				if (combinationProduct.getProduct4Id() != null) {
					Product product4 = productMapper.load(combinationProduct.getProduct4Id());
					if (product4 == null) {
						log.warn(BizSupportCode._2001017.getMsg());
						throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
					}
					num += 1;
					volume = volume.add(product4.getVolume());
					weight = weight.add(product4.getWeight());
				}
				if (combinationProduct.getProduct5Id() != null) {
					Product product5 = productMapper.load(combinationProduct.getProduct5Id());
					if (product5 == null) {
						log.warn(BizSupportCode._2001017.getMsg());
						throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
					}
					num += 1;
					volume = volume.add(product5.getVolume());
					weight = weight.add(product5.getWeight());
				}

				Express express = expressMapper.getByEntId(dto.getEnterpriseId());
				if (express == null) {
					log.warn(BizSupportCode._2002008.getMsg());
					throw new ServiceException(BizSupportCode._2002008.getCode(), BizSupportCode._2002008.getMsg());
				}
				String price = express.getEcPrice();
				if (StringUtils.isNoneBlank(price)) {
					Double v = null;
					if (express.getValuation() == 2) {// 按重量计费
						v = weight.doubleValue();
					} else if (express.getValuation() == 3) {// 按体积计费
						v = volume.doubleValue();
					}
					postFee = GenerateUtil.getPostFee(price, dto.getCity(), num, v);
				}
			} else {
				CollectPlace collectPlace = collectPlaceMapper.load(dto.getAddressId());
				if (collectPlace != null) {
					postFee = collectPlace.getFee();
				}
			}
		}
		totalPrice = totalPrice.add(postFee);
		dto.setExpressPrice(postFee);// 邮递费用
		dto.setRealPrice(totalPrice);// 实际支付
		dto.setDiscountDesc("组合商品优惠");
		return dto;
	}

	@Override
	public PageResult<CombinationProduct> queryListForPage(CombinationProductQuery combinationProductQuery) {
		List<CombinationProduct> data = this.queryList(combinationProductQuery);
		int count = this.queryCount(combinationProductQuery);
		return PageResult.create(combinationProductQuery, data, count);
	}

	@Override
	public void updateNoDecide(CombinationProduct combinationProduct) {
		combinationProductMapper.updateNoDecide(combinationProduct);
	}

	public List<CombinationProduct> queryByProductId(CombinationProductQuery combinationProductQuery) {

		List<CombinationProduct> listCombinationProduct = combinationProductMapper
				.queryByProductId(combinationProductQuery);
		return listCombinationProduct;
	}
}
