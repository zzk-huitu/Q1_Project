package com.yc.q1.base.pt.device.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.device.dao.PtIrDeviceBrandDao;
import com.yc.q1.base.pt.device.model.IrDeviceBrand;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: PtIrDeviceBrandDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 红外设备品牌型号(PT_IR_DEVICE_BRAND)实体Dao接口实现类.
 * date: 2017-01-12
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class PtIrDeviceBrandDaoImpl extends BaseDaoImpl<IrDeviceBrand> implements PtIrDeviceBrandDao {
    public PtIrDeviceBrandDaoImpl() {
        super(IrDeviceBrand.class);
        // TODO Auto-generated constructor stub
    }
}