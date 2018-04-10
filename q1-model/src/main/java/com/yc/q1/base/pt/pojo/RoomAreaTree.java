/**
 * Project Name:school-model
 * File Name:BuildRoomAreaTree.java
 * Package Name:com.zd.school.build.define.model
 * Date:2016年8月24日上午9:55:33
 * Copyright (c) 2016 ZDKJ All Rights Reserved.
 *
*/

package com.yc.q1.base.pt.pojo;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.extjs.ExtTreeNode;

import java.util.List;

/**
 * POJO 实体类（待修改）
 * 
 * 注：移除areaStatu区域状态字段
 * 
 * @author ZZK
 *
 */
public class RoomAreaTree extends ExtTreeNode<RoomAreaTree> {
	@FieldInfo(name = "区域编码")
	private String areaCode;

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	@FieldInfo(name = "区域类型")
	private String areaType;

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getAreaType() {
		return areaType;
	}



	@FieldInfo(name = "区域说明")
	private String areaExplains;

	public void setAreaExplains(String areaExplains) {
		this.areaExplains = areaExplains;
	}

	public String getAreaExplains() {
		return areaExplains;
	}

	@FieldInfo(name = "区域地址")
	private String areaAddress;

	public void setAreaAddress(String areaAddress) {
		this.areaAddress = areaAddress;
	}

	public String getAreaAddress() {
		return areaAddress;
	}

	@FieldInfo(name = "上级区域")
	private String parentNode;

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public RoomAreaTree(String id, List<RoomAreaTree> children) {

		super(id, children);

	}

	@FieldInfo(name = "区域房间数")
	private Integer roomCount;

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public RoomAreaTree(String id, String text, String iconCls, Boolean leaf, Integer level, String treeid,
			List<RoomAreaTree> children, String areaCode, String areaType,String areaDesc,
			String areaAddr, String parentNode, Integer orderIndex, Integer roomCount) {

		//super(id, text, iconCls, leaf, level, treeid, children, parentNode, orderIndex);
		super(id,text,iconCls,leaf,level,treeid,parentNode,orderIndex,children);
		this.areaCode = areaCode;
		this.areaType = areaType;
		this.areaExplains = areaDesc;
		this.areaAddress = areaAddr;
		this.parentNode = parentNode;
		// this.orderIndex = orderIndex;
		this.roomCount = roomCount;
	}
}
