package com.sszh.web.admin.cache.init.user;

import com.sszh.cache.BaseCacheService;
import com.sszh.cache.util.ExpireTime;
import com.sszh.server.sso.api.entity.UserBean;
import com.sszh.web.admin.cache.AdminBaseCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 运营缓存-用户模块
 */
public class UserCache extends AdminBaseCache {

    private static final Logger logger = LoggerFactory.getLogger(UserCache.class);
    
    /**================================== 用户相关 ========================================*/
    public static final String SELF_KEY = PROJECT_KEY + "user:";
    public static final String USER_SESSION_INFO_KEY = SELF_KEY + "session:info.";
    
    
    //获取用户信息
    public UserBean getUserSessionInfo(String sessionId) {
        return basecache.getJson(USER_SESSION_INFO_KEY + sessionId, UserBean.class);
    }
    //录入登陆信息
    public void setUserSessionInfo(UserBean userBean, String sessionId) {
        basecache.setJson(USER_SESSION_INFO_KEY + sessionId, userBean, new ExpireTime(10L));
    }
    //踢出异地登陆
    public void delUserSessionInfo(String sessionId) {
        basecache.del(USER_SESSION_INFO_KEY + sessionId);   
    }


    
    
    
    /**
     * 构造器
     */
    private BaseCacheService basecache;
    public UserCache(BaseCacheService basecache) {
        this.basecache = basecache;
    }
    /**
     * 缓存类初始化（可以用于将数据库数据缓存进redis）
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.info("----------------------->初始化缓存控制器：运营-用户模块");
    }
    
}