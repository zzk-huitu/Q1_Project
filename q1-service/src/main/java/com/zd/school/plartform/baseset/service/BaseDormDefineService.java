package com.zd.school.plartform.baseset.service;

import java.lang.reflect.InvocationTargetException;

import com.zd.core.service.BaseService;
import com.zd.school.build.define.model.DormDefine;
import com.zd.school.build.define.model.RoomInfo;
import com.zd.school.plartform.system.model.User;

/**
 * 
 * ClassName: BuildOfficeService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 宿舍定义 date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface BaseDormDefineService extends BaseService<DormDefine> {
	public DormDefine getByRoomId(String roomId);
    public void addDormRoom(RoomInfo entity, DormDefine dormRoom,String id, String userCh) throws IllegalAccessException, InvocationTargetException;
	public Boolean delDormRoom(RoomInfo roomInfo,String delIds, String xm);
	public DormDefine doUpdateEntity(DormDefine entity, User currentUser) throws Exception;
}