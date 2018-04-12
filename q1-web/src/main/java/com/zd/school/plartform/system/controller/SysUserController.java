package com.zd.school.plartform.system.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yc.q1.model.base.pt.system.PtDataDictItem;
import com.yc.q1.model.base.pt.system.PtRole;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserDeptJob;
import com.yc.q1.pojo.base.pt.DepartmentTree;
import com.yc.q1.pojo.base.pt.MenuTree;
import com.yc.q1.service.base.pt.system.DataDictItemService;
import com.yc.q1.service.base.pt.system.DepartmentService;
import com.yc.q1.service.base.pt.system.MenuService;
import com.yc.q1.service.base.pt.system.UserDeptJobService;
import com.yc.q1.service.base.pt.system.UserService;
import com.yc.q1.service.base.redis.UserRedisService;
import com.zd.core.annotation.Auth;
import com.zd.core.constant.AdminType;
import com.zd.core.constant.AuthorType;
import com.zd.core.constant.Constant;
import com.zd.core.constant.StatuVeriable;
import com.zd.core.constant.TreeVeriable;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.model.ImportNotInfo;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.util.EntityExportExcel;
import com.zd.core.util.ModelUtil;
import com.zd.core.util.PoiExportExcel;
import com.zd.core.util.PoiImportExcel;
import com.zd.core.util.StringUtils;

