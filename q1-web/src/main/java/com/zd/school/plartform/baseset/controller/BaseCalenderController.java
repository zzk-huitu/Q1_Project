package com.zd.school.plartform.baseset.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.base.pt.basic.service.CalenderService;
import com.yc.q1.model.base.pt.basic.PtCalender;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.annotation.Auth;
import com.zd.core.constant.Constant;
import com.zd.core.constant.StatuVeriable;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.util.ModelUtil;
import com.zd.core.util.StringUtils;

/**
 * 作息目录
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/BaseCalender")
public class BaseCalenderController extends FrameWorkController<PtCalender> implements Constant {

	@Resource
	CalenderService thisService; // service层接口
	
	/**
	 * 获取目录树列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getCanderTree")
	public void getDicTree(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuffer pageJson = null;
		pageJson = new StringBuffer("[{\"text\":\"ROOT\",\"expanded\":true,\"children\"" + ":");

		pageJson.append("[");

		String hql = "from Calender j where 1=1 and j.isDelete=0";
		List<PtCalender> list = thisService.queryByHql(hql);
		for (PtCalender jwTCander : list) {
			pageJson.append("{");
			pageJson.append("\"text\":\"" + jwTCander.getCalenderName() + "\",");
			pageJson.append("\"uuid\":\"" + jwTCander.getId() + "\",");
			pageJson.append("\"leaf\":true");
			pageJson.append("}");
			pageJson.append(",");
		}
		if (list.size() > 0) {
			pageJson.deleteCharAt(pageJson.length() - 1);
		}
		pageJson.append("]");
		pageJson.append("}");
		pageJson.append("]");

		System.out.println(pageJson);

		writeJSON(response, pageJson.toString());
	}

	/**
	 * 获取目录列表
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(PtCalender entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据

		QueryResult<PtCalender> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 添加目录
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("SCHOOLCALENDAR_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtCalender entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 此处为放在入库前的一些检查的代码，如唯一校验等

		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();

		entity = thisService.doAddEntity(entity, currentUser);// 执行增加方法
		if (ModelUtil.isNotNull(entity))
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
		else
			writeJSON(response, jsonBuilder.returnFailureJson("\"数据增加失败,详情见错误日志\""));
	 }

	/**
	 * 删除目录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Auth("SCHOOLCALENDAR_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnFailureJson("'未选择需删除的信息'"));
			return;
		}
		try {
			boolean flag =thisService.doDeleteEntity(delIds);
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("'删除成功'"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("'删除失败'"));
			}
		} catch (Exception e) {
			writeJSON(response, jsonBuilder.returnFailureJson("'删除失败,请刷新重试！'"));
		}
	}

	

	/**
	 * doRestore还原删除的记录 @Title: doRestore @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@RequestMapping("/doRestore")
	public void doRestore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入还原主键'"));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISNOTDELETE, currentUser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("'还原成功'"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("'还原失败'"));
			}
		}
	}

	/**
	 * 更新目录
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("SCHOOLCALENDAR_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(PtCalender entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 入库前检查代码

		// 获取当前的操作用户

		PtUser currentUser = getCurrentSysUser();
        entity = thisService.doUpdateEntity(entity, currentUser);// 执行修改方法
		if (ModelUtil.isNotNull(entity))
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
		else
			writeJSON(response, jsonBuilder.returnFailureJson("\"数据修改失败,详情见错误日志\""));
		

	}

	/**
	 * 启用目录
	 * 把之前此校区的启用目录给设置为未启用
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("SCHOOLCALENDAR_use")
	@RequestMapping("/doUpdateState")
	public void doUpdateState(HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 入库前检查代码
		String calenderIds = request.getParameter("ids");
		String campusNames = request.getParameter("campusNames");
		
		int statu = thisService.updateStatu(calenderIds,campusNames);
		if (statu == 1) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\'启用成功！\'"));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\'请求失败！\'"));
		}

	}
}
