package com.yc.q1.base.pt.device.service;

import javax.servlet.http.HttpServletRequest;

import com.yc.q1.model.base.pt.device.Gateway;
import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.pojo.base.pt.TLVModel;
import com.zd.core.service.BaseService;

/**
 * 网关表
 * @author hucy
 *
 */
public interface GatewayService extends BaseService<Gateway>{
	public Gateway doUpdateEntity(Gateway entity, User currentUser);
	public Gateway doAddEntity(Gateway entity, User currentUser);
	public void doSetGatewayParam(HttpServletRequest request, TLVModel tlvs, String userCh);
	public void doUpdateBaseHighParam(TLVModel tlvs, String xm);
	public void doUpdateBaseHighParamToIds(TLVModel tlvs, String gatewayIds, String xm);
	public void doUpdateBaseHighParamToAll(TLVModel tlvs, String xm);
	public void doUpdateBatchFront(Gateway entity, String xm);
}
