package com.yc.q1.base.pt.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.model.StudentBaseInfo;
import com.yc.q1.base.pt.basic.model.TeacherBaseInfo;
import com.yc.q1.base.pt.basic.service.TeaTeacherbaseService;
import com.yc.q1.base.pt.system.dao.UserDao;
import com.yc.q1.base.pt.system.model.DataDictItem;
import com.yc.q1.base.pt.system.model.Department;
import com.yc.q1.base.pt.system.model.MenuPermission;
import com.yc.q1.base.pt.system.model.Permission;
import com.yc.q1.base.pt.system.model.Role;
import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.system.service.BaseDicitemService;
import com.yc.q1.base.pt.system.service.SysMenuPermissionService;
import com.yc.q1.base.pt.system.service.SysOrgService;
import com.yc.q1.base.pt.system.service.SysRoleService;
import com.yc.q1.base.pt.system.service.SysUserService;
import com.yc.q1.base.pt.system.service.SysUserdeptjobService;
import com.yc.q1.base.redis.service.UserRedisService;
import com.zd.core.constant.AdminType;
import com.zd.core.constant.Constant;
import com.zd.core.model.ImportNotInfo;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.SortListUtil;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: BaseTUserServiceImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 用户管理实体Service接口实现类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl<User> implements SysUserService {

	private static Logger logger = Logger.getLogger(SysUserServiceImpl.class);

	@Resource
	public void setBaseTUserDao(UserDao dao) {
		this.dao = dao;
	}

	@Resource
	private SysRoleService roleService; // 角色数据服务接口

	@Resource
	private SysOrgService orgService; // 部门数据服务接口

	@Resource
	private TeaTeacherbaseService teacherService;

	@Resource
	private SysMenuPermissionService menuPermissionService;

	@Resource
	private UserRedisService userRedisService;

	@Resource
	private BaseDicitemService dicitemService;
	
	@Resource
	private SysUserdeptjobService userDeptJobService;

	
	@Override
	public User doAddUser(User entity, User currentUser/*, String deptJobId*/) throws Exception, InvocationTargetException {

		String userPwd = entity.getUserPwd();
		userPwd = new Sha256Hash(userPwd).toHex();

		// 根据身份来做不同的处理
		User saveEntity = null;
		String category = entity.getCategory();
		if (category.equals("1")) { // 老师
			TeacherBaseInfo t = new TeacherBaseInfo();
			saveEntity = t;
			//增加角色
			Set<Role>  theUserRoler = saveEntity.getSysRoles();
			Role role = roleService.getByProerties(new String[]{"roleCode","isDelete"}, new Object[]{"TEACHER",0});
			
			if(role!=null){
				theUserRoler.add(role);
				saveEntity.setSysRoles(theUserRoler);
			}

		} else if (category.equals("2")) { // 学生
			StudentBaseInfo t = new StudentBaseInfo();
			// t.setSchoolId("2851655E-3390-4B80-B00C-52C7CA62CB39");
			// t.setClassId(entity.getDeptId());

			saveEntity = t;
			//增加角色
			Set<Role>  theUserRoler = saveEntity.getSysRoles();
			Role role = roleService.getByProerties(new String[]{"roleCode","isDelete"}, new Object[]{"STUDENT",0});
			
			if(role!=null){
				theUserRoler.add(role);
				saveEntity.setSysRoles(theUserRoler);
			}

		} else {
			saveEntity = new User();
		}

		entity.setId(null);
		BeanUtils.copyPropertiesExceptNull(saveEntity, entity);

		// 处理用户所属的角色
		/*
		 * if (roleId.length() > 0) { String[] roleList = roleId.split(",");
		 * Set<SysRole> isUserRoles = entity.getSysRoles(); for (String ids :
		 * roleList) { SysRole role = roleService.get(ids);
		 * isUserRoles.add(role); }
		 * 
		 * entity.setSysRoles(isUserRoles); }
		 */
		saveEntity.setSchoolId(AdminType.ADMIN_ORG_ID);
		saveEntity.setUserPwd(userPwd);
		saveEntity.setIsSystem(true);
		saveEntity.setIsHidden(false);
		saveEntity.setCreateUser(currentUser.getId()); // 创建人
		saveEntity.setRightType("2");
		entity = this.merge(saveEntity);
		
		String userIds = entity.getId();
		String deptJobId = entity.getDeptId();
		userDeptJobService.doAddUserToDeptJob( deptJobId, userIds, currentUser);

		return entity;
	}

	@Override
	public User doUpdateUser(User entity, User currentUser) throws Exception, InvocationTargetException {

		// 先拿到已持久化的实体
		User perEntity = this.get(entity.getId());

		Set<Role> isUserRoles = perEntity.getSysRoles();
		/* Set<BaseOrg> userDept = perEntity.getUserDepts(); */

		// 将entity中不为空的字段动态加入到perEntity中去。
		BeanUtils.copyPropertiesExceptNull(perEntity, entity);

		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
		perEntity.setSysRoles(isUserRoles);
		/* perEntity.setUserDepts(userDept); */
		// entity = thisService.merge(perEntity);// 执行修改方法

		// 处理用户所属的角色
		/*
		 * if (roleId.length() > 0) { String[] roleList = roleId.split(",");
		 * Set<SysRole> isUserRoles = entity.getSysRoles(); //先清除所有的角色
		 * isUserRoles.removeAll(isUserRoles); for (String ids : roleList) {
		 * SysRole role = roleService.get(ids); isUserRoles.add(role); }
		 * 
		 * perEntity.setSysRoles(isUserRoles); }
		 */
		// 持久化到数据库
		entity = this.merge(perEntity);

		// //切换数据源
		// DBContextHolder.setDBType(DBContextHolder.DATA_SOURCE_Up6);
		//
		// String sqlInsert = "insert into
		// Tc_Employee(UserId,DepartmentID,EmployeeName,EmployeeStrID,SID,EmployeePWD,SexID,identifier,cardid,CardTypeID,EmployeeStatusID,PositionId)
		// "
		// + "values('" + entity.getId() + "','1','"
		// + currentUser.getId() + "'," + "'" + currentUser.getJobId() +
		// "','','','"
		// + currentUser.getXbm() + "','" + currentUser.getSfzjh() +
		// "',0,1,24,19)";
		//
		// int row=this.doExecuteCountBySql(sqlInsert.toString());
		//
		// DBContextHolder.clearDBType();

		return entity;
	}

	@Override
	public Boolean doDeleteUserRole(String userId, String delRoleIds, User currentUser) {
		Boolean delReurn = false;
		// 获取当前用户的信息
		User theUser = this.get(userId);
		Set<Role> theUserRole = theUser.getSysRoles();

		String[] delId = delRoleIds.split(",");
		List<Role> delRoles = roleService.queryByProerties("id", delId);

		theUserRole.removeAll(delRoles);

		theUser.setSysRoles(theUserRole);

		this.merge(theUser);

		delReurn = true;
		// TODO Auto-generated method stub
		return delReurn;
	}

	@Override
	public Boolean doAddUserRole(String userId, String addRoleIds, User currentUser) {

		Boolean addResult = false;
		// 获取当前用户的信息
		User theUser = this.get(userId);
		Set<Role> theUserRole = theUser.getSysRoles();

		String[] addId = addRoleIds.split(",");
		List<Role> addRoles = roleService.queryByProerties("id", addId);

		theUserRole.addAll(addRoles);

		theUser.setSysRoles(theUserRole);

		this.merge(theUser);

		addResult = true;

		return addResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<User> getDeptUser(Integer start, Integer limit, String sort, String filter, Boolean isDelete,
			String userIds, User currentUser) {

		String sortSql = StringUtils.convertSortToSql(sort);
		String userId = userIds;
		StringBuffer hql = new StringBuffer();
		String countHql = "";
		String filterSql = "";
		if (StringUtils.isEmpty(userIds)) {
			return null;
		} else {
			if (!StringUtils.isEmpty(filter)) {
				filterSql = StringUtils.convertFilterToSql(filter);
			}
			/*
			 * BaseOrg org = orgService.get(deptId); deptIds = org.getTreeIds();
			 * hql.append(
			 * " from SysUser as o inner join fetch o.userDepts as r where r.treeIds like '"
			 * + deptIds + "%' and r.isDelete=0 "); if (isDelete) hql.append(
			 * " and o.isDelete=0 "); hql.append(filterSql); hql.append(
			 * " order by  o.jobCode "); QueryResult<SysUser> qr =
			 * this.queryPageResult(hql.toString(), start, limit);
			 */
			hql.append(" from User o  where o.id in(" + userId + ")");
			if (isDelete)
				hql.append(" and o.isDelete=0 ");
			hql.append(filterSql);
			
			if(StringUtils.isNotEmpty(sortSql))
				hql.append(" order by "+sortSql);
			QueryResult<User> qr = this.queryResult(hql.toString(), start, limit);

			return qr;
		}
	}

	/**
	 * 
	 * batchSetDept:批量设置用户的所属部门.
	 *
	 * @author luoyibo
	 * @param deptId
	 * @param userIds
	 * @param cuurentUser
	 * @return Boolean
	 * @throws @since
	 *             JDK 1.8
	 */
	// @Override
	// public Boolean batchSetDept(String deptId, String userIds, SysUser
	// cuurentUser) {
	// Boolean reResult = false;
	// String[] delId = userIds.split(",");
	// List<SysUser> listUser = this.queryByProerties("uuid", delId);
	// for (SysUser sysUser : listUser) {
	// Set<BaseOrg> userDept = sysUser.getUserDepts();
	// BaseOrg org = orgService.get(deptId);
	// BaseOrg tempOrg = orgService.get("058b21fe-b37f-41c9-ad71-091f97201ff8");
	// userDept.remove(tempOrg);
	// userDept.add(org);
	//
	// sysUser.setUserDepts(userDept);
	// sysUser.setUpdateTime(new Date());
	// sysUser.setUpdateUser(cuurentUser.getId());
	//
	// this.merge(sysUser);
	// reResult = true;
	// }
	// return reResult;
	// }

	@Override
	public List<User> getUserByRoleName(String roleName) {
		/*
		 * String sql =
		 * "SELECT USER_ID FROM SYS_T_USER WHERE USER_ID IN(SELECT USER_ID FROM SYS_T_ROLEUSER WHERE ROLE_ID IN(SELECT ROLE_ID FROM SYS_T_ROLE WHERE ROLE_NAME='"
		 * + roleName + "'))"; List<Object[]> list = this.queryObjectBySql(sql);
		 * List<SysUser> users = new ArrayList<SysUser>(); for (int i = 0; i <
		 * list.size(); i++) { String userid = list.get(i) + "";
		 * users.add(this.get(userid)); }
		 */
		String hql = "from User as u inner join fetch u.sysRoles as r where r.roleName='" + roleName
				+ "' and r.isDelete=0 and u.isDelete=0";
		return this.queryByHql(hql);
	}

	@Override
	public Boolean doDeleteUser(String delIds, String orgId, User currentUser) {
		String[] ids = delIds.split(",");
		boolean flag = false;
		/*
		 * for (String id : ids) { SysUser user = this.get(id); BaseOrg org =
		 * orgService.get(orgId); Set<BaseOrg> userDept = user.getUserDepts();
		 * userDept.remove(org);
		 * 
		 * user.setUpdateTime(new Date());
		 * user.setUpdateUser(currentUser.getId()); user.setUserDepts(userDept);
		 * 
		 * this.merge(user);
		 * 
		 * flag = true; }
		 */
		// TODO Auto-generated method stub
		return flag;
	}

	@Override
	public QueryResult<User> getUserByRoleId(String roleId) {
		QueryResult<User> qr = new QueryResult<User>();
		String hql = "from User as u inner join fetch u.sysRoles as r where r.id='" + roleId
				+ "' and r.isDelete=0 and u.isDelete=0 ";
		List<User> list = this.queryByHql(hql);

		SortListUtil<User> sortJob = new SortListUtil<User>();
		sortJob.Sort(list, "jobCode", "String");
		qr.setResultList(list);
		qr.setTotalCount((long) list.size());

		return qr;
		// List<SysUser> newList = new ArrayList<SysUser>();
		// if (list.size() > 0) {
		// StringBuffer sbJobName = new StringBuffer();
		// for (SysUser user : list) {
		// TeaTeacherbase teacherbase = teacherService.get(user.getId());
		// String jobInfo = teacherService.getTeacherJobs(teacherbase);
		// String[] strings = jobInfo.split(",");
		// user.setJobId(strings[0]);
		// user.setJobName(strings[1]);
		//
		// String deptInfo = teacherService.getTeacherDepts(teacherbase);
		// strings = deptInfo.split(",");
		// user.setDeptName(strings[1]);
		// newList.add(user);
		// }
		// qr.setResultList(newList);
		// qr.setTotalCount((long) list.size());
		// return qr;
		// } else
		// return null;
	}




	@Override
	public HashMap<String, Set<String>> getUserRoleMenuPermission(User sysUser, Session session) {
		// TODO Auto-generated method stub
		HashMap<String, Set<String>> map = new HashMap<>();

		// 若redis中不存在此人员的数据，就去查库（redis的相关数据，在用户被修改角色的时候，会删除）		
		Object userAuth = userRedisService.getAuthByUser(sysUser.getId());
		Object userBtn = userRedisService.getBtnByUser(sysUser.getId());
				
		if (userAuth != null && userBtn != null) { // 若存在，表明，没有更新更新redis，则不需要设置
			// 如果session为空，表明是初次登录进来，所以设置
			if (session.getAttribute(Constant.SESSION_SYS_AUTH) == null
					|| session.getAttribute(Constant.SESSION_SYS_BTN) == null) {
				session.setAttribute(Constant.SESSION_SYS_AUTH, userAuth);
				session.setAttribute(Constant.SESSION_SYS_BTN, userBtn);
			}
			return null; // 返回空，不在调用处处理
		}

		Set<String> userRMP_AUTH = new HashSet<>(); // 接口
		Set<String> userRMP_BTN = new HashSet<>(); // 按钮

		Set<Role> sysRoleSet = sysUser.getSysRoles();
		Iterator<Role> iterator = sysRoleSet.iterator();
		while (iterator.hasNext()) {
			Role sysRole = iterator.next();
			if (sysRole.getIsDelete() == 0) { // 只加入正常状态的角色数据
				List<MenuPermission> menuPerLists = menuPermissionService.getRoleMenuPerlist(sysRole.getId(),
						null);

				for (int i = 0; i < menuPerLists.size(); i++) {
					MenuPermission smp = menuPerLists.get(i);
					userRMP_AUTH.add(smp.getAuthPrefix() + "_" + smp.getAuthPostfix()); // 前缀+后缀
					userRMP_BTN.add(smp.getMenuCode() + "_" + smp.getButtonName()); // 菜单编码+按钮ref名称
				}
			}
		}
		map.put("auth", userRMP_AUTH);
		map.put("btn", userRMP_BTN);

		// 设置登录用户的功能权限（使用redis hash类型存储）
		if(userRMP_AUTH.size()==0)
			userRedisService.setAuthByUser(sysUser.getId(), null);
		else
			userRedisService.setAuthByUser(sysUser.getId(), userRMP_AUTH);
		
		if(userRMP_BTN.size()==0)
			userRedisService.setBtnByUser(sysUser.getId(), null);
		else
			userRedisService.setBtnByUser(sysUser.getId(), userRMP_BTN);

		return map;

	}

	/***
	 * 通过SysPermission权限菜单，这个参数，来获取相关的角色的用户ID，然后清除redis
	 */
	@Override
	public void deleteUserMenuTreeRedis(Permission sysPermission) {
		// TODO Auto-generated method stub
		Set<Role> sysRoleSet = sysPermission.getSysRoles();
		Set<String> setUserId = new HashSet<>();
		for (Role sysRole : sysRoleSet) {
			List<User> listUser = this.getUserByRoleId(sysRole.getId()).getResultList();
			for (int j = 0; j < listUser.size(); j++) {
				setUserId.add(listUser.get(j).getId());
			}
		}

		if (setUserId.size() > 0) {
			/* 删除用户的菜单redis数据，以至于下次刷新或请求时，可以加载最新数据 */
			userRedisService.deleteMenuTreeByUser(setUserId.toArray());			
		}
	}

	@Override
	public void deleteUserMenuTreeRedis(String[] roleIds) {
		// TODO Auto-generated method stub
		Set<String> setUserId = new HashSet<>();
		for (int i = 0; i < roleIds.length; i++) {
			List<User> listUser = this.getUserByRoleId(roleIds[i]).getResultList();
			for (int j = 0; j < listUser.size(); j++) {
				setUserId.add(listUser.get(j).getId());
			}
		}

		if (setUserId.size() > 0) {
			/* 删除用户的菜单redis数据，以至于下次刷新或请求时，可以加载最新数据 */
			userRedisService.deleteMenuTreeByUser(setUserId.toArray());
		}
	}

	@Override
	public void deleteUserRoleRedis(String ... userId) {
		// TODO Auto-generated method stub/*
		userRedisService.deleteAuthByUser(userId);
		userRedisService.deleteBtnByUser(userId);
		userRedisService.deleteMenuTreeByUser(userId);
	}

	@Override
	public QueryResult<User> getUserNotInRoleId(String roleId, int start, int limit, String sort, String filter) {
		String hql = "from User as o where o.isDelete=0  and state='0' "; // 只列出状态正常的用户
		if (StringUtils.isNotEmpty(roleId)) {
			String hql1 = " from User as u inner join fetch u.sysRoles as k where k.id='" + roleId
					+ "' and k.isDelete=0 and u.isDelete=0 ";
			List<User> tempList = this.queryByHql(hql1);
			if (tempList.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (User sysUser : tempList) {
					sb.append(sysUser.getId());
					sb.append(",");
				}
				sb = sb.deleteCharAt(sb.length() - 1);
				String str = sb.toString().replace(",", "','");
				hql += " and o.id not in ('" + str + "')";
			}
		}
		if (StringUtils.isNotEmpty(filter)) {
			hql += filter;
		}
		if (StringUtils.isNotEmpty(sort)) {
			hql += " order by ";
			hql += sort;
		}
		QueryResult<User> qr = this.queryResult(hql, start, limit);
		return qr;
	}

	/**
	 * 根据部门ID获取部门下的人员
	 * 
	 * @param deptId
	 *            部门ID
	 * @return
	 */
	@Override
	public List<User> getUserByDeptId(String deptId) {
		String hql = "select u.id from User as u,UserDeptJob as r where u.id=r.userId and r.deptId='"
				+ deptId + "' and r.isDelete=0 and u.isDelete=0";
		return this.queryByHql(hql);
	}
	
	/**
	 * 根据用户ID获取人员的部门
	 * 
	 * @param deptId
	 *            部门ID
	 * @return
	 */
	@Override
	public Set<Department> getDeptByUserId(String userId) {
		String hql = "select u.id from User as u,UserDeptJob as r,Department o where u.id=r.userId and r.id='"
				+ userId + "' and r.deptId=o.id and r.isDelete=0 and u.isDelete=0 and o.isDelete=0";
		List<Department>  orgs= orgService.queryByHql(hql);
		
		Set<Department> set=new HashSet<>(orgs);      
	
		return set;
	}
	
	@Override
	public List<ImportNotInfo> doImportUser(List<List<Object>> listObject, User currentUser) {
		// TODO Auto-generated method stub
		
		List<ImportNotInfo> listNotExit = new ArrayList<>();
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		ImportNotInfo notExits = null;
		Integer notCount = 1;

		
		Map<String, String> mapZzmmm = new HashMap<>();
		Map<String, String> mapXbm = new HashMap<>();
		Map<String, String> mapCategory = new HashMap<>();
		String hql1 = " from DataDictItem where dicCode in ('ZZMMM','XBM','CATEGORY')";
		List<DataDictItem> listBaseDicItems1 = dicitemService.queryByHql(hql1);
		for (DataDictItem baseDicitem : listBaseDicItems1) {
			if (baseDicitem.getDicCode().equals("XBM"))
				mapXbm.put(baseDicitem.getItemName(), baseDicitem.getItemCode());
			else if (baseDicitem.getDicCode().equals("ZZMMM"))
				mapZzmmm.put(baseDicitem.getItemName(), baseDicitem.getItemCode());
			else
				mapCategory.put(baseDicitem.getItemName(), baseDicitem.getItemCode());
		}

		

		/**
		 * 用户名    姓名	性别	 身份	  学号/工号	   身份证件号	移动电话	出生日期	电子邮件	政治面貌	
		 * 
		 */
		String doResult = "";
		String title = "";
		String errorLevel = "";
		User user = null;
		for (int i = 0; i < listObject.size(); i++) {
			try {

				List<Object> lo = listObject.get(i);

				// 导入的表格会错误的读取空行的内容，所以，当判断第一列为空，就跳过此行。
				if (!StringUtils.isNotEmpty((String) lo.get(0))) {
					continue;
				}

				title = String.valueOf(lo.get(0));
				doResult = "导入成功"; // 默认是成功
	
				// 查询库中是否存在此用户
				user = this.getByProerties(new String[]{"userName","isDelete"}, new Object[]{lo.get(0),0});
				
				//如果存在，就返回错误信息，不允许导入此条数据
				if(user!=null){
					
					errorLevel = "重复导入";
					doResult = "导入失败；异常信息：已存在此用户名的帐号信息";
					
				}else{
					user = new User();
					user.setUserName(String.valueOf(lo.get(0)));
					user.setName(String.valueOf(lo.get(1)));
					user.setSex(mapXbm.get(lo.get(2)));
					user.setCategory(mapCategory.get(lo.get(3)));
					user.setUserNumb(String.valueOf(lo.get(4)));
					user.setIdentityNumber(String.valueOf(lo.get(5)));
					user.setMobilePhone(String.valueOf(lo.get(6)));
					user.setBirthDate(dateSdf.parse(String.valueOf(lo.get(7))));
					user.setEmail(String.valueOf(lo.get(8)));
					user.setPoliticalStatus(mapZzmmm.get(lo.get(9)));
					user.setUserPwd(new Sha256Hash("123456").toHex());
					this.doAddUser(user,currentUser);
				}				

			} catch (Exception e) {
				// return null;
				errorLevel = "错误";
				doResult = "导入失败；异常信息：" + e.getMessage();
			}

			if (!"导入成功".equals(doResult)) {
				// List<Map<String, Object>>
				notExits = new ImportNotInfo();
				notExits.setOrderIndex(notCount);
				notExits.setTitle(title);
				notExits.setErrorLevel(errorLevel);
				notExits.setErrorInfo(doResult);

				listNotExit.add(notExits);
				notCount++;
			}
		}

		return listNotExit;
		
	}
	@Override
	public String getUserOwnDeptids(User currentUser) {
		List<Department> rightOrg = orgService.getUserRightDeptList(currentUser);
		StringBuffer orgids = new StringBuffer();
		if (rightOrg.size() > 0) {
			for (Department baseOrg : rightOrg) {
				orgids.append("'" + baseOrg.getId() + "',");
			}
		}
		if (orgids.length() > 0) {
			orgids = new StringBuffer(StringUtils.trimLast(orgids.toString()));
			return orgids.toString();
		} else
			return "";
	}

}