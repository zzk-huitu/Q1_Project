package com.zd.core.service;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.DateUtil;
import com.zd.core.util.EntityUtil;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @param <E>
 * @ClassName: BaseServiceImpl
 * @Description: 基础服务接口实现类
 * @author: luoyibo
 * @date: 2016年3月9日 下午6:18:55
 */
@Transactional
public class BaseServiceImpl<E> implements BaseService<E> {
	private static Logger logger = Logger.getLogger(BaseServiceImpl.class);
	/**
	 * The dao.
	 */
	protected BaseDao<E> dao;

	@Override
	public Session getSession() {
		return this.dao.getSession();
	}
	
	/**
	 * 持久化对象实体.
	 *
	 * @param entity
	 *            对象实体
	 */
	@Override
	public void persist(E entity) {
		this.dao.persist(entity);
	}

	/**
	 * 根据多个id参数删除对象.
	 *
	 * @param id
	 *            多个id，以英文逗号隔开
	 * @return 返回true或者false
	 */
	@Override
	public boolean deleteByPK(Serializable... id) {
		return this.dao.deleteByPK(id);
	}

	/**
	 * 删除对象实体.
	 *
	 * @param entity
	 *            对象实体
	 */
	@Override
	public void delete(E entity) {
		this.dao.delete(entity);
	}

	/**
	 * 以HQL的方式，根据单个属性删除对象实体.
	 *
	 * @param propName
	 *            属性名称
	 * @param propValue
	 *            属性值
	 */
	@Override
	public void deleteByProperties(String propName, Object propValue) {
		this.dao.deleteByProperties(propName, propValue);
	}

	/**
	 * 以HQL的方式，根据多个属性删除对象实体.
	 *
	 * @param propName
	 *            属性名称
	 * @param propValue
	 *            属性值
	 */
	@Override
	public void deleteByProperties(String[] propName, Object[] propValue) {
		this.dao.deleteByProperties(propName, propValue);
	}

	/**
	 * 根据给定的Detached对象标识符更新对象实体.
	 *
	 * @param entity
	 *            对象实体
	 */
	@Override
	public void update(E entity) {
		this.dao.update(entity);
	}

