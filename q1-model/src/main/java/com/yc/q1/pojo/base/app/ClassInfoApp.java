package com.yc.q1.pojo.base.app;

import java.util.List;

import com.yc.q1.model.base.pt.basic.PtGradeClass;
import com.yc.q1.model.base.pt.basic.PtTeacherBaseInfo;
import com.yc.q1.model.base.pt.wisdomclass.PtClassRedFlag;
import com.yc.q1.model.base.pt.wisdomclass.PtClassStar;


public class ClassInfoApp {
	private boolean message;//调用结果 ture&false
	private String messageInfo;//返回状态信息
	private PtGradeClass ClassInfo;//班级信息
	private PtTeacherBaseInfo teacherInfo;//班主任信息
	private PtClassStar classstarInfo; //班级评星信息
	private List<PtClassRedFlag> redflagList; //班级流动红旗
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
	public PtGradeClass getClassInfo() {
		return ClassInfo;
	}
	public void setClassInfo(PtGradeClass classInfo) {
		ClassInfo = classInfo;
	}
	public PtTeacherBaseInfo getTeacherInfo() {
		return teacherInfo;
	}
	public void setTeacherInfo(PtTeacherBaseInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}
	public PtClassStar getClassstarInfo() {
		return classstarInfo;
	}
	public void setClassstarInfo(PtClassStar classstarInfo) {
		this.classstarInfo = classstarInfo;
	}
	public List<PtClassRedFlag> getRedflagList() {
		return redflagList;
	}
	public void setRedflagList(List<PtClassRedFlag> redflagList) {
		this.redflagList = redflagList;
	}
	
}
