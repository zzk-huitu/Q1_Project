package com.yc.q1.base.pt.basic.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.dao.StudentParentsDao;
import com.yc.q1.base.pt.basic.model.StudentParents;
import com.yc.q1.base.pt.basic.service.StudentParentsService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: StuParentsServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 学生家长信息实体Service接口实现类.
 * date: 2016-08-05
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class StudentParentsServiceImpl extends BaseServiceImpl<StudentParents> implements StudentParentsService{

	@Resource(name = "StudentParentsDao") // 将具体的dao注入进来
	public void setDao(BaseDao<StudentParents> dao) {
		super.setDao(dao);
	}

}