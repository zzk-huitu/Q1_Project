package com.yc.q1.service.base.pt.system;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserDeptRight;


/**
 * 
 * ClassName: SysDeptrightService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 用户权限部门(SYS_T_DEPTRIGHT)实体Service接口类.
 * date: 2017-04-06
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtUserDeptRightService extends BaseService<PtUserDeptRight> {

	boolean doDelete(String delIds);

	Boolean doUserRightDept(String userIds, String deptIds, PtUser currentUser);

	

	void doUpdateRightType(String uuid, String rightType, String userId);


	
}