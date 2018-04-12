package com.yc.q1.service.base.sk.impl;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.device.PtRoomBagRuleBind;
import com.yc.q1.model.base.sk.SkMeterBind;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.base.sk.SkMeterBindService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
@Service
@Transactional
public class SkMeterBindServiceImpl  extends BaseServiceImpl<SkMeterBind> implements SkMeterBindService{
	
	@Resource(name="SkMeterBindDao")	//将具体的dao注入进来
	public void setDao(BaseDao<SkMeterBind> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	@Override
	public void doMeterBind(String[] termId, String[] termSn, String meterId, String xm) {
		Date date=new Date();
		SkMeterBind perEntity = null;
		for (int i = 0; i < termId.length; i++) {
			perEntity = this.getByProerties("termId",termId[i]);
			if (perEntity != null) {
				perEntity.setMeterId(meterId);
				perEntity.setUpdateTime(new Date());
				perEntity.setUpdateUser(xm);
				this.merge(perEntity);
			} else {
				perEntity = new SkMeterBind();
				perEntity.setId(keyRedisService.getId(SkMeterBind.ModuleType));
				perEntity.setMeterId(meterId);
				perEntity.setTermId(termId[i]);
				perEntity.setTermSn(termSn[i]);
				perEntity.setCreateUser(xm);
				perEntity.setCreateTime(date);
				this.merge(perEntity);
			}
		}
	}

}
