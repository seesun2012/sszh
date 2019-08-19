package com.sszh.server.order.api.feign.fallback;


import com.alibaba.fastjson.JSONObject;
import com.sszh.core.code.BaseExceptionCodeEnum;
import com.sszh.core.exception.BaseBusinessException;
import com.sszh.server.order.api.feign.interfaces.OrderClient;
import org.springframework.stereotype.Component;

@Component
public class OrderClientHystric implements OrderClient {
    
    @Override
    public Integer insertSelective(JSONObject json) throws Exception {
        throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_105.getCode(), "服务器正在重启中，请稍后重试...");
    }
    
}