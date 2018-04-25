package com.yc.q1.service.base.pt.card.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.basic.PtCalender;
import com.yc.q1.model.base.pt.card.PtCardType;
import com.yc.q1.service.base.pt.card.PtCardTypeService;

/**
 * 
 * ClassName: JwClassdormServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 班级宿舍(JW_T_CLASSDORM)实体Service接口实现类. date:
 * 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtCardTypeServiceImpl extends BaseServiceImpl<PtCardType> implements PtCardTypeService {

	@Resource(name = "PtCardTypeDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtCardType> dao) {
		super.setDao(dao);
	}

	@Override
	public PtCardType doAddPtCardType(PtCardType entity) {
		// TODO Auto-generated method stub
		PtCardType perEntity = new PtCardType();
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
	public PtCardType doUpdatePtCardType(PtCardType entity) {
		PtCardType perEntity = this.getByProerties("cardTypeId", entity.getCardTypeId());
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