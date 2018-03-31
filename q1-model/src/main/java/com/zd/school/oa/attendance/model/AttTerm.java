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
 * ClassName: AttTerm 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 考勤设备(T_PT_AttendanceDevice)实体类.
 * date: 2017-05-15
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_AttendanceDevice")
@AttributeOverride(name = "attendanceDeviceId", column = @Column(name = "attendanceDeviceId", length = 20, nullable = false))
public class AttTerm extends BaseEntity implements Serializable{
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

	@FieldInfo(name = "终端号",type="varchar(6)",explain="信息终端的终端号")
    @Formula("(SELECT ISNULL(a.terminalNO,'') FROM T_PT_InfoTerminal a WHERE a.infoTerminalId=attendanceDeviceId)")
    private String terminalNo;
    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }
    public String getTerminalNoo() {
        return terminalNo;
    }
        
    @FieldInfo(name = "房间ID",type="varchar(20)",explain="信息终端的使用房间Id")
    @Formula("(SELECT ISNULL(a.roomId,'') FROM T_PT_InfoTerminal a WHERE a.infoTerminalId=attendanceDeviceId)")
    private String roomId;
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoomId() {
        return roomId;
    }
        
    @FieldInfo(name = "房间名称",type="nvarchar(64)",explain="信息终端的使用房间名称")
    @Formula("(SELECT ISNULL(a.roomName,'') FROM T_PT_InfoTerminal a WHERE a.infoTerminalId=attendanceDeviceId)")
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