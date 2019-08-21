package com.sszh.server.sso.api.feign.fallback;


import com.sszh.core.code.BaseExceptionCodeEnum;
import com.sszh.core.exception.BaseBusinessException;
import com.sszh.server.sso.api.entity.SysMenuEntity;
import com.sszh.server.sso.api.feign.interfaces.SysMenuClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysMenuClientHystric implements SysMenuClient {

    @Override
    public List<SysMenuEntity> selectAll() throws Exception {
        throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_105.getCode(), "服务器正在重启中，请稍后重试...");
    }
    
}