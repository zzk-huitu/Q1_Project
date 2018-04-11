package com.yc.q1.model.base.pt.device;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 红外设备品牌型号
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_IrDeviceBrand")
@AttributeOverride(name = "id", column = @Column(name = "brandId", length = 20, nullable = false) )
public class IrDeviceBrand extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "品牌名称", type = "nvarchar(16)  NOT NULL", explain = "品牌名称")
	@Column(name = "brandName", columnDefinition = "nvarchar(16)", nullable = false)
	private String brandName;

	@FieldInfo(name = "类型编号id", type = "varchar(20)  default ''", explain = "类型编号id")
	@Column(name = "deviceTypeCode", columnDefinition = "varchar(20) default ''", nullable = true)
	private String deviceTypeCode;

	@FieldInfo(name = "产品型号", type = "nvarchar(16)  default ''", explain = "产品型号")
	@Column(name = "productModel", columnDefinition = "nvarchar(16) default ''", nullable = true)
	private String productModel;

	@FieldInfo(name = "区域等级", type = "int default 0", explain = "区域等级")
	@Column(name = "level", columnDefinition = "int default 0", nullable = true)
	private Integer level;

	@FieldInfo(name = "是否有下级", type = "bit default 0", explain = "是否有下级")
	@Column(name = "isLeaf", columnDefinition = "bit default 0", nullable = true)
	private Boolean isLeaf;

	@FieldInfo(name = "上级区域ID", type = "varchar(20) default ''", explain = "上级区域ID")
	@Column(name = "parentNode",  columnDefinition = "varchar(20) default ''", nullable = true)
	private String parentNode;

	@FieldInfo(name = "备注", type = "nvarchar(256) default ''", explain = "备注")
	@Column(name = "notes", columnDefinition = "nvarchar(256) default ''", nullable = true)
	private String notes;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public IrDeviceBrand() {
		super();
	}

	public IrDeviceBrand(String id) {
		super(id);
	}

}