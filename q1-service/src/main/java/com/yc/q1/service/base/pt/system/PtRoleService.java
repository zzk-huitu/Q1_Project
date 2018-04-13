package com.yc.q1.service.base.pt.system;

import java.util.Map;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtRole;

/**
 * 
 * ClassName: BaseTRoleService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 角色管理实体Service接口类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface PtRoleService extends BaseService<PtRole> {

	boolean doDelete(String delIds, String isdelete, String xm);
    //public List<SysRole> doQueryForIn(String hql, Integer start, Integer limit,Object[] objs);

	Boolean doDeleteRoleUser(String ids, String userId);

	Boolean doAddRoleUser(String ids, String userId);
	Boolean doDeleteRole(String ids,  Map hashMap,String xm);
}