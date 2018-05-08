package com.yc.q1.service.base.xf.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.xf.XfRateSet;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.base.xf.XfRateSetService;
@Service
@Transactional
public class XfRateSetServiceImpl extends BaseServiceImpl<XfRateSet> implements XfRateSetService{
	@Resource(name = "XfRateSetDao") // 将具体的dao注入进来
	public void setDao(BaseDao<XfRateSet> dao) {
		super.setDao(dao);
	}
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	private static Logger logger = Logger.getLogger(XfRateSetServiceImpl.class);
	@Override
	public XfRateSet doUpdateEntity(XfRateSet entity, PtUser currentUser) {
		// 先拿到已持久化的实体
		XfRateSet perEntity = this.get(entity.getId());
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

	@Override
	public XfRateSet doAddEntity(XfRateSet entity, PtUser currentUser) {
		try {
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			XfRateSet perEntity = new XfRateSet();
			perEntity.setCreateUser(currentUser.getId());
			perEntity.setOrderIndex(orderIndex);
			BeanUtils.copyPropertiesExceptNull(entity, perEntity);
				// 持久化到数据库
			entity.setId(keyRedisService.getId(XfRateSet.ModuleType));	//手动设置id
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
}
