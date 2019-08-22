package com.sszh.web.admin.controller.menu;

import com.github.pagehelper.Page;
import com.sszh.core.result.JSONResult;
import com.sszh.server.sso.api.entity.SysMenuEntity;
import com.sszh.web.admin.cache.AdminCacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统菜单模块
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private AdminCacheFactory adminCacheFactory;


    /**
     * 菜单管理-主页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() throws Exception {
        return "menu/menu-list";
    }

    /**
     * 菜单管理-列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JSONResult<Page<SysMenuEntity>> list(SysMenuEntity entity) throws Exception {
        return JSONResult.newSuccessResult(new Page<SysMenuEntity>());
    }







    /* ========================================================== 菜单栏（左部导航栏） ========================================================== */
    /**
     * 首页菜单栏（左部导航）
     */
    @RequestMapping(value = "/getReloMenuList", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult<List<SysMenuEntity>> getReloMenuList() throws Exception {
        return JSONResult.newSuccessResult(adminCacheFactory.getSysMenuCache().getSystemMenu());
    }


}