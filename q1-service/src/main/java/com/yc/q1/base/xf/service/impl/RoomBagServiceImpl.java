package com.yc.q1.base.xf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.xf.model.RoomBag;
import com.yc.q1.base.xf.service.RoomBagService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 宿舍钱包
 * @author hucy
 *
 */
@Service
@Transactional
public class RoomBagServiceImpl extends BaseServiceImpl<RoomBag> implements RoomBagService{
	
	@Resource(name="RoomBagDao")	//将具体的dao注入进来
	public void setDao(BaseDao<RoomBag> dao) {
		super.setDao(dao);
	}
}
