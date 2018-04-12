
package com.yc.q1.service.storage.log;

import java.util.List;

import com.yc.q1.model.storage.log.LogUserOprate;
import com.zd.core.service.BaseService;

public interface LogUserOprateService extends BaseService<LogUserOprate> {

	void multiAddEntity(List<LogUserOprate> lists);


}
