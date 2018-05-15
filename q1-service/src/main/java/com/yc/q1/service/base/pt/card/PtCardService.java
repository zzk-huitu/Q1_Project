package com.yc.q1.service.base.pt.card;


import javax.servlet.http.HttpServletRequest;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.card.PtCard;
import com.yc.q1.model.base.pt.card.PtCardBags;
import com.yc.q1.model.base.pt.system.PtUser;


/**
 * 
 * ClassName: JwClassdormService
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班级宿舍(JW_T_CLASSDORM)实体Service接口类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
public interface PtCardService extends BaseService<PtCard> {
	public Boolean doLockOrOn(String cardId ,PtUser currentUser);
	
	public Boolean doLockBatch(String ids ,PtUser currentUser);
	
	public Boolean doOnBatch(String ids ,PtUser currentUser);
	public Boolean doAddAcountOperator(PtCardBags entity ,PtUser currentUser,HttpServletRequest request);
	public Boolean doAddFillOperate(PtCardBags entity ,PtUser currentUser,HttpServletRequest request );
	
}