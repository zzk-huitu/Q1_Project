
package com.zd.school.ykt.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.ykt.dao.PtTaskDao ;
import com.zd.school.ykt.model.Task;

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

