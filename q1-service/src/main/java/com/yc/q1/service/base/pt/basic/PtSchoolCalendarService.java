package com.yc.q1.service.base.pt.basic;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.basic.PtSchoolCalendar;
import com.yc.q1.model.base.pt.system.PtUser;

public interface PtSchoolCalendarService extends BaseService<PtSchoolCalendar> {
	public  PtSchoolCalendar doAddEntity(PtSchoolCalendar entity,PtUser currentUser);

}
