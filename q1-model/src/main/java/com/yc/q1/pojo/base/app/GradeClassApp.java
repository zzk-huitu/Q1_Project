package com.yc.q1.pojo.base.app;

import com.yc.q1.model.base.pt.basic.PtGradeClass;


public class GradeClassApp {
	
	private boolean message;//掉用结果 ture&false
	private String messageInfo;//返回状态信息
	private PtGradeClass gradeClass;//班级信息对象
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
	public PtGradeClass getGradeClass() {
		return gradeClass;
	}
	public void setGradeClass(PtGradeClass gradeClass) {
		this.gradeClass = gradeClass;
	}
	
	
}