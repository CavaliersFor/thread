package com.lunjar.ebp.portal.console.enums;

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
		Map<String, DataDictionaryEnum[]> map = new HashMap<String, DataDictionaryEnum[]>(8);
		
//		map.put("adminUserStatus", EnumAdminUserStatus.values());//登录用户状态
//		map.put("regionStatus", EnumRegionStatus.values());//地区状态
//		map.put("regionType", EnumRegionType.values());//地区类型
//		map.put("dataDictStatus", EnumDataDictStatus.values());//数据字典状态
//		map.put("apiType", EnumApiType.values());
//		map.put("templateStatus", EnmuTemplateStatus.values());
		
//		map.
//		map.put("templateStatus", EnumTemplateStatus.values());
		return map;
	}
}
