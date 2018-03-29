package com.zd.school.build.allot.model;

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
 * ClassName: JwOfficeallot 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: JW_T_OFFICEALLOT实体类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "JW_T_OfficeAllot")
@AttributeOverride(name = "officeAllotId", column = @Column(name = "officeAllotId", length = 36, nullable = false))
public class JwOfficeAllot extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "房间主键")
    @Column(name = "roomId", length = 36, nullable = true)
    private String roomId;

    @FieldInfo(name = "教师主键")
    @Column(name = "teacherId", length = 50, nullable = true)
    private String teacherId;

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
    @Formula("(SELECT A.XM FROM SYS_T_USER A  WHERE A.USER_ID=TTEAC_ID)")
    @FieldInfo(name = "用于选择框显示教师姓名")
    private String xm;
    
    @Formula("(SELECT A.NODE_TEXT FROM dbo.BUILD_T_ROOMAREA A "
    		+ " WHERE A.AREA_ID=(SELECT B.AREA_ID FROM dbo.BUILD_T_ROOMINFO B"
    		+ " WHERE B.ROOM_ID=ROOM_ID))")
	@FieldInfo(name = "楼层名称")
	private String areaName;

    @Formula("(SELECT A.NODE_TEXT FROM dbo.BUILD_T_ROOMAREA A"
    		+ " WHERE A.AREA_ID=(SELECT B.PARENT_NODE"
    		+ " FROM dbo.BUILD_T_ROOMAREA B WHERE B.AREA_ID="
    		+ " (SELECT C.AREA_ID FROM dbo.BUILD_T_ROOMINFO C"
    		+ " WHERE C.ROOM_ID=ROOM_ID)))")
	@FieldInfo(name = "楼栋名称")
	private String upAreaName;
    
    @Formula("(SELECT A.USER_NUMB FROM SYS_T_USER A  WHERE A.USER_ID=TTEAC_ID)")
    @FieldInfo(name = "教师工号")
    private String gh;
    
    @Formula("(SELECT A.ROOM_NAME FROM dbo.BUILD_T_ROOMINFO A WHERE A.ROOM_ID=ROOM_ID)")
    @FieldInfo(name = "房间名")
    private String roomName;
    
    
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
    public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	public String getGh() {
        return gh;
    }
    public void setGh(String gh) {
        this.gh = gh;
    }

	public String getUpAreaName() {
		return upAreaName;
	}
	public void setUpAreaName(String upAreaName) {
		this.upAreaName = upAreaName;
	}
}