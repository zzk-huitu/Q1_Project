package com.zd.school.plartform.system.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: SysAppinfo 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: (SYS_T_APPINFO)实体类.
 * date: 2017-05-15
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_AppInfo")
@AttributeOverride(name = "appInfoId", column = @Column(name = "appInfoId", length = 20, nullable = false))
public class SysAppinfo extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "app描述",type="nvarchar(256)",explain="app描述")
    @Column(name = "appIntro",columnDefinition="nvarchar(256) defalut ''", nullable = true)
    private String appIntro;
    public void setAppIntro(String appIntro) {
        this.appIntro = appIntro;
    }
    public String getAppIntro() {
        return appIntro;
    }
        
    @FieldInfo(name = "app是否启用",type="boolean",explain="app是否启用")
    @Column(name = "appIsuse",columnDefinition="defalut 0", nullable = true)
    private boolean appIsuse;
    public void setAppIsuse(boolean appIsuse) {
        this.appIsuse = appIsuse;
    }
    public boolean getAppIsuse() {
        return appIsuse;
    }
        
    @FieldInfo(name = "app名称",type="nvarchar(128)",explain="app标题名称")
    @Column(name = "appTitle", columnDefinition="nvarchar(128) defalut ''", nullable = true)
    private String appTitle;
    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }
    public String getAppTitle() {
        return appTitle;
    }
        
    @FieldInfo(name = "app类型",type="nvarchar(4)",explain="app类型")
    @Column(name = "appType",columnDefinition="nvarchar(4) defalut ''", nullable = true)
    private String appType;
    public void setAppType(String appType) {
        this.appType = appType;
    }
    public String getAppType() {
        return appType;
    }
        
    @FieldInfo(name = "app上传路径",type="nvarchar(256)",explain="app上传路径")
    @Column(name = "appUrl",columnDefinition="nvarchar(256) defalut ''", nullable = true)
    private String appUrl;
    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
    public String getAppUrl() {
        return appUrl;
    }
        
    @FieldInfo(name = "app版本号",type="Integer",explain="app版本号")
    @Column(name = "appVersion",columnDefinition="defalut 0", nullable = true)
    private Integer appVersion;
    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }
    public Integer getAppVersion() {
        return appVersion;
    }
        

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}