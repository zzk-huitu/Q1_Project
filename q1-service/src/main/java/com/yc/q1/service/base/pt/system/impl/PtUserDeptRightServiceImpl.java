package com.yc.q1.service.base.pt.system.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserDeptRight;
import com.yc.q1.service.base.pt.system.PtUserDeptRightService;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.yc.q1.service.base.redis.DeptRedisService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: SysDeptrightServiceImpl Function: ADD FUNCTION. Reason: ADD
 * REASON(可选). Description: 用户权限部门(SYS_T_DEPTRIGHT)实体Service接口实现类. date:
 * 2017-04-06
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtUserDeptRightServiceImpl extends BaseServiceImpl<PtUserDeptRight> implements PtUserDeptRightService {
	
	//自动注入dao到service层中，并设置到dao变量中
	@Resource(name = "PtUserDeptRightDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtUserDeptRight> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	@Resource
	private DeptRedisService deptRedisService;
	
	@Resource
	private PtUserService userService;
	
	@Override
	public Boolean doUserRightDept(String userId, String deptId, PtUser currentUser) {
		Date date=new Date();
		String[] userIds = userId.split(",");
		String[] deptIds = deptId.split(",");
		String[] propertyName = { "updateUser", "updateTime", "rightType" };
		Object[] propertyValue = { currentUser.getId(), date, 1 };
		
		String hql="select deptId from UserDeptRight where isDelete=0 and userId=?";
		PtUserDeptRight deptright = null;
		for (String ui : userIds) {
			//一次性查询出这个用户的所有部门权限，判断是否要入库		
			List<Object> deptIdList = this.queryEntityByHql(hql, ui);		
			for (String di : deptIds) {
				if(!deptIdList.contains(di)){			
					deptright = new PtUserDeptRight();
					deptright.setId(keyRedisService.getId(PtUserDeptRight.ModuleType));
					deptright.setUserId(ui);
					deptright.setDeptId(di);
					//deptright.setRightSource(1);取消了此字段
					deptright.setCreateTime(date);
					deptright.setCreateUser(currentUser.getId());
					this.merge(deptright);
				}
			}
		}
		
		// 清除这个用户的部门树缓存，以至于下次读取时更新缓存
		deptRedisService.deleteDeptTreeByUsers(userIds);
				
		// 更新指定的用户信息
		userService.updateByProperties("id", userIds, propertyName, propertyValue);
		return true;
	}

	@Override
	public boolean doDelete(String delIds) {
		// TODO Auto-generated method stub
		String doIds = "'" + delIds.replace(",", "','") + "'";
		
		// 所有要设置的用户	
		String hql="select userId from UserDeptRight where id in ("+doIds+")";
		
		List<String> userIds = this.queryEntityByHql(hql);
		// 清除这个用户的部门树缓存，以至于下次读取时更新缓存
		if(userIds.size()>0){
			userIds.stream().distinct().collect(Collectors.toList());
			deptRedisService.deleteDeptTreeByUsers(userIds.toArray());
		}
			
		hql="Delete from UserDeptRight where id in ("+doIds+")";
			
	    return this.doExecuteCountByHql(hql)>0;
	}
	
	@Override
	public void doUpdateRightType(String uuid, String rightType,String userId) {
		// TODO Auto-generated method stub
		deptRedisService.deleteDeptTreeByUsers(uuid);
		
		String[] propertyName = { "updateUser", "updateTime", "rightType" };
		Object[] propertyValue = { userId, new Date(), Integer.valueOf(rightType) };	
		// 更新指定的用户信息
		userService.updateByProperties("id", uuid, propertyName, propertyValue);
	}
	
	


}