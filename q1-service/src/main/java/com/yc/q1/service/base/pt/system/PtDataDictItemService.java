package com.yc.q1.service.base.pt.system;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtDataDictItem;


/**
 * 
 * ClassName: BaseDicitemService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 数据字典项实体Service接口类.
 * date: 2016-07-19
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtDataDictItemService extends BaseService<PtDataDictItem> {

	PtDataDictItem doAdd(PtDataDictItem entity, String xm);

	PtDataDictItem doUpdate(PtDataDictItem entity, String xm);

	boolean doDeleteOrRestore(String delIds, String isdelete, String xm);

}