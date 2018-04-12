package com.yc.q1.service.base.pt.build;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.build.PtTeacherDorm;
import com.yc.q1.model.base.pt.system.PtUser;

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
 
public interface PtTeacherDormService extends BaseService<PtTeacherDorm> {
	
	public QueryResult<PtTeacherDorm> list(Integer start, Integer limit, String sort, String filter, String whereSql,String orderSql,
			PtUser currentUser); 
	public Boolean doOut(String ids,PtUser currentUser);
	public Boolean doAddDormTea(PtTeacherDorm entity,Map hashMap,HttpServletRequest request,PtUser currentUser)throws IllegalAccessException, InvocationTargetException;
    public Boolean doDelete(String delIds);
    public void doSettingOff(String roomIds);
}