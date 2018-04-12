
package com.yc.q1.controller.basedevice;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.device.PtTerm;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.sk.SkMeterBind;
import com.yc.q1.service.base.pt.device.PtTermService;
import com.yc.q1.service.base.sk.SkMeterBindService;

/**
 * 水控流量记表绑定
 * @author hucy
 *
 */
@Controller
@RequestMapping("/SkMeterBind")
public class SkMeterBindController extends FrameWorkController<SkMeterBind> implements Constant {

	@Resource
	SkMeterBindService thisService; // service层接口
	@Resource
	PtTermService ptTermService; // service层接口
	/**
	 * list查询 @Title: list @Description: TODO @param @param entity
	 * 实体类 @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(@ModelAttribute SkMeterBind entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<SkMeterBind> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	@Auth("WATER_METER_meterBingTerm")
	@RequestMapping(value = { "/meterBingTermlist" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void meterBingTermlist(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<SkMeterBind> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);
		if(qr.getTotalCount()==0){
			writeJSON(response, strData);// 返回数据	
			return;
		}
		
		StringBuffer termId = new StringBuffer();
		for(SkMeterBind ptSkMeterbind:qr.getResultList()){
			termId.append(ptSkMeterbind.getTermId()+",");
		}
		String filter = "[{\"type\":\"string\",\"comparison\":\"in\",\"value\":\"" + termId.substring(0, termId.length() - 1)
			+ "\",\"field\":\"id\"}]";
		//String sor="[{\"property\":\"orderIndex\",\"direction\":\"DESC\"}]";
		QueryResult<PtTerm> termQr = ptTermService.queryPageResult(0,0,null, filter, true);
		
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), termQr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	/**
	 * 
	 * @Title: 增加新实体信息至数据库 @Description: TODO @param @param ContPerimisson
	 *         实体类 @param @param request @param @param response @param @throws
	 *         IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping("/doAdd")
	public void doAdd(String[] termId, String[] termSn, String meterId, HttpServletRequest request,
			HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {
		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();

		thisService.doMeterBind(termId, termSn, meterId, currentUser.getId());

		writeJSON(response, jsonBuilder.returnSuccessJson("'成功'"));
	 }

	/**
	 * doDelete @Title: 逻辑删除指定的数据 @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@RequestMapping("/dodelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE,currentUser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("'删除成功'"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("'删除失败'"));
			}
		}
	}
	@RequestMapping("/doPtTermDelete")
	public void doPtTermDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String termIds = request.getParameter("termIds");
		if (StringUtils.isEmpty(termIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
			return;
		} else {
			String[] ids =termIds.split(",");
			for(int i=0;i<ids.length;i++){
				 String hql = " from SkMeterBind where termId = '"+ ids[i]+"'";
				 SkMeterBind entity = thisService.getEntityByHql(hql);
				 thisService.delete(entity);
			}
		}
		writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
	}
	/**
	 * doRestore还原删除的记录 @Title: doRestore @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@RequestMapping("/dorestore")
	public void doRestore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入还原主键'"));
			return;
		} else {
			PtUser currentUser = getCurrentSysUser();
			boolean flag = thisService.doLogicDelOrRestore(delIds, StatuVeriable.ISNOTDELETE,currentUser.getId());
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("'还原成功'"));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("'还原失败'"));
			}
		}
	}

	/**
	 * doUpdate编辑记录 @Title: doUpdate @Description: TODO @param @param
	 * ContPerimisson @param @param request @param @param
	 * response @param @throws IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping("/doupdate")
	public void doUpdates(SkMeterBind entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 入库前检查代码

		// 获取当前的操作用户
		String userCh = "超级管理员";
		PtUser currentUser = getCurrentSysUser();
		if (currentUser != null)
			userCh = currentUser.getId();

		// 先拿到已持久化的实体
		// entity.getSchoolId()要自己修改成对应的获取主键的方法
		SkMeterBind perEntity = thisService.get(entity.getId());

		// 将entity中不为空的字段动态加入到perEntity中去。
		BeanUtils.copyPropertiesExceptNull(perEntity, entity);

		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(userCh); // 设置修改人的中文名
		entity = thisService.merge(perEntity);// 执行修改方法

		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(perEntity)));

	}
}
