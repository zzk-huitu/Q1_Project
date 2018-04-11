package com.yc.q1.base.mj.service;

import com.yc.q1.model.base.mj.MjUserRight;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: MjUserrightService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 门禁权限表(MJ_UserRight)实体Service接口类.
 * date: 2016-09-08
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface MjUserRightService extends BaseService<MjUserRight> {

	public MjUserRight doAddEntity(MjUserRight entity, User currentUser);

	public void doAddMj(String userId, String termId, User currentUser);
	
}