package com.yc.q1.base.pt.build.service;

import java.lang.reflect.InvocationTargetException;

import com.yc.q1.model.base.pt.build.ClassRoomDefine;
import com.yc.q1.model.base.pt.build.RoomInfo;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: BuildClassroomService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 教室信息实体Service接口类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface ClassRoomDefineService extends BaseService<ClassRoomDefine> {
	public ClassRoomDefine getByRoomId(String roomId);
	public void addClassRoom(RoomInfo entity, String id, String userCh) throws IllegalAccessException, InvocationTargetException;
	public Boolean delClassRoom(RoomInfo roomInfo,String delId, String xm);
}