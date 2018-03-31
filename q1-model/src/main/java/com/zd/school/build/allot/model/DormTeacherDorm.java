package com.zd.school.build.allot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: DormStudentdorm Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: (教师分配宿舍)实体类. date: 2016-08-26
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_TeacherDorm")
@AttributeOverride(name = "teacherDormId", column = @Column(name = "teacherDormId", length = 20, nullable = false))
public class DormTeacherDorm extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "dormId", type = "varchar(20)", explain = "宿舍Id")
	@Column(name = "dormId", length = 20, nullable = false)
	private String dormId;

	@FieldInfo(name = "教师ID", type = "varchar(20)", explain = "教师Id")
	@Column(name = "teacherId", length = 20, nullable = false)
	private String teacherId;

	@FieldInfo(name = "", type = "varchar(20)", explain = "房间Id")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "sarkNo", type = "Byte", explain = "柜子编号")
	@Column(name = "sarkNo", nullable = false)
	private Byte sarkNo = 0;

	@FieldInfo(name = "bedNo", type = "Byte", explain = "床位编号")
	@Column(name = "bedNo", nullable = false)
	private Byte bedNo = 0;

	@FieldInfo(name = "inOutState", type = "int", explain = "入/退状态(0:入住,1:退住)")
	@Column(name = "inOutState", length = 1, nullable = false)
	private Integer inOutState = 0;

	@FieldInfo(name = "", type = "datetime", explain = "入住时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "inTime", columnDefinition = "datetime", nullable = false)
	private Date inTime;

	@FieldInfo(name = "outTime", type = "datetime", explain = "退住时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "outTime", columnDefinition = "datetime", nullable = true)
	private Date outTime;

	@Formula("(SELECT A.name FROM T_PT_User A  WHERE A.userId=teacherId)")
	@FieldInfo(name = "用于选择框显示教师姓名")
	private String name;

	@Formula("(SELECT A.userNumb FROM T_PT_User A  WHERE A.userId=teacherId)")
	@FieldInfo(name = "教师工号")
	private String userNumb;

	@Formula("(SELECT B.roomName FROM dbo.T_PT_DormDefine A JOIN dbo.T_PT_RoomInfo B "
			+ "ON A.roomId=B.roomId WHERE A.isDelete=0 AND A.dormId=dormId)")
	@FieldInfo(name = "房间名称")
	private String dormName;

	public String getDormId() {
		return dormId;
	}

	public void setDormId(String dormId) {
		this.dormId = dormId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Byte getSarkNo() {
		return sarkNo;
	}

	public void setSarkNo(Byte sarkNo) {
		this.sarkNo = sarkNo;
	}

	public Byte getBedNo() {
		return bedNo;
	}

	public void setBedNo(Byte bedNo) {
		this.bedNo = bedNo;
	}

	public Integer getInOutState() {
		return inOutState;
	}

	public void setInOutState(Integer inOutState) {
		this.inOutState = inOutState;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
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

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}
}
