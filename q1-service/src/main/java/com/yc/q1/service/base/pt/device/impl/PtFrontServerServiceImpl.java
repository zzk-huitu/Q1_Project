package com.yc.q1.service.base.pt.device.impl;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.device.PtFrontServer;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.device.PtFrontServerService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 综合前置管理
 * 
 * @author hucy
 *
 */
@Service
@Transactional
public class PtFrontServerServiceImpl extends BaseServiceImpl<PtFrontServer> implements PtFrontServerService {

	private static Logger logger = Logger.getLogger(PtFrontServerServiceImpl.class);
	
	@Resource(name = "PtFrontServerDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtFrontServer> dao) {
		super.setDao(dao);
	}

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public PtFrontServer doUpdateEntity(PtFrontServer entity, PtUser currentUser) {

		// 先拿到已持久化的实体
		// entity.getSchoolId()要自己修改成对应的获取主键的方法
		PtFrontServer perEntity = this.get(entity.getId());
		perEntity.setUpdateUser(currentUser.getId());
		// 将entity中不为空的字段动态加入到perEntity中去。
		try {
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
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
	public PtFrontServer doAddEntity(PtFrontServer entity, PtUser currentUser) {
		try {
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			PtFrontServer perEntity = new PtFrontServer();
			perEntity.setCreateUser(currentUser.getId());
			perEntity.setOrderIndex(orderIndex);
			// perEntity.setPriceValue(entity.getPriceValue());
			// perEntity.setPriceStatus(entity.getPriceStatus());
			BeanUtils.copyPropertiesExceptNull(entity, perEntity);
			
			// 持久化到数据库
			entity.setId(keyRedisService.getId(PtFrontServer.ModuleType));	//手动设置id
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