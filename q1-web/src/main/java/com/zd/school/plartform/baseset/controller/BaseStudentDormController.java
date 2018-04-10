package com.zd.school.plartform.baseset.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.base.pt.basic.model.ClassStudent;
import com.yc.q1.base.pt.basic.service.CommTreeService;
import com.yc.q1.base.pt.basic.service.ClassStudentService;
import com.yc.q1.base.pt.build.model.ClassDormAllot;
import com.yc.q1.base.pt.build.model.DormDefine;
import com.yc.q1.base.pt.build.model.StudentDorm;
import com.yc.q1.base.pt.build.service.ClassDormAllotService;
import com.yc.q1.base.pt.build.service.DormDefineService;
import com.yc.q1.base.pt.build.service.OfficeAllotService;
import com.yc.q1.base.pt.build.service.StudentDormService;
import com.yc.q1.base.pt.pojo.CommTree;
import com.yc.q1.base.pt.pojo.StandVClassStudent;
import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.system.service.DepartmentService;
import com.zd.core.annotation.Auth;
import com.zd.core.constant.AdminType;
import com.zd.core.constant.Constant;
import com.zd.core.constant.TreeVeriable;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.util.JsonBuilder;
import com.zd.core.util.PoiExportExcel;
import com.zd.core.util.StringUtils;

/**
 * 学生宿舍分配
 *
 */
@Controller
@RequestMapping("/BaseStudentDorm")
public class BaseStudentDormController extends FrameWorkController<StudentDorm> implements Constant {
	@Resource
	StudentDormService thisService; // service层接口
	@Resource
	CommTreeService treeService;
	@Resource
	ClassDormAllotService classDormService;// 班级宿舍
	@Resource
	ClassStudentService classStuService; // 学生分班
	@Resource
	DormDefineService dormDefineService;// 宿舍定义
	@Resource
	private OfficeAllotService roomaAllotService;// 房间分配 办公室
	@Resource
	DepartmentService sysOrgService;

