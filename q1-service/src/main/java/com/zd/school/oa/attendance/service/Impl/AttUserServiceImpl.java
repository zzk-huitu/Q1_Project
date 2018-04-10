package com.zd.school.oa.attendance.service.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.StringUtils;
import com.zd.school.plartform.system.model.User;
import com.zd.school.oa.attendance.model.AttendUser ;
import com.zd.school.control.device.model.PriceBind;
import com.zd.school.oa.attendance.dao.AttUserDao ;
import com.zd.school.oa.attendance.service.AttUserService ;

/**
 * 
 * ClassName: AttUserServiceImpl
 * Function:  ADD FUNCTION. 
 * Reason:  ADD REASON(可选). 
 * Description: 考勤人员(ATT_T_USER)实体Service接口实现类.
 * date: 2017-05-15
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class AttUserServiceImpl extends BaseServiceImpl<AttendUser> implements AttUserService{

    @Resource
    public void setAttUserDao(AttendUserDao dao) {
        this.dao = dao;
    }
	private static Logger logger = Logger.getLogger(AttUserServiceImpl.class);
	
	@Override
	public QueryResult<AttendUser> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete) {
        QueryResult<AttendUser> qResult = this.queryPageResult(start, limit, sort, filter, isDelete);
		return qResult;
	}
	/**
	 * 根据主键逻辑删除数据
	 * 
	 * @param ids
	 *            要删除数据的主键
	 * @param currentUser
	 *            当前操作的用户
	 * @return 操作成功返回true，否则返回false
	 */
	@Override
	public Boolean doLogicDeleteByIds(String ids, User currentUser) {
		Boolean delResult = false;
		try {
			Object[] conditionValue = ids.split(",");
			String[] propertyName = { "isDelete", "updateUser", "updateTime" };
			Object[] propertyValue = { 1, currentUser.getId(), new Date() };
			this.updateByProperties("id", conditionValue, propertyName, propertyValue);
			delResult = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			delResult = false;
		}
		return delResult;
	}
	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	@Override
	public AttendUser doUpdateEntity(AttendUser entity, User currentUser) {
		// 先拿到已持久化的实体
		AttendUser saveEntity = this.get(entity.getId());
		try {
			BeanUtils.copyProperties(saveEntity, entity);
			saveEntity.setUpdateTime(new Date()); // 设置修改时间
			saveEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
			entity = this.merge(saveEntity);// 执行修改方法

			return entity;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			return null;
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	@Override
	public AttendUser doAddEntity(AttendUser entity, User currentUser) {
		AttendUser saveEntity = new AttendUser();
		try {
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("id");
			BeanUtils.copyProperties(saveEntity, entity,excludedProp);
			saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名
			entity = this.merge(saveEntity);// 执行修改方法

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
	public void doUserAttendBind(String[] userIds, String titleId,String xm) {
		Date date=new Date();
		AttendUser perEntity = null;
		for (int i = 0; i < userIds.length; i++) {
			perEntity = this.getByProerties("userId",userIds[i]);
			if (perEntity != null) {
				perEntity.setAttendThemeId(titleId);
				perEntity.setUpdateTime(date);
				perEntity.setUpdateUser(xm);
				this.merge(perEntity);
			} else {
				perEntity = new AttendUser();
				perEntity.setAttendThemeId(titleId);
				perEntity.setUserId(userIds[i]);
			    perEntity.setCreateUser(xm);
				perEntity.setCreateTime(date);
				this.merge(perEntity);
			}
		}
	}
}