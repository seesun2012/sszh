package com.sszh.web.admin.cache.init.user;

import com.sszh.cache.BaseCacheService;
import com.sszh.common.util.date.ExpireTime;
import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.web.admin.cache.AdminBaseCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Set;

/**
 * 运营缓存-用户模块
 */
public class UserCache extends AdminBaseCache {

    private static final Logger logger = LoggerFactory.getLogger(UserCache.class);
    
    /**================================== 用户相关 ========================================*/
    public static final String SELF_KEY = PROJECT_KEY + "user:";
    public static final String USER_SESSION_INFO_KEY = SELF_KEY + "session:info.";



    /**================================== 登陆相关 ========================================*/
    /**
     * 获取用户信息
     * @param ip                真实IP地址（包含代理IP）
     * @param sessionId         会话ID
     */
    public SysUserEntity getUserSessionInfo(final String sessionId, final String ip) {
        Set<String> keys = basecache.keys(USER_SESSION_INFO_KEY + "*." + sessionId + "." + ip);
        if (null == keys) return null;
        for (String key : keys) {
            return basecache.getJson(key, SysUserEntity.class);
        }
        return null;
    }

    /**
     * 录入登陆信息（半个小时有效）
     * @param sysUserEntity     用户实体，必须包含ID
     * @param ip                真实IP地址（包含代理IP）
     * @param sessionId         会话ID
     */
    public void setUserSessionInfo(final SysUserEntity sysUserEntity, final String sessionId, final String ip) {
        //踢出异地登陆
        delUserSessionInfo(sysUserEntity, sessionId);
        //添加登陆缓存（key组合：前缀 + 用户ID + session会话ID + IP地址）
        basecache.setJson((USER_SESSION_INFO_KEY + sysUserEntity.getId() + "." + sessionId + "." + ip), sysUserEntity, new ExpireTime(1800L));
    }

    /**
     * 踢出所有的异地登陆
     * @param sysUserEntity     用户实体，必须包含ID
     * @param sessionId         会话ID
     */
    public void delUserSessionInfo(final SysUserEntity sysUserEntity, final String sessionId) {
        if (null != sysUserEntity && !"".equals(sysUserEntity.getId())) {
            Set<String> keys = basecache.keys(USER_SESSION_INFO_KEY + sysUserEntity.getId() + ".*");
            if (null != keys && keys.size()>0) {
                for (String key : keys) {
                    basecache.del(key);
                }
            }
        }
        if (null != sessionId && !"".equals(sessionId)) {
            Set<String> keys = basecache.keys(USER_SESSION_INFO_KEY + "*." + sessionId + ".*");
            if (null != keys && keys.size()>0) {
                for (String key : keys) {
                    basecache.del(key);
                }
            }
        }
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