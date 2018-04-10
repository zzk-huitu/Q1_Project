package com.yc.q1.base.pt.basic.service;

import java.lang.reflect.InvocationTargetException;

import com.yc.q1.base.pt.basic.model.CourseTeacher;
import com.yc.q1.base.pt.pojo.CommTree;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: JwCourseteacherService Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 教师任课信息(JW_T_COURSETEACHER)实体Service接口类. date:
 * 2016-08-26
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface JwCourseteacherService extends BaseService<CourseTeacher> {

    /**
     * 
     * doAddCourseTeacher:设置任课教师.
     * 
     * @author luoyibo
     * @param semester
     *            任课学期
     * @param currentUser
     *            当前操作者
     * @return String
     * @throws IllegalArgumentException 
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws @since
     *             JDK 1.8
     */
    public Boolean doAddCourseTeacher(String jsonData, User currentUser) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException;

    public Boolean doDelCourseTeacher(String delIds, User currentUser) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
    
    public QueryResult<CourseTeacher> getClassCourseTeacherList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, String classId, Integer classLevel);   
    
	/**
	 * 批量更新周课时
	 */
	public String updateZjsByClassId(String classId,String courseid,int zjs);

	public Integer doReplaceCourseTeacher(String jctUuid, String teacherId, User sysser) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	public void updatePubliceClass(String classId, String courseId, String publicClassId);
	
	/**
	 * 获取用户有权限的学科树
	 * @param node
	 * @param currentUser
	 * @return
	 */
	public CommTree getUserRightDeptDisciplineTree(String rootId, User currentUser);
}