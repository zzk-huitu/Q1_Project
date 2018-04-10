package com.yc.q1.base.pt.wisdomclass.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.yc.q1.base.pt.basic.model.Attachment;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 班级风采
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_ClassMien")
@AttributeOverride(name = "id", column = @Column(name = "classMienId", length = 20, nullable = false) )
public class ClassMien extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "班级Id", type = "varchar(20) NOT NULL", explain = "班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	// @FieldInfo(name = "班级名称")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String className;

	@FieldInfo(name = "标题", type = "nvarchar(16) NOT NULL", explain = "标题")
	@Column(name = "title", columnDefinition = "nvarchar(16)", nullable = false)
	private String title;

	@Transient
	// @FieldInfo(name = "文件列表")
	private List<Attachment> fileList;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Attachment> getFileList() {
		return fileList;
	}

	public void setFileList(List<Attachment> fileList) {
		this.fileList = fileList;
	}

	public ClassMien() {
		super();
	}

	public ClassMien(String id) {
		super(id);
	}

}
