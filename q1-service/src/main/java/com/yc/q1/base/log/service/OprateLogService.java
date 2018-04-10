
package com.yc.q1.base.log.service;

import java.util.List;

import com.yc.q1.base.log.model.OprateLog;
import com.zd.core.service.BaseService;

public interface OprateLogService extends BaseService<OprateLog> {

	void multiAddEntity(List<OprateLog> lists);


}
