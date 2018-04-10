package com.yc.q1.base.pt.wisdomclass.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.system.model.DeptJob;
import com.yc.q1.base.pt.system.model.Job;
import com.yc.q1.base.pt.system.model.Role;
import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.system.model.UserDeptJob;
import com.yc.q1.base.pt.system.service.SysDeptjobService;
import com.yc.q1.base.pt.system.service.SysJobService;
import com.yc.q1.base.pt.system.service.SysOrgService;
import com.yc.q1.base.pt.system.service.SysRoleService;
import com.yc.q1.base.pt.system.service.SysUserService;
import com.yc.q1.base.pt.system.service.SysUserdeptjobService;
import com.yc.q1.base.pt.wisdomclass.dao.ClassTeacherDao;
import com.yc.q1.base.pt.wisdomclass.model.ClassTeacher;
import com.yc.q1.base.pt.wisdomclass.service.JwClassteacherService;
import com.yc.q1.base.redis.service.DeptRedisService;
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
public class JwClassteacherServiceImpl extends BaseServiceImpl<ClassTeacher> implements JwClassteacherService {

    @Resource
    public void setJwClassteacherDao(ClassTeacherDao dao) {
        this.dao = dao;
    }

    @Resource
    private SysOrgService orgService;

    @Resource
    private SysJobService jobService;

    @Resource
    private SysUserService userService;

    @Resource
    private SysRoleService roleService;

    @Resource
	private SysDeptjobService deptJobService;

	@Resource
	private SysUserdeptjobService userDeptJobService;
	
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
	public ClassTeacher doAddClassTeacher(ClassTeacher entity, User currentUser)
			throws IllegalAccessException, InvocationTargetException {

		ClassTeacher perEntity = new ClassTeacher();
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

		User user = userService.get(teacherId);
		// 设置增加默认的班主任角色
		Set<Role> theUserRole = user.getSysRoles();
		Role role = roleService.getByProerties(new String[]{"roleCode","isDelete"},new Object[]{ "CLASSLEADER",0});
		if (role!=null) {
			theUserRole.add(role);
			user.setSysRoles(theUserRole);
		}

		//设置部门岗位
		String[] propName = new String[] { "jobName", "isDelete" };
		Object[] propValue = new Object[] { "班主任", 0 };
		Job job = jobService.getByProerties(propName, propValue);
		if(job!=null){
			propName = new String[] { "jobId", "deptId", "isDelete" };
			propValue = new Object[] { job.getId(), deptId, 0 };
			DeptJob deptjob = deptJobService.getByProerties(propName, propValue);
			
			if(deptjob!=null){
				propName = new String[] { "userId", "deptjobId", "isDelete" };
				propValue = new Object[] { user.getId(), deptjob.getId(), 0 };
				UserDeptJob userdeptjob=userDeptJobService.getByProerties(propName, propValue);
				if(userdeptjob==null){
					userdeptjob = new UserDeptJob();
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
	public Boolean doDelete(String delIds, User currentUser) {
		Boolean result = false;
		String[] dels = delIds.split(",");
		List<ClassTeacher> list = this.queryByProerties("id", dels);
		for (ClassTeacher gt : list) {
			gt.setEndTime(new Date());
			gt.setIsDelete(1);
			gt.setUpdateTime(new Date());
			gt.setUpdateUser(currentUser.getId());
			this.merge(gt);
			
			String[] propName = new String[] { "jobName", "isDelete" };
			Object[] propValue = new Object[] { "班主任", 0 };
			Job job = jobService.getByProerties(propName, propValue);
			if(job!=null){
				propName = new String[] { "userId", "deptId", "jobId", "isDelete" };
				propValue = new Object[] { gt.getTeacherId(), gt.getClassId(), job.getId(), 0 };
				UserDeptJob userdeptjob=userDeptJobService.getByProerties(propName, propValue);
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
				User user = userService.get(teacherId);
				Set<Role> theUserRole = user.getSysRoles();
				Role role = roleService.getByProerties(new String[]{"roleCode","isDelete"},new Object[]{ "CLASSLEADER",0});
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
		List<ClassTeacher> list = this.queryByProerties("id", dels);
		for (ClassTeacher gt : list) {
			this.delete(gt);
			
			String[] propName = new String[] { "jobName", "isDelete" };
			Object[] propValue = new Object[] { "班主任", 0 };
			Job job = jobService.getByProerties(propName, propValue);
			if(job!=null){
				propName = new String[] { "userId", "deptId", "jobId", "isDelete" };
				propValue = new Object[] { gt.getTeacherId(), gt.getClassId(), job.getId(), 0 };
				UserDeptJob userdeptjob=userDeptJobService.getByProerties(propName, propValue);
				if(userdeptjob!=null)
					userDeptJobService.delete(userdeptjob);
			}
			
			String teacherId = gt.getTeacherId(); // 教师ID
			// 根据老师是否还担任其它班级的班主任确定是否取消其班主任岗位、角色
			String where = " o.isDelete=0 ";
			Boolean isExit = this.IsFieldExist("teacherId", teacherId, "-1", where);
			if (!isExit) {
				// 移除班主任角色
				User user = userService.get(teacherId);
				Set<Role> theUserRole = user.getSysRoles();
				Role role = roleService.getByProerties(new String[]{"roleCode","isDelete"},new Object[]{ "CLASSLEADER",0});
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