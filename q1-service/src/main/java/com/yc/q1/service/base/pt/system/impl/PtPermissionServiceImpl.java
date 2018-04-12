package com.yc.q1.service.base.pt.system.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.system.PtPermission;
import com.yc.q1.service.base.pt.system.PtPermissionService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: BaseTPerimissonServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 权限表实体Service接口实现类.
 * date: 2016-07-17
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtPermissionServiceImpl extends BaseServiceImpl<PtPermission> implements PtPermissionService{

	@Resource(name = "PtPermissionDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtPermission> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
}