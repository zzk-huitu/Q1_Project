
package com.zd.school.plartform.system.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.base.pt.system.service.DepartmentService;
import com.yc.q1.model.base.pt.system.Department;
import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.pojo.base.pt.DepartmentTree;
import com.zd.core.annotation.Auth;
import com.zd.core.constant.Constant;
import com.zd.core.constant.TreeVeriable;
import com.zd.core.controller.core.FrameWorkController;
import com.zd.core.util.DBContextHolder;
import com.zd.core.util.EntityUtil;
import com.zd.core.util.JsonBuilder;
import com.zd.core.util.StringUtils;

/**
 * 部门管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/SysOrg")
public class SysOrgController extends FrameWorkController<Department> implements Constant {

	@Resource
	private DepartmentService thisService; // service层接口

    /**
     * 获取所有部门信息
     * @param request
     * @param response
     * @throws IOException
     */
	@RequestMapping("/treeList")
	public void getOrgTreeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = "";
		String whereSql = request.getParameter("whereSql");
		String orderSql = request.getParameter("orderSql");
        String excludes = super.excludes(request);

		User currentUser = getCurrentSysUser();
		List<DepartmentTree> lists = thisService.getOrgTreeList(whereSql, orderSql, currentUser);

		strData = JsonBuilder.getInstance().buildList(lists, excludes);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	
	/**
	 * 获取带有选择框的部门树（★见getUserRightDeptTree方法，通过参数来判断是否显示复选框）
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/chkTreeList")
	public void getOrgChkTreeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String strData = "";
		String whereSql = request.getParameter("whereSql");
		String orderSql = request.getParameter("orderSql");
		String deptId = request.getParameter("deptId")== null ?TreeVeriable.ROOT: request.getParameter("deptId");
		String excludes = super.excludes(request);

		User currentUser = getCurrentSysUser();
		List<DepartmentTree> lists = thisService.getOrgTreeList(whereSql, orderSql,deptId, currentUser);

		strData = JsonBuilder.getInstance().buildList(lists, excludes);// 处理数据
		writeJSON(response, strData);// 返回数据
	}
	
	/**
	 * 根据用户的权限，来显示具备权限的树
	 * 注：将每个人的权限树的值，存入到redis中；在修改部门权限或指定部门岗位时删除redis数据。
	 * 通过excludes参数，来排除不需要的参数，如checked复选框参数
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getUserRightDeptTree" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void getUserRightDeptTree( HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String strData = "";
		// 得到根节点ID
		String node = request.getParameter("node");	//一般传 ROOT
		String nodeId = request.getParameter("nodeId");
		String excludes = request.getParameter("excludes");	//在结果集中排除某个字段，比如checked复选框字段
		if (!(StringUtils.isNotEmpty(node) && node.equalsIgnoreCase(TreeVeriable.ROOT))) {
			node = TreeVeriable.ROOT;
		}
		if (StringUtils.isNotEmpty(nodeId)) {
			node = nodeId;
		}
		
		User currentUser = getCurrentSysUser();
		DepartmentTree root = thisService.getUserRightDeptTree(currentUser, node);
		if (node.equalsIgnoreCase(TreeVeriable.ROOT)) {
			strData = jsonBuilder.buildList(root.getChildren(), excludes);
		} else {
			List<DepartmentTree> alist = new ArrayList<DepartmentTree>();
			alist.add(root);
			strData = jsonBuilder.buildList(root.getChildren(), excludes);
		}
		writeJSON(response, strData);// 返回数据
	}
	
	/**
	 * 获取用户有权限的部门id
	 */
	@RequestMapping(value = { "/getUserRightDeptIds" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public void getUserRightDeptIds( HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		User currentUser=getCurrentSysUser();
		
		List<DepartmentTree> baseOrgList = thisService.getUserRightDeptTreeList(currentUser);
		String deptIds = baseOrgList.stream().filter((x) -> x.getIsRight().equals("1"))
				.map((x) -> x.getId()).collect(Collectors.joining("','"));					
		
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(deptIds)));	// 返回数据
	}
	
	
    /**
     * 添加部门
     * @param entity
     * @param request
     * @param response
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
	@Auth("DEPARTMENT_add")
	@RequestMapping("/doAdd")
	public void doAdd(Department entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {
		String parentNode = entity.getParentNode();
		String parentName = entity.getParentName();
		String parentType = entity.getParentType();
		String nodeText = entity.getNodeText();
		//Integer orderIndex = entity.getOrderIndex();
		Integer defaultOrderIndex = Integer.valueOf(0);

		// 此处为放在入库前的一些检查的代码，如唯一校验等
		String hql1 = " o.isDelete='0' and o.parentNode='" + parentNode + "' ";
/*		if (thisService.IsFieldExist("orderIndex", orderIndex.toString(), "-1", hql1)) {
			writeJSON(response, jsonBuilder.returnFailureJson("'同一级别已有此顺序号！'"));
			return;
		}*/
		if (thisService.IsFieldExist("nodeText", nodeText, "-1", hql1)) {
			writeJSON(response, jsonBuilder.returnFailureJson("'同一级别已有此部门！'"));
			return;
		}
		//获取同一级别的顺序号
		String hql = " from Department where orderIndex = (select max(o.orderIndex) from Department o where  o.isDelete='0' and o.parentNode='" + parentNode + "' )";
		List list = thisService.queryByHql(hql);
		if (list.size() > 0) {
			defaultOrderIndex = (Integer) EntityUtil.getPropertyValue(list.get(0), "orderIndex") + 1;
		} else
			defaultOrderIndex = 0;
		entity.setOrderIndex(defaultOrderIndex);
		
		User sysuser = getCurrentSysUser();
		
		entity = thisService.addOrg(entity, sysuser);
		entity.setParentName(parentName);
		entity.setParentNode(parentNode);
		entity.setParentType(parentType);
		
		// 返回的是实体前端界面
		writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
	}

    /**
     * 删除部门
     * @param request
     * @param response
     * @throws IOException
     */
	@Auth("DEPARTMENT_delete")
	@RequestMapping("/doDelete")
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String deptId = request.getParameter("ids");
		if (StringUtils.isEmpty(deptId)) {
			writeJSON(response, JsonBuilder.getInstance().returnSuccessJson("\"没有传入删除主键\""));
			return;
		}
		User currentUser = getCurrentSysUser();
		String flag = thisService.delOrg(deptId, currentUser);
		if ("1".equals(flag)) {
			writeJSON(response, JsonBuilder.getInstance().returnSuccessJson("\"删除成功\""));
		} else {
			writeJSON(response, JsonBuilder.getInstance().returnFailureJson("\""+flag+"\""));
		}
	}

	/**
	 * 
	 * doUpdates:修改部门信息.
	 *
	 * @author luoyibo
	 * @param entity
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             void
	 * @throws @since
	 *             JDK 1.8
	 */
	@Auth("DEPARTMENT_update")
	@RequestMapping("/doUpdate")
	public void doUpdate(Department entity, HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalAccessException, InvocationTargetException {

		String parentNode = entity.getParentNode();
		String parentName = entity.getParentName();
		String nodeText = entity.getNodeText();
		String uuid = entity.getId();
		Integer orderIndex = entity.getOrderIndex();
	//	Integer defaultOrderIndex = Integer.valueOf(0);

		// 此处为放在入库前的一些检查的代码，如唯一校验等
		String hql1 = " o.isDelete='0' and o.parentNode='" + parentNode + "' ";
		if (thisService.IsFieldExist("orderIndex", orderIndex.toString(), uuid, hql1)) {
			writeJSON(response, jsonBuilder.returnFailureJson("'同一级别已有此顺序号！'"));
			return;
		}
		if (thisService.IsFieldExist("nodeText", nodeText, uuid, hql1)) {
			writeJSON(response, jsonBuilder.returnFailureJson("'同一级别已有此部门！'"));
			return;
		}
/*		BaseOrg baseOrg = thisService.get(uuid);
		if(!baseOrg.getParentNode().equals(parentNode)){//编辑时改变了它的上级部门，需要重新设置orderIndex
			// 获取同一级别的最大的顺序号
			String hql = " from BaseOrg where orderIndex = (select max(o.orderIndex) from BaseOrg o where  o.isDelete='0' and o.parentNode='"
					+ parentNode + "' )";
			List list = thisService.queryByHql(hql);
			if (list.size() > 0) {
				defaultOrderIndex = (Integer) EntityUtil.getPropertyValue(list.get(0), "orderIndex") + 1;
			} else
				defaultOrderIndex = 0;
			entity.setOrderIndex(defaultOrderIndex);
		}else{
			entity.setOrderIndex(baseOrg.getOrderIndex());
		}
*/
		User currentUser = getCurrentSysUser();

		entity = thisService.doUpdate(entity, currentUser.getId());		
		
		
		if (entity == null){
			writeJSON(response, jsonBuilder.returnFailureJson("\"修改失败，请重试或联系管理员！\""));
		}else{
			//重新设定显示的数据
			entity.setParentName(parentName);
			entity.setParentNode(parentNode);
			writeJSON(response, jsonBuilder.returnSuccessJson(jsonBuilder.toJson(entity)));
		}	
	}
	

}
