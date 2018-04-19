package com.yc.q1.controller.wisdomclass;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.AdminType;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.basic.PtAttachment;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.wisdomclass.PtClassMien;
import com.yc.q1.service.base.pt.basic.PtAttachmentService;
import com.yc.q1.service.base.pt.wisdomclass.PtClassMienService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 班级风采
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PtClassMien")
public class PtClassMienController extends FrameWorkController<PtClassMien> implements Constant{
	@Resource
	PtClassMienService thisService; // service层接口
	
	@Resource
	PtAttachmentService baseTAttachmentService;// service层接口
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	@Value("${realFileUrl}")  
    private String realFileUrl; //文件目录物理路径
	
	@Value("${virtualFileUrl}")  
    private String virtualFileUrl; //文件目录虚拟路径
	
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute PtClassMien entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		String filter=request.getParameter("filter");;
		String classId = request.getParameter("classId");
		String claiIdLeaf = request.getParameter("claiIdLeaf");
		
		if (StringUtils.isNotEmpty(classId) && !AdminType.ADMIN_ORG_ID.equals(classId)) {
			if ("1".equals(claiIdLeaf)) { // 当选择的区域为房间时
				if (StringUtils.isNotEmpty(filter)) {
					filter = filter.substring(0, filter.length() - 1);
					filter += ",{\"type\":\"string\",\"comparison\":\"=\",\"value\":\"" + classId
							+ "\",\"field\":\"classId\"}" + "]";
				} else {
					filter = "[{\"type\":\"string\",\"comparison\":\"=\",\"value\":\"" + classId
							+ "\",\"field\":\"classId\"}]";
				}
			} else {					// 当选择的区域不为房间时
				// 当选择的区域不为房间时
				List<String> claiIdList = new ArrayList<>();
				String hql = "select a.id from PtDepartment a where a.isDelete=0  and a.deptType='05' and a.treeIds like '%"
						+ classId + "%'";
		    	claiIdList = thisService.queryEntityByHql(hql);
			
				if(!claiIdList.isEmpty()){
					String roomIds=claiIdList.stream().collect(Collectors.joining(","));		
					if (StringUtils.isNotEmpty(filter)) {
						filter = filter.substring(0, filter.length() - 1);
						filter += ",{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + roomIds
								+ "\",\"field\":\"classId\"}" + "]";
					} else {
						filter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + roomIds
								+ "\",\"field\":\"classId\"}]";
					}
					
				}else{	// 若区域之下没有房间，则直接返回空数据
					
					strData = jsonBuilder.buildObjListToJson(0L,new ArrayList<>(), true);// 处理数据
					writeJSON(response, strData);// 返回数据
					return;
				}				
			}
		}	

		QueryResult<PtClassMien> qResult = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), filter, true);
		strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	
	/**
	 * 上传文件
	 * 
	 * @param sendId
	 * @param file
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/doUpload" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void doUpload(@RequestParam("recordId") String recordId,
			@RequestParam(value = "attachIsMain", required = false, defaultValue = "0") Integer attachIsMain,
			@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		try {

			if (file != null) {
				//图片服务器路径  
		        //String file_path = "D:\\Q1_Files\\uploadFiles\\";  
				String file_path =realFileUrl;
				
				// 取得当前上传文件的文件名称
				String myFileName = file.getOriginalFilename();
				
				// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if (myFileName.trim() != "") {
					// 重命名上传后的文件名
					String type = myFileName.substring(myFileName.lastIndexOf("."));
					//String fileName = String.valueOf(System.currentTimeMillis()) + type;

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					//String url = "/static/upload/OaNotice/" + sdf.format(System.currentTimeMillis()) + "/";
					String url = "PtClassMien/" + sdf.format(System.currentTimeMillis()) + "/";
					//String rootPath = request.getSession().getServletContext().getRealPath("/");
					//rootPath = rootPath.replace("\\", "/");				
					
					// 定义上传路径
					String path = file_path+ url + myFileName;
					File localFile = new File(path);

					if (!localFile.exists()) { // 判断文件夹是否存在
						localFile.mkdirs(); // 不存在则创建
					}

					file.transferTo(localFile);
					

					// 插入数据
					PtAttachment bt = new PtAttachment();
					bt.setId(keyRedisService.getId(PtAttachment.ModuleType));
					bt.setEntityName("PtClassMien");
					bt.setRecordId(recordId);
					bt.setFileUrl(url + myFileName);
					bt.setFileName(myFileName);
					bt.setFileType(type);
					bt.setFileSize(file.getSize());
					//bt.setAttachIsMain(attachIsMain);
					baseTAttachmentService.merge(bt);

					writeJSON(response, "{ \"success\" : true,\"obj\":\"" + url + myFileName + "\"}");
				}
			}

		} catch (Exception e) {
			writeJSON(response, "{ \"success\" : false,\"obj\":null}");
			return;
		}
	}
	
	@RequestMapping("/doDeleteFile") // Filename sendId
	public void doDeleteFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String fileIds = request.getParameter("fileIds");

			String doIds = "'" + fileIds.replace(",", "','") + "'";

			String hql = "DELETE FROM PtAttachment b  WHERE b.id IN (" + doIds + ")";

			int flag = baseTAttachmentService.doExecuteCountByHql(hql);

			if (flag > 0) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		} catch (Exception e) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败,请刷新重试！\""));
		}
	}
	

	
	@Auth("CLASSELEGANT_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtClassMien entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();
		entity.setId(keyRedisService.getId(PtClassMien.ModuleType));
		entity = thisService.doAddEntity(entity, currentUser.getId());

		if (entity == null)
			writeJSON(response, jsonBuilder.returnFailureJson("\"添加失败，请重试或联系管理员！\""));
		else
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	}
	
	@Auth("CLASSELEGANT_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"没有传入删除主键\""));
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
	
	@Auth("CLASSELEGANT_update")
	@RequestMapping("/doUpdate")
	public void doUpdate(PtClassMien entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 入库前检查代码

		// 获取当前的操作用户
		PtUser currentUser = getCurrentSysUser();

		entity = thisService.doUpdateEntity(entity, currentUser.getId(), null);

		if (entity == null)
			writeJSON(response, jsonBuilder.returnFailureJson("\"修改失败，请重试或联系管理员！\""));
		else
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));

	}
}