	/**
	 * 根据多个属性条件更新对象实体多个属性.
	 *
	 * @param conditionName
	 *            WHERE子句条件的属性数组名称
	 * @param conditionValue
	 *            WHERE子句条件的属性数组值
	 * @param propertyName
	 *            UPDATE子句属性数组名称
	 * @param propertyValue
	 *            UPDATE子句属性数组值
	 */
	@Override
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName,
			Object[] propertyValue) {
		this.dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	/**
	 * 根据单个属性条件更新对象实体多个属性.
	 *
	 * @param conditionName
	 *            WHERE子句条件的属性数组名称
	 * @param conditionValue
	 *            WHERE子句条件的属性数组值
	 * @param propertyName
	 *            UPDATE子句属性名称
	 * @param propertyValue
	 *            UPDATE子句属性值
	 */
	@Override
	public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName,
			Object propertyValue) {
		this.dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	/**
	 * 根据多个属性条件更新对象实体单个属性.
	 *
	 * @param conditionName
	 *            WHERE子句条件的属性名称
	 * @param conditionValue
	 *            WHERE子句条件的属性值
	 * @param propertyName
	 *            UPDATE子句属性数组名称
	 * @param propertyValue
	 *            UPDATE子句属性数组值
	 */
	@Override
	public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName,
			Object[] propertyValue) {
		this.dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	/**
	 * 根据单个属性条件更新对象实体单个属性.
	 *
	 * @param conditionName
	 *            WHERE子句条件的属性名称
	 * @param conditionValue
	 *            WHERE子句条件的属性值
	 * @param propertyName
	 *            UPDATE子句属性名称
	 * @param propertyValue
	 *            UPDATE子句属性值
	 */
	@Override
	public void updateByProperties(String conditionName, Object conditionValue, String propertyName,
			Object propertyValue) {
		this.dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	/**
	 * 先删除再插入去更新对象实体.
	 *
	 * @param entity
	 *            待更新的对象实体
	 * @param oldId
	 *            已存在的对象实体主键
	 */
	@Override
	public void update(E entity, Serializable oldId) {
		this.dao.update(entity, oldId);
	}

	/**
	 * 合并给定的对象实体状态到当前的持久化上下文.
	 *
	 * @param entity
	 *            给定的对象实体
	 * @return 返回对象实体
	 */
	@Override
	public E merge(E entity) {
		return this.dao.merge(entity);
	}

	/**
	 * 根据ID立即加载持久化对象实体.
	 *
	 * @param id
	 *            ID值
	 * @return 返回对象实体
	 */
	@Override
	public E get(Serializable id) {
		return this.dao.get(id);
	}

	/**
	 * 根据ID延迟加载持久化对象实体.
	 *
	 * @param id
	 *            ID值
	 * @return 返回对象实体
	 */
	@Override
	public E load(Serializable id) {
		return this.dao.load(id);
	}

	/**
	 * 根据属性数组获取单个对象实体.
	 *
	 * @param propName
	 *            属性数组名称
	 * @param propValue
	 *            属性数组值
	 * @return 返回对象实体
	 */
	@Override
	public E getByProerties(String[] propName, Object[] propValue) {
		return this.dao.getByProerties(propName, propValue);
	}

	/**
	 * 根据属性数组和排序条件获取单个对象实体.
	 *
	 * @param propName
	 *            属性数组名称
	 * @param propValue
	 *            属性数组值
	 * @param sortedCondition
	 *            排序条件
	 * @return 返回对象实体
	 */
	@Override
	public E getByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		return this.dao.getByProerties(propName, propValue, sortedCondition);
	}

	/**
	 * 根据属性获取单个对象实体.
	 *
	 * @param propName
	 *            属性名称
	 * @param propValue
	 *            属性值
	 * @return 返回对象实体
	 */
	@Override
	public E getByProerties(String propName, Object propValue) {
		return this.dao.getByProerties(propName, propValue);
	}

	/**
	 * 根据属性和排序条件获取单个对象实体.
	 *
	 * @param propName
	 *            属性名称
	 * @param propValue
	 *            属性值
	 * @param sortedCondition
	 *            排序条件
	 * @return 返回对象实体
	 */
	@Override
	public E getByProerties(String propName, Object propValue, Map<String, String> sortedCondition) {
		return this.dao.getByProerties(propName, propValue, sortedCondition);
	}

	/**
	 * 根据属性、排序条件和要返回的记录数目获取对象实体列表.
	 *
	 * @param propName
	 *            属性数组名称
	 * @param propValue
	 *            属性数组值
	 * @param sortedCondition
	 *            排序条件
	 * @param top
	 *            要返回的记录数目
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition,
			Integer top) {
		return this.dao.queryByProerties(propName, propValue, sortedCondition, top);
	}

	/**
	 * 根据属性和排序条件获取对象实体列表.
	 *
	 * @param propName
	 *            属性数组名称
	 * @param propValue
	 *            属性数组值
	 * @param sortedCondition
	 *            排序条件
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryByProerties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
		return this.dao.queryByProerties(propName, propValue, sortedCondition);
	}

	/**
	 * 根据属性和要返回的记录数目获取对象实体列表.
	 *
	 * @param propName
	 *            属性数组名称
	 * @param propValue
	 *            属性数组值
	 * @param top
	 *            要返回的记录数目
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryByProerties(String[] propName, Object[] propValue, Integer top) {
		return this.dao.queryByProerties(propName, propValue, top);
	}

	/**
	 * 根据属性获取对象实体列表.
	 *
	 * @param propName
	 *            属性数组名称
	 * @param propValue
	 *            属性数组值
	 * @return the list
	 */
	@Override
	public List<E> queryByProerties(String[] propName, Object[] propValue) {
		return this.dao.queryByProerties(propName, propValue);
	}

	/**
	 * 根据属性、排序条件和要返回的记录数目获取对象实体列表.
	 *
	 * @param propName
	 *            属性名称
	 * @param propValue
	 *            属性值
	 * @param sortedCondition
	 *            排序条件
	 * @param top
	 *            要返回的记录数目
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition,
			Integer top) {
		return this.dao.queryByProerties(propName, propValue, sortedCondition, top);
	}

	/**
	 * 根据属性和排序条件获取对象实体列表.
	 *
	 * @param propName
	 *            属性名称
	 * @param propValue
	 *            属性值
	 * @param sortedCondition
	 *            排序条件
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryByProerties(String propName, Object propValue, Map<String, String> sortedCondition) {
		return this.dao.queryByProerties(propName, propValue, sortedCondition);
	}

	/**
	 * 根据属性和要返回的记录数目获取对象实体列表.
	 *
	 * @param propName
	 *            属性名称
	 * @param propValue
	 *            属性值
	 * @param top
	 *            要返回的记录数目
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryByProerties(String propName, Object propValue, Integer top) {
		return this.dao.queryByProerties(propName, propValue, top);
	}

	/**
	 * 根据属性获取对象实体列表.
	 *
	 * @param propName
	 *            属性名称
	 * @param propValue
	 *            属性值
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryByProerties(String propName, Object propValue) {
		return this.dao.queryByProerties(propName, propValue);
	}

	/**
	 * 彻底清除会话.
	 */
	@Override
	public void clear() {
		this.dao.clear();
	}

	/**
	 * 从会话缓存中删除此对象实体.
	 *
	 * @param entity
	 *            待删除的对象实体
	 */
	@Override
	public void evict(E entity) {
		this.dao.evict(entity);
	}

	/**
	 * 查询出对象实体的所有数目.
	 *
	 * @return 返回对象实体所有数目
	 */
	@Override
	public Long countAll() {
		return this.dao.countAll();
	}

	/**
	 * 查询出所有的对象实体列表.
	 *
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryAll() {
		return this.dao.queryAll();
	}

	/**
	 * 根据排序条件和要返回的记录数目查询出对象实体列表.
	 *
	 * @param sortedCondition
	 *            排序条件
	 * @param top
	 *            要返回的记录数目
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryAll(Map<String, String> sortedCondition, Integer top) {
		return this.dao.queryAll(sortedCondition, top);
	}

	/**
	 * 根据要返回的记录数目查询出对象实体列表.
	 *
	 * @param top
	 *            要返回的记录数目
	 * @return 返回对象实体列表
	 */
	@Override
	public List<E> queryAll(Integer top) {
		return this.dao.queryAll(top);
	}

	/**
	 * 根据HQL查询实体列表.
	 *
	 * @param hql
	 *            the hql
	 * @return the list
	 */
	@Override
	public List<E> queryByHql(String hql) {
		return this.dao.queryByHql(hql);
	}

	/**
	 * 根据HQL查询实体列表.
	 *
	 * @param hql
	 *            查询语句
	 * @param start
	 *            返回记录起始位置
	 * @param limit
	 *            返回最大记录数
	 * @return the list
	 */
	@Override
	public List<E> queryByHql(String hql, Integer start, Integer limit) {
		return this.dao.queryByHql(hql, start, limit);
	}

	@Override
	public List<E> queryByHql(String hql, Integer start, Integer limit, String propName, Object[] objs) {
		return this.dao.queryByHql(hql, start, limit, propName, objs);
	}

	/**
	 * 根据HQL语句返回对象实体数目.
	 *
	 * @param hql
	 *            执行的HQL语句
	 * @return the count
	 */
	@Override
	public Integer getQueryCountByHql(String hql) {
		return this.dao.getQueryCountByHql(hql);
	}

	@Override
	public Integer getQueryCountByHql(String hql, String propName, Object[] objs) {
		return this.dao.getQueryCountByHql(hql, propName, objs);
	}

	/**
	 * 执行HQL语句并返回受影响的记录的条数.
	 *
	 * @param hql
	 *            要执行的HQL语句
	 * @return 受影响的记录数
	 */
	@Override
	public Integer doExecuteCountByHql(String hql) {
		return this.dao.getExecuteCountByHql(hql);
	}

	/**
	 * 判断字段的值是否存在 如果是插入id赋值-1或者new Guid,如果是修改id赋值 要修改项的值.
	 *
	 * @param fieldName
	 *            要判断的字段
	 * @param fieldValue
	 *            要判断的字段的值
	 * @param id
	 *            实体的标识
	 * @param where
	 *            附加查询条件
	 * @return true, if successful
	 */
	@Override
	public boolean IsFieldExist(String fieldName, String fieldValue, String id, String where) {
		return this.dao.IsFieldExist(fieldName, fieldValue, id, where);
	}

	/**
	 * 判断字段的值是否存在 如果是插入id赋值-1或者new Guid,如果是修改id赋值 要修改项的值.
	 *
	 * @param fieldName
	 *            要判断的字段
	 * @param fieldValue
	 *            要判断的字段的值
	 * @param id
	 *            实体的标识
	 * @return true, if successful
	 */
	@Override
	public boolean IsFieldExist(String fieldName, String fieldValue, String id) {
		return this.dao.IsFieldExist(fieldName, fieldValue, id);
	}

	/**
	 * 逻辑删除或还原指定的记录.
	 *
	 * @param ids
	 *            要处理的记录的ID,多个ID使用","间隔
	 * @param isDelete
	 *            处理标记
	 * @return true, if successful
	 */
	@Override
	public boolean doLogicDelOrRestore(String ids, String isDelete, String operator) {
		return this.dao.logicDelOrRestore(ids, isDelete, operator);
	}

	/**
	 * 生成指定实体的默认排序号
	 *
	 * @param entity
	 *            要获取默认排序号的实体
	 * @return 得到的默认排序号
	 */
	@Override
	public Integer getDefaultOrderIndex(E entity) {
		Integer defaultOrderIndex = Integer.valueOf(0);
		String className = entity.getClass().getSimpleName();
		String hql = " from  " + className + " where orderIndex=(select max(orderIndex) from " + className + ")";
		List<E> list = queryByHql(hql);
		if (list.size() > 0) {
			defaultOrderIndex = (Integer) EntityUtil.getPropertyValue(list.get(0), "orderIndex") + 1;
		} else
			defaultOrderIndex = 0;

		return defaultOrderIndex;
	}

	/**
	 * 获取树形数据
	 *
	 * @param entity
	 *            构建树形数据的实体
	 * @param node
	 *            根节点
	 * @param nodeId
	 *            根节点ID
	 * @param whereSql
	 *            构建条件
	 * @param expanded
	 *            是否展开
	 * @param excludes
	 *            构建时排除的字段
	 * @return
	 */
	/**
	 * 通用列表导出excel reportExcel:(这里用一句话描述这个方法的作用). TODO(这里描述这个方法适用条件 – 可选).
	 * TODO(这里描述这个方法的执行流程 – 可选). TODO(这里描述这个方法的使用方法 – 可选). TODO(这里描述这个方法的注意事项 –
	 * 可选).
	 *
	 * @param @param
	 *            tableCode
	 * @param @param
	 *            whereSql
	 * @param @param
	 *            fieldNames
	 * @param @param
	 *            fieldCodes
	 * @param @return
	 * @return String
	 * @throws @since
	 *             JDK 1.8
	 * @author luoyibo
	 */
	@Override
	@SuppressWarnings("unchecked")
	public String reportExcel(String tableCode, String whereSql, String fieldNames, String fieldCodes, String userFlag,
			String uploadUrl) {

		String temPath = tableCode + "_" + DateUtil.formatDate(new Date()) + "_" + userFlag + ".xls";
		// String classPath =
		// BaseServiceImpl.class.getClassLoader().getResource("/").getPath();
		// String rootPath = "";
		// if("\\".equals(File.separator)){
		// rootPath = classPath.substring(1,classPath.indexOf("/classes"));
		// rootPath = rootPath.replace("/", "\\");
		// }
		// String exclePath = rootPath + "\\jw-web\\static\\upload\\tem\\" +
		// temPath;
		String exclePath = uploadUrl + temPath;
		// 创建临时 excel文集
		try {
			WritableWorkbook book = Workbook.createWorkbook(new File(exclePath));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页 ", 0);
			// 查表
			String sql = buildQuerySql(tableCode, fieldCodes, whereSql);
			List<Object[]> list = (List<Object[]>) this.dao.queryByHql(sql);
			// 初始化表头
			String[] fields = fieldNames.split(",");
			Label label = null;
			for (int i = 0; i < fields.length; i++) {
				label = new Label(i, 0, fields[i]);
				sheet.addCell(label);
			}
			// 创建内容
			int rowIndex = 1;// 因为第0行被表头占用了
			for (Object[] row : list) {
				Object text = null;
				for (int i = 0; i < row.length; i++) {
					text = row[i];
					if (text == null) {
						text = "";
					}
					label = new Label(i, rowIndex, text.toString());
					sheet.addCell(label);
				}
				rowIndex++;
			}
			// 写入数据并关闭文件
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/upload/tem/" + temPath;
	}

	private String buildQuerySql(String tableCode, String fieldCodes, String whereSql) {
		StringBuffer querySql = new StringBuffer();
		String[] fields = fieldCodes.split(",");
		querySql.append("select ");
		for (String f : fields) {
			querySql.append(f);
			querySql.append(",");
		}
		querySql.deleteCharAt(querySql.length() - 1);
		querySql.append(" from " + tableCode + " where 1=1" + whereSql);
		return querySql.toString();
	}

	/**
	 * 根据SQL查询实体列表
	 *
	 * @param sql
	 *            查询语句
	 * @return
	 */
	@Override
	public List<E> querySql(String sql) {
		return this.dao.queryBySql(sql);
	}

	/**
	 * 根据SQL查询对象集合
	 *
	 * @param sql
	 *            查询语句
	 * @return
	 */
	@Override
	public List<Object[]> queryObjectBySql(String sql) {
		return this.dao.queryObjectBySql(sql);
	}

	@Override
	public QueryResult<E> queryPageResult(Integer start, Integer limit, String sort, String filter,
			boolean isDelete) {

		// TODO Auto-generated method stub
		return this.dao.queryPageResult(start, limit, sort, filter, isDelete);
	}

	@Override
	public <T> QueryResult<T> queryCountToHql(Integer start, Integer limit, String sort, String filter, String hql,
			String groupBy, String orderBy) {
		return this.dao.doQueryCountToHql(start, limit, sort, filter, hql, groupBy, orderBy);
	}

	@Override
	public <T> QueryResult<T> queryCountToHql(Integer start, Integer limit, String sort, String filter, String hql,
			String groupBy, String orderBy, String where) {
		return this.dao.doQueryCountToHql(start, limit, sort, filter, hql, groupBy, orderBy, where);
	}

	@Override
	public <T> T getEntityByHql(String hql, Object... args) {
		return this.dao.getEntityByHql(hql, args);
	}

	@Override
	public <T> List<T> queryEntityByHql(String hql, Object... args) {
		return this.dao.queryEntityByHql(hql, args);
	}

	@Override
	public <T> List<T> queryEntityBySql(String sql, Class<T> clz) {

		// TODO Auto-generated method stub
		return this.dao.queryEntityBySql(sql, clz);
	}

	@Override
	public Integer doExecuteCountBySql(String sql) {

		// TODO Auto-generated method stub
		return this.dao.getExecuteCountBySql(sql);
	}

	@Override
	public Integer getQueryCountBySql(String sql) {
		return this.dao.getQueryCountBySql(sql);
	}

	@Override
	public List<E> queryByHql(String hql, String propName, Object[] objs) {
		return this.dao.queryByHql(hql, propName, objs);
	}

	@Override
	public QueryResult<E> queryResult(String hql, Integer start, Integer limit) {
		return this.dao.queryPageResult(start, limit, hql);
	}
	@Override
	public QueryResult<E> queryResult(String hql, Integer start, Integer limit,String countHql) {
		return this.dao.queryPageResult(start, limit, hql,countHql);
	}
	
	@Override
	public <T> T getEntityBySql(String sql) {
		// TODO Auto-generated method stub
		return this.dao.getEntityBySql(sql);
	}

	@Override
	public List<Map<String, Object>> queryMapBySql(String sql) {
		// TODO Auto-generated method stub
		return this.dao.queryMapBySql(sql);
	}

	/**
	 * 带分页的sql查询并转换成实体类
	 *
	 * @param sql
	 *            查询的sql语句
	 * @param start
	 *            起始页
	 * @param limit
	 *            每页记录数
	 * @param clz
	 *            要转换成的实体类
	 * @param <T>
	 *            实体类的泛型参数
	 * @return 返回转换后的结果
	 */
	@Override
	public <T> QueryResult<T> queryPageResultBySql(String sql, Integer start, Integer limit, Class<T> clz) {
		return this.dao.queryPageResultBySql(sql, start, limit, clz);
	}
	@Override
	public <T> QueryResult<T> queryPageResultBySql(String sql, Integer start, Integer limit, Class<T> clz,String countSql) {
		return this.dao.queryPageResultBySql(sql,start, limit, clz,countSql);
	}

	/**
	 * 默认的添加实体的方法
	 * 
	 * @param entity
	 *            实体对象
	 * @param operator
	 *            操作人姓名或ID
	 * @return 返回持久化对象
	 */	
	@Override
	public E doAddEntity(E entity,String operator) {
		// TODO Auto-generated method stub
		E saveEntity;
		try {
			Class<? extends Object> clazz = entity.getClass();
			saveEntity = (E) clazz.newInstance();

			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("uuid");
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
		
			clazz.getMethod("setCreateUser", String.class).invoke(saveEntity, operator);// 设置修改人		
			entity = this.dao.merge(saveEntity);// 执行修改方法
			return entity;

		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException e)  {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 默认的更新实体的方法
	 * 
	 * @param entity
	 *            实体对象
	 * @return 返回持久化对象
	 */
	@Override
	public E doUpdateEntity(E entity, String operator,List<String> excludedProp ) {
		// TODO Auto-generated method stub
		try {
			// 先拿到已持久化的实体
			String uuid = (String) entity.getClass().getMethod("getUuid").invoke(entity);
			E saveEntity = this.get(uuid);
			
			if(excludedProp==null)
				BeanUtils.copyPropertiesExceptNull(saveEntity, entity);
			else
				BeanUtils.copyPropertiesExceptNull(saveEntity, entity,excludedProp);
			
			Class<? extends Object> clazz = saveEntity.getClass();
			clazz.getMethod("setUpdateTime", Date.class).invoke(saveEntity, new Date());// 设置修改时间
			clazz.getMethod("setUpdateUser", String.class).invoke(saveEntity, operator);// 设置修改人
			entity = this.merge(saveEntity);// 执行修改方法

			return entity;
		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
