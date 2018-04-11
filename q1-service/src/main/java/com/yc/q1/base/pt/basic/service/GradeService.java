package com.yc.q1.base.pt.basic.service;

import com.yc.q1.model.base.pt.basic.PtGrade;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: JwTGradeService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学校年级信息实体Service接口类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface GradeService extends BaseService<PtGrade> {

    public QueryResult<PtGrade> getGradeList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, PtUser currentUser);
}