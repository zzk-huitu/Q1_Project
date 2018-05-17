package com.yc.q1.service.base.pt.basic.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.basic.PtCalender;
import com.yc.q1.model.base.pt.basic.PtSchoolCalendar;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.basic.PtSchoolCalendarService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
@Service
@Transactional
public class PtSchoolCalendarServiceImpl extends BaseServiceImpl<PtSchoolCalendar> implements PtSchoolCalendarService {

	@Resource(name = "PtSchoolCalendarDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtSchoolCalendar> dao) {
		super.setDao(dao);
	}
	private static Logger logger = Logger.getLogger(PtSchoolCalendarServiceImpl.class);
	@Resource
	private PrimaryKeyRedisService keyRedisService;
	@Override
	public PtSchoolCalendar doAddEntity(PtSchoolCalendar entity, PtUser currentUser) {
		PtSchoolCalendar saveEntity = new PtSchoolCalendar();
		try {
			BeanUtils.copyPropertiesExceptNull(entity, saveEntity);

			// 生成默认的orderindex
			// 如果界面有了排序号的输入，则不需要取默认的了
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			entity.setOrderIndex(orderIndex);// 排序
            // 增加时要设置创建人
			entity.setCreateUser(currentUser.getId()); // 创建人
			// 持久化到数据库
			entity.setId(keyRedisService.getId(PtSchoolCalendar.ModuleType));	//手动设置id
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
