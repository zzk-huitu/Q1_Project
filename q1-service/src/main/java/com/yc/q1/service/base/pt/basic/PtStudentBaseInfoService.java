package com.yc.q1.service.base.pt.basic;

import com.yc.q1.model.base.pt.basic.PtStudentBaseInfo;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: StuBaseinfoService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学生基本信息实体Service接口类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface PtStudentBaseInfoService extends BaseService<PtStudentBaseInfo> {

    public QueryResult<PtStudentBaseInfo> getStudentList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, String claiId, PtUser currentUser);
    
    public PtStudentBaseInfo doAddStudent(PtStudentBaseInfo stuBaseInfo, PtUser currentUser/*, String deptJobId*/);
}