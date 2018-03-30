package com.zd.school.control.device.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 水控、电控费率绑定
 * 
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_PriceBind")
@AttributeOverride(name = "priceBindId", column = @Column(name = "priceBindId", length = 20, nullable = false))
public class PtPriceBind extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "deviceId", type = "varchar(20)", explain = "设备编号")
	@Column(name = "deviceId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String deviceId;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@FieldInfo(name = "deviceSn", type = "varchar(14)", explain = "设备序列号")
	@Column(name = "deviceSn", columnDefinition = "varchar(14) default ''", nullable = true)
	private String deviceSn;

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	@FieldInfo(name = "priceId", type = "varchar(20)", explain = "费率编号")
	@Column(name = "priceId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String priceId;

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}