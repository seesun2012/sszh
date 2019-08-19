package com.sszh.server.sso.controller;

import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.server.sso.api.feign.interfaces.SysUserClient;
import com.sszh.server.sso.service.ISysUserService;
import com.sszh.server.sso.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户主控制器
 */
@RestController
@RequestMapping("/user/user")
public class SysUserController implements SysUserClient {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 登陆查询
     */
    @RequestMapping(value = "/loginQuery", method = RequestMethod.POST)
    public SysUserEntity loginQuery(@RequestParam(name = "account") String account) throws Exception {
        return sysUserService.loginQuery(account);
    }

    /**
     * 根据用户ID查询用户信息
     * @param id    用户ID
     */
    @Override
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public SysUserEntity selectByPrimaryKey(@RequestParam(name = "id") String id) throws Exception {
        return sysUserService.selectByPrimaryKey(id);
    }

    /**
     * 新增用户信息
     */
    @Override
    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    public Integer insertSelective(@RequestBody SysUserEntity user) throws Exception {
        if (null == user) throw new Exception("user对象不能为空");
        if (null == user.getId()) throw new Exception("id不能为空");
        if (null == user.getUserName()) throw new Exception("name不能为空");
        int i = sysUserService.insertSelective(user);
        return i;
    }

}
