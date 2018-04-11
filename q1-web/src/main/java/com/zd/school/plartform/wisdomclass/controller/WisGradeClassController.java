package com.zd.school.plartform.wisdomclass.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.base.pt.basic.service.GradeService;
import com.yc.q1.base.pt.basic.service.GradeClassService;
import com.yc.q1.base.pt.system.service.DepartmentService;
import com.yc.q1.base.pt.system.service.UserService;
import com.yc.q1.base.pt.wisdomclass.service.ClassTeacherService;
import com.yc.q1.model.base.pt.basic.PtGradeClass;
import com.yc.q1.model.base.pt.system.PtDepartment;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.wisdomclass.PtClassTeacher;
import com.zd.core.annotation.Auth;
import com.zd.core.constant.AdminType;
import com.zd.core.constant.Constant;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.StringUtils;

@Controller
@RequestMapping("/GradeClass")
public class WisGradeClassController extends FrameWorkController<PtGradeClass> implements Constant {
	@Resource
	private GradeClassService thisService;
	@Resource
	private DepartmentService orgService;
	@Resource
	private GradeService gradeService;
	@Resource
	private UserService userService;

	@Resource
	private ClassTeacherService cTeacherService;

	// 获取数据
	@RequestMapping(value = "/classmottolist", method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void classmottolist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		String filter = request.getParameter("filter");
		String claiId = request.getParameter("claiId");
		String claiIdLeaf = request.getParameter("claiIdLeaf");

		if (StringUtils.isNotEmpty(claiId) && !AdminType.ADMIN_ORG_ID.equals(claiId)) {
			if ("1".equals(claiIdLeaf)) { // 当选择的区域为房间时
				if (StringUtils.isNotEmpty(filter)) {
					filter = filter.substring(0, filter.length() - 1);
					filter += ",{\"type\":\"string\",\"comparison\":\"=\",\"value\":\"" + claiId
							+ "\",\"field\":\"id\"}" + "]";
				} else {
					filter = "[{\"type\":\"string\",\"comparison\":\"=\",\"value\":\"" + claiId
							+ "\",\"field\":\"id\"}]";
				}
			} else { // 当选择的区域不为房间时
				// 当选择的区域不为房间时
				List<String> claiIdList = new ArrayList<>();
				String hql = "select a.id from Department a where a.isDelete=0  and a.deptType='05' and a.treeIds like '%"
						+ claiId + "%'";
				claiIdList = thisService.queryEntityByHql(hql);

				if (!claiIdList.isEmpty()) {
					String roomIds = claiIdList.stream().collect(Collectors.joining(","));
					if (StringUtils.isNotEmpty(filter)) {
						filter = filter.substring(0, filter.length() - 1);
						filter += ",{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + roomIds
								+ "\",\"field\":\"id\"}" + "]";
					} else {
						filter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + roomIds
								+ "\",\"field\":\"id\"}]";
					}

				} else { // 若区域之下没有房间，则直接返回空数据

					strData = jsonBuilder.buildObjListToJson(0L, new ArrayList<>(), true);// 处理数据
					writeJSON(response, strData);// 返回数据
					return;
				}
			}
		}

		QueryResult<PtGradeClass> qResult = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), filter, true);
		strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	@Auth("CLASSMOTTO_update")
	@RequestMapping("/doUpdate")
	public void doupdate(PtGradeClass entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		PtUser currentUser = getCurrentSysUser();

		PtGradeClass perEntity = thisService.get(entity.getId());
		BeanUtils.copyPropertiesExceptNull(perEntity, entity);

		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(currentUser.getId());

		entity = thisService.merge(perEntity);// 执行修改方法

		PtDepartment org = orgService.get(entity.getId());
		org.setNodeText(perEntity.getClassName());
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(perEntity)));
	}

	@RequestMapping(value = "/listUser", method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void listUser(@ModelAttribute PtGradeClass entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		PtUser currentUser = getCurrentSysUser();
		Integer start = super.start(request);
		Integer limit = super.limit(request);

		String hql = "from GradeClass where isDelete=0";
		Boolean isSchoolAdminRole = false;
		List<PtUser> roleUsers = userService.getUserByRoleName("学校管理员");
		for (PtUser su : roleUsers) {
			if (su.getId().equals(currentUser.getId())) {
				isSchoolAdminRole = true;
				break;
			}
		}
		if (!isSchoolAdminRole) {
			// 判断是否是班主任
			String ghql = "from ClassTeacher where isDelete=0 and teacherId='" + currentUser.getId() + "'";
			List<PtClassTeacher> classteachers = cTeacherService.queryByHql(ghql);
			if (classteachers != null && classteachers.size() > 0) {
				PtClassTeacher cTeacher = classteachers.get(0);
				hql += " and id='" + cTeacher.getClassId() + "'";
			}

		}

		QueryResult<PtGradeClass> qr = thisService.queryResult(hql, start, limit);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}


}
