package com.zd.school.oa.notice.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 公告类型
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_NoticeType")
@AttributeOverride(name = "id", column = @Column(name = "noticeTypeId", length = 20, nullable = false) )
public class OaNoticetype extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "类型名称", type = "nvarchar(10) NOT NULL", explain = "公告类型名称")
	@Column(name = "typeName", columnDefinition = "nvarchar(10)", nullable = false)
	private String typeName;

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	@Transient
	private String failed;
	@Transient
	private boolean success;

	public String getFailed() {
		return failed;
	}

	public void setFailed(String failed) {
		this.failed = failed;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public OaNoticetype() {
		super();
	}

	public OaNoticetype(String id) {
		super(id);
	}

}