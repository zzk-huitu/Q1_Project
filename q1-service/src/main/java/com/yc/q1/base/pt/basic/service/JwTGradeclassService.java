package com.yc.q1.base.pt.basic.service;

import com.yc.q1.base.pt.basic.model.Grade;
import com.yc.q1.base.pt.basic.model.GradeClass;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: JwTGradeclassService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学校班级信息实体Service接口类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface JwTGradeclassService extends BaseService<GradeClass> {
    /**
     * 根据班级ID得到年级对象
     * 
     * @param claiId
     * @return
     * @author huangzc
     */
    public Grade findJwTGradeByClassId(String classId);

    public QueryResult<GradeClass> getGradeClassList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, User currentUser);
    
    public QueryResult<GradeClass> getGradeClassList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, User currentUser,String classId,String classLevel);    
}