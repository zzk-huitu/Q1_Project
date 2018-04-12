package com.zd.school.plartform.baseset.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.model.base.pt.build.PtDormDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.build.ClassRoomDefineService;
import com.yc.q1.service.base.pt.build.DormDefineService;
import com.yc.q1.service.base.pt.build.FuncRoomDefineService;
import com.yc.q1.service.base.pt.build.OfficeDefineService;
import com.yc.q1.service.base.pt.build.RoomInfoService;
import com.zd.core.annotation.Auth;
import com.zd.core.constant.Constant;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.util.ModelUtil;
import com.zd.core.util.StringUtils;

/**
 * 房间定义
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/BaseRoomdefine")
public class BaseRoomdefineController extends FrameWorkController<PtRoomInfo> implements Constant {

	@Resource
	private RoomInfoService thisService; // service层接口
	@Resource
	ClassRoomDefineService classRoomService;// 教室service层接口
	@Resource
	DormDefineService dormRoomService; // 宿舍service层接口
	@Resource
	OfficeDefineService offRoomService; // 办公室service层接口
	@Resource
	FuncRoomDefineService funRoomService; // 功能室service层接口
	
	/**
	 * 定义房间
	 * @param entity
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
    @Auth("BASEROOMDEFINE_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtRoomInfo entity, HttpServletRequest request, HttpServletResponse response)
			throws Exception, IllegalAccessException, InvocationTargetException {

		boolean flag = true;
		
		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();
		String userCh =  currentUser.getId();
		
		// 在add前判断房间名称是否唯一
		String roomName = entity.getRoomName();
		Integer conuts = thisService.getCount(roomName);
		if (conuts > 0) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"该房间名称已存在,不能重复。\""));
			return;
		}
		
		flag=thisService.doAddRoomDefine(entity, request,userCh);
		
		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"增加成功。\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"添加失败，房间可能已经定义了！\""));
		}

	}
    
    /**
     * 解除房间定义
     * @param request
     * @param response
     * @throws Exception
     */
    @Auth("BASEROOMDEFINE_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Boolean flag = false;
		
		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();
		String xm = currentUser.getId();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入删除主键\""));
			return;
		}
		
		flag=thisService.doDeleteRoomDefine(delIds,xm,hashMap);
		
		if (flag) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
		} else {
			writeJSON(response, jsonBuilder.returnFailureJson("\"操作未完全成功！<br/><br/>"
					+ "房间【"+hashMap.get("roomNames")+"】可能已分配，不允许删除！\""));
		}

	}
    
    
	@RequestMapping("/doUpdate")
	public void doUpdate(PtDormDefine entity, HttpServletRequest request, HttpServletResponse response)
			throws Exception, IOException, IllegalAccessException, InvocationTargetException {

		PtUser currentUser = getCurrentSysUser();
		entity = dormRoomService.doUpdateEntity(entity, currentUser);

		if (ModelUtil.isNotNull(entity))
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
		else
			writeJSON(response, jsonBuilder.returnFailureJson("\"数据修改失败,详情见错误日志\""));

	}

	/**
	 * 获取宿舍实体
	 * @param roomId
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/getDormEntity")
	public void getDormEntity(String roomId, HttpServletRequest request, HttpServletResponse response)
			throws Exception, IOException, IllegalAccessException, InvocationTargetException {
		PtDormDefine entity = null;
		if (!roomId.isEmpty()) {
			entity = dormRoomService.getByRoomId(roomId);
		}
		if (ModelUtil.isNotNull(entity))
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
		else
			writeJSON(response, jsonBuilder.returnFailureJson("\"获取宿舍对象失败,详情见错误日志\""));
	}
	/**
	 * 用于一键分配宿舍时使用（获取男生、女生可用宿舍）/ 添加班级宿舍时
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/onKeylist" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void onKeylist(@ModelAttribute PtDormDefine entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		String filter = request.getParameter("filter");
		String areaId = request.getParameter("areaId");
		String hql = "select a.id from RoomArea a where a.isDelete=0  and a.areaType='04' and a.treeIds like '%"
				+ areaId + "%'";
		List<String> lists = thisService.queryEntityByHql(hql);
		StringBuffer sb = new StringBuffer();
		String areaIds = "";
		for (int i = 0; i < lists.size(); i++) {
			sb.append(lists.get(i) + ",");
		}
		if(sb.length()>0){
			List<String> roomIdLists = new ArrayList<>();
			areaIds = sb.substring(0, sb.length() - 1);

			hql = "select a.id from DormDefine a where a.isDelete=0 and a.dormCategory='1' and a.areaId in ('"
					+ areaIds.replace(",", "','") + "')";
			roomIdLists = dormRoomService.queryEntityByHql(hql);
			sb.setLength(0);
			for (int i = 0; i < roomIdLists.size(); i++) {
				sb.append(roomIdLists.get(i) + ",");
			}
			if (sb.length() > 0) {
				filter = filter.substring(0, filter.length()-1);
				filter+=",{\"type\":\"string\",\"comparison\":\"in\",\"value\":\""+ sb.substring(0,sb.length()-1)+"\",\"field\":\"roomId\"}"+"]";
			} else {
				filter = filter.substring(0, filter.length()-1);
				filter+=",{\"type\":\"string\",\"comparison\":\"in\",\"value\":\""+ areaId+"\",\"field\":\"roomId\"}"+"]";
			}

		}else{
			   filter = filter.substring(0, filter.length()-1);
			   filter+=",{\"type\":\"string\",\"comparison\":\"in\",\"value\":\""+ areaId+"\",\"field\":\"roomId\"}"+"]";
			}
		QueryResult<PtDormDefine> qr = dormRoomService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), filter, true);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
}
