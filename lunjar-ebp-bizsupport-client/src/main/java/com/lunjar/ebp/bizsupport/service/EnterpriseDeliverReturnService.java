package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.model.EnterpriseDeliverReturn;
import com.lunjar.ebp.bizsupport.query.EnterpriseDeliverReturnQuery;
/**
 * 商家发货(退货)地址服务类
 * @author Administrator
 *
 */
public interface EnterpriseDeliverReturnService extends CommonService<EnterpriseDeliverReturn, EnterpriseDeliverReturnQuery>{
	/**
	 * 通过id和EnterpriseId删除	
	 * @param enterpriseDeliverReturn
	 * @return
	 */
	int deleteByIdAndEnterpriseId(EnterpriseDeliverReturn enterpriseDeliverReturn);
}
	
