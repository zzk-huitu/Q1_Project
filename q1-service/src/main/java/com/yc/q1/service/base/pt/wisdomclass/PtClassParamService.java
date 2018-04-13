package com.yc.q1.service.base.pt.wisdomclass;

import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.wisdomclass.PtClassParam;


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
 
public interface PtClassParamService extends BaseService<PtClassParam> {

    public QueryResult<PtClassParam> list(Integer start, Integer limit, String sort, String filter, String whereSql,String orderSql,
           PtUser currentUser); 
}