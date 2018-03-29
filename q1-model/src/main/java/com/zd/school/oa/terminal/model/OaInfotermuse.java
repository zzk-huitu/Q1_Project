package com.zd.school.oa.terminal.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: OaInfotermuse 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 终端使用历史(T_PT_InfoTerminalHistory)实体类.
 * date: 2017-01-14
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_InfoTerminalHistory")
@AttributeOverride(name = "infoTerminalHistoryId", column = @Column(name = "infoTerminalHistoryId", length = 20, nullable = false))
public class OaInfotermuse extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "终端主键ID")
    @Column(name = "terminalId", length = 20, nullable = false)
    private String terminalId;
  
        
    public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@FieldInfo(name = "终端号")
    @Column(name = "terminalIdNo", length = 6, nullable = false)
    private String terminalIdNo;
  
        
    public String getTerminalIdNo() {
		return terminalIdNo;
	}
	public void setTerminalIdNo(String terminalIdNo) {
		this.terminalIdNo = terminalIdNo;
	}

	@FieldInfo(name = "使用房间ID")
    @Column(name = "roomId", columnDefinition="varchar(20) defalut ''", nullable = true)
    private String roomId;
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoomId() {
        return roomId;
    }
        
    @FieldInfo(name = "使用房间名称")
    @Column(name = "roomName", columnDefinition="nvarchar(64) defalut ''", nullable = true)
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