package com.yc.q1.base.pt.device.service;

import javax.servlet.http.HttpServletRequest;

import com.yc.q1.model.base.pt.device.PtGateway;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.TLVModel;
import com.zd.core.service.BaseService;

/**
 * 网关表
 * @author hucy
 *
 */
public interface GatewayService extends BaseService<PtGateway>{
	public PtGateway doUpdateEntity(PtGateway entity, PtUser currentUser);
	public PtGateway doAddEntity(PtGateway entity, PtUser currentUser);
	public void doSetGatewayParam(HttpServletRequest request, TLVModel tlvs, String userCh);
	public void doUpdateBaseHighParam(TLVModel tlvs, String xm);
	public void doUpdateBaseHighParamToIds(TLVModel tlvs, String gatewayIds, String xm);
	public void doUpdateBaseHighParamToAll(TLVModel tlvs, String xm);
	public void doUpdateBatchFront(PtGateway entity, String xm);
}
