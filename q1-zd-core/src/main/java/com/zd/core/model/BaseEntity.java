/**
 * Project Name:zd-core
 * File Name:BaseEntity.java
 * Package Name:com.zd.core.model
 * Date:2016年5月30日下午8:15:46
 * Copyright (c) 2016 ZDKJ All Rights Reserved.
 *
*/

package com.zd.core.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.util.DateTimeDeserializer;
import com.zd.core.util.DateTimeSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName:BaseEntity Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2016年5月30日 下午8:15:46
 * 
 * @author luoyibo
 * @version
 * @since JDK 1.8
 * @see
 */

@MappedSuperclass
public abstract class BaseEntity {
		
	@Id
	@FieldInfo(name = "id", type = "ID")
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", length = 20, nullable = false)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@FieldInfo(name = "创建时间")
	@Column(name = "createTime", nullable = true, columnDefinition = "datetime", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date createTime = new Date();

	@FieldInfo(name = "创建人")
	@Column(name = "createUser", length = 36)
	private String createUser;

	@FieldInfo(name = "最后修改时间")
	@Column(name = "updateTime", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date updateTime = new Date();

	@FieldInfo(name = "修改人")
	@Column(name = "updateUser", length = 36)
	private String updateUser;

	@FieldInfo(name = "版本")
	@Version
	@Column(name = "version", nullable = false)
	private Integer version;

	@FieldInfo(name = "是否删除",explain = "0-未删除 1-已删除")
	@Column(name = "isDelete",columnDefinition = "smallint DEFAULT 0 ",nullable = false)
	private Integer isDelete = 0;

	@FieldInfo(name = "排序字段")
	@Column(name = "orderIndex",columnDefinition = "int DEFAULT 0 ",nullable = false)
	private Integer orderIndex=0;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public BaseEntity() {
		super();		
	}

	public BaseEntity(String id) {
		super();
		this.id = id;
	}
}
