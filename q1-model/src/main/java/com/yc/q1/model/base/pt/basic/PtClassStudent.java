package com.yc.q1.model.base.pt.basic;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 学生班级信息（此数据由 学生绑定班级部门为主部门时，自动创建此数据）
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_ClassStudent")
@AttributeOverride(name = "id", column = @Column(name = "classStudentId", length = 20, nullable = false) )
public class PtClassStudent extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "班级ID", type = "varchar(20) NOT NULL", explain = "学生分班后的班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	@FieldInfo(name = "学生ID", type = "varchar(20) NOT NULL", explain = "班级中的学生Id")
	@Column(name = "studentId", length = 20, nullable = false)
	private String studentId;

	@FieldInfo(name = "学年", type = "varchar(8)", explain = "当前所属学年")
	@Column(name = "studyYear", length = 8, nullable = true)
	private String studyYear;

	@FieldInfo(name = "学期", type = "varchar(8)", explain = "当前所属学期")
	@Column(name = "semester", length = 8, nullable = true)
	private String semester;

	// @FieldInfo(name = "所属班级名称",type="varchar(20)",explain="班级名称")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a WHERE a.classId=classId)")
	private String className;

	// @FieldInfo(name = "年级编码",type="nvarchar",explain="当前班级的年级编码")
	@Formula("(SELECT B.gradeCode FROM T_PT_GradeClass A JOIN T_PT_Grade B "
			+ "ON A.gradeId=B.gradeId WHERE A.classId=classId)")
	private String gradeCode;

	// @FieldInfo(name = "学号",type="nvarchar(18)",explain="班级中的学生学号")
	@Formula("(SELECT A.userNumb FROM T_PT_User A WHERE A.userId=studentId)")
	private String userNumb;

	// @FieldInfo(name = "姓名",type="nvarchar(16)",explain="班级中的学生姓名")
	@Formula("(SELECT A.name FROM dbo.T_PT_User A WHERE A.userId=studentId)")
	private String name;

	// @FieldInfo(name = "性别码GB/T
	// 2261.1",type="varchar(4)",explain="班级中的学生的性别码")
	@Formula("(SELECT A.sex FROM dbo.T_PT_User A WHERE A.userId=studentId)")
	private String sex;

	// @Formula("(SELECT A.photo FROM T_PT_StudentBaseInfo A WHERE
	// A.userId=studentId)")//USER_ID?
	@FieldInfo(name = "照片", type = "nvarchar(200)", explain = "班级中的学生个人照片")
	private String photo;

	// @FieldInfo(name = "卡流水号",type="varchar(19)",explain="班级中的学生卡流水号")
	@Formula("(SELECT TOP 1 A.cardNo FROM T_PT_Card A WHERE A.userId=studentId AND A.cardStatusId=1)")
	private String cardNo;

	// @FieldInfo(name = "物理卡号",type="Long",explain="班级中的学生的物理卡号")
	@Formula("(SELECT TOP 1 A.physicalNo FROM T_PT_Card A WHERE A.userId=studentId AND A.cardStatusId=1)")
	private String physicsNo;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(String studyYear) {
		this.studyYear = studyYear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPhysicsNo() {
		return physicsNo;
	}

	public void setPhysicsNo(String physicsNo) {
		this.physicsNo = physicsNo;
	}

	public PtClassStudent() {
		super();
	}

	public PtClassStudent(String id) {
		super(id);
	}

}