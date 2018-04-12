package com.yc.q1.service.base.pt.build.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.dao.base.pt.build.DormDefineDao;
import com.yc.q1.model.base.pt.build.PtDormDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.build.PtDormDefineService;
import com.yc.q1.service.base.pt.build.PtRoomInfoService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: BuildOfficeServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 宿舍定义Service接口实现类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtDormDefineServiceImpl extends BaseServiceImpl<PtDormDefine> implements PtDormDefineService {
	@Resource(name = "DormDefineDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtDormDefine> dao) {
		super.setDao(dao);
	}

	@Resource
	private PtRoomInfoService thisService; // service层接口

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public PtDormDefine getByRoomId(String roomId) {
		String hql = "from DormDefine where 1=1";
		if (!roomId.isEmpty()) {
			hql += " and roomId='" + roomId + "' ";
		}
		PtDormDefine entity = this.getEntityByHql(hql);
		return entity;
	}

	@Override
	public PtDormDefine doUpdateEntity(PtDormDefine entity, PtUser currentUser) throws Exception {
		PtRoomInfo roomInfo = null;
		// 先拿到已持久化的实体
		PtDormDefine perEntity = this.getByRoomId(entity.getId());

		// 将entity中不为空的字段动态加入到perEntity中去。
		BeanUtils.copyPropertiesExceptNull(perEntity, entity);
		if (entity.getTeacherId() != null && !entity.getTeacherId().equals(""))
			perEntity.setDormAdminId(entity.getTeacherId()); // 设置教师id
		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
		entity = this.merge(perEntity);// 执行修改方法

		roomInfo = thisService.get(entity.getId());
		roomInfo.setRoomName(entity.getRoomName());
		roomInfo.setUpdateTime(new Date());
		roomInfo.setUpdateUser(currentUser.getId());
		// 执行更新方法
		thisService.merge(roomInfo);
		return entity;
	}

	@Override
	public void addDormRoom(PtRoomInfo entity, PtDormDefine dormRoom, String id, String userCh)
			throws IllegalAccessException, InvocationTargetException {
		PtRoomInfo roomInfo = null;

		roomInfo = thisService.get(id);
		roomInfo.setUpdateTime(new Date());
		roomInfo.setUpdateUser(userCh);
		roomInfo.setRoomName(entity.getRoomName());
		roomInfo.setRoomType("1");// 设置房间类型 1.宿舍
		// roomInfo.setAreaStatu(1);// 设置为已分配
		
		// 执行更新方法
		thisService.merge(roomInfo);

		BeanUtils.copyPropertiesExceptNull(dormRoom, entity);

		// 生成默认的orderindex
		Integer orderIndex = this.getDefaultOrderIndex(dormRoom);
		dormRoom.setRoomId(id);// 设置房间id
		dormRoom.setCreateTime(new Date());
		dormRoom.setCreateUser(userCh); // 创建人
		dormRoom.setUpdateUser(userCh); // 创建人的中文名
		dormRoom.setOrderIndex(orderIndex);// 排序
		
		dormRoom.setId(keyRedisService.getId(PtDormDefine.ModuleType));	//手动设置id
		this.merge(dormRoom); // 执行添加方法

	}

	@Override
	public Boolean delDormRoom(PtRoomInfo roomInfo, String delId, String xm) {
		Boolean flag = false;
		PtDormDefine dormRoom = null;// 宿舍定义
		dormRoom = this.getByRoomId(delId);// roomId
		if (!dormRoom.getIsAllot() == true) {// 0：未分配 1:已分配
			roomInfo.setUpdateTime(new Date());
			roomInfo.setUpdateUser(xm);
			roomInfo.setRoomType("0");// 设置房间类型为空
			// roomInfo.setAreaStatu(0);// 设置房间状态为未分配
			roomInfo.setRoomName(roomInfo.getRoomCode());
			thisService.merge(roomInfo);// 执行更新方法

			this.delete(dormRoom);
			/*
			 * dormRoom.setIsDelete(1); dormRoom.setUpdateTime(new Date());
			 * dormRoom.setUpdateUser(xm); this.merge(dormRoom);
			 */
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

}