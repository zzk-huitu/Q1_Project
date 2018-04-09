package com.zd.school.plartform.basedevice.service;

import com.zd.core.service.BaseService;
import com.zd.school.control.device.model.Term;
import com.zd.school.control.device.model.TLVModel;
import com.zd.school.plartform.system.model.User;

/**
 * 设备表
 * @author hucy
 *
 */
public interface BasePtTermService extends BaseService<Term>{

	public Term doAddEntity(Term entity, User currentUser);
	
	void batchUpdate(int termTypeID, String areaType, String areaType2, String[] strings, Object[] objects);

	public Term doUpdateEntity(Term entity, User currentUser);

	public void doUpdatHighParamToIds(TLVModel tlvs, String termIds, String xm);

	public void doUpdateHighParam(TLVModel tlvs, String xm);

	public void doBatchUpdateHighParam(TLVModel tlvs, String termTypeID, String areaType, String xm);

	public void doUpdateBaseParam(TLVModel tlvs, String notes, String xm);

	public void doBatchUpdateBaseParam(TLVModel tlvs, String termTypeID, String notes, String areaType, String xm);

	public void doSetPtTerm(String roomId, String uuid, User currentUser);
	
}
