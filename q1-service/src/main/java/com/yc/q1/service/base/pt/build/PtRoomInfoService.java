package com.yc.q1.service.base.pt.build;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.model.base.pt.system.PtUser;
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
 
public interface PtRoomInfoService extends BaseService<PtRoomInfo> {
	public Integer getCount(String roomName);

	public Boolean doBatchAddRoom(PtRoomInfo roominfo, PtUser currentUser);

	public Boolean doDeleteRoomDefine(String delIds, String xm, Map<String, Object> hashMap);

	public Boolean doAddRoomDefine(PtRoomInfo entity, HttpServletRequest request, String userCh) throws IllegalAccessException, InvocationTargetException;

}