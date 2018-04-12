package com.yc.q1.service.base.pt.wisdomclass.impl;

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
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.wisdomclass.PtClassRedFlag;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.yc.q1.service.base.pt.wisdomclass.PtClassRedFlagService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: EccClassregflagServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班级流动红旗(ECC_T_CLASSREGFLAG)实体Service接口实现类.
 * date: 2016-12-13
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtClassRedFlagServiceImpl extends BaseServiceImpl<PtClassRedFlag> implements PtClassRedFlagService{

	@Resource
	private PtUserService userService;
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	@Resource(name="ptClassRedFlagDao")	//将具体的dao注入进来
	public void setDao(BaseDao<PtClassRedFlag> dao) {
		super.setDao(dao);
	}
	private static Logger logger = Logger.getLogger(PtClassRedFlagServiceImpl.class);
	
	@Override
	public QueryResult<PtClassRedFlag> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete) {
        QueryResult<PtClassRedFlag> qResult = this.queryPageResult(start, limit, sort, filter, isDelete);
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
	public PtClassRedFlag doUpdateEntity(PtClassRedFlag entity, PtUser currentUser) {
		// 先拿到已持久化的实体
		PtClassRedFlag saveEntity = this.get(entity.getId());
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
	public PtClassRedFlag doAddEntity(PtClassRedFlag entity, PtUser currentUser) {
		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("id");
		excludedProp.add("classId");
		excludedProp.add("className");
		try {
			String [] claiIds = entity.getClassId().split(",");
			String [] classNames = entity.getClassName().split(",");	
			for (int i = 0; i < claiIds.length; i++) {
				PtClassRedFlag saveEntity = new PtClassRedFlag();
				entity.setId(keyRedisService.getId(PtClassRedFlag.ModuleType));
				BeanUtils.copyProperties(saveEntity, entity,excludedProp);
				saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名
				saveEntity.setClassId(claiIds[i]);
				saveEntity.setClassName(classNames[i]);
				entity = this.merge(saveEntity);// 执行修改方法
			}

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
	public QueryResult<PtClassRedFlag> list(Integer start, Integer limit, String sort, String filter, String whereSql,
			String orderSql, PtUser currentUser) {
		String sortSql = StringUtils.convertSortToSql(sort);
		String filterSql = StringUtils.convertFilterToSql(filter);

		StringBuffer hql = new StringBuffer("from ClassRedFlag o where 1=1 and isDelete=0 ");
		hql.append(whereSql);
		hql.append(filterSql);
		String rightDeptIds = "";
		if ("0".equals(currentUser.getRightType())) {
			// 如果当前用户不是所有的部门权限，则取有权限的部门
			//rightDeptIds = userService.getUserOwnDeptids(currentUser);(zzk 2017/12/22 暂不存在此方法)
		}
		if (StringUtils.isNotEmpty(rightDeptIds)) {
			whereSql+=" and classId in("+rightDeptIds+")";
		}
        if (orderSql.length()>0){
        	if (sortSql.length()>0)
        		hql.append(orderSql+ " , " + sortSql);
        	else 
        		hql.append(orderSql);
        } else {
        	if (sortSql.length()>0)
        		hql.append(" order by  " + sortSql);
        }
        
        QueryResult<PtClassRedFlag> qResult = this.queryResult(hql.toString(), start, limit);
		return qResult;
	}
}