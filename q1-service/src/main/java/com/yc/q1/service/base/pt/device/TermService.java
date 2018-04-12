package com.yc.q1.service.base.pt.device;

import com.yc.q1.model.base.pt.device.PtTerm;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.TLVModel;
import com.zd.core.service.BaseService;

/**
 * 设备表
 * @author hucy
 *
 */
public interface TermService extends BaseService<PtTerm>{

	public PtTerm doAddEntity(PtTerm entity, PtUser currentUser);
	
	void batchUpdate(int termTypeID, String areaType, String areaType2, String[] strings, Object[] objects);

	public PtTerm doUpdateEntity(PtTerm entity, PtUser currentUser);

	public void doUpdatHighParamToIds(TLVModel tlvs, String termIds, String xm);

	public void doUpdateHighParam(TLVModel tlvs, String xm);

	public void doBatchUpdateHighParam(TLVModel tlvs, String termTypeID, String areaType, String xm);

	public void doUpdateBaseParam(TLVModel tlvs, String notes, String xm);

	public void doBatchUpdateBaseParam(TLVModel tlvs, String termTypeID, String notes, String areaType, String xm);

	public void doSetPtTerm(String roomId, String uuid, PtUser currentUser);
	
}
