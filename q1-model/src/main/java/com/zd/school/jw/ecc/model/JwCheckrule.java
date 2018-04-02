package com.zd.school.jw.ecc.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 课程考勤规则
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_AttenceRule")
@AttributeOverride(name = "id", column = @Column(name = "attenceRuleId", length = 20, nullable = false) )
public class JwCheckrule extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "规则名称", type = "nvarchar(20) NOT NULL", explain = "规则名称")
	@Column(name = "ruleName", columnDefinition = "nvarchar(20)", nullable = false)
	private String ruleName;

	@FieldInfo(name = "考勤模式", type = "varchar(1) NOT NULL", explain = "考勤模式:1-按半天考勤 ;2-按全天考勤; 3-按节次考勤")
	@Column(name = "attenceMode", length = 1, nullable = false)
	private String attenceMode;

	@FieldInfo(name = "签到提前分钟", type = "int NOT NULL", explain = "签到提前分钟")
	@Column(name = "inAdvanceTime", nullable = false)
	private Integer inAdvanceTime;

	@FieldInfo(name = "迟到分钟", type = "int NOT NULL", explain = "迟到分钟")
	@Column(name = "beLateTime", nullable = false)
	private Integer beLateTime;

	@FieldInfo(name = "缺勤分钟", type = "int NOT NULL", explain = "缺勤分钟")
	@Column(name = "absenceTime", nullable = false)
	private Integer absenceTime;

	@FieldInfo(name = "是否需要签退", type = "bit NOT NULL", explain = "是否需要签退 :0-不需要; 1-需要")
	@Column(name = "needSignOut", nullable = false)
	private Boolean needSignOut;

	@FieldInfo(name = "签退提前分钟", type = "int default 0", explain = "签退提前分钟")
	@Column(name = "outAdvanceTimme", columnDefinition = "default 0", nullable = true)
	private Integer outAdvanceTimme;

	@FieldInfo(name = "早退分钟", type = "int default 0", explain = "早退分钟")
	@Column(name = "leaveEarlyTime", columnDefinition = "default 0", nullable = true)
	private Integer leaveEarlyTime;

	@FieldInfo(name = "签退延迟分钟", type = "int default 0", explain = "签退延迟分钟")
	@Column(name = "leaveDelayTime", columnDefinition = "default 0", nullable = true)
	private Integer leaveDelayTime;

	@FieldInfo(name = "规则说明", type = "nvarchar(128)  default ''", explain = "规则说明")
	@Column(name = "ruleExplain", columnDefinition = "nvarchar(128) default ''", nullable = true)
	private String ruleExplain;

	@FieldInfo(name = "启用标识", type = "bit NOT NULL", explain = "启用标识:0-不启用 ;1-启用")
	@Column(name = "startUsing", nullable = false)
	private Boolean startUsing;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getAttenceMode() {
		return attenceMode;
	}

	public void setAttenceMode(String attenceMode) {
		this.attenceMode = attenceMode;
	}

	public Integer getInAdvanceTime() {
		return inAdvanceTime;
	}

	public void setInAdvanceTime(Integer inAdvanceTime) {
		this.inAdvanceTime = inAdvanceTime;
	}

	public Integer getBeLateTime() {
		return beLateTime;
	}

	public void setBeLateTime(Integer beLateTime) {
		this.beLateTime = beLateTime;
	}

	public Integer getAbsenceTime() {
		return absenceTime;
	}

	public void setAbsenceTime(Integer absenceTime) {
		this.absenceTime = absenceTime;
	}

	public Boolean getNeedSignOut() {
		return needSignOut;
	}

	public void setNeedSignOut(Boolean needSignOut) {
		this.needSignOut = needSignOut;
	}

	public Integer getOutAdvanceTimme() {
		return outAdvanceTimme;
	}

	public void setOutAdvanceTimme(Integer outAdvanceTimme) {
		this.outAdvanceTimme = outAdvanceTimme;
	}

	public Integer getLeaveEarlyTime() {
		return leaveEarlyTime;
	}

	public void setLeaveEarlyTime(Integer leaveEarlyTime) {
		this.leaveEarlyTime = leaveEarlyTime;
	}

	public Integer getLeaveDelayTime() {
		return leaveDelayTime;
	}

	public void setLeaveDelayTime(Integer leaveDelayTime) {
		this.leaveDelayTime = leaveDelayTime;
	}

	public String getRuleExplain() {
		return ruleExplain;
	}

	public void setRuleExplain(String ruleExplain) {
		this.ruleExplain = ruleExplain;
	}

	public Boolean getStartUsing() {
		return startUsing;
	}

	public void setStartUsing(Boolean startUsing) {
		this.startUsing = startUsing;
	}

	public JwCheckrule() {
		super();
	}

	public JwCheckrule(String id) {
		super();
	}

}