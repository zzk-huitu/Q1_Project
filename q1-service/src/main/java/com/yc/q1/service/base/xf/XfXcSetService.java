package com.yc.q1.service.base.xf;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.xf.XfXcSet;

public interface XfXcSetService extends BaseService<XfXcSet> {
	
    public XfXcSet doUpdateEntity(XfXcSet entity, PtUser currentUser);
	
	public XfXcSet doAddEntity(XfXcSet entity, PtUser currentUser);

}
