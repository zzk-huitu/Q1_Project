package com.zd.school.jw.ecc.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.school.plartform.baseset.model.BaseAttachment;

/**
 * 
 * ClassName: EccClasselegant Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 班级风采实体类. date: 2018-03-28 注：备用字段5作为 课表是否启用的标识
 * 
 * @author tongzy 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_ClassMien")
@AttributeOverride(name = "classMienId", column = @Column(name = "classMienId", length = 20, nullable = false))
public class EccClasselegant extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "classId",type="varchar(20)",explain="班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Formula("(SELECT a.CLASS_NAME FROM JW_T_GRADECLASS a WHERE a.CLAI_ID=CLAI_ID )")
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@FieldInfo(name = "title",type="nvarchar(20)",explain="标题")
	@Column(name = "title", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Transient
	@FieldInfo(name = "文件列表")
	private List<BaseAttachment> fileList;

	public List<BaseAttachment> getFileList() {
		return fileList;
	}

	public void setFileList(List<BaseAttachment> fileList) {
		this.fileList = fileList;
	}
}
