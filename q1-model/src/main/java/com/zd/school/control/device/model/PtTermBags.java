package com.zd.school.control.device.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

@Entity
@Table(name = "T_PT_DeviceBag")
@AttributeOverride(name = "deviceBagId", column = @Column(name = "deviceBagId", length = 20, nullable = false))
public class PtTermBags extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "deviceSn", type = "varchar(14)", explain = "设备序列号")
	@Column(name = "deviceSn", columnDefinition = "varchar(14) default ''", nullable = true)
	private String deviceSn;

	@FieldInfo(name = "deviceTypeId", type = "Integer", explain = "设备类型")
	@Column(name = "deviceTypeId")
	private Integer deviceTypeId;

	@FieldInfo(name = "bagValue", type = "BigDecimal", explain = "设备余额")
	@Column(name = "bagValue")
	private BigDecimal bagValue;

	@FieldInfo(name = "totalBoughtValue", type = "BigDecimal", explain = "总买量")
	@Column(name = "totalBoughtValue")
	private BigDecimal totalBoughtValue;

	@FieldInfo(name = "totalUsedValue", type = "BigDecimal", explain = "总用量")
	@Column(name = "totalUsedValue")
	private BigDecimal totalUsedValue;

	@FieldInfo(name = "totalClearValue", type = "BigDecimal", explain = "总计清除补助量")
	@Column(name = "totalClearValue")
	private BigDecimal totalClearValue;

	@FieldInfo(name = "surplusValue", type = "BigDecimal", explain = "补助剩余量")
	@Column(name = "surplusValue")
	private BigDecimal surplusValue;

	@Transient
	@FieldInfo(name = "绑定费率规则")
	protected String bdrole = "";

	@Formula("(SELECT A.deviceName FROM dbo.T_PT_Deveice A WHERE A.deviceSn=deviceSn)")
	public String deviceName;

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public BigDecimal getBagValue() {
		return bagValue;
	}

	public void setBagValue(BigDecimal bagValue) {
		this.bagValue = bagValue;
	}

	public BigDecimal getTotalUsedValue() {
		return totalUsedValue;
	}

	public void setTotalUsedValue(BigDecimal totalUsedValue) {
		this.totalUsedValue = totalUsedValue;
	}

	public BigDecimal getTotalClearValue() {
		return totalClearValue;
	}

	public void setTotalClearValue(BigDecimal totalClearValue) {
		this.totalClearValue = totalClearValue;
	}

	public BigDecimal getTotalBoughtValue() {
		return totalBoughtValue;
	}

	public void setTotalBoughtValue(BigDecimal totalBoughtValue) {
		this.totalBoughtValue = totalBoughtValue;
	}

	public BigDecimal getSurplusValue() {
		return surplusValue;
	}

	public void setSurplusValue(BigDecimal surplusValue) {
		this.surplusValue = surplusValue;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getBdrole() {
		return bdrole;
	}

	public void setBdrole(String bdrole) {
		this.bdrole = bdrole;
	}

}
