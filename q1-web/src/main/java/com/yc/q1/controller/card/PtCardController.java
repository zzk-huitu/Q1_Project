package com.yc.q1.controller.card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.constant.AdminType;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.basic.PtGradeClass;
import com.yc.q1.model.base.pt.basic.PtStudentBaseInfo;
import com.yc.q1.model.base.pt.card.PtCard;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.DepartmentTree;
import com.yc.q1.service.base.pt.basic.PtGradeClassService;
import com.yc.q1.service.base.pt.basic.PtStudentBaseInfoService;
import com.yc.q1.service.base.pt.card.PtCardService;
import com.yc.q1.service.base.pt.system.PtDepartmentService;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BaseTAttachmentController Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 公共附件信息表实体Controller. date: 2016-07-06
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/PtCard")
public class PtCardController extends FrameWorkController<PtCard> {

	@Resource
	PtCardService thisService; // service层接口

	@Resource
	PtUserService userService; // service层接口

	@Resource
	PtGradeClassService gradeClassService; // service层接口
	
	@Resource
	PtDepartmentService departmentService; // service层接口

	@Resource
	PtStudentBaseInfoService studentBaseInfoService; // service层接口

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	/**
	 * 标准的查询列表功能
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(PtCard entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<PtCard> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), false);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 查询用户列表功能
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/userList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void userList(PtUser entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		String deptId = request.getParameter("deptId");

		if (StringUtils.isEmpty(deptId)) {
			deptId = AdminType.ADMIN_ORG_ID;
		}

		Integer isAdmin = (Integer) request.getSession().getAttribute(Constant.SESSION_SYS_ISADMIN);
		Integer isSchoolAdmin = (Integer) request.getSession().getAttribute(Constant.SESSION_SYS_ISSCHOOLADMIN);

		// 若当前用户是超级管理员/学校管理员，并且为学校部门，则查询出所有的用户
		if ((isAdmin == 1 || isSchoolAdmin == 1) && deptId.equals(AdminType.ADMIN_ORG_ID)) {

			QueryResult<PtUser> qr = userService.queryPageResult(super.start(request), super.limit(request),
					super.sort(request), super.filter(request), true);
			strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据

		} else {
			PtUser currentUser = getCurrentSysUser();
			// 其他非管理员的，并且点击了学校部门，则查询出它有权限的部门
			if (deptId.equals(AdminType.ADMIN_ORG_ID)) {
				List<DepartmentTree> baseOrgList = departmentService.getUserRightDeptTreeList(currentUser);
				deptId = baseOrgList.stream().filter((x) -> x.getIsRight().equals("1")).map((x) -> x.getId())
						.collect(Collectors.joining("','"));
			}

			// 根据deptId，查询出所有用户信息（主部门和副部门的）
			if (StringUtils.isNotEmpty(deptId)) {
				String hql = "from PtUser g where g.isDelete=0 and g.id in ("
						+ "	select distinct userId from PtUserDeptJob where isDelete=0 and deptId in ('" + deptId
                        + "')" + ")"; // and masterDept=1 目前显示部门的全部用户

				QueryResult<PtUser> qr = thisService.queryCountToHql(super.start(request), super.limit(request),
						super.sort(request), super.filter(request), hql, null, null);

				strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据

			} else {
				strData = jsonBuilder.buildObjListToJson((long) 0, new ArrayList<>(), true);// 处理数据
			}

		}

		writeJSON(response, strData);// 返回数据
	}
	
	/**
	 * 单个挂失解挂功能
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/doLockOrOn" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void doLockOrOn(PtCard entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PtUser currentUser = getCurrentSysUser();
		String cardId = request.getParameter("id");
		Boolean flag = thisService.doLockOrOn(cardId, currentUser);
		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"操作成功\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"操作失败\""));
		}
		
	}
	
	/**
	 * 批量挂失功能
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/doLockBatch" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void doLockBatch(PtCard entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PtUser currentUser = getCurrentSysUser();
		String ids = request.getParameter("ids");
		Boolean flag = thisService.doLockBatch(ids, currentUser);
		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"操作成功\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"操作失败\""));
		}
		
	}
	
	/**
	 * 批量解挂功能
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/doOnBatch" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void doOnBatch(PtCard entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PtUser currentUser = getCurrentSysUser();
		String ids = request.getParameter("ids");
		Boolean flag = thisService.doOnBatch(ids, currentUser);
		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"操作成功\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"操作失败\""));
		}
		
	}

}
