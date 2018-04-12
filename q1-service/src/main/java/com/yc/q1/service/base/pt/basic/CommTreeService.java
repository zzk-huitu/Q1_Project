package com.yc.q1.service.base.pt.basic;

import java.util.List;

import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.service.BaseService;
import com.yc.q1.pojo.base.pt.CommTree;

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