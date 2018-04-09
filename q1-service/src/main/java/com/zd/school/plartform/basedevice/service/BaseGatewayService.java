package com.zd.school.plartform.basedevice.service;

import javax.servlet.http.HttpServletRequest;

import com.zd.core.service.BaseService;
import com.zd.school.control.device.model.Gateway;
import com.zd.school.control.device.model.TLVModel;
import com.zd.school.plartform.system.model.User;

/**
 * 网关表
 * @author hucy
 *
 */
public interface BaseGatewayService extends BaseService<Gateway>{
	public Gateway doUpdateEntity(Gateway entity, User currentUser);
	public Gateway doAddEntity(Gateway entity, User currentUser);
	public void doSetGatewayParam(HttpServletRequest request, TLVModel tlvs, String userCh);
	public void doUpdateBaseHighParam(TLVModel tlvs, String xm);
	public void doUpdateBaseHighParamToIds(TLVModel tlvs, String gatewayIds, String xm);
	public void doUpdateBaseHighParamToAll(TLVModel tlvs, String xm);
	public void doUpdateBatchFront(Gateway entity, String xm);
}
