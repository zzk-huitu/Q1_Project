package com.zd.school.oa.attendance.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 特殊考勤主题
 * @author ZZK
 *
 */
 
@Entity
@Table(name = "T_PT_AttendTheme")
@AttributeOverride(name = "id", column = @Column(name = "attendThemeId", length = 20, nullable = false))
public class AttTitle extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "主题名称",type="nvarchar(16) NOT NULL",explain="考勤主题的主题名称")
    @Column(name = "themeName", columnDefinition="nvarchar(16)", nullable = false)
    private String themeName;

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public AttTitle() {
		super();
	}

	public AttTitle(String id) {
		super(id);
	}
}