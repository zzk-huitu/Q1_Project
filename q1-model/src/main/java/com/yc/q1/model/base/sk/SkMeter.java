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
 * 水控流量计
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_SK_Meter")
@AttributeOverride(name = "id", column = @Column(name = "meterId", length = 20, nullable = false) )
public class SkMeter extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.SK;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "计量数（脉冲数/升）", type = "int NOT NULL", explain = "计量数（脉冲数/升）")
	@Column(name = "measure", nullable = false)
	private Integer measure;

	@FieldInfo(name = "备注", type = "nvarchar(100)  default ''", explain = "备注说明")
	@Column(name = "notes", columnDefinition = "nvarchar(100) default ''", nullable = true)
	private String notes;

	public Integer getMeasure() {
		return measure;
	}

	public void setMeasure(Integer measure) {
		this.measure = measure;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public SkMeter() {
		super();
	}

	public SkMeter(String id) {
		super(id);
	}

	
}