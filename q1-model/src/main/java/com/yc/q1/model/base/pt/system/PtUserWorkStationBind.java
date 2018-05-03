package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 用户绑定工作站表
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_UserWorkSationBind", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "workStationId" }) })
@AttributeOverride(name = "id", column = @Column(name = "bindId", length = 20, nullable = false) )
public class PtUserWorkStationBind  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。
	
	@FieldInfo(name = "用户ID")
	@Column(name = "userId", length=20, nullable = false)
	private String userId;

	@FieldInfo(name = "工作站ID")
	@Column(name = "workStationId", length = 30, nullable = false)
	private String workStationId;

}
