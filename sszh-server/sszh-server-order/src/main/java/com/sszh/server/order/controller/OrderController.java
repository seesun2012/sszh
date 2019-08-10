package com.sszh.server.order.controller;


import com.alibaba.fastjson.JSONObject;
import com.sszh.server.order.api.entity.OrderBean;
import com.sszh.server.order.api.feign.interfaces.OrderClient;
import com.sszh.server.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单-主控制器
 */
@RestController
@RequestMapping("/order/order")
public class OrderController implements OrderClient {


    @Resource
    private IOrderService orderService;

    
    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    public Integer insertSelective(@RequestBody JSONObject json) throws Exception {
        OrderBean record = JSONObject.toJavaObject(json, OrderBean.class);
        int i = orderService.insertSelective(record);
        return i;
    }


    

}