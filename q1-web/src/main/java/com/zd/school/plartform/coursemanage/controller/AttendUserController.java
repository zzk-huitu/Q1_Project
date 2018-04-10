
package com.zd.school.plartform.coursemanage.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.wisdomclass.model.AttendUser;
import com.zd.core.annotation.Auth;
import com.zd.core.constant.Constant;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.util.ModelUtil;
import com.zd.core.util.StringUtils;
import com.zd.school.oa.attendance.service.AttUserService;
import com.zd.school.plartform.system.service.SysUserService;

/**
 * 
 * ClassName: AttUserController Function: ADD FUNCTION. Reason: ADD REASON(可选).
 * Description: 考勤人员(ATT_T_USER)实体Controller. date: 2017-05-15
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/AttUser")
public class AttendUserController extends FrameWorkController<AttendUser> implements Constant {

	@Resource
	AttUserService thisService; // service层接口
	@Resource
	SysUserService userService;

	/**
	 * @Title: list
	 * @Description: 查询数据列表
	 * @param entity
	 *            实体类
	 * @param request
	 * @param response
	 * @throws IOException
	 *             设定参数
	 * @return void 返回类型
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute AttendUser entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据

		QueryResult<AttendUser> qResult = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);
		strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	@Auth("SPECIAL_COURSEATTEND_attendUse")
	@RequestMapping(value = { "/userAttendlist" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void userAttendlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<AttendUser> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);
		if (qr.getTotalCount() == 0) {
			writeJSON(response, strData);// 返回数据
			return;
		}
		StringBuffer userId = new StringBuffer();
		for (AttendUser attUser : qr.getResultList()) {
			userId.append(attUser.getUserId() + ",");
		}
		String filter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\""
				+ userId.substring(0, userId.length() - 1) + "\",\"field\":\"id\"}]";

		QueryResult<User> termQr = userService.queryPageResult(0, 0, null, filter, true);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), termQr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 
	 * @Title: doadd
	 * @Description: 增加新实体信息至数据库
	 * @param AttendUser
	 *            实体类
	 * @param request
	 * @param response
	 * @return void 返回类型
	 * @throws IOException
	 *             抛出异常
	 */
	@RequestMapping("/doAdd")
	public void doAdd(AttendUser entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 此处为放在入库前的一些检查的代码，如唯一校验等

		// 获取当前操作用户
		User currentUser = getCurrentSysUser();
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

	@RequestMapping("/doUserAttendBind")
	public void doUserAttendAdd(String[] userIds, String titleId, HttpServletRequest request,
			HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {

		// 此处为放在入库前的一些检查的代码，如唯一校验等

		// 获取当前操作用户
		User currentUser = getCurrentSysUser();
		thisService.doUserAttendBind(userIds, titleId, currentUser.getId());// 执行增加方法

		writeJSON(response, jsonBuilder.returnSuccessJson("'成功'"));

	}

	/**
	 * 
	 * @Title: doDelete
	 * @Description: 逻辑删除指定的数据
	 * @param request
	 * @param response
	 * @return void 返回类型
	 * @throws IOException
	 *             抛出异常
	 */
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
			return;
		} else {
			User currentUser = getCurrentSysUser();
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

	@RequestMapping("/doUserAttendDelete")
	public void doUserAttendDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userIds = request.getParameter("isSelectAttendIds");
		if (StringUtils.isEmpty(userIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
			return;
		} else {
			String[] ids = userIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				String hql = " from AttendUser where userId = '" + ids[i] + "'";
				AttendUser entity = thisService.getEntityByHql(hql);
				thisService.delete(entity);
			}
		}
		writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
	}

	/**
	 * @Title: doUpdate
	 * @Description: 编辑指定记录
	 * @param AttendUser
	 * @param request
	 * @param response
	 * @return void 返回类型
	 * @throws IOException
	 *             抛出异常
	 */
	@RequestMapping("/doUpdate")
	public void doUpdates(AttendUser entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 入库前检查代码

		// 获取当前的操作用户
		User currentUser = getCurrentSysUser();
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
