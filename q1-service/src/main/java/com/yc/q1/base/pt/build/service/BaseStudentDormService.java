package com.yc.q1.base.pt.build.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.yc.q1.base.pt.build.model.ClassDormAllot;
import com.yc.q1.base.pt.build.model.StudentDorm;
import com.yc.q1.base.pt.pojo.CommTree;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: DormStudentdormService Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: (DORM_T_STUDENTDORM)实体Service接口类. date:
 * 2016-08-26
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface BaseStudentDormService extends BaseService<StudentDorm> {

	//public CommTree getCommTree(String rootId, String deptType, SysUser currentUser);
	public List<StudentDorm> oneKeyList(StudentDorm entity,String whereSql);
	public Boolean doOneKeyAllotDorm(String gradId,String boyId,String girlId,User currentUser);
	public Integer doDormAutoAllot(String claiId,User currentUser);
	public Boolean doDormHandAllot(StudentDorm entity,Map hashMap,User currentUser)throws IllegalAccessException, InvocationTargetException ;
	public List<ClassDormAllot>  mixDormList(ClassDormAllot entity);
	public List<ClassDormAllot>  emptyMixDormList(ClassDormAllot entity);
	public Boolean doPushMessage(String classId);
	public Boolean doDeleteDorm(String[] delIds, String userId);
	public Integer doUpdateBedArkNum(String[] list, String userId);
	public Boolean doAddClassDorm(String classId, String dormIds, User currentUser);
	public CommTree getUserRightDeptClassTree(String rootId, User currentUser);
}