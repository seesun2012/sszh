package com.sszh.web.admin.cache;

import com.sszh.cache.BaseCacheFactory;
import com.sszh.web.admin.cache.init.system.SystemCache;
import com.sszh.web.admin.cache.init.user.UserCache;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 运营端-统一缓存处理类（缓存工厂类-子类）
 */
@Component
//@DependsOn("springContextHolder")
public class AdminCacheFactory extends BaseCacheFactory {



    //系统缓存
    private SystemCache systemCache;
    public SystemCache getSystemCache() {
        if (null == systemCache) {
            systemCache = new SystemCache(getDefaultCache());
        }
        return systemCache;
    }
    //用户缓存
    private UserCache userCache;
    public UserCache getUserCache() {
        if (null == userCache) {
            userCache = new UserCache(getDefaultCache());
        }
        return userCache;
    }



    /**
     * 统一缓存构造器
     * @param redisTemplate
     * @param stringRedisTemplate
     */
    public AdminCacheFactory(RedisTemplate<Object, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        super(redisTemplate, stringRedisTemplate);
    }
    
    /**
     * 初始化缓存（业务缓存）
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        getSystemCache().setApplicationContext(applicationContext);
        getUserCache().setApplicationContext(applicationContext);
    }
    
    
}