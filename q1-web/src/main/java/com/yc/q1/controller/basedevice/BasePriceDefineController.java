package com.yc.q1.controller.basedevice;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.dk.DkPriceDefine;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.sk.SkPriceDefine;
import com.yc.q1.service.base.dk.DkPriceDefineService;
import com.yc.q1.service.base.sk.SkPriceDefineService;

/**
 * 费率定义(水控与电控)
 *
 */
@Controller
@RequestMapping("/BasePriceDefine")
public class BasePriceDefineController extends FrameWorkController<BaseEntity> implements Constant  {
	@Resource
	SkPriceDefineService skPriceDefineService; // service层接口
	@Resource
	DkPriceDefineService dkPriceDefineService; // service层接口
	
	/**
	 * list查询 @Title: list @Description: TODO 
	 * @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		//获取类型
		String categroy = request.getParameter("categroy");
		if(categroy!=null){
			//为水控时
			if(categroy.equals("0")){
				QueryResult<SkPriceDefine> qr = skPriceDefineService.queryPageResult(super.start(request), super.limit(request),
						super.sort(request), super.filter(request), true);
				strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
			}
			
			//为电控时
			if(categroy.equals("1")){
				QueryResult<DkPriceDefine> qr = dkPriceDefineService.queryPageResult(super.start(request), super.limit(request),
						super.sort(request), super.filter(request), true);
				strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
			}	
		}
		
		writeJSON(response, strData);// 返回数据
	}
	
	/**
	 * 增加
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Auth("BASERATE_add")
	@RequestMapping("/doAdd")
    public void doAdd(SkPriceDefine entity,DkPriceDefine entitys,HttpServletRequest request, HttpServletResponse response)
            throws IOException, IllegalAccessException, InvocationTargetException {
		String categroy = request.getParameter("categroy");
		 // 获取当前操作用户
        PtUser currentUser = getCurrentSysUser();
        
		if(categroy.equals("0")){		
			String hql1 = " o.isDelete='0' ";
			// 此处为放在入库前的一些检查的代码，如唯一校验等
			if (skPriceDefineService.IsFieldExist("priceName", entity.getPriceName(), "-1", hql1)) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"水控费率名称不能重复！\""));
				return;
			}
			
			entity = skPriceDefineService.doAddEntity(entity, currentUser);// 执行增加方法
			if (ModelUtil.isNotNull(entity))
	            writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	        else
	            writeJSON(response, jsonBuilder.returnFailureJson("\"数据增加失败,详情见错误日志\""));    
		}
		if(categroy.equals("1")){
			String hql1 = " o.isDelete='0' ";
			// 此处为放在入库前的一些检查的代码，如唯一校验等
			if (dkPriceDefineService.IsFieldExist("priceName", entitys.getPriceName(), "-1", hql1)) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"电控费率名称不能重复！\""));
				return;
			}
			
			entitys = dkPriceDefineService.doAddEntity(entitys, currentUser);// 执行增加方法
			if (ModelUtil.isNotNull(entitys))
	            writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	        else
	            writeJSON(response, jsonBuilder.returnFailureJson("\"数据增加失败,详情见错误日志\"")); 
		}
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
	@Auth("BASERATE_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(SkPriceDefine entity,DkPriceDefine entitys, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String categroy = request.getParameter("categroy");
		
		PtUser currentUser = getCurrentSysUser();
		if(categroy.equals("0")){
			String hql1 = " o.isDelete='0' ";
			// 此处为放在入库前的一些检查的代码，如唯一校验等
			if (skPriceDefineService.IsFieldExist("priceName", entity.getPriceName(),entity.getId(), hql1)) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"水控费率名称不能重复！\""));
				return;
			}
			
	        entity = skPriceDefineService.doUpdateEntity(entity, currentUser);// 执行修改方法
	        if (ModelUtil.isNotNull(entity))
	            writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	        else
	            writeJSON(response, jsonBuilder.returnFailureJson("\"数据修改失败,详情见错误日志\""));
		}
		if(categroy.equals("1")){
			String hql1 = " o.isDelete='0' ";
			// 此处为放在入库前的一些检查的代码，如唯一校验等
			if (dkPriceDefineService.IsFieldExist("priceName", entitys.getPriceName(), entitys.getId(), hql1)) {
				writeJSON(response, jsonBuilder.returnFailureJson("\"电控费率名称不能重复！\""));
				return;
			}
			
	        entitys = dkPriceDefineService.doUpdateEntity(entitys, currentUser);// 执行修改方法
	        if (ModelUtil.isNotNull(entitys))
	            writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	        else
	            writeJSON(response, jsonBuilder.returnFailureJson("\"数据修改失败,详情见错误日志\""));
			}
    }
	
	/**
	 * doDelete @Title: 逻辑删除指定的数据 @Description: TODO @param @param
	 * request @param @param response @param @throws IOException 设定参数 @return
	 * void 返回类型 @throws
	 */
	@Auth("BASERATE_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids=request.getParameter("ids");
		String categroy = request.getParameter("categroy");
		if (StringUtils.isEmpty(ids)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("'没有传入删除主键'"));
			return;
		} else {
			
			// 判断这些费率是否正在被其他设备所绑定
			String hql = "select count(a.id) from PtPriceBind as a where a.priceId in ('" + ids.replace(",", "','")
					+ "') and a.isDelete=0";
					
			PtUser currentUser = getCurrentSysUser();
			boolean flag =false;
			if(categroy.equals("水控")){
				int count = skPriceDefineService.getQueryCountByHql(hql);
				if (count > 0) {
					writeJSON(response, jsonBuilder.returnFailureJson("\"这些费率参数当前绑定了设备，请解除绑定后再删除！\""));
					return;
				}			
				flag = skPriceDefineService.doLogicDelOrRestore(ids, StatuVeriable.ISDELETE,currentUser.getId());
			}
			
			if(categroy.equals("电控")){			
				int count = dkPriceDefineService.getQueryCountByHql(hql);
				if (count > 0) {
					writeJSON(response, jsonBuilder.returnFailureJson("\"这些费率参数当前绑定了设备，请解除绑定后再删除！\""));
					return;
				}		
				flag = dkPriceDefineService.doLogicDelOrRestore(ids, StatuVeriable.ISDELETE,currentUser.getId());
			}
			if (flag) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			} else {
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		}
	}
}
