package com.yc.q1.base.pt.system.model;

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
public class Job extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "岗位名称", type = "nvarchar(16) not null", explain = "岗位名称")
	@Column(name = "jobName", columnDefinition = "nvarchar(16)", nullable = false)
	private String jobName;

	@FieldInfo(name = "岗位编码", type = "nvarchar(16) DEFAULT ''", explain = "岗位的编码")
	@Column(name = "jobCode", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String jobCode;
	

	@FieldInfo(name = "备注", type = "nvarchar(128)  DEFAULT ''", explain = "岗位的备注")
	@Column(name = "remark", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
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

	public Job() {
		super();
	}

	public Job(String id) {
		super(id);
	}

}