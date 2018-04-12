package com.yc.q1.service.base.pt.build.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.build.PtClassRoomDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.service.base.pt.build.PtClassRoomDefineService;
import com.yc.q1.service.base.pt.build.PtRoomInfoService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BuildClassroomServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 教室信息实体Service接口实现类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtClassRoomDefineServiceImpl extends BaseServiceImpl<PtClassRoomDefine> implements PtClassRoomDefineService {

	@Resource(name = "PtClassRoomDefineDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtClassRoomDefine> dao) {
		super.setDao(dao);
	}

	@Resource
	private PtRoomInfoService thisService; // service层接口
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public PtClassRoomDefine getByRoomId(String roomId) {
		String hql = "from ClassRoomDefine where 1=1";
		if (!roomId.isEmpty()) {
			hql += " and roomId='" + roomId + "' ";
		}
		PtClassRoomDefine entity = this.getEntityByHql(hql);
		return entity;
	}

	@Override
	public void addClassRoom(PtRoomInfo entity, String id, String userCh)
			throws IllegalAccessException, InvocationTargetException {
		PtRoomInfo roomInfo = null;
		PtClassRoomDefine classRoom = null;// 教室定义
		roomInfo = thisService.get(id);
		roomInfo.setUpdateTime(new Date());
		roomInfo.setUpdateUser(userCh);
		roomInfo.setRoomName(entity.getRoomName());
		roomInfo.setRoomType("3");// 设置房间类型 3.教室
		// roomInfo.setAreaStatu("1");// 设置为已分配
		// 执行更新方法
		thisService.merge(roomInfo);

		classRoom = new PtClassRoomDefine();
		BeanUtils.copyPropertiesExceptNull(classRoom, entity);
		// 生成默认的orderindex
		Integer orderIndex = this.getDefaultOrderIndex(classRoom);
		classRoom.setRoomId(id);// 设置房间id
		classRoom.setCreateTime(new Date());
		classRoom.setCreateUser(userCh); // 创建人
		classRoom.setUpdateUser(userCh); // 创建人的中文名
		classRoom.setOrderIndex(orderIndex);// 排序
		
		classRoom.setId(keyRedisService.getId(PtClassRoomDefine.ModuleType));	//手动设置id
		this.merge(classRoom); // 执行添加方法

	}

	@Override
	public Boolean delClassRoom(PtRoomInfo roomInfo, String delId, String xm) {
		PtClassRoomDefine classRoom = null;// 教室定义
		classRoom = this.getByRoomId(delId);

		roomInfo.setUpdateTime(new Date());
		roomInfo.setUpdateUser(xm);
		roomInfo.setRoomType("0");// 设置房间类型为空
		// roomInfo.setAreaStatu("0");// 设置房间状态为未分配
		roomInfo.setRoomName(roomInfo.getRoomCode());
		thisService.merge(roomInfo);

		this.delete(classRoom);
		/*
		 * classRoom.setIsDelete(1); classRoom.setUpdateTime(new Date());
		 * classRoom.setUpdateUser(xm); this.merge(classRoom);
		 */
		return true;
	}

}