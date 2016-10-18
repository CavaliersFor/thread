package com.lunjar.ebp.admin.web.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ancun.bps.core.datadict.BpsDataDictService;
import com.lunjar.products.core.model.DataDictionary;
/**
 * 数据字典 Velocity-Tools
 * 
 * <p>
 * create at 2016年4月26日 上午11:03:38
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public class PubDataDictTool {

	private static final Logger logger = LoggerFactory.getLogger(PubDataDictTool.class);

    private WebApplicationContext context;

    private BpsDataDictService bpsDataDictService;


    public void init(Object obj) {
        context = ContextLoaderListener.getCurrentWebApplicationContext();
        bpsDataDictService = context.getBean(BpsDataDictService.class);
    }
    
	public void destroy() {
	}
    
    /**
     * 获取字典text
     * @param dictGroup
     * @param value
     * @return
     * <p>
     * author: <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a><br>
     * create at: 2016年4月26日 上午11:04:30
     */
    public String getText(String dictGroup, String value) {
        if (StringUtils.isBlank(value) || StringUtils.isBlank(dictGroup)) {
            return "";
        }
        DataDictionary o = bpsDataDictService.getByValue(dictGroup, value);
        if (o==null){
            logger.warn("dictGroup[{}],value[{}] not match",dictGroup,value);
            return null;
        }
        return o.getText();
    }
    
    /**
     * 根据分组和parentValue取字典列表
     * @param dictGroup
     * @param parentValue
     * @return
     * <p>
     * author: <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a><br>
     * create at: 2016年4月26日 上午11:03:30
     */
    public List<DataDictionary> getDictGroupByParentValue(String dictGroup,String parentValue){
    	if (StringUtils.isBlank(dictGroup)||StringUtils.isBlank(parentValue)) {
			return null;
		}
    	List<DataDictionary> resultList=bpsDataDictService.getByGroupAndParentValue(dictGroup, parentValue);
    	if(resultList!=null&&resultList.iterator().hasNext()){
    		return resultList;
    	}
    	logger.warn("dictGroup[{}],parentValue[{}] not match",dictGroup,parentValue);
        return null;
    }
    /**
     * 根据分组取列表
     * @param dictGroup
     * @return
     * <p>
     * author: <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a><br>
     * create at: 2016年4月26日 上午11:05:23
     */
    public List<DataDictionary> getDictGroup(String dictGroup) {
		if (StringUtils.isBlank(dictGroup)) {
			return null;
		}
		List<DataDictionary> resultList=bpsDataDictService.getByGroup(dictGroup);
		if(CollectionUtils.isNotEmpty(resultList)){
    		return resultList;
    	}
    	logger.warn("dictGroup[{}] not match",dictGroup);
        return null;
	}
}
