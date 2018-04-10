
package com.yc.q1.base.pt.card.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.card.dao.PtTaskDao;
import com.yc.q1.base.pt.card.model.Task;
import com.zd.core.dao.BaseDaoImpl;

/**
* Created by zenglj on 2017-05-16.
*/
@Repository
public class PtTaskDaoImpl extends BaseDaoImpl<Task> implements PtTaskDao {
    public PtTaskDaoImpl() {
        super(Task.class);
        // TODO Auto-generated constructor stub
    }
}

