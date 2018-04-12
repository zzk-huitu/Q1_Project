package com.yc.q1.dao.storage.sk.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.storage.sk.SkTermStatusDao;
import com.yc.q1.model.storage.sk.SkTermStatus;


@Repository("SkTermStatusDao")
public class SkTermStatusDaoImpl extends BaseDaoImpl<SkTermStatus> implements SkTermStatusDao {
}