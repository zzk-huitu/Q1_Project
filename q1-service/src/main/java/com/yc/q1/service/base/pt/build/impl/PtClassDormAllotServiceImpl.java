package com.yc.q1.service.base.pt.build.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.build.PtClassDormAllot;
import com.yc.q1.service.base.pt.build.PtClassDormAllotService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: JwClassdormServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班级宿舍(JW_T_CLASSDORM)实体Service接口实现类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtClassDormAllotServiceImpl extends BaseServiceImpl<PtClassDormAllot> implements PtClassDormAllotService{

	@Resource(name = "PtClassDormAllotDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtClassDormAllot> dao) {
		super.setDao(dao);
	}

}