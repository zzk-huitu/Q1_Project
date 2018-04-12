package com.yc.q1.service.base.pt.wisdomclass.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.wisdomclass.PtNoticeType;
import com.yc.q1.service.base.pt.wisdomclass.PtNoticeTypeService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: OaNoticetypeServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 公告类型(OA_T_NOTICETYPE)实体Service接口实现类.
 * date: 2016-09-19
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtNoticeTypeServiceImpl extends BaseServiceImpl<PtNoticeType> implements PtNoticeTypeService{

	@Resource(name="PtNoticeTypeDao")	//将具体的dao注入进来
	public void setDao(BaseDao<PtNoticeType> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
}