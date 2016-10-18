package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnterpriseLogisticsList extends ArrayList<EnterpriseLogistics> implements Serializable {

	private static final long serialVersionUID = -3857938530973951828L;

	public EnterpriseLogisticsList() {

	}

	public EnterpriseLogisticsList(List<EnterpriseLogistics> enterpriseLogisticsList) {
		Collections.addAll(this, enterpriseLogisticsList.toArray(new EnterpriseLogistics[] {}));
	}
}
