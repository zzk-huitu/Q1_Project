
package com.yc.q1.service.base.pt.basic;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.basic.PtPushInfo;
import com.yc.q1.model.base.pt.system.PtUser;

public interface PtPushInfoService extends BaseService<PtPushInfo> {

	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus,PtUser currentUser);

	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus, String pushUrl,PtUser currentUser);
	
}
