package com.zd.school.build.allot.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 学生宿舍移除
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_StudentDormRemove")
@AttributeOverride(name = "id", column = @Column(name = "removeId", length = 20, nullable = false) )
public class DormStudentRemove extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "班级宿舍id", type = "varchar(20) NOT NULL", explain = "班级宿舍主键")
	@Column(name = "classDormId", length = 20, nullable = false)
	private String classDormId;

	@FieldInfo(name = "房间ID", type = "varchar(20) NOT NULL", explain = "房间主键")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "学生ID", type = "varchar(20) NOT NULL", explain = "学生主键")
	@Column(name = "studentId", length = 20, nullable = false)
	private String studentId;

	@FieldInfo(name = "操作类型", type = "nvarchar(10)  default ''", explain = "操作类型")
	@Column(name = "operateType", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String operateType;

	@FieldInfo(name = "是否处理", type = "bit default 0", explain = "是否处理")
	@Column(name = "isHandle", columnDefinition = "default 0", nullable = true)
	private Boolean isHandle;

	@FieldInfo(name = "amount", type = "numeric default 0", explain = "金额")
	@Column(name = "amount", columnDefinition = "default 0", nullable = true)
	private BigDecimal amount;

	public String getClassDormId() {
		return classDormId;
	}

	public void setClassDormId(String classDormId) {
		this.classDormId = classDormId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Boolean getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(Boolean isHandle) {
		this.isHandle = isHandle;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public DormStudentRemove() {
		super();
	}

	public DormStudentRemove(String id) {
		super(id);
	}

}
