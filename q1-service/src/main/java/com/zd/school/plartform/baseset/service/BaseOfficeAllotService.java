package com.zd.school.plartform.baseset.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.zd.core.service.BaseService;
import com.zd.school.build.allot.model.OfficeAllot;
import com.zd.school.build.allot.model.StudentDorm;
import com.zd.school.plartform.system.model.User;
import com.zd.school.student.studentclass.model.ClassStudent;


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
 
public interface BaseOfficeAllotService extends BaseService<OfficeAllot> {
	/**
	 * 分配门禁
	 */
	public boolean mjUserRight(String uuid, String roomId, String userId, StudentDorm dorm,ClassStudent classStu);
	public Boolean doAddRoom(OfficeAllot entity,Map hashMap,User currentUser)throws IllegalAccessException, InvocationTargetException;
	public Boolean doPushMessage(String roomId);
	public Boolean doDeleteOff(String delIds,String roomId,String tteacId);
	public void doOffSetOff(String roomIds);
}