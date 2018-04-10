package com.yc.q1.base.pt.build.service;

import java.lang.reflect.InvocationTargetException;

import com.yc.q1.base.pt.build.model.OfficeDefine;
import com.yc.q1.base.pt.build.model.RoomInfo;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: BuildOfficeService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 办公室信息实体Service接口类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface BaseOfficeDefineService extends BaseService<OfficeDefine> {
	public OfficeDefine getByRoomId(String roomId);
	public void addOffRoom(RoomInfo entity, String id, String userCh) throws IllegalAccessException, InvocationTargetException;
	public Boolean delOffRoom(RoomInfo roomInfo,String delId, String xm);
	
}