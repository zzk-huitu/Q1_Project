	
package com.yc.q1.base.pt.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.system.dao.MenuPermissionDao;
import com.yc.q1.base.pt.system.model.MenuPermission;
import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.system.service.SysMenuPermissionService;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.StringUtils;

@Service
@Transactional
public class SysMenuPermissionServiceImpl extends BaseServiceImpl<MenuPermission> implements SysMenuPermissionService{

    @Resource
    public void setSysMenuPermissionDao(MenuPermissionDao dao) {
        this.dao = dao;
    }

	@Override
	public List<MenuPermission> getRoleMenuPerlist(String roleId, String perId) {
		List<MenuPermission> returnList=null;
		//查询此菜单的功能权限	
		String hql = "select menuPermissionId from RoleMenuPermission a where a.isDelete=0 ";
		if(StringUtils.isNotEmpty(roleId)){
			hql+=" and a.roleId='"+roleId+"'";		
		}
		if(StringUtils.isNotEmpty(perId)){
			hql+=" and a.permissionId='"+perId+"'";				
		}
		List<String> menuPerIds = this.queryEntityByHql(hql);
		if(menuPerIds.size()>0){
			hql = "from MenuPermission s where s.id in (:ids) and s.isDelete=0";          
        	returnList = this.queryByHql(hql.toString(), 0, -1, "ids", menuPerIds.toArray());// 执行查询方法
		}else{
			returnList=new ArrayList<>();
		}
        return returnList;
	}

	@Override
	public MenuPermission doAddEntity(MenuPermission entity, User currentUser) {
		// TODO Auto-generated method stub	
		MenuPermission saveEntity = new MenuPermission();
		try {
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("id");
			BeanUtils.copyProperties(saveEntity,entity,excludedProp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 生成默认的orderindex
		// 如果界面有了排序号的输入，则不需要取默认的了
		Integer orderIndex = this.getDefaultOrderIndex(saveEntity);
		saveEntity.setOrderIndex(orderIndex);// 排序

		// 增加时要设置创建人
		saveEntity.setCreateUser(currentUser.getId()); // 创建人
		
		// 持久化到数据库
		entity = this.merge(saveEntity);
		
		return entity;
	}
	
	@Override
	public MenuPermission doUpdateEntity(MenuPermission entity, User currentUser) {
		// TODO Auto-generated method stub	
		// 先拿到已持久化的实体	
		MenuPermission perEntity = this.get(entity.getId());
	
		try {
			// 将entity中不为空的字段动态加入到perEntity中去。
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
		entity = this.merge(perEntity);// 执行修改方法

		return entity;
	}

}