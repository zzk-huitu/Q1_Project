package com.yc.q1.service.base.pt.system.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.system.PtSysParameter;
import com.yc.q1.pojo.base.version.VersionInfo;
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
	
	@Override
	public boolean doUpdateVerInfo(VersionInfo entity, String userCh) {
		PtSysParameter mainLogoEntity = this.get(entity.getMainLogoId());
		mainLogoEntity.setSysParamValue(entity.getMainLogo());
		this.persist(mainLogoEntity);
		
		PtSysParameter smallLogoEntity = this.get(entity.getSmallLogoId());
		smallLogoEntity.setSysParamValue(entity.getSmallLogo());
		this.persist(smallLogoEntity);
		
		PtSysParameter schoolLogoEntity = this.get(entity.getSchoolLogoId());
		schoolLogoEntity.setSysParamValue(entity.getSchoolLogo());
		this.persist(schoolLogoEntity);
		
		PtSysParameter clientEntity = this.get(entity.getClientNameId());
		clientEntity.setSysParamValue(entity.getClientName());
		this.persist(clientEntity);
		
		PtSysParameter serviceEntity = this.get(entity.getServiceNameId());
		serviceEntity.setSysParamValue(entity.getServiceName());
		this.persist(serviceEntity);
		
		PtSysParameter versionEntity = this.get(entity.getVarsionNameId());
		versionEntity.setSysParamValue(entity.getVarsionName());
		this.persist(versionEntity);
		
		return true;
	}

}