/**
 * Project Name:zd-core
 * File Name:TreeNodeEntity.java
 * Package Name:com.zd.core.model
 * Date:2016年5月11日下午4:33:08
 * Copyright (c) 2016 ZDKJ All Rights Reserved.
 *
*/

package com.zd.core.model;

import com.zd.core.annotation.FieldInfo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 树节点实体
 * @author ZZK
 *
 */
@MappedSuperclass
public abstract class TreeNodeEntity extends BaseEntity {

    @FieldInfo(name = "节点编码",type="varchar(20) default ''",explain="节点编码")
    @Column(name = "nodeCode",columnDefinition="default ''",length=20 )
    private String nodeCode;

    @FieldInfo(name = "节点名称",type="nvarchar(16) default ''",explain="节点名称")
    @Column(name = "nodeText",columnDefinition="nvarchar(16) default ''" )
    private String nodeText;

    @FieldInfo(name = "父节点",type="varchar(20) default ''",explain="父节点")
    @Column(name = "parentNode",columnDefinition="default ''",length=20 )
    private String parentNode;

    @FieldInfo(name = "是否 叶节点",type="bit default 0",explain="是否 叶节点")
    @Column(name = "isLeaf",columnDefinition="default 0")
    private Boolean isLeaf;

    @FieldInfo(name = "节点层级",type="int default 0",explain="节点层级")
    @Column(name = "nodeLevel",columnDefinition="default 0" )
    private Integer nodeLevel;

    @FieldInfo(name = "节点标识层次",type="varchar(1024) default ''",explain="节点层级")
    @Column(name = "treeIds", columnDefinition="default ''",length=1024 )
    private String treeIds;

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeText() {
        return nodeText;
    }

    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getTreeIds() {
        return treeIds;
    }

    public void setTreeIds(String treeIds) {
        this.treeIds = treeIds;
    }

    public void BuildNode(TreeNodeEntity parentNode) {
        if (parentNode == null) {
            treeIds = super.getId();
            nodeLevel = 1;
        } else {
            treeIds = parentNode.treeIds + "," + super.getId();
            nodeLevel = parentNode.nodeLevel + 1;
        }
    }

    public TreeNodeEntity() {

        super();
        // TODO Auto-generated constructor stub

    }

    public TreeNodeEntity(String id) {

        super(id);
        // TODO Auto-generated constructor stub

    }
}
