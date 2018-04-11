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
 * 终端使用历史
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_InfoTerminalHistory")
@AttributeOverride(name = "id", column = @Column(name = "terminalHistoryId", length = 20, nullable = false) )
public class InfoTerminalHistory extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "信息终端主键ID", type = "varchar(20) NOT NULL", explain = "信息终端的主键ID")
	@Column(name = "terminalId", length = 20, nullable = false)
	private String terminalId;

	@FieldInfo(name = "终端号", type = "varchar(6) NOT NULL", explain = "信息终端的终端号")
	@Column(name = "terminalNo", length = 6, nullable = false)
	private String terminalNo;

	@FieldInfo(name = "使用房间ID", type = "varchar(20) DEFAULT ''", explain = "终端的使用房间ID")
	@Column(name = "roomId", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String roomId;

	@FieldInfo(name = "使用房间名称", type = "nvarchar(20)  DEFAULT ''", explain = "终端的使用房间名称")
	@Column(name = "roomName", columnDefinition = "nvarchar(20) DEFAULT ''", nullable = true)
	private String roomName;

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
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

	public InfoTerminalHistory() {
		super();
	}

	public InfoTerminalHistory(String id) {
		super(id);
	}
}