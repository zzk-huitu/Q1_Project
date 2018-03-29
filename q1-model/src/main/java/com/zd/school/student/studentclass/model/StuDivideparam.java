package com.zd.school.student.studentclass.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: StuDivideparam 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 学生分班参数实体类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_DivideParameter")
@AttributeOverride(name = "DivideParameterId", column = @Column(name = "DivideParameterId", length = 36, nullable = false))
public class StuDivideparam extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "班级ID")
    @Column(name = "classId", length = 36, nullable = true)
    private String classId;
  
        
    @FieldInfo(name = "记录ID")
    @Column(name = "divideRecodeId", length = 36, nullable = true)
    private String divideRecodeId;
  
        
    public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getDivideRecodeId() {
		return divideRecodeId;
	}
	public void setDivideRecodeId(String divideRecodeId) {
		this.divideRecodeId = divideRecodeId;
	}

	@FieldInfo(name = "分班类型")
    @Column(name = "divideType", length = 4, nullable = false)
    private String divideType;
    public void setDivideType(String divideType) {
        this.divideType = divideType;
    }
    public String getDivideType() {
        return divideType;
    }
        
    @FieldInfo(name = "优先级别，1-重点班-2特长班3-普通班")
    @Column(name = "divideLevel", length = 10, nullable = false)
    private Integer divideLevel;
    public void setDivideLevel(Integer divideLevel) {
        this.divideLevel = divideLevel;
    }
    public Integer getDivideLevel() {
        return divideLevel;
    }
        
    @FieldInfo(name = "分班人数")
    @Column(name = "divideCount", length = 10, nullable = false)
    private Integer divideCount;
    public void setDivideCount(Integer divideCount) {
        this.divideCount = divideCount;
    }
    public Integer getDivideCount() {
        return divideCount;
    }
        

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}