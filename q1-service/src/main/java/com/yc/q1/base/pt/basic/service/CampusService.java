package com.yc.q1.base.pt.basic.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.yc.q1.model.base.pt.basic.PtCampus;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.model.base.pt.system.PtUser;
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

public interface CampusService extends BaseService<PtCampus> {

    public PtCampus doAdd(PtCampus entity, PtUser currentUser)
            throws IllegalAccessException, InvocationTargetException;

    public PtCampus doUpdate(PtCampus entity, PtUser currentUser)
            throws IllegalAccessException, InvocationTargetException;

    public boolean doDelete(String delIds, PtUser currentUser,Map hashMap)
            throws IllegalAccessException, InvocationTargetException;

	public String getCampusIdByRoom(PtRoomInfo roominfo);
}