package com.sszh.server.sso.controller;

import com.alibaba.fastjson.JSONObject;
import com.sszh.server.sso.entity.UserBean;
import com.sszh.server.sso.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户主控制器
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * 查询用户信息
     * @param id    用户ID
     */
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public UserBean findById(@RequestParam(name = "id") Long id) throws Exception {
        UserBean user = userServiceImpl.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 新增用户信息
     */
    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    public int insertSelective(@RequestBody JSONObject json) throws Exception {
        if (null == json) throw new Exception("user对象不能为空");
        UserBean user = JSONObject.toJavaObject(json, UserBean.class);
        if (null == user.getId()) throw new Exception("id不能为空");
        if (null == user.getName()) throw new Exception("name不能为空");
        int i = userServiceImpl.insertSelective(user);
        return i;
    }

}
