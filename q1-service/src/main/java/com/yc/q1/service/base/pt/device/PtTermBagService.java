package com.yc.q1.service.base.pt.device;

import java.util.Map;

import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.device.PtTermBag;

/**
 * 设备钱包
 * @author hucy
 *
 */
public interface PtTermBagService extends BaseService<PtTermBag>{


	QueryResult<Map> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete,
			String roomId);

}
