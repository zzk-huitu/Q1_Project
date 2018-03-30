package com.zd.school.build.allot.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: JwClassdorm Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 班级分配宿舍(JW_T_CLASSDORM)实体类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_ClassDormAllot")
@AttributeOverride(name = "classDormId", column = @Column(name = "classDormId", length = 20, nullable = false))
public class JwClassDormAllot extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "dormId",type="varchar(20)",explain="宿舍Id")
	@Column(name = "dormId", length = 20, nullable = false)
	private String dormId;

	@FieldInfo(name = "classId",type="varchar(20)",explain="班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	@FieldInfo(name = "isMixed",type="Boolean",explain="是否是混班宿舍：0否-1是")
	@Column(name = "isMixed", columnDefinition = "default 0", nullable = true)
	private Boolean isMixed;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	@FieldInfo(name = "房间编码")
	@Formula("(SELECT B.roomName FROM dbo.T_PT_DormDefine A JOIN dbo.T_PT_RoomInfo B "
			+ "ON A.roomId=B.roomId WHERE A.isDelete=0 AND A.dormId=dormId)")
	private String dormCode;

	@FieldInfo(name = "房间电话")
	@Formula("(SELECT B.roomPhone FROM  dbo.T_PT_DormDefine A JOIN dbo.T_PT_RoomInfo B "
			+ "ON A.roomId=B.roomId WHERE  A.isDelete=0 AND A.dormId=dormId)")
	private String roomPhone;

	@Formula("(SELECT A.dormType FROM dbo.T_PT_DormDefine A  WHERE A.dormId=dormId AND A.isDelete=0)")
	@FieldInfo(name = "宿舍类型")
	private String dormType;

	// @Transient
	@FieldInfo(name = "已入住人数")
	@Formula("(select COUNT(*) from T_PT_StudentDorm A where A.dormId=dormId AND A.isDelete=0)")
	private String studentCount;

	@Formula("(SELECT A.bedCount FROM dbo.T_PT_DormDefine A WHERE A.dormId=dormId AND A.isDelete=0)")
	@FieldInfo(name = "床位数")
	private String dormBedCount;

	@FieldInfo(name = "班级名称")
	@Formula("(SELECT A.className FROM dbo.JW_T_GRADECLASS A WHERE A.classId=classId)")
	private String clainame;

	@Formula("(SELECT B.roomName FROM  dbo.T_PT_DormDefine A JOIN dbo.T_PT_RoomInfo B "
			+ "ON A.roomId=B.roomId WHERE  A.isDelete=0 AND A.dormId=dormId)")
	@FieldInfo(name = "宿舍名称")
	private String dormName;

	@Formula("(SELECT D.nodeText FROM dbo.T_PT_DormDefine B JOIN dbo.T_PT_RoomInfo C ON "
			+ "B.roomId=C.roomId JOIN dbo.T_PT_RoomArea D ON C.areaId=D.areaId WHERE b.dormId=dormId)")
	@FieldInfo(name = "所属楼栋")
	private String areaName;

	public String getDormType() {
		return dormType;
	}

	public void setDormType(String dormType) {
		this.dormType = dormType;
	}

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getDormId() {
		return dormId;
	}

	public void setDormId(String dormId) {
		this.dormId = dormId;
	}

	public String getClainame() {
		return clainame;
	}

	public void setClainame(String clainame) {
		this.clainame = clainame;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Boolean getIsMixed() {
		return isMixed;
	}

	public void setIsMixed(Boolean isMixed) {
		this.isMixed = isMixed;
	}

	public String getDormBedCount() {
		return dormBedCount;
	}

	public void setDormBedCount(String dormBedCount) {
		this.dormBedCount = dormBedCount;
	}

	public String getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(String studentCount) {
		this.studentCount = studentCount;
	}

	public String getDormCode() {
		return dormCode;
	}

	public void setDormCode(String dormCode) {
		this.dormCode = dormCode;
	}

	public String getRoomPhone() {
		return roomPhone;
	}

	public void setRoomPhone(String roomPhone) {
		this.roomPhone = roomPhone;
	}

}