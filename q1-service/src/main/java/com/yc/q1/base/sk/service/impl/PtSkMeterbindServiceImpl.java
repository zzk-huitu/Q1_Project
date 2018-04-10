package com.yc.q1.base.sk.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.sk.dao.SkMeterBindDao;
import com.yc.q1.base.sk.model.SkMeterBind;
import com.yc.q1.base.sk.service.PtSkMeterbindService;
import com.zd.core.service.BaseServiceImpl;
@Service
@Transactional
public class PtSkMeterbindServiceImpl  extends BaseServiceImpl<SkMeterBind> implements PtSkMeterbindService{
	
	@Resource
    public void setPtSkMeterbindDao(SkMeterBindDao dao) {
        this.dao = dao;
    }

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
