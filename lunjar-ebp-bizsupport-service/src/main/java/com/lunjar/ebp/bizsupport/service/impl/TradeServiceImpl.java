package com.lunjar.ebp.bizsupport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.constants.Constants;
import com.lunjar.ebp.bizsupport.dto.CombinDto;
import com.lunjar.ebp.bizsupport.dto.DiscountDto;
import com.lunjar.ebp.bizsupport.dto.TradeDto;
import com.lunjar.ebp.bizsupport.dto.TradeInfoDto;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.enums.EnumLogistics;
import com.lunjar.ebp.bizsupport.enums.EnumOrdersRefundStatus;
import com.lunjar.ebp.bizsupport.enums.EnumProductStatus;
import com.lunjar.ebp.bizsupport.enums.EnumTradeStatus;
import com.lunjar.ebp.bizsupport.mappers.BuyerAddressMapper;
import com.lunjar.ebp.bizsupport.mappers.CartMapper;
import com.lunjar.ebp.bizsupport.mappers.CollectPlaceMapper;
import com.lunjar.ebp.bizsupport.mappers.EnterpriseMapper;
import com.lunjar.ebp.bizsupport.mappers.LogisticsMapper;
import com.lunjar.ebp.bizsupport.mappers.OrdersMapper;
import com.lunjar.ebp.bizsupport.mappers.PartnerMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductSkuMapper;
import com.lunjar.ebp.bizsupport.mappers.RefundMapper;
import com.lunjar.ebp.bizsupport.mappers.SellerMapper;
import com.lunjar.ebp.bizsupport.mappers.ShopMapper;
import com.lunjar.ebp.bizsupport.mappers.TradeMapper;
import com.lunjar.ebp.bizsupport.model.BuyerAddress;
import com.lunjar.ebp.bizsupport.model.Cart;
import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.model.Logistics;
import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.Partner;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.model.Refund;
import com.lunjar.ebp.bizsupport.model.Seller;
import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.model.TradeParams;
import com.lunjar.ebp.bizsupport.model.TradeRefundParams;
import com.lunjar.ebp.bizsupport.query.CartQuery;
import com.lunjar.ebp.bizsupport.query.OrdersQuery;
import com.lunjar.ebp.bizsupport.query.TradeQuery;
import com.lunjar.ebp.bizsupport.service.CombinationProductService;
import com.lunjar.ebp.bizsupport.service.DiscountService;
import com.lunjar.ebp.bizsupport.service.OrdersService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.ebp.bizsupport.utils.GenerateUtil;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;

