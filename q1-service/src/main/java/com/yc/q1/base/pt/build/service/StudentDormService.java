package com.yc.q1.base.pt.build.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.yc.q1.model.base.pt.build.PtClassDormAllot;
import com.yc.q1.model.base.pt.build.PtStudentDorm;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.CommTree;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: DormStudentdormService Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: (DORM_T_STUDENTDORM)实体Service接口类. date:
 * 2016-08-26
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface StudentDormService extends BaseService<PtStudentDorm> {

	//public CommTree getCommTree(String rootId, String deptType, SysUser currentUser);
	public List<PtStudentDorm> oneKeyList(PtStudentDorm entity,String whereSql);
	public Boolean doOneKeyAllotDorm(String gradId,String boyId,String girlId,PtUser currentUser);
	public Integer doDormAutoAllot(String claiId,PtUser currentUser);
	public Boolean doDormHandAllot(PtStudentDorm entity,Map hashMap,PtUser currentUser)throws IllegalAccessException, InvocationTargetException ;
	public List<PtClassDormAllot>  mixDormList(PtClassDormAllot entity);
	public List<PtClassDormAllot>  emptyMixDormList(PtClassDormAllot entity);
	public Boolean doPushMessage(String classId);
	public Boolean doDeleteDorm(String[] delIds, String userId);
	public Integer doUpdateBedArkNum(String[] list, String userId);
	public Boolean doAddClassDorm(String classId, String dormIds, PtUser currentUser);
	public CommTree getUserRightDeptClassTree(String rootId, PtUser currentUser);
}