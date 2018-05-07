package com.yc.q1.service.base.pt.system;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserRoomBind;


/**
 * 
 * ClassName: PtUserRoomBindService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 操作员房间绑定Service接口类.
 * date: 2018-05-05
 *
 * @author  tongzy 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtUserRoomBindService extends BaseService<PtUserRoomBind> {
	public boolean doAddRoom(String userId, String ids, PtUser currentUser);
	public boolean doDeleteRoom(String delIds);
}