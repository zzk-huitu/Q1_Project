package com.yc.q1.model.base.pt.basic;

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
 * 校区信息
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_Campus")
@AttributeOverride(name = "id", column = @Column(name = "campusId", length = 20, nullable = false) )
public class PtCampus extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "学校主键", type = "varchar(20) NOT NULL", explain = "学校主键")
	@Column(name = "schoolId", length = 20, nullable = false)
	private String schoolId;

	@FieldInfo(name = "校区名称", type = "nvarchar(64) NOT NULL", explain = "校区的名称")
	@Column(name = "campusName", columnDefinition = "nvarchar(64)", nullable = false)
	private String campusName;

	@FieldInfo(name = "校区编码", type = "varchar(32) DEFAULT ''", explain = "校区的编码")
	@Column(name = "campusCode", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String campusCode;

	@FieldInfo(name = "校区地址", type = "nvarchar(180)  DEFAULT ''", explain = "校区的地址")
	@Column(name = "campusAddr", columnDefinition = "nvarchar(180) DEFAULT ''", nullable = true)
	private String campusAddr;

	@FieldInfo(name = "邮政编码", type = "varchar(10) DEFAULT ''", explain = "校区的邮政编码")
	@Column(name = "mailCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String mailCode;

	@FieldInfo(name = "校区联系电话", type = "varchar(20) DEFAULT ''", explain = "校区的联系电话")
	@Column(name = "campusPhone", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String campusPhone;

	@FieldInfo(name = "校区传真电话", type = "varchar(20)  DEFAULT ''", explain = "校区的传真电话")
	@Column(name = "campusFax", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String campusFax;

	@FieldInfo(name = "校区负责人工号", type = "varchar(20) DEFAULT ''", explain = "校区的负责人工号")
	@Column(name = "campusPrincipal", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String campusPrincipal;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	// @FieldInfo(name = "学校名称",type="nvarchar(64)",explain="学校名称")
	@Formula("(SELECT a.schoolName FROM T_PT_School a WHERE a.schoolId=schoolId )")
	private String schoolName;
	
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getCampusCode() {
		return campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public String getCampusAddr() {
		return campusAddr;
	}

	public void setCampusAddr(String campusAddr) {
		this.campusAddr = campusAddr;
	}

	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	public String getCampusPhone() {
		return campusPhone;
	}

	public void setCampusPhone(String campusPhone) {
		this.campusPhone = campusPhone;
	}

	public String getCampusFax() {
		return campusFax;
	}

	public void setCampusFax(String campusFax) {
		this.campusFax = campusFax;
	}

	public String getCampusPrincipal() {
		return campusPrincipal;
	}

	public void setCampusPrincipal(String campusPrincipal) {
		this.campusPrincipal = campusPrincipal;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public PtCampus() {
		super();
	}

	public PtCampus(String id) {
		super(id);
	}

}