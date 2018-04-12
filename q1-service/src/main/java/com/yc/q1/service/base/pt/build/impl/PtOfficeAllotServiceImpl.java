package com.yc.q1.service.base.pt.build.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.mj.MjUserRight;
import com.yc.q1.model.base.pt.basic.PtClassStudent;
import com.yc.q1.model.base.pt.basic.PtPushInfo;
import com.yc.q1.model.base.pt.build.PtOfficeAllot;
import com.yc.q1.model.base.pt.build.PtOfficeDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.model.base.pt.build.PtStudentDorm;
import com.yc.q1.model.base.pt.device.PtTerm;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.mj.MjUserRightService;
import com.yc.q1.service.base.pt.basic.PtPushInfoService;
import com.yc.q1.service.base.pt.build.PtClassDormAllotService;
import com.yc.q1.service.base.pt.build.PtDormDefineService;
import com.yc.q1.service.base.pt.build.PtOfficeAllotService;
import com.yc.q1.service.base.pt.build.PtOfficeDefineService;
import com.yc.q1.service.base.pt.build.PtRoomInfoService;
import com.yc.q1.service.base.pt.device.PtTermService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: JwOfficeallotServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: JW_T_OFFICEALLOT实体Service接口实现类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtOfficeAllotServiceImpl extends BaseServiceImpl<PtOfficeAllot> implements PtOfficeAllotService {
	
	@Resource(name = "PtOfficeAllotDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtOfficeAllot> dao) {
		super.setDao(dao);
	}
	
	@Resource
	PtOfficeDefineService offRoomService; // 办公室service层接口
	
	@Resource
	PtRoomInfoService infoService;// 房间
	
	@Resource
	PtPushInfoService pushService; // 推送
	
	@Resource
	MjUserRightService mjService; // 门禁权限
	
	@Resource
	PtTermService ptTermService; // 设备表接口

	/*
	 * @Resource JwClassRoomAllotService classservice;
	 */

	@Resource
	PtDormDefineService dormDefine;

	@Resource
	PtClassDormAllotService classDormService;
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	/**
	 * uuid：需要进行设置门禁权限的学生ID或教师ID； roomId：需要设置门禁的房间id； userId：需要取消门禁权限的学生ID或教师ID；
	 * dorm：在学生宿舍分配门禁使用，通过它来找到roomId； classStu：班级学生，暂时不设置，已经取消了班级的方式。
	 */
	@Override
	public boolean mjUserRight(String uuid, String roomId, String userId, PtStudentDorm dorm, PtClassStudent classStu) {
		try {
			if (dorm != null) {// 学生宿舍门禁分配
				String dormId = classDormService.get(dorm.getClassDormId()).getDormId(); // 班级宿舍id
				roomId = dormDefine.get(dormId).getRoomId();
			} else if (classStu != null) { // 班级的教师分配门禁 #目前还未增加教师分配该模块
				// String[] propName = { "claiId", "isDelete" };
				// Object[] propValue = { classStu.getClaiId(), 0 };
				// roomId = classservice.getByProerties(propName,
				// propValue).getRoomId();
			}
			String[] propName = { "termTypeId", "isDelete", "roomId" };
			Object[] propValue = { "4", 0, roomId };
			MjUserRight userRight = null;
			List<PtTerm> list = ptTermService.queryByProerties(propName, propValue);// 该房间是否有设备
			if (uuid == null || uuid.equals("")) {
				if (list.size() > 0) {// 解除门禁权限
					String[] uId = userId.split(","); // 房间分配解除门禁设置
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < uId.length; j++) {
							String[] name = { "termId", "userId" };
							String[] value = { list.get(i).getId(), uId[j] };
							userRight = mjService.getByProerties(name, value);
							if (userRight != null) {
								userRight.setIsDelete(1);
								userRight.setControlsegId(0);
								userRight.setCardStatusId(0);
								userRight.setUpdateTime(new Date());
								mjService.merge(userRight);
							}
						}
					}
				}
			} else {// 增加门禁权限
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						String[] name = { "termId", "userId" };
						String[] value = { list.get(i).getId(), uuid };
						userRight = mjService.getByProerties(name, value);// 该学生或教师是否已经被分配了该房间的设备
						if (userRight != null) {
							userRight.setIsDelete(0);
							userRight.setUpdateTime(new Date());
							mjService.merge(userRight);
						} else {
							userRight = new MjUserRight();
							userRight.setTermId(list.get(i).getId());
							userRight.setCreateUser("超级管理员");
							userRight.setUserId(uuid);
							
							userRight.setId(keyRedisService.getId(MjUserRight.ModuleType));	//手动设置id
							mjService.merge(userRight);
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean doAddRoom(PtOfficeAllot entity, Map hashMap, PtUser currentUser)
			throws IllegalAccessException, InvocationTargetException {
		Boolean flag = false;
		Boolean qxflag = false;
		Integer orderIndex = 0;
		PtOfficeAllot perEntity = null;
		PtOfficeAllot valioff = null;
		String[] strId = null;// 多个老师id
		StringBuffer xm = new StringBuffer();
		StringBuffer roomName = new StringBuffer();
		strId = entity.getTeacherId().split(",");// 多个老师id
		for (int i = 0; i < strId.length; i++) {
			Object[] objValue = { entity.getRoomId(), strId[i], 0 };
			String[] objName = { "roomId", "teacherId", "isDelete" };
			valioff = this.getByProerties(objName, objValue);
			if (valioff != null) {
				xm.append(valioff.getName() + ',');
				roomName.append(valioff.getRoomName() + ',');
				flag = false;
				hashMap.put("flag", flag);
				continue;
			}
			// 保存房间分配信息
			orderIndex = this.getDefaultOrderIndex(entity);
			perEntity = new PtOfficeAllot();
			BeanUtils.copyPropertiesExceptNull(entity, perEntity);
			entity.setCreateUser(currentUser.getId()); // 创建人
			entity.setTeacherId(strId[i]);
			entity.setOrderIndex(orderIndex);// 排序
			
			entity.setId(keyRedisService.getId(PtOfficeAllot.ModuleType));	//手动设置id
			this.merge(entity); // 执行添加方法

			qxflag = this.mjUserRight(strId[i], entity.getRoomId(), entity.getId(), null, null);
			/*
			 * if(!qxflag){ flag = false; hashMap.put("flag", flag);
			 * hashMap.put("qx", "qx"); continue; }
			 */
			// 将办公室设置为已分配
			String hql = " from OfficeDefine a where a.roomId='" + entity.getRoomId() + "' ";
			PtOfficeDefine office = this.getEntityByHql(hql);
			if (office != null) {
				office.setIsAllot(true);
				offRoomService.merge(office);
			}
			flag = true;
		}
		hashMap.put("xm", xm);
		hashMap.put("roomName", roomName);
		return flag;
	}

	@Override
	public Boolean doPushMessage(String roomId) {
		Boolean flag = false;
		List<PtOfficeAllot> offTeas = null;
		PtPushInfo pushInfo = null;
		PtRoomInfo roominfo = null;
		String[] str = { "roomId", "isDelete" };
		Object[] str2 = { roomId, 0 };
		offTeas = this.queryByProerties(str, str2);// 该办公室下的老师
		for (PtOfficeAllot jwTOfficeAllot : offTeas) {
			pushInfo = new PtPushInfo();
			pushInfo.setEmpleeName(jwTOfficeAllot.getName());// 姓名
			pushInfo.setEmpleeNo(jwTOfficeAllot.getUserNumb());// 学号
			pushInfo.setRegTime(new Date());
			pushInfo.setEventType("办公室分配");
			pushInfo.setPushStatus(0);
			pushInfo.setPushWay(1);
			roominfo = infoService.get(jwTOfficeAllot.getRoomId());
			pushInfo.setRegStatus(pushInfo.getEmpleeName() + "您好，你的办公室分配在" + roominfo.getAreaUpName() + "，"
					+ roominfo.getAreaName() + "，" + jwTOfficeAllot.getRoomName() + "房");
			
			pushInfo.setId(keyRedisService.getId(PtPushInfo.ModuleType));	//手动设置id
			pushService.merge(pushInfo);
		}
		flag = true;
		return flag;
	}

	@Override
	public Boolean doDeleteOff(String delIds, String roomId, String tteacId) {
		PtOfficeAllot offAllot = null;
		Boolean flag = false;
		String offRoomId = "";
		String[] delId = delIds.split(",");
		for (String id : delId) {
			offAllot = this.get(id);
			offRoomId += offAllot.getRoomId() + ',';
			this.mjUserRight(null, offAllot.getRoomId(), offAllot.getTeacherId(), null, null);
			flag = this.deleteByPK(id);
		}
		return flag;
	}

	@Override
	public void doOffSetOff(String roomIds) {
		String[] roomId = roomIds.split(",");
		PtOfficeDefine office = null;
		String sql = "";
		// List list =new ArrayList<>();
		for (String officeRoomId : roomId) {
			sql = "select count(*) from T_PT_OfficeAllot a join T_PT_OfficeDefine b  " + " on  a.roomId = b.roomId "
					+ " where a.isDelete=0 and b.isDelete=0 and b.roomId='" + officeRoomId + "'";

			Integer count = this.getQueryCountBySql(sql);
			if (count == 0) {
				office = offRoomService.get(officeRoomId);
				if (office.getIsAllot() == true) {
					office.setIsAllot(false);
					office.setUpdateTime(new Date());
					offRoomService.merge(office);
				}
			}
			/*
			 * sql="select a.ROOM_ID ,b.OFFICE_ID from JW_T_OFFICEALLOT a right join BUILD_T_OFFICEDEFINE b  on  a.ROOM_ID = b.ROOM_ID where b.ROOM_ID='"
			 * +officeRoomId+"'"; list = this.querySql(sql); for(int j=0;
			 * j<list.size();j++){ Object[] object= (Object[]) list.get(j);
			 * if(object[0]==null){ String dormId= (String) object[1]; office =
			 * offRoomService.get(dormId);
			 * if(office.getRoomStatus().equals("1")){
			 * office.setRoomStatus("0"); office.setUpdateTime(new Date());
			 * offRoomService.merge(office); }
			 * 
			 * } }
			 */
		}
	}
}