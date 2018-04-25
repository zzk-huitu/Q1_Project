package com.yc.q1.model.storage.xf;

import java.io.Serializable;

import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 资金收支备份表
 * @author ZZK
 *
 */
//@Entity
//@Table(name = "T_XF_CreditAccountBack",catalog="Q1_Storage",schema="dbo")
//@AttributeOverride(name = "id", column = @Column(name = "creditAccountBackId", length = 20, nullable = false) )
public class XfCreditAccountBack extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.XF; // 指定此对象生成的模块编码值。


}
