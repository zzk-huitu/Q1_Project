
package com.yc.q1.controller.wisdomclass;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.AdminType;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.wisdomclass.PtClassTeacher;
import com.yc.q1.pojo.base.pt.CommTree;
import com.yc.q1.service.base.pt.basic.CommTreeService;
import com.yc.q1.service.base.pt.system.PtDepartmentService;
import com.yc.q1.service.base.pt.wisdomclass.PtClassTeacherService;

/**
 * 班主任
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PtClassTeacher")
public class PtClassTeacherController extends FrameWorkController<PtClassTeacher> implements Constant {

	@Resource
	PtClassTeacherService thisService; // service层接口

	@Resource
	private CommTreeService treeSerice;

	@Resource
	private PtDepartmentService sysOrgService;

	/**
	 * list查询 @Title: list @Description: TODO @param @param entity
	 * 实体类 @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute PtClassTeacher entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		String filter = request.getParameter("filter");
		String deptId = request.getParameter("deptId");
		String deptType = request.getParameter("deptType");

		if (StringUtils.isEmpty(deptId)) {
			deptId = AdminType.ADMIN_ORG_ID;
		}

		Integer isAdmin = (Integer) request.getSession().getAttribute(Constant.SESSION_SYS_ISADMIN);
		Integer isSchoolAdmin = (Integer) request.getSession().getAttribute(Constant.SESSION_SYS_ISSCHOOLADMIN);

		// 若当前用户是超级管理员/学校管理员，并且为学校部门，则查询出所有的用户
		// if ((isAdmin == 1 || isSchoolAdmin==1) &&
		// deptId.equals(AdminType.ADMIN_ORG_ID)) {...}
		// 当部门不为根部门时 或者 不为管理员时，就要去查询内部的数据
		if (!deptId.equals(AdminType.ADMIN_ORG_ID) || !(isAdmin == 1 || isSchoolAdmin == 1)) {
			if ("05".equals(deptType)) { // 当选择的区域为班级时

				if (StringUtils.isNotEmpty(filter)) {
					filter = filter.substring(0, filter.length() - 1);
					filter += ",{\"type\":\"string\",\"comparison\":\"=\",\"value\":\"" + deptId
							+ "\",\"field\":\"classId\"}" + "]";
				} else {
					filter = "[{\"type\":\"string\",\"comparison\":\"=\",\"value\":\"" + deptId
							+ "\",\"field\":\"classId\"}]";
				}

			} else { // 当选择的区域不为班级时

				PtUser currentUser = getCurrentSysUser();
				String classIds = getClassIds(deptId, currentUser);

				if (StringUtils.isNotEmpty(classIds)) {

					if (StringUtils.isNotEmpty(filter)) {
						filter = filter.substring(0, filter.length() - 1);
						filter += ",{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + classIds
								+ "\",\"field\":\"classId\"}" + "]";
					} else {
						filter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + classIds
								+ "\",\"field\":\"classId\"}]";
					}

				} else { // 若区域之下没有班级，则直接返回空数据

					strData = jsonBuilder.buildObjListToJson(0L, new ArrayList<>(), true);// 处理数据
					writeJSON(response, strData);// 返回数据
					return;
				}
			}
		}

		QueryResult<PtClassTeacher> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), filter, true);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据

	}

	/**
	 * 
	 * @Title: 增加新实体信息至数据库 @Description: TODO @param @param JwClassteacher
	 *         实体类 @param @param request @param @param response @param @throws
	 *         IOException 设定参数 @return void 返回类型 @throws
	 */
	@Auth("CLASSTEACHER_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtClassTeacher entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		String classId = entity.getClassId(); // 班级
		String teacherId = entity.getTeacherId(); // 教师
		Integer category = entity.getCategory(); // 身份

		// 此处为放在入库前的一些检查的代码，如唯一校验等
		// 同一老师不能重复设置
		String hql = " o.isDelete='0' ";
		if (thisService.IsFieldExist("teacherId", teacherId, "-1", hql)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"此教师已是班主任！\""));
			return;
		}
		// 正班主任只能有一个
		if (category == 0) {
			hql = " o.isDelete='0' and category='" + category + "'";
			if (thisService.IsFieldExist("classId", classId, "-1", hql)) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"正班主任只能有一个！\""));
				return;
			}
		}

		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();

		// 持久化到数据库
		entity = thisService.doAddClassTeacher(entity, currentUser);
		
		// 返回实体到前端界面
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	}

	/**
	 * doDelete @Title: 物理删除指定的数据 @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@Auth("CLASSTEACHER_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
			return;
		} else {
			boolean flag = false;
			flag = thisService.doDeleteByPK(delIds);
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("'删除成功'"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("'删除失败'"));
			}
		}
	}
	
	// 解除设置班主任（逻辑删除）
	@Auth("CLASSTEACHER_out")
	@RequestMapping("/doOut")
	public void out(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String outIds = request.getParameter("ids");
		if (StringUtils.isEmpty(outIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入解除设置主键'"));
			return;
		} else {
			boolean flag = false;
			PtUser currentUser = getCurrentSysUser();
			flag = thisService.doDelete(outIds, currentUser);
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("'解除设置成功'"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("'解除设置失败'"));
			}
		}
	}

	/**
	 * 获取某个区域下的所有班级id
	 * 
	 * @param roomId
	 * @param roomLeaf
	 * @return
	 */
	private String getClassIds(String deptId, PtUser currentUser) {

		List<CommTree> baseOrgList = sysOrgService.getUserRightDeptClassTreeList(currentUser);
		String classIds = baseOrgList.stream().filter((x) -> {
			if (x.getNodeType().equals("05") && x.getTreeid().indexOf(deptId) != -1)
				return true;
			return false;
		}).map((x) -> x.getId()).collect(Collectors.joining(","));

		return classIds;
	}
}