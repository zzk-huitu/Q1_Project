package com.yc.q1.base.pt.build.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yc.q1.model.base.pt.build.RoomInfo;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: BuildRoominfoService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 教室信息实体Service接口类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface RoomInfoService extends BaseService<RoomInfo> {
	public Integer getCount(String roomName);

	public Boolean doBatchAddRoom(RoomInfo roominfo, User currentUser);

	public Boolean doDeleteRoomDefine(String delIds, String xm, Map<String, Object> hashMap);

	public Boolean doAddRoomDefine(RoomInfo entity, HttpServletRequest request, String userCh) throws IllegalAccessException, InvocationTargetException;

}