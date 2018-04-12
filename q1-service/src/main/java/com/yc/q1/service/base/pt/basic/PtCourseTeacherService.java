package com.yc.q1.service.base.pt.basic;

import java.lang.reflect.InvocationTargetException;

import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.basic.PtCourseTeacher;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.CommTree;

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

public interface PtCourseTeacherService extends BaseService<PtCourseTeacher> {

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
    public Boolean doAddCourseTeacher(String jsonData, PtUser currentUser) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException;

    public Boolean doDelCourseTeacher(String delIds, PtUser currentUser) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
    
    public QueryResult<PtCourseTeacher> getClassCourseTeacherList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, String classId, Integer classLevel);   
    
	/**
	 * 批量更新周课时
	 */
	public String updateZjsByClassId(String classId,String courseid,int zjs);

	public Integer doReplaceCourseTeacher(String jctUuid, String teacherId, PtUser sysser) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	public void updatePubliceClass(String classId, String courseId, String publicClassId);
	
	/**
	 * 获取用户有权限的学科树
	 * @param node
	 * @param currentUser
	 * @return
	 */
	public CommTree getUserRightDeptDisciplineTree(String rootId, PtUser currentUser);
}