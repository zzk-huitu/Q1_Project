package com.yc.q1.base.pt.wisdomclass.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.wisdomclass.service.AttendTermService;
import com.yc.q1.base.pt.wisdomclass.service.AttendThemeService;
import com.yc.q1.base.pt.wisdomclass.service.AttendTimeService;
import com.yc.q1.base.pt.wisdomclass.service.AttendUserService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.model.base.pt.wisdomclass.AttendTerm;
import com.yc.q1.model.base.pt.wisdomclass.AttendTheme;
import com.yc.q1.model.base.pt.wisdomclass.AttendUser;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: AttTitleServiceImpl
 * Function:  ADD FUNCTION. 
 * Reason:  ADD REASON(可选). 
 * Description: 考勤主题(ATT_T_TITLE)实体Service接口实现类.
 * date: 2017-05-15
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class AttendThemeServiceImpl extends BaseServiceImpl<AttendTheme> implements AttendThemeService{

	@Resource
	AttendUserService attUserService;
	@Resource
	AttendTermService attTermService;
	@Resource
	AttendTimeService attTimeService;
	
	@Resource(name="AttendThemeDao")	//将具体的dao注入进来
	public void setDao(BaseDao<AttendTheme> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	private static Logger logger = Logger.getLogger(AttendThemeServiceImpl.class);
	
	@Override
	public QueryResult<AttendTheme> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete) {
        QueryResult<AttendTheme> qResult = this.queryPageResult(start, limit, sort, filter, isDelete);
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
			attTermService.updateByProperties("attendThemeId", conditionValue, propertyName, propertyValue);
			attTermService.updateByProperties("attendThemeId", conditionValue, propertyName, propertyValue);
			attUserService.updateByProperties("attendThemeId", conditionValue, propertyName, propertyValue);
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
	public AttendTheme doUpdateEntity(AttendTheme entity, User currentUser) {
		// 先拿到已持久化的实体
		AttendTheme saveEntity = this.get(entity.getId());
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
	public AttendTheme doAddEntity(AttendTheme entity, User currentUser) {
		AttendTheme saveEntity = new AttendTheme();
		try {
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("id");
			entity.setId(keyRedisService.getId(AttendTheme.ModuleType));
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
	public Integer doAddUsers(String titleId, String[] userIds, String[] userNames, String[] userNumbs, String xm) {
		Integer count=0;
	
		for (int i = 0; i < userIds.length; i++) {
			AttendUser attUser = new AttendUser();
			attUser.setId(keyRedisService.getId(AttendUser.ModuleType));
            attUser.setAttendThemeId(titleId);
			attUser.setUserId(userIds[i]);
			attUser.setName(userNames[i]);
			attUser.setUserNumb(userNumbs[i]);

			attUser.setCreateUser(xm);
			attUser.setCreateTime(new Date());

			attUserService.merge(attUser);
			count++;
		}
		
		return count;	
	}
	
	@Override
	public Integer doAddTerms(String titleId,  String[] termCodes,String[] roomIds, String[] roomNames, String xm) {
		Integer count=0;
		
		for (int i = 0; i < termCodes.length; i++) {
			AttendTerm attTerm = new AttendTerm();
			attTerm.setId(keyRedisService.getId(AttendTerm.ModuleType));
			attTerm.setAttendThemeId(titleId);
			attTerm.setTerminalNo(termCodes[i]);
			attTerm.setRoomId(roomIds[i]);
			attTerm.setRoomName(roomNames[i]);

			attTerm.setCreateUser(xm);
			attTerm.setCreateTime(new Date());

			attTermService.merge(attTerm);
			count++;
		}
		
		return count;
		
	}
}