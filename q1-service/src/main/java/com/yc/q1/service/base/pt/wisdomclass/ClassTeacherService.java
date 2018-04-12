package com.yc.q1.service.base.pt.wisdomclass;

import java.lang.reflect.InvocationTargetException;

import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.wisdomclass.PtClassTeacher;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: JwClassteacherService Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 班主任信息实体Service接口类. date: 2016-08-22
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface ClassTeacherService extends BaseService<PtClassTeacher> {

	/**
//	 * 
//	 * getClassLeader:获取指定学生的所在班级的班主任
//	 *
//	 * @author luoyibo
//	 * @param userId
//	 * @return String
//	 * @throws @since
//	 *             JDK 1.8
//	 */
//	public String getClassLeader(String userId);
//
//	/**
//	 * 获取指定学生的所有班主任
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	public String getClassLeaderList(String userId);

	public PtClassTeacher doAddClassTeacher(PtClassTeacher entity, PtUser currentUser)
			throws IllegalAccessException, InvocationTargetException;

	public Boolean doDelete(String delIds, PtUser currentUser);

	public boolean doDeleteByPK(String id);
}