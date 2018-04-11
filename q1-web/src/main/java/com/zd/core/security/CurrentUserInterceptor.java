package com.zd.core.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yc.q1.base.pt.system.service.UserService;
import com.yc.q1.model.base.pt.system.PtUser;

@Component
public class CurrentUserInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService sysUserService;

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
            ModelAndView modelAndView) throws Exception {
        // Add the current user into the request
        final String currentUserId = (String) SecurityUtils.getSubject().getPrincipal();
        PtUser currentUser = sysUserService.get(currentUserId);
        if (currentUser != null) {
            httpServletRequest.setAttribute("currentUser", currentUser);
        }
    }
}
