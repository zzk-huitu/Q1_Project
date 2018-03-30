package com.zd.school.control.device.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: PtIrRoomDevice 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 房间红外设备(PT_IR_ROOM_DEVICE)实体类.
 * date: 2017-01-12
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_IrRoomDevice")
@AttributeOverride(name = "irRoomDeviceId", column = @Column(name = "irRoomDeviceId", length = 20, nullable = false))
public class PtIrRoomDevice extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "roomId", type = "varchar(20)", explain = "房间编号")
    @Column(name = "roomId", length = 20, nullable = false)
    private String roomId;
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoomId() {
        return roomId;
    }
        
    @FieldInfo(name = "brandId", type = "nvarchar(18)", explain = "型号编号")
    @Column(name = "brandId", columnDefinition = "nvarchar(18)", nullable = false)
    private String brandId;
    
    @FieldInfo(name = "notes", type = "nvarchar(200) ", explain = "备注")
    @Column(name = "notes", columnDefinition = "nvarchar(200) default ''", nullable = true)
    private String notes;
    
    @Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	@FieldInfo(name = "房间名称")
	private String roomName;
    
    @Formula("(SELECT A.productModel FROM dbo.T_PT_IrDeviceBrand A WHERE A.brandId=brandId)")
   	@FieldInfo(name = "型号名称")
   	private String deviceTypeCode;
    
    @Formula("(SELECT A.brandName FROM dbo.T_PT_IrDeviceBrand A WHERE A.brandId=brandId)")
   	@FieldInfo(name = "品牌名称")
   	private String deviceBrandName;	//zzk新加入
    
    @Formula("(select B.brandName from dbo.T_PT_IrDeviceBrand B where B.brandId=("
    		+ "	SELECT A.deviceTypeCode FROM dbo.T_PT_IrDeviceBrand A WHERE A.brandId=brandId"
    		+ "))")
   	@FieldInfo(name = "产品类型名称")
   	private String deviceTypeName;	//zzk新加入
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getNotes() {
        return notes;
    }
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
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
	
	
	
	
    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}