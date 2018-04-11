package com.yc.q1.base.pt.wisdomclass.service;

import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.model.base.pt.wisdomclass.ClassParam;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: EccClassparamService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班牌参数设置表(ECC_T_CLASSPARAM)实体Service接口类.
 * date: 2016-11-28
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface ClassParamService extends BaseService<ClassParam> {

    public QueryResult<ClassParam> list(Integer start, Integer limit, String sort, String filter, String whereSql,String orderSql,
           User currentUser); 
}