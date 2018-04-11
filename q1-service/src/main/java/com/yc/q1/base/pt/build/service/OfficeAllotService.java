package com.yc.q1.base.pt.build.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.yc.q1.model.base.pt.basic.ClassStudent;
import com.yc.q1.model.base.pt.build.OfficeAllot;
import com.yc.q1.model.base.pt.build.StudentDorm;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: JwOfficeallotService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: JW_T_OFFICEALLOT实体Service接口类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface OfficeAllotService extends BaseService<OfficeAllot> {
	/**
	 * 分配门禁
	 */
	public boolean mjUserRight(String uuid, String roomId, String userId, StudentDorm dorm,ClassStudent classStu);
	public Boolean doAddRoom(OfficeAllot entity,Map hashMap,User currentUser)throws IllegalAccessException, InvocationTargetException;
	public Boolean doPushMessage(String roomId);
	public Boolean doDeleteOff(String delIds,String roomId,String tteacId);
	public void doOffSetOff(String roomIds);
}