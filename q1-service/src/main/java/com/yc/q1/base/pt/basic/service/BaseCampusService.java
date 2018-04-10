package com.yc.q1.base.pt.basic.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.yc.q1.base.pt.basic.model.Campus;
import com.yc.q1.base.pt.build.model.RoomInfo;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: BaseCampusService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 校区信息实体Service接口类. date: 2016-08-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface BaseCampusService extends BaseService<Campus> {

    public Campus doAdd(Campus entity, User currentUser)
            throws IllegalAccessException, InvocationTargetException;

    public Campus doUpdate(Campus entity, User currentUser)
            throws IllegalAccessException, InvocationTargetException;

    public boolean doDelete(String delIds, User currentUser,Map hashMap)
            throws IllegalAccessException, InvocationTargetException;

	public String getCampusIdByRoom(RoomInfo roominfo);
}