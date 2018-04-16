package com.yc.q1.service.storage.log;

import org.apache.shiro.session.Session;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.storage.log.LogUserLogin;

public interface LogUserLoginService extends BaseService<LogUserLogin> {

	public void doAddLoginLog(PtUser user, Session session);


}
