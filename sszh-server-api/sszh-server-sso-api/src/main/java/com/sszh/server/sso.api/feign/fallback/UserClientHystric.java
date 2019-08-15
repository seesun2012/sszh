package com.sszh.server.sso.api.feign.fallback;


import com.sszh.server.sso.api.entity.UserBean;
import com.sszh.server.sso.api.feign.interfaces.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserClientHystric implements UserClient {

    @Override
    public UserBean loginQuery(String account) throws Exception {
        return null;
    }

    @Override
    public UserBean selectByPrimaryKey(Long id) throws Exception {
        return null;
    }

    @Override
    public Integer insertSelective(UserBean user) throws Exception {
        return null;
    }
}