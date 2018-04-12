package com.yc.q1.service.base.pt.wisdomclass;

import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.pt.wisdomclass.PtNotice;
import com.yc.q1.model.base.pt.wisdomclass.PtNoticeOther;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: OaNoticeService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 公告信息表(OA_T_NOTICE)实体Service接口类. date: 2016-12-21
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface PtNoticeService extends BaseService<PtNotice> {

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
	public QueryResult<PtNotice> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete);

	/**
	 * 根据主键逻辑删除数据
	 * 
	 * @param ids
	 *            要删除数据的主键
	 * @param currentUser
	 *            当前操作的用户
	 * @return 操作成功返回true，否则返回false
	 */
	public Boolean doLogicDeleteByIds(String ids, PtUser currentUser);

	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public PtNotice doUpdateEntity(PtNotice entity, PtUser currentUser);

	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @param deptIds
	 *            公告通知的部门
	 * @param roleIds
	 *            公告通知的角色
	 * @param userIds
	 *            公告通知的用户
	 * @param isNoticeParent 
	 * @param stuIds 
	 * @param terminalIds 
	 * @return
	 */
	public PtNotice doUpdateEntity(PtNotice entity, PtUser currentUser, String deptIds, String roleIds,
			String userIds, String terminalIds, String stuIds, String isNoticeParent);

	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public PtNotice doAddEntity(PtNotice entity, PtUser currentUser);

	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @param deptIds
	 *            公告通知的部门
	 * @param roleIds
	 *            公告通知的角色
	 * @param userIds
	 *            公告通知的用户
	 * @param terminalIds 
	 * @param isNoticeParent 
	 * @param stuIds 
	 * @return
	 */
	public PtNotice doAddEntity(PtNotice entity, PtUser currentUser, String deptIds, String roleIds, String userIds, String terminalIds, String stuIds, String isNoticeParent);

	/**
	 * 获取指定公告的通知部门、角色、人员的信息
	 * 
	 * @param id
	 *            指定的公告id
	 * @return
	 */
	public PtNoticeOther getNoticeOther(String id);
	
//	public  List<Notice> getUserOaNotice(User currentUser);
	
	/**
	 * 获取发送到指定终端的通知公告数据列表
	 * 
	 * @param start
	 *            查询的起始记录数
	 * @param limit
	 *            每页的记录数
	 * @param sort
	 *            排序参数
	 * @param filter
	 *            查询过滤参数
	 * @param termCode
	 *            指定的终端号
	 * @return
	 */
	public QueryResult<PtNotice> list(Integer start, Integer limit, String sort, String filter, String  termCode);
}