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
@AttributeOverride(name = "classStudentId", column = @Column(name = "classStudentId", length = 36, nullable = false) )
public class JwClassstudent extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "班级ID")
	@Column(name = "classId", length = 36, nullable = true)
	private String classId;

	@FieldInfo(name = "学生ID")
	@Column(name = "studentId", length = 36, nullable = true)
	private String studentId;

	@MapperCell(cellName="学年",order=2)
	@FieldInfo(name = "学年")
	@Column(name = "schoolYear", length = 10, nullable = false)
	private String schoolYear;

	
	@FieldInfo(name = "学期")
	@Column(name = "semester", length = 8, nullable = false)
	private String semester;

	@FieldInfo(name = "status")
	@Column(name = "STATUS", length = 8, nullable = true)
	private String status="0";

	@FieldInfo(name = "签到状态  1-签到 2请假 3旷课 4迟到")
	@Column(name = "signInState", length = 8, nullable = true)
	private String signInState="3";
	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@MapperCell(cellName="所属班级",order=1)
	@FieldInfo(name = "所属班级")
	@Formula("(SELECT a.CLASS_NAME FROM JW_T_GRADECLASS a WHERE a.CLAI_ID=CLAI_ID)")
	private String className;

	@FieldInfo(name = "学段标识")
	@Formula("(SELECT B.GRADE_CODE FROM dbo.JW_T_GRADECLASS A JOIN dbo.JW_T_GRADE B "
			+ "ON A.GRAI_ID=B.GRAI_ID WHERE A.CLAI_ID=CLAI_ID)")
	private String gradeCode;

	@MapperCell(cellName="学号",order=5)
	@FieldInfo(name = "学号")
	@Formula("(SELECT A.USER_NUMB FROM dbo.SYS_T_USER A WHERE A.USER_ID=STUDENT_ID)")
	private String userNumb;

	@MapperCell(cellName="姓名",order=4)
	@Formula("(SELECT A.XM FROM dbo.SYS_T_USER A WHERE A.USER_ID=STUDENT_ID)")
	@FieldInfo(name = "姓名")
	private String name;
	
	@FieldInfo(name = "性别码GB/T 2261.1")
	@Formula("(SELECT A.XBM FROM dbo.SYS_T_USER A WHERE A.USER_ID=STUDENT_ID)")
	private String genderCode;
	
	@Transient
	@MapperCell(cellName="学期",order=3)
	private String semesterEx;
	
	@Transient
	@MapperCell(cellName="性别",order=6)
	private String xbmEx;
	
	@Formula("(SELECT A.ZP FROM dbo.STU_T_BASEINFO A WHERE A.USER_ID=STUDENT_ID)")
	@FieldInfo(name = "照片")
	private String photo;
	
	@Formula("(SELECT TOP 1 A.CARDNO FROM dbo.PT_CARD A WHERE A.USER_ID=STUDENT_ID AND A.CARDSTATUSID=1)")
	@FieldInfo(name = "卡流水号")
	private String cardNo;
	
	@Formula("(SELECT TOP 1 A.FACTORYFIXID FROM dbo.PT_CARD A WHERE A.USER_ID=STUDENT_ID AND A.CARDSTATUSID=1)")
	@FieldInfo(name = "物理卡号")
	private String physicsNo;

	@Transient
	private boolean isLeaveed=false;
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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