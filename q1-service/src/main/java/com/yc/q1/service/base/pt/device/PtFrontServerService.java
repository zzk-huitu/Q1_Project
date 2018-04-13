package com.yc.q1.service.base.pt.device;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.device.PtFrontServer;
import com.yc.q1.model.base.pt.system.PtUser;


/**
 * 综合前置管理
 * @author hucy
 *
 */
public interface PtFrontServerService extends BaseService<PtFrontServer> {
	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public PtFrontServer doUpdateEntity(PtFrontServer entity, PtUser currentUser);
	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public PtFrontServer doAddEntity(PtFrontServer entity, PtUser currentUser);

}