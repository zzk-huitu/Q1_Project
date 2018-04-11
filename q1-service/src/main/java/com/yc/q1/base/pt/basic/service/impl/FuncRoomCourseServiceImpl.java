package com.yc.q1.base.pt.basic.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.service.FuncRoomCourseService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.basic.FuncRoomCourse;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: JwFuncroomcourseServiceImpl Function: ADD FUNCTION. Reason: ADD
 * REASON(可选). Description: 排课课程表(JW_T_FUNCROOMCOURSE)实体Service接口实现类. date:
 * 2017-03-06
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class FuncRoomCourseServiceImpl extends BaseServiceImpl<FuncRoomCourse> implements FuncRoomCourseService {

	private static Logger logger = Logger.getLogger(FuncRoomCourseServiceImpl.class);

	@Resource(name = "FuncRoomCourseDao") // 将具体的dao注入进来
	public void setDao(BaseDao<FuncRoomCourse> dao) {
		super.setDao(dao);
	}
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public QueryResult<FuncRoomCourse> list(Integer start, Integer limit, String sort, String filter,
			Boolean isDelete) {
		QueryResult<FuncRoomCourse> qResult = this.queryPageResult(start, limit, sort, filter, isDelete);
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
	public FuncRoomCourse doUpdateEntity(FuncRoomCourse entity, User currentUser) {
		// 先拿到已持久化的实体
		FuncRoomCourse saveEntity = this.get(entity.getId());
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
	public FuncRoomCourse doAddEntity(FuncRoomCourse entity, User currentUser) {
		FuncRoomCourse saveEntity;
		try {
			// List<String> excludedProp = new ArrayList<>();
			// excludedProp.add("uuid");
			// BeanUtils.copyProperties(saveEntity, entity,excludedProp);
			// saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名
			// entity = this.merge(saveEntity);// 执行修改方法
			String[] propName = { "funcRoomId", "sections", "isDelete" };
			Object[] propValue = { entity.getFuncRoomId(), entity.getSections(), 0 };
			saveEntity = this.getByProerties(propName, propValue);

			if (saveEntity == null) {
				saveEntity = new FuncRoomCourse();
			}
			BeanUtils.copyPropertiesExceptNullAndStringEmpty(saveEntity, entity);
			saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名
			
			entity.setId(keyRedisService.getId(FuncRoomCourse.ModuleType));	//手动设置id
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
	 * 批量增加功能室课程
	 * 
	 * @param funcRoomCourseList
	 *            传入的要更新的实体对象集合
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	@Override
	public Integer doAddEntityList(List<FuncRoomCourse> funcRoomCourseList, User currentUser)
			throws IllegalAccessException, InvocationTargetException {
		Integer count = 0;

		FuncRoomCourse saveEntity;
		for (int i = 0; i < funcRoomCourseList.size(); i++) {

			FuncRoomCourse entity = funcRoomCourseList.get(i);

			String[] propName = { "funcRoomId", "sections", "isDelete" };
			Object[] propValue = { entity.getFuncRoomId(), entity.getSections(), 0 };
			saveEntity = this.getByProerties(propName, propValue);

			if (saveEntity == null) {
				saveEntity = new FuncRoomCourse();
			} else {
				entity.setId(null);
			}
			BeanUtils.copyPropertiesExceptNullAndStringEmpty(saveEntity, entity);
			saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名
			
			entity.setId(keyRedisService.getId(FuncRoomCourse.ModuleType));	//手动设置id
			entity = this.merge(saveEntity);// 执行修改方法

			count++;
		}
		return count;

	}
}