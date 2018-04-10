package com.yc.q1.base.pt.build.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.build.dao.ClassDormAllotDao;
import com.yc.q1.base.pt.build.model.ClassDormAllot;
import com.yc.q1.base.pt.build.service.BaseClassDormAllotService;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: JwClassdormServiceImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班级宿舍(JW_T_CLASSDORM)实体Service接口实现类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class BaseClassDormAllotServiceImpl extends BaseServiceImpl<ClassDormAllot> implements BaseClassDormAllotService{

    @Resource
    public void setJwClassdormDao(ClassDormAllotDao dao) {
        this.dao = dao;
    }

}