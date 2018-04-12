
package com.zd.school.plartform.system.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserDeptRight;
import com.yc.q1.service.base.pt.system.PtUserDeptRightService;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.zd.core.annotation.Auth;
import com.zd.core.constant.Constant;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.util.StringUtils;

/**
 * 用户权限部门
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/SysDeptright")
public class SysDeptRightController extends FrameWorkController<PtUserDeptRight> implements Constant {

	@Resource
	PtUserDeptRightService thisService; // service层接口
	
	@Resource
	private PtUserService userService;
	
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
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		Integer start = super.start(request);
		Integer limit = super.limit(request);
		String sort = super.sort(request);
		String filter = super.filter(request);
		QueryResult<PtUserDeptRight> qResult = thisService.queryPageResult(start, limit, sort, filter, true);
		strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}


	
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"没有传入删除主键\""));
			return;
		} else {

			//SysUser currentUser = getCurrentSysUser();
			//boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE, currentUser.getXm());
			boolean flag = thisService.doDelete(delIds);	//物理删除
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		}
	}
	
	/**
	 * 给用户设置部门权限
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Auth("SYSUSER_doUserRightDept")
	@RequestMapping(value = { "/doUserRightDept" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void doUserRightDept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String deptIds = request.getParameter("deptIds");
		String userIds = request.getParameter("userIds");
		if (StringUtils.isEmpty(deptIds) || StringUtils.isEmpty(userIds)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"没有传入设置的参数\""));
			return;
		}
		PtUser currentUser = getCurrentSysUser();
	
		Boolean flag = thisService.doUserRightDept(userIds, deptIds, currentUser);
		if (flag)
			writeJSON(response, jsonBuilder.returnSuccessJson("\"设置部门权限成功\""));
			
	}
	
	@Auth("SYSUSER_doUpdateRightType")
	@RequestMapping("/doUpdateRightType")
	public void doUpdateRightType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PtUser  currentUser=getCurrentSysUser();
		
		String uuid=request.getParameter("userId");
		String rightType=request.getParameter("rightType");
		
		thisService.doUpdateRightType(uuid,rightType,currentUser.getId());
		
		
		writeJSON(response, jsonBuilder.returnSuccessJson("\"设置部门权限成功\""));
	}
	
}
