package com.yc.q1.base.pt.basic.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.service.AttachmentService;
import com.yc.q1.model.base.pt.basic.Attachment;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: BaseTAttachmentServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 公共附件信息表实体Service接口实现类. date: 2016-07-06
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class AttachmentServiceImpl extends BaseServiceImpl<Attachment> implements AttachmentService {

	@Resource(name = "AttachmentDao") // 将具体的dao注入进来
	public void setDao(BaseDao<Attachment> dao) {
		super.setDao(dao);
	}

}