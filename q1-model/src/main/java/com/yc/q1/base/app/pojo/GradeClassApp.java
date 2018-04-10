package com.yc.q1.base.app.pojo;

import com.yc.q1.base.pt.basic.model.GradeClass;


public class GradeClassApp {
	
	private boolean message;//掉用结果 ture&false
	private String messageInfo;//返回状态信息
	private GradeClass gradeClass;//班级信息对象
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
	public GradeClass getGradeClass() {
		return gradeClass;
	}
	public void setGradeClass(GradeClass gradeClass) {
		this.gradeClass = gradeClass;
	}
	
	
}
