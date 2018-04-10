package com.yc.q1.base.pt.basic.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.basic.dao.JwTGradeDao;
import com.yc.q1.base.pt.basic.model.Grade;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 
 * ClassName: JwTGradeDaoImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学校年级信息实体Dao接口实现类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class JwTGradeDaoImpl extends BaseDaoImpl<Grade> implements JwTGradeDao {
    public JwTGradeDaoImpl() {
        super(Grade.class);
        // TODO Auto-generated constructor stub
    }
}