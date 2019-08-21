package com.sszh.web.admin.controller.login;

import com.sszh.common.util.string.StringUtils;
import com.sszh.core.code.BaseExceptionCodeEnum;
import com.sszh.core.exception.BaseException;
import com.sszh.core.exception.BaseBusinessException;
import com.sszh.core.result.JSONResult;
import com.sszh.server.sso.api.entity.SysUserEntity;
import com.sszh.server.sso.api.feign.interfaces.SysUserClient;
import com.sszh.web.admin.cache.AdminCacheFactory;
import com.sszh.web.admin.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 登陆-主控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {


    @Resource
    private SysUserClient sysUserClient;
    @Autowired
    private AdminCacheFactory adminCacheFactory;

    
    
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
    
    /**
     * 指定跳登录页
     */
    @RequestMapping(value = "")
    public String loginLogin() {
        return "login/login";
    }

    /**
     * 登陆校验
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult<String> doLogin(HttpServletRequest request, @RequestParam(name = "account") String account, @RequestParam(name = "passWord") String passWord, @RequestParam(name = "vCode") String vCode) throws Exception {
        // 校验参数
        if (StringUtils.isEmpty(account)) {
            throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_10000.getCode(), "登陆用户名不能为空");
        }
        if (StringUtils.isEmpty(passWord)) {
            throw new BaseException(BaseExceptionCodeEnum.BASE_10000.getCode(), "登陆密码不能为空");
        }
        if (StringUtils.isEmpty(vCode)) {
            throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_10000.getCode(), "请输入验证码");
        }
        //获取sessionId
        String sessionId = request.getSession().getId();
        // 校验验证码
        String sessionCode = adminCacheFactory.getSystemCache().getYanZhengMa(sessionId);
        if (!(sessionCode.equals(vCode))) {
            throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_10000.getCode(), "验证码不正确");
        }
        SysUserEntity user = sysUserClient.loginQuery(account);
        if (null == user) {
            throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_105.getCode(), "用户不存在");
        }
        if (!user.getPassWord().equals(passWord)) {
            throw new BaseBusinessException(BaseExceptionCodeEnum.BASE_105.getCode(), "密码不正确");
        }
        //踢出异地登陆
        adminCacheFactory.getUserCache().delUserSessionInfo(sessionId);
        //缓存用户信息
        adminCacheFactory.getUserCache().setUserSessionInfo(user, sessionId);
        //删除验证码缓存
        adminCacheFactory.getSystemCache().delYanZhengMa(sessionId);
        return JSONResult.newSuccessResult(request.getContextPath() + "/");
    }


    /**
     * 退出登陆
     */
    @RequestMapping(value = "/doOut", method = RequestMethod.GET)
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminCacheFactory.getUserCache().delUserSessionInfo(request.getSession().getId());      //删除缓存
        response.sendRedirect(request.getContextPath() + "/login");
    }


}
