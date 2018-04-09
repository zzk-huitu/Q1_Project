package com.zd.school.jw.eduresources.service;

import java.lang.reflect.InvocationTargetException;
import com.zd.core.service.BaseService;
import com.zd.school.jw.eduresources.model.ClassTeacher;
import com.zd.school.plartform.system.model.User;

/**
 * 
 * ClassName: JwClassteacherService Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 班主任信息实体Service接口类. date: 2016-08-22
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface JwClassteacherService extends BaseService<ClassTeacher> {

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

	public ClassTeacher doAddClassTeacher(ClassTeacher entity, User currentUser)
			throws IllegalAccessException, InvocationTargetException;

	public Boolean doDelete(String delIds, User currentUser);

	public boolean doDeleteByPK(String id);
}