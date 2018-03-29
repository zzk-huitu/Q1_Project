package com.zd.school.oa.attendance.model;

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
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: AttTerm 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 考勤设备(T_PT_AttendanceTerm)实体类.
 * date: 2017-05-15
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_AttendanceTerm")
@AttributeOverride(name = "attendanceTermId", column = @Column(name = "attendanceTermId", length = 36, nullable = false))
public class AttTerm extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "主题ID")
    @Column(name = "themeId", length = 36, nullable = true)
    private String themeId;
    public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}

	@FieldInfo(name = "终端号")
    @Formula("(SELECT ISNULL(a.TERM_CODE,'') FROM OA_T_INFOTERM a WHERE a.TERM_ID=TERM_ID)")
    private String termNo;
    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }
    public String getTermNo() {
        return termNo;
    }
        
    @FieldInfo(name = "房间ID")
    @Formula("(SELECT ISNULL(a.ROOM_ID,'') FROM OA_T_INFOTERM a WHERE a.TERM_ID=TERM_ID)")
    private String roomId;
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoomId() {
        return roomId;
    }
        
    @FieldInfo(name = "房间名称")
    @Formula("(SELECT ISNULL(a.ROOM_NAME,'') FROM OA_T_INFOTERM a WHERE a.TERM_ID=TERM_ID)")
    private String roomName;
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public String getRoomName() {
        return roomName;
    }    
  
        

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}