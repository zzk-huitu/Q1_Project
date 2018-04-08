package com.zd.school.plartform.basedevice.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.control.device.model.RoomBag;
import com.zd.school.plartform.basedevice.dao.PtRoomBagsDao;

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
