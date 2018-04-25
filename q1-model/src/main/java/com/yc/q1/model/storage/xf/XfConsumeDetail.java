package com.yc.q1.model.storage.xf;

import java.io.Serializable;

import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 消费机的消费交易明细
 * 
 * @author ZZK
 *
 */
//@Entity
//@Table(name = "T_XF_ConsumeDetail",catalog="Q1_Storage",schema="dbo")
//@AttributeOverride(name = "id", column = @Column(name = "consumeDetailId", length = 20, nullable = false) )
public class XfConsumeDetail extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.XF; // 指定此对象生成的模块编码值。
	/*
	CardID
	ConsumeValue
	CardValue
	ConsumeCountor
	TermID
	TermRecordID
	OperatorID
	AccountID
	ConsumeDate
	MealTyepID
	ActBalanceID
	IsNorMal
	POSRecordState
	EmployeeID
	BagType
	OriRecordID
	EmployeeStrID
	EmployeeName
	DepartmentID
	DepartmentName
	TermName
	AreaID
	AreaName
	AccountName
	MealTypeName
	StatusFlag
	ManualNotes
	CardSerNo
	IsSMSSend
	*/

}
