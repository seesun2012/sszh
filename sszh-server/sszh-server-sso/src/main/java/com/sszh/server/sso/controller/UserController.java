package com.sszh.server.sso.controller;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.sszh.server.sso.api.entity.UserBean;
import com.sszh.server.sso.api.feign.interfaces.UserClient;
import com.sszh.server.sso.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户主控制器
 */
@RestController
@RequestMapping("/user/user")
public class UserController implements UserClient {

    @Autowired
    private IUserService userService;

    /**
     * 登陆查询
     */
    @RequestMapping(value = "/loginQuery", method = RequestMethod.POST)
    public UserBean loginQuery(@RequestParam(name = "account") String account) throws Exception {
        return userService.loginQuery(account);
    }

    /**
     * 根据用户ID查询用户信息
     * @param id    用户ID
     */
    @Override
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public UserBean selectByPrimaryKey(@RequestParam(name = "id") Long id) throws Exception {
        return userService.selectByPrimaryKey(id);
    }

    /**
     * 新增用户信息
     */
    @Override
    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    public Integer insertSelective(@RequestBody UserBean user) throws Exception {
        if (null == user) throw new Exception("user对象不能为空");
        if (null == user.getId()) throw new Exception("id不能为空");
        if (null == user.getUserName()) throw new Exception("name不能为空");
        int i = userService.insertSelective(user);
        return i;
    }

}
