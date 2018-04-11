
package com.yc.q1.base.pt.basic.service;

import com.yc.q1.model.base.pt.basic.PushInfo;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.service.BaseService;

public interface PushInfoService extends BaseService<PushInfo> {

	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus,User currentUser);

	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus, String pushUrl,User currentUser);
	
}
