package com.zd.school.jw.ecc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: JwCheckrule Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 课程考勤规则(JW_T_CHECKRULE)实体类. date: 2017-05-10
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_AttenceRule")
@AttributeOverride(name = "attenceRuleId", column = @Column(name = "attenceRuleId", length = 36, nullable = false))
public class JwCheckrule extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "规则名称")
	@Column(name = "ruleName", length = 36, nullable = false)
	private String ruleName;

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleName() {
		return ruleName;
	}

	@FieldInfo(name = "考勤模式 1-按半天考勤 2-按全天考勤 3-按节次考勤")
	@Column(name = "attenceMode", length = 5, nullable = false)
	private Integer attenceMode;

	public Integer getAttenceMode() {
		return attenceMode;
	}

	public void setAttenceMode(Integer attenceMode) {
		this.attenceMode = attenceMode;
	}

	@FieldInfo(name = "签到提前分钟")
	@Column(name = "inAdvance", length = 5, nullable = false)
	private Integer inAdvance;

	public Integer getInAdvance() {
		return inAdvance;
	}

	public void setInAdvance(Integer inAdvance) {
		this.inAdvance = inAdvance;
	}

	@FieldInfo(name = "迟到分钟")
	@Column(name = "beLate", length = 5, nullable = false)
	private Integer beLate;

	public Integer getBeLate() {
		return beLate;
	}

	public void setBeLate(Integer beLate) {
		this.beLate = beLate;
	}

	@FieldInfo(name = "缺勤分钟")
	@Column(name = "absenceMinute", length = 5, nullable = false)
	private Integer absenceMinute;

	public Integer getAbsenceMinute() {
		return absenceMinute;
	}

	public void setAbsenceMinute(Integer absenceMinute) {
		this.absenceMinute = absenceMinute;
	}

	@FieldInfo(name = "是否需要签退 0-不需要 1-需要")
	@Column(name = "needSignOut", length = 5, nullable = false)
	private Integer needSignOut;

	public Integer getNeedSignOut() {
		return needSignOut;
	}

	public void setNeedSignOut(Integer needSignOut) {
		this.needSignOut = needSignOut;
	}

	@FieldInfo(name = "签退提前分钟")
	@Column(name = "signOutAdvanceMin", length = 5, nullable = true)
	private Integer signOutAdvanceMin;

	public Integer getSignOutAdvanceMin() {
		return signOutAdvanceMin;
	}

	public void setSignOutAdvanceMin(Integer signOutAdvanceMin) {
		this.signOutAdvanceMin = signOutAdvanceMin;
	}

	@FieldInfo(name = "早退分钟")
	@Column(name = "leaveEarlyMinute", length = 5, nullable = true)
	private Integer leaveEarlyMinute;

	public Integer getLeaveEarlyMinute() {
		return leaveEarlyMinute;
	}

	public void setLeaveEarlyMinute(Integer leaveEarlyMinute) {
		this.leaveEarlyMinute = leaveEarlyMinute;
	}

	@FieldInfo(name = "签退延迟分钟")
	@Column(name = "leaveDelayMinute", length = 5, nullable = true)
	private Integer leaveDelayMinute;

	public Integer getLeaveDelayMinute() {
		return leaveDelayMinute;
	}

	public void setLeaveDelayMinute(Integer leaveDelayMinute) {
		this.leaveDelayMinute = leaveDelayMinute;
	}

	@FieldInfo(name = "规则说明")
	@Column(name = "ruleExplain", length = 255, nullable = true)
	private String ruleExplain;

	public String getRuleExplain() {
		return ruleExplain;
	}

	public void setRuleExplain(String ruleExplain) {
		this.ruleExplain = ruleExplain;
	}

	@FieldInfo(name = "启用标识 0-不启用 1-启用")
	@Column(name = "START_USING", length = 5, nullable = false)
	private Integer startUsing;

	public Integer getStartUsing() {
		return startUsing;
	}

	public void setStartUsing(Integer startUsing) {
		this.startUsing = startUsing;
	}
	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}