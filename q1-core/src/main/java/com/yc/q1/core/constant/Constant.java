/**
 * Project Name:zd-core
 * File Name:Constant.java
 * Package Name:com.zd.core.constant
 * Date:2016年6月1日下午2:14:23
 * Copyright (c) 2016 ZDKJ All Rights Reserved.
 *
*/

package com.yc.q1.core.constant;

/**
 * ClassName:Constant Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2016年6月1日 下午2:14:23
 * 
 * @author luoyibo
 * @version
 * @since JDK 1.8
 * @see
 */
public interface Constant {
    public static final String SESSION_SYS_USER = "SESSION_SYS_USER";
    public static final String SESSION_ROLE_KEY = "SESSION_ROLE_KEY";
    public static final String SESSION_SYS_ISADMIN = "SESSION_SYS_ISADMIN";	//是否为超级管理员
    public static final String SESSION_SYS_ISSCHOOLADMIN = "SESSION_SYS_ISSCHOOLADMIN";	//是否为超级管理员
    public static final String SESSION_SYS_AUTH = "SESSION_SYS_AUTH";	//后端接口权限，前缀+后缀
    public static final String SESSION_SYS_BTN = "SESSION_SYS_BTN";		//前端按钮权限，菜单编码+按钮ref值
    /**
     * 费用支出类型定义,对应字典表中的字典码为 FEEINOUT
     */
    public static final String FEE_TYPE_IN = "01"; //收入 

    public static final String FEE_TYPE_OUT = "02"; //支出
}