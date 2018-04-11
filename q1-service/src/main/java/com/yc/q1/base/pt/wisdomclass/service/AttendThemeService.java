package com.yc.q1.base.pt.wisdomclass.service;

import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.model.base.pt.wisdomclass.AttendTheme;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: AttTitleService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 考勤主题(ATT_T_TITLE)实体Service接口类.
 * date: 2017-05-15
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface AttendThemeService extends BaseService<AttendTheme> {

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
    public QueryResult<AttendTheme> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete); 

	/**
	 * 根据主键逻辑删除数据
	 * 
	 * @param ids
	 *            要删除数据的主键
	 * @param currentUser
	 *            当前操作的用户
	 * @return 操作成功返回true，否则返回false
	 */
	public Boolean doLogicDeleteByIds(String ids, User currentUser);

	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public AttendTheme doUpdateEntity(AttendTheme entity, User currentUser);

	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public AttendTheme doAddEntity(AttendTheme entity, User currentUser);

	public Integer doAddUsers(String titleId, String[] userIds, String[] userNames, String[] userNumbs, String xm);

	public Integer doAddTerms(String titleId,  String[] termCodes, String[] roomIds, String[] roomNames, String xm);
}