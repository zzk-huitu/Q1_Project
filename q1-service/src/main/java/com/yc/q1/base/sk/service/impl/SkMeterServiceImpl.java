package com.yc.q1.base.sk.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.sk.model.SkMeter;
import com.yc.q1.base.sk.service.SkMeterService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

@Service
@Transactional
public class SkMeterServiceImpl extends BaseServiceImpl<SkMeter> implements SkMeterService{

	private static Logger logger = Logger.getLogger(SkMeterServiceImpl.class);
	
	@Resource(name="SkMeterDao")	//将具体的dao注入进来
	public void setDao(BaseDao<SkMeter> dao) {
		super.setDao(dao);
	}
	
	 @Override
		public SkMeter doAddEntity(SkMeter entity, User currentUser) {
			try {
				Integer orderIndex = this.getDefaultOrderIndex(entity);
				SkMeter perEntity = new SkMeter();
				perEntity.setCreateUser(currentUser.getId());
				perEntity.setOrderIndex(orderIndex);
				BeanUtils.copyPropertiesExceptNull(entity, perEntity);
				// 持久化到数据库
				entity = this.merge(entity);
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
	public SkMeter doUpdateEntity(SkMeter entity, User currentUser) {
		// 先拿到已持久化的实体
		SkMeter perEntity = this.get(entity.getId());
		try {
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
			perEntity.setUpdateTime(new Date()); // 设置修改时间
			perEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
			entity = this.merge(perEntity);// 执行修改方法
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
