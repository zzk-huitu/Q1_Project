package com.yc.q1.model.storage.xf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 消费机的消费交易明细
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_XF_ConsumeDetail", catalog = "Q1_Storage", schema = "dbo")
@AttributeOverride(name = "id", column = @Column(name = "consumeDetailId", length = 20, nullable = false) )
public class XfConsumeDetail extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.XF; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "卡流水号")
	@Column(name = "cardNo", columnDefinition = "bigint default 0", nullable = true)
	private Long cardNo;

	@FieldInfo(name = "消费金额")
	@Column(name = "consumeValue", columnDefinition = "money default 0", nullable = true)
	private BigDecimal consumeValue;

	@FieldInfo(name = "卡上余额")
	@Column(name = "cardValue", columnDefinition = "money default 0", nullable = true)
	private BigDecimal cardValue;

	@FieldInfo(name = "卡使用次数")
	@Column(name = "consumeCountor", columnDefinition = "bigint default 0", nullable = true)
	private Long consumeCountor;

	@FieldInfo(name = "机器号")
	@Column(name = "termNo", columnDefinition = "int default 0", nullable = true)
	private Integer termNo;

	@FieldInfo(name = "机器流水号")
	@Column(name = "termRecordId", columnDefinition = "bigint default 0", nullable = true)
	private Long termRecordId;

	@FieldInfo(name = "营业操作员ID")
	@Column(name = "operatorId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String operatorId;

	@FieldInfo(name = "结算账户ID")
	@Column(name = "accountId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String accountId;

	@FieldInfo(name = "消费日期")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "consumeDate", columnDefinition = "datetime", nullable = true)
	private Date consumeDate;

	@FieldInfo(name = "消费时段ID")
	@Column(name = "mealTyepId", columnDefinition = "int default 0", nullable = true)
	private Integer mealTyepId;

	@FieldInfo(name = "消费记录标志位")
	@Column(name = "posRecordState", columnDefinition = "int default 0", nullable = true)
	private Integer posRecordState;

	@FieldInfo(name = "钱包类型编码", explain = "数据字典：1消费2水控3消费补助4脱机信用5就餐钱包6配餐钱包")
	@Column(name = "bagType", columnDefinition = "varchar(10) default ''", nullable = true)
	private String bagType;

	@FieldInfo(name = "原始记录ID")
	@Column(name = "oriRecordID", columnDefinition = "varchar(32)  default ''", nullable = true)
	private String oriRecordID;

	@FieldInfo(name = "人员ID")
	@Column(name = "userId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String userId;

	@FieldInfo(name = "人员编号")
	@Column(name = "userNumb", columnDefinition = "varchar(16) default ''", nullable = true)
	private String userNumb;

	@FieldInfo(name = "人员姓名")
	@Column(name = "xm", columnDefinition = "nvarchar(16) default ''", nullable = true)
	private String xm;

	@FieldInfo(name = "部门ID")
	@Column(name = "deptId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String deptId;

	@FieldInfo(name = "部门名称")
	@Column(name = "deptName", columnDefinition = "nvarchar(16) default ''", nullable = true)
	private String deptName;

	@FieldInfo(name = "设备名称")
	@Column(name = "termName", columnDefinition = "nvarchar(50) default ''", nullable = true)
	private String termName;

	@FieldInfo(name = "区域ID")
	@Column(name = "areaId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String areaId;

	@FieldInfo(name = "区域名称")
	@Column(name = "areaName", columnDefinition = "nvarchar(16) default ''", nullable = true)
	private String areaName;

	@FieldInfo(name = "结算账户名称")
	@Column(name = "accountName", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String accountName;

	@FieldInfo(name = "是否发送短信")
	@Column(name = "isSmsSend", columnDefinition = "bit default 0", nullable = true)
	private Boolean isSmsSend;

	@FieldInfo(name = "状态标识")
	@Column(name = "statusFlag", columnDefinition = "smallint default 0", nullable = true)
	private Integer statusFlag;

	@FieldInfo(name = "备注")
	@Column(name = "manualNotes", columnDefinition = "nvarchar(200) default ''", nullable = true)
	private String manualNotes;

	@FieldInfo(name = "物理卡号")
	@Column(name = "factoryFixId", columnDefinition = "bigint default 0", nullable = true)
	private Long factoryFixId;

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public BigDecimal getConsumeValue() {
		return consumeValue;
	}

	public void setConsumeValue(BigDecimal consumeValue) {
		this.consumeValue = consumeValue;
	}

	public BigDecimal getCardValue() {
		return cardValue;
	}

	public void setCardValue(BigDecimal cardValue) {
		this.cardValue = cardValue;
	}

	public Long getConsumeCountor() {
		return consumeCountor;
	}

	public void setConsumeCountor(Long consumeCountor) {
		this.consumeCountor = consumeCountor;
	}

	public Integer getTermNo() {
		return termNo;
	}

	public void setTermNo(Integer termNo) {
		this.termNo = termNo;
	}

	public Long getTermRecordId() {
		return termRecordId;
	}

	public void setTermRecordId(Long termRecordId) {
		this.termRecordId = termRecordId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}

	public Integer getMealTyepId() {
		return mealTyepId;
	}

	public void setMealTyepId(Integer mealTyepId) {
		this.mealTyepId = mealTyepId;
	}

	public Integer getPosRecordState() {
		return posRecordState;
	}

	public void setPosRecordState(Integer posRecordState) {
		this.posRecordState = posRecordState;
	}

	public String getBagType() {
		return bagType;
	}

	public void setBagType(String bagType) {
		this.bagType = bagType;
	}

	public String getOriRecordID() {
		return oriRecordID;
	}

	public void setOriRecordID(String oriRecordID) {
		this.oriRecordID = oriRecordID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Boolean getIsSmsSend() {
		return isSmsSend;
	}

	public void setIsSmsSend(Boolean isSmsSend) {
		this.isSmsSend = isSmsSend;
	}

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getManualNotes() {
		return manualNotes;
	}

	public void setManualNotes(String manualNotes) {
		this.manualNotes = manualNotes;
	}

	public Long getFactoryFixId() {
		return factoryFixId;
	}

	public void setFactoryFixId(Long factoryFixId) {
		this.factoryFixId = factoryFixId;
	}

	public XfConsumeDetail() {
		super();
	}

	public XfConsumeDetail(String id) {
		super(id);
	}

}
