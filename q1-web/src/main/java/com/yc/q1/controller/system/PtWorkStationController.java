package com.yc.q1.controller.system;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

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
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.system.PtWorkStation;
import com.yc.q1.model.base.pt.system.PtRole;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserWorkStationBind;
import com.yc.q1.service.base.pt.system.PtUserWorkStationBindService;
import com.yc.q1.service.base.pt.system.PtWorkStationService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 岗位管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PtWorkStation")
public class PtWorkStationController extends FrameWorkController<PtWorkStation> implements Constant {

	@Resource
	PtWorkStationService thisService; // service层接口
	
	@Resource
	PtUserWorkStationBindService ptUserWorkStationBindService; // service层接口
	
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
	public void list(PtWorkStation entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<PtWorkStation> qr = thisService.queryPageResult(super.start(request), super.limit(request),
				super.sort(request), super.filter(request), true);

		strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
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
	@Auth("WORKSTATION_add")
	@RequestMapping("/doAdd")
	public void doAdd(PtWorkStation entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();
		entity.setId(keyRedisService.getId(PtWorkStation.ModuleType));
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
	@Auth("WORKSTATION_delete")
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
	@Auth("WORKSTATION_update")
	@RequestMapping("/doUpdate")
	public void doUpdates(PtWorkStation entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String userCh = getCurrentSysUser().getId();
		PtWorkStation resultEntity = thisService.doUpdateEntity(entity, userCh, null);
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(resultEntity)));
	}
	
	
	/**
     * 获取用户未分配的角色列表
     * @param entity
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = { "/selectList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
            org.springframework.web.bind.annotation.RequestMethod.POST })
    public void selectList(@ModelAttribute PtRole entity, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String strData = ""; // 返回给js的数据
        String userId = request.getParameter("userId");
        int start = super.start(request); // 起始记录数
        int limit = super.limit(request);// 每页记录数
        String sort = StringUtils.convertSortToSql(super.sort(request));
        String filter = StringUtils.convertFilterToSql(super.filter(request));
        
        String unBindStationHql = "from PtUserWorkStationBind o where o.isDelete=0 ";
        List<PtUserWorkStationBind> unBindStation = ptUserWorkStationBindService.queryByHql(unBindStationHql);
        // hql语句
        StringBuffer hql = new StringBuffer("from PtWorkStation o where o.isDelete=0 ");           
        if (unBindStation.size() > 0) {
        	StringBuilder sb = new StringBuilder();
            for (int i=0 ;i<unBindStation.size();i++) {
                sb.append(unBindStation.get(i).getWorkStationId());
                sb.append(",");
            }
            sb = sb.deleteCharAt(sb.length()-1);
            String str = sb.toString().replace(",", "','");
            hql.append(" and o.id not in('" + str + "')");
        }
    
        //countHql.append("and e not in(:roles)");            
        if(StringUtils.isNotEmpty(filter)){
            hql.append(filter);
        }
        if(StringUtils.isNotEmpty(sort)){
            hql.append(" order by ");
            hql.append( sort);
        }
        
        QueryResult<PtWorkStation> qr = thisService.queryResult(hql.toString(), start, limit);
                  
        strData = jsonBuilder.buildObjListToJson(qr.getTotalCount(), qr.getResultList(), true);// 处理数据
        writeJSON(response, strData);// 返回数据        
    }
	

}
