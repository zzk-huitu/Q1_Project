package com.yc.q1.base.pt.build.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.mj.service.MjUserRightService;
import com.yc.q1.base.pt.basic.service.PushInfoService;
import com.yc.q1.base.pt.build.dao.TeacherDormDao;
import com.yc.q1.base.pt.build.service.DormDefineService;
import com.yc.q1.base.pt.build.service.OfficeAllotService;
import com.yc.q1.base.pt.build.service.TeacherDormService;
import com.yc.q1.base.pt.device.service.TermService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.build.PtDormDefine;
import com.yc.q1.model.base.pt.build.PtTeacherDorm;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: DormStudentdormServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: (DormTeacherDorm)实体Service接口实现类. date:
 * 2016-08-26
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class TeacherDormServiceImpl extends BaseServiceImpl<PtTeacherDorm> implements TeacherDormService {

	@Resource(name = "TeacherDormDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtTeacherDorm> dao) {
		super.setDao(dao);
	}

	@Resource
	DormDefineService dormRoomService; // 宿舍service层接口

	@Resource
	TermService ptTermService;

	@Resource
	PushInfoService pushService;

	@Resource
	MjUserRightService mjService;

	@Resource
	OfficeAllotService officeAllotService;

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public QueryResult<PtTeacherDorm> list(Integer start, Integer limit, String sort, String filter, String whereSql,
			String orderSql, PtUser currentUser) {
		String sortSql = StringUtils.convertSortToSql(sort);
		String filterSql = StringUtils.convertFilterToSql(filter);

		StringBuffer hql = new StringBuffer("from TeacherDorm o where 1=1 and isDelete=0 ");
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

		QueryResult<PtTeacherDorm> qResult = this.queryResult(hql.toString(), start, limit);
		return qResult;
	}

	@Override
	public Boolean doOut(String outIds, PtUser currentUser) {
		boolean flag = false;
		PtTeacherDorm entity = null;
		// MjUserright userRight = null;
		String[] outId = outIds.split(",");
		for (String id : outId) {
			entity = this.get(id);
			// 解除门禁权限
			officeAllotService.mjUserRight(null, entity.getRoomId(), entity.getTeacherId(), null, null);

			entity.setOutTime(new Date());
			entity.setInOutState("1");
			entity.setCreateUser(currentUser.getId());
			entity.setUpdateTime(new Date());
			this.merge(entity);
			flag = true;
		}
		return flag;
	}

	@Override
	public Boolean doAddDormTea(PtTeacherDorm entity, Map hashMap, HttpServletRequest request, PtUser currentUser)
			throws IllegalAccessException, InvocationTargetException {
		Boolean flag = false;
		String roomName = request.getParameter("roomName");
		String bedCounts = request.getParameter("bedCount");
		String arkCounts = request.getParameter("arkCount");
		String userNumbs = request.getParameter("userNumb");
		String sendCheckNames = request.getParameter("sendCheckName");
		String tteacIds = entity.getTeacherId();

		String[] tteacIdArr = tteacIds.split(",");
		String[] bedCount = bedCounts.split(",");
		String[] arkCount = arkCounts.split(",");
		String[] userNumb = userNumbs.split(",");
		String[] sendCheckName = sendCheckNames.split(",");

		List<String> excludedProp = new ArrayList<String>();
		excludedProp.add("id");

		StringBuffer teaName = new StringBuffer();
		StringBuffer teaInRoom = new StringBuffer();
		PtTeacherDorm perEntity = null;
		for (int i = 0; i < tteacIdArr.length; i++) {
			// 查询此教师当前是否在入住此宿舍
			String hql = " from TeacherDorm where isDelete = 0 and inOutState=0 and teacherId = '" + tteacIdArr[i]
					+ "' ";
			List<PtTeacherDorm> lists = this.queryByHql(hql);
			if (lists.size() > 0) {
				for (PtTeacherDorm dormEntity : lists) {
					teaName.append(dormEntity.getName() + ',');
					teaInRoom.append(dormEntity.getDormName() + ',');
				}
				flag = false;
				hashMap.put("flag", flag);
				continue;
			}
			perEntity = new PtTeacherDorm();
			BeanUtils.copyProperties(perEntity, entity, excludedProp);
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			perEntity.setOrderIndex(orderIndex);// 排序
			// 增加时要设置创建人
			perEntity.setCreateUser(currentUser.getId()); // 创建人
			perEntity.setTeacherId(tteacIdArr[i]);
			perEntity.setSarkNo(Integer.parseInt(arkCount[i]));
			perEntity.setBedNo(Integer.parseInt(bedCount[i]));
			
			// 持久化到数据库
			perEntity.setId(keyRedisService.getId(PtTeacherDorm.ModuleType));	//手动设置id
			entity = this.merge(perEntity);

			// 写入门禁权限
			officeAllotService.mjUserRight(tteacIdArr[i], entity.getRoomId(), null, null, null);
			/*
			 * List<PtTerm> ptTrems = ptTermService.queryByProerties("roomId",
			 * entity.getRoomId()); for (PtTerm ptTerm : ptTrems) { MjUserright
			 * mj = new MjUserright(); mj.setStatusID(0);
			 * mj.setStuId(userNumb[i]); mj.setTermId(ptTerm.getId());
			 * mjService.merge(mj); }
			 */

			// 推送消息
			String regStatus = "您好," + sendCheckName[i] + "老师,您已经成功分配至" + roomName + "房间,床位编号:" + bedCount[i] + ",柜子编号:"
					+ arkCount[i];
			pushService.pushInfo(sendCheckName[i], userNumb[i], "事件提醒", regStatus, currentUser);

			// 将教室宿舍设置为已分配
			String dormHql = "update DormDefine set isAllot='1' where isDelete=0 and roomId='" + entity.getRoomId()
					+ "'";
			dormRoomService.doExecuteCountByHql(dormHql);

			flag = true;
		}
		hashMap.put("teaName", teaName);
		hashMap.put("teaInRoom", teaInRoom);
		return flag;
	}

	@Override
	public Boolean doDelete(String delIds) {
		PtTeacherDorm entity = null;
		boolean flag = false;
		String[] delId = delIds.split(",");
		for (String id : delId) {
			entity = this.get(id);
			// 解除门禁权限
			officeAllotService.mjUserRight(null, entity.getRoomId(), entity.getTeacherId(), null, null);
			flag = this.deleteByPK(id);
		}

		return flag;
	}

	@Override
	public void doSettingOff(String roomIds) {
		String[] roomId = roomIds.split(",");
		String teacDormSql = "";
		PtDormDefine dorm = null;
		// List list = new ArrayList<>();
		for (String teacRoomId : roomId) {
			teacDormSql = "select count(*) from T_PT_TeacherDorm a join T_PT_DormDefine b  "
					+ " on  a.roomId = b.roomId " + " where a.isDelete=0 and b.isDelete=0 and b.roomId='" + teacRoomId
					+ "'";

			Integer count = this.getQueryCountBySql(teacDormSql);
			if (count == 0) {
				dorm = dormRoomService.get(teacRoomId);
				if (dorm.getIsAllot() == true) {
					dorm.setIsAllot(false);
					dorm.setUpdateTime(new Date());
					dormRoomService.merge(dorm);
				}
			}
			/*
			 * teacDormSql =
			 * "select a.ROOM_ID ,b.DORM_ID from DORM_T_TEACHERDORM a right join BUILD_T_DORMDEFINE b  on  a.ROOM_ID = b.ROOM_ID where b.ROOM_ID='"
			 * + teacRoomId + "'"; list = this.querySql(teacDormSql);
			 * 
			 * for (int j = 0; j < list.size(); j++) { Object[] object =
			 * (Object[]) list.get(j); if (object[0] == null) { String dormId =
			 * (String) object[1]; dorm = dormRoomService.get(dormId); if
			 * (dorm.getRoomStatus().equals("1")) { dorm.setRoomStatus("0");
			 * dorm.setUpdateTime(new Date()); dormRoomService.merge(dorm);
			 * 
			 * } } }
			 */

		}
	}
}