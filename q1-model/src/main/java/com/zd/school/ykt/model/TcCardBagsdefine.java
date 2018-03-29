package com.zd.school.ykt.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 钱包定义 ClassName: TcCardBagsdefine Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: (TC_Card_BagsDefine)实体类. date: 2017-03-21
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_CardBagsDefine")
@AttributeOverride(name = "cardBagsDefineId", column = @Column(name = "cardBagsDefineId", length = 20, nullable = false) )
public class TcCardBagsdefine extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "bagNo")
	@Column(name = "bagNo", nullable = false)
	private Short bagNo;

	@FieldInfo(name = "钱包名称")
	@Column(name = "bagName", columnDefinition="nvarchar(50)", nullable = false)
	private String bagName;

	@FieldInfo(name = "是否启用")
	@Column(name = "status", columnDefinition="defalut 0",nullable = true)
	private Boolean status;

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getStatus() {
		return status;
	}

	@FieldInfo(name = "钱包描述")
	@Column(name = "bagNotes", columnDefinition="nvarchar(500) defalut ''", nullable = true)
	private String bagNotes;

	public Short getBagNo() {
		return bagNo;
	}

	public void setBagNo(Short bagNo) {
		this.bagNo = bagNo;
	}

	public String getBagName() {
		return bagName;
	}

	public void setBagName(String bagName) {
		this.bagName = bagName;
	}

	public String getBagNotes() {
		return bagNotes;
	}

	public void setBagNotes(String bagNotes) {
		this.bagNotes = bagNotes;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}