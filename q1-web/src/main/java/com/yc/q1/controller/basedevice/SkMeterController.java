package com.yc.q1.controller.basedevice;

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
import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.sk.SkMeter;
import com.yc.q1.service.base.sk.SkMeterService;

/**
 * 水控流量计表
 * @author hucy
 *
 */
@Controller
@RequestMapping("/SkMeter")
public class SkMeterController extends FrameWorkController<SkMeter> implements Constant  {
	
	@Resource
	SkMeterService thisService; // service层接口

	/**
	 * list查询 @Title: list @Description: TODO @param @param entity
	 * 实体类 @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute SkMeter entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
	        QueryResult<SkMeter> qr = thisService.queryPageResult(super.start(request), super.limit(request),
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
	@Auth("WATER_METER_add")
	@RequestMapping("/doAdd")
	public void doAdd(
			SkMeter entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 获取当前操作用户
        PtUser currentUser = getCurrentSysUser();
      
        entity = thisService.doAddEntity(entity, currentUser);// 执行增加方法
        if (ModelUtil.isNotNull(entity))
            writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
        else
            writeJSON(response, jsonBuilder.returnFailureJson("\"数据增加失败,详情见错误日志\""));      
	}

	/**
	 * doDelete @Title: 逻辑删除指定的数据 @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@Auth("WATER_METER_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		if (StringUtils.isEmpty(ids)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入删除主键\""));
			return;
		} else {
			// 判断这些流量计是否正在被其他设备所绑定
			String hql = "select count(a.id) from SkMeterBind as a where a.meterId in ('" + ids.replace(",", "','")
					+ "') and a.isDelete=0";
			int count = thisService.getQueryCountByHql(hql);
			if (count > 0) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"这些流量计当前绑定了设备，请解除绑定后再删除！\""));
				return;
			}
			
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(ids, StatuVeriable.ISDELETE,currentUser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		}
	}

	/**
	 * doUpdate编辑记录 @Title: doUpdate @Description: TODO @param @param
	 * MjUserright @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@Auth("WATER_METER_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(SkMeter entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		PtUser currentUser = getCurrentSysUser();
		 entity = thisService.doUpdateEntity(entity, currentUser);// 执行修改方法
	        if (ModelUtil.isNotNull(entity))
	            writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	        else
	            writeJSON(response, jsonBuilder.returnFailureJson("\"数据修改失败,详情见错误日志\""));
		
		
	}

}
