package com.yc.q1.service.storage.pt.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.card.PtSubsidyFillMoneyItem;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.pt.PtSubsidyFillMoneyItemService;
@Service
@Transactional
public class PtSubsidyFillMoneyItemServiceImpl  extends BaseServiceImpl<PtSubsidyFillMoneyItem> implements PtSubsidyFillMoneyItemService{
	@Resource(name="SkTermStatusDao")	//将具体的dao注入进来
	public void setDao(BaseDao<PtSubsidyFillMoneyItem> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
}
