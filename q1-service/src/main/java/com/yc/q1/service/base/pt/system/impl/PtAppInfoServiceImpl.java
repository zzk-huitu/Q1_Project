package com.yc.q1.service.base.pt.system.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.system.PtAppInfo;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.system.PtAppInfoService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: SysAppinfoServiceImpl Function: ADD FUNCTION. Reason: ADD
 * REASON(可选). Description: (SYS_T_APPINFO)实体Service接口实现类. date: 2017-05-15
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtAppInfoServiceImpl extends BaseServiceImpl<PtAppInfo> implements PtAppInfoService {

	@Resource(name = "PtAppInfoDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtAppInfo> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	private static Logger logger = Logger.getLogger(PtAppInfoServiceImpl.class);

	@Override
	public QueryResult<PtAppInfo> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete) {
		QueryResult<PtAppInfo> qResult = this.queryPageResult(start, limit, sort, filter, isDelete);
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
	public PtAppInfo doUpdateEntity(PtAppInfo entity, PtUser currentUser) {
		// 先拿到已持久化的实体
		PtAppInfo saveEntity = this.get(entity.getId());
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
	public PtAppInfo doAddEntity(PtAppInfo entity, PtUser currentUser) {
		PtAppInfo saveEntity = new PtAppInfo();
		try {
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("id");
			entity.setId(keyRedisService.getId(PtAppInfo.ModuleType));
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
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
}