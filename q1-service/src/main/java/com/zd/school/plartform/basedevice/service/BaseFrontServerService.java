package com.zd.school.plartform.basedevice.service;

import com.zd.core.service.BaseService;
import com.zd.school.build.define.model.FrontServer;
import com.zd.school.plartform.system.model.User;


/**
 * 综合前置管理
 * @author hucy
 *
 */
public interface BaseFrontServerService extends BaseService<FrontServer> {
	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public FrontServer doUpdateEntity(FrontServer entity, User currentUser);
	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public FrontServer doAddEntity(FrontServer entity, User currentUser);

}