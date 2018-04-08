
package com.zd.school.jw.push.service;

import com.zd.core.service.BaseService;
import com.zd.school.jw.push.model.PushInfo;
import com.zd.school.plartform.system.model.User;

public interface PushInfoService extends BaseService<PushInfo> {

	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus,User currentUser);

	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus, String pushUrl,User currentUser);
	
}
