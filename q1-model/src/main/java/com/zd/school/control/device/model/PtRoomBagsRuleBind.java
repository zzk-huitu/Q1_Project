package com.zd.school.control.device.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.school.excel.annotation.MapperCell;

/**
 * 钱包规则费率绑定
 * 
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_RoomBagRuleBind")
@AttributeOverride(name = "boomBagRuleBindId", column = @Column(name = "boomBagRuleBindId", length = 20, nullable = false))
public class PtRoomBagsRuleBind extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "房间ID",type="varchar(20)",explain="房间钱包规则绑定的房间ID")
	@Column(name = "roomId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String roomId;

	@FieldInfo(name = "钱包规则费率绑定ID",type="varchar(20)",explain="钱包规则费率绑定ID")
	@Column(name = "roomRuleId",columnDefinition = "varchar(20) default ''", nullable = true)
	private String roomRuleId;

	@FieldInfo(name = "指定扣费模式下扣费的用户ID",type="varchar(20)",explain="指定扣费模式下扣费的用户ID")
	@Column(name = "deductionUserId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String deductionUserId;

	
	@MapperCell(cellName="学号")
	@FieldInfo(name = "学号",type="varchar(20)",explain="指定扣费模式下扣费的用户ID")
	@Formula("(SELECT A.userNumb FROM T_PT_User A WHERE A.userId=deductionUserId)")
	private String userNumb;

	@MapperCell(cellName="学生姓名")
	@FieldInfo(name = "姓名",type="varchar(20)",explain="学生姓名")
	@Formula("(SELECT A.name FROM T_PT_User A WHERE A.userId=deductionUserId)")
	private String name;
	
	@MapperCell(cellName="房间名称")
	@FieldInfo(name = "房间名称",type="varchar(20)",explain="房间名称")
	@Formula("(SELECT A.roomName FROM T_PT_RoomInfo A WHERE A.roomId=roomId)")
	private String roomName;
	
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomRuleId() {
		return roomRuleId;
	}

	public void setRoomRuleId(String roomRuleId) {
		this.roomRuleId = roomRuleId;
	}

	public String getDeductionUserId() {
		return deductionUserId;
	}

	public void setDeductionUserId(String deductionUserId) {
		this.deductionUserId = deductionUserId;
	}

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	
	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}