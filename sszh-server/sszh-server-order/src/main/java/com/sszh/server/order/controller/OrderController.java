package com.sszh.server.order.controller;

import com.sszh.server.order.bean.User;
import com.sszh.server.order.feign.OrderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单-主控制器
 */
@RestController
public class OrderController {

    @Autowired
    private OrderFeignClient orderFeignClient;

    @GetMapping("/order/{id}")
    public User findById(@PathVariable Long id) {
        User user = this.orderFeignClient.findById(id);
        return user;
    }


    @GetMapping("/order/getOrderStr")
    @ResponseBody
    public String getOrderStr(String id) {
        return "ASLFKA:" + id;
    }


}