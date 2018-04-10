package com.yc.q1.base.pt.basic.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.basic.dao.BaseCampusDao;
import com.yc.q1.base.pt.basic.model.Campus;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: BaseCampusDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 校区信息实体Dao接口实现类.
 * date: 2016-08-13
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseCampusDaoImpl extends BaseDaoImpl<Campus> implements BaseCampusDao {
    public BaseCampusDaoImpl() {
        super(Campus.class);
        // TODO Auto-generated constructor stub
    }
}