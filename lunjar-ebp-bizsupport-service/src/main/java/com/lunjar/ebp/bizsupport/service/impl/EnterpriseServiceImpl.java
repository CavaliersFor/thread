package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.dto.EnterpriseDto;
import com.lunjar.ebp.bizsupport.enums.EnterpriseServiceResultEnum;
import com.lunjar.ebp.bizsupport.mappers.EnterpriseMapper;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.query.EnterpriseQuery;
import com.lunjar.ebp.bizsupport.service.EnterpriseService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.service.ServiceResult;
import com.lunjar.products.core.utils.DigestsUtils;

@Service(value = "enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(EnterpriseServiceImpl.class);
	
	@Value("${store.server.url}")
	private String storeUrl;
	
	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Override
	public Enterprise load(Long id) {
		Enterprise enterprise = enterpriseMapper.load(id);
		enterprise.setHeadPicUrl(storeUrl + enterprise.getHeadPicUrl());
		return enterprise;
	}

	@Override
	public Long add(Enterprise t) throws ServiceException {
		enterpriseMapper.insert(t);
		return t.getId();
	}

	@Override
	public int update(Enterprise t) {
		return enterpriseMapper.update(t);
	}

	@Override
	public void delete(Long id) {
		enterpriseMapper.delete(id);
	}

	@Override
	public List<Enterprise> queryList(EnterpriseQuery q) {
		List<Enterprise> list = enterpriseMapper.queryList(q);
		if(list!=null && list.size()>0){
			for(Enterprise e:list){
				e.setAbsoluteHeadPicUrl(storeUrl + e.getHeadPicUrl());
			}
		}
		return list;
	}

	@Override
	public int queryCount(EnterpriseQuery q) {
		return enterpriseMapper.queryCount(q);
	}
	
	/***
	 * 登陆方法
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Enterprise login(EnterpriseDto dto) throws ServiceException {
		Enterprise user = loadByAccount(dto.getAccount());

		ServiceResult<Enterprise> result = validateUser(user, dto.getSign(), dto.getRandom());

		if (result.isSuccess()) {
			logger.debug("用户{}登陆成功",dto.getAccount());
			//handleSuccessLogin(user);

			//Enterprise agent = createAdminAgent(user);
		//	result.setData(agent);

			// 写登录日志表 xyy
			//operateLogService.writeUpdateLog(agent, user, user.getId());//写登录日志
			return user;
		}else{
			return null;
		}

	}
	
	private ServiceResult<Enterprise> validateUser(Enterprise user, String sign, String random) throws ServiceException {
		ServiceResult<Enterprise> result = new ServiceResult<Enterprise>();

		// 账户不存在
		if (user == null) {
			logger.error(EnterpriseServiceResultEnum._2060501.getMsg());
			throw new ServiceException(EnterpriseServiceResultEnum._2060501.getCode(),EnterpriseServiceResultEnum._2060501.getMsg());
		}

		/*// 如果状态为空,则认为是正常,避免空指针错误
		if (user.getStatus() == null) {
			user.setStatus(EnumAdminUserStatus.NORMAL.getValue());
		}*/

		//  验证用户状态
		/*if (user.getStatus().intValue() == -1) {// 已删除
			result.setResponseCode(AdminServiceResultCode._2060501);
			return  result;
		} else if (EnumAdminUserStatus.DEPARTURE.getValue() == user.getStatus().intValue()) {// 冻结
			result.setResponseCode(AdminServiceResultCode._2060503);
			return  result;
		} else if (EnumAdminUserStatus.LOCK.getValue() == user.getStatus().intValue()) {// 锁定

			// 如果未超过解锁时间账户更新账户状态为正常
			if (new Date().after(user.getLatestUnlockTime())) {// 解锁
				user.setStatus(EnumAdminUserStatus.NORMAL.getValue());
				adminUserMapper.update(user);
			} else {//
				result.setResponseCode(AdminServiceResultCode._2060502);// 登录失败账户被锁定
				return  result;
			}

		}

		// 当用户的密码为空或是初始密码，必须让用户设置密码才能登录
		Boolean firstLogin=user.getLoginCount()==0;
		Boolean isInitPassword = DEFAULT_PASSWD.equals(user.getPwd()) || StringUtils.isBlank(user.getPwd());
		if(firstLogin && isInitPassword){
			result.setResponseCode(AdminServiceResultCode._2060504);
			return  result;
		}*/

		// 验证用户名密码是否正确
		String s1 = DigestsUtils.md5Hex((user.getAccount() + user.getPassword()).getBytes());
		String sign2 = DigestsUtils.md5Hex((s1 + random).getBytes());
		if (!StringUtils.equals(sign, sign2)) {
			throw new ServiceException(EnterpriseServiceResultEnum._2060501.getCode(),EnterpriseServiceResultEnum._2060501.getMsg());
			//handleFailLogin(user,result);
		}
		// 验证通过
		return result;

	}
	
	private Enterprise loadByAccount(String account) {
		EnterpriseQuery query = new EnterpriseQuery();
		query.setAccount(account);
		//通过账号查询
		List<Enterprise> users = this.queryList(query);
		if (users == null || users.size()!=1) {
			//
			return null;
		}else{
			return users.get(0);
		}
	}

	@Override
	public Enterprise getById(Long id) {
		Enterprise enterprise = enterpriseMapper.load(id);
		if(enterprise.getHeadPicUrl()!=null && !"".equals(enterprise.getHeadPicUrl())){
			enterprise.setAbsoluteHeadPicUrl(storeUrl + enterprise.getHeadPicUrl());
		}
		return enterprise;
	}
}
