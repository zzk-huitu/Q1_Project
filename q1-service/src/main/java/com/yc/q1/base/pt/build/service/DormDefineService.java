package com.yc.q1.base.pt.build.service;

import java.lang.reflect.InvocationTargetException;

import com.yc.q1.model.base.pt.build.PtDormDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: BuildOfficeService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 宿舍定义 date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface DormDefineService extends BaseService<PtDormDefine> {
	public PtDormDefine getByRoomId(String roomId);
    public void addDormRoom(PtRoomInfo entity, PtDormDefine dormRoom,String id, String userCh) throws IllegalAccessException, InvocationTargetException;
	public Boolean delDormRoom(PtRoomInfo roomInfo,String delIds, String xm);
	public PtDormDefine doUpdateEntity(PtDormDefine entity, PtUser currentUser) throws Exception;
}