@Service(value = "tradeService")
public class TradeServiceImpl implements TradeService {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TradeServiceImpl.class);

	@Autowired
	private DataFieldMaxValueIncrementer tradeNoGenarater;
	@Autowired
	private DataFieldMaxValueIncrementer pickUpNoGenarater;
	@Autowired
	private TradeMapper tradeMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private SellerMapper sellerMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private PartnerMapper partnerMapper;
	@Autowired
	private BuyerAddressMapper buyerAddressMapper;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private CollectPlaceMapper collectPlaceMapper;
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductSkuMapper productSkuMapper;
	@Autowired
	private RefundMapper refundMapper;
	@Autowired
	private CombinationProductService combinationProductService;
	@Autowired 
	private ProductService productService;
	@Autowired
	private ProductSkuService productSkuService;
	
	@Value("${store.server.url}")
	private String storeUrl;
	
	@Autowired
	private LogisticsMapper logisticsMapper;
	
	@Override
	public Trade load(Long id) {
		return tradeMapper.load(id);
	}

	@Override
	public Long add(Trade trade) throws ServiceException {
		tradeMapper.insert(trade);
		return trade.getId();
	}

	@Transactional
	@Override
	public Long add(TradeInfoDto tradeInfoDto) throws ServiceException {
		Trade trade = new Trade();
		List<Orders> ordersList = new ArrayList<>();
		validateParams(tradeInfoDto, trade);
		validateCartList(tradeInfoDto, trade, ordersList);
		trade.setStatus(EnumTradeStatus.WAIT_BUYER_PAY.getValue());
		// 处理优惠、
		DiscountDto discountDto = new DiscountDto();
		discountDto.setEnterpriseId(trade.getEnterpriseId());
		discountDto.setAddressId(trade.getBuyerAddressId());
		if (StringUtils.isNotBlank(trade.getCity())) {
			discountDto.setCity(trade.getCity());
		}
		discountDto.setDistributionMode(trade.getDistributionMode());
		List<Cart> cartList = new ArrayList<>();
		Cart cart = null;
//		Product product = null;
//		ProductSku sku = null;
		for (Orders orders : ordersList) {
			cart = new Cart();
			cart.setNum(orders.getNum());
			cart.setProductId(orders.getProductId());
			if (orders.getSkuId() != null) {
				cart.setSkuId(orders.getSkuId());
			}
			cartList.add(cart);
			//减去库存
			destocking(orders);
//			product = productService.load(orders.getProductId());
//			if (product == null) {
//				logger.warn(BizSupportCode._2001017.getMsg());
//				throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
//			}
//			product.setProductNum(product.getProductNum() - orders.getNum());//库存
//			product.setSaleNum(product.getSaleNum() + orders.getNum());//销量
//			productService.update(product);
//			if (orders.getSkuId() != null) {
//				sku = productSkuService.load(orders.getSkuId());
//				if (sku == null) {
//					logger.warn(BizSupportCode._3000017.getMsg());
//					throw new ServiceException(BizSupportCode._3000017.getCode(), BizSupportCode._3000017.getMsg());
//				}
//				sku.setQuantity(sku.getQuantity() - orders.getNum());//sku库存
//				productSkuService.update(sku);
//			}
			
		}
		discountDto.setList(cartList);
		// discountService.doDiscount(trade, ordersList);
		discountService.doDiscount(discountDto);
		trade.setExpressPrice(discountDto.getExpressPrice());
		trade.setRealPrice(discountDto.getRealPrice());
		trade.setDiscountPrice(discountDto.getDiscountPrice());
		trade.setPrice(discountDto.getRealPrice());
		trade.setTradeDesc(discountDto.getDiscountDesc());
		String tradeNo = tradeNoGenarater.nextLongValue() + "" 
		+ GenerateUtil.autoGenericCode(trade.getShopId().toString(), Constants.INTERCEPT_STRING_LENGTH) 
		+ GenerateUtil.autoGenericCode(trade.getBuyerId().toString(), Constants.INTERCEPT_STRING_LENGTH);
		trade.setTradeNo(tradeNo);
		//生成提货码
		if (trade.getDistributionMode() == 2) {
			String pickUpNo = pickUpNoGenarater.nextLongValue() + "" 
					+ GenerateUtil.autoGenericCode(trade.getBuyerId().toString(), Constants.INTERCEPT_STRING_LENGTH) 
					+ GenerateUtil.autoGenericCode(trade.getShopId().toString(), Constants.INTERCEPT_STRING_LENGTH);
			trade.setPickUpNo(pickUpNo);
		}
		tradeMapper.insert(trade);
		for (Orders o : ordersList) {
			o.setTradeId(trade.getId());
			ordersService.add(o);
		}
		if (CollectionUtils.isNotEmpty(tradeInfoDto.getCartIdList())) {
//			for(Cart c: cartList) {
//				cartMapper.delete(c.getId());
//			}
			int len = tradeInfoDto.getCartIdList().size();
			Long[] ids = new Long[len];
			for(int i = 0; i< len; i++) {
				ids[i] = tradeInfoDto.getCartIdList().get(i);
			}
			CartQuery cartQuery = new CartQuery();
			cartQuery.setIdArray(ids);
			cartMapper.deleteByQuery(cartQuery);
		}
		return trade.getId();
	}

	/**
	 * 校验订单参数
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月18日上午11:37:23
	 * @param trade
	 * @throws ServiceException
	 */
	private void validateParams(TradeInfoDto dto, Trade trade) throws ServiceException {
		// 商家id
		if (dto.getEnterpriseId() == null) {
			logger.warn(BizSupportCode._2001002.getMsg());
			throw new ServiceException(BizSupportCode._2001002.getCode(), BizSupportCode._2001002.getMsg());
		}
		Enterprise enterprise = enterpriseMapper.load(dto.getEnterpriseId());
		if (enterprise == null) {
			logger.warn(BizSupportCode._3000007.getMsg());
			throw new ServiceException(BizSupportCode._3000007.getCode(), BizSupportCode._3000007.getMsg());
		}
		trade.setEnterpriseId(dto.getEnterpriseId());
		trade.setEnterpriseName(enterprise.getEnterpiseName());
		trade.setDistributionMode(enterprise.getDistributionMode());
		if (enterprise.getDistributionMode() == 1) {// 邮递
			// 买家地址
			if (dto.getAddressId() == null) {
				logger.warn(BizSupportCode._3000011.getMsg());
				throw new ServiceException(BizSupportCode._3000011.getCode(), BizSupportCode._3000011.getMsg());
			}
			BuyerAddress buyerAddress = buyerAddressMapper.load(dto.getAddressId());
			if (buyerAddress == null) {
				logger.warn(BizSupportCode._3000012.getMsg());
				throw new ServiceException(BizSupportCode._3000012.getCode(), BizSupportCode._3000012.getMsg());
			}
			trade.setBuyerAddressId(dto.getAddressId());
			trade.setBuyerName(buyerAddress.getBuyerName());
			trade.setBuyerAddress(buyerAddress.getBuyerAddress());
			trade.setBuyerPhone(buyerAddress.getBuyerPhone());
			trade.setProvince(buyerAddress.getProvince());
			trade.setCity(buyerAddress.getCity());
		} else {// 自取
			if (dto.getAddressId() == null) {
				logger.warn(BizSupportCode._3000020.getMsg());
				throw new ServiceException(BizSupportCode._3000020.getCode(), BizSupportCode._3000020.getMsg());
			}
			if (StringUtils.isBlank(dto.getBuyerName())) {
				logger.warn(BizSupportCode._3000022.getMsg());
				throw new ServiceException(BizSupportCode._3000022.getCode(), BizSupportCode._3000022.getMsg());
			}
			if (StringUtils.isBlank(dto.getBuyerPhone())) {
				logger.warn(BizSupportCode._3000023.getMsg());
				throw new ServiceException(BizSupportCode._3000023.getCode(), BizSupportCode._3000023.getMsg());
			}
			trade.setBuyerAddressId(dto.getAddressId());
			CollectPlace collectPlace = collectPlaceMapper.load(trade.getBuyerAddressId());
			if (collectPlace == null) {
				logger.warn(BizSupportCode._3000021.getMsg());
				throw new ServiceException(BizSupportCode._3000021.getCode(), BizSupportCode._3000021.getMsg());
			}
			trade.setBuyerName(dto.getBuyerName());
			trade.setBuyerPhone(dto.getBuyerPhone());
			trade.setBuyerAddress(collectPlace.getClpAddress());
		}
		
		// 卖家id
		if (dto.getSaleId() == null) {
			logger.warn(BizSupportCode._3000001.getMsg());
			throw new ServiceException(BizSupportCode._3000001.getCode(), BizSupportCode._3000001.getMsg());
		}
		trade.setSaleId(dto.getSaleId());
		Seller seller = sellerMapper.load(trade.getSaleId());
		if (seller == null) {
			logger.warn(BizSupportCode._3000008.getMsg());
			throw new ServiceException(BizSupportCode._3000008.getCode(), BizSupportCode._3000008.getMsg());
		}
		trade.setSaleName(seller.getRegisterName());
		
		// 是否需要开发票
		if (dto.getIsInvoice() == null) {
			logger.warn(BizSupportCode._3000005.getMsg());
			throw new ServiceException(BizSupportCode._3000005.getCode(), BizSupportCode._3000005.getMsg());
		}
		trade.setIsInvoice(dto.getIsInvoice());
		if (trade.getIsInvoice() == 1) {
			if (StringUtils.isBlank(dto.getInvoiceName())) {
				logger.warn(BizSupportCode._3000019.getMsg());
				throw new ServiceException(BizSupportCode._3000019.getCode(), BizSupportCode._3000019.getMsg());
			}
			if (dto.getInvoiceType() == null) {
				logger.warn(BizSupportCode._3000026.getMsg());
				throw new ServiceException(BizSupportCode._3000026.getCode(), BizSupportCode._3000026.getMsg());
			}
			trade.setInvoiceName(dto.getInvoiceName());
			trade.setInvoiceType(dto.getInvoiceType());
		}
		// 配送方式1:快递2：自取
		if (trade.getDistributionMode() == null) {
			logger.warn(BizSupportCode._3000006.getMsg());
			throw new ServiceException(BizSupportCode._3000006.getCode(), BizSupportCode._3000006.getMsg());
		}
		if (StringUtils.isNoneBlank(dto.getBuyerRemarks())) {
			trade.setBuyerRemarks(dto.getBuyerRemarks());
		}
		//自提时间
		if (StringUtils.isBlank(dto.getCollectTime())) {
			logger.warn(BizSupportCode._4000004.getMsg());
			throw new ServiceException(BizSupportCode._4000004.getCode(), BizSupportCode._4000004.getMsg());
		}
		trade.setPickUpTime(dto.getCollectTime());
	}

	/**
	 * 校验
	 * @return 
	 */
	private void validateCartList(TradeInfoDto dto, Trade trade, List<Orders> ordersList) throws ServiceException {
		if (CollectionUtils.isEmpty(dto.getCartIdList())) {
			logger.warn(BizSupportCode._3000024.getMsg());
			throw new ServiceException(BizSupportCode._3000024.getCode(), BizSupportCode._3000024.getMsg());
		}
		Orders orders = null;
		Cart cart = null;
		Product product = null;
		ProductSku productSku = null;
		Long shopId = null;
		Long buyerId = null;
		BigDecimal totalPrice = new BigDecimal(0);
		for (Long l : dto.getCartIdList()) {
			cart = cartMapper.load(l);
			if (cart != null) {
				orders = new Orders();
				orders.setNum(cart.getNum());
				orders.setProductId(cart.getProductId());
				if (cart.getSkuId() != null) {
					orders.setSkuId(cart.getSkuId());
					productSku = productSkuMapper.load(cart.getSkuId());
					if (productSku == null) {
						logger.warn(BizSupportCode._3000017.getMsg());
						throw new ServiceException(BizSupportCode._3000017.getCode(), BizSupportCode._3000017.getMsg());
					}
					totalPrice = productSku.getPrice().multiply(new BigDecimal(cart.getNum().toString()));
				} else {
					product = productMapper.load(cart.getProductId());
					if (product == null) {
						logger.warn(BizSupportCode._2001017.getMsg());
						throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
					}
					totalPrice = product.getSalePrice().multiply(new BigDecimal(cart.getNum().toString()));
				}
				orders.setTotalRealPrice(totalPrice);
				shopId = cart.getShopId();
				buyerId = cart.getBuyerId();
				ordersList.add(orders);
			} else {
				logger.warn(BizSupportCode._3000025.getMsg());
				throw new ServiceException(BizSupportCode._3000025.getCode(), BizSupportCode._3000025.getMsg());
			}
		}
		trade.setShopId(shopId);
		trade.setBuyerId(buyerId);
		// 商铺id
		if (trade.getShopId() == null) {
			logger.warn(BizSupportCode._2001016.getMsg());
			throw new ServiceException(BizSupportCode._2001016.getCode(), BizSupportCode._2001016.getMsg());
		}
		Shop shop = shopMapper.load(trade.getShopId());
		if (shop == null) {
			logger.warn(BizSupportCode._3000009.getMsg());
			throw new ServiceException(BizSupportCode._3000009.getCode(), BizSupportCode._3000009.getMsg());
		}
		trade.setShopName(shop.getShopName());
		trade.setPartnerId(shop.getPartnerId());
		// 合作者平台id ---根据商铺id去拿
		Partner partner = partnerMapper.load(shop.getPartnerId());
		if (partner == null) {
			logger.warn(BizSupportCode._3000010.getMsg());
			throw new ServiceException(BizSupportCode._3000010.getCode(), BizSupportCode._3000010.getMsg());
		}
		trade.setPartnerName(partner.getName());
		// 买家id
		if (trade.getBuyerId() == null) {
			logger.warn(BizSupportCode._3000002.getMsg());
			throw new ServiceException(BizSupportCode._3000002.getCode(), BizSupportCode._3000002.getMsg());
		}
	}
	
	@Override
	public int update(Trade t) {
		Assert.notNull(t, "object trade is null");
		Assert.notNull(t.getId(), "required trade id is null");
		return tradeMapper.update(t);
	}

	@Override
	public void delete(Long id) {
		tradeMapper.delete(id);
	}

	@Override
	public List<Trade> queryList(TradeQuery q) {
		return tradeMapper.queryList(q);
	}

	@Override
	public int queryCount(TradeQuery q) {
		return tradeMapper.queryCount(q);
	}

	@Override
	public PageResult<TradeDto> quyerTradeList(TradeQuery tradeQuery) {
		List<TradeDto> list = new ArrayList<>();
		List<Trade> listTrade = tradeMapper.queryList(tradeQuery);
		if (listTrade != null && listTrade.size() > 0) {
			TradeDto tradeDto = null;
			OrdersQuery ordersQuery = null;
			List<Orders> listOrders = null;
			Product product = null;
			for (Trade t : listTrade) {
				boolean itCanPaid = false;
				tradeDto = new TradeDto();
				if(t.getShopId()!=null){
					Shop shop = shopMapper.load(t.getShopId());
					if(shop!=null){
						t.setHeadPicUrl(storeUrl+shop.getHeadPicUrl());
					}
				}
				tradeDto.setTrade(t);
				ordersQuery = new OrdersQuery();
				ordersQuery.setTradeId(t.getId());
				listOrders = ordersService.queryList(ordersQuery);
				if (CollectionUtils.isNotEmpty(listOrders)) {
					for(Orders o: listOrders) {
						product = productService.load(o.getProductId());
						if (product != null) {
							if (product.getStatus() == 1 && !itCanPaid) {
								itCanPaid = true;
							}
							o.setProductStatus(product.getStatus());
						}
					}
				}
				tradeDto.setList(listOrders);
				tradeDto.setItCanPaid(itCanPaid);
				list.add(tradeDto);
			}
		}
		int recordCount = tradeMapper.queryCount(tradeQuery);
		PageResult<TradeDto> listResult = PageResult.create(tradeQuery, list, recordCount);
		return listResult;
	}

	@Override
	public TradeDto getTradeInfo(Long tradeId, Long ownerId, Integer ownerType) throws ServiceException {
		TradeDto dto = null;
		if (tradeId == null) {
			logger.warn(BizSupportCode._2001022.getMsg());
			throw new ServiceException(BizSupportCode._2001022.getCode(), BizSupportCode._2001022.getMsg());
		}
		if (ownerId == null) {
			logger.warn(BizSupportCode._3000014.getMsg());
			throw new ServiceException(BizSupportCode._3000014.getCode(), BizSupportCode._3000014.getMsg());
		}
		if (ownerType == null) {
			logger.warn(BizSupportCode._3000015.getMsg());
			throw new ServiceException(BizSupportCode._3000015.getCode(), BizSupportCode._3000015.getMsg());
		}
		if (ownerType < 1 || ownerType > 3) {
			logger.warn(BizSupportCode._3000016.getMsg());
			throw new ServiceException(BizSupportCode._3000016.getCode(), BizSupportCode._3000016.getMsg());
		}
		TradeParams params = new TradeParams();
		params.setTradeId(tradeId);
		params.setOwnerId(ownerId);
		params.setOwnerType(ownerType);
		Trade trade = tradeMapper.getTrade(params);
		if (trade == null) {
			return null;
		}
		dto = new TradeDto();
		dto.setTrade(trade);
		OrdersQuery query = new OrdersQuery();
		query.setTradeId(trade.getId());
		List<Orders> list = ordersService.queryList(query);
		if (CollectionUtils.isNotEmpty(list)) {
			Product product = null;
			boolean itCanPaid = false; 
			for(Orders o: list) {
				product = productService.load(o.getProductId());
				if (product != null) {
					if (product.getStatus() == 1 && !itCanPaid) {
						itCanPaid = true;
					}
					o.setProductStatus(product.getStatus());
				}
			}
			dto.setItCanPaid(itCanPaid);
			dto.setList(list);
		}
		return dto;
	}

	@Override
	public List<Trade> getTradeForRefundStatus(Long buyerId, Integer refundStatus) {
		TradeRefundParams tradeRefundParams = new TradeRefundParams(buyerId, refundStatus);
		return tradeMapper.getTradeForRefundStatus(tradeRefundParams);
	}

	@Override
	public void addRefund(Long ordersId, Long buyerId, String reason) throws ServiceException {

		logger.debug("-----子订单{}发起退款-------", ordersId);

		Orders orders = ordersService.load(ordersId);

		Product product = productMapper.load(orders.getProductId());
		if (product == null) {
			logger.error(BizSupportCode._2001017.getMsg());
			throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
		}

		Enterprise enterprise = enterpriseMapper.load(product.getEnterpriseId());
		if (enterprise == null) {
			logger.error(BizSupportCode._3000007.getMsg());
			throw new ServiceException(BizSupportCode._3000007.getCode(), BizSupportCode._3000007.getMsg());
		}

		Long tradeId = orders.getTradeId();
		Trade trade = tradeMapper.load(tradeId);
		if (trade == null) {
			logger.error(BizSupportCode._3000027.getMsg());
			throw new ServiceException(BizSupportCode._3000027.getCode(), BizSupportCode._3000027.getMsg());
		}

		String buyerPhone = trade.getBuyerPhone();
		if (buyerPhone == null || "".equals(buyerPhone)) {
			logger.error(BizSupportCode._3000023.getMsg());
			throw new ServiceException(BizSupportCode._3000023.getCode(), BizSupportCode._3000023.getMsg());
		}

		Refund refund = new Refund();
		refund.setBuyerId(buyerId);
		refund.setEnterpriseId(enterprise.getId());
		refund.setEnterpriseName(enterprise.getEnterpiseName());
		refund.setOrdersId(ordersId);
		refund.setPrice(orders.getTotalRealPrice());
		refund.setGmtCreate(new Date());
		refund.setReason(reason);
		refund.setType(EnumOrdersRefundStatus.WAIT_SELLER_AGREE.getValue());
		refund.setBuyerPhone(buyerPhone);
		refundMapper.insert(refund);

		Orders o = new Orders();
		o.setId(ordersId);
		o.setRefundStatus(EnumOrdersRefundStatus.WAIT_SELLER_AGREE.getValue());
		ordersMapper.update(o);
	}

	@Override
	public Trade getTradeInfoBuyTradeNo(String tradeNo) {
		// TODO Auto-generated method stub
		Assert.notNull(tradeNo, "tradeNo is not null");
		return tradeMapper.getTradeInfoBuyTradeNo(tradeNo);
	}

	@Transactional
	@Override
	public Long addCombinationTrade(TradeInfoDto tradeInfoDto) throws ServiceException {
		Trade trade = new Trade();
		List<Orders> ordersList = new ArrayList<>();
		validateParams(tradeInfoDto, trade);
		validateCombinationList(tradeInfoDto, trade, ordersList);
		trade.setStatus(EnumTradeStatus.WAIT_BUYER_PAY.getValue());
		// 处理优惠、
		DiscountDto discountDto = new DiscountDto();
		discountDto.setEnterpriseId(tradeInfoDto.getEnterpriseId());
		combinationProductService.doCombinationDiscount(tradeInfoDto.getCombinationId(), discountDto);
		
		trade.setExpressPrice(discountDto.getExpressPrice());
		trade.setRealPrice(discountDto.getRealPrice());
		trade.setPrice(discountDto.getRealPrice());
//		trade.setDiscountPrice(discountDto.getDiscountPrice());
		trade.setDiscountPrice(new BigDecimal("0"));
		trade.setTradeDesc(discountDto.getDiscountDesc());
		String tradeNo = tradeNoGenarater.nextLongValue() + "" 
				+ GenerateUtil.autoGenericCode(tradeInfoDto.getShopId().toString(), Constants.INTERCEPT_STRING_LENGTH) 
				+ GenerateUtil.autoGenericCode(tradeInfoDto.getBuyerId().toString(), Constants.INTERCEPT_STRING_LENGTH);
		trade.setTradeNo(tradeNo);
		//生成提货码
		if (trade.getDistributionMode() == 2) {
			String pickUpNo = pickUpNoGenarater.nextLongValue() + "" 
					+ GenerateUtil.autoGenericCode(trade.getBuyerId().toString(), Constants.INTERCEPT_STRING_LENGTH) 
					+ GenerateUtil.autoGenericCode(trade.getShopId().toString(), Constants.INTERCEPT_STRING_LENGTH);
			trade.setPickUpNo(pickUpNo);
		}
		tradeMapper.insert(trade);
		for (Orders o : ordersList) {
			o.setTradeId(trade.getId());
			ordersService.add(o);
			destocking(o);
		}
		return trade.getId();
	}

	//库存、销量变化操作
	public void destocking(Orders orders) throws ServiceException {
		//减去库存
		Product product = productService.load(orders.getProductId());
		if (product == null) {
			logger.warn(BizSupportCode._2001017.getMsg());
			throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
		}
		Integer productNum = product.getProductNum() - orders.getNum()< 0 ? 0:(product.getProductNum() - orders.getNum());
		if (productNum == 0) {
			product.setStatus(EnumProductStatus.WAREHOSE.getValue());
		}
		product.setProductNum(productNum);//库存
		product.setSaleNum(product.getSaleNum() + orders.getNum());//销量
		productService.update(product);
		if (orders.getSkuId() != null) {
			ProductSku sku = productSkuService.load(orders.getSkuId());
			if (sku == null) {
				logger.warn(BizSupportCode._3000017.getMsg());
				throw new ServiceException(BizSupportCode._3000017.getCode(), BizSupportCode._3000017.getMsg());
			}
			sku.setQuantity((sku.getQuantity() - orders.getNum()) > 0 ? (sku.getQuantity() - orders.getNum()):0);//sku库存
			productSkuService.update(sku);
		}
	}
	private void validateCombinationList(TradeInfoDto tradeInfoDto, Trade trade, List<Orders> ordersList) throws ServiceException {
		// 组合列表不能为空
		if (CollectionUtils.isEmpty(tradeInfoDto.getListCombinDto())) {
			logger.error(BizSupportCode._3000028.getMsg());
			throw new ServiceException(BizSupportCode._3000028.getCode(), BizSupportCode._3000028.getMsg());
		}
		if (tradeInfoDto.getBuyerId() == null) {
			logger.error(BizSupportCode._3000002.getMsg());
			throw new ServiceException(BizSupportCode._3000002.getCode(), BizSupportCode._3000002.getMsg());
		}
		trade.setBuyerId(tradeInfoDto.getBuyerId());
		if (tradeInfoDto.getShopId() == null) {
			logger.error(BizSupportCode._2001016.getMsg());
			throw new ServiceException(BizSupportCode._2001016.getCode(), BizSupportCode._2001016.getMsg());
		}
		trade.setShopId(tradeInfoDto.getShopId());
		// 商铺id
		if (trade.getShopId() == null) {
			logger.warn(BizSupportCode._2001016.getMsg());
			throw new ServiceException(BizSupportCode._2001016.getCode(), BizSupportCode._2001016.getMsg());
		}
		Shop shop = shopMapper.load(trade.getShopId());
		if (shop == null) {
			logger.warn(BizSupportCode._3000009.getMsg());
			throw new ServiceException(BizSupportCode._3000009.getCode(), BizSupportCode._3000009.getMsg());
		}
		trade.setShopName(shop.getShopName());
		trade.setPartnerId(shop.getPartnerId());
		// 合作者平台id ---根据商铺id去拿
		Partner partner = partnerMapper.load(shop.getPartnerId());
		if (partner == null) {
			logger.warn(BizSupportCode._3000010.getMsg());
			throw new ServiceException(BizSupportCode._3000010.getCode(), BizSupportCode._3000010.getMsg());
		}
		trade.setPartnerName(partner.getName());
		// 买家id
		if (trade.getBuyerId() == null) {
			logger.warn(BizSupportCode._3000002.getMsg());
			throw new ServiceException(BizSupportCode._3000002.getCode(), BizSupportCode._3000002.getMsg());
		}
		Orders orders = null;
		Product product = null;
		ProductSku productSku = null;
	
		for (CombinDto dto: tradeInfoDto.getListCombinDto()) {
			orders = new Orders();
			orders.setNum(dto.getNum());
			orders.setProductId(dto.getProductId());
			if (dto.getSkuId() != null) {
				orders.setSkuId(dto.getSkuId());
				productSku = productSkuMapper.load(dto.getSkuId());
				if (productSku == null) {
					logger.warn(BizSupportCode._3000017.getMsg());
					throw new ServiceException(BizSupportCode._3000017.getCode(), BizSupportCode._3000017.getMsg());
				}
			} else {
				product = productMapper.load(dto.getProductId());
				if (product == null) {
					logger.warn(BizSupportCode._2001017.getMsg());
					throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
				}
			}
			orders.setTotalRealPrice(dto.getRealPrice());
			ordersList.add(orders);
		}

	}

	@Override
	public void sendGoogs(Long[] tradeIds, String[] companys, String[] codes, Integer distributionMode) {
		for(int i=0;i<tradeIds.length;i++){
			//首先判断商户的发货类型是什么
			if(new Integer(1).equals(distributionMode)){
				//表示快递
				//如果是快递的话，参数中必须要有快递公司和运单号还有订单号
				Logistics logistics = new Logistics();
				logistics.setTradeId(tradeIds[i]);
				//快递
				logistics.setLogisticsName(companys[i]);
				//物流单号
				logistics.setLogisticsNo(codes[i]);
				logistics.setGmtCreate(new Date());
				logistics.setStatus(EnumLogistics.WAIT_START.getValue());
				logisticsMapper.insert(logistics);
				
			}
			Trade t = new Trade();
			//如果是自提的话，只需要订单号即可
			t.setId(tradeIds[i]);
			t.setStatus(EnumTradeStatus.WAIT_BUYER_CONFIRM_GOODS.getValue());
			tradeMapper.update(t);
		}
	}

	@Override
	public List<Trade> getWaitBuyerPayTradeList(String overGmtCreate) {
		return tradeMapper.getWaitBuyerPayTradeList(overGmtCreate);
	}
}
