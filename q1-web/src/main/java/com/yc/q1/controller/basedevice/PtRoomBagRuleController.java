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
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.device.PtRoomBagRule;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.device.PtRoomBagRuleService;

/**
 * 房间钱包规则
 * 
 * @author hucy
 *
 */
@Controller
@RequestMapping("/PtRoomBagRule")
public class PtRoomBagRuleController extends FrameWorkController<PtRoomBagRule> implements Constant {
	@Resource
	PtRoomBagRuleService thisService; // service层接口

	/**
	 * 房间钱包规则列表
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute PtRoomBagRule entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<PtRoomBagRule> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 添加
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("ROOM_BAG_RULE_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtRoomBagRule entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		String hql1 = " o.isDelete='0' ";
		// 此处为放在入库前的一些检查的代码，如唯一校验等
		if (thisService.IsFieldExist("roomBagRuleName", entity.getRoomRuleName(), "-1", hql1)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"钱包规则名称不能重复！\""));
			return;
		}
		
		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();
		
		entity = thisService.doAddEntity(entity, currentUser.getId());

		if (entity == null)
			writeJSON(response, jsonBuilder.returnFailureJson("\"添加失败，请重试或联系管理员！\""));
		else
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));

	}

	/**
	 * 删除规则
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Auth("ROOM_BAG_RULE_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入删除主键\""));
			return;
		} else {
			
			// 判断这些钱包规则是否正在被其他房间所绑定
			String hql = "select count(a.id) from RoomBagRuleBind as a where a.roomRuleId in ('" + delIds.replace(",", "','")
					+ "') and a.isDelete=0";
			int count = thisService.getQueryCountByHql(hql);
			if (count > 0) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"这些房间钱包规则当前绑定了房间，请解除绑定后再删除！\""));
				return;
			}

						
			PtUser sysuser = getCurrentSysUser();

			boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE, sysuser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
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
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入还原主键\""));
			return;
		} else {
			PtUser sysuser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISNOTDELETE, sysuser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"还原成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"还原失败\""));
			}
		}
	}

	/**
	 * 更新钱包房间规则
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("ROOM_BAG_RULE_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(PtRoomBagRule entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		
		String hql1 = " o.isDelete='0' ";
		// 此处为放在入库前的一些检查的代码，如唯一校验等
		if (thisService.IsFieldExist("roomBagRuleName", entity.getRoomRuleName(), entity.getId(), hql1)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"钱包规则名称不能重复！\""));
			return;
		}
		
		// 获取当前的操作用户
		PtUser currentUser = getCurrentSysUser();

		entity = thisService.doUpdateEntity(entity, currentUser.getId(), null);

		if (entity == null)
			writeJSON(response, jsonBuilder.returnFailureJson("\"修改失败，请重试或联系管理员！\""));
		else
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));

	}

}