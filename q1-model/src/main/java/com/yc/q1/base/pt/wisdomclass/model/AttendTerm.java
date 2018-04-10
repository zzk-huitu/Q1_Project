package com.yc.q1.base.pt.wisdomclass.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 考勤设备（特殊考勤）
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_AttendTerm")
@AttributeOverride(name = "id", column = @Column(name = "attendTermId", length = 20, nullable = false) )
public class AttendTerm extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "考勤主题ID", type = "varchar(20) NOT NULL", explain = "考勤主题Id")
	@Column(name = "attendThemeId", length = 20, nullable = false)
	private String attendThemeId;

	// @FieldInfo(name = "终端号",type="varchar(6)",explain="信息终端的终端号")
	@Formula("(SELECT ISNULL(a.terminalNo,'') FROM T_PT_InfoTerminal a WHERE a.terminalId=attendTermId)")
	private String terminalNo;

	// @FieldInfo(name = "房间ID",type="varchar(20)",explain="信息终端的使用房间Id")
	@Formula("(SELECT ISNULL(a.roomId,'') FROM T_PT_InfoTerminal a WHERE a.terminalId=attendTermId)")
	private String roomId;

	// @FieldInfo(name = "房间名称",type="nvarchar(64)",explain="信息终端的使用房间名称")
	@Formula("(SELECT ISNULL(a.roomName,'') FROM T_PT_InfoTerminal a WHERE a.terminalId=attendTermId)")
	private String roomName;

	public String getAttendThemeId() {
		return attendThemeId;
	}

	public void setAttendThemeId(String attendThemeId) {
		this.attendThemeId = attendThemeId;
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

	public AttendTerm() {
		super();
	}

	public AttendTerm(String id) {
		super(id);
	}

}