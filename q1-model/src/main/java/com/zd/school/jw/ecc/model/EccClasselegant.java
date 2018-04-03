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
 * 班级风采
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_ClassMien")
@AttributeOverride(name = "id", column = @Column(name = "classMienId", length = 20, nullable = false) )
public class EccClasselegant extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "班级Id", type = "varchar(20) NOT NULL", explain = "班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	// @FieldInfo(name = "班级名称")
	@Formula("(SELECT a.className FROM T_PT_GrageClass a WHERE a.classId=classId )")
	private String className;

	@FieldInfo(name = "标题", type = "nvarchar(16) NOT NULL", explain = "标题")
	@Column(name = "title", columnDefinition = "nvarchar(16)", nullable = false)
	private String title;

	@Transient
	// @FieldInfo(name = "文件列表")
	private List<BaseAttachment> fileList;

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

	public List<BaseAttachment> getFileList() {
		return fileList;
	}

	public void setFileList(List<BaseAttachment> fileList) {
		this.fileList = fileList;
	}

	public EccClasselegant() {
		super();
	}

	public EccClasselegant(String id) {
		super(id);
	}

}
