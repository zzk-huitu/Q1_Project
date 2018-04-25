package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;

/**
 * 就餐营业时段管理类
 * 
 * 数据在初始化时生成，基本不会新增和修改
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_MealType")
public class PtMealType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@FieldInfo(name = "营业时段ID", explain = "营业时段id值")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mealTypeId", columnDefinition = "smallint", nullable = false)
	private Integer mealTypeId;

	@FieldInfo(name = "就餐时段名称")
	@Column(name = "mealName", length = 30, nullable = false)
	private String mealName;

	@FieldInfo(name = "就餐开始时间")
	@Column(name = "beginTime", length = 8, nullable = false)
	private String beginTime;

	@FieldInfo(name = "就餐结束时间")
	@Column(name = "endTime", length = 8, nullable = false)
	private String endTime;

	@FieldInfo(name = "就餐描述")
	@Column(name = "mealNotes", columnDefinition = "nvarchar(200) DEFAULT ''", nullable = true)
	private String mealNotes;

	public Integer getMealTypeId() {
		return mealTypeId;
	}

	public void setMealTypeId(Integer mealTypeId) {
		this.mealTypeId = mealTypeId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMealNotes() {
		return mealNotes;
	}

	public void setMealNotes(String mealNotes) {
		this.mealNotes = mealNotes;
	}

}
