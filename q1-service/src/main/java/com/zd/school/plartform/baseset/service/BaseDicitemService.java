package com.zd.school.plartform.baseset.service;

import com.zd.core.service.BaseService;
import com.zd.school.plartform.baseset.model.DataDictItem;


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
 
public interface BaseDicitemService extends BaseService<DataDictItem> {

	DataDictItem doAdd(DataDictItem entity, String xm);

	DataDictItem doUpdate(DataDictItem entity, String xm);

	boolean doDeleteOrRestore(String delIds, String isdelete, String xm);

}