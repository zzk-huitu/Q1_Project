package com.yc.q1.base.pt.device.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 房间红外设备
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_IrRoomDevice")
@AttributeOverride(name = "id", column = @Column(name = "deviceId", length = 20, nullable = false) )
public class IrRoomDevice extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "房间编号", type = "varchar(20) NOT NULL", explain = "房间编号")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "型号编号", type = "varchar(20) NOT NULL", explain = "型号编号")
	@Column(name = "brandId", length=20, nullable = false)
	private String brandId;

	@FieldInfo(name = "备注", type = "nvarchar(256) default ''", explain = "备注")
	@Column(name = "notes", columnDefinition = "nvarchar(256) default ''", nullable = true)
	private String notes;

	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间名称")
	private String roomName;

	@Formula("(SELECT A.productModel FROM dbo.T_PT_IrDeviceBrand A WHERE A.brandId=brandId)")
	// @FieldInfo(name = "型号名称")
	private String deviceTypeCode;

	@Formula("(SELECT A.brandName FROM dbo.T_PT_IrDeviceBrand A WHERE A.brandId=brandId)")
	// @FieldInfo(name = "品牌名称")
	private String deviceBrandName; // zzk新加入

	@Formula("(select B.brandName from dbo.T_PT_IrDeviceBrand B where B.brandId=("
			+ "	SELECT A.deviceTypeCode FROM dbo.T_PT_IrDeviceBrand A WHERE A.brandId=brandId" + "))")
	// @FieldInfo(name = "产品类型名称")
	private String deviceTypeName; // zzk新加入

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public String getDeviceBrandName() {
		return deviceBrandName;
	}

	public void setDeviceBrandName(String deviceBrandName) {
		this.deviceBrandName = deviceBrandName;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public IrRoomDevice() {
		super();
	}

	public IrRoomDevice(String id) {
		super(id);
	}

}