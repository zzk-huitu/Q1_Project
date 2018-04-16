package com.yc.q1.service.base.pt.build.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.build.PtFuncRoomDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.service.base.pt.build.PtFuncRoomDefineService;
import com.yc.q1.service.base.pt.build.PtRoomInfoService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BuildFuncroomdefinServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: BUILD_T_FUNCROOMDEFIN实体Service接口实现类. date:
 * 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtFuncRoomDefineServiceImpl extends BaseServiceImpl<PtFuncRoomDefine> implements PtFuncRoomDefineService {

	@Resource(name = "PtFuncRoomDefineDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtFuncRoomDefine> dao) {
		super.setDao(dao);
	}

	@Resource
	private PtRoomInfoService thisService; // service层接口

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public PtFuncRoomDefine getByRoomId(String roomId) {
		PtFuncRoomDefine entity;
		String hql = "from PtFuncRoomDefine where 1=1";
		if (!roomId.isEmpty()) {
			hql += " and roomId='" + roomId + "' ";
		}
		entity = this.getEntityByHql(hql);
		return entity;

	}

	@Override
	public void addFunRoom(PtRoomInfo entity, String id, String userCh)
			throws IllegalAccessException, InvocationTargetException {
		PtRoomInfo roomInfo = null;
		PtFuncRoomDefine funRoom = null;// 功能室定义
		funRoom = new PtFuncRoomDefine();
		BeanUtils.copyPropertiesExceptNull(funRoom, entity);
		// 生成默认的orderindex
		Integer orderIndex = this.getDefaultOrderIndex(funRoom);
		funRoom.setRoomId(id);// 设置房间id
		funRoom.setCreateTime(new Date());
		funRoom.setCreateUser(userCh); // 创建人
		funRoom.setUpdateUser(userCh); // 创建人的中文名
		funRoom.setOrderIndex(orderIndex);// 排序

		entity.setId(keyRedisService.getId(PtFuncRoomDefine.ModuleType)); // 手动设置id
		this.merge(funRoom); // 执行添加方法

		roomInfo = thisService.get(id);
		roomInfo.setRoomName(entity.getRoomName());
		roomInfo.setUpdateTime(new Date());
		roomInfo.setUpdateUser(userCh);
		roomInfo.setRoomType("5");// 设置房间类型 5、功能室
		// roomInfo.setAreaStatu("1");// 设置为已分配
		// 执行更新方法
		thisService.merge(roomInfo);

	}

	@Override
	public Boolean delFunRoom(PtRoomInfo roomInfo, String delId, String xm) {
		Boolean flag = false;
		PtFuncRoomDefine funRoom = null;// 功能室定义
		funRoom = this.getByRoomId(delId);

		roomInfo.setUpdateTime(new Date());
		roomInfo.setUpdateUser(xm);
		roomInfo.setRoomType("0");// 设置房间类型为空
		// roomInfo.setAreaStatu("0");// 设置房间状态为未分配
		roomInfo.setRoomName(roomInfo.getRoomCode());
		thisService.merge(roomInfo);

		this.delete(funRoom);
		/*
		 * funRoom.setIsDelete(1); funRoom.setUpdateTime(new Date());
		 * funRoom.setUpdateUser(xm); this.merge(funRoom);
		 */
		return true;
	}

}