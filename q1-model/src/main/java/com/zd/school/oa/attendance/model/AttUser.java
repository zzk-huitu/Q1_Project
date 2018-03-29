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
 * ClassName: AttUser 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 考勤人员(T_PT_AttendanceUser)实体类.
 * date: 2017-05-15
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_AttendanceUser")
@AttributeOverride(name = "attendanceUserId", column = @Column(name = "attendanceUserId", length = 36, nullable = false))
public class AttUser extends BaseEntity implements Serializable{
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
    @FieldInfo(name = "主键")
    @Column(name = "userId", length = 36, nullable = false)
    private String userId;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
    
    
    @FieldInfo(name = "姓名")
    @Formula("(SELECT ISNULL(a.XM,'') FROM SYS_T_USER a WHERE a.USER_ID=USER_ID)")
    private String name;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
   
    @FieldInfo(name = "学号")
    @Formula("(SELECT ISNULL(a.USER_NUMB,'') FROM SYS_T_USER a WHERE a.USER_ID=USER_ID)")
    private String studentNo;
	
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

  

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}