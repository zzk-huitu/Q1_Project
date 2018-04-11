package com.yc.q1.model.base.pt.build;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 房间信息表
 * 
 * 注：houseNo01-05：旧版本使用备用字段1-5，作为房间的门牌号1-5
 * 
 * 注：删除areaStatu字段，目前而言没什么意义（roomType不为0，则代表已经分配）
 * 
 * @author ZZK
 */

@Entity
@Table(name = "T_PT_RoomInfo")
@AttributeOverride(name = "id", column = @Column(name = "roomId", length = 20, nullable = false) )
public class RoomInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "区域ID", type = "varchar(20) NOT NULL", explain = "区域ID")
	@Column(name = "areaId", length = 20, nullable = false)
	private String areaId;

	@FieldInfo(name = "房间编码", type = "varchar(16) NOT NULL", explain = "房间编码")
	@Column(name = "roomCode", length = 16, nullable = false)
	private String roomCode;

	@FieldInfo(name = "房间名称", type = "nvarchar(16) NOT NULL", explain = "房间名称")
	@Column(name = "roomName", columnDefinition = "nvarchar(16)", nullable = false)
	private String roomName;

	@FieldInfo(name = "房间类型", type = "varchar(4) NOT NULL", explain = "房间类型：宿舍-1，办公室-2，教室-3，功能室-5") // 参考数据字典表｛｝
	@Column(name = "roomType", length = 4, nullable = false)
	private String roomType;

	@FieldInfo(name = "是否多媒体教室", type = "bit default 0", explain = "是否多媒体教室：0-否,1-是")
	@Column(name = "isMediaRoom", columnDefinition = "bit default 0", nullable = true)
	private Boolean isMediaRoom;

	@FieldInfo(name = "网络状态", type = "bit default 0", explain = "网络状态：0-无网络，1-有网络")
	@Column(name = "roomNet", columnDefinition = "bit default 0", nullable = true)
	private Boolean roomNet;

	@FieldInfo(name = "教室说明", type = "nvarchar(128) default ''", explain = "教室说明")
	@Column(name = "roomExplain", columnDefinition = "nvarchar(128)  default ''", nullable = true)
	private String roomExplain;

	@FieldInfo(name = "房间电话", type = "varchar(16)  default ''", explain = "房间电话")
	@Column(name = "roomPhone", columnDefinition = "varchar(16)  default ''", nullable = true)
	private String roomPhone;

	@FieldInfo(name = "门牌号1", type = "varchar(16) default ''", explain = "门牌号1")
	@Column(name = "houseNo01", columnDefinition = "varchar(16)  default ''", nullable = true)
	private String houseNo01;

	@FieldInfo(name = "门牌号2", type = "varchar(16) default ''", explain = "门牌号2")
	@Column(name = "houseNo02", columnDefinition = "varchar(16)  default ''", nullable = true)
	private String houseNo02;

	@FieldInfo(name = "门牌号3", type = "varchar(16) default ''", explain = "门牌号1")
	@Column(name = "houseNo03", columnDefinition = "varchar(16)  default ''", nullable = true)
	private String houseNo03;

	@FieldInfo(name = "门牌号4", type = "varchar(16) default ''", explain = "门牌号4")
	@Column(name = "houseNo04", columnDefinition = "varchar(16)  default ''", nullable = true)
	private String houseNo04;

	@FieldInfo(name = "门牌号5", type = "varchar(16) default ''", explain = "门牌号5")
	@Column(name = "houseNo05", columnDefinition = "varchar(16)  default ''", nullable = true)
	private String houseNo05;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	// @FieldInfo(name = "区域名称")
	@Formula("(SELECT isnull(a.nodeText,'ROOT') FROM T_PT_RoomArea a WHERE a.areaId=areaId)")
	private String areaName;

	@Transient
	// @FieldInfo(name = "批量添加房间数量")
	private Integer roomCount;

	// @FieldInfo(name = "上级名称")
	@Formula("(SELECT R.nodeText  FROM dbo.T_PT_RoomArea R WHERE R.areaId="
			+ "(SELECT A.parentNode FROM dbo.T_PT_RoomArea A WHERE A.areaId=areaId))")
	private String areaUpName;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Boolean getIsMediaRoom() {
		return isMediaRoom;
	}

	public void setIsMediaRoom(Boolean isMediaRoom) {
		this.isMediaRoom = isMediaRoom;
	}

	public Boolean getRoomNet() {
		return roomNet;
	}

	public void setRoomNet(Boolean roomNet) {
		this.roomNet = roomNet;
	}

	public String getRoomExplain() {
		return roomExplain;
	}

	public void setRoomExplain(String roomExplain) {
		this.roomExplain = roomExplain;
	}

	public String getRoomPhone() {
		return roomPhone;
	}

	public void setRoomPhone(String roomPhone) {
		this.roomPhone = roomPhone;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public String getAreaUpName() {
		return areaUpName;
	}

	public void setAreaUpName(String areaUpName) {
		this.areaUpName = areaUpName;
	}

	public String getHouseNo01() {
		return houseNo01;
	}

	public void setHouseNo01(String houseNo01) {
		this.houseNo01 = houseNo01;
	}

	public String getHouseNo02() {
		return houseNo02;
	}

	public void setHouseNo02(String houseNo02) {
		this.houseNo02 = houseNo02;
	}

	public String getHouseNo03() {
		return houseNo03;
	}

	public void setHouseNo03(String houseNo03) {
		this.houseNo03 = houseNo03;
	}

	public String getHouseNo04() {
		return houseNo04;
	}

	public void setHouseNo04(String houseNo04) {
		this.houseNo04 = houseNo04;
	}

	public String getHouseNo05() {
		return houseNo05;
	}

	public void setHouseNo05(String houseNo05) {
		this.houseNo05 = houseNo05;
	}

	public RoomInfo() {
		super();
	}

	public RoomInfo(String id) {
		super(id);
	}

}