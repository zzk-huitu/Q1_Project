package com.yc.q1.base.pt.device.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 红外设备数据
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_IrData")
@AttributeOverride(name = "id", column = @Column(name = "irDataId", length = 20, nullable = false) )
public class IrData extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "红外数据所属品牌ID", type = "varchar(20) NOT NULL", explain = "红外数据所属品牌")
	@Column(name = "brandId", length = 20, nullable = false)
	private String brandId;

	@FieldInfo(name = "红外数据编码", type = "varchar(10) NOT NULL", explain = "红外数据编码")
	@Column(name = "irDataNo", length = 10,  nullable = false)
	private Long irDataNo;

	@FieldInfo(name = "红外数据名称", type = "nvarchar(20) NOT NULL", explain = "红外数据名称")
	@Column(name = "irDataName", columnDefinition = "nvarchar(20) ", nullable = false)
	private String irDataName;

	@FieldInfo(name = "红外动作数据", type = "varbinary(255) NOT NULL", explain = "红外动作数据")
	@Column(name = "irActionData", length = 255, nullable = false)
	private byte[] irActionData;

	@FieldInfo(name = "红外转换数据", type = "varbinary(255) NOT NULL", explain = "红外转换数据")
	@Column(name = "irConvertedData", length = 255, nullable = false)
	private byte[] irConvertedData;

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public Long getIrDataNo() {
		return irDataNo;
	}

	public void setIrDataNo(Long irDataNo) {
		this.irDataNo = irDataNo;
	}

	public String getIrDataName() {
		return irDataName;
	}

	public void setIrDataName(String irDataName) {
		this.irDataName = irDataName;
	}

	public byte[] getIrActionData() {
		return irActionData;
	}

	public void setIrActionData(byte[] irActionData) {
		this.irActionData = irActionData;
	}

	public byte[] getIrConvertedData() {
		return irConvertedData;
	}

	public void setIrConvertedData(byte[] irConvertedData) {
		this.irConvertedData = irConvertedData;
	}

	public IrData() {
		super();
	}

	public IrData(String id) {
		super(id);
	}

}