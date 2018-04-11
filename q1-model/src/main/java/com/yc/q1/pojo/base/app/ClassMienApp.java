package com.yc.q1.pojo.base.app;

import java.util.List;

import com.yc.q1.model.base.pt.wisdomclass.PtClassMien;

public class ClassMienApp {
	private boolean message;//调用结果 ture&false
	private String messageInfo;//返回状态信息
	private List<PtClassMien> elegantList;
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
	public List<PtClassMien> getElegantList() {
		return elegantList;
	}
	public void setElegantList(List<PtClassMien> elegantList) {
		this.elegantList = elegantList;
	}
	
	
}
