package com.sszh.server.order.feign;


import com.sszh.server.order.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "SSZH-SERVER-ORDER", fallbackFactory = FeignClientFallbackFactory.class)
public interface OrderFeignClient {

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);

}