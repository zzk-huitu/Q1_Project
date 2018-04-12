package com.yc.q1.service.base.sk.impl;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.sk.SkMeter;
import com.yc.q1.model.base.sk.SkPriceDefine;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.base.sk.SkPriceDefineService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 水控费率定义
 * @author hucy
 *
 */
@Service
@Transactional
public class SkPriceDefineServiceImpl extends BaseServiceImpl<SkPriceDefine> implements SkPriceDefineService{

	@Resource(name="SkPriceDefineDao")	//将具体的dao注入进来
	public void setDao(BaseDao<SkPriceDefine> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
    private static Logger logger = Logger.getLogger(SkPriceDefineServiceImpl.class);
    
    @Override
	public SkPriceDefine doAddEntity(SkPriceDefine entity, PtUser currentUser) {
		try {
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			double priceValue = entity.getPriceValue();
			SkPriceDefine perEntity = new SkPriceDefine();
			perEntity.setCreateUser(currentUser.getId());
			perEntity.setOrderIndex(orderIndex);
			perEntity.setId(keyRedisService.getId(SkPriceDefine.ModuleType));
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
	public SkPriceDefine doUpdateEntity(SkPriceDefine entity, PtUser currentUser) {
		// 先拿到已持久化的实体
    	SkPriceDefine perEntity = this.get(entity.getId());
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