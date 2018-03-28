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
@Table(name = "T_PT_StudentRemove")
@AttributeOverride(name = "studentRemoveId", column = @Column(name = "studentRemoveId", length = 36, nullable = false) )
public class DormStudentRemove extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldInfo(name = "班级宿舍主键")
	@Column(name = "classDormId", length = 36, nullable = true)
	private String classDormId;
	
	@FieldInfo(name = "房间主键")
	@Column(name = "roomId", length = 36, nullable = true)
	private String roomId;
	
	@FieldInfo(name = "学生主键")
	@Column(name = "studentId", length = 50, nullable = true)
	private String studentId;
	
	@FieldInfo(name = "操作类型")
	@Column(name = "operatingType", length = 36, nullable = true)
	private String operatingType;
	
	@FieldInfo(name = "是否处理")
	@Column(name = "isHandle", length = 36, nullable = true)
	private String isHandle;
	
	@FieldInfo(name = "金额")
    @Column(name = "amount", length = 18, nullable = true)
    private BigDecimal amount;

	public String getClassDormId() {
		return classDormId;
	}

	public void setClassDormId(String classDormId) {
		this.classDormId = classDormId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getOperatingType() {
		return operatingType;
	}

	public void setOperatingType(String operatingType) {
		this.operatingType = operatingType;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(String isHandle) {
		this.isHandle = isHandle;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
