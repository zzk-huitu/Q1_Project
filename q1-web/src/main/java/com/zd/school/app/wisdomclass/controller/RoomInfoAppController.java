package com.zd.school.app.wisdomclass.controller;


import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.q1.model.base.pt.basic.PtInfoTerminal;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.pojo.base.app.RoomInfoApp;
import com.yc.q1.service.base.pt.basic.InfoTerminalService;
import com.yc.q1.service.base.pt.build.RoomInfoService;

@Controller
@RequestMapping("/app/RoomInfo")
public class RoomInfoAppController {
	@Resource
	private RoomInfoService thisService;
	
	@Resource
	private InfoTerminalService termService; // 终端设备serice层接口
	
	/**
	 * 获取房间信息
	 * @param termCode
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = { "/getRoomInfo" }, method = RequestMethod.GET)
	public  RoomInfoApp getRoomInfo(@RequestParam(value="termCode") String termCode){
		RoomInfoApp info=new RoomInfoApp();
		
		PtInfoTerminal roomTerm = termService.getByProerties("terminalNo", termCode);
		
		if (roomTerm==null) {
			info.setMessage(false);
			info.setMessageInfo("没有找到该终端设备！");
			return info;
		}
		
		PtRoomInfo room  = thisService.get(roomTerm.getRoomId());;
		if (room == null) {
			info.setMessage(false);
			info.setMessageInfo("没有找到该终端对应的房间！");
			return info;
		}
		
		info.setMessage(true);
		info.setMessageInfo("请求成功！");
		info.setObj(room);
		return info;
	}
}
