package com.yc.q1.service.base.pt.device;

import com.yc.q1.model.base.pt.device.PtIrDeviceBrand;
import com.yc.q1.model.base.pt.system.PtUser;
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
 
public interface IrDeviceBrandService extends BaseService<PtIrDeviceBrand> {
	public PtIrDeviceBrand doAddEntity(PtIrDeviceBrand entity, PtUser currentUser);
	public PtIrDeviceBrand doUpdateEntity(PtIrDeviceBrand entity, PtUser currentUser);
	
}