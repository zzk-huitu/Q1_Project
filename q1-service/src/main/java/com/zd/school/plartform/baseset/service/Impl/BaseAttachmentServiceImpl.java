package com.zd.school.plartform.baseset.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.service.BaseServiceImpl;
import com.zd.school.plartform.baseset.dao.BaseAttachmentDao;
import com.zd.school.plartform.baseset.model.Attachment;
import com.zd.school.plartform.baseset.service.BaseAttachmentService;

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
public class BaseAttachmentServiceImpl extends BaseServiceImpl<Attachment> implements BaseAttachmentService {

    @Resource
    public void setBaseTAttachmentDao(AttachmentDao dao) {
        this.dao = dao;
    }

}