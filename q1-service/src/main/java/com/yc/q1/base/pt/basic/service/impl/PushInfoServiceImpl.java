package com.yc.q1.base.pt.basic.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.service.PushInfoService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.basic.PushInfo;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.StringUtils;

@Service
@Transactional
public class PushInfoServiceImpl extends BaseServiceImpl<PushInfo> implements PushInfoService {

	@Resource(name = "PushInfoDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PushInfo> dao) {
		super.setDao(dao);
	}
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

    @Override
	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus,User currentUser) {
		return this.pushInfo(empName, empNo, eventType, regStatus,null, currentUser);
	}

	@Override
	public boolean pushInfo(String empName, String empNo, String eventType, String regStatus, String pushUrl,User currentUser) {
		Boolean br = false;
		PushInfo pushInfo = new PushInfo();
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
		
		pushInfo.setId(keyRedisService.getId(PushInfo.ModuleType));	//手动设置id
		this.persist(pushInfo);
		br = true;
		return br;
	}

}
