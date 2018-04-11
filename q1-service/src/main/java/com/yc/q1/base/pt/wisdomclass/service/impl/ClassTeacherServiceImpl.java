package com.yc.q1.base.pt.wisdomclass.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.system.service.DeptJobService;
import com.yc.q1.base.pt.system.service.JobService;
import com.yc.q1.base.pt.system.service.DepartmentService;
import com.yc.q1.base.pt.system.service.RoleService;
import com.yc.q1.base.pt.system.service.UserService;
import com.yc.q1.base.pt.system.service.UserDeptJobService;
import com.yc.q1.base.pt.wisdomclass.dao.ClassTeacherDao;
import com.yc.q1.base.pt.wisdomclass.service.ClassTeacherService;
import com.yc.q1.base.redis.service.DeptRedisService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.system.PtDeptJob;
import com.yc.q1.model.base.pt.system.PtJob;
import com.yc.q1.model.base.pt.system.PtRole;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserDeptJob;
import com.yc.q1.model.base.pt.wisdomclass.PtClassStar;
import com.yc.q1.model.base.pt.wisdomclass.PtClassTeacher;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: JwClassteacherServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 班主任信息实体Service接口实现类. date: 2016-08-22
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class ClassTeacherServiceImpl extends BaseServiceImpl<PtClassTeacher> implements ClassTeacherService {

	@Resource(name="ClassTeacherDao")	//将具体的dao注入进来
	public void setDao(BaseDao<PtClassTeacher> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
    @Resource
    private DepartmentService orgService;

    @Resource
    private JobService jobService;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
	private DeptJobService deptJobService;

	@Resource
	private UserDeptJobService userDeptJobService;
	
	@Resource
	private DeptRedisService deptRedisService;
	
//    /**
//	 * 
//	 * getClassLeader:获取指定学生的所在班级的班主任
//	 *
//	 * @author luoyibo
//	 * @param userId
//	 * @return String
//	 * @throws @since
//	 *             JDK 1.8
//	 */
//	@Override
//	public String getClassLeader(String userId) {
//		String classLeader = "";
//		String sql = "EXECUTE T_PT_ClassTeacher '" + userId + "'";
//
//		List lists = this.queryEntityBySql(sql, null);
//		classLeader = lists.get(0).toString();
//
//		return classLeader;
//	}
//
//	@Override
//	public String getClassLeaderList(String userId) {
//		String classLeader = "";
//		String sql = "EXECUTE T_PT_ClassTeacher '" + userId + "'";
//
//		try {
//			List lists = this.dao.queryEntityBySql(sql);
//			Set set = new HashSet<>(lists);
//			lists = new ArrayList<>(set);
//			for (Object object : lists) {
//				classLeader += object + ",";
//			}
//			classLeader = classLeader.substring(0, classLeader.length() - 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			classLeader = "-1";
//		}
//
//		return classLeader;
//	}

	@Override
	public PtClassTeacher doAddClassTeacher(PtClassTeacher entity, PtUser currentUser)
			throws IllegalAccessException, InvocationTargetException {

		PtClassTeacher perEntity = new PtClassTeacher();
		perEntity.setId(keyRedisService.getId(PtClassTeacher.ModuleType));
		BeanUtils.copyPropertiesExceptNull(entity, perEntity);
		// 生成默认的orderindex
		Integer orderIndex = this.getDefaultOrderIndex(entity);
		entity.setOrderIndex(orderIndex);// 排序
		// 增加时要设置创建人
		entity.setCreateUser(currentUser.getId()); // 创建人
		// 持久化到数据库
		entity = this.merge(entity);

		/*暂不处理年级组长问题
		JwGradeclassteacher gcTeacher = new JwGradeclassteacher(entity.getId());
		BeanUtils.copyPropertiesExceptNull(gcTeacher, entity);

		gcTeacher.setTeaType("1");
		gcTeacher.setCategory(entity.getCategory() + 2);
		gcTeacher.setGraiId(entity.getClaiId());
		gcTeacher.setStudyYear(entity.getStudyYear());
		gcTeacherService.persist(gcTeacher);
		 */
		
		// 增加后要同步此人的岗位数据
		String teacherId = entity.getTeacherId(); // 教师ID
		String deptId = entity.getClassId(); // 班级ID,对应部门ID

		PtUser user = userService.get(teacherId);
		// 设置增加默认的班主任角色
		Set<PtRole> theUserRole = user.getSysRoles();
		PtRole role = roleService.getByProerties(new String[]{"roleCode","isDelete"},new Object[]{ "CLASSLEADER",0});
		if (role!=null) {
			theUserRole.add(role);
			user.setSysRoles(theUserRole);
		}

		//设置部门岗位
		String[] propName = new String[] { "jobName", "isDelete" };
		Object[] propValue = new Object[] { "班主任", 0 };
		PtJob job = jobService.getByProerties(propName, propValue);
		if(job!=null){
			propName = new String[] { "jobId", "deptId", "isDelete" };
			propValue = new Object[] { job.getId(), deptId, 0 };
			PtDeptJob deptjob = deptJobService.getByProerties(propName, propValue);
			
			if(deptjob!=null){
				propName = new String[] { "userId", "deptjobId", "isDelete" };
				propValue = new Object[] { user.getId(), deptjob.getId(), 0 };
				PtUserDeptJob userdeptjob=userDeptJobService.getByProerties(propName, propValue);
				if(userdeptjob==null){
					userdeptjob = new PtUserDeptJob();
					userdeptjob.setId(keyRedisService.getId(PtUserDeptJob.ModuleType));
					userdeptjob.setCreateUser(currentUser.getId());
					userdeptjob.setCreateTime(new Date());
					userdeptjob.setUserId(user.getId());
					userdeptjob.setDeptId(deptId);
					userdeptjob.setJobId(job.getId());
					userdeptjob.setDeptJobId(deptjob.getId());
					userdeptjob.setIsMainDept(false);
					userDeptJobService.merge(userdeptjob);
					
				}				
			}
			
		}
		
		user.setUpdateTime(new Date());
		user.setUpdateUser(currentUser.getId());
		userService.merge(user);
		
		//清除这个用户的部门树缓存，以至于下次读取时更新缓存
		deptRedisService.deleteDeptTreeByUsers(user.getId());
		
		return entity;
	}

	/**
	 * 逻辑删除班主任信息
	 */
	@Override
	public Boolean doDelete(String delIds, PtUser currentUser) {
		Boolean result = false;
		String[] dels = delIds.split(",");
		List<PtClassTeacher> list = this.queryByProerties("id", dels);
		for (PtClassTeacher gt : list) {
			gt.setEndTime(new Date());
			gt.setIsDelete(1);
			gt.setUpdateTime(new Date());
			gt.setUpdateUser(currentUser.getId());
			this.merge(gt);
			
			String[] propName = new String[] { "jobName", "isDelete" };
			Object[] propValue = new Object[] { "班主任", 0 };
			PtJob job = jobService.getByProerties(propName, propValue);
			if(job!=null){
				propName = new String[] { "userId", "deptId", "jobId", "isDelete" };
				propValue = new Object[] { gt.getTeacherId(), gt.getClassId(), job.getId(), 0 };
				PtUserDeptJob userdeptjob=userDeptJobService.getByProerties(propName, propValue);
				if(userdeptjob!=null){
					userdeptjob.setIsDelete(1);
					userdeptjob.setUpdateTime(new Date());
					userdeptjob.setUpdateUser(currentUser.getId());
					userDeptJobService.merge(userdeptjob);
				}			
			}
			
			
			String teacherId = gt.getTeacherId(); // 教师ID
			// 根据老师是否还担任其它班级的班主任确定是否取消其班主任岗位、角色
			String where = " o.isDelete=0 ";
			Boolean isExit = this.IsFieldExist("teacherId", teacherId, "-1", where);
			if (!isExit) {
				// 移除班主任角色
				PtUser user = userService.get(teacherId);
				Set<PtRole> theUserRole = user.getSysRoles();
				PtRole role = roleService.getByProerties(new String[]{"roleCode","isDelete"},new Object[]{ "CLASSLEADER",0});
				if (role!=null) {
					theUserRole.remove(role);
					user.setSysRoles(theUserRole);
				}

				user.setUpdateTime(new Date());
				user.setUpdateUser(currentUser.getId());
				userService.merge(user);
			}		
			
			//清除这个用户的部门树缓存，以至于下次读取时更新缓存
			deptRedisService.deleteDeptTreeByUsers(gt.getTeacherId());
			
		}
		
		
		result = true;
		return result;
	}

	/**
	 * 物理删除班主任信息
	 */
	@Override
	public boolean doDeleteByPK(String delIds) {
		Boolean result = false;
		String[] dels = delIds.split(",");
		List<PtClassTeacher> list = this.queryByProerties("id", dels);
		for (PtClassTeacher gt : list) {
			this.delete(gt);
			
			String[] propName = new String[] { "jobName", "isDelete" };
			Object[] propValue = new Object[] { "班主任", 0 };
			PtJob job = jobService.getByProerties(propName, propValue);
			if(job!=null){
				propName = new String[] { "userId", "deptId", "jobId", "isDelete" };
				propValue = new Object[] { gt.getTeacherId(), gt.getClassId(), job.getId(), 0 };
				PtUserDeptJob userdeptjob=userDeptJobService.getByProerties(propName, propValue);
				if(userdeptjob!=null)
					userDeptJobService.delete(userdeptjob);
			}
			
			String teacherId = gt.getTeacherId(); // 教师ID
			// 根据老师是否还担任其它班级的班主任确定是否取消其班主任岗位、角色
			String where = " o.isDelete=0 ";
			Boolean isExit = this.IsFieldExist("teacherId", teacherId, "-1", where);
			if (!isExit) {
				// 移除班主任角色
				PtUser user = userService.get(teacherId);
				Set<PtRole> theUserRole = user.getSysRoles();
				PtRole role = roleService.getByProerties(new String[]{"roleCode","isDelete"},new Object[]{ "CLASSLEADER",0});
				if (role!=null) {
					theUserRole.remove(role);
					user.setSysRoles(theUserRole);
				}
				
				userService.merge(user);
			}

			//清除这个用户的部门树缓存，以至于下次读取时更新缓存
			deptRedisService.deleteDeptTreeByUsers(gt.getTeacherId());
			
		}
		result = true;
		return result;
	}	


}