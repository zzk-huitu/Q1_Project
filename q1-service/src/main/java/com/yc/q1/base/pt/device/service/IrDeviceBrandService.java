package com.yc.q1.base.pt.device.service;

import com.yc.q1.model.base.pt.device.IrDeviceBrand;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: PtIrDeviceBrandService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 红外设备品牌型号(PT_IR_DEVICE_BRAND)实体Service接口类.
 * date: 2017-01-12
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface IrDeviceBrandService extends BaseService<IrDeviceBrand> {
	public IrDeviceBrand doAddEntity(IrDeviceBrand entity, User currentUser);
	public IrDeviceBrand doUpdateEntity(IrDeviceBrand entity, User currentUser);
	
}