package com.yc.q1.base.pt.build.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.build.dao.BaseRoomareaDao;
import com.yc.q1.base.pt.build.model.RoomArea;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: BuildRoomareaDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 教室区域实体Dao接口实现类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseRoomareaDaoImpl extends BaseDaoImpl<RoomArea> implements BaseRoomareaDao {
    public BaseRoomareaDaoImpl() {
        super(RoomArea.class);
        // TODO Auto-generated constructor stub
    }
}