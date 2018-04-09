package com.zd.school.plartform.basedevice.service;

import com.zd.core.service.BaseService;
import com.zd.school.control.device.model.IrDeviceBrand ;
import com.zd.school.plartform.system.model.User;


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
 
public interface PtIrDeviceBrandService extends BaseService<IrDeviceBrand> {
	public IrDeviceBrand doAddEntity(IrDeviceBrand entity, User currentUser);
	public IrDeviceBrand doUpdateEntity(IrDeviceBrand entity, User currentUser);
	
}