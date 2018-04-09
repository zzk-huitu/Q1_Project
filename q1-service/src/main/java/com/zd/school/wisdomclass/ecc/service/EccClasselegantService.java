package com.zd.school.wisdomclass.ecc.service;

import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;
import com.zd.school.jw.ecc.model.ClassMien;
import com.zd.school.plartform.system.model.User;

/**
 * 
 * ClassName: EccClassparamService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 班牌参数设置表(ECC_T_CLASSPARAM)实体Service接口类. date:
 * 2016-11-28
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface EccClasselegantService extends BaseService<ClassMien> {

	public QueryResult<ClassMien> list(Integer start, Integer limit, String sort, String filter, String whereSql,
			String orderSql, User currentUser);
}