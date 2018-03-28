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
@AttributeOverride(name = "teacherDormId", column = @Column(name = "teacherDormId", length = 36, nullable = false) )
public class DormTeacherDorm extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldInfo(name = "宿舍ID")
	@Column(name = "dormId", length = 36, nullable = false)
	private String dormId;
	
	@FieldInfo(name = "教师ID")
	@Column(name = "teacherId", length = 36, nullable = false)
	private String teacherId;
	
	@FieldInfo(name = "房间ID")
	@Column(name = "roomId", length = 36, nullable = false)
	private String roomId;
	
	@FieldInfo(name = "柜子编号")
	@Column(name = "sarkNo", length = 8, nullable = false)
	private Integer sarkNo=0;
	
	@FieldInfo(name = "床位编号")
	@Column(name = "bedNo", length = 8, nullable = false)
	private Integer bedNo=0;
	
	@FieldInfo(name = "入/退状态(0:入住,1:退住)")
	@Column(name = "inOutState", length = 1, nullable = false)
	private Integer inOutState=0;
	
	@FieldInfo(name = "入住时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "inTime", length = 27, nullable = false)
	private Date inTime;
	
	@FieldInfo(name = "退住时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "outTime", length = 27, nullable = true)
	private Date outTime;
	
	@Formula("(SELECT A.XM FROM SYS_T_USER A  WHERE A.USER_ID=TTEAC_ID)")
    @FieldInfo(name = "用于选择框显示教师姓名")
    private String xm;
	
	@Formula("(SELECT A.USER_NUMB FROM SYS_T_USER A  WHERE A.USER_ID=TTEAC_ID)")
    @FieldInfo(name = "教师工号")
    private String gh;
	
	@Formula("(SELECT B.ROOM_NAME FROM  dbo.BUILD_T_DORMDEFINE A JOIN dbo.BUILD_T_ROOMINFO B "
			+ "ON A.ROOM_ID=B.ROOM_ID WHERE  A.ISDELETE=0 AND A.DORM_ID=DORM_ID)")
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

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}
}
