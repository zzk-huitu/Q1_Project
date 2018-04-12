package com.yc.q1.model.base.sk;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 水控流量记表绑定
 * 
 * @author hucy
 *
 */
@Entity
@Table(name = "T_SK_MeterBind")
@AttributeOverride(name = "id", column = @Column(name = "meterBindId", length = 20, nullable = false) )
public class SkMeterBind extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.SK;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "设备编号", type = "varchar(20) NOT NULL", explain = "设备编号")
	@Column(name = "termId", length = 20, nullable = false)
	private String termId;

	@FieldInfo(name = "设备序列号", type = "varchar(14) NOT NULL", explain = "设备序列号")
	@Column(name = "termSn", length = 14,  nullable = false)
	private String termSn;

	@FieldInfo(name = "水控流量计编号", type = "varchar(20) NOT NULL", explain = "设备序列号")
	@Column(name = "meterId", length = 20, nullable = false)
	private String meterId;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public SkMeterBind() {
		super();
	}

	public SkMeterBind(String id) {
		super(id);
	}

}