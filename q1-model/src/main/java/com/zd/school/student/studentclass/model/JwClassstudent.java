package com.zd.school.student.studentclass.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.school.excel.annotation.MapperCell;

/**
 * 学生分班的信息
 * @author Administrator
 *
 */
@Entity
@Table(name = "T_PT_ClassStudent")
@AttributeOverride(name = "classStudentId", column = @Column(name = "classStudentId", length = 20, nullable = false) )
public class JwClassstudent extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "班级ID",type="varchar(20)",explain="学生分班后的年级班级Id")
	@Column(name = "gradeClassId", columnDefinition="varchar(20) defalut ''", nullable = true)
	private String gradeClassId;

	@FieldInfo(name = "学生ID",type="varchar(20)",explain="班级中的学生Id")
	@Column(name = "studentId", columnDefinition="varchar(20) defalut ''", nullable = true)
	private String studentId;

	@MapperCell(cellName="学年",order=2)
	@FieldInfo(name = "学年",type="nvarchar(10)",explain="当前所属学年")
	@Column(name = "schoolYear", columnDefinition="nvarchar(10)", nullable = false)
	private String schoolYear;

	
	@FieldInfo(name = "学期",type="nvarchar(8)",explain="当前所属学期")
	@Column(name = "semester", columnDefinition="nvarchar(8)", nullable = false)
	private String semester;

	@FieldInfo(name = "/*status*/",type="nvarchar(8)",explain="状态")
	@Column(name = "status", columnDefinition="nvarchar(8) defalut ''", nullable = true)
	private String status="0";

	@FieldInfo(name = "签到状态  1-签到 2请假 3旷课 4迟到",type="varchar(1)",explain="班级中的学生签到状态")
	@Column(name = "signInState", columnDefinition="varchar(1) defalut ''", nullable = true)
	private String signInState="3";
	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@MapperCell(cellName="所属班级",order=1)
	@FieldInfo(name = "所属班级名称",type="varchar(20)",explain="班级名称")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a WHERE a.gradeClassId=gradeClassId)")
	private String className;

	@FieldInfo(name = "年级编码",type="nvarchar",explain="当前班级的年级编码")
	@Formula("(SELECT B.gradeCode FROM T_PT_GradeClass A JOIN T_PT_Grade B "
			+ "ON A.gradeId=B.gradeId WHERE A.gradeClassId=gradeClassId)")
	private String gradeCode;

	@MapperCell(cellName="学号",order=5)
	@FieldInfo(name = "学号",type="nvarchar(18)",explain="班级中的学生学号")
	@Formula("(SELECT A.userNumb FROM T_PT_User A WHERE A.userId=studentId)")
	private String userNumb;

	@MapperCell(cellName="姓名",order=4)
	@FieldInfo(name = "姓名",type="nvarchar(36)",explain="班级中的学生姓名")
	@Formula("(SELECT A.name FROM dbo.T_PT_User A WHERE A.userId=studentId)")
	private String name;
	
	@FieldInfo(name = "性别码GB/T 2261.1",type="nvarchar(10)",explain="班级中的学生的性别码")
	@Formula("(SELECT A.genderCode FROM dbo.T_PT_User A WHERE A.userId=studentId)")
	private String genderCode;
	
	@Transient
	@MapperCell(cellName="学期",order=3)
	private String semesterEx;
	
	@Transient
	@MapperCell(cellName="性别",order=6)
	private String xbmEx;
	
	@Formula("(SELECT A.photo FROM T_PT_StudentBaseInfo A WHERE A.userId=studentId)")//USER_ID?
	@FieldInfo(name = "照片",type="nvarchar(200)",explain="班级中的学生个人照片")
	private String photo;
	
	@FieldInfo(name = "卡流水号",type="varchar(19)",explain="班级中的学生卡流水号")
	@Formula("(SELECT TOP 1 A.cardNo FROM T_PT_Card A WHERE A.userId=studentId AND A.cardStatusId=1)")
    private String cardNo;
	
	@FieldInfo(name = "物理卡号",type="Long",explain="班级中的学生的物理卡号")
	@Formula("(SELECT TOP 1 A.physicalNo FROM T_PT_Card A WHERE A.userId=studentId AND A.cardStatusId=1)")
	private String physicsNo;

	@Transient
	private boolean isLeaveed=false;
	
	
	public String getGradeClassId() {
		return gradeClassId;
	}

	public void setGradeClassId(String gradeClassId) {
		this.gradeClassId = gradeClassId;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhysicsNo() {
		return physicsNo;
	}

	public void setPhysicsNo(String physicsNo) {
		this.physicsNo = physicsNo;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}



	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}


	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}


	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	public String getSemesterEx() {
		return semesterEx;
	}

	public void setSemesterEx(String semesterEx) {
		this.semesterEx = semesterEx;
	}

	public String getXbmEx() {
		return xbmEx;
	}

	public void setXbmEx(String xbmEx) {
		this.xbmEx = xbmEx;
	}


	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	

	public String getSignInState() {
		return signInState;
	}

	public void setSignInState(String signInState) {
		this.signInState = signInState;
	}

	public boolean isLeaveed() {
		return isLeaveed;
	}

	public void setLeaveed(boolean isLeaveed) {
		this.isLeaveed = isLeaveed;
	}

}