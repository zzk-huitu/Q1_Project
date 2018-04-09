package com.zd.school.plartform.baseset.service;

import com.zd.core.service.BaseService;
import com.zd.school.jw.eduresources.model.Calender;
import com.zd.school.jw.eduresources.model.Grade;
import com.zd.school.plartform.system.model.User;


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
 
public interface BaseCalenderService extends BaseService<Calender> {
	
	/**
     * 
     * 根据JwCalender得到该班级所对应的校历（某个学段）
     * @param jtg
     * @return
     * @author huangzc
     */
	public Calender findJwTcanderByClaiId(Grade  jtg);
	
	public int updateStatu(String calenderIds,String campusNames);
	public Calender  doUpdateEntity(Calender entity, User currentUser);
	public Calender  doAddEntity(Calender entity, User currentUser);
	public Boolean  doDeleteEntity(String delIds);
}