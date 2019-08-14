package com.sszh.web.admin.controller.login;

import com.sszh.common.util.string.StringUtil;
import com.sszh.core.enums.code.BaseExceptionCodeEnum;
import com.sszh.core.exception.BaseException;
import com.sszh.core.exception.BusinessException;
import com.sszh.core.result.JSONResult;
import com.sszh.server.sso.api.entity.UserBean;
import com.sszh.web.admin.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆-主控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

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
    public JSONResult<String> doLogin(HttpServletRequest request, @RequestParam(name = "userName") String userName, @RequestParam(name = "passWord") String passWord, @RequestParam(name = "vCode") String vCode) throws Exception {
        // 校验参数
        if (StringUtil.isEmpty(userName)) {
            throw new BusinessException(BaseExceptionCodeEnum.BASE_10000.getCode(), "登陆用户名不能为空");
        }
        if (StringUtil.isEmpty(passWord)) {
            throw new BaseException(BaseExceptionCodeEnum.BASE_10000.getCode(), "登陆密码不能为空");
        }
        if (StringUtil.isEmpty(vCode)) {
            throw new BusinessException(BaseExceptionCodeEnum.BASE_10000.getCode(), "请输入验证码");
        }
        // 校验验证码
        HttpSession session = request.getSession();                                 //初始化Session
        String sessionCode = (String) session.getAttribute("safecode");             //获取的验证码
        if (!(sessionCode.equals(vCode))) {
            throw new BusinessException(BaseExceptionCodeEnum.BASE_10000.getCode(), "验证码不正确");
        }
        UserBean userInfo = new UserBean();
        userInfo.setId(123L);
        userInfo.setName(userName);
        request.getSession().setAttribute("userInfo", userInfo);
        // 校验缓存
        return JSONResult.newSuccessResult(request.getContextPath() + "/");
    }


    /**
     * 退出登陆
     */
    @RequestMapping(value = "/doOut", method = RequestMethod.GET)
    public void loginOut(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //删除缓存
        Object sesionStr = httpRequest.getSession().getAttribute("userInfo");                        //获取用户信息
        httpRequest.getSession().setAttribute("userInfo", null);
        sesionStr = httpRequest.getSession().getAttribute("userInfo");                        //获取用户信息
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
    }


}
