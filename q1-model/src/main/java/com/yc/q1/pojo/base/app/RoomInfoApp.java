package com.yc.q1.pojo.base.app;

import com.yc.q1.model.base.pt.build.PtRoomInfo;

public class RoomInfoApp {
	private boolean message;//调用结果 ture&false
	private String messageInfo;//返回状态信息
	private PtRoomInfo obj;
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
	public PtRoomInfo getObj() {
		return obj;
	}
	public void setObj(PtRoomInfo obj) {
		this.obj = obj;
	}
}
