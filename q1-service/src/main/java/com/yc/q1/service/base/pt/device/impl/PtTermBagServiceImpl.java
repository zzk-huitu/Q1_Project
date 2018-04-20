package com.yc.q1.service.base.pt.device.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.device.PtTerm;
import com.yc.q1.model.base.pt.device.PtTermBag;
import com.yc.q1.service.base.pt.device.PtTermBagService;
import com.yc.q1.service.base.pt.device.PtTermService;

/**
 * 设备钱包
 * 
 * @author hucy
 *
 */
@Service
@Transactional
public class PtTermBagServiceImpl extends BaseServiceImpl<PtTermBag> implements PtTermBagService {

	@Resource(name = "PtTermBagDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtTermBag> dao) {
		super.setDao(dao);
	}

	@Resource
	private PtTermService pttermService;
	
	@Override
	public QueryResult<Map> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete,
			String roomId) {
		String hql = " from PtTermBag g, PtTerm t where t.termSn=g.termSn and  t.roomId='" + roomId
				+ "' and (t.isDelete=0 or t.isDelete is null) and (g.isDelete=0 or g.isDelete is null)";
		String select = "select new Map(t.uuid as termid,g.termSn as  termSn,g.termTypeId as termTypeId ,"
				+ "		g.bagValue as bagValue,		g.totalBoughtValue as totalBoughtValue,		g.totalUsedValue as totalUsedValue,"
				+ "		g.totalClearValue  as totalClearValue,		g.subValue as subValue,t.termName as termName ) ";
		QueryResult<Map> qResult = this.queryCountToHql(start, limit, sort, filter, select + hql, null, null);
		
		String sql = "select count(*) from T_PT_RoomBagRuleBind where roomId='" + roomId
				+ "' and (isDelete=0 or isDelete is null)";
		Integer bdrole = pttermService.getQueryCountBySql(sql);

		for (Map map : qResult.getResultList()) {
			PtTerm t = pttermService.get((Serializable) map.get("termid"));
			map.put("skprice", t.getSkprice());
			map.put("dkprice", t.getDkprice());
			map.put("skmeasure", t.getSkmeasure());
			map.put("bdrole", bdrole);
		}
		return qResult;
	}

}
