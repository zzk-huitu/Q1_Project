package com.yc.q1.service.base.pt.card.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.dk.DkPriceDefine;
import com.yc.q1.model.base.pt.card.PtCard;
import com.yc.q1.model.base.pt.system.PtRole;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.storage.pt.PtCardStatusChange;
import com.yc.q1.service.base.pt.card.PtCardService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: JwClassdormServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 班级宿舍(JW_T_CLASSDORM)实体Service接口实现类. date:
 * 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtCardServiceImpl extends BaseServiceImpl<PtCard> implements PtCardService {

	@Resource(name = "PtCardDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtCard> dao) {
		super.setDao(dao);
	}

	@Resource
	private PrimaryKeyRedisService keyRedisService;
	
	@Override
	public Boolean doLockOrOn(String cardId,PtUser currentUser) {
		
		Boolean flag = false;
		// 先拿到已持久化的实体
		PtCard perEntity = this.get(cardId);
		Integer oldcardStatusId = perEntity.getCardStatusId();
//		Integer cardType = perEntity.getCardTypeId();
		Integer newcardStatusId;
		String cardClassName="";
		String cardStatusName="";
		if(oldcardStatusId==1){
			newcardStatusId =2;
			cardStatusName="挂失";
		}else{
			newcardStatusId =1;
			cardStatusName="正常";
		}
				
		perEntity.setUpdateUser(currentUser.getId());
		perEntity.setStatusChangeTime(new Date());
		perEntity.setCardStatusId(newcardStatusId);
		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("cardNo");
		this.doUpdateEntity(perEntity, currentUser.getId(), excludedProp);
		
		PtCardStatusChange cardStatusChange = new PtCardStatusChange();
		cardStatusChange.setUserId(perEntity.getUserId());
		cardStatusChange.setCardNo(perEntity.getCardNo());
		cardStatusChange.setCardClassName(cardClassName);
		cardStatusChange.setCardStatusName(cardStatusName);
		cardStatusChange.setId(keyRedisService.getId(PtCardStatusChange.ModuleType));
		this.getSession().merge(cardStatusChange);
		flag = true;
		return flag;
	}

	@Override
	public Boolean doLockBatch(String ids, PtUser currentUser) {
		Boolean flag = false;
		String[] lockBatchIds = ids.split(",");
		for(int i=0 ;i<lockBatchIds.length;i++){
			// 先拿到已持久化的实体
			PtCard perEntity = this.get(lockBatchIds[i]);
					
			perEntity.setUpdateUser(currentUser.getId());
			perEntity.setStatusChangeTime(new Date());
			perEntity.setCardStatusId(2);
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("cardNo");
			this.doUpdateEntity(perEntity, currentUser.getId(), excludedProp);
			
			PtCardStatusChange cardStatusChange = new PtCardStatusChange();
			cardStatusChange.setUserId(perEntity.getUserId());
			cardStatusChange.setCardNo(perEntity.getCardNo());
			cardStatusChange.setCardStatusName("挂失");
			cardStatusChange.setId(keyRedisService.getId(PtCardStatusChange.ModuleType));
			this.getSession().merge(cardStatusChange);
		}
		flag = true;
		return flag;
	}

	@Override
	public Boolean doOnBatch(String ids, PtUser currentUser) {
		Boolean flag = false;
		String[] lockBatchIds = ids.split(",");
		for(int i=0 ;i<lockBatchIds.length;i++){
			// 先拿到已持久化的实体
			PtCard perEntity = this.get(lockBatchIds[i]);
					
			perEntity.setUpdateUser(currentUser.getId());
			perEntity.setStatusChangeTime(new Date());
			perEntity.setCardStatusId(1);
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("cardNo");
			this.doUpdateEntity(perEntity, currentUser.getId(), excludedProp);
			
			PtCardStatusChange cardStatusChange = new PtCardStatusChange();
			cardStatusChange.setUserId(perEntity.getUserId());
			cardStatusChange.setCardNo(perEntity.getCardNo());
			cardStatusChange.setCardStatusName("正常");
			cardStatusChange.setId(keyRedisService.getId(PtCardStatusChange.ModuleType));
			this.getSession().merge(cardStatusChange);
		}
		flag = true;
		return flag;
	}


}