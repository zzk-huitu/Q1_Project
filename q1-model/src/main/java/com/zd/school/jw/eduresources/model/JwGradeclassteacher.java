package com.zd.school.jw.eduresources.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: JwGradeclassteacher Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 年级组长信息(JW_T_GRADECLASSTEACHER)实体类. date: 2016-09-20
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_GradeClassTeacher")
// @AttributeOverride(name = "uuid", column = @Column(name = "UUID", length =
// 36, nullable = false))
public class JwGradeclassteacher extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "gradeId", type = "varchar(20)", explain = "年级Id")
	@Column(name = "gradeId", length = 20, nullable = false)
	private String gradeId;

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	@FieldInfo(name = "teacherId", type = "varchar(20)", explain = "教职工Id")
	@Column(name = "teacherId", length = 20, nullable = false)
	private String teacherId;

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@FieldInfo(name = "studyYear", type = "nvarchar(20)", explain = "学年")
	@Column(name = "studyYear", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private Integer studyYear;

	public Integer getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(Integer studyYear) {
		this.studyYear = studyYear;
	}

	@FieldInfo(name = "studyYearName", type = "nvarchar(20)", explain = "学年名")
	@Column(name = "studyYearName", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String studyYearName;
	public String getStudyYearName() {
		return studyYearName;
	}

	public void setStudyYearName(String studyYearName) {
		this.studyYearName = studyYearName;
	}

	@FieldInfo(name = "semester", type = "nvarchar(20)", explain = "学期")
	@Column(name = "semester", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String semester;

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	@FieldInfo(name = "category", type = "Integer", explain = "身份 :0-正年级组长 1-副年级组长 2-班主任 3-副班主任")
	@Column(name = "category", nullable = false)
	private Integer category;

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getCategory() {
		return category;
	}

	@FieldInfo(name = "teacherType", type = "varchar(2)", explain = "教师类型 :0-年级组长 ;1-班主任")
	@Column(name = "teacherType", columnDefinition = "varchar(2),default '0'", nullable = true)
	private String teacherType;

	public String getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(String teacherType) {
		this.teacherType = teacherType;
	}

	public JwGradeclassteacher() {

		super();
		// TODO Auto-generated constructor stub

	}

	public JwGradeclassteacher(String uuid) {

		super(uuid);
		// TODO Auto-generated constructor stub

	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@FieldInfo(name = "班级名称")
	@Formula("(SELECT a.nodeText FROM T_PT_Department a WHERE a.deptId=gradeId)")
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@FieldInfo(name = "老师工号")
	@Formula("(SELECT a.userNumb FROM T_PT_User a WHERE a.userId=teacherId )")
	private String userNumb;

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	@FieldInfo(name = "老师姓名")
	@Formula("(SELECT a.name FROM T_PT_User a WHERE a.userId=teacherId )")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@FieldInfo(name = "老师性别")
	@Formula("(SELECT a.genderCode FROM T_PT_User a WHERE a.userId=teacherId )")
	private String genderCode;

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	@FieldInfo(name = "老师岗位")
	@Formula("(SELECT a.jobName FROM T_PT_User a WHERE a.userId=teacherId )")
	private String jobName;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}