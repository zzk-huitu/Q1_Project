package com.zd.school.plartform.basedevice.service;

import com.zd.core.service.BaseService;
import com.zd.school.build.define.model.SkPriceDefine;
import com.zd.school.plartform.system.model.User;


/**
 * 水控费率定义
 * @author hucy
 *
 */
public interface BaseSkPriceDefineService extends BaseService<SkPriceDefine> {

	public SkPriceDefine doAddEntity(SkPriceDefine entity, User currentUser);
	public SkPriceDefine doUpdateEntity(SkPriceDefine entity, User currentUser);
	
}