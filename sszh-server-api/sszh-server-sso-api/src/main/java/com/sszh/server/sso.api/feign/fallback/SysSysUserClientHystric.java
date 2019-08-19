package com.sszh.server.sso.api.feign.fallback;


import com.sszh.core.code.BaseExceptionCodeEnum;
import com.sszh.core.exception.BaseBusinessException;
import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.server.sso.api.feign.interfaces.SysUserClient;
import org.springframework.stereotype.Component;

@Component
public class SysSysUserClientHystric implements SysUserClient {

    @Override
    public SysUserEntity loginQuery(String account) throws Exception {
        throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_105.getCode(), "服务器正在重启中，请稍后重试...");
    }

    @Override
    public SysUserEntity selectByPrimaryKey(String id) throws Exception {
        throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_105.getCode(), "服务器正在重启中，请稍后重试...");
    }

    @Override
    public Integer insertSelective(SysUserEntity user) throws Exception {
        throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_105.getCode(), "服务器正在重启中，请稍后重试...");
    }
}