package com.zd.school.jw.arrangecourse.service;

import java.util.List;
import java.util.Map;

import com.zd.core.model.ImportNotInfo;
import com.zd.core.service.BaseService;
import com.zd.school.jw.arrangecourse.model.CourseArrange;
import com.zd.school.plartform.system.model.User;


/**
 * 
 * ClassName: JwCourseArrangeService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 排课课程表实体Service接口类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface JwCourseArrangeService extends BaseService<CourseArrange> {

	public List<ImportNotInfo> doImportCourse(Map<String, List<String>> gccMap, User currentUser);


	public void doCouseUse(String[] idArr, String[] classIdArr, String[] sectionsArr, String xm);

}