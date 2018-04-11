package com.yc.q1.base.pt.basic.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.dao.TeacherBaseInfoDao;
import com.yc.q1.base.pt.basic.service.TeacherBaseInfoService;
import com.yc.q1.base.pt.system.service.JobService;
import com.yc.q1.base.pt.system.service.DepartmentService;
import com.yc.q1.base.pt.system.service.RoleService;
import com.yc.q1.base.pt.system.service.UserService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.basic.TeacherBaseInfo;
import com.yc.q1.model.base.pt.system.Department;
import com.yc.q1.model.base.pt.system.Role;
import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.base.pt.system.service.UserDeptJobService;
import com.zd.core.constant.AdminType;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.StringUtils;

/**
 * ClassName: TeaTeacherbaseServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 教职工基本数据实体Service接口实现类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class TeacherBaseInfoServiceImpl extends BaseServiceImpl<TeacherBaseInfo> implements TeacherBaseInfoService {

	@Resource(name = "TeacherBaseInfoDao") // 将具体的dao注入进来
	public void setDao(BaseDao<TeacherBaseInfo> dao) {
		super.setDao(dao);
	}

	@Resource
	private DepartmentService orgService; // 部门服务层

	@Resource
	private JobService jobService;

	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService; // service层接口

	@Resource
	private UserDeptJobService userDeptJobService;
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public QueryResult<TeacherBaseInfo> getDeptTeacher(Integer start, Integer limit, String sort, String filter,
			String qureyFilter, Boolean isDelete, String deptId, User currentUser) {
		StringBuilder hql = new StringBuilder();
		StringBuilder countHql = new StringBuilder();
		String filterHql = "";
		String rightDeptIds = "";
		if ("0".equals(currentUser.getRightType())) {
			// 如果当前用户不是所有的部门权限，则取有权限的部门
			rightDeptIds = userService.getUserOwnDeptids(currentUser);
		}
		String queryFilterSql = StringUtils.convertFilterToSql(qureyFilter);
		// 当前是进行组合查询
		if (StringUtils.isNotEmpty(qureyFilter)) {
			if ("0".equals(currentUser.getRightType())) {
				// 当前用户有所有部门权限
				hql.append(
						"select o,k.jobName from TeacherBaseInfo as o left join UserDeptJob as r on o.id=r.userId and r.isDelete=0 ");
				hql.append(" left join Job as k on k.id=r.jobId where o.isDelete=0  ");
				hql.append(queryFilterSql);
			} else {
				if (StringUtils.isNotEmpty(rightDeptIds)) {
					// 不是根部门
					if (deptId.equals("058b21fe-b37f-41c9-ad71-091f97201ff8")) {
						hql.append(
								"select o,'临时部门' from TeacherBaseInfo o where o.id not in(select userId from UserDeptJob where isDelete=0) ");
						hql.append(queryFilterSql);
					} else {
						// 当前用户有指定的部门权限
						hql.append(
								"select o,k.jobName from TeacherBaseInfo as o left join UserDeptJob as r on o.id=r.userId and r.isDelete=0 ");
						hql.append(" and r.deptId in(" + rightDeptIds + ") ");
						hql.append(" left join Department as k on k.id=r.jobId where o.isDelete=0  ");
						hql.append(queryFilterSql);
					}
				}
			}
		} else {
			// 指定部门显示
			if ("0".equals(currentUser.getRightType())) {
				if ("2851655E-3390-4B80-B00C-52C7CA62CB39".equals(deptId)) {
					// 点击的是根部门，取有权限的部门的数据
					if (StringUtils.isNotEmpty(rightDeptIds)) {
						// 当前用户有指定的部门权限
						hql.append(
								"select o,k.jobName from TeacherBaseInfo as o inner join UserDeptJob as r on o.id=r.userId and r.isDelete=0 ");
						hql.append(" inner join Job as k on k.id=r.jobId where o.isDelete=0  ");
						hql.append(" order by k.orderIndex ");
					}
				} else {
					if (StringUtils.isNotEmpty(deptId)) {
						// 不是根部门
						if (deptId.equals("058b21fe-b37f-41c9-ad71-091f97201ff8")) {
							hql.append(
									"select o,'临时部门' from TeacherBaseInfo o where o.id not in(select userId from UserDeptJob where isDelete=0) ");
						} else {
							hql.append("SELECT a,d.jobName FROM TeacherBaseInfo a ");
							hql.append(" INNER JOIN UserDeptJob b ON a.id=b.userId ");
							hql.append(" INNER JOIN Job d ON d.id=b.jobId ");
							hql.append(" WHERE PATINDEX('%" + deptId + "%', b.treeIds)>0 ");
							hql.append(" and a.isDelete=0 and b.isDelete=0 and d.isDelete=0 ");
							hql.append(" ORDER BY d.orderIndex ");
						}
					}
				}
			} else {
				if (StringUtils.isNotEmpty(deptId)) {
					// 不是根部门
					if (deptId.equals("058b21fe-b37f-41c9-ad71-091f97201ff8")) {
						hql.append(
								"select o,'临时部门' from TeacherBaseInfo o where o.id not in(select userId from UserDeptJob where isDelete=0) ");
					} else {
						hql.append("select o,k.jobName from TeacherBaseInfo as o ,BaseUserdeptjob as r,Job as k ");
						hql.append(
								"where o.id=r.userId and k.id=r.jobId and o.isDelete=0 and r.isDelete=0 and patindex('%"
										+ deptId + "%',r.treeIds)>0 ");
						hql.append(" and r.deptId in(" + rightDeptIds + ")");
						hql.append(" order by k.orderIndex ");
					}
				}
			}
		}
		if (hql.length() > 0) {
			QueryResult<TeacherBaseInfo> qr = this.queryResult(hql.toString(), start, limit);
			List<?> alist = qr.getResultList();
			Integer lenth = alist.size();
			// Set<TeaTeacherbase> tt = new LinkedHashSet<TeaTeacherbase>();
			List<TeacherBaseInfo> tt = new ArrayList<>();
			for (int i = 0; i < lenth; i++) {
				Object[] obj = (Object[]) alist.get(i);
				TeacherBaseInfo teacherbase = (TeacherBaseInfo) obj[0];
				teacherbase.setJobName((String) obj[1]);
				tt.add(teacherbase);
			}
			qr.getResultList().clear();
			qr.getResultList().addAll(tt);
			// qr.setTotalCount((long) tt.size());
			return qr;
		} else {
			return null;
		}
	}

	@Override
	public Boolean batchSetDept(String deptId, String userIds, User currentUser) {
		Boolean reResult = false;
		/*
		 * String[] delId = userIds.split(","); List<TeaTeacherbase> list =
		 * this.queryByProerties("uuid", delId); for (TeaTeacherbase teacher :
		 * list) { Set<BaseOrg> userDept = teacher.getUserDepts(); BaseOrg org =
		 * orgService.get(deptId); BaseOrg tempOrg =
		 * orgService.get("058b21fe-b37f-41c9-ad71-091f97201ff8");
		 * userDept.remove(tempOrg); userDept.add(org);
		 * 
		 * teacher.setUserDepts(userDept); teacher.setUpdateTime(new Date());
		 * teacher.setUpdateUser(currentUser.getId());
		 * 
		 * this.merge(teacher); reResult = true; }
		 */
		return reResult;
	}

	@Override
	public Boolean delTeaFromDept(String deptId, String userIds, User currentUser) {
		Boolean reResult = false;
		/*
		 * String[] delId = userIds.split(","); List<BaseOrg> all =
		 * orgService.getOrgAndChildList(deptId, "", currentUser, false); for
		 * (String id : delId) { String hql =
		 * "from TeaTeacherbase as u inner join fetch u.userDepts as d where u.uuid='"
		 * + id + "' and d.isDelete=0"; TeaTeacherbase teahcher =
		 * this.queryByHql(hql).get(0);
		 * 
		 * Set<BaseOrg> userDept = teahcher.getUserDepts();
		 * userDept.removeAll(all); teahcher.setUserDepts(userDept);
		 * teahcher.setUpdateTime(new Date());
		 * teahcher.setUpdateUser(currentUser.getId()); this.merge(teahcher);
		 * reResult = true; }
		 */
		return reResult;
	}

	@Override
	public Boolean setTeaToJob(String jobId, String userIds, User currentUser) {
		Boolean reResult = false;
		/*
		 * String[] delId = userIds.split(","); String[] jobIds =
		 * jobId.split(","); Map<String, String> sortedCondition = new
		 * HashMap<String, String>(); sortedCondition.put("jobCode", "ASC");
		 * 
		 * List<BaseJob> listJob = jobService.queryByProerties("uuid", jobIds,
		 * sortedCondition); List<TeaTeacherbase> list =
		 * this.queryByProerties("uuid", delId); for (TeaTeacherbase teacher :
		 * list) { Set<BaseJob> userJob = teacher.getUserJobs(); for (BaseJob
		 * job : listJob) { userJob.add(job); }
		 * teacher.setJobId(listJob.get(0).getId());
		 * teacher.setJobCode(listJob.get(0).getJobCode());
		 * teacher.setUserJobs(userJob); teacher.setUpdateTime(new Date());
		 * teacher.setUpdateUser(currentUser.getId());
		 * 
		 * this.merge(teacher); reResult = true; }
		 */
		return reResult;
	}

	@Override
	public Boolean delTeaFromJob(String jobId, String userIds, User currentUser) {
		Boolean reResult = false;
		/*
		 * String[] delId = userIds.split(","); Object[] propValue =
		 * jobId.split(","); Map<String, String> sortedCondition = new
		 * HashMap<String, String>(); sortedCondition.put("jobCode", "ASC");
		 * 
		 * List<BaseJob> all = jobService.queryByProerties("uuid", propValue,
		 * sortedCondition); for (String id : delId) { String hql =
		 * "from TeaTeacherbase as u inner join fetch u.userJobs as d where u.uuid='"
		 * + id + "' and d.isDelete=0"; TeaTeacherbase teahcher =
		 * this.queryByHql(hql).get(0);
		 * 
		 * Set<BaseJob> userJob = teahcher.getUserJobs(); //删除要解除绑定的岗位
		 * userJob.removeAll(all);
		 * 
		 * //将剩下的岗位按jobCode排序后将第一个设置为主岗位 List<BaseJob> listTemp = new
		 * ArrayList<BaseJob>(); listTemp.addAll(userJob); SortListUtil<BaseJob>
		 * sortJob = new SortListUtil<BaseJob>(); sortJob.Sort(listTemp,
		 * "jobCode", ""); teahcher.setJobId(listTemp.get(0).getId());
		 * teahcher.setJobCode(listTemp.get(0).getJobCode());
		 * 
		 * teahcher.setUserJobs(userJob); teahcher.setUpdateTime(new Date());
		 * teahcher.setUpdateUser(currentUser.getId()); this.merge(teahcher);
		 * reResult = true; }
		 */
		return reResult;
	}

	@Override
	public QueryResult<Department> getTeahcerJobList(TeacherBaseInfo teahcher, User currentUser) {
		/*
		 * String hql =
		 * "from SysUser as u inner join fetch u.userJobs as r where u.uuid='" +
		 * teahcher.getId() + "' and r.isDelete=0 "; List<TeaTeacherbase> list =
		 * this.queryByHql(hql); Set<BaseJob> userJobs =
		 * list.get(0).getUserJobs(); // Set<BaseJob> userJobs =
		 * teahcher.getUserJobs(); if (userJobs.size() > 0) { List<BaseJob>
		 * listJob = new ArrayList<BaseJob>(); for (BaseJob baseJob : userJobs)
		 * { listJob.add(baseJob); } SortListUtil<BaseJob> sortJob = new
		 * SortListUtil<BaseJob>(); sortJob.Sort(listJob, "jobCode", "");
		 * QueryResult<BaseJob> qr = new QueryResult<BaseJob>();
		 * qr.setResultList(listJob); qr.setTotalCount((long) listJob.size());
		 * 
		 * return qr; } else
		 */
		return null;
	}

	@Override
	public String getTeacherJobs(TeacherBaseInfo teacher) {
		/*
		 * String hql =
		 * "from SysUser as u inner join fetch u.userJobs as r where u.uuid='" +
		 * teacher.getId() + "' and r.isDelete=0 "; List<TeaTeacherbase>
		 * listTeachar = this.queryByHql(hql); Set<BaseJob> userJobs =
		 * listTeachar.get(0).getUserJobs(); // Set<BaseJob> userJobs =
		 * teahcher.getUserJobs(); List<BaseJob> list = new ArrayList<>();
		 * list.addAll(userJobs); SortListUtil<BaseJob> sortJob = new
		 * SortListUtil<BaseJob>(); sortJob.Sort(list, "jobCode", "");
		 * StringBuffer sbJobId = new StringBuffer(); StringBuffer sbJobName =
		 * new StringBuffer(); for (BaseJob baseJob : list) {
		 * sbJobId.append(baseJob.getId() + "|");
		 * sbJobName.append(baseJob.getJobName() + "|"); } String jobIds =
		 * StringUtils.trimLast(sbJobId.toString()); String jobNames =
		 * StringUtils.trimLast(sbJobName.toString()); if (jobIds.length() > 0)
		 * return jobIds + "," + jobNames; else return ",";
		 */
		return "";
	}

	@Override
	public String getTeacherDepts(TeacherBaseInfo teacher) {
		/*
		 * Set<BaseOrg> userDepts = teacher.getUserDepts(); List<BaseOrg> list =
		 * new ArrayList<>(); list.addAll(userDepts); SortListUtil<BaseOrg>
		 * sortJob = new SortListUtil<BaseOrg>(); sortJob.Sort(list, "jobCode",
		 * ""); StringBuffer sbDeptId = new StringBuffer(); StringBuffer
		 * sbDeptName = new StringBuffer(); for (BaseOrg baseOrg : list) {
		 * sbDeptId.append(baseOrg.getId() + "|");
		 * sbDeptName.append(baseOrg.getNodeText() + "|"); } String deptIds =
		 * StringUtils.trimLast(sbDeptId.toString()); String deptNames =
		 * StringUtils.trimLast(sbDeptName.toString()); if (deptNames.length() >
		 * 0) return deptIds + "," + deptNames; else return ",";
		 */
		return "";
	}

	@Override
	public QueryResult<TeacherBaseInfo> getCourseTeacherlist(Integer start, Integer limit, String sort, String filter,
			String whereSql, String orderSql, String querySql, Boolean isDelete) {
		QueryResult<TeacherBaseInfo> qr = this.queryPageResult(start, limit, sort, filter, true);

		return this.setTeacherJobAndDept(qr);
	}

	public QueryResult<TeacherBaseInfo> setTeacherJobAndDept(QueryResult<TeacherBaseInfo> qr) {
		QueryResult<TeacherBaseInfo> qrr = new QueryResult<TeacherBaseInfo>();
		List<TeacherBaseInfo> newList = new ArrayList<TeacherBaseInfo>();
		/*
		 * for (TeaTeacherbase teaTeacherbase : qr.getResultList()) { String
		 * jobInfo = this.getTeacherJobs(teaTeacherbase); String[] strings =
		 * jobInfo.split(","); teaTeacherbase.setAllJobId(strings[0]);
		 * teaTeacherbase.setAllJobName(strings[1]);
		 * 
		 * String deptInfo = this.getTeacherDepts(teaTeacherbase); strings =
		 * deptInfo.split(","); teaTeacherbase.setDeptName(strings[1]);
		 * 
		 * newList.add(teaTeacherbase); } qrr.setResultList(newList);
		 * qrr.setTotalCount(qr.getTotalCount());
		 */

		return qrr;
	}

	@Override
	public TeacherBaseInfo doAddTeacher(TeacherBaseInfo entity,
			User currentUser/* , String deptJobId */) {
		TeacherBaseInfo saveEntity = new TeacherBaseInfo();
		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("id");
		try {
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 给老师加入 教师角色
		Integer orderIndex = this.getDefaultOrderIndex(entity);
		saveEntity.setOrderIndex(orderIndex);// 排序
		saveEntity.setCategory("1");
		saveEntity.setState(true);
		saveEntity.setIsDelete(0);
		saveEntity.setIsHidden(false);
		saveEntity.setIsSystem(true);
		saveEntity.setRightType("2");
		saveEntity.setUserPwd(new Sha256Hash("123456").toHex());
		saveEntity.setSchoolId(AdminType.ADMIN_ORG_ID);

		// 增加角色
		Set<Role> theUserRoler = saveEntity.getSysRoles();
		Role role = roleService.getByProerties(new String[] { "roleCode", "isDelete" }, new Object[] { "TEACHER", 0 });
		if (role != null) {
			theUserRoler.add(role);
			saveEntity.setSysRoles(theUserRoler);
		}

		// 增加时要设置创建人
		saveEntity.setCreateUser(currentUser.getId()); // 创建人

		// 持久化到数据库
		saveEntity.setId(keyRedisService.getId(TeacherBaseInfo.ModuleType));	//手动设置id
		entity = this.merge(saveEntity);

		String userIds = entity.getId();
		String deptJobId = entity.getDeptId();
		userDeptJobService.doAddUserToDeptJob(deptJobId, userIds, currentUser);
		return entity;
	}
}