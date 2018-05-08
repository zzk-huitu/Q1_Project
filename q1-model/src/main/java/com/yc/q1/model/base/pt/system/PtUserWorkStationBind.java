package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Formula;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@FieldInfo(name = "工作站ID")
	@Column(name = "workStationId", length = 30, nullable = false)
	private String workStationId;

	public String getWorkStationId() {
		return workStationId;
	}

	public void setWorkStationId(String workStationId) {
		this.workStationId = workStationId;
	}
	
	/*
	 * 以下字段不需要持续化到数据库中
	 */
	@Formula("(SELECT a.workStationName FROM T_PT_WorkStation a WHERE a.workStationId=workStationId )")
	private String workStationName;

	public String getWorkStationName() {
		return workStationName;
	}

	public void setWorkStationName(String workStationName) {
		this.workStationName = workStationName;
	}

	@Formula("(SELECT a.workStationIP FROM T_PT_WorkStation a WHERE a.workStationId=workStationId )")
	private String workStationIP;

	public String getWorkStationIP() {
		return workStationIP;
	}

	public void setWorkStationIP(String workStationIP) {
		this.workStationIP = workStationIP;
	}

	public PtUserWorkStationBind() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PtUserWorkStationBind(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
}
