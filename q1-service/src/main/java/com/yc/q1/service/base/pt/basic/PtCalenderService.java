package com.yc.q1.service.base.pt.basic;

import com.yc.q1.model.base.pt.basic.PtCalender;
import com.yc.q1.model.base.pt.basic.PtGrade;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.service.BaseService;


/**
 * 
 * ClassName: JwCalenderService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 校历信息(JW_T_CALENDER)实体Service接口类.
 * date: 2016-08-30
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtCalenderService extends BaseService<PtCalender> {
	
	/**
     * 
     * 根据JwCalender得到该班级所对应的校历（某个学段）
     * @param jtg
     * @return
     * @author huangzc
     */
	public PtCalender findJwTcanderByClaiId(PtGrade  jtg);
	
	public int updateStatu(String calenderIds,String campusNames);
	public PtCalender  doUpdateEntity(PtCalender entity, PtUser currentUser);
	public PtCalender  doAddEntity(PtCalender entity, PtUser currentUser);
	public Boolean  doDeleteEntity(String delIds);
}