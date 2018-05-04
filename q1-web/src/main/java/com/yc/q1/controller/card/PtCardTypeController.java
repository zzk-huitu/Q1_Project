
package com.yc.q1.controller.card;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.BaseController;
import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.core.util.FileOperateUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.basic.PtAttachment;
import com.yc.q1.model.base.pt.basic.PtBaseCourse;
import com.yc.q1.model.base.pt.card.PtCardType;
import com.yc.q1.model.base.pt.system.PtSysParameter;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.basic.PtAttachmentService;
import com.yc.q1.service.base.pt.card.PtCardTypeService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BaseTAttachmentController Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 公共附件信息表实体Controller. date: 2016-07-06
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/PtCardType")
public class PtCardTypeController extends FrameWorkController<PtCardType>{
	
	@Resource
	PtCardTypeService thisService; // service层接口
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;
	/**
	 * 标准的查询列表功能
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void list(PtCardType entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<PtCardType> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), false);
		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	
	@RequestMapping(value = { "/cardTypeList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void cardTypeList(PtCardType entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		String sql = " select cardTypeId,cardTypeName from PtCardType";
		List<PtCardType> list = thisService.querySql(sql);
        strData = jsonBuilder.buildObjListToJson(Long.valueOf(list.size()), list, true);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	/**
	 * 
	 * doAdd @Title: doAdd @Description: TODO @param @param BizTJob
	 * 实体类 @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Auth("CARDTYPEINFO_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtCardType entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		// String courseCode = entity.getCourseCode();
		String cardTypeName = entity.getCardTypeName();
		// 此处为放在入库前的一些检查的代码，如唯一校验等
		String sql = "SELECT cardTypeId FROM T_PT_CardType WHERE cardTypeName='"+cardTypeName+"'";
		Integer count = thisService.getQueryCountBySql(sql);
		if (count!=0) {
			writeJSON(response, jsonBuilder.returnFailureJson("\"卡类名称不能重复！\""));
			return;
		}
		String sql1 = "SELECT MAX(cardTypeId) FROM T_PT_CardType";
		Short maxCount = thisService.getEntityBySql(sql1);
		if(null!=maxCount&&maxCount==64){
			writeJSON(response, jsonBuilder.returnFailureJson("\"超出最大卡类！\""));
			return;
		}
		PtCardType resultEntity = thisService.doAddPtCardType(entity);
		// 返回实体到前端界面
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(resultEntity)));
	}
	
	/**
	 * doUpdate编辑记录 @Title: doUpdate @Description: TODO @param @param
	 * BizTJob @param @param request @param @param response @param @throws
	 * IOException 设定参数 @return void 返回类型 @throws
	 */
	@Auth("CARDTYPEINFO_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(PtCardType entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String userCh = getCurrentSysUser().getId();
		PtCardType resultEntity = thisService.doUpdatePtCardType(entity);
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(resultEntity)));
	}
	
}
