package com.yc.q1.dao.base.pt.basic.impl;



import org.springframework.stereotype.Repository;

import com.yc.q1.dao.base.pt.basic.CommTreeDao;
import com.zd.core.dao.BaseDaoImpl;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: BaseDicDaoImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 数据字典实体Dao接口实现类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class CommTreeDaoImpl extends BaseDaoImpl<BaseEntity> implements CommTreeDao {
    public CommTreeDaoImpl() {
        super(BaseEntity.class);
        // TODO Auto-generated constructor stub
    }
}