
package com.zd.core.model.extjs;

import java.util.ArrayList;
import java.util.List;

/**
 * ExtTreeNodeNotChk 不带checked属性
 * Date: 2016年5月11日 下午4:10:59
 * 
 * @author luoyibo
 * @version
 * @since JDK 1.8
 * @see
 */
public class ExtTreeNodeNotChk<T> {

    /** 节点ID */
    public String id;

    /** 节点文本 */
    public String text;

    /** 节点样式 */
    public String iconCls;

    /** 是否叶节点 */
    public Boolean leaf;

    /** 节点层级 */
    public Integer level;

    /** 各级上级节点ID组 */
    public String treeid;

    /** 子节点清单 */
    private List<T> children = new ArrayList<T>();

	/** 上级节点ID */
	private String parent;

	/** 同级别排序号 */
	private Integer orderIndex;

	public String getId() {
		return id;
	}

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTreeid() {
        return treeid;
    }

    public void setTreeid(String treeid) {
        this.treeid = treeid;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	
	public ExtTreeNodeNotChk() {
		super();
	}

	public ExtTreeNodeNotChk(String id, List<T> children) {
		super();
		this.id = id;
		this.children = children;
	}

	public ExtTreeNodeNotChk(String id, String text, String iconCls, Boolean leaf, Integer level, String treeid,String parent,Integer orderIndex,
			List<T> children) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.leaf = leaf;
		this.level = level;
		this.treeid = treeid;
		this.children = children;
		this.parent = parent;
		this.orderIndex = orderIndex;
	}
}
