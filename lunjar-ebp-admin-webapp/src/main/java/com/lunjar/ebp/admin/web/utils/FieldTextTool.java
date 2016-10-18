package com.lunjar.ebp.admin.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ancun.bps.core.config.BpsSysConfig;
import com.lunjar.ebp.admin.domain.enums.EnumPartnerStatus;
import com.lunjar.ebp.admin.domain.enums.EnumPartnerType;
import com.lunjar.products.core.config.SysConfig;
import com.lunjar.products.core.utils.PathUtils;

/**
 * 专门处理数据字段转换
 *
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/5 20:27
 * @version %I%,%G%
 * @see
 */
public class FieldTextTool {

    private static final Logger logger = LoggerFactory.getLogger(FieldTextTool.class);
    private static final  String EMPTY = "";

    private WebApplicationContext context;

    private SysConfig sysConfig;
//    private BizSystemCacheForId bizSystemCache;

    public void init(Object obj) {
        context = ContextLoaderListener.getCurrentWebApplicationContext();
        sysConfig=context.getBean(BpsSysConfig.class);
//        bizSystemCache = context.getBean(BizSystemCacheForId.class);
    }

    /**
     * 转换接入者实名状态颜色
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/5 20:29
     * @version %I%,%G%
     * @see
     */
    public String convertPartnerStatus(String status) {
        if (StringUtils.isBlank(status)) {
            return EMPTY;
        }

        EnumPartnerStatus partnerStatus = EnumPartnerStatus.valueOf(Integer.parseInt(status));
        if (partnerStatus == null) {
            return status;
        }

        String color = partnerStatus.getColor();
        String text = partnerStatus.getText();
        String desc = "<span class='" + color + "'>" + text + "</span>";
        return desc;
    }

    /**
     * 转换接入者类型
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/6 9:56
     */
    public String convertPartnerType(String type) {
        if (StringUtils.isBlank(type)) {
            return EMPTY;
        }

        return EnumPartnerType.valueOf(Integer.parseInt(type)).getText();
    }

    /**
     * 转换子系统名称,如用户用户账号来源
     *
     * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/5/6 9:56
     */
    public String ucUser_userForm(String userFromString) {
        if (StringUtils.isBlank(userFromString)) {
            return EMPTY;
        }

        Integer userForm = Integer.parseInt(userFromString);

//        BizSystem bizSystem = bizSystemCache.get(userForm);
//        if (bizSystem==null){
//            logger.debug("can't find bizSystem,userForm【{}】", userForm);
//            return EMPTY;
//        }else {
//            return bizSystem.getSystemName();
//        }
        return null;
    }

    public String convertIpBlack(String ipBlack) {
        if (StringUtils.isBlank(ipBlack)) {
            return null;
        }
        String[] blacks = ipBlack.split(",");
        String s = "";
        for (int i = 0; i < blacks.length; i++) {
            s = s + "<td id=\"ipback" + i + "\">" + blacks[i] + "</td>";
            if ((i + 1) % 2 == 0 && i > 0) {
                s = s + "<br/>";
            }
        }
        return s;
    }

    public String convertPictureUrl(String photoUrl,String imageName) {
        if (StringUtils.isBlank(photoUrl)) {
            return PathUtils.concat(getWebresourcesUrl(),imageName);
        }

        return PathUtils.concat(getStoreUrl(),photoUrl);
    }

    public String getStoreUrl() {
        return sysConfig.get("store.server.url");
    }

    public String getWebresourcesUrl() {
        return sysConfig.get("webresources.url");
    }

//    public static void main(String[] args) {
//		String s = "192.168.1.1,192.168.1.2,192.168.1.3,192.168.1.4,192.168.1.5";
//		System.out.print(convertIpBlack(s));
//	}
}
