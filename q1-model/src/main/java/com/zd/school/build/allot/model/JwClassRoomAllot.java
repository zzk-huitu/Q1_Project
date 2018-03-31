package com.zd.school.build.allot.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * ####已废弃
 * ClassName: JwClassroomallot Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 班级分配教室实体类. date: 2016-08-23
 * 
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_ClassRoomAllot")
@AttributeOverride(name = "classRoomId", column = @Column(name = "classRoomId", length = 20, nullable = false) )
public class JwClassRoomAllot extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "classId",type="varchar(20)",explain="班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	@FieldInfo(name = "roomId",type="varchar(20)",explain="房间Id")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	@FieldInfo(name = "房间名称")
	private String roomName;

	@Formula("(SELECT B.nodeText FROM dbo.T_PT_RoomInfo A"
			+ " JOIN dbo.T_PT_RoomArea B ON A.areaId=B.areaId"
			+ " WHERE A.roomId=roomId AND A.isDelete=0)")
	@FieldInfo(name = "楼层名称")
	private String areaName;

	@Formula("(SELECT A.className FROM dbo.T_PT_GradeClass A WHERE A.classId=classId)")
	@FieldInfo(name = "班级名称")
	private String className;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
}