package com.yc.q1.base.pt.build.service;

import java.lang.reflect.InvocationTargetException;

import com.yc.q1.base.pt.build.model.FuncRoomDefine;
import com.yc.q1.base.pt.build.model.RoomInfo;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: BuildFuncroomdefinService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: BUILD_T_FUNCROOMDEFIN实体Service接口类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface FuncRoomDefineService extends BaseService<FuncRoomDefine> {
	public FuncRoomDefine getByRoomId(String roomId);
	public void addFunRoom(RoomInfo entity, String id, String userCh) throws IllegalAccessException, InvocationTargetException ;
	public Boolean delFunRoom(RoomInfo roomInfo,String delId, String xm);
}