package com.sszh.cache;

import org.springframework.context.ApplicationContext;

public abstract class BaseCache implements BaseCacheKeys {

    public abstract void setApplicationContext(ApplicationContext applicationContext);

    
}