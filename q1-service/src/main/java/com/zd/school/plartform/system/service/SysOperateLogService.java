
package com.zd.school.plartform.system.service;

import java.util.List;

import com.zd.core.service.BaseService;
import com.zd.school.plartform.system.model.OprateLog;

public interface SysOperateLogService extends BaseService<OprateLog> {

	void multiAddEntity(List<OprateLog> lists);


}
