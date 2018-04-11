package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.TreeNodeEntity;

/**
 * 数据字典
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_DataDict")
@AttributeOverride(name = "id", column = @Column(name = "dictId", length = 20, nullable = false) )
public class DataDict extends TreeNodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "字典编码", type = "varchar(16) NOT NULL", explain = "字典编码")
	@Column(name = "dicCode", columnDefinition = "varchar(16)", nullable = false)
	private String dicCode;

	@FieldInfo(name = "字典类型", type = "varchar(8) NOT NULL", explain = "字典类型(LIST、TREE、OTHER类型，目前LIST为主)")
	@Column(name = "dicType", columnDefinition = "varchar(8)", nullable = false)
	private String dicType;

	@FieldInfo(name = "引用实体路径", type = "varchar(256)", explain = "引用实体路径（暂未使用到）")
	@Column(name = "physicalPath", columnDefinition = "varchar(256)", nullable = true)
	private String physicalPath;

	// @FieldInfo(name = "上级字典名称")
	@Transient
	private String parentName;

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}

	public String getPhysicalPath() {
		return physicalPath;
	}

	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public DataDict() {
		super();
	}

	public DataDict(String id) {
		super(id);
	}

}