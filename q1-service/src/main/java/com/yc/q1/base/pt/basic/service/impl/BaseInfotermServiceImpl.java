package com.yc.q1.base.pt.basic.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.basic.dao.InfoTerminalDao;
import com.yc.q1.base.pt.basic.model.InfoTerminal;
import com.yc.q1.base.pt.basic.model.InfoTerminalHistory;
import com.yc.q1.base.pt.basic.service.BaseInfotermService;
import com.yc.q1.base.pt.basic.service.BaseInfotermuseService;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.model.extjs.QueryResult;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.ModelUtil;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: OaInfotermServiceImpl Function: ADD FUNCTION. Reason: ADD
 * REASON(可选). Description: 信息发布终端(OA_T_INFOTERM)实体Service接口实现类. date:
 * 2017-01-14
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class BaseInfotermServiceImpl extends BaseServiceImpl<InfoTerminal> implements BaseInfotermService {

	@Resource
	public void setOaInfotermDao(InfoTerminalDao dao) {
		this.dao = dao;
	}

	@Resource
	private BaseInfotermuseService useHistoryService;

	private static Logger logger = Logger.getLogger(BaseInfotermServiceImpl.class);

	@Override
	public QueryResult<InfoTerminal> list(Integer start, Integer limit, String sort, String filter, Boolean isDelete) {
		QueryResult<InfoTerminal> qResult = this.queryPageResult(start, limit, sort, filter, isDelete);
		return qResult;
	}

	/**
	 * 根据主键逻辑删除数据
	 * 
	 * @param ids
	 *            要删除数据的主键
	 * @param currentUser
	 *            当前操作的用户
	 * @return 操作成功返回true，否则返回false
	 */
	@Override
	public Boolean doLogicDeleteByIds(String ids, User currentUser) {
		Boolean delResult = false;
		try {
			Object[] conditionValue = ids.split(",");
			String[] propertyName = { "isUse", "updateUser", "updateTime", "roomId", "roomName", "houseNo" };
			Object[] propertyValue = { 0, currentUser.getId(), new Date(), "", "", "" };
			this.updateByProperties("id", conditionValue, propertyName, propertyValue);
			delResult = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			delResult = false;
		}
		return delResult;
	}

	/**
	 * 根据传入的实体对象更新数据库中相应的数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	@Override
	public InfoTerminal doUpdateEntity(InfoTerminal entity, User currentUser) {
		// 先拿到已持久化的实体
		InfoTerminal saveEntity = this.get(entity.getId());
		try {
			BeanUtils.copyProperties(saveEntity, entity);
			saveEntity.setUpdateTime(new Date()); // 设置修改时间
			saveEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
			entity = this.merge(saveEntity);// 执行修改方法

			return entity;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			return null;
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 将传入的实体对象持久化到数据
	 * 
	 * @param entity
	 *            传入的要更新的实体对象
	 * @param beforeNumber
	 *            批量增加的起始终端号
	 * @param termCount
	 *            批量增加的终端 个数
	 * @param currentUser
	 *            当前操作用户
	 * @return
	 */
	@Override
	public InfoTerminal doAddEntity(InfoTerminal entity, User currentUser, Integer beforeNumber, Integer termCount) {
		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("id");
		InfoTerminal saveEntity = null;
		Integer newNumber = beforeNumber;
	
		for (int i = 0; i < termCount; i++) {
			saveEntity = new InfoTerminal();
			try {
				BeanUtils.copyProperties(saveEntity, entity, excludedProp);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			saveEntity.setIsUse(false);
			saveEntity.setOrderIndex(newNumber);
			saveEntity.setTerminalNo(StringUtils.addString(newNumber.toString(), "0", 6, "L"));
			saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名

			entity = this.merge(saveEntity);// 执行修改方法
			newNumber++;
		}

		return saveEntity;
		
		/*
		 * OaInfoterm saveEntity = new OaInfoterm(); try { List<String>
		 * excludedProp = new ArrayList<>();
		 * 
		 * BeanUtils.copyProperties(saveEntity, entity,excludedProp);
		 * saveEntity.setCreateUser(currentUser.getId()); // 设置修改人的中文名 entity =
		 * this.merge(saveEntity);// 执行修改方法
		 * 
		 * return entity; } catch (IllegalAccessException e) {
		 * logger.error(e.getMessage()); return null; } catch
		 * (InvocationTargetException e) { logger.error(e.getMessage()); return
		 * null; }
		 */
	}

	@Override
	public Boolean doSetTerminal(List<InfoTerminal> terminals, String roomId, String roomName, User currentUser) {
		
		String[] propName = { "roomId", "houseNo" };

		for (InfoTerminal oaInfoterm : terminals) {
			String houseNumb = oaInfoterm.getHouseNo();
			String termId = oaInfoterm.getId();
			Object[] propValue = { roomId, houseNumb };
			InfoTerminal saveEntity = this.getByProerties(propName, propValue);
			if (ModelUtil.isNotNull(saveEntity)) {
				// 原来给此门牌分配过终端
				if (!saveEntity.getId().equals(termId)) {
					// 当前设置的和原来的不一致，需要清除原来的，再更新
					String conditionValue = saveEntity.getId();
					String[] propertyName = { "isUse", "updateUser", "updateTime", "roomId", "roomName",
							"houseNo" };
					Object[] propertyValue = { 0, currentUser.getId(), new Date(), "", "", "" };
					this.updateByProperties("id", conditionValue, propertyName, propertyValue);
					saveEntity = this.get(oaInfoterm.getId());
				}
			} else {
				// 原来没有分配，取当前设置的
				saveEntity = this.get(oaInfoterm.getId());
			}
			// OaInfoterm saveEntity = this.get(oaInfoterm.());
			saveEntity.setRoomId(roomId);
			saveEntity.setRoomName(roomName);
			saveEntity.setIsUse(true);
			saveEntity.setHouseNo(houseNumb);
			saveEntity.setUpdateTime(new Date()); // 设置修改时间
			saveEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名

			this.merge(saveEntity);// 执行修改方法

			// 写入分配历史记录
			InfoTerminalHistory useHistory = new InfoTerminalHistory();
			useHistory.setTerminalId(oaInfoterm.getId());
			useHistory.setTerminalNo(oaInfoterm.getTerminalNo());
			useHistory.setRoomId(roomId);
			useHistory.setRoomName(roomName);
			useHistory.setCreateUser(currentUser.getId());

			useHistoryService.persist(useHistory);
		}
		//若上面处理失败，自动跳转到异常处理程序，中断此方法代码运行
		return true;		
	}
}