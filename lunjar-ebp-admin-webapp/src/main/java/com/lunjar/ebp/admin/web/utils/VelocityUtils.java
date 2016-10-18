package com.lunjar.ebp.admin.web.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ancun.bps.core.datadict.BpsDataDictService;
import com.lunjar.products.core.model.DataDictionary;

/**
 * velocity转换数据字典字段工具类
 *
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/4/21 20:34
 */
@Component
public class VelocityUtils {
    private static final Logger logger = LoggerFactory.getLogger(VelocityUtils.class);

    private static final String EMPTY = "";

    private WebApplicationContext context;

    private BpsDataDictService bpsDataDictService;

    public void init(Object obj) {
        context = ContextLoaderListener.getCurrentWebApplicationContext();
        bpsDataDictService = context.getBean(BpsDataDictService.class);
    }

    /**
     * 获取数据字典的值
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/27 19:19
     * @version %I%,%G%
     * @see
     */
    public String getText(String dictGroup, String value) {
        if (StringUtils.isBlank(value) || StringUtils.isBlank(dictGroup)) {
            return "";
        }

        DataDictionary o = bpsDataDictService.getByValue(dictGroup, value);

        if (o == null) {
            logger.warn("dictGroup【{}】,value【{}】 not match", dictGroup, value);
            return null;
        }

        return o.getText();
    }

    /**
     * 根据dictGroup和parentValue，获取数据字典中的枚举集合
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/27 19:20
     * @version %I%,%G%
     * @see
     */
    public List<DataDictionary> getDictGroupByParentValue(String dictGroup, String parentValue) {
        if (StringUtils.isBlank(dictGroup) || StringUtils.isBlank(parentValue)) {
            return null;
        }
        List<DataDictionary> resultList = bpsDataDictService.getByGroupAndParentValue(dictGroup, parentValue);
        if (resultList != null && resultList.iterator().hasNext()) {
            return resultList;
        }
        logger.warn("dictGroup[{}],parentValue[{}] not match", dictGroup, parentValue);
        return null;
    }

}
