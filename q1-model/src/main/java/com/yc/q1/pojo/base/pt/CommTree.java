/**
 * Project Name:school-model
 * File Name:GradeTree.java
 * Package Name:com.zd.school.plartform.comm.model
 * Date:2016年8月23日下午1:53:51
 * Copyright (c) 2016 ZDKJ All Rights Reserved.
 *
*/

package com.yc.q1.pojo.base.pt;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.model.extjs.ExtTreeNode;

import java.util.List;
/**
 * POJO （保留这个类，并开放checked字段；而通过排除字段的方式去排除checked字段）
 * @author ZZK
 *
 */
public class CommTree extends ExtTreeNode<CommTree> {
	private static final long serialVersionUID = 1L;
	@FieldInfo(name = "节点类型")
	private String nodeType;

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	
    public CommTree() {

        super();
        // TODO Auto-generated constructor stub

    }

    public CommTree(String id, List<CommTree> children) {

        super(id, children);
        // TODO Auto-generated constructor stub

    }

    public CommTree(String id, String text, String iconCls, Boolean leaf, Integer level, String treeid, String parent,Integer orderIndex,
            List<CommTree> children) {

        super(id, text, iconCls, leaf, level, treeid,parent,orderIndex, children);
    }

}
