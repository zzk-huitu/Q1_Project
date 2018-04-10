package com.yc.q1.base.xf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.xf.dao.RoomBagDao;
import com.yc.q1.base.xf.model.RoomBag;
import com.yc.q1.base.xf.service.PtRoomBagsService;
import com.zd.core.service.BaseServiceImpl;

/**
 * 宿舍钱包
 * @author hucy
 *
 */
@Service
@Transactional
public class PtRoomBagsServiceImpl extends BaseServiceImpl<RoomBag> implements PtRoomBagsService{
	
	@Resource
    public void setPtRoomBagsDao(RoomBagDao dao) {
        this.dao = dao;
    }
}
