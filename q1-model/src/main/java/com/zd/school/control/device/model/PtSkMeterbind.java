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
    
    @FieldInfo(name = "设备编号",type="varchar(20)",explain="设备编号")
    @Column(name = "deveiceId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String deveiceId; 
  
    @FieldInfo(name = "设备序列号",type="varchar(14)",explain="设备序列号")
    @Column(name = "deveiceSn", columnDefinition = "varchar(14) default ''", nullable = true)
    private String deveiceSn;
   
    @FieldInfo(name = "水控流量计编号",type="varchar(20)",explain="设备序列号")
    @Column(name = "meterId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String meterId;

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public String getDeveiceId() {
		return deveiceId;
	}

	public void setDeveiceId(String deveiceId) {
		this.deveiceId = deveiceId;
	}

	public String getDeveiceSn() {
		return deveiceSn;
	}

	public void setDeveiceSn(String deveiceSn) {
		this.deveiceSn = deveiceSn;
	}

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}