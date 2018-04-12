
package com.yc.q1.dao.storage.pt.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.storage.pt.PtTaskDao;
import com.yc.q1.model.storage.pt.PtTask;

/**
* Created by zenglj on 2017-05-16.
*/
@Repository("PtTaskDao")
public class PtTaskDaoImpl extends BaseDaoImpl<PtTask> implements PtTaskDao {
}

