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
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_DormDefine")
@AttributeOverride(name = "id", column = @Column(name = "dormId", length = 20, nullable = false) )
public class DormDefine extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "房间Id", type = "varchar(20) NOT NULL", explain = "房间Id")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "区域Id", type = "varchar(20) NOT NULL", explain = "区域Id")
	@Column(name = "areaId", length = 20, nullable = false)
	private String areaId;

	@FieldInfo(name = "宿舍类型", type = "varchar(1) NOT NULL default '3'", explain = "宿舍类型:1男、2女、3不限")
	@Column(name = "dormType",  columnDefinition = "varchar(1) default '3'", nullable = false)
	private String dormType;

	@FieldInfo(name = "宿舍类别", type = "varchar(1) NOT NULL default '1'", explain = "宿舍类别:1学生宿舍、2教师宿舍")
	@Column(name = "dormCategory", columnDefinition = "varchar(1) default '1'", nullable = false)
	private String dormCategory;

	@FieldInfo(name = "床位数", type = "tinyint NOT NULL", explain = "床位数")
	@Column(name = "bedCount", columnDefinition = "tinyint", nullable = false)
	private Integer bedCount;

	@FieldInfo(name = "柜子数", type = "tinyint NOT NULL", explain = "柜子数")
	@Column(name = "sarkCount", columnDefinition = "tinyint", nullable = false)
	private Integer sarkCount;

	@FieldInfo(name = "宿舍管理员", type = "varchar(20) default ''", explain = "宿舍管理员(教师ID)")
	@Column(name = "dormAdminId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String dormAdminId;

	@FieldInfo(name = "宿舍管理员姓名", type = "nvarchar(10)  default ''", explain = "宿舍管理员姓名")
	@Column(name = "dormAdminName", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String dormAdminName;

	@FieldInfo(name = "宿舍电话", type = "varchar(16) default ''", explain = "电话")
	@Column(name = "dormPhone", columnDefinition = "varchar(16) default ''", nullable = true)
	private String dormPhone;

	@FieldInfo(name = "传真", type = "varchar(16) default ''", explain = "传真")
	@Column(name = "dormFax", columnDefinition = "varchar(16) default ''", nullable = true)
	private String dormFax;

	@FieldInfo(name = "分配状态", type = "bit default 0", explain = "状态,用于标识是否分配：0未分配。1已分配")
	@Column(name = "isAllot", columnDefinition = "bit default 0", nullable = true)
	private Boolean isAllot;

	@FieldInfo(name = "是否混班宿舍", type = "bit default 0", explain = "是否混班宿舍：0否,1是")
	@Column(name = "isMixed", columnDefinition = "bit default 0", nullable = true)
	private Boolean isMixed;
	
	/*使用下面的房间名称*/
//	@FieldInfo(name = "宿舍名称", type = "nvarchar(20)  default ''", explain = "宿舍名称")
//	@Column(name = "dormName", columnDefinition = "nvarchar(20) default ''", nullable = true)
//	private String dormName;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 8
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间名称")
	private String roomName;

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A WHERE A.areaId=areaId)")
	// @FieldInfo(name = "楼层名称")
	private String areaName;

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A" + " WHERE A.areaId=(SELECT B.parentNode "
			+ " FROM dbo.T_PT_RoomArea B WHERE B.areaId=areaId))")
	// @FieldInfo(name = "楼栋名称")
	private String upAreaName;

	@Transient
	// @FieldInfo(name = "用于教师id标识")
	private String teacherId;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getDormType() {
		return dormType;
	}

	public void setDormType(String dormType) {
		this.dormType = dormType;
	}

	public String getDormCategory() {
		return dormCategory;
	}

	public void setDormCategory(String dormCategory) {
		this.dormCategory = dormCategory;
	}

	public Integer getBedCount() {
		return bedCount;
	}

	public void setBedCount(Integer bedCount) {
		this.bedCount = bedCount;
	}

	public Integer getSarkCount() {
		return sarkCount;
	}

	public void setSarkCount(Integer sarkCount) {
		this.sarkCount = sarkCount;
	}

	public String getDormAdminId() {
		return dormAdminId;
	}

	public void setDormAdminId(String dormAdminId) {
		this.dormAdminId = dormAdminId;
	}

	public String getDormAdminName() {
		return dormAdminName;
	}

	public void setDormAdminName(String dormAdminName) {
		this.dormAdminName = dormAdminName;
	}

	public String getDormPhone() {
		return dormPhone;
	}

	public void setDormPhone(String dormPhone) {
		this.dormPhone = dormPhone;
	}

	public String getDormFax() {
		return dormFax;
	}

	public void setDormFax(String dormFax) {
		this.dormFax = dormFax;
	}

	public Boolean getIsAllot() {
		return isAllot;
	}

	public void setIsAllot(Boolean isAllot) {
		this.isAllot = isAllot;
	}

	public Boolean getIsMixed() {
		return isMixed;
	}

	public void setIsMixed(Boolean isMixed) {
		this.isMixed = isMixed;
	}

	

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

	public String getUpAreaName() {
		return upAreaName;
	}

	public void setUpAreaName(String upAreaName) {
		this.upAreaName = upAreaName;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public DormDefine() {
		super();
	}

	public DormDefine(String id) {
		super(id);
	}

}
