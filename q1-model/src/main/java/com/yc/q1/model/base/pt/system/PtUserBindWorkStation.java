package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 操作员绑定表
 * 
 * 交易数据的查询关系到此表，若未绑定，对报表中对应的数据将无法显示
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_UserBinding")
@AttributeOverride(name = "id", column = @Column(name = "userBindId", length = 20, nullable = false) )
public class PtUserBindWorkStation extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "账号", type = "varchar(16) NOT NULL", explain = "用户的账号")
	@Column(name = "userName", length = 16, nullable = false)
	private String userName;
	


}
