package com.yc.q1.service.base.pt.basic;

import java.util.List;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.basic.PtCalender;
import com.yc.q1.model.base.pt.basic.PtCalenderDetail;
import com.yc.q1.model.base.pt.system.PtUser;


/**
 * 
 * ClassName: JwCalenderdetailService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 校历节次信息表(JW_T_CALENDERDETAIL)实体Service接口类.
 * date: 2016-08-30
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtCalenderDetailService extends BaseService<PtCalenderDetail> {
	
	/**
     * 根据JwTCander对象找到校历详细列表
     * @param jtc
     * @return
     * @author huangzc
     */
	public List<PtCalenderDetail> queryJwTCanderdetailByJwTCander(PtCalender jtc);
	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	public PtCalenderDetail doUpdateEntity(PtCalenderDetail entity, PtUser currentUser);
	
	public PtCalenderDetail doAddEntity(PtCalenderDetail entity, PtUser currentUser);
	public Boolean doDeleteEntity(String delIds);

}