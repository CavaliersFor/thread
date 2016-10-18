package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.cache.LogisticsCompanyCache;
import com.lunjar.ebp.bizsupport.cache.LogisticsCompanyListCache;
import com.lunjar.ebp.bizsupport.mappers.LogisticsCompanyMapper;
import com.lunjar.ebp.bizsupport.model.LogisticsCompany;
import com.lunjar.ebp.bizsupport.model.LogisticsCompanyList;
import com.lunjar.ebp.bizsupport.query.LogisticsCompanyQuery;
import com.lunjar.ebp.bizsupport.service.LogisticsCompanyService;

/**
 * 物流公司服务类实现
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年9月23日上午9:08:43
 */
@Service("logisticsCompanyService")
public class LogisticsCompanyServiceImpl implements LogisticsCompanyService {
	private static final Logger logger = LoggerFactory.getLogger(LogisticsCompanyServiceImpl.class);
	private static final String logistics_company_cache_key = "logistics_company";
	private static final String logistics_company_cache_key_list = "logistics_company_list";
	@Autowired
	LogisticsCompanyMapper logisticsCompanyMapper;
	@Autowired
	LogisticsCompanyListCache logisticsCompanyListCache;
	@Autowired
	LogisticsCompanyCache logisticsCompanyCache;

	@Override
	public Long add(LogisticsCompany logisticsCompany) {
		Assert.notNull(logisticsCompany, "object logisticsCompany is null");
		logisticsCompanyMapper.insert(logisticsCompany);
		logisticsCompanyListCache.remove(logistics_company_cache_key_list);
		return logisticsCompany.getId();

	}

	@Override
	public void update(LogisticsCompany logisticsCompany) {
		Assert.notNull(logisticsCompany, "object logisticsCompany is null");
		Assert.notNull(logisticsCompany.getId(), "required logisticsCompany id is null");
		logisticsCompanyListCache.remove(logistics_company_cache_key_list);
		logisticsCompanyCache.remove(logistics_company_cache_key + logisticsCompany.getId());
		logisticsCompanyMapper.update(logisticsCompany);
	}
	
	@Override
	public List<LogisticsCompany> queryList(LogisticsCompanyQuery query){
		return  logisticsCompanyMapper.queryList(query);
	}
	
	@Override
	public int queryCount(LogisticsCompanyQuery query) {
		return logisticsCompanyMapper.queryCount(query);
	}

	@Override
	public LogisticsCompany load(Long id) {
		Assert.notNull(id, "object id is null");
		LogisticsCompany logisticsCompany = logisticsCompanyCache.get(logistics_company_cache_key + id);
		if (logisticsCompany == null) {
			logisticsCompany = logisticsCompanyMapper.load(id);
			if (logisticsCompany != null) {
				logisticsCompanyCache.put(logistics_company_cache_key + id, logisticsCompany);
			}
		}
		return logisticsCompany;
	}

	@Override
	public List<LogisticsCompany> getAllList() {
		List<LogisticsCompany> list = logisticsCompanyListCache.get(logistics_company_cache_key_list);
		if (CollectionUtils.isEmpty(list)) {
			LogisticsCompanyQuery query = new LogisticsCompanyQuery();
			query.setStatus(1);
			list = logisticsCompanyMapper.queryList(query);
			if (CollectionUtils.isNotEmpty(list)) {
				logisticsCompanyListCache.put(logistics_company_cache_key_list, new LogisticsCompanyList(list));
			}
		}
		return list;
	}
}
