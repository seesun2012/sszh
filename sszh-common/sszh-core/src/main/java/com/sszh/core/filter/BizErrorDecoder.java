package com.sszh.core.filter;


import com.alibaba.fastjson.JSONObject;
import com.sszh.core.exception.BaseBusinessException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 处理500请求错误
 */
@Configuration
public class BizErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        try {
            String jsonStr = Util.toString(response.body().asReader());
            JSONObject json = JSONObject.parseObject(jsonStr);
            return new BaseBusinessException(json.getString("status"), json.getString("message"));
        } catch (IOException e) {
            return new BaseBusinessException("104","系统异常");
        }
    }
    
}