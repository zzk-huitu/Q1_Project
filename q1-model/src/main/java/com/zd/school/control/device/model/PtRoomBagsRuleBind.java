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
 * 房间钱包规则绑定
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_RoomBagRuleBind")
@AttributeOverride(name = "id", column = @Column(name = "ruleBindId", length = 20, nullable = false))
public class PtRoomBagsRuleBind extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "房间ID",type="varchar(20) NOT NULL",explain="房间钱包规则绑定的房间ID")
	@Column(name = "roomId", length=20, nullable = false)
	private String roomId;

	@FieldInfo(name = "钱包规则费率绑定ID",type="varchar(20) NOT NULL",explain="钱包规则费率绑定ID")
	@Column(name = "roomRuleId",length=20, nullable = false)
	private String roomRuleId;

	@FieldInfo(name = "指定扣费模式下扣费的用户ID",type="varchar(20) default ''",explain="指定扣费模式下扣费的用户ID")
	@Column(name = "deductionUserId", length=20, columnDefinition = "default ''", nullable = true)
	private String deductionUserId;

	
	//@FieldInfo(name = "学号",type="varchar(20)",explain="指定扣费模式下扣费的用户ID")
	@Formula("(SELECT A.userNumb FROM T_PT_User A WHERE A.userId=deductionUserId)")
	private String userNumb;

	//@FieldInfo(name = "姓名",type="varchar(20)",explain="学生姓名")
	@Formula("(SELECT A.name FROM T_PT_User A WHERE A.userId=deductionUserId)")
	private String name;
	
	//@FieldInfo(name = "房间名称",type="varchar(20)",explain="房间名称")
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

	public PtRoomBagsRuleBind() {
		super();
	}
	
	public PtRoomBagsRuleBind(String id) {
		super(id);
	}
	
}