package com.sszh.server.sso.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis缓存配置
 */
@Component
public class RedisConfig {

    @Value("${unionpay.redis.hosts}")
    private String hosts;

    @Value("${unionpay.redis.maxIdle}")
    private Integer maxIdle;

    @Value("${unionpay.redis.maxWait}")
    private Integer maxWait;

    @Value("${unionpay.redis.maxTotal}")
    private Integer maxTotal;

    @Value("${unionpay.redis.minIdle}")
    private Integer minIdle;

    @Value("${unionpay.redis.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${unionpay.redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${unionpay.redis.testOnReturn}")
    private boolean testOnReturn;

    @Value("${unionpay.redis.testWhileIdle}")
    private boolean testWhileIdle;

}
