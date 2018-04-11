/**
 * Project Name:jw-service
 * File Name:BaseOrgService.java
 * Package Name:com.zd.school.base.service
 * Date:2016年5月18日上午11:27:25
 * Copyright (c) 2016 ZDKJ All Rights Reserved.
 *
*/

package com.yc.q1.base.pt.system.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.system.Department;
import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.pojo.base.pt.CommTree;
import com.yc.q1.pojo.base.pt.DepartmentTree;
import com.zd.core.service.BaseService;

/**
 * ClassName:BaseOrgService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON. Date: 2016年5月18日 上午11:27:25
 * 
 * @author luoyibo
 * @version
 * @since JDK 1.8
 * @see
 */
@Transactional
public interface DepartmentService extends BaseService<Department> {

    /**
     * 
     * getOrgTreeList:获取部门树形数据
     *
     * @author luoyibo
     * @param whereSql
     *            获取指定的查询条件
     * @param orderSql
     *            获取时指定的排序条件
     * @param currentUser
     *            当前操作用户对象 *
     * @return List<BaseOrgTree>
     * @throws @since
     *             JDK 1.8
     */
    public List<DepartmentTree> getOrgTreeList(String whereSql, String orderSql, User currentUser);

    /**
     * 
     * delOrg:删除指定的部门.
     *
     * @author luoyibo
     * @param delIds
     *            要删除的部门的标识
     * @param currentUser
     *            当前操作的用户对象
     * @return Boolean
     * @throws @since
     *             JDK 1.8
     */
    public String delOrg(String delIds, User currentUser);

    /**
     * 
     * addOrg:增加新部门.
     *
     * @author luoyibo
     * @param entity
     *            要增加的部门实体
     * @param currentUser
     *            当前操作的用户对象
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *             BaseOrg
     * @throws @since
     *             JDK 1.8
     */
    public Department addOrg(Department entity, User currentUser) throws IllegalAccessException, InvocationTargetException;

    /**
     * 
     * getOrgList:部门列表数据
     *
     * @author luoyibo
     * @param whereSql
     * @param orderSql
     * @param currentUser
     * @return List<BaseOrg>
     * @throws @since
     *             JDK 1.8
     */
    public List<Department> getOrgList(String whereSql, String orderSql, User currentUser);

    /**
     * 
     * getOrgAndChildList:得到指定部门及其子部门数据.
     *
     * @author luoyibo
     * @param deptId
     *            指定的部门ID
     * @param orderSql
     *            指定的排序条件
     * @param currentUser
     *            当前操作用户对象
     * @param isRight
     *            是否加上权限控制 true 表示加上
     * @return List<BaseOrg>
     * @throws @since
     *             JDK 1.8
     */
    public List<Department> getOrgAndChildList(String deptId, String orderSql, User currentUser, Boolean isRight);

    public Integer getChildCount(String deptId);

    /**
     * 
     * getOrgChkTreeList:查询带checkbox框的部门树形数据
     *
     * @author luoyibo
     * @param whereSql
     * @param orderSql
     * @param currentUser
     * @return List<BaseOrgChkTree>
     * @throws @since
     *             JDK 1.8
     */
    public List<DepartmentTree> getOrgTreeList(String whereSql, String orderSql, String deptId, User currentUser);

    /**
     * 
     * getOrgChildIds:获取指定部门的所有子部门的ID的拼接串.
     *
     * @author luoyibo
     * @param orgId
     *            指定的部门的ID
     * @param isSelf
     *            返回字符串是否包含本部门 为true则包含，或者不包含，默认为true
     * @return String
     * @throws @since
     *             JDK 1.8
     */
    public String getOrgChildIds(String orgId, boolean isSelf);

    /**
     * 
     * getOrgChildMaps:获取指定部门的所有子部门的Map
     *
     * @author luoyibo
     * @param OrgId
     * @param isSelf
     * @return Map<String,BaseOrg>
     * @throws @since
     *             JDK 1.8
     */
    public Map<String, Department> getOrgChildMaps(String OrgId, boolean isSelf);

	public Department doUpdate(Department entity, String xm);

	public Integer getDeptJobCount(String uuid);

	public void setDeptName(String deptName, String uuid);

	public void setChildAllDeptName(Department dept, String parentAllDeptName);

	//新版本不需要这个副IDpublic void doCreateFuId();

	

	/**
	 * 获取指定用户有权限的部门列表
	 * 
	 * @param currentUser
	 *            指定的用户对象
	 * @return
	 */
	public List<Department> getUserRightDeptList(User currentUser);

	public DepartmentTree getUserRightDeptTree(User currentUser, String node);
	
	/**
	 * 用户有权限的部门列表
	 * @param currentUser
	 * @return
	 */
	public List<DepartmentTree> getUserRightDeptTreeList(User currentUser);
	/**
	 * 用户有权限的班级列表
	 * @param currentUser
	 * @return
	 */
	public List<CommTree> getUserRightDeptClassTreeList(User currentUser);
	/**
	 * 用户有权限的学科列表
	 * @param currentUser
	 * @return
	 */
	public List<CommTree> getUserRightDeptDisciplineTreeList(User currentUser);
	
}
