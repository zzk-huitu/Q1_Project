
package com.zd.school.plartform.system.service.Impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.StringUtils;
import com.zd.school.plartform.system.dao.SysRoleMenuPermissionDao;
import com.zd.school.plartform.system.model.Permission;
import com.zd.school.plartform.system.model.Role;
import com.zd.school.plartform.system.model.RoleMenuPermission;
import com.zd.school.plartform.system.model.User;
import com.zd.school.plartform.system.service.SysPerimissonService;
import com.zd.school.plartform.system.service.SysRoleMenuPermissionService;
import com.zd.school.plartform.system.service.SysRoleService;
import com.zd.school.plartform.system.service.SysUserService;
import com.zd.school.redis.service.UserRedisService;

@Service
@Transactional
public class SysRoleMenuPermissionServiceImpl extends BaseServiceImpl<RoleMenuPermission>
		implements SysRoleMenuPermissionService {
	
	@Resource  
	private UserRedisService userRedisService;
	
	@Resource
	public void setSysRoleMenuPermissionDao(SysRoleMenuPermissionDao dao) {
		this.dao = dao;
	}

	@Resource
	private SysRoleService sysRoleService;
	
	@Resource
    private SysUserService userSerive;

	@Resource
	private SysPerimissonService perimissonSevice;
	
	/**
	 * 设置角色菜单功能权限，并清除相关角色的用户的redis数据，防止用户请求服务时，采用未更新的权限列表。
	 */
	@Override
	public boolean doSetRoleMenuPermission(String roleId, String perId, String roleMenuPers) {
		Boolean doResult = false;
		
		// 查询此角色菜单中已有的功能权限
		String hql = "select menuPermissionId from RoleMenuPermission a where a.roleId=? and a.permissionId=? and a.isDelete=0";
		List<String> currentRoleMenuPerIds = this.queryEntityByHql(hql, roleId, perId);
		
		boolean isUpdate=false;	//若更新了功能权限，则执行删除redis的功能。
		if(StringUtils.isNotEmpty(roleMenuPers)){
			String[] roleMenuPerIds = roleMenuPers.split(",");
	
			// 要增加权限菜单的角色及已有权限菜单信息
			Role addRoleEntity = sysRoleService.get(roleId);
			Permission sysPermission = perimissonSevice.get(perId);
			Set<Permission> rolePermission = addRoleEntity.getSysPermissions();
	
			// 判断此角色是否拥有此菜单权限
			if (!rolePermission.contains(sysPermission)) {
				return false;
			}
		
			for (int i = 0; i < roleMenuPerIds.length; i++) {
				String menuPerId = roleMenuPerIds[i];
				if (currentRoleMenuPerIds.contains(menuPerId))
					continue;
				RoleMenuPermission rmp = new RoleMenuPermission();
				rmp.setRoleId(roleId); // 角色id
				rmp.setPermissionId(perId); // 角色菜单id
				rmp.setMenuPermissionId(menuPerId); // 菜单功能id			
				this.merge(rmp);
				isUpdate=true;
			}
			
			if(currentRoleMenuPerIds.size()>0){
				// 删除角色菜单功能不属于roleMenuPerIds中的功能
				String deleteHql = "delete from RoleMenuPermission a where a.roleId='" + roleId + "' and a.permissionId='" + perId
						+ "' and a.menuPermissionId not in ('" + roleMenuPers.replace(",", "','") + "')";
				int row=this.doExecuteCountByHql(deleteHql);
				if(row>0)
					isUpdate=true;
			}
			
		}else{
			if(currentRoleMenuPerIds.size()>0){
				// 删除角色菜单所有功能
				String deleteHql = "delete from RoleMenuPermission a where a.roleId='" + roleId + "' and a.permissionId='" + perId + "'";
				this.doExecuteCountByHql(deleteHql);
				isUpdate=true;
			}
		}
		
		//删除此role的相关用户的redis功能权限数据
		List<User> roleUsers = userSerive.getUserByRoleId(roleId).getResultList();
		Object[] userIds=new String[roleUsers.size()];
		for(int i=0;i<roleUsers.size();i++){
			userIds[i]=roleUsers.get(i).getId();			
		}		
		if(userIds.length>0){
			//若更新了功能权限，就删除它们
			if(isUpdate==true){
				userRedisService.deleteAuthByUser(userIds);
				userRedisService.deleteBtnByUser(userIds);
			}		
		}
	
		doResult = true;

		return doResult;
	}

	@Override
	public void removeRoleMenuPermission(String roleId, List<Permission> cancelPerimission) {
		int row=0;
		for(int i=0;i< cancelPerimission.size();i++){
			// 删除角色菜单所有功能
			String deleteHql = "delete from RoleMenuPermission a where a.roleId='" + roleId + "' and a.permissionId='" + cancelPerimission.get(i).getId() + "'";
			row+=this.doExecuteCountByHql(deleteHql);
		}
		
		
		//删除此role的相关用户的redis功能权限数据				
		List<User> roleUsers = userSerive.getUserByRoleId(roleId).getResultList();
		Object[] userIds=new String[roleUsers.size()];
		for(int i=0;i<roleUsers.size();i++){
			userIds[i]=roleUsers.get(i).getId();			
		}		
		if(userIds.length>0){
			if(row>0){
				//若更新了功能权限，就删除它们
				userRedisService.deleteAuthByUser(userIds);
				userRedisService.deleteBtnByUser(userIds);
			}
		}
		
	
	}
	

}