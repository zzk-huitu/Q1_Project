package com.yc.q1.service.base.pt.device;

import javax.servlet.http.HttpServletRequest;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.device.PtGateway;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.TLVModel;

/**
 * 网关表
 * @author hucy
 *
 */
public interface PtGatewayService extends BaseService<PtGateway>{
	public PtGateway doUpdateEntity(PtGateway entity, PtUser currentUser);
	public PtGateway doAddEntity(PtGateway entity, PtUser currentUser);
	public void doSetGatewayParam(HttpServletRequest request, TLVModel tlvs, String userCh);
	public void doUpdateBaseHighParam(TLVModel tlvs, String xm);
	public void doUpdateBaseHighParamToIds(TLVModel tlvs, String gatewayIds, String xm);
	public void doUpdateBaseHighParamToAll(TLVModel tlvs, String xm);
	public void doUpdateBatchFront(PtGateway entity, String xm);
}
