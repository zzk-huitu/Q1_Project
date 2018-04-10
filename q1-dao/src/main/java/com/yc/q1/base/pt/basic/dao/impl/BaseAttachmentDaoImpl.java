package com.yc.q1.base.pt.basic.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.basic.dao.BaseAttachmentDao;
import com.yc.q1.base.pt.basic.model.Attachment;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 
 * ClassName: BaseTAttachmentDaoImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 公共附件信息表实体Dao接口实现类. date: 2016-07-06
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseAttachmentDaoImpl extends BaseDaoImpl<Attachment> implements BaseAttachmentDao {
    public BaseAttachmentDaoImpl() {
        super(Attachment.class);
        // TODO Auto-generated constructor stub
    }
}