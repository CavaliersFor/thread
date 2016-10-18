package com.lunjar.ebp.bizsupport.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.dto.DiscountDto;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.enums.EnumDiscountStatus;
import com.lunjar.ebp.bizsupport.mappers.CollectPlaceMapper;
import com.lunjar.ebp.bizsupport.mappers.DiscountMapper;
import com.lunjar.ebp.bizsupport.mappers.ExpressMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductSkuMapper;
import com.lunjar.ebp.bizsupport.model.Cart;
import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.model.Discount;
import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.DiscountQuery;
import com.lunjar.ebp.bizsupport.service.DiscountService;
import com.lunjar.ebp.bizsupport.utils.GenerateUtil;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;

@Service(value = "discountService")
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountMapper discountMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ExpressMapper expressMapper;
	@Autowired
	private CollectPlaceMapper collectPlaceMapper;
	@Autowired
	private ProductSkuMapper productSkuMapper;

	private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DiscountServiceImpl.class);

	@Override
	public Discount load(Long id) {
		return discountMapper.load(id);
	}

	@Override
	public Long add(Discount discount) {
		Assert.notNull(discount, "object discount is null");
		discountMapper.insert(discount);
		return discount.getId();
	}

	@Override
	public int update(Discount discount) {
		Assert.notNull(discount, "object discount is null");
		Assert.notNull(discount.getId(), "required discount id is null");
		return discountMapper.update(discount);
	}

	@Override
	public void delete(Long id) {
		discountMapper.delete(id);
	}

	@Override
	public List<Discount> queryList(DiscountQuery discountQuery) {
		return discountMapper.queryList(discountQuery);
	}

	@Override
	public int queryCount(DiscountQuery discountQuery) {
		return discountMapper.queryCount(discountQuery);
	}

	@Override
	public int deleteByIdAndEnterpriseId(Discount discount) {
		Assert.notNull(discount, "object discount is null");
		Assert.notNull(discount.getId(), "object discount id is null");
		Assert.notNull(discount.getEnterpriseId(), "object discount enterpriseId is null");
		return discountMapper.deleteByIdAndEnterpriseId(discount);
	}

	@Override
	public void doDiscount(DiscountDto dto) throws ServiceException {
		// TODO Auto-generated method stub
		// 先算商品总共需要支付多少钱
		Product product = null;
		ProductSku productSku = null;
		BigDecimal totalPrice = new BigDecimal("0");
		int num = 0;
		BigDecimal volume = null;
		BigDecimal weight = null;
		for (Cart o : dto.getList()) {
			num = num + o.getNum();
			if (o.getSkuId() == null) {
				product = productMapper.load(o.getProductId());
				if (product == null) {
					logger.warn(BizSupportCode._2001017.getMsg());
					throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
				}
				if (product.getWeight() != null) {
					weight = product.getWeight();
				}
				if (product.getVolume() != null) {
					volume = product.getVolume();
				}
				totalPrice = totalPrice.add(product.getSalePrice().multiply(new BigDecimal(o.getNum().toString())));
			} else {
				productSku = productSkuMapper.load(o.getSkuId());
				if (productSku == null) {
					logger.warn(BizSupportCode._3000017.getMsg());
					throw new ServiceException(BizSupportCode._3000017.getCode(), BizSupportCode._3000017.getMsg());
				}
				if (productSku.getWeight() != null) {
					weight = productSku.getWeight();
				}
				if (productSku.getVolume() != null) {
					volume = productSku.getVolume();
				}
				totalPrice = totalPrice.add(productSku.getPrice().multiply(new BigDecimal(o.getNum().toString())));
			}
		}
		// 再算商家优惠条件，
		DiscountQuery query = new DiscountQuery();
		query.setEnterpriseId(dto.getEnterpriseId());
		query.setStatus(EnumDiscountStatus.IN_USE.getValue());
		query.setSort(" conditions desc");
		List<Discount> discounts = discountMapper.queryList(query);
		BigDecimal discountPrice = new BigDecimal("0");
		boolean freePost = false;
		if (CollectionUtils.isNotEmpty(discounts)) {
			int i = 0;
			String discountDesc = "";
			for (Discount d : discounts) {
				i = totalPrice.compareTo(d.getConditions());
				if (i > -1) {
					discountPrice = d.getDiscountFee();
					discountDesc = "满" + d.getConditions() + "减" + d.getDiscountFee();
					if (d.getIsPost() == 1) {
						discountDesc += ", 免邮费";
						freePost = true;
					}
					dto.setDiscountDesc(discountDesc);
					break;
				}
			}
		}
		totalPrice = totalPrice.subtract(discountPrice);
		//邮费
		BigDecimal postFee = new BigDecimal("0");
		if (dto.getDistributionMode() == 1) {// 邮递
			if (!freePost) {
				Express express = expressMapper.getByEntId(dto.getEnterpriseId());
				if (express == null) {
					logger.warn(BizSupportCode._2001017.getMsg());
					throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
				}
				String price = express.getEcPrice();
				if (StringUtils.isNoneBlank(price)) {
					Double v = null;
					if (express.getValuation() == 2) {//按重量计费
						v = weight.doubleValue();
					}else if (express.getValuation() == 3) {//按体积计费
						v = volume.doubleValue();
					}
					postFee = GenerateUtil.getPostFee(price, dto.getCity(), num, v);
				}
			}
		} else {
			CollectPlace collectPlace = collectPlaceMapper.load(dto.getAddressId());
			if (collectPlace != null) {
				postFee = collectPlace.getFee();
			}
		}
		totalPrice = totalPrice.add(postFee);
		dto.setExpressPrice(postFee);// 邮递费用
		dto.setDiscountPrice(discountPrice);// 优惠价格
		dto.setRealPrice(totalPrice);// 实际支付
	}

	@Override
	public PageResult<Discount> queryListByPage(DiscountQuery query) {
		List<Discount> data= this.queryList(query);
		int count = this.queryCount(query);
		return PageResult.create(query, data, count);
	}

	@Override
	public void addDiscount(List<Discount> list) {
		for(Discount d : list){
			this.add(d);
		}
	}

}
