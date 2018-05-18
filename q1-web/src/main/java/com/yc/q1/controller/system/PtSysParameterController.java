package com.yc.q1.controller.system;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.system.PtSysParameter;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.VersionInfo;
import com.yc.q1.service.base.pt.system.PtSysParameterService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;


/**
 * 岗位管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PtSysParameter")
public class PtSysParameterController extends FrameWorkController<PtSysParameter> implements Constant {

	@Resource
	PtSysParameterService thisService; // service层接口

	@Resource
	private PrimaryKeyRedisService keyRedisService;
	
	@Value("${realFileUrl}")
	private String realFileUrl; // 文件目录物理路径

	@Value("${virtualFileUrl}")
	private String virtualFileUrl; // 文件目录虚拟路径

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
	public void list(PtSysParameter entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<PtSysParameter> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	
	/**
	 * 标准的查询列表功能
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/versionInfoList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void versionInfoList(VersionInfo entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		String sql = "from PtSysParameter where";
	
		//客户名称
		String clientNamesql = sql+" sysParamCode='CLIENTNAME' and isDelete=0 ";
		PtSysParameter clientNameList = thisService.getEntityByHql(clientNamesql);
		entity.setClientNameId(clientNameList.getId());
		entity.setClientName(clientNameList.getSysParamValue());
		entity.setClientNameCode(clientNameList.getSysParamCode());
		entity.setClientNameName(clientNameList.getSysParamName());
		
		//公司名称
		String serviceNamesql = sql+" sysParamCode='SERVICENAME' and isDelete=0 ";
		PtSysParameter serviceNameList = thisService.getEntityByHql(serviceNamesql);
		entity.setServiceNameId(serviceNameList.getId());
		entity.setServiceName(serviceNameList.getSysParamValue());
		entity.setServiceNameCode(serviceNameList.getSysParamCode());
		entity.setServiceNameName(serviceNameList.getSysParamName());
		
		//版本信息
		String versionNamesql = sql+" sysParamCode='VERSIONNAME' and isDelete=0 ";
		PtSysParameter versionNameList = thisService.getEntityByHql(versionNamesql);
		entity.setVarsionNameId(versionNameList.getId());
		entity.setVarsionName(versionNameList.getSysParamValue());
		entity.setVarsionNameCode(versionNameList.getSysParamCode());
		entity.setVarsionNameName(versionNameList.getSysParamName());
		
		strData = jsonBuilder.toJson(entity);
		writeJSON(response, strData);// 返回数据
		
	}

	/**
	 * 标准的添加功能
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("SYSPARAMETER_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtSysParameter entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();
		entity.setId(keyRedisService.getId(PtSysParameter.ModuleType));
		entity = thisService.doAddEntity(entity, currentUser.getId());

		if (entity == null)
			writeJSON(response, jsonBuilder.returnFailureJson("\"添加失败，请重试或联系管理员！\""));
		else
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	}

	/**
	 * doDelete @Title: doDelete @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@Auth("SYSPARAMETER_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入删除主键\""));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE, currentUser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		}
	}

	/**
	 * doUpdate编辑记录 @Title: doUpdate @Description: TODO @param @param
	 * BizTJob @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@Auth("SYSPARAMETER_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(PtSysParameter entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String userCh = getCurrentSysUser().getId();
		PtSysParameter resultEntity = thisService.doUpdateEntity(entity, userCh, null);
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(resultEntity)));
	}
	
	/**
	 * doUpdate编辑记录 @Title: doUpdate @Description: TODO @param @param
	 * BizTJob @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@Auth("SYSPARAMETER_update")
	@RequestMapping("/doUpdateVer")
	public void doUpdateVer(VersionInfo entity, @RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String userCh = getCurrentSysUser().getId();
		if (!file1.isEmpty() && file1.getSize() > 0) {
			// 图片服务器路径
			//String file_path = realFileUrl;// String file_path =
			// 取得当前上传文件的文件名称
			String myFileName = file1.getOriginalFilename();
			if (myFileName.trim() != "") {// 如果名称不为“”,说明该文件存在，否则说明该文件不存在						
				String type = myFileName.substring(myFileName.lastIndexOf("."));		
				if(!type.equalsIgnoreCase(".PNG")&&!type.equalsIgnoreCase(".JPG")&&!type.equalsIgnoreCase(".JPEG")){
 	            	writeJSON(response, jsonBuilder.returnFailureJson("'上传失败,请选择PNG|JPG|JPEG类型的图片！'"));
 	            	return;
 	            }	
				/*
				 * // 重命名上传后的文件名	
				String fileName = String.valueOf(System.currentTimeMillis()) + type;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String url = "versionInfo/" + sdf.format(System.currentTimeMillis()) + "/";
				*/

				// 定义上传路径
				String rootPath = request.getSession().getServletContext().getRealPath("/");
	 	        rootPath = rootPath.replace("\\", "/");
				String path = "/static/core/resources/images/login/login_logo.png";
				File localFile = new File(rootPath+path);

				if (!localFile.exists()) { // 判断文件夹是否存在
					localFile.mkdirs(); // 不存在则创建
				}

				file1.transferTo(localFile);
			}
		}
		if (!file2.isEmpty() && file2.getSize() > 0) {
			// 图片服务器路径
			//String file_path = realFileUrl;// String file_path =
			// 取得当前上传文件的文件名称
			String myFileName = file2.getOriginalFilename();
			if (myFileName.trim() != "") {// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				String type = myFileName.substring(myFileName.lastIndexOf("."));		
				if(!type.equalsIgnoreCase(".PNG")&&!type.equalsIgnoreCase(".JPG")&&!type.equalsIgnoreCase(".JPEG")){
 	            	writeJSON(response, jsonBuilder.returnFailureJson("'上传失败,请选择PNG|JPG|JPEG类型的图片！'"));
 	            	return;
 	            }
				/*
				String type = myFileName.substring(myFileName.lastIndexOf("."));
				String fileName = String.valueOf(System.currentTimeMillis()) + type;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String url = "versionInfo/" + sdf.format(System.currentTimeMillis()) + "/";
				 */
				// 定义上传路径
				String rootPath = request.getSession().getServletContext().getRealPath("/");
	 	        rootPath = rootPath.replace("\\", "/");
				String path = "/static/core/resources/images/index_logo.png";
				File localFile = new File(rootPath+path);
				
				if (!localFile.exists()) { // 判断文件夹是否存在
					localFile.mkdirs(); // 不存在则创建
				}

				file2.transferTo(localFile);
			}
		}
		
		if (!file3.isEmpty() && file3.getSize() > 0) {
			// 图片服务器路径
			//String file_path = realFileUrl;// String file_path =
			// 取得当前上传文件的文件名称
			String myFileName = file3.getOriginalFilename();
			if (myFileName.trim() != "") {// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				String type = myFileName.substring(myFileName.lastIndexOf("."));		
				if(!type.equalsIgnoreCase(".PNG")&&!type.equalsIgnoreCase(".JPG")&&!type.equalsIgnoreCase(".JPEG")){
 	            	writeJSON(response, jsonBuilder.returnFailureJson("'上传失败,请选择PNG|JPG|JPEG类型的图片！'"));
 	            	return;
 	            }
				/*
				String type = myFileName.substring(myFileName.lastIndexOf("."));
				String fileName = String.valueOf(System.currentTimeMillis()) + type;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String url = "versionInfo/" + sdf.format(System.currentTimeMillis()) + "/";
				 */
				// 定义上传路径
				String rootPath = request.getSession().getServletContext().getRealPath("/");
	 	        rootPath = rootPath.replace("\\", "/");
				String path = "/static/core/resources/images/index_title.png";
				File localFile = new File(rootPath+path);

				if (!localFile.exists()) { // 判断文件夹是否存在
					localFile.mkdirs(); // 不存在则创建
				}

				file3.transferTo(localFile);
			}
		}
		
		boolean flag = thisService.doUpdateVerInfo(entity, userCh);
		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"修改成功\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"修改失败\""));
		}
	}

}
