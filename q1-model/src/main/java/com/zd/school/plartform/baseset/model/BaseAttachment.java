package com.zd.school.plartform.baseset.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: BaseTAttachment Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 公共附件信息表实体类. date: 2016-07-06
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_AttachmentInfo")
//@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AttributeOverride(name = "attachmentInfoId", column = @Column(name = "attachmentInfoId", length = 20, nullable = false))
public class BaseAttachment extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "实体名称",type="nvarchar(36)",explain="文件的实体名称")
    @Column(name = "entityName", columnDefinition="nvarchar(36)", nullable = false)
    private String entityName;

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    @FieldInfo(name = "记录ID",type="varchar(20)",explain="文件存放的记录ID")
    @Column(name = "recordId", length = 20, nullable = false)
    private String recordId;

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordId() {
        return recordId;
    }

    @FieldInfo(name = "存放路径",type="varchar(128)",explain="文件存放的路径")
    @Column(name = "storeUrl", length = 128, nullable = false)
    private String storeUrl;

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    @FieldInfo(name = "文件名称",type="nvarchar(64)",explain="文件的名称")
    @Column(name = "fileName", columnDefinition="nvarchar(64)", nullable = false)
    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }
    
    @FieldInfo(name = "文件大小",type="Long",explain="文件的大小")
    @Column(name = "fileSize", nullable = false)
    private Long fileSize;
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    public Long getFileSize() {
        return fileSize;
    }
    
    @FieldInfo(name = "文件类型",type="nvarchar(20)",explain="文件的类型")
    @Column(name = "fileType", columnDefinition="nvarchar(20)", nullable = false)
    private String fileType;
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFileType() {
        return fileType;
    }
    
    @FieldInfo(name = "是否是正文文件",type="boolean",explain="是否是正文文件")
    @Column(name = "fileIsMain", nullable = true,columnDefinition="default 0")
    private boolean fileIsMain;
    public void setFileIsMain(boolean fileIsMain) {
        this.fileIsMain = fileIsMain;
    }
    public boolean getFileIsMain() {
        return fileIsMain;
    }
    
    /**
     * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
     * 
     * @Transient
     * @FieldInfo(name = "") private String field1;
     */
}