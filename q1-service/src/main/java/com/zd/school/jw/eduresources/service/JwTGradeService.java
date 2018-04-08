package com.zd.school.jw.eduresources.service;

import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;
import com.zd.school.jw.eduresources.model.Grade;
import com.zd.school.plartform.system.model.User;

/**
 * 
 * ClassName: JwTGradeService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学校年级信息实体Service接口类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface JwTGradeService extends BaseService<Grade> {

    public QueryResult<Grade> getGradeList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, User currentUser);
}