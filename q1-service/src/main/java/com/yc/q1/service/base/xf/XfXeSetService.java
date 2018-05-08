package com.yc.q1.service.base.xf;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.xf.XfXeSet;

public interface XfXeSetService extends BaseService<XfXeSet> {

	public XfXeSet doUpdateEntity(XfXeSet entity, PtUser currentUser);
	
	public XfXeSet doAddEntity(XfXeSet entity, PtUser currentUser);

}
