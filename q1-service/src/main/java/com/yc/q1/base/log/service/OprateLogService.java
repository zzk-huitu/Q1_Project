
package com.yc.q1.base.log.service;

import java.util.List;

import com.yc.q1.model.storage.log.LogUserOprate;
import com.zd.core.service.BaseService;

public interface OprateLogService extends BaseService<LogUserOprate> {

	void multiAddEntity(List<LogUserOprate> lists);


}
