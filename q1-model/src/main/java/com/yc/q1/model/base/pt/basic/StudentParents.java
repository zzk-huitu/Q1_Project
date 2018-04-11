package com.yc.q1.model.base.pt.basic;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * /** 学生家长信息
 */
@Entity
@Table(name = "T_PT_StudentParents")
@AttributeOverride(name = "uuid", column = @Column(name = "parentsId", length = 20, nullable = false) )
public class StudentParents extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "学生ID", type = "varchar(20) NOT NULL", explain = "学生id")
	@Column(name = "stuId", length = 20, nullable = false)
	private String stuId;

	@FieldInfo(name = "家长姓名", type = "nvarchar(10) NOT NULL", explain = "家长姓名")
	@Column(name = "name", columnDefinition = "nvarchar(10)", nullable = false)
	private String name;

	@FieldInfo(name = "家长的类型", type = "varchar(10) NOT NULL", explain = "家长的类型,与学生关系（字典码）")
	@Column(name = "parentsType", length = 10, nullable = false)
	private String parentsType;

	@FieldInfo(name = "性别码", type = "varchar(1) NOT NULL", explain = "性别码")
	@Column(name = "sex", length = 1, nullable = false)
	private String sex;

	@FieldInfo(name = "身份证件类型码", type = "varchar(10) default ''", explain = "身份证件类型码（字典码）")
	@Column(name = "identityType", columnDefinition = "varchar(10) default ''", nullable = true)
	private String identityType;

	@FieldInfo(name = "身份证件号", type = "varchar(18) default ''", explain = "身份证件号")
	@Column(name = "identityNo", columnDefinition = "varchar(18) default ''", nullable = true)
	private String identityNo;

	@FieldInfo(name = "电子邮箱", type = "varchar(128) default ''", explain = "电子邮箱")
	@Column(name = "email", columnDefinition = "varchar(128) default ''", nullable = true)
	private String email;

	@FieldInfo(name = "移动电话号码", type = "varchar(20) default ''", explain = "移动电话号码")
	@Column(name = "mobile",  columnDefinition = "varchar(20) default ''", nullable = true)
	private String mobile;

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentsType() {
		return parentsType;
	}

	public void setParentsType(String parentsType) {
		this.parentsType = parentsType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public StudentParents() {
		super();
	}

	public StudentParents(String id) {
		super(id);
	}

}