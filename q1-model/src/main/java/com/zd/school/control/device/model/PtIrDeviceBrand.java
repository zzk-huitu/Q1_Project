package com.zd.school.control.device.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: PtIrDeviceBrand 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 红外设备品牌型号(PT_IR_DEVICE_BRAND)实体类.
 * date: 2017-01-12
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_IrDeviceBrand")
@AttributeOverride(name = "irDeviceBrandId", column = @Column(name = "irDeviceBrandId", length = 36, nullable = false))
public class PtIrDeviceBrand extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "品牌名称")
    @Column(name = "brandName", length = 50, nullable = true)
    private String brandName;
    
    
    @FieldInfo(name = "类型编号")
    @Column(name = "deviceTypeCode", length = 36, nullable = true)
    private String deviceTypeCode;
    
    
    @FieldInfo(name = "产品型号")
    @Column(name = "productModel", length = 100, nullable = true)
    private String productModel;
    
    @FieldInfo(name = "区域等级")
    @Column(name = "level")
    private Integer level;
    
    @FieldInfo(name = "是否有下级")
    @Column(name = "isLeaf")
    private Integer isLeaf;
     
    @FieldInfo(name = "上级区域ID")
    @Column(name = "parentNode", length = 36, nullable = true)
    private String parentNode;
    
    @FieldInfo(name = "备注")
    @Column(name = "notes", length = 1000, nullable = true)
    private String notes;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}


	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}
        

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}