package com.yc.q1.base.xf.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.xf.dao.PtRoomBagsDao;
import com.yc.q1.base.xf.model.RoomBag;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 宿舍钱包
 * @author hucy
 *
 */
@Repository
public class PtRoomBagsDaoImpl extends BaseDaoImpl<RoomBag> implements PtRoomBagsDao{
	
	public PtRoomBagsDaoImpl() {
		super(RoomBag.class);
	}

}
