package com.zd.school.jw.model.app;

import java.util.List;

import com.zd.school.jw.ecc.model.ClassRedFlag;
import com.zd.school.jw.ecc.model.ClassStar;
import com.zd.school.jw.eduresources.model.GradeClass;
import com.zd.school.teacher.teacherinfo.model.TeacherBaseInfo;


public class ClassInfoApp {
	private boolean message;//调用结果 ture&false
	private String messageInfo;//返回状态信息
	private GradeClass ClassInfo;//班级信息
	private TeacherBaseInfo teacherInfo;//班主任信息
	private ClassStar classstarInfo; //班级评星信息
	private List<ClassRedFlag> redflagList; //班级流动红旗
	public boolean getMessage() {
		return message;
	}
	public void setMessage(boolean message) {
		this.message = message;
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	public GradeClass getClassInfo() {
		return ClassInfo;
	}
	public void setClassInfo(GradeClass classInfo) {
		ClassInfo = classInfo;
	}
	public TeacherBaseInfo getTeacherInfo() {
		return teacherInfo;
	}
	public void setTeacherInfo(TeacherBaseInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}
	public ClassStar getClassstarInfo() {
		return classstarInfo;
	}
	public void setClassstarInfo(ClassStar classstarInfo) {
		this.classstarInfo = classstarInfo;
	}
	public List<ClassRedFlag> getRedflagList() {
		return redflagList;
	}
	public void setRedflagList(List<ClassRedFlag> redflagList) {
		this.redflagList = redflagList;
	}
	
}
