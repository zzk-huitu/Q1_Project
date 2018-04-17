package com.yc.q1.service.base.pt.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.basic.PtClassStudent;
import com.yc.q1.model.base.pt.system.PtDepartment;
import com.yc.q1.model.base.pt.system.PtDeptJob;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserDeptJob;
import com.yc.q1.service.base.pt.basic.PtClassStudentService;
import com.yc.q1.service.base.pt.system.PtDepartmentService;
import com.yc.q1.service.base.pt.system.PtDeptJobService;
import com.yc.q1.service.base.pt.system.PtUserDeptJobService;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.yc.q1.service.base.redis.DeptRedisService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BaseUserdeptjobServiceImpl Function: ADD FUNCTION. Reason: ADD
 * REASON(可选). Description: 用户部门岗位(BASE_T_USERDEPTJOB)实体Service接口实现类. date:
 * 2017-03-27
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtUserDeptJobServiceImpl extends BaseServiceImpl<PtUserDeptJob> implements PtUserDeptJobService {

	@Resource(name = "PtUserDeptJobDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtUserDeptJob> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	@Resource
	private DeptRedisService deptRedisService;

	@Resource
	private PtUserService userService;

	@Resource
	private PtDeptJobService baseDeptjobService;
	
	@Resource
	private PtDepartmentService orgService;
	
	@Resource
	private PtClassStudentService classstudentService;
	
	private static Logger logger = Logger.getLogger(PtUserDeptJobServiceImpl.class);

	@Override
	public List<PtUserDeptJob> getUserDeptJobList(PtUser currentUser) {
		Map<String, String> sortedCondition = new HashMap<>();
		sortedCondition.put("isMainDept", "desc");
		sortedCondition.put("orderIndex", "asc");
		String[] propName = { "userId", "isDelete" };
		Object[] propValue = { currentUser.getId(), 0 };

		List<PtUserDeptJob> list = this.queryByProerties(propName, propValue, sortedCondition);

		return list;
	}

	@Override
	public Map<String, PtUserDeptJob> getUserDeptJobMaps(PtUser currentUser) {
		Map<String, PtUserDeptJob> maps = new HashMap<>();
		List<PtUserDeptJob> list = this.getUserDeptJobList(currentUser);
		for (PtUserDeptJob job : list) {
			maps.put(job.getDeptJobId(), job);
		}
		return maps;
	}

	@Override
	public PtUserDeptJob getUserMasterDeptJob(PtUser currentUser) {
		String[] propName = { "userId", "isMainDept", "isDelete" };
		Object[] propValue = { currentUser.getId(), true, 0 };

		PtUserDeptJob isMasterDeptJob = this.getByProerties(propName, propValue);
		return isMasterDeptJob;

	}

	// @Override
	// public QueryResult<BaseUserdeptjob> list(Integer start, Integer limit,
	// String sort, String filter, Boolean isDelete) {
	// QueryResult<BaseUserdeptjob> qResult = this.queryPageResult(start,
	// limit, sort, filter, isDelete);
	// return qResult;
	// }
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
	public Boolean doLogicDeleteByIds(String ids, PtUser currentUser) {
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

	@Override
	public boolean doAddUserToDeptJob(String deptJobId, String userId, PtUser currentUser) {
		Boolean reResult = false;
		String[] userIds = userId.split(",");

		// 所有要设置的用户
		List<PtUser> users = userService.queryByProerties("id", userIds);

		String hql = " select a from PtDeptJob a  where a.id in ('" + deptJobId.replace(",", "','")
				+ "') order by a.orderIndex asc ";
		List<PtDeptJob> deptjobs = baseDeptjobService.queryByHql(hql);

		for (PtUser user : users) {
			// 查询当前用户已有的部门岗位
			Map<String, PtUserDeptJob> userHasJobMap = this.getUserDeptJobMaps(user);
			PtUserDeptJob isMasterDeptJob = this.getUserMasterDeptJob(user);
			for (int i = 0; i < deptjobs.size(); i++) {
				PtDeptJob deptjob=deptjobs.get(i);
				String id = deptjob.getId(); // 选择的部门岗位Id
				if (userHasJobMap.get(id) == null) {
					// 如果当用户还没有设置的此部门岗位
					PtUserDeptJob userDeptJob = new PtUserDeptJob();
					userDeptJob.setId(keyRedisService.getId(PtUserDeptJob.ModuleType));
					userDeptJob.setDeptId(deptjob.getDeptId());
					userDeptJob.setJobId(deptjob.getJobId());
					userDeptJob.setDeptJobId(id);
					userDeptJob.setUserId(user.getId());
					userDeptJob.setCreateTime(new Date());
					userDeptJob.setCreateUser(currentUser.getId());
					// 当前人没有主工作部门时将一个岗位设置为主部门
					if (!ModelUtil.isNotNull(isMasterDeptJob) && i == 0) {
						userDeptJob.setIsMainDept(true);
											
						//--------判断是否要更新班级学生表(2018-3-15加入)-----------						
						//是否为学生
						if(user.getCategory().equals("2")){							
							PtDepartment dept=orgService.get(deptjob.getDeptId());	
							//是否为班级部门、学生岗位
							if(dept.getDeptType().equals("05")&&deptjob.getJobName().equals("学生")){
								
								PtClassStudent classStudent=classstudentService.getByProerties(
										new String[]{"studentId","isDelete"}, 		//新版本暂不根据学年学期来查
										new Object[]{user.getId(),0});
								if(classStudent==null){
									classStudent=new PtClassStudent();
									classStudent.setClassId(deptjob.getDeptId());
									classStudent.setId(keyRedisService.getId(PtClassStudent.ModuleType));
									classStudent.setStudentId(user.getId());
									classStudent.setCreateUser(currentUser.getId());
									classStudent.setSemester(currentUser.getSemester());
									classStudent.setStudyYear(String.valueOf(currentUser.getStudyYear()));
								}else{
									classStudent.setSemester(currentUser.getSemester());
									classStudent.setStudyYear(String.valueOf(currentUser.getStudyYear()));
									classStudent.setClassId(deptjob.getDeptId());
									classStudent.setUpdateUser(currentUser.getId());
									classStudent.setUpdateTime(new Date());
								}
								classstudentService.merge(classStudent);			
							}
						}
						//-----------------结束--------------------
						
						
					} else
						userDeptJob.setIsMainDept(false);

					this.merge(userDeptJob);
				}
			}
			// 将老师从临时部门删除
			user.setDeptId("");
			user.setUpdateTime(new Date());
			user.setUpdateUser(currentUser.getId());
			userService.merge(user);
		}

		// 清除这个用户的部门树缓存，以至于下次读取时更新缓存
		deptRedisService.deleteDeptTreeByUsers(userIds);
		
		return true;

	}

	@Override
	public boolean doRemoveUserFromDeptJob(String delIds, PtUser currentUser) {
		String[] uuids = delIds.split(",");
		
		// 所有要设置的用户	
		List<PtUserDeptJob> baseUserdeptjobs = this.queryByProerties("id", uuids);	
		List<String> userIds = baseUserdeptjobs.stream().map((x)->x.getUserId()).distinct().collect(Collectors.toList());		
		// 清除这个用户的部门树缓存，以至于下次读取时更新缓存
		if(userIds.size()>0)
			deptRedisService.deleteDeptTreeByUsers(userIds.toArray());
		
		
		/*---------判断是否要更新班级学生表(2018-3-15加入)-----------*/
		for(int i=0;i<baseUserdeptjobs.size();i++){
			PtUserDeptJob userdeptjob=baseUserdeptjobs.get(i);
			//若为主部门、班级部门、学生岗位，就执行更新操作
			if(userdeptjob.getIsMainDept()==true&&userdeptjob.getDeptType().equals("05")
					&&userdeptjob.getJobName().equals("学生")){
				
				PtUser user=userService.get(userdeptjob.getUserId());
				//是否为学生
				if(user!=null&&user.getCategory().equals("2")){
					//将JwClassstudent设置为isDelete
					String hql="update PtClassStudent set isDelete=1 where isDelete=0 "
							+ "	and studentId='"+user.getId()+"' and classId='"+userdeptjob.getDeptId()+"'";
					classstudentService.doExecuteCountByHql(hql);
				}
			}
		}
		/*-------------------结束--------------------*/
		
		
		return this.doLogicDeleteByIds(delIds, currentUser);
	}


	@Override
	public boolean doSetMasterDeptJob(String delIds, String userId, PtUser currentUser) {

		// 先将原来的主部门岗位设置成非主部门岗位
		PtUser user = userService.get(userId);
		PtUserDeptJob oldMaster = this.getUserMasterDeptJob(user);
		if (ModelUtil.isNotNull(oldMaster)) {
			oldMaster.setIsMainDept(false);
			oldMaster.setUpdateTime(new Date());
			oldMaster.setUpdateUser(currentUser.getId());
			this.merge(oldMaster);
		}
		
		// 将新的部门岗位设置为主部门岗位
		String[] propertyName = { "isMainDept", "updateTime", "updateUser" };
		Object[] propertyValue = { true, new Date(), currentUser.getId() };
		this.updateByProperties("id", delIds, propertyName, propertyValue);
		
		
		//--------判断是否要更新班级学生表(2018-3-15加入)-----------
		//是否为学生
		if(user.getCategory().equals("2")){
			//是否为学生的班级学生岗位
			if(oldMaster.getDeptType().equals("05")&&oldMaster.getJobName().equals("学生")){
				//将JwClassstudent设置为isDelete
				String hql="update PtClassStudent set isDelete=1 where isDelete=0 "
						+ "	and studentId='"+userId+"' and classId='"+oldMaster.getDeptId()+"'";
				classstudentService.doExecuteCountByHql(hql);
			}
			
			//判断新的部门岗位是否为班级学生岗位
			PtUserDeptJob	newMaster=this.get(delIds);
			if(newMaster.getDeptType().equals("05")&&newMaster.getJobName().equals("学生")){
				PtClassStudent classStudent=classstudentService.getByProerties(
						new String[]{"studentId","isDelete"}, 		//新版本暂不根据学年学期来查
						new Object[]{userId,0});
				if(classStudent==null){
					classStudent=new PtClassStudent();
					classStudent.setClassId(newMaster.getDeptId());
					classStudent.setStudentId(userId);
					classStudent.setCreateUser(currentUser.getId());
					classStudent.setSemester(currentUser.getSemester());
					classStudent.setStudyYear(String.valueOf(currentUser.getStudyYear()));
					classStudent.setId(keyRedisService.getId(PtClassStudent.ModuleType));
				}else{
					classStudent.setSemester(currentUser.getSemester());
					classStudent.setStudyYear(String.valueOf(currentUser.getStudyYear()));
					classStudent.setClassId(newMaster.getDeptId());
					classStudent.setUpdateUser(currentUser.getId());
					classStudent.setUpdateTime(new Date());
				}
				classstudentService.merge(classStudent);			
			}
		}
		//-----------------结束--------------------
		
		return true;
	}

	/**
	 * 获取部门岗位的用户信息 zzk
	 */
	@Override
	public QueryResult<PtUserDeptJob> getUserByDeptJobId(String deptJobId, Integer start, Integer limit,
			String sort) {
		// TODO Auto-generated method stub

		String hql = "from PtUserDeptJob o where o.deptJobId='" + deptJobId + "' and o.isDelete=0 ";

		if (StringUtils.isNotEmpty(sort)) {
			hql += " order by ";
			hql += sort;
		}

		QueryResult<PtUserDeptJob> qr = this.queryResult(hql, start, limit);

		return qr;

	}

	@Override
	public boolean doSetMasterDeptJobFromUser(String userIds, String deptJobId, PtUser currentUser) {

		// 先将原来的主部门岗位设置成非主部门岗位
		Object[] userArray = userIds.split(",");
		String[] conditionName = { "isMainDept", "userId" };
		Object[] conditionValue = { true, userArray };
		String[] propertyName = { "isMainDept", "updateTime", "updateUser" };
		Object[] propertyValue = { false, new Date(), currentUser.getId() };
		this.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);

		// 将新的部门岗位设置为主部门岗位
		String[] conditionName2 = { "deptJobId", "userId" };
		Object[] conditionValue2 = { deptJobId, userArray };
		String[] propertyName2 = { "isMainDept", "updateTime", "updateUser" };
		Object[] propertyValue2 = { true, new Date(), currentUser.getId() };
		this.updateByProperties(conditionName2, conditionValue2, propertyName2, propertyValue2);
		return true;
	}

	

}