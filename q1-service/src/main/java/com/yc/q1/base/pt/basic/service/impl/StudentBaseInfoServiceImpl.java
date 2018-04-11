package com.yc.q1.base.pt.basic.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.dao.StudentBaseInfoDao;
import com.yc.q1.base.pt.basic.service.GradeClassService;
import com.yc.q1.base.pt.basic.service.StudentBaseInfoService;
import com.yc.q1.base.pt.system.service.RoleService;
import com.yc.q1.base.pt.system.service.UserDeptJobService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.basic.GradeClass;
import com.yc.q1.model.base.pt.basic.StudentBaseInfo;
import com.yc.q1.model.base.pt.system.Role;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.constant.AdminType;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.ExtDataFilter;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.JsonBuilder;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: StuBaseinfoServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 学生基本信息实体Service接口实现类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class StudentBaseInfoServiceImpl extends BaseServiceImpl<StudentBaseInfo> implements StudentBaseInfoService {

	@Resource(name = "StudentBaseInfoDao") // 将具体的dao注入进来
	public void setDao(BaseDao<StudentBaseInfo> dao) {
		super.setDao(dao);
	}

	@Resource
	private GradeClassService classService;

	@Resource
	private RoleService roleService; // service层接口

	@Resource
	private UserDeptJobService userDeptJobService;
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<StudentBaseInfo> getStudentList(Integer start, Integer limit, String sort, String filter,
			Boolean isDelete, String claiId, User currentUser) {
		String queryFilter = filter;
		String qrClassId = claiId;
		// 当前用户有权限的班级列表
		QueryResult<GradeClass> qr = classService.getGradeClassList(0, 0, "", "", true, currentUser);
		List<GradeClass> jgClass = qr.getResultList();
		StringBuffer sb = new StringBuffer();
		if (jgClass.size() > 0) {
			for (GradeClass jwTGrade : jgClass) {
				sb.append(jwTGrade.getId() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		if (StringUtils.isEmpty(claiId) || claiId.equals("2851655E-3390-4B80-B00C-52C7CA62CB39")) {
			// 选择没有选择年级，使用有权限的所有年级
			qrClassId = sb.toString();
		}
		ExtDataFilter selfFilter = (ExtDataFilter) JsonBuilder.getInstance().fromJson(
				"{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + qrClassId + "\",\"field\":\"classId\"}",
				ExtDataFilter.class);

		if (StringUtils.isNotEmpty(filter)) {
			List<ExtDataFilter> listFilters = (List<ExtDataFilter>) JsonBuilder.getInstance().fromJsonArray(filter,
					ExtDataFilter.class);
			listFilters.add(selfFilter);

			queryFilter = JsonBuilder.getInstance().buildObjListToJson((long) listFilters.size(), listFilters, false);
		} else {
			queryFilter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + qrClassId
					+ "\",\"field\":\"classId\"}]";
		}
		QueryResult<StudentBaseInfo> qrReturn = this.queryPageResult(start, limit, sort, queryFilter, true);
		return qrReturn;
	}

	@Override
	public StudentBaseInfo doAddStudent(StudentBaseInfo entity,
			User currentUser/* , String deptJobId */) {
		StudentBaseInfo saveEntity = new StudentBaseInfo();
		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("uuid");
		try {
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Integer orderIndex = this.getDefaultOrderIndex(entity);
		saveEntity.setOrderIndex(orderIndex);
		saveEntity.setCategory("2");
		saveEntity.setIsHidden(false);
		saveEntity.setIsSystem(true);
		saveEntity.setRightType("2");
		saveEntity.setState(true);
		saveEntity.setUserPwd(new Sha256Hash("123456").toHex());
		saveEntity.setSchoolId(AdminType.ADMIN_ORG_ID);

		// 增加角色
		Set<Role> theUserRoler = saveEntity.getSysRoles();
		Role role = roleService.getByProerties(new String[] { "roleCode", "isDelete" }, new Object[] { "STUDENT", 0 });

		if (role != null) {
			theUserRoler.add(role);
			saveEntity.setSysRoles(theUserRoler);
		}

		// 增加时要设置创建人
		saveEntity.setCreateUser(currentUser.getId()); // 创建人

		// 持久化到数据库
		saveEntity.setId(keyRedisService.getId(StudentBaseInfo.ModuleType));	//手动设置id
		entity = this.merge(saveEntity);

		String userIds = entity.getId();
		String deptJobId = entity.getDeptId();
		userDeptJobService.doAddUserToDeptJob(deptJobId, userIds, currentUser);
		return entity;
	}
}