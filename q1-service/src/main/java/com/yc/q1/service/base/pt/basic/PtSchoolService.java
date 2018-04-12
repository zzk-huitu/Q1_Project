package com.yc.q1.service.base.pt.basic;

import com.yc.q1.model.base.pt.basic.PtSchool;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: BaseSchoolService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 学校信息实体Service接口类.
 * date: 2016-08-13
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtSchoolService extends BaseService<PtSchool> {

	PtSchool doUpdate(PtSchool entity, String xm);

}