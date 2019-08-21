package com.sszh.server.sso.api.feign.interfaces;


import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.server.sso.api.feign.fallback.SysSysUserClientHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sszh-server-sso", path = "/sso/sysUser", fallback = SysSysUserClientHystric.class)
public interface SysUserClient {

    @RequestMapping(value = "/loginQuery", method = RequestMethod.POST)
    SysUserEntity loginQuery(@RequestParam(name = "account") String account) throws Exception;
    
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    SysUserEntity selectByPrimaryKey(@RequestParam(name = "id", required = false) String id) throws Exception;

    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    Integer insertSelective(@RequestBody SysUserEntity user) throws Exception;
    
}