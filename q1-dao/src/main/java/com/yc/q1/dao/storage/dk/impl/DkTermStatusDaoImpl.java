package com.yc.q1.dao.storage.dk.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.storage.dk.DkTermStatusDao;
import com.yc.q1.model.storage.dk.DkTermStatus;

@Repository("DkTermStatusDao")
public class DkTermStatusDaoImpl extends BaseDaoImpl<DkTermStatus> implements DkTermStatusDao {
}