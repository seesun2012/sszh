package com.sszh.web.admin.controller.system;

import com.github.pagehelper.PageInfo;
import com.sszh.common.util.string.StringUtils;
import com.sszh.core.code.BaseExceptionCodeEnum;
import com.sszh.core.exception.BaseBusinessException;
import com.sszh.core.result.JSONResult;
import com.sszh.web.admin.cache.AdminCacheFactory;
import com.sszh.web.admin.entity.SysMenuEntity;
import com.sszh.web.admin.service.ISysMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单模块
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    private ISysMenuService sysMenuService;
    @Resource
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
    @RequestMapping(value = "/selectPage", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult<PageInfo<SysMenuEntity>> selectPage(SysMenuEntity entity, Integer pageNum, Integer pageSize) throws Exception {
        return JSONResult.newSuccessResult(sysMenuService.getPage(entity, pageNum, pageSize));
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult<Integer> update(SysMenuEntity record) throws Exception {
        if (null == record || null == record.getId()) throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_10000.getCode(), "参数对象不能为空");
        return JSONResult.newSuccessResult(sysMenuService.updateByPrimaryKeySelective(record));
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult<Integer> delete(Integer id) throws Exception {
        if (null == id) throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_10000.getCode(), "参数对象不能为空");
        return JSONResult.newSuccessResult(sysMenuService.deleteByPrimaryKey(id));
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