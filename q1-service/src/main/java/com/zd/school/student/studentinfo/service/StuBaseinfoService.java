package com.zd.school.student.studentinfo.service;

import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;
import com.zd.school.plartform.system.model.User;
import com.zd.school.student.studentinfo.model.StudentBaseInfo;

/**
 * 
 * ClassName: StuBaseinfoService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学生基本信息实体Service接口类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface StuBaseinfoService extends BaseService<StudentBaseInfo> {

    public QueryResult<StudentBaseInfo> getStudentList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, String claiId, User currentUser);
    
    public StudentBaseInfo doAddStudent(StudentBaseInfo stuBaseInfo, User currentUser/*, String deptJobId*/);
}