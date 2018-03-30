package com.zd.school.build.define.model;

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
 * 宿舍定义
 * 
 * @ClassName: BuildDormDefine
 * @Description: TODO
 * @author: hucy
 * @date: 2016年4月20日 下午3:45:12
 *
 */
@Entity
@Table(name = "T_PT_DormDefine")
@AttributeOverride(name = "dormId", column = @Column(name = "dormId", length = 20, nullable = false))
public class BuildDormDefine extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "roomId", type = "varchar(20)", explain = "房间Id")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@FieldInfo(name = "areaId", type = "varchar(20)", explain = "区域Id")
	@Column(name = "areaId", length = 20, nullable = false)
	private String areaId;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@FieldInfo(name = "dormType", type = "varchar(2)", explain = "宿舍类型:1男、2女、3不限")
	@Column(name = "dormType", columnDefinition = "varchar(2) default '3'", nullable = true)
	private String dormType;

	public String getDormType() {
		return dormType;
	}

	public void setDormType(String dormType) {
		this.dormType = dormType;
	}

	@FieldInfo(name = "dormTypeLb", type = "varchar(2)", explain = "宿舍类别:1学生宿舍、2教师宿舍")
	@Column(name = "dormTypeLb", columnDefinition = "varchar(2) default ''", nullable = true)
	private String dormTypeLb;

	public String getDormTypeLb() {
		return dormTypeLb;
	}

	public void setDormTypeLb(String dormTypeLb) {
		this.dormTypeLb = dormTypeLb;
	}

	@FieldInfo(name = "bedCount", type = "Byte", explain = "床位数")
	@Column(name = "bedCount", nullable = false)
	private Byte bedCount;

	public Byte getBedCount() {
		return bedCount;
	}

	public void setBedCount(Byte bedCount) {
		this.bedCount = bedCount;
	}

	@FieldInfo(name = "sarkCount", type = "Byte", explain = "柜子数")
	@Column(name = "sarkCount", nullable = false)
	private Byte sarkCount;

	public Byte getSarkCount() {
		return sarkCount;
	}

	public void setSarkCount(Byte sarkCount) {
		this.sarkCount = sarkCount;
	}

	@FieldInfo(name = "dormAdminId", type = "varchar(20)", explain = "宿舍管理员(教师ID)")
	@Column(name = "dormAdminId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String dormAdminId;

	public String getDormAdminId() {
		return dormAdminId;
	}

	public void setDormAdminId(String dormAdminId) {
		this.dormAdminId = dormAdminId;
	}

	@FieldInfo(name = "dormAdminName", type = "nvarchar(10)", explain = "宿舍管理员姓名")
	@Column(name = "dormAdminName", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String dormAdminName;

	public String getDormAdminName() {
		return dormAdminName;
	}

	public void setDormAdminName(String dormAdminName) {
		this.dormAdminName = dormAdminName;
	}

	@FieldInfo(name = "电话", type = "varchar(20)", explain = "区域Id")
	@Column(name = "dormPhone", columnDefinition = "varchar(11) default ''", nullable = true)
	private String dormPhone;

	public String getDormPhone() {
		return dormPhone;
	}

	public void setDormPhone(String dormPhone) {
		this.dormPhone = dormPhone;
	}

	@FieldInfo(name = "dormFax", type = "varchar(20)", explain = "传真")
	@Column(name = "dormFax", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String dormFax;

	public void setDormFax(String dormFax) {
		this.dormFax = dormFax;
	}

	public String getDormFax() {
		return dormFax;
	}

	@FieldInfo(name = "isAllot", type = "Boolean", explain = "状态,用于标识是否分配：0未分配。1已分配")
	@Column(name = "isAllot", columnDefinition = "default 0", nullable = true)
	private Boolean isAllot;

	public Boolean getIsAllot() {
		return isAllot;
	}

	public void setIsAllot(Boolean isAllot) {
		this.isAllot = isAllot;
	}

	@FieldInfo(name = "isMixed", type = "Boolean", explain = "是否混班宿舍：0否,1是")
	@Column(name = "isMixed", columnDefinition = "default 0", nullable = true)
	private Boolean isMixed;

	public Boolean getIsMixed() {
		return isMixed;
	}

	public void setIsMixed(Boolean isMixed) {
		this.isMixed = isMixed;
	}

	@FieldInfo(name = "dormName", type = "varchar(20)", explain = "宿舍名称")
	@Column(name = "dormName", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String dormName;

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 8
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

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A WHERE A.areaId=areaId)")
	@FieldInfo(name = "楼层名称")
	private String areaName;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A" + " WHERE A.areaId=(SELECT B.parentNode "
			+ " FROM dbo.T_PT_RoomArea B WHERE B.areaId=areaId))")
	@FieldInfo(name = "楼栋名称")
	private String upAreaName;

	public String getUpAreaName() {
		return upAreaName;
	}

	public void setUpAreaName(String upAreaName) {
		this.upAreaName = upAreaName;
	}

	@Transient
	@FieldInfo(name = "用于教师id标识")
	private String teacherId;

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

}
