package com.yc.q1.service.base.pt.basic.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.basic.PtStudentParents;
import com.yc.q1.service.base.pt.basic.PtStudentParentsService;

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
public class PtStudentParentsServiceImpl extends BaseServiceImpl<PtStudentParents> implements PtStudentParentsService{

	@Resource(name = "ptStudentParentsDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtStudentParents> dao) {
		super.setDao(dao);
	}

}