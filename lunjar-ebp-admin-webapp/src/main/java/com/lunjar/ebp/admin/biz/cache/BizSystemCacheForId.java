package com.lunjar.ebp.admin.biz.cache;
//package com.lunjar.demo.biz.cache;
//
//import com.ancun.bps.bizsupport.model.BizSystem;
//import com.ancun.bps.bizsupport.service.BizSystemService;
//import com.ancun.products.core.cache.CacheSupport;
//import com.ancun.products.core.cache.EHCacheUtil;
//import com.lunjar.demo.web.utils.FieldTextTool;
//
//import net.sf.ehcache.CacheManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.Serializable;
//
///**
// * bizsupport.biz_system对应的的缓存,主要用于实名验证显示用户来源
// *
// * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/6/26 13:40
// * @see FieldTextTool#ucUser_userForm(java.lang.String)
// */
//@Component
//public class BizSystemCacheForId extends CacheSupport<BizSystem> {
//    private static final Logger logger = LoggerFactory.getLogger(BizSystemCacheForId.class);
//
//    private static final String CACHE_ID = BizSystemCacheForId.class.getSimpleName();
//
//    @Autowired
//    @Qualifier("ehCacheManagerAdmin")
//    private CacheManager ehCacheManager;
//    @Autowired
//    private BizSystemService bizSystemService;
//
//    @Override
//    public String getCacheId() {
//        return CACHE_ID;
//    }
//
//    @PostConstruct
//    public void init() {
//        ehCacheUtil = new EHCacheUtil(ehCacheManager, CACHE_ID);
//    }
//
//    @Override
//    public BizSystem get(Serializable key) {
//        BizSystem bizSystem = super.get(key);
//
//        if (bizSystem==null){
//            bizSystem = bizSystemService.load((Integer) key);
//
//            if(bizSystem!=null){
//                if (logger.isDebugEnabled()){
//                    logger.debug("can't find bizSystem from cache,then find from database,key【{}】,bizSystem【{}】",key,bizSystem);
//                }
//
//                put(key,bizSystem);
//            }else {
//                if (logger.isDebugEnabled()){
//                    logger.debug("can't find bizSystem from cache,but can't find from database either,key【{}】",key);
//                }
//            }
//        }
//
//        return bizSystem;
//    }
//}
