package com.zd.school.plartform.basedevice.service.Impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.school.control.device.model.Term;
import com.zd.school.control.device.model.TermBag;
import com.zd.school.plartform.basedevice.dao.PtTermBagsDao;
import com.zd.school.plartform.basedevice.service.BasePtTermService;
import com.zd.school.plartform.basedevice.service.PtTermBagsService;

/**
 * 设备钱包
 * @author hucy
 *
 */
@Service
@Transactional
public class PtTermBagsServiceImpl extends BaseServiceImpl<TermBag> implements PtTermBagsService{
	
	@Resource
    public void setPtTermBagsDao(PtTermBagsDao dao) {
        this.dao = dao;
    }
	@Resource
	private BasePtTermService pttermService;
	@Override
	public QueryResult<Map> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete,String roomId) {
		String hql=" from TermBag g, Term t where t.termSn=g.termSn and  t.roomId='"+roomId+"' and (t.isDelete=0 or t.isDelete is null) and (g.isDelete=0 or g.isDelete is null)";
		String select="select new Map(t.uuid as termid,g.termSn as  termSn,g.termTypeId as termTypeId ,"
				+ "		g.bagValue as bagValue,		g.totalBoughtValue as totalBoughtValue,		g.totalUsedValue as totalUsedValue,"
				+ "		g.totalClearValue  as totalClearValue,		g.subValue as subValue,t.termName as termName ) ";
	   QueryResult<Map> qResult = this.dao.doQueryCountToHql(start, limit, sort, filter,
				   select+ hql, null, null);
		   
		String sql="select count(*) from T_PT_RoomBagRuleBind where roomId='"+roomId+"' and (isDelete=0 or isDelete is null)";
		Integer bdrole=pttermService.getQueryCountBySql(sql);
		
		for(Map map:qResult.getResultList()){
			Term t=pttermService.get((Serializable) map.get("termid"));
			map.put("skprice", t.getSkprice());
			map.put("dkprice", t.getDkprice());
			map.put("skmeasure", t.getSkmeasure());
			map.put("bdrole", bdrole);
		}
		return qResult;
	}
	
}
