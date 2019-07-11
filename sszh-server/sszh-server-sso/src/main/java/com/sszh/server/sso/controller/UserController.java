package com.sszh.server.sso.controller;

import com.sszh.server.sso.bean.User;
import com.sszh.server.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户主控制器
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${unionpay.redis.hosts}")
    private String hosts;

    /**
     * 查询用户信息
     * @param id    用户ID
     */
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

}
