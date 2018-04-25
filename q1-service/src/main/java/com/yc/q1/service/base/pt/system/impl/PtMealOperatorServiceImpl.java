package com.yc.q1.service.base.pt.system.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.system.PtMealOperator;
import com.yc.q1.service.base.pt.system.PtMealOperatorService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BizTJobServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 岗位信息实体Service接口实现类.
 * date: 2016-05-16
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
//@Transactional
public class PtMealOperatorServiceImpl extends BaseServiceImpl<PtMealOperator> implements PtMealOperatorService{

	@Resource(name = "PtMealOperatorDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtMealOperator> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;

}