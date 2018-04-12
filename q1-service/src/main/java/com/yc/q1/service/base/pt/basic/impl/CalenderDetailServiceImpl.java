package com.yc.q1.service.base.pt.basic.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.basic.PtCalender;
import com.yc.q1.model.base.pt.basic.PtCalenderDetail;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.basic.CalenderDetailService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
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
public class CalenderDetailServiceImpl extends BaseServiceImpl<PtCalenderDetail> implements CalenderDetailService {

	private static Logger logger = Logger.getLogger(CalenderDetailServiceImpl.class);
	
	@Resource(name = "CalenderDetailDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtCalenderDetail> dao) {
		super.setDao(dao);
	}
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public List<PtCalenderDetail> queryJwTCanderdetailByJwTCander(PtCalender jtc) {
		if (jtc == null)
			return null;
		if (jtc.getId() == null || jtc.getId().trim().equals(""))
			return null;
		StringBuffer hql = new StringBuffer(" from CalenderDetail where isDelete=0 and calenderId='");
		hql.append(jtc.getId()).append("'");
		return this.queryByHql(hql.toString());
	}

	@Override
	public PtCalenderDetail doUpdateEntity(PtCalenderDetail entity, PtUser currentUser) {
		PtCalenderDetail perEntity = this.get(entity.getId());

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
	public PtCalenderDetail doAddEntity(PtCalenderDetail entity, PtUser currentUser) {
		PtCalenderDetail saveEntity = new PtCalenderDetail();
		try {
			BeanUtils.copyPropertiesExceptNull(entity, saveEntity);
			// 生成默认的orderindex
			// 如果界面有了排序号的输入，则不需要取默认的了
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			entity.setOrderIndex(orderIndex);// 排序
			// 增加时要设置创建人
			entity.setCreateUser(currentUser.getId()); // 创建人

			// 持久化到数据库
			entity.setId(keyRedisService.getId(PtCalenderDetail.ModuleType));	//手动设置id
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