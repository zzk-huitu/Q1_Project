package com.yc.q1.model.base.pt.build;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 学生宿舍
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_StudentDorm")
@AttributeOverride(name = "id", column = @Column(name = "studentDormId", length = 20, nullable = false) )
public class StudentDorm extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "班级宿舍Id", type = "varchar(20) NOT NULL", explain = "班级宿舍Id")
	@Column(name = "classDormId", length = 20, nullable = false)
	private String classDormId;

	@FieldInfo(name = "学生Id", type = "varchar(20) NOT NULL", explain = "学生Id")
	@Column(name = "studentId", length = 20, nullable = false)
	private String studentId;

	@FieldInfo(name = "柜子编号", type = "tinyint NOT NULL", explain = "柜子编号")
	@Column(name = "sarkNo", columnDefinition = "tinyint", nullable = false)
	private Integer sarkNo;

	@FieldInfo(name = "床位编号", type = "tinyint NOT NULL", explain = "床位编号")
	@Column(name = "bedNo", columnDefinition = "tinyint", nullable = false)
	private Integer bedNo;

	@FieldInfo(name = "inTime", type = "datetime NOT NULL", explain = "入住时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "inTime", columnDefinition = "datetime", nullable = false)
	private Date inTime;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	// @FieldInfo(name = "班级ID")
	@Formula("(SELECT a.classId FROM V_PT_ClassStudent a where a.userId=studentId)")
	private String classId;

	@Formula("(SELECT C.roomName FROM dbo.T_PT_ClassDormAllot A" + " JOIN dbo.T_PT_DormDefine B ON A.dormId=B.dormId"
			+ " JOIN dbo.T_PT_RoomInfo C ON C.roomId = B.roomId"
			+ " WHERE A.classDormId =classDormId AND A.isDelete=0)")
	// @FieldInfo(name = "房间名称")
	private String roomName;

	@Formula("(SELECT a.className FROM V_PT_ClassStudent a where a.userId=studentId)")
	// @FieldInfo(name = "班级名称")
	private String className;

	// @FieldInfo(name = "学号")
	@Formula("(SELECT A.userNumb FROM dbo.T_PT_User A WHERE A.userId=studentId)")
	private String userNumb;

	@Formula("(SELECT A.name FROM dbo.T_PT_User A WHERE A.userId=studentId)")
	// @FieldInfo(name = "姓名")
	private String xm;

	// @FieldInfo(name = "性别码GB/T 2261.1")
	@Formula("(SELECT A.sex FROM dbo.T_PT_User A WHERE A.userId=studentId)")
	private String sex;

	@Formula("(SELECT A.isMixed FROM T_PT_ClassDormAllot A WHERE A.classDormId=classDormId)")
	// @FieldInfo(name = "混合宿舍")
	private String isMixed;

	@Transient
	// @FieldInfo(name = "学生人数")
	private Integer studentCount;
	@Transient
	// @FieldInfo(name = "有效宿舍")
	private Integer validDorm;

	@Transient
	// @FieldInfo(name = "有效男宿舍")
	private Integer maleDorm;
	@Transient
	// @FieldInfo(name = "有效女宿舍")
	private Integer femaleDorm;
	@Transient
	// @FieldInfo(name = "有效混班宿舍")
	private Integer mixedDorm;

	@Transient
	// @FieldInfo(name = "所需宿舍床位")
	private Integer needDormBed;

	@Transient
	// @FieldInfo(name = "男生所需床位")
	private Integer maleNeedBed;
	@Transient
	// @FieldInfo(name = "女生所需床位")
	private Integer femaleNeedBed;

	@Transient
	// @FieldInfo(name = "男生数量")
	private Integer maleCount;
	@Transient
	// @FieldInfo(name = "女生数量")
	private Integer femaleCount;

	@Transient
	// @FieldInfo(name = "所属楼层")
	private String areaName;

	@Transient
	// @FieldInfo(name = "所属楼栋")
	private String upAreaName;

	public String getClassDormId() {
		return classDormId;
	}

	public void setClassDormId(String classDormId) {
		this.classDormId = classDormId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Integer getSarkNo() {
		return sarkNo;
	}

	public void setSarkNo(Integer sarkNo) {
		this.sarkNo = sarkNo;
	}

	public Integer getBedNo() {
		return bedNo;
	}

	public void setBedNo(Integer bedNo) {
		this.bedNo = bedNo;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
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

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIsMixed() {
		return isMixed;
	}

	public void setIsMixed(String isMixed) {
		this.isMixed = isMixed;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public Integer getValidDorm() {
		return validDorm;
	}

	public void setValidDorm(Integer validDorm) {
		this.validDorm = validDorm;
	}

	public Integer getMaleDorm() {
		return maleDorm;
	}

	public void setMaleDorm(Integer maleDorm) {
		this.maleDorm = maleDorm;
	}

	public Integer getFemaleDorm() {
		return femaleDorm;
	}

	public void setFemaleDorm(Integer femaleDorm) {
		this.femaleDorm = femaleDorm;
	}

	public Integer getMixedDorm() {
		return mixedDorm;
	}

	public void setMixedDorm(Integer mixedDorm) {
		this.mixedDorm = mixedDorm;
	}

	public Integer getNeedDormBed() {
		return needDormBed;
	}

	public void setNeedDormBed(Integer needDormBed) {
		this.needDormBed = needDormBed;
	}

	public Integer getMaleNeedBed() {
		return maleNeedBed;
	}

	public void setMaleNeedBed(Integer maleNeedBed) {
		this.maleNeedBed = maleNeedBed;
	}

	public Integer getFemaleNeedBed() {
		return femaleNeedBed;
	}

	public void setFemaleNeedBed(Integer femaleNeedBed) {
		this.femaleNeedBed = femaleNeedBed;
	}

	public Integer getMaleCount() {
		return maleCount;
	}

	public void setMaleCount(Integer maleCount) {
		this.maleCount = maleCount;
	}

	public Integer getFemaleCount() {
		return femaleCount;
	}

	public void setFemaleCount(Integer femaleCount) {
		this.femaleCount = femaleCount;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUpAreaName() {
		return upAreaName;
	}

	public void setUpAreaName(String upAreaName) {
		this.upAreaName = upAreaName;
	}

	public StudentDorm() {
		super();
	}

	public StudentDorm(String id) {
		super(id);
	}

}