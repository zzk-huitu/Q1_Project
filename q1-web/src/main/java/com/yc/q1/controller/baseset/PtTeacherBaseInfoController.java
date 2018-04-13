package com.yc.q1.controller.baseset;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.basic.PtTeacherBaseInfo;
import com.yc.q1.model.base.pt.system.PtDepartment;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.basic.PtAttachmentService;
import com.yc.q1.service.base.pt.basic.PtTeacherBaseInfoService;
import com.yc.q1.service.base.pt.system.PtDepartmentService;
import com.yc.q1.service.base.pt.system.PtRoleService;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

@Controller
@RequestMapping("/PtTeacherBaseInfo")
public class PtTeacherBaseInfoController extends FrameWorkController<PtTeacherBaseInfo> implements Constant {
	@Resource
	private PtTeacherBaseInfoService thisService; // service层接口

	@Resource
	private PtDepartmentService sysOrgService;

	@Resource
	private PtRoleService roleService;

	@Value("${realFileUrl}")
	private String realFileUrl; // 文件目录物理路径

	@Value("${virtualFileUrl}")
	private String virtualFileUrl; // 文件目录虚拟路径

	@Resource
	private PtAttachmentService baseTAttachmentService;// service层接口

	/**
	 * list查询 @Title: list @Description: TODO @param @param entity
	 * 实体类 @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute PtTeacherBaseInfo entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		String deptId = request.getParameter("deptId");
		String qureyFilter = request.getParameter("queryFilter");
		if (StringUtils.isEmpty(qureyFilter)) {
			qureyFilter = request.getParameter("filter");
		}
		PtUser currentUser = getCurrentSysUser();

		QueryResult<PtTeacherBaseInfo> qr = thisService.getDeptTeacher(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), qureyFilter, true, deptId, currentUser);
		if (ModelUtil.isNotNull(qr))
			strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 直接查询某个学科下的教师
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/listCourseTeacher" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		String deptId = request.getParameter("deptId");
		String courseId = request.getParameter("courseId");

		// 当传入的参数树courseId时。就去查询deptId
		if (StringUtils.isEmpty(deptId) && StringUtils.isNotEmpty(courseId)) {
			PtDepartment baseOrg = sysOrgService.getByProerties("courseId", courseId);
			deptId = baseOrg.getId();
		}

		if (StringUtils.isNotEmpty(deptId)) {

			String hql = "from TeacherBaseInfo g where g.isDelete=0 and g.id in ("
					+ "	select distinct userId  from UserDeptJob where isDelete=0 and deptId = '" + deptId + "'"
					+ ")";
			QueryResult<PtTeacherBaseInfo> qr = thisService.queryCountToHql(super.start(request), super.limit(request),
					super.sort(request), super.filter(request), hql, null, null);

			strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据

		} else {
			strData = jsonBuilder.buildObjListToJson((long) 0, new ArrayList<>(), true);// 处理数据
		}

		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 
	 * @throws BadHanyuPinyinOutputFormatCombination
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: 增加新实体信息至数据库 @Description: TODO @param @param TeaTeacherbase
	 *         实体类 @param @param request @param @param response @param @throws
	 *         IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping(value = { "/doAdd" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void doAdd(PtTeacherBaseInfo entity, @RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException, BadHanyuPinyinOutputFormatCombination,
			IllegalAccessException, InvocationTargetException {
		if (!file.isEmpty()) {
			// 图片服务器路径
			String file_path = realFileUrl;// String file_path ="D:\\Q1_Files\\uploadFiles\\";

			// 取得当前上传文件的文件名称
			String myFileName = file.getOriginalFilename();

			if (myFileName.trim() != "") {// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				// 重命名上传后的文件名
//				String type = myFileName.substring(myFileName.lastIndexOf("."));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String url = "TeacherBase/" + sdf.format(System.currentTimeMillis()) + "/";

				// 定义上传路径
				String path = file_path + url + myFileName;
				File localFile = new File(path);

				if (!localFile.exists()) { // 判断文件夹是否存在
					localFile.mkdirs(); // 不存在则创建
				}

				file.transferTo(localFile);
				entity.setPhoto(url + myFileName);
			}
		}

		// 此处为放在入库前的一些检查的代码，如唯一校验等
		String userName = entity.getUserName();
		String  sfzjh = entity.getIdentityNumber();
		String  userNumb = entity.getUserNumb();
		// 判断身份证件号是否重复
		if (StringUtils.isNotEmpty(sfzjh) && thisService.IsFieldExist("identityNumber", sfzjh, "-1")) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"身份证件号不能重复！\""));
			return;
		}

		// 判断学号是否重复
		if (thisService.IsFieldExist("userNumb", userNumb, "-1")) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"工号不能重复！\""));
			return;
		}
		
		// 判断学号是否重复
		if (thisService.IsFieldExist("userName", userName, "-1")) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"用户名不能重复！\""));
			return;
		}
		
//		String deptJobId = request.getParameter("deptJobId");
		// 获取当前操作用户    
        PtUser currentUser = getCurrentSysUser();
        entity=thisService.doAddTeacher(entity,currentUser/*,deptJobId*/);  

		// 返回实体到前端界面
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	}

	/**
	 * @param entity
	 * @param file
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = { "/doUpdate" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void doUpdates(PtTeacherBaseInfo entity, @RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {
		// 入库前检查代码
		try {
			String hql1 = " o.isDelete='0'";
			if (thisService.IsFieldExist("identityNumber", entity.getIdentityNumber(), entity.getId(), hql1)) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"教师的身份证件号不能重复！\""));
				return;
			}
			if (StringUtils.isNotEmpty(entity.getUserName())&&thisService.IsFieldExist("userName", entity.getUserName(), entity.getId(), hql1)) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"用户名不能重复！\""));
				return;
			}
			if (StringUtils.isNotEmpty(entity.getUserNumb())&&thisService.IsFieldExist("userNumb", entity.getUserNumb(), entity.getId(), hql1)) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"工号不能重复！\""));
				return;
			}

			if (!file.isEmpty() && file.getSize() > 0) {
				// 图片服务器路径
				String file_path = realFileUrl;// String file_path =
				// 取得当前上传文件的文件名称
				String myFileName = file.getOriginalFilename();
				if (myFileName.trim() != "") {// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					// 重命名上传后的文件名
					String type = myFileName.substring(myFileName.lastIndexOf("."));

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String url = "TeacherBase/" + sdf.format(System.currentTimeMillis()) + "/";

					// 定义上传路径
					String path = file_path + url + myFileName;
					File localFile = new File(path);

					if (!localFile.exists()) { // 判断文件夹是否存在
						localFile.mkdirs(); // 不存在则创建
					}

					file.transferTo(localFile);
					entity.setPhoto(url + myFileName);
				}
			}
			// 获取当前的操作用户
			PtUser currentUser = getCurrentSysUser();
			entity = thisService.doUpdateEntity(entity, currentUser.getId(), null);

			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));

		} catch (Exception e) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"请求失败,请联系管理员！\""));
		}

	}

}