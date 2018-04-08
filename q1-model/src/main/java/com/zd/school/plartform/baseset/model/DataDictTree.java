/**
 * Project Name:school-model
 * File Name:BaseDicTree.java
 * Package Name:com.zd.school.plartform.baseset.model
 * Date:2016年7月19日下午4:13:01
 * Copyright (c) 2016 ZDKJ All Rights Reserved.
 *
*/

package com.zd.school.plartform.baseset.model;

import java.util.List;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.extjs.ExtTreeNode;

/**
 * POJO 数据字典树
 * @author ZZK
 *
 */
public class DataDictTree extends ExtTreeNode<DataDictTree> {

    @FieldInfo(name = "字典编码")
    private String dicCode;

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicCode() {
        return dicCode;
    }

    @FieldInfo(name = "字典类型，目前就LIST与TREE两类")
    private String dicType;

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public String getDicType() {
        return dicType;
    }


    @FieldInfo(name = "上级字典")
    private String parent;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @FieldInfo(name = "排序号")
    private Integer orderIndex;

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public DataDictTree(String id, List<DataDictTree> children) {

        super(id, children);
    }

    public DataDictTree(String id, String text, String iconCls, Boolean leaf, Integer level, String treeid,
            List<DataDictTree> children, String dicCode, String dicType,  String parent,
            Integer orderIndex) {
        super(id, text, iconCls, leaf, level, treeid,parent,orderIndex, children);
        this.dicCode = dicCode;
        this.dicType = dicType;
        this.parent = parent;
        this.orderIndex = orderIndex;
    }
}
