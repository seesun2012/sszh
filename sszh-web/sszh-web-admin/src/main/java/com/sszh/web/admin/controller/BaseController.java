package com.sszh.web.admin.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    
    
    public String getSessionId(HttpServletRequest request) {
        String sessionId = request.getParameter("sessionId");
        return sessionId;
    }
    
}