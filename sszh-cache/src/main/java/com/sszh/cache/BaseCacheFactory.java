package com.sszh.cache;

import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * 统一缓存处理类（缓存工厂类-父类）
 */
//项目起不来，请加上这四行被注释的内容
//@Component
//@DependsOn("springContextHolder")
//import org.springframework.stereotype.Component;
//import org.springframework.context.annotation.DependsOn;
public abstract class BaseCacheFactory implements ApplicationContextAware {

    private BaseCacheService basecache;
    public StringRedisTemplate stringRedisTemplate;
    public RedisTemplate<Object, Object> redisTemplate;
    
    public BaseCacheFactory(RedisTemplate<Object, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 默认缓存处理器
     */
    public BaseCacheService getDefaultCache() {
        if (basecache == null) {
            basecache = new BaseCacheService(redisTemplate, stringRedisTemplate);
        }
        return basecache;
    }

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public RedisTemplate<Object, Object> getRedisTemplate() {
        return redisTemplate;
    }
}