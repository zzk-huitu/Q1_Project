package com.yc.q1.service.base.pt.system.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.model.base.pt.system.PtJob;
import com.yc.q1.model.base.pt.system.PtSysParameter;
import com.yc.q1.service.base.pt.system.PtJobService;
import com.yc.q1.service.base.pt.system.PtSysParameterService;
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
public class PtSysParameterServiceImpl extends BaseServiceImpl<PtSysParameter> implements PtSysParameterService{

	@Resource(name = "PtSysParameterDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtSysParameter> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;

}