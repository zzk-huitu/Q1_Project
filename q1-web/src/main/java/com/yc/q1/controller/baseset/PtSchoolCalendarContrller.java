package com.yc.q1.controller.baseset;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.basic.PtSchoolCalendar;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.basic.PtSchoolCalendarService;
@Controller
@RequestMapping("/PtSchoolCalendar")
public class PtSchoolCalendarContrller extends FrameWorkController<PtSchoolCalendar> implements Constant{
	@Resource
	PtSchoolCalendarService thisService; // service层接口
	
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public @ResponseBody List<PtSchoolCalendar> list(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<PtSchoolCalendar> lists = thisService.queryAll();// 执行查询方法
		return lists;
	}

	@RequestMapping("/doAdd")
	public void doAdd(@ModelAttribute PtSchoolCalendar entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {

		// 此处为放在入库前的一些检查的代码，如唯一校验等

		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();

		entity = thisService.doAddEntity(entity, currentUser);// 执行增加方法
		if (ModelUtil.isNotNull(entity))
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
		else
			writeJSON(response, jsonBuilder.returnFailureJson("\"数据增加失败,详情见错误日志\""));
		
	}

	@RequestMapping("/doUpdate")
	public void doUpdate(@ModelAttribute PtSchoolCalendar entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {
		PtUser currentUser = getCurrentSysUser();
		
		PtSchoolCalendar perEntity = thisService.get(entity.getId());
		BeanUtils.copyPropertiesExceptNull(perEntity, entity);
		String createUserId = entity.getCreateUser();// 获取创建日历的用户id
		if (!createUserId.equals(currentUser.getId())) {
			writeJSON(response, jsonBuilder.returnFailureJson("'修改失败,不能修改非自己创建的校历!'"));
			return;

		}
		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
		entity = thisService.merge(perEntity);// 执行修改方法
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));

	}

	@RequestMapping("/doDelete")
	public void doDelete(@ModelAttribute PtSchoolCalendar  entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {
		String delIds = request.getParameter("id");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入主键\""));
			return;
		} else {
			
			PtSchoolCalendar  perEntity = thisService.get( entity.getId());
			PtUser currentUser = getCurrentSysUser();
			String createUserId=entity.getCreateUser();
			if (!createUserId.equals(currentUser.getId())) {
				writeJSON(response, jsonBuilder.returnFailureJson("'删除失败,不能删除非自己创建的校历!'"));
				return;
			}
			boolean flag = thisService.deleteByPK(delIds);
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("'删除成功'"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("'删除失败'"));
			}
			
		}
	
	}

}
