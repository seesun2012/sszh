package com.sszh.server.order.api.feign.fallback;


import com.alibaba.fastjson.JSONObject;
import com.sszh.server.order.api.feign.interfaces.OrderClient;
import org.springframework.stereotype.Component;

@Component
public class OrderClientHystric implements OrderClient {
    
    @Override
    public Integer insertSelective(JSONObject json) throws Exception {
        return null;
    }
    
}