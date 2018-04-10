package com.yc.q1.base.pt.build.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.build.dao.FuncRoomDefineDao;
import com.yc.q1.base.pt.build.model.FuncRoomDefine;
import com.yc.q1.base.pt.build.model.RoomInfo;
import com.yc.q1.base.pt.build.service.BaseFuncRoomDefineService;
import com.yc.q1.base.pt.build.service.BaseRoominfoService;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

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
public class BaseFuncroomdefineServiceImpl extends BaseServiceImpl<FuncRoomDefine>
		implements BaseFuncRoomDefineService {

	@Resource
	public void setBuildFuncroomdefinDao(FuncRoomDefineDao dao) {
		this.dao = dao;
	}

	@Resource
	private BaseRoominfoService thisService; // service层接口

	@Override
	public FuncRoomDefine getByRoomId(String roomId) {
		FuncRoomDefine entity;
		String hql = "from FuncRoomDefine where 1=1";
		if (!roomId.isEmpty()) {
			hql += " and roomId='" + roomId + "' ";
		}
		entity = this.getEntityByHql(hql);
		return entity;

	}

	@Override
	public void addFunRoom(RoomInfo entity, String id, String userCh) throws IllegalAccessException, InvocationTargetException {
		RoomInfo roomInfo = null;
		FuncRoomDefine funRoom = null;// 功能室定义
		funRoom = new FuncRoomDefine();
		BeanUtils.copyPropertiesExceptNull(funRoom, entity);
		// 生成默认的orderindex
		Integer orderIndex = this.getDefaultOrderIndex(funRoom);
		funRoom.setRoomId(id);// 设置房间id
		funRoom.setCreateTime(new Date());
		funRoom.setCreateUser(userCh); // 创建人
		funRoom.setUpdateUser(userCh); // 创建人的中文名
		funRoom.setOrderIndex(orderIndex);// 排序
		this.merge(funRoom); // 执行添加方法

		roomInfo = thisService.get(id);
		roomInfo.setRoomName(entity.getRoomName());
		roomInfo.setUpdateTime(new Date());
		roomInfo.setUpdateUser(userCh);
		roomInfo.setRoomType("5");// 设置房间类型 5、功能室
//		roomInfo.setAreaStatu("1");// 设置为已分配
		// 执行更新方法
		thisService.merge(roomInfo);

	}

	@Override
	public Boolean delFunRoom(RoomInfo roomInfo, String delId, String xm) {
		Boolean flag=false;
		FuncRoomDefine funRoom = null;// 功能室定义
		funRoom = this.getByRoomId(delId);
		
		roomInfo.setUpdateTime(new Date());
		roomInfo.setUpdateUser(xm);
		roomInfo.setRoomType("0");// 设置房间类型为空
//		roomInfo.setAreaStatu("0");// 设置房间状态为未分配
		roomInfo.setRoomName(roomInfo.getRoomCode());
		thisService.merge(roomInfo);
		
		this.delete(funRoom);
		/*funRoom.setIsDelete(1);
		funRoom.setUpdateTime(new Date());
		funRoom.setUpdateUser(xm);
		this.merge(funRoom);*/
		return true;
	}

}