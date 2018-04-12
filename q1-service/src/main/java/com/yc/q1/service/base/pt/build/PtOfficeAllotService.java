package com.yc.q1.service.base.pt.build;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.yc.q1.model.base.pt.basic.PtClassStudent;
import com.yc.q1.model.base.pt.build.PtOfficeAllot;
import com.yc.q1.model.base.pt.build.PtStudentDorm;
import com.yc.q1.model.base.pt.system.PtUser;
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
 
public interface PtOfficeAllotService extends BaseService<PtOfficeAllot> {
	/**
	 * 分配门禁
	 */
	public boolean mjUserRight(String uuid, String roomId, String userId, PtStudentDorm dorm,PtClassStudent classStu);
	public Boolean doAddRoom(PtOfficeAllot entity,Map hashMap,PtUser currentUser)throws IllegalAccessException, InvocationTargetException;
	public Boolean doPushMessage(String roomId);
	public Boolean doDeleteOff(String delIds,String roomId,String tteacId);
	public void doOffSetOff(String roomIds);
}