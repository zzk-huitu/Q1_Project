package com.yc.q1.base.pt.device.service;

import com.yc.q1.base.pt.device.model.IrRoomDevice;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: PtIrRoomDeviceService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 房间红外设备(PT_IR_ROOM_DEVICE)实体Service接口类.
 * date: 2017-01-12
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface IrRoomDeviceService extends BaseService<IrRoomDevice> {

	public void doBindRoomBrand(String roomIds, String brandIds, String xm);
	
}