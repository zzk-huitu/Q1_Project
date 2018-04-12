package com.yc.q1.service.base.pt.basic.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.basic.PtCourseArrange;
import com.yc.q1.model.base.pt.basic.PtCourseTeacher;
import com.yc.q1.model.base.pt.basic.PtGrade;
import com.yc.q1.model.base.pt.system.PtDepartment;
import com.yc.q1.model.base.pt.system.PtDeptJob;
import com.yc.q1.model.base.pt.system.PtJob;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserDeptJob;
import com.yc.q1.pojo.base.pt.CommTree;
import com.yc.q1.service.base.pt.basic.PtCourseArrangeService;
import com.yc.q1.service.base.pt.basic.PtCourseTeacherService;
import com.yc.q1.service.base.pt.basic.PtGradeClassService;
import com.yc.q1.service.base.pt.basic.PtTeacherBaseInfoService;
import com.yc.q1.service.base.pt.system.PtDepartmentService;
import com.yc.q1.service.base.pt.system.PtDeptJobService;
import com.yc.q1.service.base.pt.system.PtJobService;
import com.yc.q1.service.base.pt.system.PtUserDeptJobService;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.JsonBuilder;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: JwCourseteacherServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 教师任课信息(JW_T_COURSETEACHER)实体Service接口实现类.
 * date: 2016-08-26
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtCourseTeacherServiceImpl extends BaseServiceImpl<PtCourseTeacher> implements PtCourseTeacherService {

	@Resource(name = "PtCourseTeacherDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtCourseTeacher> dao) {
		super.setDao(dao);
	}

	@Resource
	private PtJobService jobService;

	@Resource
	private PtDeptJobService deptJobService;

	@Resource
	private PtUserDeptJobService userDeptJobService;

	@Resource
	private PtCourseArrangeService courseArrangeService;

	@Resource
	private PtDepartmentService orgService;

	@Resource
	private PtUserService userService;

	@Resource
	private PtGradeClassService gradeClassService;

	@Resource
	private PtTeacherBaseInfoService teacherService;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	/**
	 * 
	 * doAddCourseTeacher:设置任课教师.
	 * 
	 * @author luoyibo
	 * @param studyYeah
	 *            任课学年
	 * @param semester
	 *            任课学期
	 * @param jsonData
	 *            需要设置的教师数据
	 * @param removeIds
	 *            要移除的教师数据
	 * @param currentUser
	 *            当前操作者
	 * @return String
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws @since
	 *             JDK 1.8
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean doAddCourseTeacher(String jsonData, PtUser currentUser)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Boolean strData = false;

		List<PtCourseTeacher> addList = (List<PtCourseTeacher>) JsonBuilder.getInstance().fromJsonArray(jsonData,
				PtCourseTeacher.class);

		for (PtCourseTeacher addTeacher : addList) {
			PtCourseTeacher saveEntity = new PtCourseTeacher();
			BeanUtils.copyPropertiesExceptNull(addTeacher, saveEntity);
			addTeacher.setOrderIndex(0);// 排序
			// 增加时要设置创建人
			addTeacher.setCreateUser(currentUser.getId()); // 创建人
			// 持久化到数据库
			addTeacher.setId(keyRedisService.getId(PtCourseTeacher.ModuleType));	//手动设置id
			this.merge(addTeacher);

			// 根据设置的班级和课程来处理教师所在的部门
			PtUser user = userService.get(addTeacher.getTeacherId());
			String[] propName = new String[] { "jobName", "isDelete" };
			Object[] propValue = new Object[] { "教师", 0 };
			PtJob job = jobService.getByProerties(propName, propValue);
			if (job != null) {
				propName = new String[] { "jobId", "deptId", "isDelete" };
				propValue = new Object[] { job.getId(), addTeacher.getClassId(), 0 };
				PtDeptJob deptjob = deptJobService.getByProerties(propName, propValue);
				if (deptjob != null) {
					propName = new String[] { "userId", "deptId", "jobId", "isDelete" };
					propValue = new Object[] { user.getId(), addTeacher.getClassId(), job.getId(), 0 };
					PtUserDeptJob userdeptjob = userDeptJobService.getByProerties(propName, propValue);
					if (userdeptjob == null) {
						userdeptjob = new PtUserDeptJob();
						userdeptjob.setCreateUser(currentUser.getId());
						userdeptjob.setCreateTime(new Date());
						userdeptjob.setUserId(user.getId());
						userdeptjob.setDeptId(addTeacher.getClassId());
						userdeptjob.setJobId(job.getId());
						userdeptjob.setDeptJobId(deptjob.getId());
						userdeptjob.setIsMainDept(false);
						
						userdeptjob.setId(keyRedisService.getId(PtUserDeptJob.ModuleType));//手动设置id
						userDeptJobService.merge(userdeptjob);

						user.setUpdateTime(new Date());
						user.setUpdateUser(currentUser.getId());
						userService.merge(user);

						// 清除这个用户的部门树缓存，以至于下次读取时更新缓存
						this.delDeptTreeByUsers(user.getId());

					}
				}
			}

			// 更新课表的教师信息
			StringBuffer sql = new StringBuffer("SELECT ISNULL(MAX(ID),'null') FROM T_PT_CourseArrange");
			sql.append(" WHERE isDelete=0 AND isUse=1");
			sql.append(" AND classId='" + addTeacher.getClassId() + "'");
			for (int i = 1; i <= 7; i++) {
				StringBuffer sBuffer = new StringBuffer(" AND courseId0" + i + "='" + addTeacher.getCourseId() + "'");
				String str = sql.toString() + sBuffer.toString();
				List<Object[]> objects = this.queryObjectBySql(str);
				String uuid = objects.get(0) + "";
				if (!uuid.equals("null")) {
					PtCourseArrange courseArrange = courseArrangeService.get(uuid);
					Class clazz = courseArrange.getClass();

					String methodName = "getTeacherId0" + i;
					Method method = clazz.getDeclaredMethod(methodName);
					String tteacId = method.invoke(courseArrange) + "";

					methodName = "getTeacherName0" + i;
					method = clazz.getDeclaredMethod(methodName);
					String teacherName = method.invoke(courseArrange) + "";
					if (StringUtils.isNotEmpty(tteacId)) {
						tteacId += "," + addTeacher.getTeacherId();
						teacherName += "," + addTeacher.getName();
					} else {
						tteacId = addTeacher.getTeacherId();
						teacherName = addTeacher.getName();
					}
					methodName = "setTeacherId0" + i;
					method = clazz.getDeclaredMethod(methodName, String.class);
					method.invoke(courseArrange, tteacId);
					methodName = "setTeacherName0" + i;
					method = clazz.getDeclaredMethod(methodName, String.class);
					method.invoke(courseArrange, teacherName);
					courseArrangeService.merge(courseArrange);
				}
			}
		}

		strData = true;

		return strData;
	}

	public PtDepartment getCourseDept(String classId, String courseId) {
		PtDepartment couseDept = null;
		PtDepartment classDept = orgService.get(classId);
		String[] classDeptTreIds = classDept.getTreeIds().split(",");
		List<PtDepartment> courseDeptList = orgService.queryByProerties("courseId", courseId);
		for (PtDepartment baseOrg : courseDeptList) {
			String[] treeIds = baseOrg.getTreeIds().split(",");
			if (classDeptTreIds[1].equals(treeIds[1])) {
				couseDept = baseOrg;
				break;
			}
		}
		return couseDept;
	}

	@Override
	public Boolean doDelCourseTeacher(String delIds, PtUser currentUser) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Boolean reResult = false;
		String[] idStrings = delIds.split(",");
		List<PtCourseTeacher> relaceList = this.queryByProerties("id", idStrings);
		for (PtCourseTeacher jwCourseteacher : relaceList) {

			// 删除部门岗位（存在问题，当一名教师能教授多门课程时，若删除了一门，那么这个教师在此班级的部门岗位也被删除了）
			String[] propName = new String[] { "jobName", "isDelete" };
			Object[] propValue = new Object[] { "教师", 0 };
			PtJob job = jobService.getByProerties(propName, propValue);
			if (job != null) {
				propName = new String[] { "userId", "deptId", "jobId", "isDelete" };
				propValue = new Object[] { jwCourseteacher.getTeacherId(), jwCourseteacher.getClassId(), job.getId(),
						0 };
				PtUserDeptJob userdeptjob = userDeptJobService.getByProerties(propName, propValue);
				if (userdeptjob != null) { // 在事务中，若第一次循环设置为了isdelete=1，那么第二次同样的条件查询是查不到该数据，即会预先执行。
					// 查询此人员是否在此班级任课多门
					String hql = "select count(*) from CourseTeacher a where a.isDelete=0 " + " and a.classId='"
							+ jwCourseteacher.getName() + "' and teacherId='" + jwCourseteacher.getTeacherId() + "'";
					Integer num = this.getQueryCountByHql(hql);
					boolean del = false;
					if (num > 1) {
						// 当num大于1时，就查询当前删除的数据列表中，是否是存在这些多门课程
						long num2 = relaceList.stream()
								.filter(x -> x.getTeacherId().equals(jwCourseteacher.getTeacherId()))
								.filter(x -> x.getClassId().equals(jwCourseteacher.getClassId())).count();
						if (num == num2) {
							del = true;
						}
					} else
						del = true;

					if (del == true) {
						userdeptjob.setIsDelete(1);
						userdeptjob.setUpdateTime(new Date());
						userdeptjob.setUpdateUser(currentUser.getId());
						userDeptJobService.merge(userdeptjob);

						// 清除这个用户的部门树缓存，以至于下次读取时更新缓存
						this.delDeptTreeByUsers(jwCourseteacher.getTeacherId());
					}
				}
			}

			// 删除课表（修改课表中的教师信息）
			StringBuffer sql = new StringBuffer("SELECT ISNULL(MAX(ID),'null') FROM T_PT_CourseArrange");
			sql.append(" WHERE  isDelete=0 AND isUse=1");
			sql.append(" AND classId='" + jwCourseteacher.getClassId() + "'");
			for (int i = 1; i <= 7; i++) {
				StringBuffer sBuffer = new StringBuffer(
						" AND courseId0" + i + "='" + jwCourseteacher.getCourseId() + "'");
				String str = sql.toString() + sBuffer.toString();
				List<Object[]> objects = this.queryObjectBySql(str);
				String uuid = objects.get(0) + "";
				if (!uuid.equals("null")) {
					PtCourseArrange courseArrange = courseArrangeService.get(uuid);
					Class clazz = courseArrange.getClass();
					String methodName = "getTeacherId0" + i;
					Method method = clazz.getDeclaredMethod(methodName);
					String tteacId = method.invoke(courseArrange) + "";

					methodName = "getTeacherName0" + i;
					method = clazz.getDeclaredMethod(methodName);
					String teacherName = method.invoke(courseArrange) + "";
					if (StringUtils.isNotEmpty(tteacId)) {
						if (tteacId.indexOf(jwCourseteacher.getTeacherId()) == 0) {
							tteacId = tteacId.replace(jwCourseteacher.getTeacherId() + ",", "");
							teacherName = teacherName.replace(jwCourseteacher.getName() + ",", "");
						} else {
							tteacId = tteacId.replace("," + jwCourseteacher.getTeacherId(), "")
									.replace(jwCourseteacher.getTeacherId(), "");
							teacherName = teacherName.replace("," + jwCourseteacher.getName(), "")
									.replace(jwCourseteacher.getName(), "");
						}
					}
					methodName = "setTeacherId0" + i;
					method = clazz.getDeclaredMethod(methodName, String.class);
					method.invoke(courseArrange, tteacId);
					methodName = "setTeacherName0" + i;
					method = clazz.getDeclaredMethod(methodName, String.class);
					method.invoke(courseArrange, teacherName);
					courseArrangeService.merge(courseArrange);
				}
			}

			// 删除任课教师表
			jwCourseteacher.setUpdateTime(new Date());
			jwCourseteacher.setUpdateUser(currentUser.getId());
			jwCourseteacher.setIsDelete(1);
			this.merge(jwCourseteacher);
			reResult = true;
		}

		return reResult;

	}

	@Override
	public String updateZjsByClassId(String classId, String courseId, int zjs) {
		PtGrade grade = gradeClassService.findJwTGradeByClassId(classId);
		String hql = "update CourseTeacher ct set ct.courseCountWeek=" + zjs + " where "
				+ " ct.classId in( select gc.id from GradeClass gc where gc.gradeId"
				+ " in (select id from Grade where sectionCode='" + grade.getSectionCode() + "' )) and  ct.courseId='"
				+ courseId + "' ";
		doExecuteCountByHql(hql);
		return null;
	}

	@Override
	public void updatePubliceClass(String classId, String courseId, String publicClassId) {
		// TODO Auto-generated method stub
		PtGrade grade = gradeClassService.findJwTGradeByClassId(courseId);
		String hql = "update CourseTeacher ct set ct.classId='" + publicClassId + "'  where ct.id in ("
				+ " select c.id from GradeClass g, CourseTeacher c where" + " c.classId=g.id and c.courseId='"
				+ courseId + "'  and g.gradeId   ='" + grade.getId() + "' ) ";

		doExecuteCountByHql(hql);
	}

	@Override
	public CommTree getUserRightDeptDisciplineTree(String rootId, PtUser currentUser) {
		// 1.查询部门的数据，并封装到实体类中
		List<CommTree> list = orgService.getUserRightDeptDisciplineTreeList(currentUser);

		// 2.找到根节点
		CommTree root = new CommTree();
		for (CommTree node : list) {
			// if (!(StringUtils.isNotEmpty(node.getParent()) &&
			// !node.getId().equals(rootId))) {
			if (StringUtils.isEmpty(node.getParent()) || node.getId().equals(rootId)) {
				root = node;
				list.remove(node);
				break;
			}
		}

		// 3.递归组装children
		createTreeChildren(list, root);

		return root;
	}

	private void createTreeChildren(List<CommTree> childrens, CommTree root) {
		String parentId = root.getId();
		for (int i = 0; i < childrens.size(); i++) {
			CommTree node = childrens.get(i);
			if (StringUtils.isNotEmpty(node.getParent()) && node.getParent().equals(parentId)) {
				root.getChildren().add(node);
				createTreeChildren(childrens, node);
			}
			if (i == childrens.size() - 1) {
				if (root.getChildren().size() < 1) {
					root.setLeaf(true);
				}
				return;
			}
		}
	}

	/**
	 * 删除这个部门下所有用户的部门权限的缓存数据
	 * 
	 * @param userIds
	 */
	public void delDeptTreeByUsers(Object... userIds) {
		// TODO Auto-generated method stub
		/* 删除用户的菜单redis数据，以至于下次刷新或请求时，可以加载最新数据 */
		if (userIds.length > 0) {
			HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
			hashOper.delete("userRightDeptTree", userIds);
			hashOper.delete("userRightDeptClassTree", userIds);
			hashOper.delete("userRightDeptDisciplineTree", userIds);
		}
	}

	@Override
	public QueryResult<PtCourseTeacher> getClassCourseTeacherList(Integer start, Integer limit, String sort,
			String filter, Boolean isDelete, String claiId, Integer claiLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer doReplaceCourseTeacher(String jctUuid, String teaId, PtUser sysUser) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		PtCourseTeacher jct = this.get(jctUuid);
		PtUser teaInfo = userService.get(teaId);

		// 1.判断新教师，是否有在此班级上任课
		String[] propName = new String[] { "classId", "teacherId", "studyYear", "semester", "courseId", "isDelete" };
		Object[] propValue = new Object[] { jct.getClassId(), teaId, jct.getStudyYear(), jct.getSemester(),
				jct.getCourseId(), 0 };
		PtCourseTeacher tempJct = this.getByProerties(propName, propValue);
		if (tempJct != null)
			return -1;

		// 2.判断此人在此班级是否还有其他任课，并确定是否删除他的部门岗位
		// 删除部门岗位（存在问题，当一名教师能教授多门课程时，若删除了一门，那么这个教师在此班级的部门岗位也被删除了）
		propName = new String[] { "jobName", "isDelete" };
		propValue = new Object[] { "教师", 0 };
		PtJob job = jobService.getByProerties(propName, propValue);
		if (job != null) {
			propName = new String[] { "userId", "deptId", "jobId", "isDelete" };
			propValue = new Object[] { jct.getTeacherId(), jct.getClassId(), job.getId(), 0 };
			PtUserDeptJob userdeptjob = userDeptJobService.getByProerties(propName, propValue);
			if (userdeptjob != null) {
				// 查询此人员是否在此班级任课多门
				String hql = "select count(*) from CourseTeacher a where a.isDelete=0 " + " and a.classId='"
						+ jct.getClassId() + "' and teacherId='" + jct.getTeacherId() + "'";
				Integer num = this.getQueryCountByHql(hql);
				boolean del = false;
				if (num == 1)
					del = true;

				if (del == true) {
					userdeptjob.setIsDelete(1);
					userdeptjob.setUpdateTime(new Date());
					userdeptjob.setUpdateUser(sysUser.getId());
					userDeptJobService.merge(userdeptjob);

					// 清除这个用户的部门树缓存，以至于下次读取时更新缓存
					this.delDeptTreeByUsers(jct.getTeacherId());
				}
			}
		}

		// 3.判断新教师是否已有此部门岗位，并确定是否加入部门岗位，
		propName = new String[] { "userId", "deptId", "jobId", "isDelete" };
		propValue = new Object[] { teaId, jct.getClassId(), job.getId(), 0 };
		PtUserDeptJob userdeptjob2 = userDeptJobService.getByProerties(propName, propValue);
		if (userdeptjob2 == null) {
			userdeptjob2 = new PtUserDeptJob();
			userdeptjob2.setCreateUser(sysUser.getId());
			userdeptjob2.setCreateTime(new Date());
			userdeptjob2.setUserId(teaId);
			userdeptjob2.setDeptId(jct.getClassId());
			userdeptjob2.setJobId(job.getId());
			userdeptjob2.setDeptJobId(jct.getId());
			userdeptjob2.setIsMainDept(false);
			userdeptjob2.setId(keyRedisService.getId(PtUserDeptJob.ModuleType));	//手动设置id
			userDeptJobService.merge(userdeptjob2);

			// 清除这个用户的部门树缓存，以至于下次读取时更新缓存
			this.delDeptTreeByUsers(teaId);

		}

		// 4.更新课表上的教师信息，采用relace的方式
		StringBuffer sql = new StringBuffer("SELECT ISNULL(MAX(ID),'null') FROM T_PT_CourseArrange");
		sql.append(" WHERE  isDelete=0 AND isUse=1");
		sql.append(" AND classId='" + jct.getClassId() + "'");
		for (int i = 1; i <= 7; i++) {
			StringBuffer sBuffer = new StringBuffer(" AND courseId0" + i + "='" + jct.getCourseId() + "'");
			String str = sql.toString() + sBuffer.toString();
			List<Object[]> objects = this.queryObjectBySql(str);
			String uuid = objects.get(0) + "";
			if (!uuid.equals("null")) {
				PtCourseArrange courseArrange = courseArrangeService.get(uuid);
				Class clazz = courseArrange.getClass();
				String methodName = "getTeacherId0" + i;
				Method method = clazz.getDeclaredMethod(methodName);
				String tteacId = method.invoke(courseArrange) + "";

				methodName = "getTeacherName0" + i;
				method = clazz.getDeclaredMethod(methodName);
				String teacherName = method.invoke(courseArrange) + "";

				if (StringUtils.isNotEmpty(tteacId)) {
					tteacId = tteacId.replace(jct.getTeacherId(), teaInfo.getId());
					teacherName = teacherName.replace(jct.getName(), teaInfo.getName());
				} else {
					tteacId = teaInfo.getId();
					teacherName = teaInfo.getName();
				}

				methodName = "setTeacherId0" + i;
				method = clazz.getDeclaredMethod(methodName, String.class);
				method.invoke(courseArrange, tteacId);
				methodName = "setTeacherName0" + i;
				method = clazz.getDeclaredMethod(methodName, String.class);
				method.invoke(courseArrange, teacherName);
				courseArrangeService.merge(courseArrange);
			}
		}

		// 5.更新courseTeacher的teacherId值
		jct.setUpdateTime(new Date());
		jct.setUpdateUser(sysUser.getId());
		jct.setTeacherId(teaInfo.getId());
		this.merge(jct);

		return 1;
	}

}