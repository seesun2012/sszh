package com.sszh.web.admin.controller.system;

import com.sszh.web.admin.cache.AdminCacheFactory;
import com.sszh.web.admin.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 运营端首页-主控制器
 */
@Controller
public class IndexController extends BaseController {
    
    @Autowired
    private AdminCacheFactory adminCacheFactory;

    /**
     * 首页调转
     */
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String ip = request.getRemoteAddr();
        adminCacheFactory.getUserCache().getUserSessionInfo(sessionId, ip);
        return "index/index";
    }
    
    
    
}