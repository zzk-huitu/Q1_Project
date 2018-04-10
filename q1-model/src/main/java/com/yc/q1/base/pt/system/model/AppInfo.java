package com.yc.q1.base.pt.system.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * App信息表
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_AppInfo")
@AttributeOverride(name = "id", column = @Column(name = "appInfoId", length = 20, nullable = false) )
public class AppInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "app是否启用", type = "bit NOT NULL DEFAULT 0", explain = "app是否启用")
	@Column(name = "appIsuse", columnDefinition = "bit DEFAULT 0", nullable = false)
	private Boolean appIsuse;

	@FieldInfo(name = "app名称", type = "nvarchar(16) NOT NULL", explain = "app标题名称")
	@Column(name = "appTitle", columnDefinition = "nvarchar(16)", nullable = false)
	private String appTitle;

	@FieldInfo(name = "app类型", type = "varchar(4) NOT NULL", explain = "app类型（数据字典）")
	@Column(name = "appType", columnDefinition = "varchar(4)", nullable = false)
	private String appType;

	@FieldInfo(name = "app上传路径", type = "varchar(256) NOT NULL", explain = "app上传的文件路径地址")
	@Column(name = "appUrl", columnDefinition = "varchar(256)", nullable = false)
	private String appUrl;

	@FieldInfo(name = "app版本号", type = "int NOT NULL", explain = "app版本号")
	@Column(name = "appVersion", columnDefinition = "int DEFAULT 0", nullable = false)
	private Integer appVersion;

	@FieldInfo(name = "app描述", type = "nvarchar(128) DEFAULT ''", explain = "app描述")
	@Column(name = "appIntro", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
	private String appIntro;

	public Boolean getAppIsuse() {
		return appIsuse;
	}

	public void setAppIsuse(Boolean appIsuse) {
		this.appIsuse = appIsuse;
	}

	public String getAppTitle() {
		return appTitle;
	}

	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppIntro() {
		return appIntro;
	}

	public void setAppIntro(String appIntro) {
		this.appIntro = appIntro;
	}

	public AppInfo() {
		super();
	}

	public AppInfo(String id) {
		super(id);
	}
}