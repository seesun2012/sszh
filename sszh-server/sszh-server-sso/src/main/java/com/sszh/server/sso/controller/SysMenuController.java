package com.sszh.server.sso.controller;

import com.sszh.server.sso.api.entity.SysMenuEntity;
import com.sszh.server.sso.api.feign.interfaces.SysMenuClient;
import com.sszh.server.sso.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/sso/sysMenu")
public class SysMenuController implements SysMenuClient {
    
    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 获取菜单（全部）
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public List<SysMenuEntity> selectAll() throws Exception {
        return sysMenuService.selectAll();
    }

}