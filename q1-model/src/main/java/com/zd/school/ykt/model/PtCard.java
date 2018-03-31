package com.zd.school.ykt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: PtCard 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: (PT_CARD)实体类.
 * date: 2017-04-20
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_Card")
@AttributeOverride(name = "cardId", column = @Column(name = "cardId", length = 20, nullable = false))
public class PtCard extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    

	@FieldInfo(name = "卡流水号",type="varchar(19)",explain="卡的流水号")
    @Column(name = "cardNo", columnDefinition="varchar(19) defalut ''", nullable = true)
    private Long cardNo;
    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }
    public Long getCardNo() {
        return cardNo;
    }
        
	@FieldInfo(name = "卡状态 1正常 2挂失 3注销 4换卡 7冻结",type="byte",explain="卡的状态 ")
    @Column(name = "cardStatusId", columnDefinition="defalut 0", nullable = true)
    private byte cardStatusId;
   
        

	@FieldInfo(name = "卡的类型编号",type="Integer",explain="卡的类型编号")
    @Column(name = "cardTypeNO", columnDefinition="defalut 0", nullable = true)
    private Integer cardTypeNO;
   
	@FieldInfo(name = "当日消费次数",type="Integer",explain="卡的当日消费次数")
    @Column(name = "dayCount", columnDefinition="defalut 0", nullable = true)
    private Integer dayCount;
  
        
	@FieldInfo(name = "当日交易金额",type="Integer",explain="卡的当日交易金额")
    @Column(name = "dayValue", columnDefinition="defalut 0", nullable = true)
    private BigDecimal dayValue;
  
        
    @FieldInfo(name = "卡押金",type="BigDecimal",explain="卡的押金")
    @Column(name = "deposit", columnDefinition="defalut 0", nullable = true)
    private BigDecimal deposit;
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
    public BigDecimal getDeposit() {
        return deposit;
    }
        
	@FieldInfo(name = "有效期",type="datetime",explain="卡的有效期")
    @Column(name = "expiryDate", columnDefinition = "datetime", nullable = true)
    private Date expiryDate= new Date();;
 
	@FieldInfo(name = "物理卡号",type="Long",explain="卡的物理卡号")
    @Column(name = "physicalNo", columnDefinition="defalut 0",nullable = true)
    private Long physicalNo;
     
    @FieldInfo(name = "最后交易时间",type="datetime",explain="卡的最后交易时间")
    @Column(name = "lastPayDate", columnDefinition = "datetime", nullable = true)
    private Date lastPayDate =new Date();
   
        
	@FieldInfo(name = "最后交易餐类",type="Integer",explain="卡的最后交易餐类")
    @Column(name = "lastPayMealType", columnDefinition="defalut 0", nullable = true)
    private Integer lastPayMealType;
  
        
    @FieldInfo(name = "当餐消费次数",type="Integer",explain="卡的当餐消费次数")
    @Column(name = "mealCount", columnDefinition="defalut 0", nullable = true)
    private Integer mealCount;
  
        
	@FieldInfo(name = "当餐交易金额",type="BigDecimal",explain="卡的当餐交易金额")
    @Column(name = "mealValue", columnDefinition="defalut 0", nullable = true)
    private BigDecimal mealValue;
  
        
	public byte getCardStatusId() {
		return cardStatusId;
	}
	public void setCardStatusId(byte cardStatusId) {
		this.cardStatusId = cardStatusId;
	}

	public Integer getCardTypeNO() {
		return cardTypeNO;
	}
	public void setCardTypeNO(Integer cardTypeNO) {
		this.cardTypeNO = cardTypeNO;
	}
	public Integer getDayCount() {
		return dayCount;
	}
	public void setDayCount(Integer dayCount) {
		this.dayCount = dayCount;
	}
	public BigDecimal getDayValue() {
		return dayValue;
	}
	public void setDayValue(BigDecimal dayValue) {
		this.dayValue = dayValue;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Long getPhysicalNo() {
		return physicalNo;
	}
	public void setPhysicalNo(Long physicalNo) {
		this.physicalNo = physicalNo;
	}
	public Date getLastPayDate() {
		return lastPayDate;
	}
	public void setLastPayDate(Date lastPayDate) {
		this.lastPayDate = lastPayDate;
	}
	public Integer getLastPayMealType() {
		return lastPayMealType;
	}
	public void setLastPayMealType(Integer lastPayMealType) {
		this.lastPayMealType = lastPayMealType;
	}
	public Integer getMealCount() {
		return mealCount;
	}
	public void setMealCount(Integer mealCount) {
		this.mealCount = mealCount;
	}
	public BigDecimal getMealValue() {
		return mealValue;
	}
	public void setMealValue(BigDecimal mealValue) {
		this.mealValue = mealValue;
	}
	public Date getStatusChangeTime() {
		return statusChangeTime;
	}
	public void setStatusChangeTime(Date statusChangeTime) {
		this.statusChangeTime = statusChangeTime;
	}

	@FieldInfo(name = "卡状态改变时间",type="datetime",explain="卡的状态改变时间")
    @Column(name = "statusChangeTime", length = 27, columnDefinition = "datetime", nullable = true)
    private Date statusChangeTime=new Date();
  
        
    @FieldInfo(name = "用户id",type="varchar(20)",explain="所持卡的用户id")
    @Column(name = "userId", length = 20, nullable = true)
    private String userId;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }

	@FieldInfo(name = "卡类型名称",type="BigDecimal",explain="卡的类型名称")
	@Formula("(select t.cardType from T_PT_CardType t where t.cardTypeNO= cardTypeNO)")
    private String cardTypeName;
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
    
    

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}