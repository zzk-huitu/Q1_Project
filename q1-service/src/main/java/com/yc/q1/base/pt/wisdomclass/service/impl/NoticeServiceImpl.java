package com.yc.q1.base.pt.wisdomclass.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yc.q1.base.pt.basic.model.InfoTerminal;
import com.yc.q1.base.pt.basic.service.InfoTerminalService;
import com.yc.q1.base.pt.basic.service.PushInfoService;
import com.yc.q1.base.pt.system.model.Department;
import com.yc.q1.base.pt.system.model.Role;
import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.system.service.DepartmentService;
import com.yc.q1.base.pt.system.service.RoleService;
import com.yc.q1.base.pt.system.service.UserService;
import com.yc.q1.base.pt.wisdomclass.dao.NoticeDao;
import com.yc.q1.base.pt.wisdomclass.model.Notice;
import com.yc.q1.base.pt.wisdomclass.model.NoticeOther;
import com.yc.q1.base.pt.wisdomclass.service.ClassTeacherService;
import com.yc.q1.base.pt.wisdomclass.service.NoticeService;
import com.yc.q1.base.pt.wisdomclass.service.NoticeAuditorService;
import com.zd.core.constant.AdminType;
import com.zd.core.constant.StringVeriable;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.DateUtil;
import com.zd.core.util.ModelUtil;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: OaNoticeServiceImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 公告信息表(OA_T_NOTICE)实体Service接口实现类. date: 2016-12-21
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class NoticeServiceImpl extends BaseServiceImpl<Notice> implements NoticeService {

	@Resource(name="NoticeDao")	//将具体的dao注入进来
	public void setDao(BaseDao<Notice> dao) {
		super.setDao(dao);
	}

	private static Logger logger = Logger.getLogger(NoticeServiceImpl.class);

	@Resource
	private DepartmentService orgService;

	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;


	@Resource
	private NoticeAuditorService auditorService;

	@Resource
	private PushInfoService pushService;

	@Resource
	private InfoTerminalService oaInfotermService;

	@Resource
	private ClassTeacherService cTeacherService;

	@Override
	public QueryResult<Notice> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete) {
		QueryResult<Notice> qResult = this.queryPageResult(start, limit, sort, filter, isDelete);
		return qResult;
	}

	/**
	 * 根据主键逻辑删除数据
	 * 
	 * @param ids
	 *            要删除数据的主键
	 * @param currentUser
	 *            当前操作的用户
	 * @return 操作成功返回true，否则返回false
	 */
	@Override
	public Boolean doLogicDeleteByIds(String ids, User currentUser) {
		Boolean delResult = false;
		try {
			Object[] conditionValue = ids.split(",");
			String[] propertyName = { "isDelete", "updateUser", "updateTime" };
			Object[] propertyValue = { 1, currentUser.getId(), new Date() };
			this.updateByProperties("id", conditionValue, propertyName, propertyValue);
			delResult = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			delResult = false;
		}
		return delResult;
	}

	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	@Override
	public Notice doUpdateEntity(Notice entity, User currentUser) {
		// 先拿到已持久化的实体
		Notice saveEntity = this.get(entity.getId());
		try {
			BeanUtils.copyProperties(saveEntity, entity);
			saveEntity.setUpdateTime(new Date()); // 设置修改时间
			saveEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
			entity = this.merge(saveEntity);// 执行修改方法

			return entity;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @param deptIds
	 *            公告通知的部门
	 * @param roleIds
	 *            公告通知的角色
	 * @param userIds
	 *            公告通知的用户
	 * @return
	 */
	@Override
	public Notice doUpdateEntity(Notice entity, User currentUser, String deptIds, String roleIds, String userIds,
			String terminalIds, String stuIds, String isNoticeParent) {
		Object[] propValue = {};
		// 先拿到已持久化的实体
		Notice saveEntity = this.get(entity.getId());
		try {
			// 根据传入的部门、人员与角色的id处理
			NoticeOther otherInfo = this.getNoticeOther(entity.getId());

			// 当不为不通知的时候，则更新
			if (!"3".equals(entity.getDeptRadio())) {

				if (!deptIds.equals(otherInfo.getDeptIds())) {
					propValue = deptIds.split(",");
					Set<Department> orgs = saveEntity.getNoticeDepts();
					List<Department> setOrgs = null;

					if (deptIds.trim().equals(AdminType.ADMIN_ORG_ID)) {
						setOrgs = orgService.getOrgList(" and isLeaf=true ", " order by orderIndex asc ", currentUser);
					} else {
						setOrgs = orgService.queryByProerties("id", propValue);
					}

					orgs.addAll(setOrgs);
					saveEntity.setNoticeDepts(orgs);
				}

			} else { // 当为3时，就处理为空
				saveEntity.setNoticeDepts(new HashSet<Department>());
			}

			if (!roleIds.equals(otherInfo.getRoleIds())) {
				propValue = roleIds.split(",");
				Set<Role> roles = saveEntity.getNoticeRoles();
				List<Role> setRoles = roleService.queryByProerties("id", propValue);
				roles.addAll(setRoles);
				saveEntity.setNoticeRoles(roles);
			}

			if (!userIds.equals(otherInfo.getUserIds())) {
				propValue = userIds.split(",");
				Set<User> users = saveEntity.getNoticeUsers();
				List<User> setUsers = userService.queryByProerties("id", propValue);
				users.addAll(setUsers);
				saveEntity.setNoticeUsers(users);
			}

			// 当不为不通知的时候，则更新
			if (!"3".equals(entity.getTerminalRadio())) {
				if (!terminalIds.equals(otherInfo.getTermIds())) {
					// 现在前台修改时，传来的是房间id，所以，要用房间roomId去查询设备。
					Set<InfoTerminal> oaInfoTrems = saveEntity.getNoticeTerms();
					List<InfoTerminal> oaInfotermsSet = new ArrayList<>();
					List<Object> roomInfo = null;

					if (terminalIds.trim().equals(AdminType.ADMIN_ORG_ID)) {
						String roomInfoHql = "select id from RoomInfo where isDelete=0";
						roomInfo = this.queryEntityByHql(roomInfoHql);

					} else {
						propValue = terminalIds.split(",");
						roomInfo = Arrays.asList(propValue);
					}

					// 分批次执行
					int propLen = roomInfo.size();
					int increment = 100;
					for (int i = 0; i < propLen; i += increment) {
						if (propLen <= i + increment) {
							List<Object> proplist = roomInfo.subList(i, propLen);
							oaInfotermsSet.addAll(oaInfotermService.queryByProerties("roomId", proplist.toArray()));
							break;
						}
						List<Object> proplist = roomInfo.subList(i, i + increment);
						oaInfotermsSet.addAll(oaInfotermService.queryByProerties("roomId", proplist.toArray()));
					}

					oaInfoTrems.addAll(oaInfotermsSet);
					saveEntity.setNoticeTerms(oaInfoTrems);

				}
			} else { // 当为3时，就处理为空
				saveEntity.setNoticeTerms(new HashSet<InfoTerminal>());
			}

			// 当不为不通知的时候，则更新
			if (!"3".equals(entity.getStudentRadio())) {
				if (!stuIds.equals(otherInfo.getStuIds())) {
					Set<User> stus = saveEntity.getNoticeStus();
					List<User> setStus = new ArrayList<>();
					if (stuIds.trim().equals(AdminType.ADMIN_ORG_ID)) {
						String hql1 = " from User where isDelete=0 and category=2 ";
						setStus = userService.queryByHql(hql1);
					} else {
						propValue = stuIds.split(",");
						// 分批次执行
						List<Object> propValueList = Arrays.asList(propValue);
						int propLen = propValueList.size();
						int increment = 100;
						for (int i = 0; i < propLen; i += increment) {
							if (propLen <= i + increment) {
								List<Object> proplist = propValueList.subList(i, propLen);
								setStus.addAll(userService.queryByProerties("id", proplist.toArray()));
								break;
							}
							List<Object> proplist = propValueList.subList(i, i + increment);
							setStus.addAll(userService.queryByProerties("id", proplist.toArray()));
						}
					}
					stus.addAll(setStus);
					saveEntity.setNoticeStus(stus);
				}
			} else { // 当为3时，就处理为空
				saveEntity.setNoticeStus(new HashSet<User>());
			}

			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("noticeTerms");
			excludedProp.add("noticeDepts");
			excludedProp.add("noticeStus");
			excludedProp.add("noticeRoles");
			excludedProp.add("noticeUsers");

			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
			saveEntity.setUpdateTime(new Date()); // 设置修改时间
			saveEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名

			entity = this.merge(saveEntity);// 执行修改方法

			return entity;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	@Override
	public Notice doAddEntity(Notice entity, User currentUser) {
		Notice saveEntity = new Notice();
		try {
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("id");
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
			saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名
			entity = this.merge(saveEntity);// 执行修改方法

			return entity;
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @param deptIds
	 *            公告通知的部门
	 * @param roleIds
	 *            公告通知的角色
	 * @param userIds
	 *            公告通知的用户
	 * @return
	 */
	@Override
	public Notice doAddEntity(Notice entity, User currentUser, String deptIds, String roleIds, String userIds,
			String terminalIds, String stuIds, String isNoticeParent) {
		Notice saveEntity = new Notice();
		try {
			List<User> userList = new ArrayList<User>();
			List<User> stuList = new ArrayList<User>();

			String ids;
			String hql;
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("id");
			String[] propValue = {};
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
			saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名

			// 如果通知部门不为空时处理
			if (StringUtils.isNotEmpty(deptIds)) {
				propValue = deptIds.split(",");
				Set<Department> orgs = saveEntity.getNoticeDepts();
				List<Department> setOrgs = null;
				ids = "";

				if (deptIds.trim().equals(AdminType.ADMIN_ORG_ID)) {
					setOrgs = orgService.getOrgList(" and isLeaf=true ", " order by orderIndex asc ", currentUser);
					ids = setOrgs.stream().map((x) -> x.getId()).collect(Collectors.joining("','", "'", "'"));

				} else {
					setOrgs = orgService.queryByProerties("id", propValue);
					ids = setOrgs.stream().map(Department::getId).collect(Collectors.joining("','", "'", "'"));
				}

				orgs.addAll(setOrgs);
				saveEntity.setNoticeDepts(orgs);

				// 注：只通知主部门的人员（2017-12-28 zzk）
				hql = "from User as o where o.deptId in(" + ids + ") and o.isDelete=0 and o.category=1 ";
				userList.addAll(userService.queryByHql(hql));
			}
			// 如果通知人员不为空时处理
			if (StringUtils.isNotEmpty(userIds)) {
				propValue = userIds.split(",");
				Set<User> users = saveEntity.getNoticeUsers();
				List<User> setUsers = userService.queryByProerties(new String[] { "id", "isDelete", "category" },
						new Object[] { propValue, 0, "1" });
				users.addAll(setUsers);
				userList.addAll(setUsers);
				saveEntity.setNoticeUsers(users);
			}
			// 如果通知角色不为空时处理
			if (StringUtils.isNotEmpty(roleIds)) {
				propValue = roleIds.split(",");
				Set<Role> roles = saveEntity.getNoticeRoles();
				List<Role> setRoles = roleService.queryByProerties("id", propValue);
				roles.addAll(setRoles);
				saveEntity.setNoticeRoles(roles);

				ids = "";
				for (String str : propValue) {
					ids += "'" + str + "',";
				}
				ids = StringUtils.trimLast(ids);

				hql = "from User as o inner join fetch o.sysRoles as d where d.id in(" + ids
						+ ") and o.isDelete=0 and  o.category=1 ";
				userList.addAll(userService.queryByHql(hql));
			}

			// 如果终端不为空时的处理
			if (StringUtils.isNotEmpty(terminalIds)) {
				Set<InfoTerminal> setOaInfoterm = saveEntity.getNoticeTerms();
				List<InfoTerminal> oaInfoterms = new ArrayList<>();
				List<String> roomInfo = null;

				if (terminalIds.trim().equals(AdminType.ADMIN_ORG_ID)) {
					String roomInfoHql = "select id from RoomInfo where isDelete=0";
					roomInfo = this.queryEntityByHql(roomInfoHql);
				} else {
					propValue = terminalIds.split(",");
					roomInfo = Arrays.asList(propValue);
					// oaInfoterms =
					// oaInfotermService.queryByProerties("roomId", propValue);
				}

				// 分批次执行
				int propLen = roomInfo.size();
				int increment = 100;
				for (int i = 0; i < propLen; i += increment) {
					if (propLen <= i + increment) {
						List<String> proplist = roomInfo.subList(i, propLen);
						oaInfoterms.addAll(oaInfotermService.queryByProerties("roomId", proplist.toArray()));
						break;
					}
					List<String> proplist = roomInfo.subList(i, i + increment);
					oaInfoterms.addAll(oaInfotermService.queryByProerties("roomId", proplist.toArray()));
				}

				setOaInfoterm.addAll(oaInfoterms);
				saveEntity.setNoticeTerms(setOaInfoterm);
			}

			// 如果通知学生不为空时处理
			if (StringUtils.isNotEmpty(stuIds)) {
				Set<User> stus = saveEntity.getNoticeStus();
				List<User> setStus = new ArrayList<>();
				if (stuIds.trim().equals(AdminType.ADMIN_ORG_ID)) {

					String hql1 = " from User where isDelete=0 and category=2 ";

					// 是否为学校管理员
					/*
					 * Boolean isSchoolAdminRole = false; List<SysUser>
					 * roleUsers =
					 * userService.getUserByRoleId(AdminType.SCHOOLADMIN_ROLE_ID
					 * ).getResultList(); for (SysUser su : roleUsers) { if
					 * (su.getId().equals(currentUser.getId())) {
					 * isSchoolAdminRole = true; break; } }
					 */

					/**
					 * 暂无年级组长和班主任设置
					 * 
					 * 
					 * if (!isSchoolAdminRole) {
					 * 
					 * // 不是学校管理员判断是否是年级组长 String ghql =
					 * "from JwGradeteacher where isDelete=0 and tteacId='" +
					 * currentUser.getId() + "'"; List
					 * <JwGradeteacher> gradeclassteachers =
					 * gTeacherService.queryByHql(ghql); if (gradeclassteachers
					 * != null && gradeclassteachers.size() > 0) {
					 * JwGradeteacher gTeacher = gradeclassteachers.get(0); hql1
					 * +=
					 * " and uuid in(select studentId from JwClassstudent where isDelete=0 and claiId in("
					 * ; hql1 +=
					 * " select uuid from JwTGradeclass where graiId='"
					 * +gTeacher.getGraiId()+"'"; hql1 += "))"; } else { //
					 * 判断是否是班主任 ghql =
					 * "from JwClassteacher where isDelete=0 and tteacId='" +
					 * currentUser.getId() + "'"; List
					 * <JwClassteacher> classteachers =
					 * cTeacherService.queryByHql(ghql); if (classteachers !=
					 * null && classteachers.size() > 0) { JwClassteacher
					 * cTeacher = classteachers.get(0); hql1 +=
					 * " and uuid in(select studentId from JwClassstudent where isDelete=0 and claiId='"
					 * +cTeacher.getClaiId()+"') "; } } }
					 */

					setStus = userService.queryByHql(hql1);
				} else {
					propValue = stuIds.split(",");
					// 分批次执行
					List<String> propValueList = Arrays.asList(propValue);
					int propLen = propValueList.size();
					int increment = 100;
					for (int i = 0; i < propLen; i += increment) {
						if (propLen <= i + increment) {
							List<String> proplist = propValueList.subList(i, propLen);
							setStus.addAll(userService.queryByProerties("uuid", proplist.toArray()));
							break;
						}
						List<String> proplist = propValueList.subList(i, i + increment);
						setStus.addAll(userService.queryByProerties("uuid", proplist.toArray()));
					}
				}
				stus.addAll(setStus);
				stuList.addAll(setStus);
				saveEntity.setNoticeStus(stus);
			}

			// 通知家长
			if (isNoticeParent.equals("1")) {
				Set<User> filterStu = new HashSet<User>(stuList);
				stuList = new ArrayList<User>(filterStu);
				for (User sysUser : filterStu) {
					String regStatus = "您好," + sysUser.getName() + "同学的家长,有通知公告需要您查看!";
					pushService.pushInfo(sysUser.getName(), sysUser.getUserNumb(), "通知公告查看",
							regStatus, StringVeriable.WEB_URL
									+ "static/core/coreApp/oa/notice/phonequery/list.jsp?userId=" + sysUser.getId(),
							currentUser);
				}
			}

			// 通知老师
			Set<User> filterUser = new HashSet<User>(userList);
			userList = new ArrayList<User>(filterUser);
			for (User sysUser : filterUser) {
				String regStatus = "您好," + sysUser.getName() + "老师,有通知公告需要您查看!";
				pushService.pushInfo(sysUser.getName(), sysUser.getUserNumb(),
						"通知公告查看", regStatus, StringVeriable.WEB_URL
								+ "static/core/coreApp/oa/notice/phonequery/list.jsp?userId=" + sysUser.getId(),
						currentUser);
			}

			//（zzk：新版本已经去除通知的权限功能）通知公告审批的权限人员
//			SysUser approveUser = rightService.getApproveUser(currentUser);
//			if (approveUser != null) {
//				saveEntity.setIsCheck("1");
//				OaNoticeauditor auditor = new OaNoticeauditor();
//				auditor.setName(approveUser.getName());
//				auditor.setUserId(approveUser.getId());
//				auditor.setAuditState("0");
//				auditor.setOaNotice(saveEntity);
//				auditorService.merge(auditor);
//				String regStatus = "您好," + approveUser.getName() + "老师,有通知公告需要您尽快处理!";
//				pushService.pushInfo(approveUser.getName(), approveUser.getUserNumb(), "通知公告审批", regStatus,
//						currentUser);
//			}

			entity = this.merge(saveEntity);// 执行修改方法
			return entity;
		} catch (Exception e) {
			// 回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("错误原因：【" + e.getMessage() + "】 出错堆栈跟踪：" + Arrays.toString(e.getStackTrace()));
			return null;
		}
	}

	/**
	 * 获取指定公告的通知部门、角色、人员的信息
	 * 
	 * @param id
	 *            指定的公告id
	 * @return
	 */
	@Override
	public NoticeOther getNoticeOther(String id) {

		NoticeOther otherEntity = new NoticeOther();
		NoticeOther otherDept = this.getNoticeDeptInfo(id);
		NoticeOther otherRole = this.getNoticeRoleInfo(id);
		NoticeOther otherUser = this.getNoticeUserInfo(id);
		NoticeOther otherTerm = this.getNoticeTermsInfo(id);
		NoticeOther otherStu = this.getNoticeStuInfo(id);

		otherEntity.setNoticeId(id);
		otherEntity.setDeptNames(otherDept.getDeptNames());
		otherEntity.setDeptIds(otherDept.getDeptIds());
		otherEntity.setRoleIds(otherRole.getRoleIds());
		otherEntity.setRoleNames(otherRole.getRoleNames());
		otherEntity.setUserIds(otherUser.getUserIds());
		otherEntity.setUserNames(otherUser.getUserNames());

		otherEntity.setStuIds(otherStu.getStuIds());
		otherEntity.setStuNames(otherStu.getStuNames());
		otherEntity.setTermIds(otherTerm.getTermIds());
		otherEntity.setTermNames(otherTerm.getTermNames());

		return otherEntity;
	}

	// 获取部门信息
	public NoticeOther getNoticeDeptInfo(String id) {

		NoticeOther otherEntity = new NoticeOther();
		Notice getEntity = this.get(id);

		// 当为指定部门时，才去查询，否则不查询
		if ("2".equals(getEntity.getDeptRadio())) {

			// 通知部门信息
			Set<Department> orgs = getEntity.getNoticeDepts();
			String sbIds = orgs.stream().map((x) -> x.getId()).collect(Collectors.joining(","));
			String sbNames = orgs.stream().map(Department::getNodeText).collect(Collectors.joining(","));

			otherEntity.setDeptIds(sbIds);
			otherEntity.setDeptNames(sbNames);
			otherEntity.setNoticeId(id);
		}

		return otherEntity;
	}

	/**
	 * 根据公告的ID获取公告通知到的的角色数据
	 * 
	 * @param id
	 * @return
	 */
	public NoticeOther getNoticeRoleInfo(String id) {

		NoticeOther otherEntity = new NoticeOther();
		Notice getEntity = this.get(id);

		Set<Role> roles = getEntity.getNoticeRoles();
		String sbIds = roles.stream().map((x) -> x.getId()).collect(Collectors.joining(","));
		String sbNames = roles.stream().map(Role::getRoleName).collect(Collectors.joining(","));

		otherEntity.setRoleIds(sbIds);
		otherEntity.setRoleNames(sbNames);
		otherEntity.setNoticeId(id);

		return otherEntity;
	}

	public NoticeOther getNoticeUserInfo(String id) {

		NoticeOther otherEntity = new NoticeOther();
		Notice getEntity = this.get(id);

		Set<User> users = getEntity.getNoticeUsers();
		String sbIds = users.stream().map((x) -> x.getId()).collect(Collectors.joining(","));
		String sbNames = users.stream().map(User::getName).collect(Collectors.joining(","));

		otherEntity.setUserIds(sbIds);
		otherEntity.setUserNames(sbNames);
		otherEntity.setNoticeId(id);

		return otherEntity;
	}

	public NoticeOther getNoticeTermsInfo(String id) {
		NoticeOther otherEntity = new NoticeOther();
		Notice getEntity = this.get(id);

		// 当为指定终端时，才去查询，否则不查询(注：set集合里面的是设备数据，而显示的应该是房间的数据，所以直接去实体类的数据，做特殊冗余)
		if ("2".equals(getEntity.getTerminalRadio())) {

			Set<InfoTerminal> infos = getEntity.getNoticeTerms();
			String sbIds = infos.stream().map((x) -> x.getId()).collect(Collectors.joining(","));
			String sbNames = infos.stream().map(InfoTerminal::getRoomName).collect(Collectors.joining(","));

			otherEntity.setTermIds(sbIds);
			otherEntity.setTermNames(sbNames);
			otherEntity.setNoticeId(id);
		}

		return otherEntity;
	}

	public NoticeOther getNoticeStuInfo(String id) {

		NoticeOther otherEntity = new NoticeOther();
		Notice getEntity = this.get(id);

		// 当为指定学生时，才去查询，否则不查询
		if ("2".equals(getEntity.getStudentRadio())) {

			Set<User> stus = getEntity.getNoticeStus();
			String sbIds = stus.stream().map((x) -> x.getId()).collect(Collectors.joining(","));
			String sbNames = stus.stream().map(User::getName).collect(Collectors.joining(","));

			otherEntity.setStuIds(sbIds);
			otherEntity.setStuNames(sbNames);
			otherEntity.setNoticeId(id);
		}

		return otherEntity;
	}

//	@Override
//	public List<Notice> getUserOaNotice(User currentUser) {
//		String today = DateUtil.formatDate(new Date());
//		StringBuffer hql = new StringBuffer("select distinct o from Notice as o ");
//		hql.append(" left join fetch o.noticeUsers as u ");
//		hql.append(" left join fetch o.noticeRoles as r ");
//		hql.append(" left join fetch o.noticeDepts as d ");
//		hql.append(" left join fetch o.noticeStus as s ");
//		hql.append(" where o.isDelete=0 ");
//		hql.append(" and o.beginDate<='" + today + "' ");
//		hql.append(" and o.endDate>='" + today + "' ");
//		hql.append(" order by o.createTime desc ");
//		List<Notice> list = this.queryByHql(hql.toString());
//		String userId = currentUser.getId();
//		StringBuffer hql2 = new StringBuffer("from User as u ");
//		hql2.append(" left join fetch u.sysRoles as r ");
//		hql2.append(" left join fetch u.userDepts as d ");
//		hql2.append(" where u.uuid='" + userId + "' ");
//		currentUser = userService.queryByHql(hql2.toString()).get(0);
//		Set<Role> userRoles = currentUser.getSysRoles();
//		// Set<Department> userDepts = currentUser.getUserDepts(); --换成下面的方式
//		Set<Department> userDepts = userService.getDeptByUserId(currentUser.getId());
//
//		List<Notice> list2 = new ArrayList<Notice>();
//		NEXT: for (Notice oaNotice : list) {
//			Set<User> noticeUsers = oaNotice.getNoticeUsers();
//			for (User sysUser : noticeUsers) {
//				if (sysUser.getId().equals(userId)) {
//					list2.add(oaNotice);
//					continue NEXT;
//				}
//			}
//			Set<User> noticeStus = oaNotice.getNoticeStus();
//			for (User sysUser : noticeStus) {
//				if (sysUser.getId().equals(userId)) {
//					list2.add(oaNotice);
//					continue NEXT;
//				}
//			}
//			Set<Role> noticeRoles = oaNotice.getNoticeRoles();
//			for (Role sysRole : noticeRoles) {
//				for (Role userRole : userRoles) {
//					if (sysRole.getId().equals(userRole.getId())) {
//						list2.add(oaNotice);
//						continue NEXT;
//					}
//				}
//			}
//			Set<Department> noticeDepts = oaNotice.getNoticeDepts();
//			for (Department Department : noticeDepts) {
//				for (Department userOrg : userDepts) {
//					if (Department.getId().equals(userOrg.getId())) {
//						list2.add(oaNotice);
//						continue NEXT;
//					}
//				}
//			}
//		}
//		return list2;
//	}

	/**
	 * 获取发送到指定终端的通知公告数据列表
	 * 
	 * @param start
	 *            查询的起始记录数
	 * @param limit
	 *            每页的记录数
	 * @param sort
	 *            排序参数
	 * @param filter
	 *            查询过滤参数
	 * @param termCode
	 *            指定的终端号
	 * @return
	 */
	@Override
	public QueryResult<Notice> list(Integer start, Integer limit, String sort, String filter, String termCode) {
		try {
			InfoTerminal term = oaInfotermService.getByProerties("terminalNo", termCode);
			// 如果存在此终端
			if (ModelUtil.isNotNull(term)) {
				String termId = term.getId();
				String justDateStr = DateUtil.formatDate(new Date());
				StringBuffer hql = new StringBuffer(" from OaNotice o ");
				hql.append(" inner join  fetch o.noticeTerms g ");
				hql.append(MessageFormat.format(" where o.isDelete=0 and g.uuid=''{0}''", termId));
				hql.append(MessageFormat.format(" and o.beginDate<=''{0}'' and o.endDate>=''{1}'' ", justDateStr,
						justDateStr));
				hql.append("order by o.createTime desc");
				QueryResult<Notice> qr = this.queryResult(hql.toString(), start, limit);
				if (qr.getTotalCount() > 0)
					return qr;
				else
					return null;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}