package com.sszh.server.sso.api.feign.fallback;


import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.server.sso.api.feign.interfaces.SysUserClient;
import org.springframework.stereotype.Component;

@Component
public class SysSysUserClientHystric implements SysUserClient {

    @Override
    public SysUserEntity loginQuery(String account) throws Exception {
        return null;
    }

    @Override
    public SysUserEntity selectByPrimaryKey(Long id) throws Exception {
        return null;
    }

    @Override
    public Integer insertSelective(SysUserEntity user) throws Exception {
        return null;
    }
}