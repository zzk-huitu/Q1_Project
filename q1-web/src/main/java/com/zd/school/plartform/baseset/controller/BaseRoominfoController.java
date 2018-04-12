package com.zd.school.plartform.baseset.controller;

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

import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.CommTree;
import com.yc.q1.service.base.pt.basic.CommTreeService;
import com.yc.q1.service.base.pt.build.PtRoomInfoService;
import com.zd.core.annotation.Auth;
import com.zd.core.constant.Constant;
import com.zd.core.constant.StatuVeriable;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.util.JsonBuilder;
import com.zd.core.util.StringUtils;

/**
 * 区域房间信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/BaseRoominfo")
public class BaseRoominfoController extends FrameWorkController<PtRoomInfo> implements Constant {

	
	@Resource
	private PtRoomInfoService thisService; // service层接口
	@Resource
	private CommTreeService treeService; // 生成树
	
	/**
	 * 根据传入的区域id，查询房间信息
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute PtRoomInfo entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		String filter = request.getParameter("filter");
		String areaId = request.getParameter("areaId");
		String areaType = request.getParameter("areaType")==null?"04":request.getParameter("areaType");
		
		//若为类型不为楼层，则去查询此区域下的所有楼层
		if(!"04".equals(areaType)){
			String hql="select a.id from RoomArea a where a.isDelete=0 and a.areaType='04' and a.treeIds like '%"+areaId+"%'";
			List<String> lists=thisService.queryEntityByHql(hql);
			if(!lists.isEmpty()){
				String areaIds=lists.stream().collect(Collectors.joining(","));
				if(filter.length()>0){
					filter = filter.substring(0, filter.length()-1);
					filter+=",{\"type\":\"string\",\"comparison\":\"in\",\"value\":\""+ areaIds+"\",\"field\":\"areaId\"}"+"]";
				}else{
					filter="[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\""+ areaIds+"\",\"field\":\"areaId\"}]";
				}
			}else{//为楼栋或校区，其下没有楼层

				strData = jsonBuilder.buildObjListToJson(0L,new ArrayList<>(), true);// 处理数据
				writeJSON(response, strData);// 返回数据
				return;
			}
		}else{
			if(areaId!=null){
				if(filter.length()>0){
					filter = filter.substring(0, filter.length()-1);
					filter+=",{\"type\":\"string\",\"comparison\":\"=\",\"value\":\""+areaId+"\",\"field\":\"areaId\"}"+"]";
				}else{
					filter="[{\"type\":\"string\",\"comparison\":\"=\",\"value\":\""+ areaId+"\",\"field\":\"areaId\"}]";
				}
			}
		}
		
		QueryResult<PtRoomInfo> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), filter, true);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 添加房间
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("JWTROOMINFO_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtRoomInfo entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String areaId = entity.getAreaId();
		String roomCode = entity.getRoomCode();
		//String rommType = entity.getRoomType();
		// 此处为放在入库前的一些检查的代码，如唯一校验等
		String hql = " o.isDelete='0' and o.areaId='" + areaId + "' ";
		if (thisService.IsFieldExist("roomCode", roomCode, "-1", hql)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"同一区域已有此编号的房间！\""));
			return;
		}

		PtUser currentUser = getCurrentSysUser();

		Integer orderIndex = thisService.getDefaultOrderIndex(entity);
		entity.setOrderIndex(orderIndex);
		entity.setRoomType("0");		//强制为 未定义类型
		entity.setRoomName(roomCode);	//默认使用编号的命名
		entity = thisService.doAddEntity(entity, currentUser.getId());

		if (entity == null)
			writeJSON(response, jsonBuilder.returnFailureJson("\"添加失败，请重试或联系管理员！\""));
		else
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));

	}

	/**
	 * 批量添加房间
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("JWTROOMINFO_batchAdd")
	@RequestMapping("/doBatchAdd")
	public void doBatchAdd(PtRoomInfo entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		PtUser currentUser = getCurrentSysUser();
	
		Boolean bResult = thisService.doBatchAddRoom(entity, currentUser);
		if (bResult) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"批量添加房间成功\""));
		} else
			writeJSON(response, jsonBuilder.returnSuccessJson("\"批量添加房间失败，详情见错误日志\""));		
	}

	/**
	 * 删除房间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Auth("JWTROOMINFO_delete")
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
	 * 更新房间
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("JWTROOMINFO_update")
	@RequestMapping("/doUpdate")
	public void doUpdate(PtRoomInfo entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String areaId = entity.getAreaId();
		String roomCode = entity.getRoomCode();
		String roomId = entity.getId();
		// 此处为放在入库前的一些检查的代码，如唯一校验等
		String hql = " o.isDelete='0' and o.areaId='" + areaId + "' ";
		if (thisService.IsFieldExist("roomCode", roomCode, roomId, hql)) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"同一区域已有此编号的房间！\""));
			return;
		}
	
		PtUser currentUser = getCurrentSysUser();	
		
		entity=thisService.doUpdateEntity(entity, currentUser.getId(), null);
        
        if(entity==null)
       	 	writeJSON(response, jsonBuilder.returnFailureJson("\"修改失败，请重试或联系管理员！\""));
        else        
        	writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));              

	}
}
