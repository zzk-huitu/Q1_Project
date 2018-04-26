package com.yc.q1.service.base.pt.card.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.card.PtCard;
import com.yc.q1.service.base.pt.card.PtCardService;

/**
 * 
 * ClassName: JwClassdormServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 班级宿舍(JW_T_CLASSDORM)实体Service接口实现类. date:
 * 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtCardServiceImpl extends BaseServiceImpl<PtCard> implements PtCardService {

	@Resource(name = "PtCardDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtCard> dao) {
		super.setDao(dao);
	}

}