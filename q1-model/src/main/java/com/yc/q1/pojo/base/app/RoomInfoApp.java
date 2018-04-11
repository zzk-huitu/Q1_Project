package com.yc.q1.pojo.base.app;

import com.yc.q1.model.base.pt.build.RoomInfo;

public class RoomInfoApp {
	private boolean message;//调用结果 ture&false
	private String messageInfo;//返回状态信息
	private RoomInfo obj;
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
	public RoomInfo getObj() {
		return obj;
	}
	public void setObj(RoomInfo obj) {
		this.obj = obj;
	}
}
