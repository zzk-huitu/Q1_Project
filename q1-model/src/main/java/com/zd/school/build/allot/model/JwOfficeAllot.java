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
 * 办公室分配
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_OfficeAllot")
@AttributeOverride(name = "id", column = @Column(name = "officeId", length = 20, nullable = false) )
public class JwOfficeAllot extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "房间Id", type = "varchar(20) NOT NULL", explain = "房间Id")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "老师Id", type = "varchar(20) NOT NULL", explain = "老师Id")
	@Column(name = "teacherId", length = 20, nullable = false)
	private String teacherId;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@Formula("(SELECT A.name FROM T_PT_User A WHERE A.userId=teacherId)")
	// @FieldInfo(name = "用于选择框显示教师姓名")
	private String name;

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A "
			+ " WHERE A.areaId=(SELECT B.areaId FROM dbo.T_PT_RoomInfo B" + " WHERE B.roomId=roomId))")
	// @FieldInfo(name = "楼层名称")
	private String areaName;

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A" + " WHERE A.areaId=(SELECT B.parentNode"
			+ " FROM dbo.T_PT_RoomArea B WHERE B.areaId=" + " (SELECT C.areaId FROM dbo.T_PT_RoomInfo C"
			+ " WHERE C.roomId=roomId)))")
	// @FieldInfo(name = "楼栋名称")
	private String upAreaName;

	@Formula("(SELECT A.userNumb FROM T_PT_User A  WHERE A.userId=teacherId)")
	// @FieldInfo(name = "教师工号")
	private String userNumb;

	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间名")
	private String roomName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
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

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	public String getUpAreaName() {
		return upAreaName;
	}

	public void setUpAreaName(String upAreaName) {
		this.upAreaName = upAreaName;
	}

	public JwOfficeAllot() {
		super();
	}

	public JwOfficeAllot(String id) {
		super(id);
	}

}