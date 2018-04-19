package com.yc.q1.controller.wisdomclass;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.wisdomclass.PtClassRedFlag;
import com.yc.q1.service.base.pt.wisdomclass.PtClassRedFlagService;

@Controller
@RequestMapping("/PtClassRedFlag")
public class PtClassRedFlagController extends FrameWorkController<PtClassRedFlag> implements Constant{
	@Resource
	PtClassRedFlagService thisService; // service层接口
	
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute PtClassRedFlag entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		PtUser currentUser = getCurrentSysUser();
		String redFlagType = request.getParameter("redFlagType");
		String filter = request.getParameter("filter");
		String whereSql = super.whereSql(request);
		String orderSql = super.orderSql(request);
		if (redFlagType == null) {
			redFlagType = "";
		}
		if(StringUtils.isNotEmpty(filter)){
			filter = filter.substring(0, filter.length()-1);
			filter+=",{\"type\":\"string\",\"comparison\":\"\",\"value\":\""+ redFlagType+"\",\"field\":\"redFlagType\"}"+"]";
		}else{
			filter="[{\"type\":\"string\",\"comparison\":\"\",\"value\":\""+ redFlagType+"\",\"field\":\"redFlagType\"}]";
		}
	    //QueryResult<EccClassredflag> qResult =thisService.queryPageResult(super.start(request), super.limit(request),super.sort(request), filter, true);
		QueryResult<PtClassRedFlag> qResult = thisService.list(super.start(request), super.limit(request),super.sort(request), filter, whereSql, orderSql, currentUser);
		strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	@Auth("REDFLAG_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtClassRedFlag entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 此处为放在入库前的一些检查的代码，如唯一校验等

		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();
		try {
			entity = thisService.doAddEntity(entity, currentUser);// 执行增加方法
			if (ModelUtil.isNotNull(entity))
				writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
			else
				writeJSON(response, jsonBuilder.returnFailureJson("'数据增加失败,详情见错误日志'"));
		} catch (Exception e) {
			writeJSON(response, jsonBuilder.returnFailureJson("'数据增加失败,详情见错误日志'"));
		}
	}
	@Auth("REDFLAG_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			try {
				boolean flag = thisService.doLogicDeleteByIds(delIds, currentUser);
				if (flag) {
					writeJSON(response, jsonBuilder.returnSuccessJson("'删除成功'"));
				} else {
					writeJSON(response, jsonBuilder.returnFailureJson("'删除失败,详情见错误日志'"));
				}
			} catch (Exception e) {
				writeJSON(response, jsonBuilder.returnFailureJson("'删除失败,详情见错误日志'"));
			}
		}
	}
	@Auth("REDFLAG_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(PtClassRedFlag entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 入库前检查代码

		// 获取当前的操作用户
		PtUser currentUser = getCurrentSysUser();
		try {
			entity = thisService.doUpdateEntity(entity, currentUser);// 执行修改方法
			if (ModelUtil.isNotNull(entity))
				writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
			else
				writeJSON(response, jsonBuilder.returnFailureJson("'数据修改失败,详情见错误日志'"));
		} catch (Exception e) {
			writeJSON(response, jsonBuilder.returnFailureJson("'数据修改失败,详情见错误日志'"));
		}

	}

}
