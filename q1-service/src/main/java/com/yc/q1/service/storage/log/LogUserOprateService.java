
package com.yc.q1.service.storage.log;

import java.util.List;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.storage.log.LogUserOprate;

public interface LogUserOprateService extends BaseService<LogUserOprate> {

	void multiAddEntity(List<LogUserOprate> lists);


}
