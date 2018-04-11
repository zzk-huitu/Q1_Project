package com.yc.q1.base.pt.device.service;

import java.util.Map;

import com.yc.q1.model.base.pt.device.PtTermBag;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;

/**
 * 设备钱包
 * @author hucy
 *
 */
public interface TermBagService extends BaseService<PtTermBag>{


	QueryResult<Map> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete,
			String roomId);

}
