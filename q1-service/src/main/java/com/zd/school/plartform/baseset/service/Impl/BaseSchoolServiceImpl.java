package com.zd.school.plartform.baseset.service.Impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.school.plartform.baseset.dao.BaseSchoolDao ;
import com.zd.school.plartform.baseset.model.Department;
import com.zd.school.plartform.baseset.model.School;
import com.zd.school.plartform.baseset.service.BaseSchoolService ;
import com.zd.school.plartform.system.service.SysOrgService;

/**
 * 
 * ClassName: BaseSchoolServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 学校信息实体Service接口实现类.
 * date: 2016-08-13
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class BaseSchoolServiceImpl extends BaseServiceImpl<School> implements BaseSchoolService{

    @Resource
    public void setBaseSchoolDao(SchoolDao dao) {
        this.dao = dao;
    }
    @Resource
    private SysOrgService sysOrgService;

	@Override
	public School doUpdate(School entity, String xm) {
		// TODO Auto-generated method stub	
		
		School saveEntity = this.get(entity.getId());
		String oldSchoolName=saveEntity.getSchoolName();
		try {
			BeanUtils.copyPropertiesExceptNull(saveEntity, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		saveEntity.setUpdateTime( new Date());
		saveEntity.setUpdateUser( xm);
		entity = this.merge(saveEntity);// 执行修改方法
		
		if(!oldSchoolName.equals(entity.getSchoolName())){
			//再更新使用到的名称
			String updateHql1="update RoomArea a set a.nodeText='"+entity.getSchoolName()+"' where a.id='"+entity.getId()+"'";
			String updateHql2="update Department a set a.nodeText='"+entity.getSchoolName()+"' where a.id='"+entity.getId()+"'";	
			this.doExecuteCountByHql(updateHql1);
			this.doExecuteCountByHql(updateHql2);
			
			sysOrgService.setDeptName(entity.getSchoolName(), entity.getId());	
			
			Department deptOrg = sysOrgService.get(entity.getId());
			Department parentOrg = sysOrgService.get(deptOrg.getParentNode());
			if(parentOrg!=null&&!deptOrg.getParentNode().equals("ROOT"))
				sysOrgService.setChildAllDeptName(deptOrg, parentOrg.getAllDeptName());
			else
				sysOrgService.setChildAllDeptName(deptOrg, "ROOT");	
		}
		return entity;
	}

}