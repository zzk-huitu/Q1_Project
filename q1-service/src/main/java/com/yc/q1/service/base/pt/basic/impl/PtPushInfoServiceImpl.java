package com.yc.q1.service.base.pt.basic.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.basic.PtPushInfo;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.basic.PtPushInfoService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

@Service
@Transactional
public class PtPushInfoServiceImpl extends BaseServiceImpl<PtPushInfo> implements PtPushInfoService {

	@Resource(name = "PtPushInfoDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtPushInfo> dao) {
		super.setDao(dao);
	}
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

    @Override
	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus,PtUser currentUser) {
		return this.pushInfo(empName, empNo, eventType, regStatus,null, currentUser);
	}

	@Override
	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus, String pushUrl,PtUser currentUser) {
		Boolean br = false;
		PtPushInfo pushInfo = new PtPushInfo();
		pushInfo.setEmpleeName(empName);
		pushInfo.setEmpleeNo(empNo);
		pushInfo.setRegTime(new Date());
		pushInfo.setEventType(eventType);
		pushInfo.setPushStatus(0);
		pushInfo.setPushWay(1);
		pushInfo.setRegStatus(regStatus);
		pushInfo.setCreateUser(currentUser.getId());
		if (StringUtils.isEmpty(pushUrl))
			pushInfo.setPushUrl("");
		else
			pushInfo.setPushUrl(pushUrl);
		
		pushInfo.setId(keyRedisService.getId(PtPushInfo.ModuleType));	//手动设置id
		this.persist(pushInfo);
		br = true;
		return br;
	}

}
