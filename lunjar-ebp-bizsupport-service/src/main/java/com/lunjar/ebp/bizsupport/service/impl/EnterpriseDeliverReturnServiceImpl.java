package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.mappers.EnterpriseDeliverReturnMapper;
import com.lunjar.ebp.bizsupport.model.EnterpriseDeliverReturn;
import com.lunjar.ebp.bizsupport.query.EnterpriseDeliverReturnQuery;
import com.lunjar.ebp.bizsupport.service.EnterpriseDeliverReturnService;

@Service(value = "enterpriseDeliverReturnService")
public class EnterpriseDeliverReturnServiceImpl implements EnterpriseDeliverReturnService {

	@Autowired
	private EnterpriseDeliverReturnMapper enterpriseDeliverReturnMapper;

	@Override
	public EnterpriseDeliverReturn load(Long id) {
		return enterpriseDeliverReturnMapper.load(id);
	}

	@Override
	public Long add(EnterpriseDeliverReturn t) {
		enterpriseDeliverReturnMapper.insert(t);
		return t.getId();
	}

	@Override
	public int update(EnterpriseDeliverReturn t) {
		return enterpriseDeliverReturnMapper.update(t);
	}

	@Override
	public void delete(Long id) {
		enterpriseDeliverReturnMapper.delete(id);
	}

	@Override
	public List<EnterpriseDeliverReturn> queryList(EnterpriseDeliverReturnQuery q) {
		return enterpriseDeliverReturnMapper.queryList(q);
	}

	@Override
	public int queryCount(EnterpriseDeliverReturnQuery q) {
		return enterpriseDeliverReturnMapper.queryCount(q);
	}

	@Override
	public int deleteByIdAndEnterpriseId(EnterpriseDeliverReturn enterpriseDeliverReturn) {
		return enterpriseDeliverReturnMapper.deleteByIdAndEnterpriseId(enterpriseDeliverReturn);
	}

}
