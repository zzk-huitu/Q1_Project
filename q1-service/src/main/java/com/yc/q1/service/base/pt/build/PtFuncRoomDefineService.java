package com.yc.q1.service.base.pt.build;

import java.lang.reflect.InvocationTargetException;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.build.PtFuncRoomDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;


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
 
public interface PtFuncRoomDefineService extends BaseService<PtFuncRoomDefine> {
	public PtFuncRoomDefine getByRoomId(String roomId);
	public void addFunRoom(PtRoomInfo entity, String id, String userCh) throws IllegalAccessException, InvocationTargetException ;
	public Boolean delFunRoom(PtRoomInfo roomInfo,String delId, String xm);
}