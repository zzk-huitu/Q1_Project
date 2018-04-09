package com.zd.school.plartform.baseset.service.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.constant.StatuVeriable;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.StringUtils;
import com.zd.school.build.define.model.BuildRoominfo;
import com.zd.school.build.define.model.RoomArea;
import com.zd.school.plartform.baseset.dao.BaseCampusDao;
import com.zd.school.plartform.baseset.model.Campus;
import com.zd.school.plartform.baseset.model.Department;
import com.zd.school.plartform.baseset.service.BaseCampusService;
import com.zd.school.plartform.baseset.service.BaseRoomareaService;
import com.zd.school.plartform.system.model.User;
import com.zd.school.plartform.system.service.SysOrgService;
import com.zd.school.redis.service.DeptRedisService;

/**
 * 
 * ClassName: BaseCampusServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 校区信息实体Service接口实现类. date: 2016-08-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class BaseCampusServiceImpl extends BaseServiceImpl<Campus> implements BaseCampusService {

	@Resource
	public void setBaseCampusDao(BaseCampusDao dao) {
		this.dao = dao;
	}

	@Resource
	private BaseRoomareaService areaService;

	@Resource
	private SysOrgService orgService;
	
	@Resource
	private DeptRedisService deptRedisService;
	
	@Override
	public Campus doAdd(Campus entity, User currentUser)
			throws IllegalAccessException, InvocationTargetException {
		// 增加区域表的数据
		Campus saveEntity = new Campus();
		
		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("id");
		BeanUtils.copyProperties(saveEntity, entity, excludedProp);

		saveEntity.setCreateUser(currentUser.getId());
		this.merge(saveEntity);
		
		// 增加到建筑物的区域
		RoomArea roomarea = new RoomArea(saveEntity.getId());			
		roomarea.setNodeText(saveEntity.getCampusName());
		roomarea.setOrderIndex(saveEntity.getOrderIndex());
		roomarea.setCreateTime(new Date());
		roomarea.setCreateUser(currentUser.getId());
		roomarea.setAreaType("02");
		roomarea.setParentNode(saveEntity.getSchoolId());
		roomarea.setLeaf(true);
		roomarea.setNodeLevel(2);
		roomarea.setTreeIds(saveEntity.getSchoolId() + "," + saveEntity.getId());
		areaService.merge(roomarea);

		// 增加到部门的第二级
		Department orgSave = new Department(saveEntity.getId());
		orgSave.setNodeText(saveEntity.getCampusName()); // 部门名称
		orgSave.setOrderIndex(saveEntity.getOrderIndex());
		orgSave.setParentNode(saveEntity.getSchoolId()); // 上级节点
		orgSave.setCreateUser(currentUser.getId()); // 创建人
		orgSave.setDeptType("02"); // 默认类型为校区
		orgSave.setLeaf(true);
		orgSave.setIsSystem(true);
		orgSave.setAllDeptName(saveEntity.getSchoolName()+"/"+saveEntity.getCampusName());

		Department parEntity = orgService.get(saveEntity.getSchoolId());
		parEntity.setLeaf(false);
		orgService.merge(parEntity);
		
		orgSave.BuildNode(parEntity);
		orgService.merge(orgSave);
		
		//删除所有redis部门缓存数据，以免产生误会
		deptRedisService.deleteDeptTreeAll();
		
		return entity;
	}

	@Override
	public Campus doUpdate(Campus entity, User currentUser)
			throws IllegalAccessException, InvocationTargetException {
		
		// 先拿到已持久化的实体
		Campus perEntity = this.get(entity.getId());
		String oldCampusName=perEntity.getCampusName();
		// 将entity中不为空的字段动态加入到perEntity中去。
		BeanUtils.copyPropertiesExceptNull(perEntity, entity);

		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
		entity = this.merge(perEntity);// 执行修改方法

		
		if(!oldCampusName.equals(entity.getCampusName())){			
			// 更新建筑物区域中对应的名称
			RoomArea roomarea = areaService.getByProerties(new String[]{"isDelete","id"} ,new Object[]{0,entity.getId()});
			if (roomarea != null) {
				roomarea.setNodeText(entity.getCampusName());
				roomarea.setUpdateTime(new Date());
				roomarea.setUpdateUser(currentUser.getId());
				areaService.merge(roomarea);
			}
	
			// 更新部门名称
			Department orgSave = orgService.getByProerties(new String[]{"isDelete","id"} ,new Object[]{0,entity.getId()});
			if(orgSave!=null){
				orgSave.setNodeText(entity.getCampusName());
				orgSave.setOrderIndex(entity.getOrderIndex());
				orgSave.setUpdateTime(new Date());
				orgSave.setUpdateUser(currentUser.getId());
				orgService.merge(orgSave);
				
				//更新其他部门岗位之类的数据				
				orgService.setDeptName(entity.getCampusName(), orgSave.getId());						
				Department parentOrg = orgService.get(orgSave.getParentNode());
				if(parentOrg!=null&&!orgSave.getParentNode().equals("ROOT"))
					orgService.setChildAllDeptName(orgSave, parentOrg.getAllDeptName());
				else
					orgService.setChildAllDeptName(orgSave, "ROOT");	
				
			}				
		}
			
		//删除所有redis部门缓存数据，以免产生误会
		deptRedisService.deleteDeptTreeAll();
		
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public boolean doDelete(String delIds, User currentUser,Map hashMap)
			throws IllegalAccessException, InvocationTargetException {
		boolean rs = true;
		String[] ids = delIds.split(",");
		Integer childOrg = 0;
		Integer childDeptJob = 0;
		Integer childArea = 0;
		StringBuffer notSb = new StringBuffer();
		StringBuffer canSb = new StringBuffer();
		StringBuffer orgSb = new StringBuffer();
		StringBuffer areaSb = new StringBuffer();
		for (String uuid : ids) {
			// 检查当前校区是否配置了下属的部门
			Department orgSave = orgService.getByProerties(new String[]{"isDelete","id"} ,new Object[]{0,uuid});		
			if(orgSave!=null){
				childOrg = orgService.getChildCount(orgSave.getId());		
				//检查是否此部门分配了岗位，或是其他部门岗位的上级部门岗位
				childDeptJob=orgService.getDeptJobCount(orgSave.getId());
			}
			
			// 检查当前校区是否配置了下属的建筑物区域
			RoomArea roomarea = areaService.getByProerties(new String[]{"isDelete","id"} ,new Object[]{0,uuid});		
			if(roomarea!=null)
				childArea = areaService.getChildCount(roomarea.getId());

			// 如果都没有配置下级，则可以删除
			if (childArea.equals(0) && childOrg.equals(0) && childDeptJob.equals(0)) {
				canSb.append(uuid + ",");
				
				if(orgSave!=null)
					orgSb.append(orgSave.getId() + ",");
				
				if(roomarea!=null)
					areaSb.append(roomarea.getId() + ",");			
			}else{//当校区信息关联了部门管理或者建筑物时 ，不能删除
				Campus baseCampus=this.get(uuid);
				notSb.append(baseCampus.getCampusName()+",");
			    hashMap.put("rs", false);
			}
		}
		if (canSb.length() > 0) {		
			if (orgSb.length() > 0) {
				String s1 = StringUtils.trimLast(orgSb.toString());
				rs = orgService.doLogicDelOrRestore(s1, StatuVeriable.ISDELETE, currentUser.getId());
			}
			
			if (areaSb.length() > 0) {
				String s2 = StringUtils.trimLast(areaSb.toString());
				rs = areaService.doLogicDelOrRestore(s2, StatuVeriable.ISDELETE, currentUser.getId());
			}
					
			String s3 = StringUtils.trimLast(canSb.toString());
			rs = this.doLogicDelOrRestore(s3, StatuVeriable.ISDELETE, currentUser.getId());
			
			//删除所有redis部门缓存数据，以免产生误会
			deptRedisService.deleteDeptTreeAll();
			
		} else {
			rs = false;
		}
		hashMap.put("notSb", notSb);
		// TODO Auto-generated method stub
		return rs;
	}
	
	
	
	//根据房间获取这个房间初中或者高中的ID（校区ID）
	@Override
	public String getCampusIdByRoom(BuildRoominfo roominfo) {
		List<Campus> campus = this.queryByHql("from Campus where isDelete=0");
		List<String> campusids = new ArrayList<String>();
		for (Campus baseCampus : campus) {
			campusids.add(baseCampus.getId());
		}
		String parentId = roominfo.getAreaId();
		while (true) {
			RoomArea roomarea = areaService.get(parentId);
			if (campusids.contains(roomarea.getId())) {
				return roomarea.getId();
			}
			parentId = roomarea.getParentNode();
			if(parentId.equals("ROOT"))
				return null;	//找不到校区
		}
	}

}