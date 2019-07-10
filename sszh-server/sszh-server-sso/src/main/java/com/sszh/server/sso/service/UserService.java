package com.sszh.server.sso.service;

import com.sszh.server.sso.bean.User;
import org.springframework.stereotype.Service;

/**
 * 用户主业务处理类
 */
@Service
public class UserService {

    public User findById(Long id) {
        User findOne = new User();
        if (id == 1) {
            findOne.setAge(20);
            findOne.setName("zhangsan");
            findOne.setUsername("zhangsan");
            findOne.setId(1L);
            findOne.setBalance(800D);
        } else {
            findOne.setAge(18);
            findOne.setName("lisi");
            findOne.setUsername("lisi");
            findOne.setId(2L);
            findOne.setBalance(2000D);
        }
        return findOne;
    }

}
