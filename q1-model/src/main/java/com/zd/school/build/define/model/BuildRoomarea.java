package com.zd.school.build.define.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.TreeNodeEntity;

/**
 * 
 * ClassName: BuildRoomarea Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 教室区域实体类. date: 2016-08-23
 * 
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_RoomArea")
@AttributeOverride(name = "roomAreaId", column = @Column(name = "roomAreaId", length = 36, nullable = false))
public class BuildRoomarea extends TreeNodeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "区域编码")
    @Column(name = "areaCode", length = 32, nullable = true)
    private String areaCode;

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    @FieldInfo(name = "区域类型")
    @Column(name = "areaType", length = 10, nullable = true)
    private String areaType;

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getAreaType() {
        return areaType;
    }

    @FieldInfo(name = "区域状态")
    @Column(name = "areaStatu", length = 10, nullable = true)
    private Integer areaStatu;

    public void setAreaStatu(Integer areaStatu) {
        this.areaStatu = areaStatu;
    }

    public Integer getAreaStatu() {
        return areaStatu;
    }

    @FieldInfo(name = "区域说明")
    @Column(name = "areaExplains", length = 128, nullable = true)
    private String areaExplains;

    public void setAreaExplains(String areaExplains) {
        this.areaExplains = areaExplains;
    }

    public String getAreaExplains() {
        return areaExplains;
    }

    @FieldInfo(name = "区域地址")
    @Column(name = "areaAddress", length = 255, nullable = true)
    private String areaAddress;

    public void setAreaAddress(String areaAddress) {
        this.areaAddress = areaAddress;
    }

    public String getAreaAddress() {
        return areaAddress;
    }

    /**
     * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
     * 
     * @Transient
     * @FieldInfo(name = "") private String field1;
     */
    @FieldInfo(name = "区域房间数")
    @Formula("(SELECT count(a.AREA_ID) FROM BUILD_T_ROOMINFO a WHERE a.AREA_ID=AREA_ID AND a.ISDELETE=0)")
    private Integer roomCount;

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    @FieldInfo(name = "上级区域名称")
    @Formula("(SELECT isnull(a.NODE_TEXT,'ROOT') FROM BUILD_T_ROOMAREA a WHERE a.AREA_ID=parent_node)")
    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @FieldInfo(name = "上级区域类型")
    @Transient
    private String parentType;

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public BuildRoomarea() {

        super();
        // TODO Auto-generated constructor stub
    }

    public BuildRoomarea(String uuid) {

        super(uuid);
        // TODO Auto-generated constructor stub

    }

}