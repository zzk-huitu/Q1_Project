package com.yc.q1.service.base.pt.basic.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.basic.PtBaseCourse;
import com.yc.q1.service.base.pt.basic.PtBaseCourseService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: JwTBasecourseServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 基础课程信息实体Service接口实现类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtBaseCourseServiceImpl extends BaseServiceImpl<PtBaseCourse> implements PtBaseCourseService {

	@Resource(name = "BaseCourseDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtBaseCourse> dao) {
		super.setDao(dao);
	}

}