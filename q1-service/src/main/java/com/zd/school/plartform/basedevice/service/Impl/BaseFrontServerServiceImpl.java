package com.zd.school.plartform.basedevice.service.Impl;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.school.build.define.model.FrontServer;
import com.zd.school.plartform.basedevice.dao.BaseFrontServerDao;
import com.zd.school.plartform.basedevice.service.BaseFrontServerService;
import com.zd.school.plartform.system.model.User;

/**
 * 综合前置管理
 * 
 * @author hucy
 *
 */
@Service
@Transactional
public class BaseFrontServerServiceImpl extends BaseServiceImpl<FrontServer> implements BaseFrontServerService {

	@Resource
	public void setSysFrontServerDao(BaseFrontServerDao dao) {
		this.dao = dao;
	}

	private static Logger logger = Logger.getLogger(BaseFrontServerServiceImpl.class);

	@Override
	public FrontServer doUpdateEntity(FrontServer entity, User currentUser) {

		// 先拿到已持久化的实体
		// entity.getSchoolId()要自己修改成对应的获取主键的方法
		FrontServer perEntity = this.get(entity.getId());
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
	public FrontServer doAddEntity(FrontServer entity, User currentUser) {
		try {
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			FrontServer perEntity = new FrontServer();
			perEntity.setCreateUser(currentUser.getId());
			perEntity.setOrderIndex(orderIndex);
			// perEntity.setPriceValue(entity.getPriceValue());
			// perEntity.setPriceStatus(entity.getPriceStatus());
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
}