package com.yc.q1.model.base.pt.wisdomclass;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 特殊考勤人员
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_AttendUser")
@AttributeOverride(name = "id", column = @Column(name = "attendUserId", length = 20, nullable = false) )
public class AttendUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "考勤主题ID", type = "varchar(20) NOT NULL", explain = "考勤主题Id")
	@Column(name = "attendThemeId", length = 20, nullable = false)
	private String attendThemeId;

	@FieldInfo(name = "用户ID", type = "varchar(20) NOT NULL", explain = "用户Id")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	// @FieldInfo(name = "姓名",type="nvarchar(36)",explain="用户姓名")
	@Formula("(SELECT ISNULL(a.name,'') FROM T_PT_User a WHERE a.userId=userId)")
	private String name;

	// @FieldInfo(name = "学号",type="nvarchar(36)",explain="用户学号")
	@Formula("(SELECT ISNULL(a.userNumb,'') FROM T_PT_User a WHERE a.userId=userId)")
	private String userNumb;

	public String getAttendThemeId() {
		return attendThemeId;
	}

	public void setAttendThemeId(String attendThemeId) {
		this.attendThemeId = attendThemeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	public AttendUser() {
		super();
	}

	public AttendUser(String id) {
		super(id);
	}

}