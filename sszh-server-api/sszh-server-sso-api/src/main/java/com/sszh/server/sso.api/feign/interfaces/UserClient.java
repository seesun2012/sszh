package com.sszh.server.sso.api.feign.interfaces;


import com.sszh.server.sso.api.entity.UserBean;
import com.sszh.server.sso.api.feign.fallback.UserClientHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sszh-server-sso", path = "/user/user", fallback = UserClientHystric.class)
public interface UserClient {

    @RequestMapping(value = "/loginQuery", method = RequestMethod.POST)
    UserBean loginQuery(@RequestParam(name = "account") String account) throws Exception;
    
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    UserBean selectByPrimaryKey(@RequestParam(name = "id", required = false) Long id) throws Exception;

    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    Integer insertSelective(@RequestBody UserBean user) throws Exception;
    
}