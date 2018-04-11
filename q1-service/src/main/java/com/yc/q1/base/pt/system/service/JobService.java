package com.yc.q1.base.pt.system.service;

import com.yc.q1.model.base.pt.system.Job;
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
 
public interface JobService extends BaseService<Job> {

	Job doUpdate(Job entity, String xm);

}