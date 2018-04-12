package com.yc.q1.dao.base.pt.system.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.base.pt.system.PtRoleDao;
import com.yc.q1.model.base.pt.system.PtRole;

/**
 * 
 * ClassName: BaseTRoleDaoImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 角色管理实体Dao接口实现类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Repository("PtRoleDao")	//默认为ptRoleDaoImpl
public class PtRoleDaoImpl extends BaseDaoImpl<PtRole> implements PtRoleDao {
}