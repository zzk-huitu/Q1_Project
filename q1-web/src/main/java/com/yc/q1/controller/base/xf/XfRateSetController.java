package com.yc.q1.controller.base.xf;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.xf.XfRateSet;
import com.yc.q1.service.base.xf.XfRateSetService;
import com.yc.q1.service.base.xf.XfRateTermSetService;
/**
 * 折扣参数设置
 *
 */
@Controller
@RequestMapping("/XfRateSet")
public class XfRateSetController extends FrameWorkController<BaseEntity> implements Constant{
	@Resource
	XfRateSetService thisService; // service层接口
	@Resource
	XfRateTermSetService xfRateTermSetService; // service层接口
	/**
	 * list查询 @Title: list @Description: TODO @param @param entity
	 * 实体类 @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute XfRateSet entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<XfRateSet> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	/**
	 * 
	 * @Title: 增加新实体信息至数据库 @Description: TODO @param @param MjUserright
	 *         实体类 @param @param request @param @param response @param @throws
	 *         IOException 设定参数 @return void 返回类型 @throws
	 */
	//@Auth("")
	@RequestMapping("/doAdd")
	public void doAdd(XfRateSet entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		Integer cardTypeId = entity.getCardTypeId();
		Integer mealTypeId = entity.getMealTypeId();
		// 此处为放在入库前的一些检查的代码，如唯一校验等
		String sql = "SELECT cardTypeId FROM T_XF_RateSet WHERE cardTypeId='" + cardTypeId
				+ "' and mealTypeId='" + mealTypeId + "'";
		Integer count = thisService.getQueryCountBySql(sql);
		if (count != 0) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"卡类名称+餐类名称不能重复！\""));
			return;
		}
		PtUser currentUser = getCurrentSysUser();
		entity = thisService.doAddEntity(entity, currentUser);// 执行增加方法
		if (ModelUtil.isNotNull(entity))
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
		else
			writeJSON(response, jsonBuilder.returnFailureJson("\"数据增加失败,详情见错误日志\""));

	}

	// @Auth("")
	@RequestMapping("/doUpdate")
	public void doUpdates(XfRateSet entity, HttpServletRequest request, HttpServletResponse response)
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
}
