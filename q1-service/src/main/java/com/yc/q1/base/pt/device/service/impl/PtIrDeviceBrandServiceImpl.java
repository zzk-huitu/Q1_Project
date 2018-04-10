package com.yc.q1.base.pt.device.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.device.dao.IrDeviceBrandDao;
import com.yc.q1.base.pt.device.model.IrDeviceBrand;
import com.yc.q1.base.pt.device.service.PtIrDeviceBrandService;
import com.yc.q1.base.pt.system.model.User;
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
public class PtIrDeviceBrandServiceImpl extends BaseServiceImpl<IrDeviceBrand> implements PtIrDeviceBrandService {

	@Resource
	public void setPtIrDeviceBrandDao(IrDeviceBrandDao dao) {
		this.dao = dao;
	}

	private static Logger logger = Logger.getLogger(PtIrDeviceBrandServiceImpl.class);

	@Override
	public IrDeviceBrand doAddEntity(IrDeviceBrand entity, User currentUser) {
		IrDeviceBrand saveEntity = new IrDeviceBrand();

		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("id");
		try {
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名
		entity = this.merge(saveEntity);// 执行修改方法

		return entity;

	}

	@Override
	public IrDeviceBrand doUpdateEntity(IrDeviceBrand entity, User currentUser) {
		// 先拿到已持久化的实体
		IrDeviceBrand perEntity = this.get(entity.getId());
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