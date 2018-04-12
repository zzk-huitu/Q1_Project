package com.yc.q1.service.base.redis;

import java.util.List;

import com.yc.q1.pojo.base.pt.CommTree;
import com.yc.q1.pojo.base.pt.DepartmentTree;

public interface DeptRedisService {
	
	public Object getRightDeptTreeByUser(String userId);
	public Object getRightDeptClassTreeByUser(String userId);
	public Object getRightDeptDisciplineTreeByUser(String userId);
	
	
	public void setRightDeptTreeByUser(String userId,List<DepartmentTree> values);	
	public void setRightDeptClassTreeByUser(String userId,List<CommTree> values);
	public void setRightDeptDisciplineTreeByUser(String userId,List<CommTree> values);
	
	
	/**
	 * 清空部门相关的缓存
	 */
	public void deleteDeptTreeAll();
	
	/**
	 * 删除用户的权限相关的部门信息
	 * @param userIds	用户id
	 */
	public void deleteDeptTreeByUsers(Object... userIds);
	
	
	
}
