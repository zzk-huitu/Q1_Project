package com.yc.q1.service.base.pt.basic;

import java.util.List;
import java.util.Map;

import com.yc.q1.model.base.pt.basic.PtCourseArrange;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.model.ImportNotInfo;
import com.zd.core.service.BaseService;


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
 
public interface PtCourseArrangeService extends BaseService<PtCourseArrange> {

	public List<ImportNotInfo> doImportCourse(Map<String, List<String>> gccMap, PtUser currentUser);


	public void doCouseUse(String[] idArr, String[] classIdArr, String[] sectionsArr, String xm);

}