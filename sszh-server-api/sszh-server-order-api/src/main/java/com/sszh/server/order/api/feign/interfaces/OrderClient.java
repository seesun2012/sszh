package com.sszh.server.order.api.feign.interfaces;


import com.alibaba.fastjson.JSONObject;
import com.sszh.server.order.api.feign.fallback.OrderClientHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "sszh-server-order", path = "/order/order", fallback = OrderClientHystric.class)
public interface OrderClient {

    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    Integer insertSelective(@RequestBody JSONObject json) throws Exception;
    
}