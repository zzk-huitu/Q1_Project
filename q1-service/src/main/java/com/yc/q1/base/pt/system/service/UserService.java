package com.yc.q1.base.pt.system.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.shiro.session.Session;

import com.yc.q1.base.pt.system.model.Department;
import com.yc.q1.base.pt.system.model.Permission;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.model.ImportNotInfo;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: BaseTUserService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 用户管理实体Service接口类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface UserService extends BaseService<User> {

    /**
     * 
     * doAddUser:增加新用户
     * 
     * @author luoyibo
     * @param entity
     * @param currentUser
     * @return
     * @throws Exception
     * @throws InvocationTargetException
     *             SysUser
     * @throws @since
     *             JDK 1.8
     */
    public User doAddUser(User entity, User currentUser/*, String deptJobId*/) throws Exception, InvocationTargetException;

    /**
     * 
     * doUpdateUser:修改用户信息
     *
     * @author luoyibo
     * @param entity
     * @param currentUser
     * @return
     * @throws Exception
     * @throws InvocationTargetException
     *             SysUser
     * @throws @since
     *             JDK 1.8
     */
    public User doUpdateUser(User entity, User currentUser) throws Exception, InvocationTargetException;

    /**
     * 
     * deleteUserRole:删除指定用户的所属角色.
     * 
     * @author luoyibo
     * @param userId
     *            要删除角色的用户ID
     * @param delRoleIds
     *            要删除的角色ID,多个用英文逗号隔开
     * @param currentUser
     *            当前操作员对象
     * @return String
     * @throws @since
     *             JDK 1.8
     */
    public Boolean doDeleteUserRole(String userId, String delRoleIds, User currentUser);

    /**
     * 
     * addUserRole:给指定用户添加角色.
     *
     * @author luoyibo
     * @param userId
     *            要添加角色的用户ID
     * @param addRoleIds
     *            要添加的角色ID,多个用英文逗号隔开
     * @param currentUser
     *            当前操作员对象
     * @return Boolean
     * @throws @since
     *             JDK 1.8
     */
    public Boolean doAddUserRole(String userId, String addRoleIds, User currentUser);

    /**
     * 
     * getDeptUser:查询指定部门的用户,带翻页功能
     *
     * @author luoyibo
     * @param start
     *            记录起始位置
     * @param limit
     *            查询的最大记录条数
     * @param sort
     *            排序条件
     * @param filter
     *            过滤条件
     * @param isDelete
     * @param deptId
     * @return QueryResult<SysUser>
     * @throws @since
     *             JDK 1.8
     */
    public QueryResult<User> getDeptUser(Integer start, Integer limit, String sort, String filter, Boolean isDelete,
            String userIds, User currentUser);

    /**
     * 
     * batchSetDept:批量设置用户的所属部门.
     *
     * @author luoyibo
     * @param deptId
     * @param userIds
     * @param cuurentUser
     * @return Boolean
     * @throws @since
     *             JDK 1.8
     */
    //    public Boolean batchSetDept(String deptId, String userIds, SysUser cuurentUser);

    public List<User> getUserByRoleName(String roleName);

    /**
     * 
     * doDeleteUser:删除指定的用户 说明：此处删除用户是删除当前用户点击时选择的部门
     * 
     * @author luoyibo
     * @param delIds
     *            要删除的用户的ID
     * @param orgId
     *            点击的部门ID
     * @param currentUser当前操作者
     * @return Boolean
     * @throws @since
     *             JDK 1.8
     */
    public Boolean doDeleteUser(String delIds, String orgId, User currentUser);

    public QueryResult<User> getUserByRoleId(String roleId);
    
    


	public HashMap<String, Set<String>> getUserRoleMenuPermission(User sysUser, Session session);

	public void deleteUserMenuTreeRedis(Permission sysPermission);

	public void deleteUserMenuTreeRedis(String[] roleIds);

	public QueryResult<User> getUserNotInRoleId(String roleId, int start, int limit, String sort, String filter);

	public List<User> getUserByDeptId(String id);

	public List<ImportNotInfo> doImportUser(List<List<Object>> listObject, User currentUser);

	public Set<Department> getDeptByUserId(String userId);
	
	public String getUserOwnDeptids(User currentUser) ;

	public void deleteUserRoleRedis(String ... userId);
}