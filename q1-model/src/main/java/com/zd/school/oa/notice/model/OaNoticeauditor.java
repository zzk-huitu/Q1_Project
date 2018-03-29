package com.zd.school.oa.notice.model;

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
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: OaNoticeauditor 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 公告审核人(T_PT_NoticeAuditor)实体类.
 * date: 2016-12-21
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_NoticeAuditor")
@AttributeOverride(name = "noticeAuditorId", column = @Column(name = "noticeAuditorId", length = 20, nullable = false))
public class OaNoticeauditor extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="noticeId")
    private OaNotice oaNotice;
    
    public OaNotice getOaNotice() {
		return oaNotice;
	}
	public void setOaNotice(OaNotice oaNotice) {
		this.oaNotice = oaNotice;
	}

	@FieldInfo(name = "人员ID")
    @Column(name = "userId", length = 20, nullable = false)
    private String userId;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
        
    @FieldInfo(name = "姓名(对应员工姓名)")
    @Column(name = "name",columnDefinition="nvarchar(32)", nullable = false)
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
        
    @FieldInfo(name = "审核意见")
    @Column(name = "auditOpinion", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String auditOpinion;
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
    public String getAuditOpinion() {
        return auditOpinion;
    }
        
    @FieldInfo(name = "审核状态(0-待审核 1-审核通过 2-审核不通过)")
    @Column(name = "auditState", length = 1, nullable = false)
    private Integer auditState;
    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }
    public Integer getAuditState() {
        return auditState;
    }
        
    @FieldInfo(name = "审核日期")
    @Column(name = "auditDate", columnDefinition="datetime", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=DateTimeSerializer.class)
    private Date auditDate;
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }
    public Date getAuditDate() {
        return auditDate;
    }
        

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}