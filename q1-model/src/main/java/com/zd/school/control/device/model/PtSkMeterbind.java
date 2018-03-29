package com.zd.school.control.device.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 水控流量记表绑定
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_SkMeterBind")
@AttributeOverride(name = "skMeterBindId", column = @Column(name = "skMeterBindId", length = 20, nullable = false))
public class PtSkMeterbind extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "设备编号")
    @Column(name = "termId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String termId; 
  
    @FieldInfo(name = "设备序列号")
    @Column(name = "termSn", columnDefinition = "varchar(14) default ''", nullable = true)
    private String termSn;
   
    @FieldInfo(name = "水控流量计编号")
    @Column(name = "meterId", columnDefinition = "varchar(20) default ''", nullable = true)
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

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}