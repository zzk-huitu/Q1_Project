package com.zd.school.student.studentclass.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: StuDividescore 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 学生分班成绩实体类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_DivideScore")
@AttributeOverride(name = "divideScoreId", column = @Column(name = "divideScoreId", length = 20, nullable = false))
public class StuDividescore extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "记录ID")
    @Column(name = "divideRecodeId", columnDefinition="varchar(20) defalut ''", nullable = true)
    private String divideRecodeId;

    @FieldInfo(name = "学号")
    @Column(name = "studentNo", length = 20, nullable = false)
    private String studentNo;
        
    @FieldInfo(name = "姓名")
    @Column(name = "name", columnDefinition="nvarchar(36)", nullable = false)
    private String name;
        
    @FieldInfo(name = "性别码")
    @Column(name = "genderCode", columnDefinition="nvarchar(10)", nullable = true)
    private String genderCode;
        
    @FieldInfo(name = "成绩")
    @Column(name = "score", length = 8, nullable = false)
    private BigDecimal score;

    @FieldInfo(name = "考号")
    @Column(name = "examNo", length = 20, nullable = false)
    private String  examNo;
    
    @FieldInfo(name = "文理类型")
    @Column(name = "artsSciencesType", columnDefinition="nvarchar(16) defalut ''", nullable = true)
    private String artsSciencesType;


	public String getDivideRecodeId() {
		return divideRecodeId;
	}

	public void setDivideRecodeId(String divideRecodeId) {
		this.divideRecodeId = divideRecodeId;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
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

	public String getExamNo() {
		return examNo;
	}

	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}

	public String getArtsSciencesType() {
		return artsSciencesType;
	}

	public void setArtsSciencesType(String artsSciencesType) {
		this.artsSciencesType = artsSciencesType;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}



    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}