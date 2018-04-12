package com.yc.q1.service.base.pt.system;

import java.util.List;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtDataDict;
import com.yc.q1.pojo.base.pt.DataDictTree;


/**
 * 
 * ClassName: BaseDicService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 数据字典实体Service接口类.
 * date: 2016-07-19
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtDataDictService extends BaseService<PtDataDict> {

    public List<DataDictTree>getDicTreeList(String whereSql);

	public PtDataDict doAdd(PtDataDict entity, String xm);

}