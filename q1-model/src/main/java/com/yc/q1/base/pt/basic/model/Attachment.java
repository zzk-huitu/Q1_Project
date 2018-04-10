package com.yc.q1.base.pt.basic.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 公共附件信息表
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_Attachment")
// @Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AttributeOverride(name = "id", column = @Column(name = "attachmentId", length = 20, nullable = false) )
public class Attachment extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "实体名称", type = "varchar(36) NOT NULL", explain = "文件的实体名称")
	@Column(name = "entityName", length = 36, nullable = false)
	private String entityName;

	@FieldInfo(name = "记录ID", type = "varchar(20) NOT NULL", explain = "文件存放的实体类记录ID")
	@Column(name = "recordId", length = 20, nullable = false)
	private String recordId;

	@FieldInfo(name = "文件路径", type = "varchar(1024) NOT NULL", explain = "文件存放的路径")
	@Column(name = "fileUrl", length = 1024, nullable = false)
	private String fileUrl;

	@FieldInfo(name = "文件名称", type = "nvarchar(64) NOT NULL", explain = "文件的名称")
	@Column(name = "fileName", columnDefinition = "nvarchar(64)", nullable = false)
	private String fileName;

	@FieldInfo(name = "文件大小", type = "bigint NOT NULL", explain = "文件的大小")
	@Column(name = "fileSize", nullable = false)
	private Long fileSize;

	@FieldInfo(name = "文件类型", type = "varchar(20) NOT NULL", explain = "文件的类型")
	@Column(name = "fileType", length = 20, nullable = false)
	private String fileType;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Attachment() {
		super();
	}

	public Attachment(String id) {
		super(id);
	}

	/* 暂不需 */
	// @FieldInfo(name = "是否是正文文件",type="bit default 0",explain="是否是正文文件")
	// @Column(name = "fileIsMain", nullable = true,columnDefinition="default
	// 0")
	// private boolean fileIsMain;
	// public void setFileIsMain(boolean fileIsMain) {
	// this.fileIsMain = fileIsMain;
	// }
	// public boolean getFileIsMain() {
	// return fileIsMain;
	// }

}