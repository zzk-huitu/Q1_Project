package com.yc.q1.base.xf.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.base.xf.service.RoomBagRuleBindService;
import com.yc.q1.model.base.pt.device.RoomBagRuleBind;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.StringUtils;

/**
 * 钱包规则绑定
 * @author hucy
 *
 */
@Service
@Transactional
public class RoomBagRuleBindServiceImpl extends BaseServiceImpl<RoomBagRuleBind> implements RoomBagRuleBindService{
	
	@Resource(name="RoomBagRuleBindDao")	//将具体的dao注入进来
	public void setDao(BaseDao<RoomBagRuleBind> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;

	@Override
	public void doAddRuleBind(String roomRuleId, String roomIds, String deductionUserIds,
			String deductionRoomIds, String xm) {
		// TODO Auto-generated method stub
		
		String rooms[]=roomIds.split(",");
		
		String deductionUsers[]=null;
		if(StringUtils.isNotEmpty(deductionUserIds))
			deductionUsers=deductionUserIds.split(",");
		
		String deductionRooms[]=null;
		if(StringUtils.isNotEmpty(deductionRoomIds))
			deductionRooms=deductionRoomIds.split(",");
		
		RoomBagRuleBind perEntity = null;
		String getDeductionUserId=null;
		
		for(int i=0;i<rooms.length;i++){
			getDeductionUserId=null;
			//找到扣费人员
			if(deductionRooms!=null)
				for(int j=0;j<deductionRooms.length;j++){
					if(rooms[i].equals(deductionRooms[j])){
						getDeductionUserId=deductionUsers[j];
						break;
					}
				}
			
			//入库
			perEntity = this.getByProerties("roomId", rooms[i]);
			if (perEntity != null) {			
				perEntity.setDeductionUserId(getDeductionUserId);
				perEntity.setRoomRuleId(roomRuleId);
				perEntity.setUpdateTime(new Date());
				perEntity.setCreateUser(xm);
				perEntity.setIsDelete(0);
			} else {
				Integer orderIndex = this.getDefaultOrderIndex(new RoomBagRuleBind());
				perEntity = new RoomBagRuleBind();
				perEntity.setId(keyRedisService.getId(RoomBagRuleBind.ModuleType));
				perEntity.setDeductionUserId(getDeductionUserId);
				perEntity.setRoomRuleId(roomRuleId);
				perEntity.setRoomId(rooms[i]);
				perEntity.setOrderIndex(orderIndex);
				perEntity.setUpdateTime(new Date());
				perEntity.setCreateUser(xm);
			}

			this.merge(perEntity);		
		}
	}
}
