package com.yc.q1.base.pt.basic.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.dao.CalenderDetailDao;
import com.yc.q1.base.pt.basic.model.Calender;
import com.yc.q1.base.pt.basic.model.CalenderDetail;
import com.yc.q1.base.pt.basic.service.CalenderDetailService;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: JwCalenderdetailServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 校历节次信息表(JW_T_CALENDERDETAIL)实体Service接口实现类.
 * date: 2016-08-30
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class CalenderDetailServiceImpl extends BaseServiceImpl<CalenderDetail> implements CalenderDetailService {

	@Resource(name = "CalenderDetailDao") // 将具体的dao注入进来
	public void setDao(BaseDao<CalenderDetail> dao) {
		super.setDao(dao);
	}

	private static Logger logger = Logger.getLogger(CalenderDetailServiceImpl.class);

	@Override
	public List<CalenderDetail> queryJwTCanderdetailByJwTCander(Calender jtc) {
		if (jtc == null)
			return null;
		if (jtc.getId() == null || jtc.getId().trim().equals(""))
			return null;
		StringBuffer hql = new StringBuffer(" from CalenderDetail where isDelete=0 and calenderId='");
		hql.append(jtc.getId()).append("'");
		return this.dao.queryByHql(hql.toString());
	}

	@Override
	public CalenderDetail doUpdateEntity(CalenderDetail entity, User currentUser) {

		CalenderDetail perEntity = this.get(entity.getId());

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
	public CalenderDetail doAddEntity(CalenderDetail entity, User currentUser) {
		CalenderDetail saveEntity = new CalenderDetail();
		try {
			BeanUtils.copyPropertiesExceptNull(entity, saveEntity);
			// 生成默认的orderindex
			// 如果界面有了排序号的输入，则不需要取默认的了
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			entity.setOrderIndex(orderIndex);// 排序

			// 增加时要设置创建人
			entity.setCreateUser(currentUser.getId()); // 创建人

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
	public Boolean doDeleteEntity(String delIds) {
		Boolean delResult = false;
		try {
			String doIds = "'" + delIds.replace(",", "','") + "'";
			String hql = "DELETE FROM CalenderDetail j  WHERE j.id IN (" + doIds + ")";
			this.doExecuteCountByHql(hql);
			delResult = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			delResult = false;
		}

		return delResult;

	}

}