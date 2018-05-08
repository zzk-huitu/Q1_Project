package com.yc.q1.service.base.pt.system;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserAccountBind;


/**
 * 
 * ClassName: BizTJobService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 岗位信息实体Service接口类.
 * date: 2016-05-16
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtUserAccountBindService extends BaseService<PtUserAccountBind> {

	public boolean doAddAccount(String userId, String ids, PtUser currentUser);
	
	public boolean doDeleteAccount(String delIds);
}