package com.yc.q1.pojo.base.app;

import java.util.ArrayList;
import java.util.List;

import com.yc.q1.model.base.pt.basic.CourseArrange;


public class CourseArrangeApp {
	private boolean message;//调用结果 ture&false
	private String messageInfo;//返回状态信息
	private List<CourseArrange> list;
	
	public boolean isMessage() {
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
	public List<CourseArrange> getList() {
		return list;
	}
	public void setList(List<CourseArrange> list) {
		this.list = list;
	}
	
	


}
