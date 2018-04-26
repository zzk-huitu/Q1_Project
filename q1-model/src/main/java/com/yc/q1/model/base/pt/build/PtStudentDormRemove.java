package com.yc.q1.model.base.pt.build;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 学生宿舍移除
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_StudentDormRemove")
@AttributeOverride(name = "id", column = @Column(name = "removeId", length = 20, nullable = false) )
public class PtStudentDormRemove extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "班级宿舍ID", type = "varchar(20) NOT NULL", explain = "班级宿舍主键")
	@Column(name = "classDormId", length = 20, nullable = false)
	private String classDormId;

	@FieldInfo(name = "房间ID", type = "varchar(20) NOT NULL", explain = "房间主键")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "学生ID", type = "varchar(20) NOT NULL", explain = "学生主键")
	@Column(name = "studentId", length = 20, nullable = false)
	private String studentId;

	@FieldInfo(name = "操作类型", type = "nvarchar(8)  default ''", explain = "操作类型")
	@Column(name = "operateType", columnDefinition = "nvarchar(8) default ''", nullable = true)
	private String operateType;

	@FieldInfo(name = "是否处理", type = "bit default 0", explain = "是否处理")
	@Column(name = "isHandle", columnDefinition = "bit default 0", nullable = true)
	private Boolean isHandle;

	@FieldInfo(name = "amount", type = "decimal(18, 2) default 0", explain = "金额")
	@Column(name = "amount", columnDefinition = "decimal(18, 2) default 0", nullable = true)
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

	public PtStudentDormRemove() {
		super();
	}

	public PtStudentDormRemove(String id) {
		super(id);
	}

}
