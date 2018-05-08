package com.yc.q1.service.base.xf;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.xf.XfRateSet;

public interface XfRateSetService  extends BaseService<XfRateSet>{
	
		public XfRateSet doUpdateEntity(XfRateSet entity, PtUser currentUser);
		
		public XfRateSet doAddEntity(XfRateSet entity, PtUser currentUser);

}
