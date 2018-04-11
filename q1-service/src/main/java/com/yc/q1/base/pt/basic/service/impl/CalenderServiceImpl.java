package com.yc.q1.base.pt.basic.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.service.CalenderService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.basic.Calender;
import com.yc.q1.model.base.pt.basic.Grade;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: JwCalenderServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 校历信息(JW_T_CALENDER)实体Service接口实现类. date:
 * 2016-08-30
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class CalenderServiceImpl extends BaseServiceImpl<Calender> implements CalenderService {

	private static Logger logger = Logger.getLogger(CalenderServiceImpl.class);

	@Resource(name = "CalenderDao") // 将具体的dao注入进来
	public void setDao(BaseDao<Calender> dao) {
		super.setDao(dao);
	}
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public Calender findJwTcanderByClaiId(Grade jtg) {
		if (jtg == null)
			return null;
		if (jtg.getSectionCode() == null || jtg.getSectionCode().trim().equals(""))
			return null;
		return this.getByProerties("sectionCode", jtg.getSectionCode());
	}

	@Override
	public int updateStatu(String calenderIds, String campusNames) {
		// TODO Auto-generated method stub
		try {
			String hql1 = "update Calender set activityState=0 where isDelete=0 and activityState=1 and campusName in('"
					+ campusNames.replace(",", "','") + "')"; // 弃用
			String hql2 = "update Calender set activityState=1 where id in('" + calenderIds.replace(",", "','") + "')";// 1：启用
			this.doExecuteCountByHql(hql1);
			this.doExecuteCountByHql(hql2);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Calender doUpdateEntity(Calender entity, User currentUser) {
		Calender perEntity = this.get(entity.getId());

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
	public Calender doAddEntity(Calender entity, User currentUser) {
		Calender saveEntity = new Calender();
		try {
			BeanUtils.copyPropertiesExceptNull(entity, saveEntity);

			// 生成默认的orderindex
			// 如果界面有了排序号的输入，则不需要取默认的了
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			entity.setOrderIndex(orderIndex);// 排序

			// 增加时要设置创建人
			entity.setCreateUser(currentUser.getId()); // 创建人
			entity.setActivityState(false);

			// 持久化到数据库
			entity.setId(keyRedisService.getId(Calender.ModuleType));	//手动设置id
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
			String hql = "DELETE FROM CalenderDetail j  WHERE j.calenderId IN (" + doIds + ")";
			this.doExecuteCountByHql(hql);

			hql = "DELETE FROM Calender j  WHERE j.id IN (" + doIds + ")";
			this.doExecuteCountByHql(hql);
			delResult = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			delResult = false;
		}

		return delResult;
	}

}