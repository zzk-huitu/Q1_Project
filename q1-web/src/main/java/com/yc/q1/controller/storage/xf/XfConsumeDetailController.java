package com.yc.q1.controller.storage.xf;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateUtil;
import com.yc.q1.model.storage.xf.XfConsumeDetail;
import com.yc.q1.service.storage.xf.XfConsumeDetailService;
@Controller
@RequestMapping("/XfConsumeDetail")
public class XfConsumeDetailController extends FrameWorkController<BaseEntity> implements Constant{
	
	@Resource
	XfConsumeDetailService thisService; // service层接口
	
	@RequestMapping(value = { "/getUserConsumeList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void getUserConsumeList(XfConsumeDetail entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		//Integer termNo = Integer.valueOf(request.getParameter("termNo"));
		String userId = request.getParameter("userId");
		String consumeDate = request.getParameter("consumeDate");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if(consumeDate==null){
			consumeDate="month";
		}
		
		String startDate ;
		String endDate = DateUtil.formatDate(new Date()); 
		if(consumeDate.equals("month")){
			calendar.add(calendar.MONTH, -1);
			startDate = DateUtil.formatDate(calendar.getTime());
		}else if(consumeDate.equals("halfYear")) {	
			calendar.add(calendar.MONTH, -6);
			startDate = DateUtil.formatDate(calendar.getTime());
		}else{
			calendar.add(calendar.YEAR, -1);	
			startDate = DateUtil.formatDate(calendar.getTime());
		}
		Date date = new Date();
		String sql =" select a.userId,a.consumeDate,a.consumeValue,a.cardNo,a.cardValue,a.termRecordId,a.termName,a.bagType,b.mealName from Q1_Storage.dbo.T_XF_ConsumeDetail a "
				+ " left join Q1_Base.dbo.T_PT_MealType b on  a.mealTypeId = b.mealTypeId and a.consumeValue >0"
				//+ " where a.termNo = '"+termNo+"' and a.userId = '"+userId+"' and a.consumeDate between '"+consumeDate+"' and '"+DateUtil.formatDateTime(date)+"' "
				+ " where  a.consumeDate between '"+startDate+"' and '"+endDate+"' "
				+ " order by a.termRecordId ";
		List<Map<String, Object>> qr = thisService.queryMapBySql(sql);
		strData = jsonBuilder.buildObjListToJson(Long.valueOf(qr.size()), qr, true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
}
