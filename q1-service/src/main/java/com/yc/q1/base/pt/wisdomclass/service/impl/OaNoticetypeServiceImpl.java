package com.yc.q1.base.pt.wisdomclass.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.wisdomclass.dao.NoticeTypeDao;
import com.yc.q1.base.pt.wisdomclass.model.NoticeType;
import com.yc.q1.base.pt.wisdomclass.service.OaNoticetypeService;
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
public class OaNoticetypeServiceImpl extends BaseServiceImpl<NoticeType> implements OaNoticetypeService{

    @Resource
    public void setOaNoticetypeDao(NoticeTypeDao dao) {
        this.dao = dao;
    }

}