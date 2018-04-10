package com.zd.school.plartform.basedevice.service.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.school.build.define.model.DkPriceDefine;
import com.zd.school.plartform.basedevice.dao.BaseDkPriceDefineDao;
import com.zd.school.plartform.basedevice.service.BaseDkPriceDefineService;
import com.zd.school.plartform.system.model.User;

/**
 * 电控费率定义
 * @author hucy
 *
 */
@Service
@Transactional
public class BaseDkPriceDefineServiceImpl extends BaseServiceImpl<DkPriceDefine> implements BaseDkPriceDefineService{

    @Resource
    public void setDkPriceDefineDao(DkPriceDefineDao dao) {
        this.dao = dao;
    }

private static Logger logger = Logger.getLogger(BaseDkPriceDefineServiceImpl.class);
    
    @Override
	public DkPriceDefine doAddEntity(DkPriceDefine entity, User currentUser) {
		try {
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			float priceValue = entity.getPriceValue();
			DkPriceDefine perEntity = new DkPriceDefine();
			perEntity.setCreateUser(currentUser.getId());
			perEntity.setOrderIndex(orderIndex);
			BeanUtils.copyPropertiesExceptNull(entity, perEntity);
			// 持久化到数据库
			entity = this.merge(entity);
			entity.setPriceValue(priceValue);
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
	public DkPriceDefine doUpdateEntity(DkPriceDefine entity, User currentUser) {
		// 先拿到已持久化的实体
    	DkPriceDefine perEntity = this.get(entity.getId());
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