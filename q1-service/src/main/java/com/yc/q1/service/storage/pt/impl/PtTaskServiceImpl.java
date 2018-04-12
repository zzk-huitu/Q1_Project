package com.yc.q1.service.storage.pt.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.storage.pt.PtTask;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.pt.PtTaskService;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;



/**
* Created by zenglj on 2017-05-16.
*/
@Service
@Transactional
public class PtTaskServiceImpl extends BaseServiceImpl<PtTask> implements PtTaskService{

	private static Logger logger = Logger.getLogger(PtTaskServiceImpl.class);
	
	@Resource(name = "PtTaskDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtTask> dao) {
		super.setDao(dao);
	}
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;
	
	@Override
	public QueryResult<PtTask> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete) {
		String hql1=" select g" ;
		String hql= " from Task g where g.executeTime= "
				+ "(select Max(executeTime) from Task s1 where s1.termSn=g.termSn)  ";
		QueryResult<PtTask> qResult = this.queryCountToHql(start, limit, sort, filter,
				   hql1+ hql, null, null);
		return qResult;
		
	}
	
	@Override
	public QueryResult<PtTask> tasklistbyTermId(Integer start, Integer limit, String sort, String filter, Boolean isDelete) {
		/*String hql1=" select g " ;
		String hql= " from PtTask g,PtTerm t where  g.termsn =t.termSN  ";
		//QueryResult<PtTask> qResult = this.dao.doQueryCountToHqlCountSql(start, limit, sort, filter,
		//		   hql1+ hql, null, null, "select count(1) "+hql);
		return qResult;*/
		return null;
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
	public Boolean doLogicDeleteByIds(String ids, PtUser currentUser) {
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
	public PtTask doUpdateEntity(PtTask entity, PtUser currentUser) {
		// 先拿到已持久化的实体
		PtTask saveEntity = this.get(entity.getId());
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
	public PtTask doAddEntity(PtTask entity, PtUser currentUser) {
		PtTask saveEntity = new PtTask();
		try {
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("id");
			BeanUtils.copyProperties(saveEntity, entity,excludedProp);
			saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名
			
			saveEntity.setId(keyRedisService.getId(PtTask.ModuleType));	//手动设置id
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
	
}
