package com.yc.q1.base.pt.device.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.device.service.IrRoomDeviceService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.device.PtIrRoomDevice;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: PtIrRoomDeviceServiceImpl Function: ADD FUNCTION. Reason: ADD
 * REASON(可选). Description: 房间红外设备(PT_IR_ROOM_DEVICE)实体Service接口实现类. date:
 * 2017-01-12
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class IrRoomDeviceServiceImpl extends BaseServiceImpl<PtIrRoomDevice> implements IrRoomDeviceService {

	@Resource(name = "IrRoomDeviceDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtIrRoomDevice> dao) {
		super.setDao(dao);
	}

	private static Logger logger = Logger.getLogger(IrRoomDeviceServiceImpl.class);
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public void doBindRoomBrand(String roomIds, String brandIds, String xm) {

		String[] roomId = roomIds.split(",");
		String[] brandId = brandIds.split(",");
		// TODO Auto-generated method stub
		PtIrRoomDevice roomDevice = null;
		for (int i = 0; i < brandId.length; i++) {
			for (int j = 0; j < roomId.length; j++) {
				String[] name = { "roomId", "brandId" };
				String[] value = { roomId[j], brandId[i] };
				roomDevice = this.getByProerties(name, value);
				if (roomDevice != null) {
					roomDevice.setBrandId(brandId[i]);
					roomDevice.setUpdateTime(new Date());
					roomDevice.setIsDelete(0);
					roomDevice.setUpdateUser(xm);
					this.merge(roomDevice);
				} else {
					roomDevice = new PtIrRoomDevice();
					roomDevice.setBrandId(brandId[i]);
					roomDevice.setRoomId(roomId[j]);
					roomDevice.setCreateTime(new Date());
					roomDevice.setCreateUser(xm);
					roomDevice.setId(keyRedisService.getId(PtIrRoomDevice.ModuleType));	//手动设置id
					this.merge(roomDevice);
				}
			}
		}
	}

}