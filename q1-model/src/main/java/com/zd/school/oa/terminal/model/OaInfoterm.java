package com.zd.school.oa.terminal.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.ExportExcelAnnotation;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.school.excel.annotation.MapperCell;

/**
 * 
 * ClassName: OaInfoterm Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 信息发布终端(T_PT_InfoTerminal)实体类. date: 2017-01-14
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_InfoTerminal")
@AttributeOverride(name = "infoTerminalId", column = @Column(name = "infoTerminalId", length = 20, nullable = false))
public class OaInfoterm extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ExportExcelAnnotation(columnName="终端号",columnWidth=15,order = 1)
	@MapperCell(cellName = "终端号", order = 1)
	@FieldInfo(name = "终端号")
	@Column(name = "terminalNO", length = 6, nullable = false)
	private String terminalNO;

	public String getTerminalNO() {
		return terminalNO;
	}

	public void setTerminalNO(String terminalNO) {
		this.terminalNO = terminalNO;
	}
	
	@FieldInfo(name = "终端类型，数据字典INFOTERTYPE")
	@Column(name = "terminalType", length = 16, nullable = false)
	private String terminalType;
	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}


	@FieldInfo(name = "规格")
	@Column(name = "terminalSpec", columnDefinition="nvarchar(32) defalut ''", nullable = true)
	private String terminalSpec;

	public String getTerminalSpec() {
		return terminalSpec;
	}

	public void setTerminalSpec(String terminalSpec) {
		this.terminalSpec = terminalSpec;
	}

	@FieldInfo(name = "使用状态，0-未使用 1-已使用")
	@Column(name = "isUse", nullable = false)
	private boolean isUse;

	public void setIsUse(boolean isUse) {
		this.isUse = isUse;
	}

	public boolean getIsUse() {
		return isUse;
	}

	@FieldInfo(name = "使用房间ID")
	@Column(name = "roomId",columnDefinition="varchar(20) defalut ''", nullable = true)
	private String roomId;

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomId() {
		return roomId;
	}

	@ExportExcelAnnotation(columnName="使用房间名称",columnWidth=25,order =3)
	@MapperCell(cellName = "使用房间名称", order = 2)
	@FieldInfo(name = "使用房间名称")
	//@Formula("(SELECT a.ROOM_NAME FROM BuildRoominfo a WHERE a.ROOM_ID=ROOM_ID)")
	@Column(name = "roomName", columnDefinition="nvarchar(64) defalut ''", nullable = true)
	private String roomName;

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	@ExportExcelAnnotation(columnName="使用房间门牌号",columnWidth=20,order = 2)
	@MapperCell(cellName = "使用房间门牌号", order = 3)
	@FieldInfo(name = "使用房间名称门牌号")
	@Column(name = "houseNo", columnDefinition="nvarchar(64) defalut ''", nullable = true)
	private String houseNo;

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}