package com.zd.school.build.allot.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

@Entity
@Table(name = "T_PT_DormStudentRemove")
@AttributeOverride(name = "dormStudentRemoveId", column = @Column(name = "dormStudentRemoveId", length = 20, nullable = false))
public class DormStudentRemove extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "classDormId",type="varchar(20)",explain="班级宿舍主键")
	@Column(name = "classDormId", length = 20, nullable = false)
	private String classDormId;

	public String getClassDormId() {
		return classDormId;
	}

	public void setClassDormId(String classDormId) {
		this.classDormId = classDormId;
	}

	@FieldInfo(name = "roomId",type="varchar(20)",explain="房间主键")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@FieldInfo(name = "studentId",type="varchar(20)",explain="学生主键")
	@Column(name = "studentId", length = 20, nullable = false)
	private String studentId;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@FieldInfo(name = "operatingType",type="nvarchar(2)",explain="操作类型")
	@Column(name = "operatingType", columnDefinition = "nvarchar(2) default ''", nullable = true)
	private String operatingType;

	public String getOperatingType() {
		return operatingType;
	}

	public void setOperatingType(String operatingType) {
		this.operatingType = operatingType;
	}

	@FieldInfo(name = "isHandle",type="Boolean",explain="是否处理")
	@Column(name = "isHandle", columnDefinition = "default 0", nullable = true)
	private Boolean isHandle;

	public Boolean getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(Boolean isHandle) {
		this.isHandle = isHandle;
	}

	@FieldInfo(name = "amount",type="numeric",explain="金额")
	@Column(name = "amount", nullable = true)
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
