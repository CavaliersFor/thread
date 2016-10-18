package com.lunjar.ebp.admin.biz.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ancun.bps.core.config.impl.BpsSysParameterRemoteService;
import com.lunjar.products.core.config.cache.SystemParameterCache;
import com.lunjar.products.core.config.model.SysParameter;
import com.lunjar.products.core.config.model.SysParameterQuery;
import com.lunjar.products.core.exception.DataNotFindException;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.utils.BeanUtils;

/**
 * 系统参数业务类
 * <p>
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version 2012-12-6 下午2:29:04
 */
@Service
public class SysParameterManager {
	private static final Logger logger = LoggerFactory.getLogger(SysParameterManager.class);

	@Autowired
	private BpsSysParameterRemoteService bpsSysParameterRemoteService;

	@Autowired
	private SystemParameterCache systemParameterCache;

	/** 获取列表 */
	public List<SysParameter> queryForList(SysParameterQuery query) {
		return bpsSysParameterRemoteService.queryForList(query);
	}

	/** 更新 
	 * @throws ServiceException */
	@Transactional
	public void update(SysParameter o) throws ServiceException {
		SysParameter old = bpsSysParameterRemoteService.load(o.getParamKey());

		if (old == null) {
			throw new DataNotFindException();
		}

		if (StringUtils.equals(old.getParamValue(), o.getParamValue())) {
			throw new ServiceException("修改失败,新旧值相同!");
		}

		bpsSysParameterRemoteService.update(o);

		// sysLogService.writeLog(EnumSysLogOpertateObject.SYS_PARAMETER,
		// old.getParamKey(), old.getParamName(),
		// "值[" + old.getParamValue() + "]改为[" + o.getParamValue() + "]");

		systemParameterCache.remove(o.getParamKey());
	}

	public SysParameter getByKey(String key) {
		// 先从缓存中获取
		SysParameter dto = systemParameterCache.get(key);
		if (dto != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Get system parameter from cache, " + dto);
			}
		} else {//
			SysParameter po = bpsSysParameterRemoteService.load(key);
			if (po != null) {
				dto = BeanUtils.quickMap(po, SysParameter.class);

				if (logger.isTraceEnabled()) {
					logger.trace("get " + po + "from database ");
				}
				systemParameterCache.put(key, dto);
			}
		}
		return dto;
	}
}
