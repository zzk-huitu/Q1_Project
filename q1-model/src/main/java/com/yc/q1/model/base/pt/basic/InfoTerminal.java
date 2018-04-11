package com.yc.q1.model.base.pt.basic;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 信息终端
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_InfoTerminal")
@AttributeOverride(name = "id", column = @Column(name = "terminalId", length = 20, nullable = false) )
public class InfoTerminal extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "终端号", type = "varchar(6) NOT NULL", explain = "信息终端的终端号")
	@Column(name = "terminalNo", length = 6, nullable = false)
	private String terminalNo;

	@FieldInfo(name = "终端类型", type = "varchar(10) NOT NULL", explain = "信息终端的终端类型，数据字典INFOTERTYPE")
	@Column(name = "terminalType", length = 10, nullable = false)
	private String terminalType;

	@FieldInfo(name = "规格", type = "nvarchar(32) DEFAULT ''", explain = "信息终端的终端规格")
	@Column(name = "terminalSpec", columnDefinition = "nvarchar(32) DEFAULT ''", nullable = true)
	private String terminalSpec;

	@FieldInfo(name = "使用状态", type = "bit NOT NULL", explain = "终端的使用状态，0-未使用 1-已使用")
	@Column(name = "isUse", nullable = false)
	private Boolean isUse;

	@FieldInfo(name = "使用房间ID", type = "varchar(20) DEFAULT ''", explain = "终端的使用房间ID")
	@Column(name = "roomId", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String roomId;

	@FieldInfo(name = "使用房间名称", type = "nvarchar(20) DEFAULT ''", explain = "终端的使用房间名称")
	// @Formula("(SELECT a.ROOM_NAME FROM BuildRoominfo a WHERE
	// a.ROOM_ID=ROOM_ID)")
	@Column(name = "roomName", columnDefinition = "nvarchar(20) DEFAULT ''", nullable = true)
	private String roomName;

	@FieldInfo(name = "使用房间名称门牌号", type = "nvarchar(20)  DEFAULT ''", explain = "终端的使用房间名称门牌号")
	@Column(name = "houseNo", columnDefinition = "nvarchar(20) DEFAULT ''", nullable = true)
	private String houseNo;

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getTerminalSpec() {
		return terminalSpec;
	}

	public void setTerminalSpec(String terminalSpec) {
		this.terminalSpec = terminalSpec;
	}

	public Boolean getIsUse() {
		return isUse;
	}

	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public InfoTerminal() {
		super();
	}

	public InfoTerminal(String id) {
		super(id);
	}
}