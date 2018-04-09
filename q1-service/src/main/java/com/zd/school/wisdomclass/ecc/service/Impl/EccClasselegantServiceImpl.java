package com.zd.school.wisdomclass.ecc.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.StringUtils;
import com.zd.school.jw.ecc.model.ClassMien;
import com.zd.school.plartform.system.model.User;
import com.zd.school.wisdomclass.ecc.dao.EccClasselegantDao;
import com.zd.school.wisdomclass.ecc.service.EccClasselegantService;

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
public class EccClasselegantServiceImpl extends BaseServiceImpl<ClassMien> implements EccClasselegantService{

    @Resource
    public void setEccClasselegantDao(EccClasselegantDao dao) {
        this.dao = dao;
    }

	@Override
	public QueryResult<ClassMien> list(Integer start, Integer limit, String sort, String filter, String whereSql,
			String orderSql,User currentUser) {
		String sortSql = StringUtils.convertSortToSql(sort);
		String filterSql = StringUtils.convertFilterToSql(filter);

		StringBuffer hql = new StringBuffer("from ClassMien o where 1=1 ");
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
        
        QueryResult<ClassMien> qResult = this.queryResult(hql.toString(), start, limit);
		return qResult;
	}
}