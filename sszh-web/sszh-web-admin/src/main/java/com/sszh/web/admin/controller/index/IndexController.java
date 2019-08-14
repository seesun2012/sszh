package com.sszh.web.admin.controller.index;

import com.sszh.web.admin.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 运营端首页-主控制器
 */
@Controller
public class IndexController extends BaseController {

    /**
     * 首页调转
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index/index";
    }
    
}