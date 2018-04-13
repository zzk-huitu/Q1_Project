package com.yc.q1.service.base.pt.system;

import java.util.List;
import java.util.Map;

import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.system.PtUserDeptJob;

/**
 * 
 * ClassName: BaseUserdeptjobService Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 用户部门岗位(BASE_T_USERDEPTJOB)实体Service接口类. date:
 * 2017-03-27
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface PtUserDeptJobService extends BaseService<PtUserDeptJob> {

	/**
	 * 数据列表
	 * 
	 * @param start
	 *            查询的起始记录数
	 * @param limit
	 *            每页的记录数
	 * @param sort
	 *            排序参数
	 * @param filter
	 *            查询过滤参数
	 * @param isDelete
	 *            为true表示只列出未删除的， 为false表示列出所有
	 * @return
	 */
	// public QueryResult<BaseUserdeptjob> list(Integer start, Integer limit,
	// String sort, String filter, Boolean isDelete);

	/**
	 * 根据主键逻辑删除数据
	 * 
	 * @param ids
	 *            要删除数据的主键
	 * @param currentUser
	 *            当前操作的用户
	 * @return 操作成功返回true，否则返回false
	 */
	// public Boolean doLogicDeleteByIds(String ids, SysUser currentUser);

	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	// public BaseUserdeptjob doUpdateEntity(BaseUserdeptjob entity, SysUser
	// currentUser);

	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	// public BaseUserdeptjob doAddEntity(BaseUserdeptjob entity, SysUser
	// currentUser);

	public List<PtUserDeptJob> getUserDeptJobList(PtUser currentUser);

	public Map<String, PtUserDeptJob> getUserDeptJobMaps(PtUser currentUser);

	public PtUserDeptJob getUserMasterDeptJob(PtUser currentUser);

	public Boolean doLogicDeleteByIds(String ids, PtUser currentUser);

	public boolean doRemoveUserFromDeptJob(String delIds, PtUser currentUser);

	public boolean doAddUserToDeptJob(String deptJobId, String userId, PtUser currentUser);

	public boolean doSetMasterDeptJob(String delIds, String userId, PtUser currentUser);

	public QueryResult<PtUserDeptJob> getUserByDeptJobId(String deptJobId, Integer start, Integer limit, String sort);

	boolean doSetMasterDeptJobFromUser(String userIds, String deptJobId, PtUser currentUser);

}