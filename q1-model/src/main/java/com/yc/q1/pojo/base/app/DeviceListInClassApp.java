package com.yc.q1.pojo.base.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.q1.model.base.pt.device.PtTerm;


public class DeviceListInClassApp {
	private boolean message;//调用结果 ture&false
	private String messageInfo;//返回状态信息
	private List<Map<String, Object>> deviceInfo; 
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
	public List<Map<String, Object>> getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(List<Map<String, Object>> deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public void putVal(PtTerm term) {
		if(this.deviceInfo==null){
			deviceInfo=new ArrayList<Map<String, Object>>();
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("roomId",term.getRoomId() );
		map.put("termId", term.getId());
		map.put("termSN", term.getTermSn());
		map.put("termName", term.getTermName());
		map.put("termTypeId", term.getTermTypeId());
		map.put("note", term.getNotes());
		deviceInfo.add(map);
	}
	
}