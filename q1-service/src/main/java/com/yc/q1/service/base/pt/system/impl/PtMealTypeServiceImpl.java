package com.yc.q1.service.base.pt.system.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.system.PtMealType;
import com.yc.q1.service.base.pt.system.PtMealTypeService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BizTJobServiceImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 岗位信息实体Service接口实现类. date: 2016-05-16
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
// @Transactional
public class PtMealTypeServiceImpl extends BaseServiceImpl<PtMealType> implements PtMealTypeService {

	@Resource(name = "PtMealTypeDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtMealType> dao) {
		super.setDao(dao);
	}

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public PtMealType doAddPtMealType(PtMealType entity) {
		// TODO Auto-generated method stub
		PtMealType perEntity = new PtMealType();
		List<String> exclude = new ArrayList<String>();
		try {
			BeanUtils.copyProperties(perEntity, entity, exclude);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		perEntity = this.merge(perEntity);
		return perEntity;
	}

	@Override
	public PtMealType doUpdatePtMealType(PtMealType entity) {
		PtMealType perEntity = this.getByProerties("mealTypeId", entity.getMealTypeId());
		try {
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		perEntity = this.merge(perEntity);
		return perEntity;
	}

}