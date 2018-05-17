package com.yc.q1.service.storage.pt.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.card.PtSubsidyFillMoneyItem;
import com.yc.q1.model.base.pt.card.PtSubsidyFillMoneyMain;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.pt.PtSubsidyFillMoneyItemService;
import com.yc.q1.service.storage.pt.PtSubsidyFillMoneyMainService;
@Service
@Transactional
public class PtSubsidyFillMoneyMainServiceImpl  extends BaseServiceImpl<PtSubsidyFillMoneyMain> implements PtSubsidyFillMoneyMainService{
	@Resource(name="PtSubsidyFillMoneyMainDao")	//将具体的dao注入进来
	public void setDao(BaseDao<PtSubsidyFillMoneyMain> dao) {
		super.setDao(dao);
	}
	@Resource
	PtSubsidyFillMoneyItemService ItemService;
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	private static Logger logger = Logger.getLogger(PtSubsidyFillMoneyMainServiceImpl.class);
	@Override
	public PtSubsidyFillMoneyMain doAddEntity(PtSubsidyFillMoneyMain entity, PtUser currentUser,String ids) {
		PtSubsidyFillMoneyMain saveEntity = new PtSubsidyFillMoneyMain();
		PtSubsidyFillMoneyItem itemEntity ;
		String[] userIds =ids.split(",");
		try {
			//保存PtSubsidyFillMoneyMain 主表
			List<String> excludedProp = new ArrayList<>();
			// excludedProp.add("id");
			String mainId = keyRedisService.getId(PtSubsidyFillMoneyMain.ModuleType);
			entity.setId(mainId);
			entity.setIsAudit(false);
			entity.setIsFill(false);
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
			saveEntity.setCreateUser(currentUser.getUserName()); // 设置修改人的中文名
			entity = this.merge(saveEntity);// 执行修改方法
			//保存PtSubsidyFillMoneyItem 从表
			
			
			excludedProp = new ArrayList<>();
			excludedProp.add("id");
			for (int i = 0; i < userIds.length; i++) {
				itemEntity = new PtSubsidyFillMoneyItem();
				BeanUtils.copyProperties(itemEntity, entity, excludedProp);
				itemEntity.setMainId(mainId);
			    itemEntity.setIsFill(false);
				itemEntity.setId(keyRedisService.getId(PtSubsidyFillMoneyItem.ModuleType));
				itemEntity.setUserId(userIds[i]);	
				itemEntity = ItemService.merge(itemEntity);// 执行修改方法
			}
			
			return entity;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			return null;
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public PtSubsidyFillMoneyMain doUpdateEntity(PtSubsidyFillMoneyMain entity, PtUser currentUser,String ids) {
		// 先拿到已持久化的实体
		PtSubsidyFillMoneyMain saveEntity = this.get(entity.getId());
	    String[] userIds =ids.split(",");
		try {
			BeanUtils.copyProperties(saveEntity, entity);
			saveEntity.setUpdateTime(new Date()); // 设置修改时间
			saveEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
			entity = this.merge(saveEntity);// 执行修改方法
			
			//查询出PtSubsidyFillMoneyItem 中的数据 可能存在多条

			String[] propName = new String[] { "mainId", "userId" };
			for (int i = 0; i < userIds.length; i++) {
				Object[]  propValue = new Object[] { entity.getId(), userIds[i] };
				PtSubsidyFillMoneyItem  itemEntity = ItemService.getByProerties(propName, propValue);
				List<String> excludedProp = new ArrayList<>();
				excludedProp.add("id");
				BeanUtils.copyProperties(itemEntity, entity, excludedProp);
				itemEntity = ItemService.merge(itemEntity);// 执行修改方法
			}

			return entity;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			return null;
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
