package com.zd.school.plartform.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

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
@AttributeOverride(name = "appInfoId", column = @Column(name = "appInfoId", length = 36, nullable = false))
public class SysAppinfo extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "appIntro")
    @Column(name = "appIntro", length = 256, nullable = true)
    private String appIntro;
    public void setAppIntro(String appIntro) {
        this.appIntro = appIntro;
    }
    public String getAppIntro() {
        return appIntro;
    }
        
    @FieldInfo(name = "appIsuse")
    @Column(name = "appIsuse", length = 10, nullable = true)
    private Integer appIsuse;
    public void setAppIsuse(Integer appIsuse) {
        this.appIsuse = appIsuse;
    }
    public Integer getAppIsuse() {
        return appIsuse;
    }
        
    @FieldInfo(name = "appTitle")
    @Column(name = "appTitle", length = 128, nullable = true)
    private String appTitle;
    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }
    public String getAppTitle() {
        return appTitle;
    }
        
    @FieldInfo(name = "appType")
    @Column(name = "appType", length = 4, nullable = true)
    private String appType;
    public void setAppType(String appType) {
        this.appType = appType;
    }
    public String getAppType() {
        return appType;
    }
        
    @FieldInfo(name = "appUrl")
    @Column(name = "appUrl", length = 256, nullable = true)
    private String appUrl;
    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
    public String getAppUrl() {
        return appUrl;
    }
        
    @FieldInfo(name = "appVersion")
    @Column(name = "appVersion", length = 10, nullable = true)
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