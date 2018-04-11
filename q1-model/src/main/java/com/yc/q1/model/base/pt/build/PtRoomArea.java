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
import com.zd.core.model.TreeNodeEntity;

/**
 * 房间区域
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_RoomArea")
@AttributeOverride(name = "id", column = @Column(name = "areaId", length = 20, nullable = false) )
public class PtRoomArea extends TreeNodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "区域类型", type = "varchar(4) NOT NULL", explain = "区域类型（01-学校 02-校区 03-楼栋 04-楼层）")
	@Column(name = "areaType", length = 4, nullable = false)
	private String areaType;

	@FieldInfo(name = "区域编码", type = "varchar(16) default ''", explain = "区域编码")
	@Column(name = "areaCode", columnDefinition = "varchar(16)  default ''", nullable = true)
	private String areaCode;

	@FieldInfo(name = "区域说明", type = "nvarchar(128) default ''", explain = "区域说明")
	@Column(name = "areaExplain", columnDefinition = "nvarchar(128) default ''", nullable = true)
	private String areaExplain;

	@FieldInfo(name = "区域地址", type = "nvarchar(128) default ''", explain = "区域地址")
	@Column(name = "areaAddress", columnDefinition = "nvarchar(128) default ''", nullable = true)
	private String areaAddress;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@FieldInfo(name = "区域房间数")
	// @Formula("(SELECT count(a.areaId) FROM T_PT_RoomInfo a WHERE
	// a.areaId=areaId AND a.isDelete=0)")
	private Integer roomCount;

	// @FieldInfo(name = "上级区域名称")
	@Formula("(SELECT isnull(a.nodeText,'ROOT') FROM T_PT_RoomArea a WHERE a.areaId=parentNode)")
	private String parentName;

	//@FieldInfo(name = "上级区域类型")
	@Transient
	private String parentType;

	
	
	
	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaExplain() {
		return areaExplain;
	}

	public void setAreaExplain(String areaExplain) {
		this.areaExplain = areaExplain;
	}

	public String getAreaAddress() {
		return areaAddress;
	}

	public void setAreaAddress(String areaAddress) {
		this.areaAddress = areaAddress;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public PtRoomArea() {

		super();
		// TODO Auto-generated constructor stub
	}

	public PtRoomArea(String id) {

		super(id);
		// TODO Auto-generated constructor stub

	}

}