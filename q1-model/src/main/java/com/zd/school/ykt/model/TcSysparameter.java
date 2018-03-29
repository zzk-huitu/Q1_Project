package com.zd.school.ykt.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 一卡通系统参数 ClassName: TcSysparameter Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: (TC_SysParameter)实体类. date: 2017-03-20
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_SysParameter")
@AttributeOverride(name = "sysParameterId", column = @Column(name = "sysParameterId", length = 20, nullable = false) )
public class TcSysparameter extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "sysParameterNo")
	@Column(name = "sysParameterNo", nullable = false)
	private Integer sysParameterNo;

	@FieldInfo(name = "sysParameterName")
	@Column(name = "sysParameterName", nullable = true, columnDefinition = "nvarchar(50) defalut ''")
	private String sysParameterName;

	@FieldInfo(name = "sysParameterValue")
	@Column(name = "sysParameterValue", nullable = true, columnDefinition = "nvarchar(50) defalut ''")
	private String sysParameterValue;

	@FieldInfo(name = "remark")
	@Column(name = "remark", columnDefinition = "nvarchar(50) defalut ''", nullable = true)
	private String remark;

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	@FieldInfo(name = "sysParameterType")
	@Column(name = "sysParameterType", columnDefinition = "nvarchar(50) defalut ''", nullable = true)
	private String sysParameterType;

	@FieldInfo(name = "parentId")
	@Column(name = "parentId",columnDefinition="defalut ''", nullable = true)
	private Integer parentId;

	public Integer getSysParameterNo() {
		return sysParameterNo;
	}

	public void setSysParameterNo(Integer sysParameterNo) {
		this.sysParameterNo = sysParameterNo;
	}

	public String getSysParameterName() {
		return sysParameterName;
	}

	public void setSysParameterName(String sysParameterName) {
		this.sysParameterName = sysParameterName;
	}

	public String getSysParameterValue() {
		return sysParameterValue;
	}

	public void setSysParameterValue(String sysParameterValue) {
		this.sysParameterValue = sysParameterValue;
	}

	public String getSysParameterType() {
		return sysParameterType;
	}

	public void setSysParameterType(String sysParameterType) {
		this.sysParameterType = sysParameterType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}