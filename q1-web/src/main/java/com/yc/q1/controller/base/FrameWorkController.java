/**
 * Project Name:school-web
 * File Name:FrameWorkController.java
 * Package Name:com.zd.core.controller.core
 * Date:2016年6月1日下午2:15:33
 * Copyright (c) 2016 ZDKJ All Rights Reserved.
 *
*/

package com.yc.q1.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.model.base.pt.system.PtUser;

/**
 * ClassName:FrameWorkController Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON. Date: 2016年6月1日 下午2:15:33
 * 
 * @author luoyibo
 * @version
 * @since JDK 1.8
 * @see
 */
public abstract class FrameWorkController<E> extends BaseController<E> implements Constant {

    public PtUser getCurrentSysUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return (PtUser) request.getSession().getAttribute(SESSION_SYS_USER);
    }
    
    public Integer getIsAdmin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return (Integer) request.getSession().getAttribute(SESSION_SYS_ISADMIN);
    }
}
