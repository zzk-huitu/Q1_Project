package com.yc.q1.service.base.pt.basic;

import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.basic.PtGrade;
import com.yc.q1.model.base.pt.basic.PtGradeClass;
import com.yc.q1.model.base.pt.system.PtUser;

/**
 * 
 * ClassName: JwTGradeclassService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学校班级信息实体Service接口类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface PtGradeClassService extends BaseService<PtGradeClass> {
    /**
     * 根据班级ID得到年级对象
     * 
     * @param claiId
     * @return
     * @author huangzc
     */
    public PtGrade findJwTGradeByClassId(String classId);

    public QueryResult<PtGradeClass> getGradeClassList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, PtUser currentUser);
    
    public QueryResult<PtGradeClass> getGradeClassList(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, PtUser currentUser,String classId,String classLevel);    
}