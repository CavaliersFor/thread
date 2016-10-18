package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.model.Refund;
import com.lunjar.ebp.bizsupport.query.RefundQuery;
import com.lunjar.products.core.exception.ServiceException;

public interface RefundService extends CommonService<Refund, RefundQuery>{

	int updateRefundStatus(Refund refund) throws ServiceException;
	
}
