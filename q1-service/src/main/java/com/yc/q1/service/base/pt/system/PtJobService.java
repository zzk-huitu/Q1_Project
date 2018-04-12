package com.yc.q1.service.base.pt.system;

import com.yc.q1.model.base.pt.system.PtJob;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: BizTJobService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 岗位信息实体Service接口类.
 * date: 2016-05-16
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtJobService extends BaseService<PtJob> {

	PtJob doUpdate(PtJob entity, String xm);

}