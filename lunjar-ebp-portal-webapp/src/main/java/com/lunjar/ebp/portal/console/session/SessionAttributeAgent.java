package com.lunjar.ebp.portal.console.session;


/**
 * 用于存放sesion级别的对象,用来代替httpSession
 *
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a>
 * @version %I%,%G%
 * @create 2016/4/18 20:05
 */
public interface SessionAttributeAgent {

    void put(String key,Object object);

    <T> T  get(String key, Class<T> c);

    void remove( String key);
}
