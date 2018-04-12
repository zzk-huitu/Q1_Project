package com.yc.q1.service.base.pt.device.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.device.PtPriceBind;
import com.yc.q1.service.base.pt.device.PtPriceBindService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 水控、电控费率绑定表
 * 
 * @author hucy
 *
 */
@Service
@Transactional
public class PtPriceBindServiceImpl extends BaseServiceImpl<PtPriceBind> implements PtPriceBindService {

	@Resource(name = "ptPriceBindDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtPriceBind> dao) {
		super.setDao(dao);
	}
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;
	
	@Override
	public void doPriceBind(String[] termId, String[] termSn, String meterId, String xm) {
		// TODO Auto-generated method stub

		Date date = new Date();
		PtPriceBind perEntity = null;
		for (int i = 0; i < termId.length; i++) {
			perEntity = this.getByProerties("termId", termId[i]);
			if (perEntity != null) {
				perEntity.setPriceId(meterId);
				perEntity.setUpdateTime(date);
				perEntity.setUpdateUser(xm);
				this.merge(perEntity);
			} else {
				perEntity = new PtPriceBind();
				perEntity.setPriceId(meterId);
				perEntity.setTermId(termId[i]);
				perEntity.setTermSn(termSn[i]);
				perEntity.setCreateUser(xm);
				perEntity.setCreateTime(date);
				perEntity.setId(keyRedisService.getId(PtPriceBind.ModuleType));	//手动设置id
				this.merge(perEntity);
			}
		}
	}
}
