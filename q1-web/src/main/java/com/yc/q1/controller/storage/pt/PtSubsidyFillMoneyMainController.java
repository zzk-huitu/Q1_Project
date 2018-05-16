package com.yc.q1.controller.storage.pt;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.annotation.Auth;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.util.DBContextHolder;
import com.yc.q1.core.util.DateUtil;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.storage.pt.PtSubsidyFillMoneyMain;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.pt.PtSubsidyFillMoneyMainService;
/**
 * 在线补助表
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PtSubsidyFillMoneyMain")
public class PtSubsidyFillMoneyMainController  extends FrameWorkController<PtSubsidyFillMoneyMain> implements Constant{
	@Resource
	PtSubsidyFillMoneyMainService thisService; // service层接口

	@Resource
	private PrimaryKeyRedisService keyRedisService;
	@Resource
	PtUserService userService; // service层接口
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
	public void list(PtSubsidyFillMoneyMain entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String strData = ""; // 返回给js的数据
		QueryResult<PtSubsidyFillMoneyMain> qr = thisService.queryPageResult(super.start(request), super.limit(request),
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
	@Auth("")
	@RequestMapping("/doAdd")
	public void doAdd(PtSubsidyFillMoneyMain entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		// 获取当前操作用户
		PtUser currentUser = getCurrentSysUser();
	    String allowRepeat = request.getParameter("allowRepeat");
		String userIds = request.getParameter("userIds");
		DBContextHolder.setDBType(DBContextHolder.DATA_SOURCE_Storage);
		if (allowRepeat.equals("true")) { // 当月允许重复时 ，均可以充值
			entity = thisService.doAddEntity(entity,currentUser,userIds);
		
		} else {
			// 判断PtSubsidyFillMoneyMain 是否已经存在该用户的该月的补助信息
			String sql = "select b.userId from T_PT_SubsidyFillMoneyMain a left join  T_PT_SubsidyFillMoneyItem b on a.mainId = b.mainId where b.userId in ('"
					+ userIds.replace(",", "','") + "') and a.fillDate = '" + DateUtil.formatDate(entity.getFillDate()) + "'";
		    List<String> userIdList  = thisService.getDao().queryEntityBySql(sql);
			if (userIdList.size() > 0) {
				for (int i = 0; i < userIdList.size(); i++) {
					String userSql = " select userNumb,name from Q1_Base.dbo.T_PT_User where userId ='"+userIdList.get(i)+"' ";
					Object[] object = userService.getEntityBySql(userSql);
					writeJSON(response, jsonBuilder.returnFailureJson("\"['" +  object[0] + "'-'"
							+ object[1] + "']在['" + DateUtil.formatDate(entity.getFillDate()) + "']已有设置充值\""));
				
					return ;
					}
			
			} else {// 如果该用户该月没有充值 ，则可以充值
				entity = thisService.doAddEntity(entity,currentUser,userIds);
			}
		}
		DBContextHolder.clearDBType();
		if (entity == null)
			writeJSON(response, jsonBuilder.returnFailureJson("\"添加失败，请重试或联系管理员！\""));
		else
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	}
	/**
	 * @Title: doUpdate
	 * @Description: 编辑指定记录
	 * @param PtAttendTerm
	 * @param request
	 * @param response
	 * @return void 返回类型
	 * @throws IOException
	 *             抛出异常
	 */
	@RequestMapping("/doUpdate")
	public void doUpdates(PtSubsidyFillMoneyMain entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		// 入库前检查代码
		String userIds = request.getParameter("userIds");
		String allowRepeat = request.getParameter("allowRepeat");
		// 获取当前的操作用户
		PtUser currentUser = getCurrentSysUser();
		DBContextHolder.setDBType(DBContextHolder.DATA_SOURCE_Storage);
		try {
			if (allowRepeat.equals("true")) { // 当月允许重复时 ，均可以充值
				entity = thisService.doUpdateEntity(entity,currentUser,userIds);
			} else {
				// 判断PtSubsidyFillMoneyMain 是否已经存在该用户的该月的补助信息
				String sql = "select b.userId from T_PT_SubsidyFillMoneyMain a left join  T_PT_SubsidyFillMoneyItem b on a.mainId = b.mainId where b.userId in ('"
						+ userIds.replace(",", "','") + "') and a.fillDate = '" + DateUtil.formatDate(entity.getFillDate()) + "'";
			    List<String> userIdList  = thisService.getDao().queryEntityBySql(sql);
				if (userIdList.size() > 0) {
					for (int i = 0; i < userIdList.size(); i++) {
						String userSql = " select userNumb,name from Q1_Base.dbo.T_PT_User where userId ='"+userIdList.get(i)+"' ";
						Object[] object = userService.getEntityBySql(userSql);
						writeJSON(response, jsonBuilder.returnFailureJson("\"['" +  object[0] + "'-'"
								+ object[1] + "']在['" +DateUtil.formatDate(entity.getFillDate()) + "']已有设置充值\""));
						}
				} else {// 如果该用户该月没有充值 ，则可以充值
					entity = thisService.doUpdateEntity(entity,currentUser,userIds);
				}
			}
			DBContextHolder.clearDBType();
		    if (ModelUtil.isNotNull(entity))
				writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
			else
				writeJSON(response, jsonBuilder.returnFailureJson("'数据修改失败,详情见错误日志'"));
		} catch (Exception e) {
			writeJSON(response, jsonBuilder.returnFailureJson("'数据修改失败,详情见错误日志'"));
		}
	}
	@Auth("")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String delIds = request.getParameter("ids");
		DBContextHolder.setDBType(DBContextHolder.DATA_SOURCE_Storage);
		if (StringUtils.isEmpty(delIds)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入删除主键\""));
			return;
		} else {
			String  hql1 = " delete from PtSubsidyFillMoneyItem where mainId in ('"+delIds.replace(",", "','")+"') ";
            String  hql2 = " delete from PtSubsidyFillMoneyMain where id in ('"+delIds.replace(",", "','")+"') ";
            Integer count1 =thisService.doExecuteCountByHql(hql1);
            Integer count2 =thisService.doExecuteCountByHql(hql2);
            DBContextHolder.clearDBType();
            if (count1 > 0 && count2 > 0) {
				writeJSON(response, jsonBuilder.returnSuccessJson("\"删除成功\""));
			}else{			
				writeJSON(response, jsonBuilder.returnFailureJson("\"删除失败\""));
			}
		}
	}
	@Auth("")
	@RequestMapping("/doAudit")
	public void doAudit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		String auditUser = request.getParameter("auditUser");
		String[] mainIds = ids.split(",");
		Date date = new Date();
		DBContextHolder.setDBType(DBContextHolder.DATA_SOURCE_Storage);
		if (StringUtils.isEmpty(ids)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入审核主键\""));
			return;
		} else {
			for (int i = 0; i < mainIds.length; i++) {
				String hql1 = " update  PtSubsidyFillMoneyItem set isFill=1,getFillDate='"+DateUtil.formatDate(date)+"' where mainId = '" + mainIds[i]
						+ "' ";
				String hql2 = " update  PtSubsidyFillMoneyMain set isAudit = 1,isFill = 1,auditUser = '"+auditUser+"',auditDate = '"+DateUtil.formatDateTime(date)+"'"
						 +" where id = '" + mainIds[i] + "'";
				Integer count1 = thisService.doExecuteCountByHql(hql1);
				Integer count2 = thisService.doExecuteCountByHql(hql2);
			}
			DBContextHolder.clearDBType();
			writeJSON(response, jsonBuilder.returnSuccessJson("\"审核成功\""));
		
		}
	}
	@Auth("")
	@RequestMapping("/getFillMoneyUsers")
	public void getFillMoneyUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mainId = request.getParameter("mainId");
		List<PtUser> list = new ArrayList<>();
		PtUser user = new PtUser();
		String strData="";
	    DBContextHolder.setDBType(DBContextHolder.DATA_SOURCE_Storage);
		if (StringUtils.isEmpty(mainId)) {
			writeJSON(response, jsonBuilder.returnSuccessJson("\"没有传入主键\""));
			return;
		} else {
			String sql = "select b.userId from T_PT_SubsidyFillMoneyMain a left join  T_PT_SubsidyFillMoneyItem b on a.mainId = b.mainId ";
			List<String> userIdList = thisService.getDao().queryEntityBySql(sql);
			if (userIdList.size() > 0) {
				for (int i = 0; i < userIdList.size(); i++) {
					String userSql = " select a.userId,a.userNumb,a.name,b.cardNo,d.deptName from Q1_Base.dbo.T_PT_User a left join  Q1_Base.dbo.T_PT_Card b on a.userId = b.userId "
							+ "  left join Q1_Base.dbo.T_PT_UseDeptJob c on a.userId = c.userId and c.isDelete=0 and  c.isMainDept=1"
							+ "  left join Q1_Base.dbo.T_PT_DeptJob d on c.deptJobId = d.deptJobId "
							+ "  where a.userId ='"+userIdList.get(i)+"' ";
					Object[] object = userService.getEntityBySql(userSql);
					user = new PtUser();
					user.setId(object[0].toString());
					user.setUserNumb(object[1].toString());
					user.setName(object[2].toString());
			        user.setCardId((String) object[3]==null?0L:Long.valueOf(object[3].toString()));
					user.setDeptName(object[4].toString());
					list.add(user);
					}
			  }
			
			DBContextHolder.clearDBType();
			strData = jsonBuilder.buildObjListToJson(Long.valueOf(list.size()), list, true);// 处理数据
			writeJSON(response, strData);// 返回数据
	      
		}
		
	}
}
