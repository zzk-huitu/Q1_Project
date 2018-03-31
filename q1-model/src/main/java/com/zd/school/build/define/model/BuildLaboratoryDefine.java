package com.zd.school.build.define.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: 实验室定义 Function: TODO ADD FUNCTION. Reason: TODO ADD REASON(可选).
 * Description: BUILD_T_LABORATORYDEFIN实体类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_LaboratoryDefine")
@AttributeOverride(name = "laboratoryId", column = @Column(name = "laboratoryId", length = 20, nullable = false))
public class BuildLaboratoryDefine extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "areaId", type = "varchar(20)", explain = "区域Id")
	@Column(name = "areaId", length = 20, nullable = false)
	private String areaId;

	@FieldInfo(name = "roomId", type = "varchar(20)", explain = "房间Id")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "isAllot", type = "varchar(20)", explain = "状态,用于标识是否分配：0未分配。1已分配")
	@Column(name = "isAllot", columnDefinition = "default 0", nullable = true)
	private Boolean isAllot;

	@FieldInfo(name = "laboratoryName", type = "varchar(20)", explain = "实验室名称")
	@Column(name = "laboratoryName", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String laboratoryName;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	@FieldInfo(name = "房间名称")
	private String roomName;

	public String getRoomName() {
		return roomName;
	}

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A WHERE A.areaId=areaId)")
	@FieldInfo(name = "楼层名称")
	private String areaName;

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A" + " WHERE A.areaId=(SELECT B.parentNode "
			+ " FROM dbo.T_PT_RoomArea B WHERE B.areaId=areaId))")
	@FieldInfo(name = "楼栋名称")
	private String upAreaName;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Boolean getIsAllot() {
		return isAllot;
	}

	public void setIsAllot(Boolean isAllot) {
		this.isAllot = isAllot;
	}

	public String getLaboratoryName() {
		return laboratoryName;
	}

	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}

	public String getUpAreaName() {
		return upAreaName;
	}

	public void setUpAreaName(String upAreaName) {
		this.upAreaName = upAreaName;
	}
}