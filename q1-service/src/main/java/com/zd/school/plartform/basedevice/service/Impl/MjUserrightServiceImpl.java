package com.zd.school.plartform.basedevice.service.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.StringUtils;
import com.zd.school.control.device.model.MjUserRight;
import com.zd.school.plartform.basedevice.dao.MjUserrightDao;
import com.zd.school.plartform.basedevice.service.MjUserrightService;
import com.zd.school.plartform.system.model.User;

/**
 * 
 * ClassName: MjUserrightServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 门禁权限表(MJ_UserRight)实体Service接口实现类. date:
 * 2016-09-08
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class MjUserrightServiceImpl extends BaseServiceImpl<MjUserRight> implements MjUserrightService {

	private static Logger logger = Logger.getLogger(MjUserrightServiceImpl.class);

	@Resource
	public void setMjUserrightDao(MjUserRightDao dao) {
		this.dao = dao;
	}

	@Override
	public MjUserRight doAddEntity(MjUserRight entity, User currentUser) {
		try {
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			MjUserRight perEntity = new MjUserRight();
			perEntity.setCreateUser(currentUser.getId());
			perEntity.setOrderIndex(orderIndex);
			BeanUtils.copyPropertiesExceptNull(entity, perEntity);
			// 持久化到数据库
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
	public void doAddMj(String userId, String termId, User currentUser) {
		// TODO Auto-generated method stub
		String userIds[] = userId.split(",");
		String termIds[] = termId.split(",");

		MjUserRight mjUserright = null;
		for (int i = 0; i < userIds.length; i++) {
			if (StringUtils.isEmpty(userIds[i]))
				break;

			for (int j = 0; j < termIds.length; j++) {
				if (StringUtils.isEmpty(termIds[j]))
					break;

				mjUserright = this.getByProerties(new String[] { "studentId", "termId" },
						new Object[] { userIds[i], termIds[j] });
				if (mjUserright != null) {
					mjUserright.setIsDelete(0);
					mjUserright.setUpdateUser(currentUser.getId());
					mjUserright.setUpdateTime(new Date());
				} else {
					mjUserright = new MjUserRight();
					mjUserright.setUserId(userIds[i]);
					mjUserright.setTermId(termIds[j]);
					mjUserright.setCreateUser(currentUser.getId());
					mjUserright.setCreateTime(new Date());
				}
				this.merge(mjUserright);
			}
		}
	}

}