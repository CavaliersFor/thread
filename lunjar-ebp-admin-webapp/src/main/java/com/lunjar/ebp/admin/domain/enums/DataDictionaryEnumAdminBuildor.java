package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.enums.DataDictionaryEnumBuildor;

/**
 * 用枚举作为数据字典的创建器
 * <p>
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version 2012-9-20 下午3:41:17
 */
@Component
public class DataDictionaryEnumAdminBuildor implements DataDictionaryEnumBuildor {

	@Override
	public Map<String, DataDictionaryEnum[]> getEnums() {
		Map<String, DataDictionaryEnum[]> map = new HashMap<String, DataDictionaryEnum[]>();

		map.put("adminUserStatus", EnumAdminUserStatus.values());//后台登录-用户状态
		map.put("regionStatus", EnumRegionStatus.values());//地区状态
		map.put("regionType", EnumRegionType.values());//地区类型
		map.put("dataDictStatus", EnumDataDictStatus.values());//数据字典状态
		map.put("apiType", EnumApiType.values());
		map.put("apiStatus", EnumApiStatus.values());
		map.put("templateStatus", EnumTemplateStatus.values());//模版-模版状态
//		map.put("templateSourceStatus", EnumTemplateSourceStatus.values());//资源资源-模版状态
		map.put("logOperateType", EnumLogOperateType.values());//操作日志-操作类型
		map.put("logOperatorType", EnumLogOperatorType.values());//操作日志-操作人类型
		map.put("logLoginUserType", EnumLogLoginUserType.values());//操作日志-登录用户类型
		map.put("partnerStatus", EnumPartnerStatus.values());//接入者管理-接入者实名审核状态
		map.put("productStatus", EnumPorductStatus.values());//产品管理-产品状态  
		map.put("bizSystemStatus", EnumBizSystemStatus.values());//接口管理-AccessKey状态   
		map.put("storePosition", EnumStorePosition.values()); //附件存储介质
//		map.put("encryptType", EnumEncryptType.values()); //加密方式
		map.put("validateRepeat", EnumProductItemValidateRepeat.values()); //保全点是否需要重复验证  
		map.put("companySealStatus", EnumCompanySealStatus.values()); //公司章状态
		map.put("personSealStatus", EnumPersonSealStatus.values()); //个人章状态
		map.put("logSubsystemType", EnumLogSubsystemType.values()); //登录系统类型
		map.put("logOperatedObjectType", EnumLogOperatedObjectType.values()); //登录系统类型
		map.put("ucStatus", EnumUcStatus.values());//用户中心状态
		map.put("ucCheckStatus", EnumUcCheckStatus.values());//用户中心审核状态
		map.put("ucUserType", EnumUcUserType.values());//用户中心审核状态
		return map;
	}
}
