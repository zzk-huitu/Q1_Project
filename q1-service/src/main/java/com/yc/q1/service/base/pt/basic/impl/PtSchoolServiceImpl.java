package com.yc.q1.service.base.pt.basic.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.basic.PtSchool;
import com.yc.q1.model.base.pt.system.PtDepartment;
import com.yc.q1.service.base.pt.basic.PtSchoolService;
import com.yc.q1.service.base.pt.system.PtDepartmentService;

/**
 * 
 * ClassName: BaseSchoolServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 学校信息实体Service接口实现类. date: 2016-08-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtSchoolServiceImpl extends BaseServiceImpl<PtSchool> implements PtSchoolService {

	@Resource(name = "PtSchoolDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtSchool> dao) {
		super.setDao(dao);
	}

	@Resource
	private PtDepartmentService sysOrgService;

	@Override
	public PtSchool doUpdate(PtSchool entity, String xm) {
		// TODO Auto-generated method stub

		PtSchool saveEntity = this.get(entity.getId());
		String oldSchoolName = saveEntity.getSchoolName();
		try {
			BeanUtils.copyPropertiesExceptNull(saveEntity, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		saveEntity.setUpdateTime(new Date());
		saveEntity.setUpdateUser(xm);
		entity = this.merge(saveEntity);// 执行修改方法

		if (!oldSchoolName.equals(entity.getSchoolName())) {
			// 再更新使用到的名称
			String updateHql1 = "update PtRoomArea a set a.nodeText='" + entity.getSchoolName() + "' where a.id='"
					+ entity.getId() + "'";
			String updateHql2 = "update PtDepartment a set a.nodeText='" + entity.getSchoolName() + "' where a.id='"
					+ entity.getId() + "'";
			this.doExecuteCountByHql(updateHql1);
			this.doExecuteCountByHql(updateHql2);

			sysOrgService.setDeptName(entity.getSchoolName(), entity.getId());

			PtDepartment deptOrg = sysOrgService.get(entity.getId());
			PtDepartment parentOrg = sysOrgService.get(deptOrg.getParentNode());
			if (parentOrg != null && !deptOrg.getParentNode().equals("ROOT"))
				sysOrgService.setChildAllDeptName(deptOrg, parentOrg.getAllDeptName());
			else
				sysOrgService.setChildAllDeptName(deptOrg, "ROOT");
		}
		return entity;
	}

}