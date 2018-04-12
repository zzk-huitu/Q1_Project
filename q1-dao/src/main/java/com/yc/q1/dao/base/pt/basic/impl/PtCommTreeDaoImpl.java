package com.yc.q1.dao.base.pt.basic.impl;



import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.dao.base.pt.basic.CommTreeDao;

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
public class PtCommTreeDaoImpl extends BaseDaoImpl<BaseEntity> implements CommTreeDao {
    public PtCommTreeDaoImpl() {
        super(BaseEntity.class);
        // TODO Auto-generated constructor stub
    }
}