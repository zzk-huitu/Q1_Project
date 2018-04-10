package com.yc.q1.base.pt.build.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yc.q1.base.pt.build.model.TeacherDorm;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: DormStudentdormService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: (DormTeacherDorm)实体Service接口类.
 * date: 2016-08-26
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface TeacherDormService extends BaseService<TeacherDorm> {
	
	public QueryResult<TeacherDorm> list(Integer start, Integer limit, String sort, String filter, String whereSql,String orderSql,
			User currentUser); 
	public Boolean doOut(String ids,User currentUser);
	public Boolean doAddDormTea(TeacherDorm entity,Map hashMap,HttpServletRequest request,User currentUser)throws IllegalAccessException, InvocationTargetException;
    public Boolean doDelete(String delIds);
    public void doSettingOff(String roomIds);
}