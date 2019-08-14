package com.sszh.web.admin.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sszh.common.util.string.StringUtil;
import com.sszh.server.sso.api.entity.UserBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 　　　　　　　   ┏┓　   ┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃				God beast body, code no BUG
 * 　　　　　　　　　┃　　　┃ +				神兽护体,代码无BUG
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 *
 * @title:      电商-Ajax请求过滤器（未登录跳转登陆界面）
 * @version     v1.0.0
 * @author      研发中心-seesun2012
 * @date        2019年02月20日 下午14:26:23  周三
 */

@WebFilter(filterName = "test", urlPatterns = "/*")
public class LoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    
    private static final Set<String> UN_FILTER_URL = new HashSet<>();
    private static final Set<String> UN_FILTER_FOLDER = new HashSet<>();

    private String[] unFilterUrl;
    private String[] unFilterFolder;

    // 构造函数获取配置
    public LoginFilter(String[] unFilterUrl, String[] unFilterFolder) {
        this.unFilterUrl = unFilterUrl;
        this.unFilterFolder = unFilterFolder;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("----------------------->过滤器被创建");
        String[] arr = unFilterUrl;
        for (String str : arr) {
            logger.info("----------------------->不需要被过滤的路径：" + str);
            UN_FILTER_URL.add(str.trim());
        }
        arr = unFilterFolder;
        for (String str : arr) {
            logger.info("----------------------->不需要被过滤的文件、文件夹：" + str);
            UN_FILTER_FOLDER.add(str.trim());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURI();
        // 是否登陆
        HttpSession session = httpRequest.getSession();                             //初始化Session
        Object sesionStr = session.getAttribute("userInfo");                        //获取用户信息
        UserBean user = null;
        if (null != sesionStr) {
            user = (UserBean) sesionStr;
        }
        if (null != user && null != user.getId()) {
            chain.doFilter(request, response);
        } else {
            for (String str : UN_FILTER_URL) {
                if (str.equals(url)){
                    chain.doFilter(request, response);
                    return;
                }
            }
            for (String str : UN_FILTER_FOLDER) {
                if (url.lastIndexOf(str) >= 0){
                    chain.doFilter(request, response);
                    return;
                }
            }
            logger.info("--------------------->过滤器：请求地址" + url);
            String requestedWith = httpRequest.getHeader("X-Requested-With");               // 如果是Ajax请求，特殊处理
            if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + httpRequest.getContextPath() + "/";		// 将要重定向WEB地址
                httpResponse.setHeader("SESSIONS_TATUS", "TIMEOUT");					    // 返回特定数据（头部信息）
                httpResponse.setHeader("CONTENT_PATH", basePath + "login");			        // 返回特定数据（首页登陆地址）
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);				    // 403 禁止：状态代码（403），指示服务器了解请求，但拒绝履行。
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");         // 重定向到登陆界面
            }
        }
    }

    @Override
    public void destroy() {
        //logger.info("----------------------->过滤器被销毁");
    }

}
