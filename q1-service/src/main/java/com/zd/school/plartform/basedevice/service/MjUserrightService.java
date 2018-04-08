package com.zd.school.plartform.basedevice.service;

import com.zd.core.service.BaseService;
import com.zd.school.control.device.model.MjUserRight ;
import com.zd.school.plartform.system.model.User;


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
 
public interface MjUserrightService extends BaseService<MjUserRight> {

	public MjUserRight doAddEntity(MjUserRight entity, User currentUser);

	public void doAddMj(String userId, String termId, User currentUser);
	
}