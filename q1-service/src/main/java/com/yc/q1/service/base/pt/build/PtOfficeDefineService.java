package com.yc.q1.service.base.pt.build;

import java.lang.reflect.InvocationTargetException;

import com.yc.q1.model.base.pt.build.PtOfficeDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
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

public interface PtOfficeDefineService extends BaseService<PtOfficeDefine> {
	public PtOfficeDefine getByRoomId(String roomId);
	public void addOffRoom(PtRoomInfo entity, String id, String userCh) throws IllegalAccessException, InvocationTargetException;
	public Boolean delOffRoom(PtRoomInfo roomInfo,String delId, String xm);
	
}