package com.yc.q1.model.base.pt.wisdomclass;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 特殊考勤主题
 * @author ZZK
 *
 */
 
@Entity
@Table(name = "T_PT_AttendTheme")
@AttributeOverride(name = "id", column = @Column(name = "attendThemeId", length = 20, nullable = false))
public class PtAttendTheme extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
    
    @FieldInfo(name = "主题名称",type="nvarchar(16) NOT NULL",explain="考勤主题的主题名称")
    @Column(name = "themeName", columnDefinition="nvarchar(16)", nullable = false)
    private String themeName;

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public PtAttendTheme() {
		super();
	}

	public PtAttendTheme(String id) {
		super(id);
	}
}