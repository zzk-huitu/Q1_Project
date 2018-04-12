package com.yc.q1.service.base.pt.basic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yc.q1.core.model.extjs.QueryResult;
import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.basic.PtFuncRoomCourse;
import com.yc.q1.model.base.pt.system.PtUser;


/**
 * 
 * ClassName: JwFuncroomcourseService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 排课课程表(JW_T_FUNCROOMCOURSE)实体Service接口类.
 * date: 2017-03-06
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtFuncRoomCourseService extends BaseService<PtFuncRoomCourse> {

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
    public QueryResult<PtFuncRoomCourse> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete); 

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
	public PtFuncRoomCourse doUpdateEntity(PtFuncRoomCourse entity, PtUser currentUser);

	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public PtFuncRoomCourse doAddEntity(PtFuncRoomCourse entity, PtUser currentUser);

	public Integer doAddEntityList(List<PtFuncRoomCourse> funcRoomCourseList, PtUser currentUser) throws IllegalAccessException, InvocationTargetException;
}