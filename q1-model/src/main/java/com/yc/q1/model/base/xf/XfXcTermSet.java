package com.yc.q1.model.base.xf;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 限次设备绑定
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_XF_XcTermSet")
@AttributeOverride(name = "id", column = @Column(name = "xcTermId", length = 20, nullable = false) )
public class XfXcTermSet extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.XF; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "设备编号")
	@Column(name = "termId", length = 20, nullable = false)
	private String termId;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public XfXcTermSet() {
		super();
	}

	public XfXcTermSet(String id) {
		super(id);
	}
}