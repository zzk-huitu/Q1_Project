package com.zd.school.oa.attendance.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

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
@AttributeOverride(name = "attendanceUserId", column = @Column(name = "attendanceUserId", length = 20, nullable = false))
public class AttUser extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "考勤主题ID",type="varchar(20)",explain="考勤主题Id")
    @Column(name = "attendanceThemeId", length = 20, nullable = false)
    private String attendanceThemeId;
    public String getAttendanceThemeId() {
 		return attendanceThemeId;
 	}
 	public void setAttendanceThemeId(String attendanceThemeId) {
 		this.attendanceThemeId = attendanceThemeId;
 	}
       
    @FieldInfo(name = "用户ID",type="varchar(20)",explain="用户Id")
    @Column(name = "userId", length = 20, nullable = false)
    private String userId;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
    
    
    @FieldInfo(name = "姓名",type="nvarchar(36)",explain="用户姓名")
    @Formula("(SELECT ISNULL(a.name,'') FROM T_PT_User a WHERE a.userId=userId)")
    private String name;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
   
    @FieldInfo(name = "学号",type="nvarchar(36)",explain="用户学号")
    @Formula("(SELECT ISNULL(a.userNumb,'') FROM T_PT_User a WHERE a.userId=userId)")
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