	/**
	 * 已入住的学生列表
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute StudentDorm entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		String filter = request.getParameter("filter");
		String deptId = request.getParameter("deptId"); // 前端传入若为区域，则是roomId，否则是dormId
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

				User currentUser = getCurrentSysUser();
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

		QueryResult<StudentDorm> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), filter, true);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据

	}

	/**
	 * 生成树 获取用户有权限的年级班级树
	 * 使用redis来存放，方便在list方法中也直接去获取这些权限部门数据
	 */
	@RequestMapping("/classtreelist")
	public void classtreelist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = "";
		String node = request.getParameter("node"); // 一般传 ROOT
		String nodeId = request.getParameter("nodeId");
		String excludes = request.getParameter("excludes"); // 在结果集中排除某个字段，比如checked复选框字段
		if (!(StringUtils.isNotEmpty(node) && node.equalsIgnoreCase(TreeVeriable.ROOT))) {
			node = TreeVeriable.ROOT;
		}
		if (StringUtils.isNotEmpty(nodeId)) {
			node = nodeId;
		}
		User currentUser = getCurrentSysUser();
		CommTree root = thisService.getUserRightDeptClassTree(node, currentUser); // (04-年级
																						// 05-班级)
		if (node.equalsIgnoreCase(TreeVeriable.ROOT)) {
			strData = jsonBuilder.buildList(root.getChildren(), excludes);
		} else {
			List<CommTree> alist = new ArrayList<CommTree>();
			alist.add(root);
			strData = jsonBuilder.buildList(root.getChildren(), excludes);
		}
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 分别获取男生宿舍树、女生宿舍树、学生宿舍树
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getTree")
	public void getBoyTree(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = "";
		List<CommTree> lists = null;
		String whereSql = request.getParameter("whereSql");
		String identity = request.getParameter("identity");
		if (identity.equals("1")) {// 男生
			lists = treeService.getCommTree("V_PT_StuBoyDormTree", whereSql);
		} else if (identity.equals("2")) {// 女生
			lists = treeService.getCommTree("V_PT_StuGirlDormTree", whereSql);
		} else {
			lists = treeService.getCommTree("V_PT_StudentDormAreaTree", whereSql);
		}

		strData = JsonBuilder.getInstance().buildList(lists, "");// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 查询出一键分配宿舍的信息
	 */
	@RequestMapping("/onKeyList")
	public void onKeyList(@ModelAttribute StudentDorm entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {
		List<StudentDorm> newlists = null;
		String whereSql = request.getParameter("whereSql");
		newlists = thisService.oneKeyList(entity, whereSql);
		String strData = jsonBuilder.buildObjListToJson(new Long(newlists.size()), newlists, false);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 一键分配宿舍调用方法
	 * 
	 * @param gradId
	 *            年级id
	 * @param boyId
	 *            男宿舍id
	 * @param girlId
	 *            女宿舍id
	 */
	@Auth("BASESTUDENTDORM_oneKeyAllot")
	@RequestMapping("/doKeyAllotDorm")
	public void doKeyAllotDorm(String gradId, String boyId, String girlId, HttpServletRequest request,
			HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {
		Boolean flag = false;
		User currentUser = getCurrentSysUser();
		flag = thisService.doOneKeyAllotDorm(gradId, boyId, girlId, currentUser);
		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"一键分配分配成功。\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"一键分配分配失败。\""));
		}

	}

	/**
	 * 班级下面宿舍列表
	 * 
	 */
	@RequestMapping(value = { "/classDormlist" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void classDormlist(@ModelAttribute ClassDormAllot entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<ClassDormAllot> qr = classDormService.queryPageResult(super.start(request), Integer.MAX_VALUE,
				super.sort(request), super.filter(request), true);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 未分配宿舍学生列表 该班级没有分配宿舍的学生
	 */
	@RequestMapping(value = { "/classStuNotAllotlist" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void classStuNotAllotlist(@ModelAttribute ClassStudent entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		String classId = request.getParameter("classId");

		if (classId == null) {
			classId = "";
		}
		String hql = "select a.id from Department a where a.isDelete=0  and a.deptType='05' and a.treeIds like '%"
				+ classId + "%'";
		List<String> lists = thisService.queryEntityByHql(hql);
		StringBuffer sb = new StringBuffer();
		String str = "";
		for (int i = 0; i < lists.size(); i++) {
			sb.append(lists.get(i) + ",");
		}
		if (sb.length() > 0)
			str = sb.substring(0, sb.length() - 1);

		String sql = " select a.userId as userId,a.xm as xm,a.userNumb as userNumb,a.xbm as xbm,a.classId as classId,"
				+ " a.className as className,a.gradeId as gradeId,a.gradeCode as gradeCode,a.gradeName as gradeName "
				+ "from V_PT_ClassStudentList a where "
				+ " a.userId not in (select studentId from T_PT_StudentDorm  where isDelete=0)";

		String countSql = "select count(*) " + "from V_PT_ClassStudentList a where "
				+ " a.userId not in (select studentId from T_PT_StudentDorm  where isDelete=0)";

		if (classId != null) {
			sql += " and a.classId in ('" + str.replace(",", "','") + "')";
			countSql += " and a.classId in ('" + str.replace(",", "','") + "')";
		}

		QueryResult<StandVClassStudent> qr = thisService.queryPageResultBySql(sql, super.start(request),super.limit(request), StandVClassStudent.class, countSql);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据

		writeJSON(response, strData);// 返回数据
	}

	
	// 手动分配宿舍 （学生分配宿舍）
	@Auth("BASESTUDENTDORM_dormAllot")
	@RequestMapping("/dormHandAllot")
	public void dormHandAllot(StudentDorm entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		Boolean flag = false;
		User currentUser = getCurrentSysUser();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		flag = thisService.doDormHandAllot(entity, hashMap, currentUser);
		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'手动分配宿舍成功。'"));
		} else {
			Integer count = (Integer) hashMap.get("count");
			DormDefine buildDormDefine = (DormDefine) hashMap.get("buildDormDefine");
			Integer inAllotCount = (Integer) hashMap.get("inAllotCount");
			if (count == 1) {
				writeJSON(response, jsonBuilder.returnFailureJson(
						"'该宿舍最大人数为：" + buildDormDefine.getBedCount() + "人。现已入住：" + inAllotCount + "。'"));
				return;
			} else {
				Integer canInAllotCount = (Integer) hashMap.get("canInAllotCount");
				writeJSON(response, jsonBuilder.returnFailureJson("'该宿舍最大人数为：" + buildDormDefine.getBedCount()
						+ "人。现已入住：" + inAllotCount + "。可分配床位数为：" + canInAllotCount + "'"));
				return;
			}
		}
	}

	/**
	 * 自动分配宿舍（学生分配宿舍）
	 */
	@Auth("BASESTUDENTDORM_dormAllot")
	@RequestMapping("/dormAutoAllot")
	public void dormAutoAllot(String classId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User currentUser = getCurrentSysUser();
		Integer resultCount = thisService.doDormAutoAllot(classId, currentUser);
		if (resultCount>0) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"自动分配宿舍成功!\""));
		} else  {
			writeJSON(response, jsonBuilder.returnFailureJson("\"没有可用的宿舍进行分配！\""));	//出错的返回方式，统一在异常处理中
		}
	}

	/**
	 * 计算未分配完的混合宿舍 该年纪下的所有未分配完的宿舍
	 */
	@RequestMapping(value = { "/mixDormList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void mixDormList(@ModelAttribute ClassDormAllot entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<ClassDormAllot> dormAllotList = null;
		dormAllotList = thisService.mixDormList(entity);
		String strData = jsonBuilder.buildObjListToJson(new Long(dormAllotList.size()), dormAllotList, true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 查询出已分配并且人数为0的混班宿舍 该年纪下的所有人数为0的混班宿舍
	 */
	@RequestMapping(value = { "/emptyMixDormList" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void emptyMixDormList(@ModelAttribute ClassDormAllot entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<ClassDormAllot> dormAllotList = null;
		dormAllotList = thisService.emptyMixDormList(entity);
		String strData = jsonBuilder.buildObjListToJson(new Long(dormAllotList.size()), dormAllotList, false);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 删除宿舍
	 */
	@Auth("BASESTUDENTDORM_dormAdjust")
	@RequestMapping("/dormDoDelete")
	public void dormDoDelete(String uuid, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int count = 0;
		int fs = 0;
		if (StringUtils.isEmpty(uuid)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入删除主键\""));
			return;
		} else {
			String[] ids = uuid.split(",");
			DormDefine defin = null;
			ClassDormAllot jwTClassdorm = null;
			for (int j = 0; j < ids.length; j++) {
				jwTClassdorm = classDormService.get(ids[j]);
				boolean flag = thisService.IsFieldExist("classDormId", jwTClassdorm.getId(), "-1", "isdelete=0");
				if (flag) {
					++count;
				}
				if (count == 0) {
					defin = dormDefineService.get(jwTClassdorm.getDormId());
					defin.setIsAllot(false); // 设置成未分配
					defin.setIsMixed(false); // 恢复为非混合宿舍
					dormDefineService.merge(defin); // 持久化
					jwTClassdorm.setIsDelete(1); // 设置删除状态
					classDormService.merge(jwTClassdorm); // 持久化
					++fs;
				}
				count = 0;
			}
			if (fs > 0) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功。\""));
			} else {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"宿舍都已分配给学生，不允许删除。\""));
			}

		}
	}

	/**
	 * 手动添加班级宿舍
	 */
	@Auth("BASESTUDENTDORM_AddClassDorm")
	@RequestMapping("/doAddClassDorm")
	public void doAddClassDorm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String classId = request.getParameter("classId");
		String dormIds = request.getParameter("dormIds");

		// 获取当前的操作用户
		User currentUser = getCurrentSysUser();

		Boolean flag = thisService.doAddClassDorm(classId, dormIds, currentUser);

		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"添加班级宿舍成功!\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"添加班级宿舍失败!\""));
		}

	}

	/**
	 * 取消分配学生宿舍 doDelete @Title: 逻辑删除指定的数据 @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@RequestMapping("/doDelete")
	public void doDelete(StudentDorm entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		boolean flag = false;
		User currentUser = getCurrentSysUser();
		if (StringUtils.isEmpty(entity.getId())) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入删除主键\""));
			return;
		} else {
			String[] delIds = entity.getId().split(",");
			flag = thisService.doDeleteDorm(delIds, currentUser.getId());

			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"取消宿舍成功!\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"取消宿舍失败!\""));
			}
		}
	}

	/**
	 * 修改床号和柜子号 doUpdate编辑记录 @Title: doUpdate @Description: TODO @param @param
	 * DormStudentdorm @param @param request @param @param
	 * response @param @throws IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping("/doUpdateBedArkNum")
	public void doUpdates(StudentDorm entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		// 获取当前的操作用户
		User currentUser = getCurrentSysUser();

		String[] list = entity.getId().split(";");
		int count = 0;

		count = thisService.doUpdateBedArkNum(list, currentUser.getId());

		if (count > 0) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"保存成功。\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"保存失败。\""));
		}

	}

	// 推送消息
	@Auth("BASESTUDENTDORM_dormTs")
	@RequestMapping("/doPushMessage")
	public void doPushMessage(String classId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Boolean flag = false;
		flag = thisService.doPushMessage(classId);
		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"推送信息成功。\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"推送信息失败。\""));
		}

	}

	@Auth("BASESTUDENTDORM_export")
	@RequestMapping("/doExportExcel")
	public void doExportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("exportStuDormIsEnd", "0");
		request.getSession().removeAttribute("exportStuDormIsState");
		String claiId = request.getParameter("claiId");

		List<Map<String, Object>> allList = new ArrayList<>();
		Integer[] columnWidth = new Integer[] { 10, 15, 15, 20, 20, 20, 35 };
		List<StudentDorm> stuDormList = null;
		String hql = " from StudentDorm where isDelete=0 and classId='" + claiId + "' order by inTime ";
		stuDormList = thisService.queryByHql(hql);

		List<Map<String, String>> stuDormExpList = new ArrayList<>();
		Map<String, String> stuDormMap = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (StudentDorm stuDorm : stuDormList) {
			stuDormMap = new LinkedHashMap<>();
			stuDormMap.put("claiName", stuDorm.getClassName());
			stuDormMap.put("xm", stuDorm.getXm());
			stuDormMap.put("userNumb", stuDorm.getUserNumb());
			stuDormMap.put("roomName", stuDorm.getRoomName());
			stuDormMap.put("bedNum", stuDorm.getBedNo().toString());
			stuDormMap.put("arkNum", stuDorm.getSarkNo().toString());
			stuDormMap.put("inTime", format.format(stuDorm.getInTime()));
			stuDormExpList.add(stuDormMap);
		}

		Map<String, Object> courseAllMap = new LinkedHashMap<>();
		courseAllMap.put("data", stuDormExpList);
		courseAllMap.put("title", null);
		courseAllMap.put("head", new String[] { "班级名称", "学生名称", "学号", "宿舍名称", "床号", "柜号", "入住时间" }); // 规定名字相同的，设定为合并
		courseAllMap.put("columnWidth", columnWidth); // 30代表30个字节，15个字符
		courseAllMap.put("columnAlignment", new Integer[] { 0, 0, 0, 0, 0, 0, 0 }); // 0代表居中，1代表居左，2代表居右
		courseAllMap.put("mergeCondition", null); // 合并行需要的条件，条件优先级按顺序决定，NULL表示不合并,空数组表示无条件
		allList.add(courseAllMap);

		// 在导出方法中进行解析
		boolean result = PoiExportExcel.exportExcel(response, "学生宿舍分配信息", "学生宿舍分配信息", allList);
		if (result == true) {
			request.getSession().setAttribute("exportStuDormIsEnd", "1");
		} else {
			request.getSession().setAttribute("exportStuDormIsEnd", "0");
			request.getSession().setAttribute("exportStuDormIsState", "0");
		}

	}

	@RequestMapping("/checkExportEnd")
	public void checkExportEnd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Object isEnd = request.getSession().getAttribute("exportStuDormIsEnd");
		Object state = request.getSession().getAttribute("exportStuDormIsState");
		if (isEnd != null) {
			if ("1".equals(isEnd.toString())) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"文件导出完成！\""));
			} else if (state != null && state.equals("0")) {
				writeJSON(response, jsonBuilder.returnFailureJson("0"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"文件导出未完成！\""));
			}
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"文件导出未完成！\""));
		}
	}

	/**
	 * 获取某个区域下的所有学生宿舍数据
	 * 
	 * @param roomId
	 * @param roomLeaf
	 * @return
	 */
	private String getClassIds(String areaId, User currentUser) {

		List<CommTree> baseOrgList = sysOrgService.getUserRightDeptClassTreeList(currentUser);
		String classIds = baseOrgList.stream().filter((x) -> {
			if (x.getNodeType().equals("05") && x.getTreeid().indexOf(areaId) != -1)
				return true;
			return false;
		}).map((x) -> x.getId()).collect(Collectors.joining(","));

		return classIds;
	}
}
