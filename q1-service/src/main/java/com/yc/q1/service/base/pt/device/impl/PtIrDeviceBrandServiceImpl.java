package com.yc.q1.service.base.pt.device.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.device.PtIrDeviceBrand;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.device.PtIrDeviceBrandService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: PtIrDeviceBrandServiceImpl Function: ADD FUNCTION. Reason: ADD
 * REASON(可选). Description: 红外设备品牌型号(PT_IR_DEVICE_BRAND)实体Service接口实现类. date:
 * 2017-01-12
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtIrDeviceBrandServiceImpl extends BaseServiceImpl<PtIrDeviceBrand> implements PtIrDeviceBrandService {

	@Resource(name = "PtIrDeviceBrandDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtIrDeviceBrand> dao) {
		super.setDao(dao);
	}

	private static Logger logger = Logger.getLogger(PtIrDeviceBrandServiceImpl.class);

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public PtIrDeviceBrand doAddEntity(PtIrDeviceBrand entity, PtUser currentUser) {
		PtIrDeviceBrand saveEntity = new PtIrDeviceBrand();

		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("id");
		try {
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名
		entity.setId(keyRedisService.getId(PtIrDeviceBrand.ModuleType));	//手动设置id
		entity = this.merge(saveEntity);// 执行修改方法
		return entity;
	}

	@Override
	public PtIrDeviceBrand doUpdateEntity(PtIrDeviceBrand entity, PtUser currentUser) {
		// 先拿到已持久化的实体
		PtIrDeviceBrand perEntity = this.get(entity.getId());
		try {
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
			perEntity.setUpdateTime(new Date()); // 设置修改时间
			perEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
			entity = this.merge(perEntity);// 执行修改方法

			/* 若修改的是第三层的品牌名称，则一并把第四层的品牌名称修改 */
			if (entity.getLevel() == 3) {
				String hql = "update IrDeviceBrand set brandName='" + entity.getBrandName() + "'"
						+ " where isDelete=0 and level=4 and parentNode='" + entity.getId() + "'";
				this.doExecuteCountByHql(hql);
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