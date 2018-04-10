package com.yc.q1.base.pt.basic.service;

import com.yc.q1.base.pt.basic.model.ClassStudent;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: JwClassstudentService Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 学生分班信息(JW_T_CLASSSTUDENT)实体Service接口类. date:
 * 2016-08-25
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface ClassStudentService extends BaseService<ClassStudent> {

    /**
     * 
     * getDeptUser:查询指定班级的学生
     *
     * @author luoyibo
     * @param start
     *            记录起始位置
     * @param limit
     *            查询的最大记录条数
     * @param sort
     *            排序条件
     * @param filter
     *            过滤条件
     * @param isDelete
     * @param deptId
     * @return QueryResult<SysUser>
     * @throws @since
     *             JDK 1.8
     */
    public QueryResult<ClassStudent> getclassStudent(Integer start, Integer limit, String sort, String filter,
            Boolean isDelete, String claiId, User currentUser);
}