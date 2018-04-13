package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 数据字典子项
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_DataDictItem")
@AttributeOverride(name = "id", column = @Column(name = "dictItemId", length = 20, nullable = false) )
public class PtDataDictItem extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "字典ID", type = "varchar(20) NOT NULL", explain = "字典ID")
	@Column(name = "dictId", columnDefinition = "varchar(20)", nullable = false)
	private String dictId;

	@FieldInfo(name = "字典项编码", type = "varchar(16) NOT NULL", explain = "字典项编码")
	@Column(name = "itemCode", columnDefinition = "varchar(16)", nullable = false)
	private String itemCode;

	@FieldInfo(name = "字典项名称", type = "nvarchar(16) NOT NULL", explain = "字典项名称")
	@Column(name = "itemName", columnDefinition = "nvarchar(16)", nullable = false)
	private String itemName;

	@FieldInfo(name = "字典项说明", type = "nvarchar(128) DEFAULT ''", explain = "字典项说明")
	@Column(name = "itemDesc", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
	private String itemDesc;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	// @FieldInfo(name = "字典代码",type="nvarchar(128)",explain="字典项说明")
	@Formula("(SELECT a.dicCode FROM T_PT_DataDict a WHERE a.dictId=dictId)")
	private String dicCode;

	// @FieldInfo(name = "字典名称",type="nvarchar(128)",explain="字典名称")
	@Formula("(SELECT a.nodeText FROM T_PT_DataDict a WHERE a.dictId=dictId)")
	private String dicName;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public PtDataDictItem() {
		super();
	}

	public PtDataDictItem(String id) {
		super(id);
	}

}