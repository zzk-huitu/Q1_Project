package com.yc.q1.base.pt.wisdomclass.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.wisdomclass.dao.ClassParamDao;
import com.yc.q1.base.pt.wisdomclass.model.ClassParam;
import com.yc.q1.base.pt.wisdomclass.service.ClassParamService;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: EccClassparamServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班牌参数设置表(ECC_T_CLASSPARAM)实体Service接口实现类.
 * date: 2016-11-28
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class ClassParamServiceImpl extends BaseServiceImpl<ClassParam> implements ClassParamService{

	@Resource(name="ClassParamDao")	//将具体的dao注入进来
	public void setDao(BaseDao<ClassParam> dao) {
		super.setDao(dao);
	}
	
	@Override
	public QueryResult<ClassParam> list(Integer start, Integer limit, String sort, String filter, String whereSql,
			String orderSql,User currentUser) {
		String sortSql = StringUtils.convertSortToSql(sort);
		String filterSql = StringUtils.convertFilterToSql(filter);

		StringBuffer hql = new StringBuffer("from ClassParam o where 1=1 ");
		hql.append(whereSql);
		hql.append(filterSql);
        if (orderSql.length()>0){
        	if (sortSql.length()>0)
        		hql.append(orderSql+ " , " + sortSql);
        	else 
        		hql.append(orderSql);
        } else {
        	if (sortSql.length()>0)
        		hql.append(" order by  " + sortSql);
        }
        
        QueryResult<ClassParam> qResult = this.queryResult(hql.toString(), start, limit);
		return qResult;
	}
}