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
@AttributeOverride(name = "cardId", column = @Column(name = "cardId", length = 36, nullable = false))
public class PtCard extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    

	@FieldInfo(name = "卡流水号")
    @Column(name = "cardNo", length = 19, nullable = true)
    private Long cardNo;
    public void setCardNo(Long cardNo) {
        this.cardNo = cardNo;
    }
    public Long getCardNo() {
        return cardNo;
    }
        
	@FieldInfo(name = "卡状态 1正常 2挂失 3注销 4换卡 7冻结")
    @Column(name = "cardStatusId", length = 10, nullable = true)
    private Integer cardStatusId;
   
        

	@FieldInfo(name = "卡类型ID")
    @Column(name = "cardTypeId", length = 10, nullable = true)
    private Integer cardTypeId;
   
	@FieldInfo(name = "当日消费次数")
    @Column(name = "dayCount", length = 10, nullable = true)
    private Integer dayCount;
  
        
	@FieldInfo(name = "当日交易金额")
    @Column(name = "dayValue", length = 19, nullable = true)
    private BigDecimal dayValue;
  
        
    @FieldInfo(name = "卡押金")
    @Column(name = "deposit", length = 19, nullable = true)
    private BigDecimal deposit;
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
    public BigDecimal getDeposit() {
        return deposit;
    }
        
	@FieldInfo(name = "有效期")
    @Column(name = "expiryDate", length = 27, columnDefinition = "datetime", nullable = true)
    private Date expiryDate= new Date();;
 
	@FieldInfo(name = "物理卡号")
    @Column(name = "physicalNo", length = 19, nullable = true)
    private Long physicalNo;
     
    @FieldInfo(name = "最后交易时间")
    @Column(name = "lastPayDate", length = 27, columnDefinition = "datetime", nullable = true)
    private Date lastPayDate =new Date();
   
        
	@FieldInfo(name = "最后交易餐类")
    @Column(name = "lastPayMealType", length = 10, nullable = true)
    private Integer lastPayMealType;
  
        
    @FieldInfo(name = "当餐消费次数")
    @Column(name = "mealCount", length = 10, nullable = true)
    private Integer mealCount;
  
        
	@FieldInfo(name = "当日交易金额")
    @Column(name = "mealValue", length = 19, nullable = true)
    private BigDecimal mealValue;
  
        
	public Integer getCardStatusId() {
		return cardStatusId;
	}
	public void setCardStatusId(Integer cardStatusId) {
		this.cardStatusId = cardStatusId;
	}
	public Integer getCardTypeId() {
		return cardTypeId;
	}
	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
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

	@FieldInfo(name = "卡状态改变时间")
    @Column(name = "statusChangeTime", length = 27, columnDefinition = "datetime", nullable = true)
    private Date statusChangeTime=new Date();
  
        
    @FieldInfo(name = "userId")
    @Column(name = "userId", length = 36, nullable = true)
    private String userId;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }

	@FieldInfo(name = "卡类")
	@Formula("(select t.CardType from TC_CardType t where t.CardTypeNO= CARDTYPEID)")
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