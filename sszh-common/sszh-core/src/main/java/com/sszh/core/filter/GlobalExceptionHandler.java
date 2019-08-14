package com.sszh.core.filter;


import com.sszh.core.enums.code.BaseExceptionCodeEnum;
import com.sszh.core.exception.BaseRuntimeException;
import com.sszh.core.exception.BusinessException;
import com.sszh.core.result.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截所有微服务controller抛出的异常信息
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${system.isFinalClient:false}")
    private Boolean isFinalClient;

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler
    public JSONResult<Object> defaultErrorHandler(HttpServletRequest req, Exception e, HttpServletResponse rep) {
        String stackMessage = null;
        logger.error("微服务Controller调用异常：---------------------------", e);
        String code = BaseExceptionCodeEnum.BASE_104.getCode();
        String message = BaseExceptionCodeEnum.BASE_104.getMsg();
        //捕获文件异常
        if (e instanceof MaxUploadSizeExceededException) {
            code = BaseExceptionCodeEnum.COMMON_1078.getCode();
            message = BaseExceptionCodeEnum.COMMON_1078.getMsg();
        }
        //捕获-运行时异常
        if (e instanceof RuntimeException) {
            //捕获业务异常
            if (e instanceof BusinessException) {
                BusinessException exception = (BusinessException) e;
                code = exception.getCode();
                message = e.getMessage();
                stackMessage = exception.getMessage();
            } else if (e instanceof BaseRuntimeException) {
                BaseRuntimeException exception = (BaseRuntimeException) e;
                code = exception.getCode();
                message = e.getMessage();
                stackMessage = exception.getStackMessage();
            } else {
                message = e.getMessage();
            }
        }
        //判断feign是否为web端
        if (null != isFinalClient && isFinalClient) {
            rep.setStatus(Integer.parseInt(BaseExceptionCodeEnum.REQUEST_200.getCode()));
        } else {
            rep.setStatus(Integer.parseInt(BaseExceptionCodeEnum.REQUEST_500.getCode()));
        }
        return new JSONResult<Object>(code, message, stackMessage);
    }
    
    
}