package com.yc.q1.base.pt.basic.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.basic.dao.BaseCalenderDao;
import com.yc.q1.base.pt.basic.model.Calender;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: JwCalenderDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 校历信息(JW_T_CALENDER)实体Dao接口实现类.
 * date: 2016-08-30
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseCalenderDaoImpl extends BaseDaoImpl<Calender> implements BaseCalenderDao {
    public BaseCalenderDaoImpl() {
        super(Calender.class);
        // TODO Auto-generated constructor stub
    }
}