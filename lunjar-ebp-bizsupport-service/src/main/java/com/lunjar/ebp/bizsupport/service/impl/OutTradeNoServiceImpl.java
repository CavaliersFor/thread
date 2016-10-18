package com.lunjar.ebp.bizsupport.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.enums.EnumTradeStatus;
import com.lunjar.ebp.bizsupport.mappers.OutTradeNoMapper;
import com.lunjar.ebp.bizsupport.model.OutTradeNo;
import com.lunjar.ebp.bizsupport.query.OutTradeNoQuery;
import com.lunjar.ebp.bizsupport.service.OutTradeNoService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.utils.DateUtils;

/***
 *  多个订单支付获取订单编号服务实现类
 * 
 * @author Administrator
 *
 */
@Service(value = "outTradeNoService")
public class OutTradeNoServiceImpl implements OutTradeNoService {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OutTradeNoServiceImpl.class);
	private static final String FORMAT = "yyyyMMdd";
	
	@Autowired
	private OutTradeNoMapper outTradeNoMapper;
	@Autowired
	private DataFieldMaxValueIncrementer outTradeNoGenarater;
	
	@Override
	public OutTradeNo load(String outTradeNo) {
		Assert.notNull(outTradeNo, BizSupportCode._4000001.getMsg());
		return outTradeNoMapper.load(outTradeNo);
	}

	@Override
	public String add(OutTradeNo t) throws ServiceException {
		Assert.notNull(t, BizSupportCode._4000002.getMsg());
		if (StringUtils.isBlank(t.getTradeIds())) {
			logger.warn(BizSupportCode._4000003.getMsg());
			throw new ServiceException(BizSupportCode._4000003.getCode(), BizSupportCode._4000003.getMsg());
		}
		Long sequnce = outTradeNoGenarater.nextLongValue();
		String date = DateUtils.getNow(FORMAT);
		String outTradeNo = date + sequnce;
		t.setOutTradeNo(outTradeNo);
		t.setStatus(EnumTradeStatus.WAIT_BUYER_PAY.getValue());
		t.setGmtCreate(new Date());
		t.setGmtModify(new Date());
		outTradeNoMapper.insert(t);
		return t.getOutTradeNo();
	}

	@Override
	public void update(OutTradeNo t) {
		Assert.notNull(t, BizSupportCode._4000002.getMsg());
		Assert.notNull(t.getOutTradeNo(), BizSupportCode._4000001.getMsg());
		outTradeNoMapper.update(t);
	}

//	@Override
//	public void delete(Long id) {
//		buyerMapper.delete(id);
//	}

	@Override
	public List<OutTradeNo> queryList(OutTradeNoQuery q) {
		return outTradeNoMapper.queryList(q);
	}

	@Override
	public int queryCount(OutTradeNoQuery q) {
		return outTradeNoMapper.queryCount(q);
	}
}
