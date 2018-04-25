package com.yc.q1.controller.system;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.model.base.pt.card.PtCardType;
import com.yc.q1.model.base.pt.system.PtMealType;
import com.yc.q1.service.base.pt.system.PtMealTypeService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 岗位管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PtMealType")
public class PtMealTypeController extends FrameWorkController<PtMealType>{

	@Resource
	PtMealTypeService thisService; // service层接口
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;
	/**
	 * 标准的查询列表功能
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(PtMealType entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<PtMealType> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), false);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	
	/**
	 * 
	 * doAdd @Title: doAdd @Description: TODO @param @param BizTJob
	 * 实体类 @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Auth("MEALTYPEINFO_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtMealType entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		// String courseCode = entity.getCourseCode();
		String mealName = entity.getMealName();
		// 此处为放在入库前的一些检查的代码，如唯一校验等
		String sql = "SELECT cardTypeId FROM T_PT_CardType WHERE cardTypeName='"+mealName+"'";
		Integer count = thisService.getQueryCountBySql(sql);
		if (count!=0) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"卡类名称不能重复！\""));
			return;
		}
		PtMealType resultEntity = thisService.doAddPtMealType(entity);
		// 返回实体到前端界面
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(resultEntity)));
	}
	
	/**
	 * doUpdate编辑记录 @Title: doUpdate @Description: TODO @param @param
	 * BizTJob @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@Auth("MEALTYPEINFO_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(PtMealType entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String userCh = getCurrentSysUser().getId();
		PtMealType resultEntity = thisService.doUpdatePtMealType(entity);
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(resultEntity)));
	}
	

}
