package com.yc.q1.base.pt.basic.service;

import java.util.List;

import com.yc.q1.base.pt.pojo.CommTree;
import com.zd.core.model.BaseEntity;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: BaseDicitemService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 数据字典项实体Service接口类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface CommTreeService extends BaseService<BaseEntity> {


    public List<CommTree> getCommTree(String treeView, String whereSql);

   
	public CommTree getGradeCommTree(String sql, String rootId);

	public List<CommTree> getCommTreeList(String sql);

	
}