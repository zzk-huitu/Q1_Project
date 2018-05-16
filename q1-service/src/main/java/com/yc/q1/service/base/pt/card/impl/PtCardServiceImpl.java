package com.yc.q1.service.base.pt.card.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.DateUtil;
import com.yc.q1.model.base.pt.card.PtCard;
import com.yc.q1.model.base.pt.card.PtCardBags;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.storage.pt.PtCardStatusChange;
import com.yc.q1.model.storage.xf.XfUserXfAndCreditTotal;
import com.yc.q1.service.base.pt.card.PtCardService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.xf.XfUserXfAndCreditTotalService;

/**
 * 
 * ClassName: JwClassdormServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 班级宿舍(JW_T_CLASSDORM)实体Service接口实现类. date:
 * 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtCardServiceImpl extends BaseServiceImpl<PtCard> implements PtCardService {

	@Resource(name = "PtCardDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtCard> dao) {
		super.setDao(dao);
	}

	@Resource
	private PrimaryKeyRedisService keyRedisService;
	@Resource
	private XfUserXfAndCreditTotalService userXfAndCreditTotalService;
	
	@Override
	public Boolean doLockOrOn(String cardId,PtUser currentUser) {
		
		Boolean flag = false;
		// 先拿到已持久化的实体
		PtCard perEntity = this.get(cardId);
		Integer oldcardStatusId = perEntity.getCardStatusId();
//		Integer cardType = perEntity.getCardTypeId();
		Integer newcardStatusId;
		String cardClassName="";
		String cardStatusName="";
		if(oldcardStatusId==1){
			newcardStatusId =2;
			cardStatusName="挂失";
		}else{
			newcardStatusId =1;
			cardStatusName="正常";
		}
				
		perEntity.setUpdateUser(currentUser.getId());
		perEntity.setStatusChangeTime(new Date());
		perEntity.setCardStatusId(newcardStatusId);
		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("cardNo");
		this.doUpdateEntity(perEntity, currentUser.getId(), excludedProp);
		
		PtCardStatusChange cardStatusChange = new PtCardStatusChange();
		cardStatusChange.setUserId(perEntity.getUserId());
		cardStatusChange.setCardNo(perEntity.getCardNo());
		cardStatusChange.setCardClassName(cardClassName);
		cardStatusChange.setCardStatusName(cardStatusName);
		cardStatusChange.setId(keyRedisService.getId(PtCardStatusChange.ModuleType));
		this.getSession().merge(cardStatusChange);
		flag = true;
		return flag;
	}

	@Override
	public Boolean doLockBatch(String ids, PtUser currentUser) {
		Boolean flag = false;
		String[] lockBatchIds = ids.split(",");
		for(int i=0 ;i<lockBatchIds.length;i++){
			// 先拿到已持久化的实体
			PtCard perEntity = this.get(lockBatchIds[i]);
					
			perEntity.setUpdateUser(currentUser.getId());
			perEntity.setStatusChangeTime(new Date());
			perEntity.setCardStatusId(2);
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("cardNo");
			this.doUpdateEntity(perEntity, currentUser.getId(), excludedProp);
			
			PtCardStatusChange cardStatusChange = new PtCardStatusChange();
			cardStatusChange.setUserId(perEntity.getUserId());
			cardStatusChange.setCardNo(perEntity.getCardNo());
			cardStatusChange.setCardStatusName("挂失");
			cardStatusChange.setId(keyRedisService.getId(PtCardStatusChange.ModuleType));
			this.getSession().merge(cardStatusChange);
		}
		flag = true;
		return flag;
	}

	@Override
	public Boolean doOnBatch(String ids, PtUser currentUser) {
		Boolean flag = false;
		String[] lockBatchIds = ids.split(",");
		for(int i=0 ;i<lockBatchIds.length;i++){
			// 先拿到已持久化的实体
			PtCard perEntity = this.get(lockBatchIds[i]);
					
			perEntity.setUpdateUser(currentUser.getId());
			perEntity.setStatusChangeTime(new Date());
			perEntity.setCardStatusId(1);
			List<String> excludedProp = new ArrayList<>();
			excludedProp.add("cardNo");
			this.doUpdateEntity(perEntity, currentUser.getId(), excludedProp);
			
			PtCardStatusChange cardStatusChange = new PtCardStatusChange();
			cardStatusChange.setUserId(perEntity.getUserId());
			cardStatusChange.setCardNo(perEntity.getCardNo());
			cardStatusChange.setCardStatusName("正常");
			cardStatusChange.setId(keyRedisService.getId(PtCardStatusChange.ModuleType));
			this.getSession().merge(cardStatusChange);
		}
		flag = true;
		return flag;
	}

	/**充值/退款 保存：
	 * 1：先插入备份表数据 XfCreditAccountBack
	 * 2：插入卡余  PtCardBags 卡余需要加密
	 * 3：正式表  XfCreditAccount
	 * 4：对当天的充值记录汇总 XfUserXfAndCreditTotal ：有则追加，没有则插入一条当天的新数据
	 * 
	 */
	@Override
	public Boolean doAddAcountOperator(PtCardBags entity, PtUser currentUser,HttpServletRequest request) {
        String payStyle = request.getParameter("payStyle");
        String cardNo = request.getParameter("cardNo"); 
        String bagCode = request.getParameter("bagCode"); 
        String czMoneyTotal = request.getParameter("czMoneyTotal"); 
        String czMoney = request.getParameter("czMoney");
        String tkMoney = request.getParameter("tkMoney");
        String sign = request.getParameter("sign"); 	//0：充值 1：退款
        
        Integer useType = 100;
        Boolean flag= false;
		Date date = new Date();
		
		String hql = " update XfCreditAccountBack set payStyle = '"+payStyle+"' ,set cardValue = "+czMoneyTotal+""
				+ " where creditFactor = 1  and userId ='"+entity.getUserId()+"' and  bagType = '"+entity.getBagCode()+"'";
		
		String hql1 = " update PtCardBags set czCount = czCount+1 ,set cardValue = "+czMoneyTotal+""
				+ " where  userId ='"+entity.getUserId()+"' and  bagType = '"+entity.getBagCode()+"'";
		
		String hql2 = " update XfCreditAccount set  payStyle = '"+payStyle+"'  ,set cardValue = "+czMoneyTotal+""
				+ " where creditFactor = 1  and userId ='"+entity.getUserId()+"' and  bagType = '"+entity.getBagCode()+"'";
		if(bagCode.equals(2)){
			useType = 101;
		}

		String hql3 = " select userId from XfUserXfAndCreditTotal where  dateDay = '"+DateUtil.formatDate(date)+"' "
				+ " and userId = '"+entity.getUserId()+"' and cardNo = '"+cardNo+"' and useType = "+useType+" ";
		List<String> list= this.queryEntityByHql(hql2);
        if(list.size()>0){
        	String updateHql;
    		if(sign.equals(1)){
    		
    			updateHql= " update XfUserXfAndCreditTotal set updateTime = '"+new Date()+"', set creditMoney = creditMoney- "+tkMoney+""
    					+ "  where dateDay = '"+DateUtil.formatDate(date)+"'"
    					+ " and userId = '"+entity.getUserId()+"' and cardNo = '"+cardNo+"' and useType = "+useType+" ";
    		}else{
    			updateHql = " update XfUserXfAndCreditTotal set updateTime = '"+new Date()+"', set creditMoney = creditMoney+ "+czMoney+""
    					+ "  where dateDay = '"+DateUtil.formatDate(date)+"'"
    					+ " and userId = '"+entity.getUserId()+"' and cardNo = '"+cardNo+"' and useType = "+useType+" ";
    		}
    		
        	
			Integer j= this.doExecuteCountByHql(updateHql);
			
		}else{
			XfUserXfAndCreditTotal userXfAndCreditTotal =new XfUserXfAndCreditTotal();
			userXfAndCreditTotal.setId(keyRedisService.getId(XfUserXfAndCreditTotal.ModuleType));
			userXfAndCreditTotal.setCardNo(Long.valueOf(cardNo));
			userXfAndCreditTotal.setUserId(entity.getUserId());
			userXfAndCreditTotal.setUseType(100);
			userXfAndCreditTotal.setDateDay(DateUtil.formatDate(date));
			if(sign.equals(1)){
				userXfAndCreditTotal.setCreditMoney(new BigDecimal(tkMoney));	
			}else{
				userXfAndCreditTotal.setCreditMoney(new BigDecimal(czMoney));
			}
		
			userXfAndCreditTotal.setXfMoney(new BigDecimal(0.00));
			userXfAndCreditTotalService.merge(userXfAndCreditTotal);
		}
		
		
		Integer i= this.doExecuteCountByHql(hql);
		Integer i1=  this.doExecuteCountByHql(hql1);
		Integer i2=  this.doExecuteCountByHql(hql2);
		if(i>0&&i1>0&&i2>0){
			flag= true;
			
		}else{
			flag= false;
		}
		return flag;
		
	}
    /*
     * 错扣补款保存: 整个保存过程可以参考 up库的存储过程[dbo].[CoumneMoneyError]
     * 1: 插入表XfConsumeDetail 数据  ： 参考存储过程[usp_TE_ConsumeDetailXFInsert]
     * 2：  修改 PtCardBags 
     * 3： 对XfUserXfAndCreditTotal当天消费总值汇总   参考存储过程 usp_total_xfandcredit_forempUpdate
     * */
	@Override
	public Boolean doAddFillOperate(PtCardBags entity, PtUser currentUser,HttpServletRequest request) {
		Boolean flag = false;
		Date date = new Date();
		String operatorMoney =request.getParameter("operatorMoney");
		String consumeValue = request.getParameter("consumeValue");
		String consumeDate = request.getParameter("consumeDate");
		String cardNo = request.getParameter("cardNo");
		
		
		BigDecimal cardValue = new BigDecimal(operatorMoney).subtract(new BigDecimal(consumeValue));
		
		//   1 : XfConsumeDetail 2:PtCardBags 3: XfUserXfAndCreditTotal  
		//存储过程   XfConsumeDetail  见  [dbo].[usp_TE_ConsumeDetailXFInsert]
		String hql1 = " update PtCardBags set xfCount = xfCount+1 ,set cardValue = "+cardValue+",set xfMoneyTotal = xfMoneyTotal+"+consumeValue+""
				+ " where  userId ='"+entity.getUserId()+"'";
		Integer i= this.doExecuteCountByHql(hql1);		
		
		String hql2 = " select userId from XfUserXfAndCreditTotal where  dateDay = '"+consumeDate+"' "
				+ " and userId = '"+entity.getUserId()+"' and cardNo = '"+cardNo+"' and useType = '100' ";
		List<String> list= this.queryEntityByHql(hql2);
		if(list.size()>0){//存在该天的这条记录 追加总的消费值
		
			String updateHql = " update XfUserXfAndCreditTotal set updateTime = '"+new Date()+"', set xfMoney = xfMoney+ "+consumeValue+""
					+ "  where dateDay = '"+consumeDate+"'"
					+ " and userId = '"+entity.getUserId()+"' and cardNo = '"+cardNo+"' and useType = 100 ";
			Integer j= this.doExecuteCountByHql(updateHql);
		}else{
			XfUserXfAndCreditTotal userXfAndCreditTotal = new XfUserXfAndCreditTotal();
			userXfAndCreditTotal.setId(keyRedisService.getId(XfUserXfAndCreditTotal.ModuleType));
			userXfAndCreditTotal.setCardNo(Long.valueOf(cardNo));
			userXfAndCreditTotal.setUserId(entity.getUserId());
			userXfAndCreditTotal.setUseType(100);
			userXfAndCreditTotal.setDateDay(consumeDate);
			userXfAndCreditTotal.setCreditMoney(new BigDecimal(0.00));
			userXfAndCreditTotal.setXfMoney(new BigDecimal(consumeValue));
			userXfAndCreditTotalService.merge(userXfAndCreditTotal);
		
		}
	    if(i>0){
			flag= true;
			
		}else{
			flag= false;
		}
		return flag;
	}


}