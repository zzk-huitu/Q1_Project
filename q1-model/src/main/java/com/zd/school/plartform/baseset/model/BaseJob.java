package com.zd.school.plartform.baseset.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 岗位信息
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_Job")
@AttributeOverride(name = "id", column = @Column(name = "jobId", length = 20, nullable = false) )
public class BaseJob extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "岗位名称", type = "nvarchar(16) not null", explain = "岗位名称")
	@Column(name = "jobName", columnDefinition = "nvarchar(16)", nullable = false)
	private String jobName;

	@FieldInfo(name = "岗位编码", type = "varchar(16) defalut ''", explain = "岗位的编码")
	@Column(name = "jobCode", columnDefinition = "nvarchar(16) defalut ''", nullable = true)
	private String jobCode;
	

	@FieldInfo(name = "备注", type = "nvarchar(128)  defalut ''", explain = "岗位的备注")
	@Column(name = "remark", columnDefinition = "nvarchar(128) defalut ''", nullable = true)
	private String remark;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BaseJob() {
		super();
	}

	public BaseJob(String id) {
		super(id);
	}

}