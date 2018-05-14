package com.yc.q1.model.base.pt.system;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeDeserializer;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 系统参数表（将UP6中的上下级关系的数据，统一迁移到数据字典中）
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_SysParameter")
@AttributeOverride(name = "id", column = @Column(name = "sysParamId", length = 20, nullable = false) )
public class PtSysParameter extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "参数编码", explain = "参数英文唯一编码")
	@Column(name = "sysParamCode", length = 20, nullable = false)
	private String sysParamCode;

	@FieldInfo(name = "参数名称", explain = "参数名称")
	@Column(name = "sysParamName", columnDefinition = "nvarchar(50)", nullable = false)
	private String sysParamName;

	@FieldInfo(name = "参数值", explain = "参数值")
	@Column(name = "sysParamValue", columnDefinition = "nvarchar(100)", nullable = false)
	private String sysParamValue;

	@FieldInfo(name = "参数备注", explain = "参数备注")
	@Column(name = "sysParamRemark", columnDefinition = "nvarchar(100) DEFAULT ''", nullable = true)
	private String sysParamRemark;


	public String getSysParamCode() {
		return sysParamCode;
	}

	public void setSysParamCode(String sysParamCode) {
		this.sysParamCode = sysParamCode;
	}

	public String getSysParamName() {
		return sysParamName;
	}

	public void setSysParamName(String sysParamName) {
		this.sysParamName = sysParamName;
	}

	public String getSysParamValue() {
		return sysParamValue;
	}

	public void setSysParamValue(String sysParamValue) {
		this.sysParamValue = sysParamValue;
	}

	public String getSysParamRemark() {
		return sysParamRemark;
	}

	public void setSysParamRemark(String sysParamRemark) {
		this.sysParamRemark = sysParamRemark;
	}

	public PtSysParameter() {
		super();
	}

	public PtSysParameter(String id) {
		super(id);
	}

}