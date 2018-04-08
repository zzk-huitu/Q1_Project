package com.zd.school.jw.push.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.StringUtils;
import com.zd.school.jw.push.dao.PushInfoDao;
import com.zd.school.jw.push.model.PushInfo;
import com.zd.school.jw.push.service.PushInfoService;
import com.zd.school.plartform.system.model.User;

@Service
@Transactional
public class PushInfoServiceImpl extends BaseServiceImpl<PushInfo> implements PushInfoService {

    @Resource
    public void setPushInfoDao(PushInfoDao dao) {
        this.dao = dao;
    }

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
		this.persist(pushInfo);
		br = true;
		return br;
	}

}
