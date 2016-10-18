package com.lunjar.ebp.bizsupport.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.tools.config.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.cache.EnterpriseLogisticsCache;
import com.lunjar.ebp.bizsupport.cache.EnterpriseLogisticsListCache;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.mappers.EnterpriseLogisticsMapper;
import com.lunjar.ebp.bizsupport.model.EnterpriseLogistics;
import com.lunjar.ebp.bizsupport.model.EnterpriseLogisticsList;
import com.lunjar.ebp.bizsupport.model.LogisticsCompany;
import com.lunjar.ebp.bizsupport.query.EnterpriseLogisticsQuery;
import com.lunjar.ebp.bizsupport.service.EnterpriseLogisticsService;
import com.lunjar.ebp.bizsupport.service.LogisticsCompanyService;
import com.lunjar.products.core.exception.ServiceException;

import redis.clients.jedis.BinaryClient.LIST_POSITION;

/**
 * 商家-物流公司服务类实现
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年9月22日下午5:12:25
 */
@Service("enterpriseLogisticsService")
public class EnterpriseLogisticsServiceImpl implements EnterpriseLogisticsService {

	private static final Logger logger = LoggerFactory.getLogger(LogisticsCompanyServiceImpl.class);
	private static final String enterprise_logistics_cache_key = "enterprise_logistics_";
	private static final String enterprise_logistics_cache_key_list = "enterprise_logistics_list";
	
	@Autowired
	EnterpriseLogisticsMapper enterpriseLogisticsMapper;
	@Autowired
	EnterpriseLogisticsCache enterpriseLogisticsCache;
	@Autowired
	EnterpriseLogisticsListCache enterpriseLogisticsListCache;
	@Autowired
	LogisticsCompanyService logisticsCompanyService;

	@Override
	public Long add(EnterpriseLogistics enterpriseLogistics) throws ServiceException {
		Assert.notNull(enterpriseLogistics, "object enterpriseLogistics is null");
		if (enterpriseLogistics.getEnterpriseId() == null) {
			logger.warn(BizSupportCode._2001002.getMsg());
			throw new ServiceException(BizSupportCode._2001002.getCode(), BizSupportCode._2001002.getMsg());
		}
		if (enterpriseLogistics.getLogisticsId() == null) {
			logger.warn(BizSupportCode._6000001.getMsg());
			throw new ServiceException(BizSupportCode._6000001.getCode(), BizSupportCode._6000001.getMsg());
		}
		LogisticsCompany logisticsCompany = logisticsCompanyService.load(enterpriseLogistics.getLogisticsId());
		if (logisticsCompany == null) {
			logger.warn(BizSupportCode._6000002.getMsg());
			throw new ServiceException(BizSupportCode._6000002.getCode(), BizSupportCode._6000002.getMsg());
		}
		enterpriseLogistics.setLogisticsCode(logisticsCompany.getCode());
		enterpriseLogistics.setLogisticsName(logisticsCompany.getName());
		enterpriseLogistics.setGmtCreate(new Date());
		enterpriseLogistics.setGmtModify(new Date());
		enterpriseLogistics.setStatus(1);
		enterpriseLogisticsMapper.insert(enterpriseLogistics);
		enterpriseLogisticsListCache.remove(enterprise_logistics_cache_key_list + enterpriseLogistics.getEnterpriseId());
		return enterpriseLogistics.getId();

	}

	@Override
	public void update(EnterpriseLogistics enterpriseLogistics) {
		Assert.notNull(enterpriseLogistics, "object enterpriseLogistics is null");
		Assert.notNull(enterpriseLogistics.getId(), "required enterpriseLogistics id is null");
		enterpriseLogisticsMapper.update(enterpriseLogistics);
		enterpriseLogisticsCache.remove(enterprise_logistics_cache_key + enterpriseLogistics.getId());
		enterpriseLogisticsListCache.remove(enterprise_logistics_cache_key_list + enterpriseLogistics.getEnterpriseId());
	}

	@Override
	public List<EnterpriseLogistics> queryList(EnterpriseLogisticsQuery query) {
		Assert.notNull(query, "object query is null");
		Assert.notNull(query.getEnterpriseId(), "商家id不能为空");
		return enterpriseLogisticsMapper.queryList(query);
	}

	@Override
	public List<EnterpriseLogistics> queryListByEnterpriseId(Long id) {
		Assert.notNull(id, "商家id不能为空");
		List<EnterpriseLogistics> list = enterpriseLogisticsListCache.get(enterprise_logistics_cache_key_list + id);
		if (CollectionUtils.isEmpty(list)) {
			EnterpriseLogisticsQuery query = new EnterpriseLogisticsQuery();
			query.setEnterpriseId(id);
			query.setStatus(1);
			list = enterpriseLogisticsMapper.queryList(query);
			if (CollectionUtils.isNotEmpty(list)) {
				enterpriseLogisticsListCache.put(enterprise_logistics_cache_key_list + id,
						new EnterpriseLogisticsList(list));
			}
		}
		return list;
	}
	
	@Override
	public int queryCount(EnterpriseLogisticsQuery query) {
		return enterpriseLogisticsMapper.queryCount(query);
	}

	@Override
	public EnterpriseLogistics load(Long id) {
		Assert.notNull(id, "object id is null");
		EnterpriseLogistics enterpriseLogistics = enterpriseLogisticsCache.get(enterprise_logistics_cache_key + id);
		if (enterpriseLogistics == null) {
			enterpriseLogistics = enterpriseLogisticsMapper.load(id);
			if (enterpriseLogistics != null) {
				enterpriseLogisticsCache.put(enterprise_logistics_cache_key + id, enterpriseLogistics);
			}
		}
		return enterpriseLogistics;
	}

	@Override
	public void delete(Long id) {
//		enterpriseLogisticsMapper.delete(id);
//		enterpriseLogisticsCache.remove(enterprise_Logistics_cache_key + id);
	}
}
