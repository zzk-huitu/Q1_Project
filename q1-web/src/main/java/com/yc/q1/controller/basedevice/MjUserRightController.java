package com.yc.q1.controller.basedevice;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.AdminType;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.JsonBuilder;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.mj.MjUserRight;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.CommTree;
import com.yc.q1.service.base.mj.MjUserRightService;
import com.yc.q1.service.base.pt.basic.CommTreeService;

/**
 * 门禁权限
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/MjUserRight")
public class MjUserRightController extends FrameWorkController<MjUserRight> implements Constant {

	@Resource
	MjUserRightService thisService; // service层接口

	@Resource
	CommTreeService treeService; // 生成树

	/**
	 * 门禁权限列表
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute MjUserRight entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据

		QueryResult<MjUserRight> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据

		writeJSON(response, strData);// 返回数据

	}

	/**
	 *  获取所有区域 与 房间的数据（包含未定义）
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/treelist")
	public void getGradeTreeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = "";
		String whereSql = request.getParameter("whereSql");
		List<CommTree> lists = treeService.getCommTree("V_PT_AreaRoomInfoTree", whereSql);
		strData = JsonBuilder.getInstance().buildList(lists, "");// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 生成树 获取分配了学生宿舍的班级房间数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/treeStuDormList")
	public void getStuDormList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = "";
		String whereSql = request.getParameter("whereSql");
		List<CommTree> lists = treeService.getCommTree("V_PT_StudentDromAllotTree", whereSql);
		strData = JsonBuilder.getInstance().buildList(lists, "");// 处理数据
		writeJSON(response, strData);// 返回数据
	}

	/**
	 * 
	 * @Title: 增加新实体信息至数据库 @Description: TODO @param @param MjUserright
	 *         实体类 @param @param request @param @param response @param @throws
	 *         IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping("/doAdd")
	public void doAdd(MjUserRight entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();

		entity = thisService.doAddEntity(entity, currentUser);// 执行增加方法
		if (ModelUtil.isNotNull(entity))
			writeJSON(response, jsonBuilder.returnSuccessJson("\"数据增加成功\""));
		else
			writeJSON(response, jsonBuilder.returnFailureJson("\"数据增加失败,详情见错误日志\""));
	}

	/**
	 * doDelete @Title: 逻辑删除指定的数据 @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@Auth("USERACCESS_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		if (StringUtils.isEmpty(ids)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(ids, StatuVeriable.ISDELETE, currentUser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		}
	}

	/**
	 * 给设备增加用户权限
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("USERACCESS_add")
	@RequestMapping("/doAddMj")
	public void doAddMj(@RequestParam("userId") String userId, @RequestParam("termId") String termId,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, IllegalAccessException, InvocationTargetException {

		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();

		thisService.doAddMj(userId, termId, currentUser);// 执行增加方法

		writeJSON(response, jsonBuilder.returnSuccessJson("\"增加权限成功\""));
	}

	/**
	 * 按人员查看权限列表
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/mjrightlist" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void mjrightlist(@ModelAttribute MjUserRight entity, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		String strData = ""; // 返回给js的数据
		String sort = super.sort(request);
		String querySql = querySql(request);
		String orderSql = orderSql(request);
		String querySql2 = request.getParameter("querySql2");// 快速搜索框中的参数
		String sql = " select  *  from PT_V_USERROOM  where 1=1 ";
		sql = sql + querySql + querySql2 + orderSql;
		QueryResult qResult = thisService.queryPageResultBySql(sql, super.start(request), super.limit(request),
				ViewUserRoom.class);
		// QueryResult qResult = thisService.getDao().getForValuesToSql(start,
		// limit, querySql,orderSql,sql, sql1);
		strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
		*/
		String strData = ""; // 返回给js的数据
		String filter = request.getParameter("filter");
		String userId = request.getParameter("userId");
		
		if (StringUtils.isNotEmpty(filter)) {
			filter = filter.substring(0, filter.length() - 1);
			filter += ",{\"type\":\"string\",\"comparison\":\"=\",\"value\":\"" + userId
					+ "\",\"field\":\"userId\"}" + "]";
		} else {
			filter = "[{\"type\":\"string\",\"comparison\":\"=\",\"value\":\"" + userId
					+ "\",\"field\":\"userId\"}]";
		}
		
		QueryResult<MjUserRight> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), filter, true);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据

		writeJSON(response, strData);// 返回数据
		
	}

	/**
	 * 获取房间有权限的用户设备列表（按房间查看权限列表）
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/roomUserRightList" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void roomUserRightList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		String filter = request.getParameter("filter");
		String roomId = request.getParameter("roomId");
		String roomLeaf = request.getParameter("roomLeaf");
		
		List<String> termList=new ArrayList<>();
		if (StringUtils.isNotEmpty(roomId) && !AdminType.ADMIN_ORG_ID.equals(roomId)) {
			if ("1".equals(roomLeaf)) { // 当选择的区域为房间时		
				termList=getPtTermIds(roomId);		
				
			} else {					// 当选择的区域不为房间时			
				List<String> roomList = getRoomIds(roomId);						
				if(!roomList.isEmpty()){				
					String areaIds = roomList.stream().collect(Collectors.joining("','"));
					termList=getPtTermIds(areaIds);
					
				}else{	// 若区域之下没有房间，则直接返回空数据				
					strData = jsonBuilder.buildObjListToJson(0L,new ArrayList<>(), true);// 处理数据
					writeJSON(response, strData);// 返回数据
					return;
				}				
			}
		}
		
		if(!termList.isEmpty()){
			String termIds=termList.stream().collect(Collectors.joining(","));	
			if (StringUtils.isNotEmpty(filter)) {
				filter = filter.substring(0, filter.length() - 1);
				filter += ",{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + termIds
						+ "\",\"field\":\"termId\"}" + "]";
			} else {
				filter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + termIds
						+ "\",\"field\":\"termId\"}]";
			}
		}else{
			strData = jsonBuilder.buildObjListToJson(0L,new ArrayList<>(), true);// 处理数据
			writeJSON(response, strData);// 返回数据
			return;
		}
		
		
		QueryResult<MjUserRight> qResult = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), filter, true);
		strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
		
		/*old
		String strData = ""; // 返回给js的数据
		String roomId = request.getParameter("roomId");
		String roomLeaf = request.getParameter("roomLeaf");
		String orderSql = orderSql(request);
		String querySql = "";
		String querySql2 = request.getParameter("querySql2");// 快速搜索框中的参数
		
		
		String sql = " select  USER_ID as USER_ID,XM as XM,ROOM_ID as ROOM_ID,ROOM_CODE as ROOM_CODE,"
				+ " EXT_FIELD01 as EXT_FIELD01,AREA_ID as AREA_ID,ROOM_NAME as ROOM_NAME,ROOM_TYPE as ROOM_TYPE"
				+ " from PT_V_USERROOM  where 1=1 ";
		String countSql = "select count(*) from PT_V_USERROOM  where 1=1  ";
		
		
		if (StringUtils.isNotEmpty(roomId) && !AdminType.ADMIN_ORG_ID.equals(roomId)) {
			if ("1".equals(roomLeaf)) { // 当选择的区域为房间时
				querySql = " and ROOM_ID ='" + roomId + "'";
			} else { // 当选择的区域不为房间时
				List<String> roomList = getRoomIds(roomId);

				if (!roomList.isEmpty()) {
					String roomIds = roomList.stream().collect(Collectors.joining("','", "'", "'"));
					querySql = " and ROOM_ID in (" + roomIds + ")";

				} else { // 若区域之下没有房间，则直接返回空数据

					strData = jsonBuilder.buildObjListToJson(0L, new ArrayList<>(), true);// 处理数据
					writeJSON(response, strData);// 返回数据
					return;
				}
			}
		}
		sql = sql + querySql + querySql2 + orderSql;
		countSql = countSql + querySql + querySql2 + orderSql;

		QueryResult<ViewUserRoom> qResult = thisService.queryPageResultBySql(sql, super.start(request),
				super.limit(request), ViewUserRoom.class, countSql);
		strData = jsonBuilder.buildObjListToJson(qResult.getTotalCount(), qResult.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
		*/
	}

	/**
	 * 获取某个区域下的所有房间数据
	 * 
	 * @param roomId
	 * @param roomLeaf
	 * @return
	 */
	private List<String> getRoomIds(String areaId) {
		List<String> result = new ArrayList<>();

		// 当选择的区域不为房间时
		String hql = "select a.id from PtRoomArea a where a.isDelete=0  and a.areaType='04' and a.treeIds like '%"
				+ areaId + "%'";
		List<String> lists = thisService.queryEntityByHql(hql);
		if (lists.size() > 0) {
			String areaIds = lists.stream().collect(Collectors.joining("','", "'", "'"));
			hql = "select a.id from PtRoomInfo a where a.isDelete=0  and a.roomType!='0' and a.areaId in (" + areaIds + ")";
			result = thisService.queryEntityByHql(hql);
		}

		return result;
	}
	
	private List<String> getPtTermIds(String roomIds){
		List<String> result = new ArrayList<>();

		String sql = "select b.termId from T_PT_RoomInfo a join T_PT_Term b "
				+ "	on a.roomId=b.roomId"
				+ " where a.isDelete=0 and a.roomType!=0 and b.isDelete=0 "
				+ " and a.roomId in ('" + roomIds + "')";
		result = thisService.queryEntityBySql(sql,null);
		
		return result;
	}

}
