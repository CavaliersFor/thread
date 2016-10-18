package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogisticsCompanyList extends ArrayList<LogisticsCompany> implements Serializable {

	private static final long serialVersionUID = 3938345353290099476L;

	public LogisticsCompanyList() {

	}

	public LogisticsCompanyList(List<LogisticsCompany> logisticsCompanyList) {
		Collections.addAll(this, logisticsCompanyList.toArray(new LogisticsCompany[] {}));
	}
}
