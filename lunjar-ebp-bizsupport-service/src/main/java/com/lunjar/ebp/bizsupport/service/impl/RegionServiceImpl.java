package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.enums.EnumRegionType;
import com.lunjar.ebp.bizsupport.mappers.RegionMapper;
import com.lunjar.ebp.bizsupport.model.Region;
import com.lunjar.ebp.bizsupport.query.RegionQuery;
import com.lunjar.ebp.bizsupport.service.RegionService;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 商铺服务实现类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日上午11:11:42
 */
@Service("regionService")
public class RegionServiceImpl implements RegionService {
	private static final Logger log = LoggerFactory.getLogger(RegionServiceImpl.class);

	@Autowired
	RegionMapper regionMapper;

	@Override
	public List<Region> getByParentCode(String parentCode) throws ServiceException {
		if (StringUtils.isBlank(parentCode)) {
			log.warn(BizSupportCode._9000000.getMsg());
			throw new ServiceException(BizSupportCode._9000000.getCode(), BizSupportCode._9000000.getMsg());
		}
		RegionQuery query = new RegionQuery();
		query.setParentCode(parentCode);
		return regionMapper.queryList(query);
	}

	@Override
	public Region getByCode(String code) throws ServiceException {
		if (StringUtils.isBlank(code)) {
			log.warn(BizSupportCode._9000001.getMsg());
			throw new ServiceException(BizSupportCode._9000001.getCode(), BizSupportCode._9000001.getMsg());
		}
		return regionMapper.load(code);
	}

	@Override
	public List<Region> getAllProvince() {
		RegionQuery query = new RegionQuery();
		query.setRegionType(EnumRegionType.PROVINCE.getValue());
		return regionMapper.queryList(query);
	}

	@Override
	public List<Region> getByParentCodeForTree(String rootCode, Integer level) throws ServiceException {
		if (StringUtils.isBlank(rootCode)) {
			log.warn(BizSupportCode._9000002.getMsg());
			throw new ServiceException(BizSupportCode._9000002.getCode(), BizSupportCode._9000002.getMsg());
		}
		if (level == null) {
			log.warn(BizSupportCode._9000003.getMsg());
			throw new ServiceException(BizSupportCode._9000003.getCode(), BizSupportCode._9000003.getMsg());
		}
		RegionQuery query = new RegionQuery();
		query.setParentCode(rootCode);
		query.setRegionType(level);
		return regionMapper.queryList(query);
	}

	@Override
	public String getParentCode(String code) throws ServiceException {
		if (StringUtils.isBlank(code)) {
			log.warn(BizSupportCode._9000001.getMsg());
			throw new ServiceException(BizSupportCode._9000001.getCode(), BizSupportCode._9000001.getMsg());
		}
		return regionMapper.getParentCode(code);
	}

}
