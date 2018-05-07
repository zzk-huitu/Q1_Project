package com.yc.q1.service.base.pt.system.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.system.PtAccount;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserWorkStationBind;
import com.yc.q1.service.base.pt.system.PtUserWorkStationBindService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BizTJobServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 岗位信息实体Service接口实现类.
 * date: 2016-05-16
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
//@Transactional
public class PtUserWorkStationBindServiceImpl extends BaseServiceImpl<PtUserWorkStationBind> implements PtUserWorkStationBindService{

	@Resource(name = "PtUserWorkStationBindDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtUserWorkStationBind> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	
	
	@Override
	public boolean doAddStation(String userId, String ids, PtUser currentUser) {
		String currentUserId = currentUser.getId();
		
		String[] addId = ids.split(",");
		int orderIndex=1;
		for(int i=0;i<addId.length;i++){
			PtUserWorkStationBind entity =  new PtUserWorkStationBind();
			entity.setId(keyRedisService.getId(PtUserWorkStationBind.ModuleType));
			entity.setUserId(userId);
			entity.setOrderIndex(orderIndex++);
			entity.setWorkStationId(addId[i]);
			entity.setCreateTime(new Date());
			entity.setCreateUser(currentUserId);
			entity.setIsDelete(0);
			entity.setVersion(0);
			this.merge(entity);
		}
		
		return true;
	}


	@Override
	public boolean doDeleteStation(String delIds) {
		String[] delIdsArra = delIds.split(",");
		delIds = delIds.replace(",", "','");
		delIds = "'"+delIds+"'";
		String sql = "DELETE FROM T_PT_UserWorkSationBind WHERE bindId IN ("+delIds+")";
		Integer count =this.doExecuteCountBySql(sql);
		if(count==delIdsArra.length){
			return true;
		}else{
			return false;
		}
		
	}

}