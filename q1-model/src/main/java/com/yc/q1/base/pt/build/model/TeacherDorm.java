package com.yc.q1.base.pt.build.model;

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
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 教师宿舍
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_TeacherDorm")
@AttributeOverride(name = "id", column = @Column(name = "teacherDormId", length = 20, nullable = false) )
public class TeacherDorm extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "宿舍Id", type = "varchar(20) NOT NULL", explain = "宿舍Id")
	@Column(name = "dormId", length = 20, nullable = false)
	private String dormId;

	@FieldInfo(name = "教师ID", type = "varchar(20) NOT NULL", explain = "教师Id")
	@Column(name = "teacherId", length = 20, nullable = false)
	private String teacherId;

	@FieldInfo(name = "房间Id", type = "varchar(20) NOT NULL", explain = "房间Id")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "柜子编号", type = "tinyint NOT NULL", explain = "柜子编号")
	@Column(name = "sarkNo", columnDefinition = "tinyint", nullable = false)
	private Integer sarkNo;

	@FieldInfo(name = "床位编号", type = "tinyint NOT NULL", explain = "床位编号")
	@Column(name = "bedNo", columnDefinition = "tinyint", nullable = false)
	private Integer bedNo;

	@FieldInfo(name = "入/退状态", type = "varchar(1) NOT NULL", explain = "入/退状态(0:入住,1:退住)")
	@Column(name = "inOutState", length = 1, nullable = false)
	private String inOutState;

	@FieldInfo(name = "入住时间", type = "datetime NOT NULL", explain = "入住时间")
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
	//@FieldInfo(name = "用于选择框显示教师姓名")
	private String name;

	@Formula("(SELECT A.userNumb FROM T_PT_User A  WHERE A.userId=teacherId)")
	//@FieldInfo(name = "教师工号")
	private String userNumb;

	@Formula("(SELECT B.roomName FROM dbo.T_PT_DormDefine A JOIN dbo.T_PT_RoomInfo B "
			+ "ON A.roomId=B.roomId WHERE A.isDelete=0 AND A.dormId=dormId)")
	//@FieldInfo(name = "房间名称")
	private String dormName;

	public String getDormId() {
		return dormId;
	}

	public void setDormId(String dormId) {
		this.dormId = dormId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
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

	public String getInOutState() {
		return inOutState;
	}

	public void setInOutState(String inOutState) {
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

	public TeacherDorm() {
		super();
	}

	public TeacherDorm(String id) {
		super(id);
	}

}
