package com.sszh.server.sso.api.feign.interfaces;


import com.sszh.server.sso.api.entity.SysMenuEntity;
import com.sszh.server.sso.api.feign.fallback.SysSysUserClientHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "sszh-server-sso", path = "/sso/sysMenu", fallback = SysSysUserClientHystric.class)
public interface SysMenuClient {

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    List<SysMenuEntity> selectAll() throws Exception;

}