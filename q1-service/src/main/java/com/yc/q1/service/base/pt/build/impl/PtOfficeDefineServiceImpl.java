package com.yc.q1.service.base.pt.build.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.build.PtOfficeDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.service.base.pt.build.PtOfficeDefineService;
import com.yc.q1.service.base.pt.build.PtRoomInfoService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

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
public class PtOfficeDefineServiceImpl extends BaseServiceImpl<PtOfficeDefine> implements PtOfficeDefineService {

	@Resource(name = "PtOfficeDefineDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtOfficeDefine> dao) {
		super.setDao(dao);
	}

	@Resource
	private PtRoomInfoService thisService; // service层接口
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public PtOfficeDefine getByRoomId(String roomId) {
		String hql = "from PtOfficeDefine where 1=1";
		if (!roomId.isEmpty()) {
			hql += " and roomId='" + roomId + "' ";
		}
		PtOfficeDefine entity = this.getEntityByHql(hql);
		return entity;
	}

	@Override
	public void addOffRoom(PtRoomInfo entity, String id, String userCh)
			throws IllegalAccessException, InvocationTargetException {
		PtRoomInfo roomInfo = null;
		PtOfficeDefine offRoom = null;// 办公室定义
		offRoom = new PtOfficeDefine();
		BeanUtils.copyPropertiesExceptNull(offRoom, entity);
		// 生成默认的orderindex
		Integer orderIndex = this.getDefaultOrderIndex(offRoom);
		offRoom.setRoomId(id);// 设置房间id
		offRoom.setCreateUser(userCh); // 创建人
		offRoom.setUpdateUser(userCh); // 创建人的中文名
		offRoom.setCreateTime(new Date());
		offRoom.setIsAllot(false);
		offRoom.setOrderIndex(orderIndex);// 排序
		
		offRoom.setId(keyRedisService.getId(PtOfficeDefine.ModuleType));	//手动设置id
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
	public Boolean delOffRoom(PtRoomInfo roomInfo, String delId, String xm) {
		Boolean flag = false;
		PtOfficeDefine offRoom = null;// 办公室定义
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