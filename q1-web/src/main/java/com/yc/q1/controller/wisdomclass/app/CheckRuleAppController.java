package com.yc.q1.controller.wisdomclass.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.q1.model.base.pt.wisdomclass.PtAttenceRule;
import com.yc.q1.pojo.base.app.CommonApp;
import com.yc.q1.service.base.pt.wisdomclass.PtAttenceRuleService;

@Controller
@RequestMapping("/app/CheckRule")
public class CheckRuleAppController {

	@Resource
	private PtAttenceRuleService thisService; // 考勤规则

	@ResponseBody
	@RequestMapping(value = { "/getRule" }, method = RequestMethod.GET)
	public CommonApp<PtAttenceRule> getRule() {

		CommonApp<PtAttenceRule> app = new CommonApp<PtAttenceRule>();

		/* 暂不需要 */
		// OaInfoterm roomTerm = termService.getByProerties("termCode",
		// termCode);
		// if (roomTerm == null) {
		// app.setMessage(false);
		// app.setMessageInfo("没有找到该终端设备！");
		// return app;
		// }
		//
		// BuildRoominfo roominfo = brService.get(roomTerm.getRoomId());
		// if (roominfo == null) {
		// app.setMessage(false);
		// app.setMessageInfo("没有找到设备对应房间！");
		// return app;
		// }

		String[] propName = new String[] { "startUsing", "isDelete" };
		Object[] propValue = new Object[] { 1, 0 };
		PtAttenceRule rule = thisService.getByProerties(propName, propValue); // 查出启用的考勤规则

		if (rule == null) {
			app.setMessage(false);
			app.setMessageInfo("没有找到已启用的考勤规则！");
			return app;
		}

		app.setObj(rule);
		app.setMessage(true);
		app.setMessageInfo("请求成功！");
		return app;
	}

}