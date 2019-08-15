package com.sszh.web.admin.cache.init.system;

import com.sszh.cache.BaseCacheService;
import com.sszh.core.enums.code.BaseExceptionCodeEnum;
import com.sszh.core.exception.BaseException;
import com.sszh.core.exception.BusinessException;
import com.sszh.web.admin.cache.AdminBaseCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 运营缓存-系统模块
 */
public class SystemCache extends AdminBaseCache {

    private static final Logger logger = LoggerFactory.getLogger(SystemCache.class);


    /**================================== 系统相关 ========================================*/
    public static final String SELF_KEY = PROJECT_KEY + "system:";



    /**================================== 系统-验证码相关 ========================================*/
    public static final String YAN_ZHENG_MA_KEY = SELF_KEY + "yzm:";
    //缓存验证码（5分钟有效）
    public void setYanZhengMa(String sessionId, String yzm) {
        basecache.set(YAN_ZHENG_MA_KEY + sessionId, yzm, 300L);
    }
    //获取验证码
    public String getYanZhengMa(String sessionId) throws BaseException {
        String yzm = basecache.getStr(YAN_ZHENG_MA_KEY + sessionId);
        if (null == yzm) throw new BusinessException(BaseExceptionCodeEnum.BASE_10000.getCode(), "页面停留时间过长，图片验证码已被清除，请刷新重试");
        return yzm;
    }
    //清除验证码
    public void delYanZhengMa(String sessionId) {
        basecache.del(YAN_ZHENG_MA_KEY + sessionId);
    }


    /**
     * 构造器
     */
    private BaseCacheService basecache;
    public SystemCache(BaseCacheService basecache) {
        this.basecache = basecache;
    }
    /**
     * 缓存类初始化（可以用于将数据库数据缓存进redis）
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.info("----------------------->初始化缓存控制器：运营-系统模块");
    }

}