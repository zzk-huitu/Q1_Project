package com.yc.q1.base.pt.wisdomclass.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.wisdomclass.dao.NoticeAuditorDao;
import com.yc.q1.base.pt.wisdomclass.model.Notice;
import com.yc.q1.base.pt.wisdomclass.model.NoticeAuditor;
import com.yc.q1.base.pt.wisdomclass.service.OaNoticeService;
import com.yc.q1.base.pt.wisdomclass.service.OaNoticeauditorService;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: OaNoticeauditorServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 公告审核人(OA_T_NOTICEAUDITOR)实体Service接口实现类.
 * date: 2016-12-21
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class OaNoticeauditorServiceImpl extends BaseServiceImpl<NoticeAuditor> implements OaNoticeauditorService{

    @Resource
    public void setOaNoticeauditorDao(NoticeAuditorDao dao) {
        this.dao = dao;
    }
	private static Logger logger = Logger.getLogger(OaNoticeauditorServiceImpl.class);
	
	@Resource
	private OaNoticeService noticeService;
	
	@Override
	public QueryResult<NoticeAuditor> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete) {
        QueryResult<NoticeAuditor> qResult = this.queryPageResult(start, limit, sort, filter, isDelete);
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
	public NoticeAuditor doUpdateEntity(NoticeAuditor entity, User currentUser) {
		// 先拿到已持久化的实体
		NoticeAuditor saveEntity = this.get(entity.getId());
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
	public NoticeAuditor doAddEntity(NoticeAuditor entity, User currentUser) {
		NoticeAuditor saveEntity = new NoticeAuditor();
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
	public QueryResult<Notice> userlist(Integer start, Integer limit, String sort, String filter, String whereSql,
			String orderSql, User currentUser) {
		String sortSql = StringUtils.convertSortToSql(sort);
		String filterSql = StringUtils.convertFilterToSql(filter);
		StringBuffer hql = new StringBuffer("from Notice where 1=1 ");

		hql.append(whereSql);
		hql.append(filterSql);
		if (orderSql.length() > 0) {
			if (sortSql.length() > 0)
				hql.append(orderSql + " , " + sortSql);
			else
				hql.append(orderSql);
		} else {
			if (sortSql.length() > 0)
				hql.append(" order by  " + sortSql);
		}

		QueryResult<Notice> qResult = noticeService.queryResult(hql.toString(), start, limit);
		return qResult;
	}
}