/**
 * 用户管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/SysUser")
public class SysUserController extends FrameWorkController<PtUser> implements Constant {

	private static Logger logger = Logger.getLogger(SysUserController.class);

	@Resource
	UserService thisService; // service层接口

	@Resource
	MenuService sysMenuService;

	@Resource
	UserDeptJobService userDeptjobService;

	@Resource
	DataDictItemService dicitemService;

	@Resource
	DepartmentService sysOrgService;

	@Resource
	private UserRedisService userRedisService;

	/**
	 * list查询 @Title: list @Description: TODO @param @param entity
	 * 实体类 @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 * 若当前用户是超级管理员/学校管理员，并且点击为学校部门，则查询出所有的用户
	 * 否则其他用户点击顶层学校部门，会显示出他所有权限部门之下的用户(主和副部门的用户)
	 * 点击其他的各个子部门，只会显示这个部门下的用户(主和副部门的用户)
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		String deptId = request.getParameter("deptId");

		if (StringUtils.isEmpty(deptId)) {
			deptId = AdminType.ADMIN_ORG_ID;
		}

		Integer isAdmin = (Integer) request.getSession().getAttribute(Constant.SESSION_SYS_ISADMIN);
		Integer isSchoolAdmin = (Integer) request.getSession().getAttribute(Constant.SESSION_SYS_ISSCHOOLADMIN);

		// 若当前用户是超级管理员/学校管理员，并且为学校部门，则查询出所有的用户
		if ((isAdmin == 1 || isSchoolAdmin == 1) && deptId.equals(AdminType.ADMIN_ORG_ID)) {

			QueryResult<PtUser> qr = thisService.queryPageResult(super.start(request), super.limit(request),
					super.sort(request), super.filter(request), true);
			strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据

		} else {
			PtUser currentUser = getCurrentSysUser();
			// 其他非管理员的，并且点击了学校部门，则查询出它有权限的部门
			if (deptId.equals(AdminType.ADMIN_ORG_ID)) {
				List<DepartmentTree> baseOrgList = sysOrgService.getUserRightDeptTreeList(currentUser);
				deptId = baseOrgList.stream().filter((x) -> x.getIsRight().equals("1")).map((x) -> x.getId())
						.collect(Collectors.joining("','"));
			}

			// 根据deptId，查询出所有用户信息（主部门和副部门的）
			if (StringUtils.isNotEmpty(deptId)) {
				String hql = "from User g where g.isDelete=0 and g.id in ("
						+ "	select distinct userId  from UserDeptJob where isDelete=0 and deptId in ('" + deptId
						+ "')" + ")"; // and masterDept=1 目前显示部门的全部用户

				QueryResult<PtUser> qr = thisService.queryCountToHql(super.start(request), super.limit(request),
						super.sort(request), super.filter(request), hql, null, null);

				strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据

			} else {
				strData = jsonBuilder.buildObjListToJson((long) 0, new ArrayList<>(), true);// 处理数据
			}

		}

		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 添加用户
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth("SYSUSER_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtUser entity, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = entity.getUserName();
		String userNumb = entity.getUserNumb();
		// 此处为放在入库前的一些检查的代码，如唯一校验等
		if (thisService.IsFieldExist("userName", userName, "-1")) {
			writeJSON(response, jsonBuilder.returnFailureJson("'用户名不能重复！'"));
			return;
		}

		if (StringUtils.isNotEmpty(userNumb)) {
			if (thisService.IsFieldExist("userNumb", userNumb, "-1")) {
				writeJSON(response, jsonBuilder.returnFailureJson("'工号/学号不能重复！'"));
				return;
			}
		}

		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();
		String deptJobId = request.getParameter("deptJobId");
		// 给学生或教师加入角色
		entity = thisService.doAddUser(entity, currentUser/* , deptJobId */);

		// 返回实体到前端界面
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	}

	/**
	 * 逻辑删除用户
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Auth("SYSUSER_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"没有传入删除主键\""));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE, currentUser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		}
	}

	/**
	 * 更新用户信息
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth("SYSUSER_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(PtUser entity, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String userName = entity.getUserName();
		String userNumb = entity.getUserNumb();
		String userId = entity.getId();
		// 此处为放在入库前的一些检查的代码，如唯一校验等
		if (thisService.IsFieldExist("userName", userName, userId)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"用户名不能重复！\""));
			return;
		}

		if (StringUtils.isNotEmpty(userNumb)) {
			if (thisService.IsFieldExist("userNumb", userNumb, userId)) {
				writeJSON(response, jsonBuilder.returnFailureJson("'工号/学号不能重复！'"));
				return;
			}
		}
		// 获取当前的操作用户
		PtUser currentUser = getCurrentSysUser();

		entity = thisService.doUpdateUser(entity, currentUser);

		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	}

	/**
	 * 获取用户菜单树
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getUserMenuTree")
	public void getUserMenuTree(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		PtUser currentUser = (PtUser) session.getAttribute(SESSION_SYS_USER);
		String strData = null;

		// 获取缓存数据
		Object userMenuTree = userRedisService.getMenuTreeByUser(currentUser.getId());

		if (userMenuTree == null) { // 若存在，则不需要设置
			String node = request.getParameter("node");
			String excludes = request.getParameter("excludes");

			if (StringUtils.isEmpty(node) || TreeVeriable.ROOT.equalsIgnoreCase(node)) {
				node = TreeVeriable.ROOT;
			}

			List<MenuTree> lists = sysMenuService.getPermTree(node, currentUser.getId(), AuthorType.USER, true,
					false);
			strData = jsonBuilder.buildList(lists, excludes);

			// 存入redis中
			userRedisService.setMenuTreeByUser(currentUser.getId(), strData);

		} else {
			strData = (String) userMenuTree;
		}

		// 取出json字符串
		writeJSON(response, strData);
	}



	/**
	 * 
	 * getUserRolelist:用户所属角色列表
	 *
	 * @author luoyibo
	 * @param request
	 * @param response
	 * @throws IOException
	 *             void
	 * @throws @since
	 *             JDK 1.8
	 */

	@RequestMapping(value = { "/userRoleList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void getUserRolelist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("userId"); // 获得传过来的roleId
		PtUser sysUser = thisService.get(userId);
		Integer count = 0;
		Set<PtRole> userRole = new HashSet<PtRole>();
		if (ModelUtil.isNotNull(sysUser)) {

			// 排除isdelet为1的角色(在获取菜单列表的方法中，排除删除的角色)
			Iterator<PtRole> sysRoles = sysUser.getSysRoles().iterator();
			while (sysRoles.hasNext()) {
				PtRole currentRole = sysRoles.next();
				if (currentRole.getIsDelete() != null && currentRole.getIsDelete() != 1) {
					userRole.add(currentRole);
				}
			}

			count = userRole.size();
		}
		String strData = jsonBuilder.buildObjListToJson(new Long(count), userRole, true);
		writeJSON(response, strData);
	}

	/**
	 * 
	 * deleteUserRole:删除用户所属的角色.
	 *
	 * @author luoyibo
	 * @param request
	 * @param response
	 * @throws IOException
	 *             void
	 * @throws @since
	 *             JDK 1.8
	 */
	@RequestMapping(value = { "/doDeleteUserRole" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void deleteUserRole(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userId = request.getParameter("userId"); // 获得传过来的用户ID
		String ids = request.getParameter("ids");
		PtUser currentUser = getCurrentSysUser();

		if (StringUtils.isEmpty(ids) || StringUtils.isEmpty(userId)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入要删除的数据'"));
			return;
		} else {
			boolean flag = thisService.doDeleteUserRole(userId, ids, currentUser);
			if (flag) {
				/* 删除用户的redis数据，以至于下次刷新或请求时，可以加载最新数据 */
				thisService.deleteUserRoleRedis(userId);

				// 当操作了当前用户的角色，则更新roleKey的session值
				if (userId.indexOf(currentUser.getId()) != -1) {
					PtUser sysUser = thisService.get(currentUser.getId());
					String roleKeys = sysUser.getSysRoles().stream().filter(x -> x.getIsDelete() == 0)
							.map(x -> x.getRoleCode()).collect(Collectors.joining(","));
					request.getSession().setAttribute(Constant.SESSION_SYS_USER, sysUser);
					request.getSession().setAttribute(Constant.SESSION_ROLE_KEY, roleKeys);
				}

				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		}
	}

	/**
	 * 
	 * addUserRole:增加用户所属的角色.
	 *
	 * @author luoyibo
	 * @param request
	 * @param response
	 * @throws IOException
	 *             void
	 * @throws @since
	 *             JDK 1.8
	 */
	@Auth("SYSUSER_role")
	@RequestMapping(value = { "/doAddUserRole" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void addUserRole(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userId = request.getParameter("userId"); // 获得传过来的用户ID
		String ids = request.getParameter("ids");
		PtUser currentUser = getCurrentSysUser();

		if (StringUtils.isEmpty(ids) || StringUtils.isEmpty(userId)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入要添加的数据\""));
			return;
		} else {
			boolean flag = thisService.doAddUserRole(userId, ids, currentUser);
			if (flag) {
				/* 删除用户的redis数据，以至于下次刷新或请求时，可以加载最新数据 */
				thisService.deleteUserRoleRedis(userId);

				// 当操作了当前用户的角色，则更新roleKey的session值
				if (userId.indexOf(currentUser.getId()) != -1) {
					PtUser sysUser = thisService.get(currentUser.getId());
					String roleKeys = sysUser.getSysRoles().stream().filter(x -> x.getIsDelete() == 0)
							.map(x -> x.getRoleCode()).collect(Collectors.joining(","));
					request.getSession().setAttribute(Constant.SESSION_SYS_USER, sysUser);
					request.getSession().setAttribute(Constant.SESSION_ROLE_KEY, roleKeys);
				}

				writeJSON(response, jsonBuilder.returnSuccessJson("\"添加成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"添加失败\""));
			}
		}
	}

	@Auth("SYSUSER_lock")
	@RequestMapping("/doLock")
	public void doLock(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入要解锁的账户\""));
			return;
		} else {
			String[] delId = delIds.split(",");
			thisService.updateByProperties("id", delId, "state", "1");
			writeJSON(response, jsonBuilder.returnSuccessJson("\"锁定成功\""));
		}
	}

	@Auth("SYSUSER_unlock")
	@RequestMapping("/doUnlock")
	public void doUnlock(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入要解锁的账户\""));
			return;
		} else {
			String[] delId = delIds.split(",");
			thisService.updateByProperties("id", delId, "state", "0");
			writeJSON(response, jsonBuilder.returnSuccessJson("\"解锁成功\""));
		}
	}

	@Auth("SYSUSER_setPwd")
	@RequestMapping("/doSetPwd")
	public void doSetpwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入要设置密码的账户\""));
			return;
		} else {
			String[] delId = delIds.split(",");
			String userPwd = new Sha256Hash("123456").toHex();
			thisService.updateByProperties("id", delId, "userPwd", userPwd);
			writeJSON(response, jsonBuilder.returnSuccessJson("\"重置密码成功\""));
		}
	}

	@RequestMapping(value = { "/userList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void getUserlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<PtUser> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	@RequestMapping(value = { "/selectedUserlist" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void getSelectedUserlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		String ids = request.getParameter("ids");
		String hql = "from User a where id in ('" + ids.replace(",", "','") + "')";
		List<PtUser> userList = thisService.queryByHql(hql);

		strData = jsonBuilder.buildObjListToJson((long) userList.size(), userList, true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 获取用户部门岗位列表
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/userDeptJobList" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void getUserDeptJobList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据

		String userId = request.getParameter("userId");

		String propName[] = { "isDelete", "userId" };
		Object propValue[] = { 0, userId };
		List<PtUserDeptJob> list = userDeptjobService.queryByProerties(propName, propValue);

		strData = jsonBuilder.buildObjListToJson((long) list.size(), list, true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 将指定的用户绑定到指定的部门岗位上
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Auth("SYSUSER_deptJob")
	@RequestMapping("/doAddUserToDeptJob")
	public void addUserToDeptJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String deptJobId = request.getParameter("ids");
		String userId = request.getParameter("setIds");
		if (StringUtils.isEmpty(deptJobId) || StringUtils.isEmpty(userId)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入设置的参数'"));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = userDeptjobService.doAddUserToDeptJob(deptJobId, userId, currentUser);
			if (flag)
				writeJSON(response, jsonBuilder.returnSuccessJson("'设置成功'"));
			else
				writeJSON(response, jsonBuilder.returnSuccessJson("'设置失败'"));
		}
	}

	/**
	 * 删除用户所在的部门岗位，只是逻辑删除
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doRmoveUserFromDeptJob")
	public void doRmoveUserFromDeptJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入要解除绑定的部门岗位'"));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = userDeptjobService.doRemoveUserFromDeptJob(delIds, currentUser);
			if (flag)
				writeJSON(response, jsonBuilder.returnSuccessJson("'解除绑定成功'"));
			else
				writeJSON(response, jsonBuilder.returnSuccessJson("'解除绑定失败'"));
		}
	}

	/**
	 * 调整指定用户的主部门岗位 2018-3-15更新：当用户是学生，并且操作的是学生岗位，那么则判断是否处理JwClassstudent表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doSetMasterDeptJob")
	public void doSetMasterDeptJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		String userId = request.getParameter("setIds");
		if (StringUtils.isEmpty(delIds) || StringUtils.isEmpty(userId)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入要设置部门岗位'"));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = userDeptjobService.doSetMasterDeptJob(delIds, userId, currentUser);
			if (flag)
				writeJSON(response, jsonBuilder.returnSuccessJson("'设置主部门成功'"));
			else
				writeJSON(response, jsonBuilder.returnSuccessJson("'设置主部门失败'"));
		}
	}

	
	/**
	 * 获取不在某角色下的用户列表
	 * 
	 * @param roleId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getUserNotInRoleId" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void getUserNotInRoleId(String roleId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据

		int start = super.start(request); // 起始记录数
		int limit = super.limit(request);// 每页记录数
		String sort = StringUtils.convertSortToSql(super.sort(request));
		String filter = StringUtils.convertFilterToSql(super.filter(request));

		QueryResult<PtUser> qr = thisService.getUserNotInRoleId(roleId, start, limit, sort, filter);
		strData = jsonBuilder.buildObjListToJson(new Long(qr.getTotalCount()), qr.getResultList(), true);// 处理数据

		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 导出用户信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Auth("SYSUSER_export")
	@RequestMapping("/doExportExcel")
	public void doExportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("exportTrainClassTraineeCardIsEnd", "0");
		request.getSession().removeAttribute("exportTrainClassTraineeCardIIsState");

		List<Map<String, Object>> allList = new ArrayList<>();
		Integer[] columnWidth = new Integer[] { 10, 15, 15, 20, 20, 20, 20, 15, 15 };

		String deptId = request.getParameter("deptId");
		String userName = request.getParameter("userName");
		String xm = request.getParameter("xm");
		String category = request.getParameter("category");

		// 数据字典项
		String mapKey = null;
		String[] propValue = { "XBM", "CATEGORY", "ZXXBZLB", "ACCOUNTSTATE", "CARDSTATE" };
		Map<String, String> mapDicItem = new HashMap<>();
		List<PtDataDictItem> listDicItem = dicitemService.queryByProerties("dicCode", propValue);
		for (PtDataDictItem baseDicitem : listDicItem) {
			mapKey = baseDicitem.getItemCode() + baseDicitem.getDicCode();
			mapDicItem.put(mapKey, baseDicitem.getItemName());
		}

		List<PtUser> sysUserList = null;
		String hql = " from User a where a.isDelete=0 ";
		if (StringUtils.isNotEmpty(deptId)) {
			if (!deptId.equals(AdminType.ADMIN_ORG_ID)) {
				hql = " select a from User a inner join UserDeptJob b on a.id=b.userId where a.isDelete=0 and b.isDelete=0 and b.deptId='"
						+ deptId + "'";
			}
		}
		if (StringUtils.isNotEmpty(userName)) {
			hql += " and a.userName like '%" + userName + "%'";
		}
		if (StringUtils.isNotEmpty(xm)) {
			hql += " and a.name like '%" + xm + "%'";
		}
		if (StringUtils.isNotEmpty(category)) {
			hql += " and a.category = '" + category + "'";
		}
		sysUserList = thisService.queryByHql(hql);

		List<Map<String, String>> traineeList = new ArrayList<>();
		Map<String, String> traineeMap = null;
		String ClassName = "";
		int i = 1;
		for (PtUser sysUser : sysUserList) {
			traineeMap = new LinkedHashMap<>();
			ClassName = sysUser.getDeptName();
			traineeMap.put("xh", i + "");
			traineeMap.put("userName", sysUser.getUserName());
			traineeMap.put("name", sysUser.getName());
			traineeMap.put("xb", mapDicItem.get(sysUser.getSex() + "XBM"));
			traineeMap.put("category", mapDicItem.get(sysUser.getCategory() + "CATEGORY"));
			traineeMap.put("zxxbzlb", mapDicItem.get(sysUser.getHeadCountType() + "ZXXBZLB"));
			traineeMap.put("stustatus", mapDicItem.get(sysUser.getState() + "ACCOUNTSTATE"));
			traineeMap.put("cardNo", String.valueOf((sysUser.getUpCardId() == null) ? 0 : sysUser.getUpCardId()));
			String useState = "";
			if (sysUser.getUseState() == null) {
				useState = "未发卡";
			} else {
				useState = mapDicItem.get(sysUser.getUseState() + "CARDSTATE");
			}
			traineeMap.put("useState", useState);
			i++;
			traineeList.add(traineeMap);
		}

		Map<String, Object> courseAllMap = new LinkedHashMap<>();
		courseAllMap.put("data", traineeList);
		courseAllMap.put("title", null);
		courseAllMap.put("head", new String[] { "序号", "用户名", "姓名", "性别", "身份", "编制", "账户状态", "卡片编号", "发卡状态" }); // 规定名字相同的，设定为合并
		courseAllMap.put("columnWidth", columnWidth); // 30代表30个字节，15个字符
		courseAllMap.put("columnAlignment", new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 }); // 0代表居中，1代表居左，2代表居右
		courseAllMap.put("mergeCondition", null); // 合并行需要的条件，条件优先级按顺序决定，NULL表示不合并,空数组表示无条件
		allList.add(courseAllMap);

		// 在导出方法中进行解析
		boolean result = PoiExportExcel.exportExcel(response, ClassName == null ? "所有部门用户详细" : ClassName + "用户详细",
				ClassName == null ? "所有部门用户详细" : ClassName + "用户信息", allList);
		if (result == true) {
			request.getSession().setAttribute("exportTrainClassTraineeCardIIsEnd", "1");
		} else {
			request.getSession().setAttribute("exportTrainClassTraineeCardIIsEnd", "0");
			request.getSession().setAttribute("exportTrainClassTraineeCardIIsState", "0");
		}
	}

	@RequestMapping("/checkExportEnd")
	public void checkExportEnd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Object isEnd = request.getSession().getAttribute("exportTrainClassTraineeCardIIsEnd");
		Object state = request.getSession().getAttribute("exportTrainClassTraineeCardIIsState");
		if (isEnd != null) {
			if ("1".equals(isEnd.toString())) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"文件导出完成！\""));
			} else if (state != null && state.equals("0")) {
				writeJSON(response, jsonBuilder.returnFailureJson("0"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"文件导出未完成！\""));
			}
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"文件导出未完成！\""));
		}
	}

	/**
	 * 设置用户的常用桌面功能菜单
	 * 
	 * @param menuCodes
	 * @throws IOException
	 */
	@RequestMapping("/setUserDeskFunc")
	public void setUserDeskFunc(@RequestParam("menuCodes") String menuCodes, HttpServletResponse response)
			throws IOException {
		try {
			PtUser sysUser = getCurrentSysUser();

			// 获取缓存数据
			Object userDeskFunc = userRedisService.getDeskFuncByUser(sysUser.getId());

			String[] strs = menuCodes.split(",");

			Set<String> set = null;
			if (userDeskFunc != null) {
				set = (Set<String>) userDeskFunc;
			} else {
				set = new HashSet<>();
			}
			set.addAll(Arrays.asList(strs));

			// 存入redis中
			userRedisService.setDeskFuncByUser(sysUser.getId(), set);

			writeJSON(response, jsonBuilder.returnSuccessJson("\"设置成功！\""));

		} catch (Exception e) {

			writeJSON(response, jsonBuilder.returnFailureJson("\"设置失败！\""));
		}
	}

	/**
	 * 取消用户的常用桌面功能菜单
	 * 
	 * @param menuCodes
	 * @throws IOException
	 */
	@RequestMapping("/cancelUserDeskFunc")
	public void cancelUserDeskFunc(@RequestParam("menuCodes") String menuCodes, HttpServletResponse response)
			throws IOException {
		try {
			PtUser sysUser = getCurrentSysUser();

			// 获取缓存数据
			Object userDeskFunc = userRedisService.getDeskFuncByUser(sysUser.getId());

			String[] strs = menuCodes.split(",");

			Set<String> set = null;
			if (userDeskFunc != null) {
				set = (Set<String>) userDeskFunc;
			} else {
				set = new HashSet<>();
			}
			set.removeAll(Arrays.asList(strs));

			// 存入redis中
			userRedisService.setDeskFuncByUser(sysUser.getId(), set);

			writeJSON(response, jsonBuilder.returnSuccessJson("\"取消成功！\""));

		} catch (Exception e) {

			writeJSON(response, jsonBuilder.returnFailureJson("\"取消失败！\""));
		}
	}

	/**
	 * 获取用户的常用桌面功能菜单
	 * 
	 * @param menuCodes
	 * @throws IOException
	 */
	@RequestMapping("/getUserDeskFunc")
	public void getUserDeskFunc(HttpServletResponse response) throws IOException {
		try {
			PtUser sysUser = getCurrentSysUser();

			// 获取缓存数据
			Object userDeskFunc = userRedisService.getDeskFuncByUser(sysUser.getId());

			Set<String> set = (Set<String>) userDeskFunc;
			String returnStr = set.stream().collect(Collectors.joining(","));
			writeJSON(response, jsonBuilder.returnSuccessJson("\"" + returnStr + "\""));

		} catch (Exception e) {

			writeJSON(response, jsonBuilder.returnFailureJson("\"取消失败！\""));
		}
	}

	/**
	 * 描述：通过传统方式form表单提交方式导入excel文件
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/importData", method = { RequestMethod.GET, RequestMethod.POST })
	public void uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			PtUser currentUser = getCurrentSysUser();

			InputStream in = null;
			List<List<Object>> listObject = null;
			List<ImportNotInfo> listReturn;

			if (!file.isEmpty()) {
				in = file.getInputStream();
				listObject = new PoiImportExcel().getListByExcel(in, file.getOriginalFilename());
				in.close();

				listReturn = thisService.doImportUser(listObject, currentUser);

				if (listReturn.size() == 0)
					writeJSON(response, jsonBuilder.returnSuccessJson("\"文件导入成功！\""));
				else {
					String strData = jsonBuilder.buildList(listReturn, "");
					request.getSession().setAttribute("UserImportError", strData);
					writeJSON(response, jsonBuilder.returnSuccessJson("-1")); // 返回前端-1，表示存在错误数据
				}

			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"文件不存在！\""));
			}
		} catch (Exception e) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"文件导入失败,请下载模板或联系管理员！\""));
		}
	}

	@RequestMapping(value = { "/downNotImportInfo" })
	public void downNotImportInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute("UserImportError");
		if (obj != null) {

			request.getSession().removeAttribute("UserImportError");// 移除此session

			String downData = (String) obj;

			List<ImportNotInfo> list = (List<ImportNotInfo>) jsonBuilder.fromJsonArray(downData, ImportNotInfo.class);
			EntityExportExcel excel = new EntityExportExcel();

			String[] Title = { "序号", "用户姓名", "异常级别", "异常原因" };
			Integer[] coulumnWidth = { 8, 20, 20, 100 };
			Integer[] coulumnDirection = { 1, 1, 1, 1 };

			List<String> excludeList = new ArrayList<>();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
			String fileNAME = "（" + sdf2.format(new Date()) + "）导入用户的异常信息名单";

			excel.exportExcel(response, fileNAME, Title, list, excludeList, coulumnWidth, coulumnDirection);
		}
	}
}
