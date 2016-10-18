package com.lunjar.ebp.portal.console.session;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import com.lunjar.products.core.cache.RedisUtil;
import com.lunjar.products.core.web.utils.CookieUtil;
import com.lunjar.products.core.web.utils.WebContextUtils;

/**
 * 用来存取session属性的redis作为缓存的实现
 *
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/18 19:42
 * @version %I%,%G%
 * @see
 */
@Component(value = "sessionAttributeAgent")
public class SessionAttributeAgentImpl implements SessionAttributeAgent {
    private final static Logger log = LoggerFactory.getLogger(SessionAttributeAgentImpl.class);

    private final static int TIME_OUT = 3600;   // 默认超时时间3600秒
    private static final String ENCODING="UTF-8";
    private final static String OBJECT_TYPE_KEY = "ANCUNJSESSIONID"; 

    @Autowired
    private UrlPathHelper urlPathHelper;
    @Autowired
    private WebContextUtils webContextUtils;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Override
    public void put(String key,Object object) {
        HttpSession session = request.getSession();

        String sessionCookieName=session.getId();   // 以sessionId作为cookieKey
        int expireSecords=session.getMaxInactiveInterval();    // 获取超时秒
        Date expireDate = DateUtils.addSeconds(new Date(), expireSecords);  // 获取超时时间

        if (log.isDebugEnabled()) {
            log.debug("Servlet [" + urlPathHelper.getPathWithinServletMapping(request) + "], put sessionCookieName:"
                    + sessionCookieName + " " + object);
        }

        // redis存放键值对
        redisUtil.put(sessionCookieName,key,object,expireDate);

        // cookie存放key
        CookieUtil.save(OBJECT_TYPE_KEY, sessionCookieName, TIME_OUT, response,  ENCODING);
    }

    @Override
    public Object get(String key, Class c) {
        String sessionCookieName = CookieUtil.read(OBJECT_TYPE_KEY, request, ENCODING);

        if (StringUtils.isBlank(sessionCookieName)) {
            return null;
        }

        if (log.isDebugEnabled()) {
            log.debug("Servlet [" + webContextUtils.getAppServer(request)
                    + urlPathHelper.getPathWithinServletMapping(request) + "], sessionCookieName: [" + sessionCookieName
                    + "]");
        }

        return  redisUtil.get(sessionCookieName,key,c);
    }

    @Override
    public void remove(String key) {
        String sessionCookieName = CookieUtil.read(OBJECT_TYPE_KEY, request, ENCODING);

        if (log.isDebugEnabled()) {
            log.debug("Servlet [" + webContextUtils.getAppServer(request)
                    + urlPathHelper.getPathWithinServletMapping(request) + "], sessionCookieName: [" + sessionCookieName
                    + "]");
        }

        redisUtil.remove(sessionCookieName,key);
    }

}
