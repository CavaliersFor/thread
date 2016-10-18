package com.lunjar.ebp.portal.console.utils;

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
public class VelocityBizUtils {
    private static final Logger logger = LoggerFactory.getLogger(VelocityBizUtils.class);

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
     * 隐藏手机号,如1506852315->150*****315
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/26 14:13
     * @version %I%,%G%
     * @see
     */
    public String hideMobile(String mobile) {
        String result = "";
        String hiddenString = "*****";
        if (StringUtils.isBlank(mobile)) {
            return result;
        }

        if (mobile.length() == 11) {
            result = mobile.substring(0, 3) + hiddenString + mobile.substring(8, 11);
            return result;
        }

        return "不支持的手机号码格式:" + mobile;
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

    /**
     * 转换模板的版本号，在版本之前加一个'0'
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/28 14:18
     * @version %I%,%G%
     * @see
     */
    public String convertTemplateVersion(String versionString) {
        if (versionString == null) {
            return "01";
        }

        Integer version = 1;
        try {
            version = Integer.parseInt(versionString);
        } catch (Exception e) {
            logger.error("解析模板版本出错", e);
        }

        return "0" + version;
    }

    public String convertTemplateStatus(String statusString) {
        if (statusString == null) {
            return "状态为空";
        }

        Integer status = Integer.parseInt(statusString);

        if (status == 1) {
            return "未提交";
        } else if (status == 2) {
            return "审核中";
        } else if (status == 3) {
            return "审核通过";
        } else if (status == 4) {
            return "审核不通过";
        } else if (status == 5) {
            return "过期";
        }

        return "0";
    }

    /**
     * 转换产品接入终端,如1,2,3 ->
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/9 15:31
     * @version %I%,%G%
     * @see
     */
    public String product_SdkTypeArray(String sdkArray) {
        if (StringUtils.isBlank(sdkArray)) {
            return EMPTY;
        }

        StringBuilder result = new StringBuilder();

        String[] sdks = StringUtils.split(sdkArray, ",");
        for (String sdk : sdks) {
            String sdkType = getText("sdkType", sdk);
            result.append(sdkType).append(",");
        }

        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    /**
     * 转换产品白名单是否启用
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/9 15:37
     * @version %I%,%G%
     * @see
     */
//    public String product_IpWhiteEnable(String ipWhiteEnable) {
//        if (StringUtils.isBlank(ipWhiteEnable)) {
//            return EMPTY;
//        }
//
//        String text = EnumIpWhiteEnable.get(ipWhiteEnable).getText();
//        return text == null ? ipWhiteEnable : text;
//    }
     /**
      * 对应于product_keys表,sdk_type字段
      *
      * SDK终端类型 1 JAVA ,2 PHP,3 Android ,4 IOS，5 windows，6 ROR ,0 无SDK,9其他
      *
      * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/10 20:42
      * @version %I%,%G%
      * @see
      */
//    public String productKeys_keyType(Integer keyType) {
//        if(keyType==null){
//            return EMPTY;
//        }
//
//        String text = EnumKeyType.valueOf(keyType).getText();
//        return text;
//    }

}
