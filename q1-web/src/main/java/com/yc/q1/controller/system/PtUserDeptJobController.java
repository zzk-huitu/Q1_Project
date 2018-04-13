
package com.yc.q1.controller.system;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserDeptJob;
import com.yc.q1.service.base.pt.system.PtUserDeptJobService;
/**
 * 用户部门岗位管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PtUserDeptJob")
public class PtUserDeptJobController extends FrameWorkController<PtUserDeptJob> implements Constant {

	@Resource
	PtUserDeptJobService thisService; // service层接口

	
	//获取此部门岗位的用户列表
    @RequestMapping(value = { "/getDeptJobUsers" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
            org.springframework.web.bind.annotation.RequestMethod.POST })
    public void getDeptJobUsers(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String strData = ""; // 返回给js的数据
        String deptJobId = request.getParameter("deptJobId");
        Integer start = super.start(request);
        Integer limit = super.limit(request);
        String sort = StringUtils.convertSortToSql(super.sort(request));
        
        QueryResult<PtUserDeptJob> qr = thisService.getUserByDeptJobId(deptJobId,start,limit,sort);
        
        if (ModelUtil.isNotNull(qr))
        	strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
        writeJSON(response, strData);// 返回数据
    }
    
    /**
	 * 删除用户所在的部门岗位，只是逻辑删除
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doRmoveDeptJobFromUser")
	public void doRmoveDeptJobFromUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("delIds");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入要解除绑定的部门岗位\""));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doRemoveUserFromDeptJob(delIds,currentUser);
			if (flag)
				writeJSON(response, jsonBuilder.returnSuccessJson("\"解除绑定成功\""));
			else
				writeJSON(response, jsonBuilder.returnSuccessJson("\"解除绑定失败\""));
		}
	}
	
	/**
	 * 调整指定用户的主部门岗位
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doSetMasterDeptJob")
	public void doSetMasterDeptJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String deptJobId = request.getParameter("deptJobId");
		String userIds = request.getParameter("userIds");
		if (StringUtils.isEmpty(deptJobId) || StringUtils.isEmpty(userIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入要设置部门岗位\""));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doSetMasterDeptJobFromUser(userIds,deptJobId, currentUser);
			if (flag)
				writeJSON(response, jsonBuilder.returnSuccessJson("\"设置主部门成功\""));
			else
				writeJSON(response, jsonBuilder.returnSuccessJson("\"设置主部门失败\""));
		}
	}
	
	/**
	 * 将指定的用户绑定到指定的部门岗位上
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doAddUserToDeptJob")
	public void addUserToDeptJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String deptJobId = request.getParameter("deptJobId");
		String userIds = request.getParameter("userIds");
		if (StringUtils.isEmpty(deptJobId) || StringUtils.isEmpty(userIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入设置的参数\""));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doAddUserToDeptJob(deptJobId, userIds, currentUser);
			if (flag)
				writeJSON(response, jsonBuilder.returnSuccessJson("\"设置成功\""));
			else
				writeJSON(response, jsonBuilder.returnSuccessJson("\"设置失败\""));
		}
	}
}