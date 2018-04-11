package com.yc.q1.base.pt.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.q1.base.pt.system.dao.JobDao;
import com.yc.q1.base.pt.system.model.Job;
import com.yc.q1.base.pt.system.service.JobService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: BizTJobServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 岗位信息实体Service接口实现类.
 * date: 2016-05-16
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
//@Transactional
public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService{

	@Resource(name = "JobDao") // 将具体的dao注入进来
	public void setDao(BaseDao<Job> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	@Override
	public Job doUpdate(Job entity, String xm) {
		// TODO Auto-generated method stub		
		Job saveEntity = this.get(entity.getId());
		String oldJobName=saveEntity.getJobName();
		try {
			BeanUtils.copyPropertiesExceptNull(saveEntity, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		saveEntity.setUpdateTime( new Date());
		saveEntity.setUpdateUser( xm);
		entity = this.merge(saveEntity);// 执行修改方法
		
		if(!oldJobName.equals(entity.getJobName())){
			//在更新部门岗位表的岗位名称数据
			String updateHql1="update DeptJob a set a.jobName='"+entity.getJobName()+"' where a.jobId='"+entity.getId()+"'";
			String updateHql2="update DeptJob a set a.parentJobName='"+entity.getJobName()+"' where a.parentJobId='"+entity.getId()+"'";
			this.doExecuteCountByHql(updateHql1);
			this.doExecuteCountByHql(updateHql2);
		}
		
		return entity;
		
	}

}