package com.yc.q1.controller.basedevice;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.core.util.JsonBuilder;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.core.util.TLVUtils;
import com.yc.q1.model.base.pt.basic.PtInfoTerminal;
import com.yc.q1.model.base.pt.device.PtFrontServer;
import com.yc.q1.model.base.pt.device.PtGateway;
import com.yc.q1.model.base.pt.device.PtTerm;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.CommTree;
import com.yc.q1.pojo.base.pt.TLVModel;
import com.yc.q1.service.base.pt.basic.CommTreeService;
import com.yc.q1.service.base.pt.device.PtGatewayService;

/**
 * 网关表
 * @author hucy
 *
 */
@Controller
@RequestMapping("/PtGateway")
public class PtGatewayController extends FrameWorkController<PtGateway> implements Constant  {

	@Resource
	PtGatewayService thisService; // service层接口

	@Resource
	CommTreeService treeService; // 生成树
	/**
	 * 网关列表
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute PtGateway entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据

		QueryResult<PtGateway> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据

		writeJSON(response, strData);// 返回数据
		
	}

	/**
	 * 前置列表树
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/treelist")
	public void getGradeTreeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = "";
		String whereSql = request.getParameter("whereSql");
		List<CommTree> lists = treeService.getCommTree("V_PT_FrontServerTree", whereSql);
		strData = JsonBuilder.getInstance().buildList(lists, "");// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	
	/**
	 * 逻辑删除网关
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Auth("BASEGATEWAY_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids=request.getParameter("ids");
		if (StringUtils.isEmpty(ids)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(ids, StatuVeriable.ISDELETE,currentUser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		}
	}

	/**
	 * doRestore还原删除的记录 @Title: doRestore @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@RequestMapping("/doRestore")
	public void doRestore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入还原主键\""));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISNOTDELETE,currentUser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"还原成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"还原失败\""));
			}
		}
	}

	/**
	 * 批量设置前置
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("BASEGATEWAY_updateBatch")
	@RequestMapping("/doUpdateBatch")
	public void doupdateBatch(PtGateway entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		
		PtUser currentUser = getCurrentSysUser();
		
		thisService.doUpdateBatchFront(entity,currentUser.getId());
		
		writeJSON(response, jsonBuilder.returnSuccessJson("\"处理成功\""));
	}
	
	/**
	 * 修改
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("BASEGATEWAY_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(PtGateway entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		
		PtUser currentUser = getCurrentSysUser();
		   
        entity = thisService.doUpdateEntity(entity, currentUser);// 执行修改方法
        if (ModelUtil.isNotNull(entity))
            writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
        else
            writeJSON(response, jsonBuilder.returnFailureJson("\"数据修改失败,详情见错误日志\""));
      
    }
	
	@RequestMapping("/doAdd")
    public void doAdd(PtGateway entity, HttpServletRequest request, HttpServletResponse response)
            throws IOException, IllegalAccessException, InvocationTargetException {

   
        // 获取当前操作用户
        PtUser currentUser = getCurrentSysUser();
      
        entity = thisService.doAddEntity(entity, currentUser);// 执行增加方法
        if (ModelUtil.isNotNull(entity))
            writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
        else
            writeJSON(response, jsonBuilder.returnFailureJson("\"数据增加失败,详情见错误日志\""));      
}

	
	/**
	 * 批量设置高级参数（弃用）
	 * 
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/batchHighParam")
	public void batchHighParam(TLVModel tlvs,HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		byte[] result=TLVUtils.encode(tlvs.getTlvs());
		List<PtGateway> list=thisService.queryAll();
		String userCh = "超级管理员";
		PtUser currentUser = getCurrentSysUser();
		if (currentUser != null)
			userCh = currentUser.getId();
		for(PtGateway gateway:list){
			gateway.setUpdateUser(userCh);
			gateway.setUpdateTime(new Date());
			gateway.setAdvParam(result);
			thisService.merge(gateway);
		}
		writeJSON(response, jsonBuilder.returnSuccessJson("'高级参数批量设置成功。'"));

	}
	
	/**
	 * 基础参数批量设置（弃用）
	 * @param tlvs
	 * @param termTypeID
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/batchBaseParam")
	public void batchBaseParam(TLVModel tlvs, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
			byte[] result=TLVUtils.encode(tlvs.getTlvs());
			List<PtGateway> list=thisService.queryAll();
			String userCh = "超级管理员";
			PtUser currentUser = getCurrentSysUser();
			if (currentUser != null)
				userCh = currentUser.getId();
			for(PtGateway gateway:list){
				gateway.setUpdateUser(userCh);
				gateway.setUpdateTime(new Date());
				gateway.setBaseParam(result);
				thisService.merge(gateway);
			}
			writeJSON(response, jsonBuilder.returnSuccessJson("'基础参数批量设置成功。'"));
	}
	
	/**
	 * 设置基础与高级参数
	 * @param tlvs
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("BASEGATEWAY_baseAndHigh")
	@RequestMapping("/doBaseAndHighParam")
	public void baseAndHighParam(TLVModel tlvs, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		
		PtUser currentUser = getCurrentSysUser();
	
		//1.判断，是否批量设置(0-不批量，1-选择批量，2-所有网关)
		String gatewayRadio=request.getParameter("gatewayRadio");
		if("1".equals(gatewayRadio)){
			String gatewayIds=request.getParameter("gatewayIds");
			thisService.doUpdateBaseHighParamToIds(tlvs, gatewayIds , currentUser.getId());
		}else if("2".equals(gatewayRadio)){
			thisService.doUpdateBaseHighParamToAll(tlvs, currentUser.getId());
		}else{	//默认为0，只设置当前自己
			thisService.doUpdateBaseHighParam(tlvs, currentUser.getId());
		}
		
		writeJSON(response, jsonBuilder.returnSuccessJson("\"设备参数设置成功！\""));

	}

	
	@RequestMapping("/baseParam_read")
	public void baseParam_read(TLVModel tlvs, HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		PtGateway perEntity = thisService.get(tlvs.getId());
		// 将entity中不为空的字段动态加入到perEntity中去。
		String strData ="";
		if(perEntity.getBaseParam()!=null){
			TLVUtils.decode(perEntity.getBaseParam(), tlvs.getTlvs());
			strData = JsonBuilder.getInstance().buildList(tlvs.getTlvs(), "");// 处理数据
		}
		writeJSON(response, strData);// 返回数据
	}
	
	@RequestMapping("/highParam_read")
	public void highParam_read(TLVModel tlvs, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		PtGateway perEntity = thisService.get(tlvs.getId());
		String strData ="";
		if(perEntity.getAdvParam()!=null){
			TLVUtils.decode(perEntity.getAdvParam(), tlvs.getTlvs());
			strData = JsonBuilder.getInstance().buildList(tlvs.getTlvs(), "");// 处理数据
		}
		writeJSON(response, strData);// 返回数据
	}

	@RequestMapping("/baseParam")
	public void baseParam(TLVModel tlvs, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String userCh = "超级管理员";
		PtUser currentUser = getCurrentSysUser();
		if (currentUser != null)
			userCh = currentUser.getId();
		PtGateway perEntity = thisService.get(tlvs.getId());
		// 将entity中不为空的字段动态加入到perEntity中去。
		perEntity.setUpdateUser(userCh);
		perEntity.setUpdateTime(new Date());
		byte[] result =null;
		result=TLVUtils.encode( tlvs.getTlvs());
		perEntity.setBaseParam(result);

		thisService.merge(perEntity);// 执行修改方法
		writeJSON(response, jsonBuilder.returnSuccessJson("'基础参数设置成功。'"));

	}
	
	@RequestMapping("/highParam")
	public void highParam(TLVModel tlvs, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		byte[] result = null;
		PtGateway perEntity = thisService.get(tlvs.getId());
		PtUser currentUser = getCurrentSysUser();
		String userCh = "超级管理员";
		if (currentUser != null)
			userCh = currentUser.getId();
		perEntity.setUpdateUser(userCh);
		perEntity.setUpdateTime(new Date());
		result=TLVUtils.encode(tlvs.getTlvs());
		perEntity.setAdvParam(result);
		thisService.merge(perEntity);// 执行修改方法
		writeJSON(response, jsonBuilder.returnSuccessJson("'高级参数设置成功。'"));

	}
	/*已弃用*/
	@Deprecated
	@RequestMapping("/batchGatewayParam")
	public void batchGatewayParam(TLVModel tlvs, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
			byte[] result=TLVUtils.encode(tlvs.getTlvs());
			List<PtGateway> list=thisService.queryAll();
			PtUser currentUser = getCurrentSysUser();
			String userCh = "超级管理员";
			if (currentUser != null)
				userCh = currentUser.getId();
			for(PtGateway gateway:list){
				gateway.setUpdateUser(userCh);
				gateway.setUpdateTime(new Date());
				gateway.setNetParam(result);
				thisService.merge(gateway);// 执行修改方法
			}
			writeJSON(response, jsonBuilder.returnSuccessJson("'网关参数批量设置成功。'"));
	}
	
	/**
	 * 设置网关的网络、服务器参数
	 * @param tlvs
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("BASEGATEWAY_gatewayParam")
	@RequestMapping("/doGatewayParam")
	public void gatewayParam(TLVModel tlvs, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		
		PtUser currentUser = getCurrentSysUser();				
		thisService.doSetGatewayParam(request,tlvs, currentUser.getId());	
		
		writeJSON(response, jsonBuilder.returnSuccessJson("\"网关参数设置成功！\""));
		
	}
	/**
	 * 获取转换后的网关参数
	 * 现在暂时不使用了，因为在实体中保存一份未转换的数据
	 * @param tlvs
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping("/gatewayParam_read")
	public void gatewayParam_read(TLVModel tlvs, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		PtGateway perEntity = thisService.get(tlvs.getId());
		String strData ="";
		if(perEntity.getAdvParam()!=null){
			TLVUtils.decode(perEntity.getNetParam(), tlvs.getTlvs());
			strData = JsonBuilder.getInstance().buildList(tlvs.getTlvs(), "");// 处理数据
		}
		writeJSON(response, strData);// 返回数据
	}
}