package com.yc.q1.base.pt.build.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.build.dao.OfficeDefineDao;
import com.yc.q1.base.pt.build.service.OfficeDefineService;
import com.yc.q1.base.pt.build.service.RoomInfoService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.build.OfficeDefine;
import com.yc.q1.model.base.pt.build.RoomInfo;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: BuildOfficeServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 办公室信息实体Service接口实现类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class OfficeDefineServiceImpl extends BaseServiceImpl<OfficeDefine> implements OfficeDefineService {

	@Resource(name = "OfficeDefineDao") // 将具体的dao注入进来
	public void setDao(BaseDao<OfficeDefine> dao) {
		super.setDao(dao);
	}

	@Resource
	private RoomInfoService thisService; // service层接口
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public OfficeDefine getByRoomId(String roomId) {
		String hql = "from OfficeDefine where 1=1";
		if (!roomId.isEmpty()) {
			hql += " and roomId='" + roomId + "' ";
		}
		OfficeDefine entity = this.getEntityByHql(hql);
		return entity;
	}

	@Override
	public void addOffRoom(RoomInfo entity, String id, String userCh)
			throws IllegalAccessException, InvocationTargetException {
		RoomInfo roomInfo = null;
		OfficeDefine offRoom = null;// 办公室定义
		offRoom = new OfficeDefine();
		BeanUtils.copyPropertiesExceptNull(offRoom, entity);
		// 生成默认的orderindex
		Integer orderIndex = this.getDefaultOrderIndex(offRoom);
		offRoom.setRoomId(id);// 设置房间id
		offRoom.setCreateUser(userCh); // 创建人
		offRoom.setUpdateUser(userCh); // 创建人的中文名
		offRoom.setCreateTime(new Date());
		offRoom.setOrderIndex(orderIndex);// 排序
		
		offRoom.setId(keyRedisService.getId(OfficeDefine.ModuleType));	//手动设置id
		this.merge(offRoom); // 执行添加方法

		roomInfo = thisService.get(id);
		roomInfo.setRoomName(entity.getRoomName());
		roomInfo.setUpdateTime(new Date());
		roomInfo.setUpdateUser(userCh);
		roomInfo.setRoomType("2");// 设置房间类型 2.办公室

		// 执行更新方法
		thisService.merge(roomInfo);

	}

	@Override
	public Boolean delOffRoom(RoomInfo roomInfo, String delId, String xm) {
		Boolean flag = false;
		OfficeDefine offRoom = null;// 办公室定义
		offRoom = this.getByRoomId(delId);
		if (!offRoom.getIsAllot() == true) {
			roomInfo.setUpdateTime(new Date());
			roomInfo.setUpdateUser(xm);
			roomInfo.setRoomType("0");// 设置房间类型为空
			roomInfo.setRoomName(roomInfo.getRoomCode());
			thisService.merge(roomInfo);// 执行更新方法

			this.delete(offRoom);
			/*
			 * offRoom.setIsDelete(1); offRoom.setUpdateTime(new Date());
			 * offRoom.setUpdateUser(xm); this.merge(offRoom);
			 */
			flag = true;
		} else {// 已分配
			flag = false;
		}
		return flag;

	}
}