package com.yc.q1.base.pt.wisdomclass.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: OaNoticeauditor Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 公告审核人(T_PT_NoticeAuditor)实体类. date: 2016-12-21
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_NoticeAuditor")
@AttributeOverride(name = "id", column = @Column(name = "noticeAuditorId", length = 20, nullable = false) )
public class NoticeAuditor extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "noticeId")
	private Notice oaNotice;

	@FieldInfo(name = "人员ID", type = "varchar(20) NOT NULL", explain = "公告的审核人员ID")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	@FieldInfo(name = "姓名(对应员工姓名)", type = "nvarchar(10) NOT NULL", explain = "公告的审核人员姓名")
	@Column(name = "name", columnDefinition = "nvarchar(10)", nullable = false)
	private String name;

	@FieldInfo(name = "审核意见", type = "nvarchar(64)  DEFAULT ''", explain = "公告的审核意见")
	@Column(name = "auditOpinion", columnDefinition = "nvarchar(64) DEFAULT ''", nullable = true)
	private String auditOpinion;

	@FieldInfo(name = "审核状态", type = "varchar(1) NOT NULL", explain = "公告的审核审核状态(0-待审核 1-审核通过 2-审核不通过)")
	@Column(name = "auditState", length = 1, nullable = false)
	private String auditState;

	@FieldInfo(name = "审核日期", type = "datetime", explain = "公告的审核日期")
	@Column(name = "auditDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date auditDate;

	public Notice getOaNotice() {
		return oaNotice;
	}

	public void setOaNotice(Notice oaNotice) {
		this.oaNotice = oaNotice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public NoticeAuditor() {
		super();
	}

	public NoticeAuditor(String id) {
		super(id);
	}

}