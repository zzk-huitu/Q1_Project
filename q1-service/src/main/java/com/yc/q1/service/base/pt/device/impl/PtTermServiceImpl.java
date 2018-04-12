package com.yc.q1.service.base.pt.device.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.core.util.TLVUtils;
import com.yc.q1.model.base.pt.device.PtTerm;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.CommBase;
import com.yc.q1.pojo.base.pt.TLVModel;
import com.yc.q1.service.base.pt.device.PtTermService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

@Service
@Transactional
public class PtTermServiceImpl extends BaseServiceImpl<PtTerm> implements PtTermService {

	private static Logger logger = Logger.getLogger(PtTermServiceImpl.class);

	@Resource(name = "ptTermDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtTerm> dao) {
		super.setDao(dao);
	}

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	// 已废弃
	@Override
	public void batchUpdate(int termTypeID, String termid, String areaType, String[] strings, Object[] objects) {
		PtTerm term = this.get(termid);
		String roomid = term.getRoomId();
		int area = Integer.parseInt(areaType);
		for (int level = 5; level > area; level--) {
			String sql = "select id,text,iconCls,leaf,level,parent from V_PT_AreaRoomInfoTree where id='" + roomid
					+ "'";
			List<CommBase> lists = this.queryEntityBySql(sql, CommBase.class);
			String sql2 = "select id,text,iconCls,leaf,level,parent from  V_PT_AreaRoomInfoTree where id='"
					+ lists.get(0).getParent() + "'";
			lists = this.queryEntityBySql(sql2, CommBase.class);
			roomid = lists.get(0).getId();
		}
		List<CommBase> list = null;
		list = findChildren(list, roomid);
		for (CommBase cb : list) {
			updateByProperties(new String[] { "termTypeId", "roomId" }, new Object[] { termTypeID, cb.getId() },
					strings, objects);
		}
	}

	@Override
	public PtTerm doAddEntity(PtTerm entity, PtUser currentUser) {
		try {
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			PtTerm perEntity = new PtTerm();
			perEntity.setCreateUser(currentUser.getId());
			perEntity.setOrderIndex(orderIndex);
			BeanUtils.copyPropertiesExceptNull(entity, perEntity);
			
			// 持久化到数据库
			entity.setId(keyRedisService.getId(PtTerm.ModuleType));	//手动设置id
			entity = this.merge(entity);
			return entity;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			return null;
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public PtTerm doUpdateEntity(PtTerm entity, PtUser currentUser) {
		// 先拿到已持久化的实体
		PtTerm perEntity = this.get(entity.getId());
		try {
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
			perEntity.setUpdateTime(new Date()); // 设置修改时间
			perEntity.setUpdateUser(currentUser.getId()); // 设置修改人的中文名
			entity = this.merge(perEntity);// 执行修改方法
			return entity;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			return null;
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void doUpdatHighParamToIds(TLVModel tlvs, String termIds, String xm) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUpdateHighParam(TLVModel tlvs, String xm) {
		// TODO Auto-generated method stub
		byte[] advResult = null;
		advResult = TLVUtils.encode(tlvs.getTlvs());
		PtTerm perEntity = this.get(tlvs.getId());

		// 将entity中不为空的字段动态加入到perEntity中去。
		perEntity.setUpdateUser(xm);
		perEntity.setUpdateTime(new Date());
		perEntity.setAdvParam(advResult);
		this.merge(perEntity);// 执行修改方法
	}

	@Override
	public void doBatchUpdateHighParam(TLVModel tlvs, String termTypeID, String areaType, String xm) {
		// TODO Auto-generated method stub
		String uuid = tlvs.getId();
		byte[] advResult = null;
		advResult = TLVUtils.encode(tlvs.getTlvs());

		PtTerm term = this.get(uuid);
		String roomid = term.getRoomId();
		int area = Integer.parseInt(areaType);
		// 最多5层，但也可能为4层（无校区的情况）
		for (int level = 5; level > area; level--) {
			String sql = "select id,text,iconCls,leaf,level,parent from V_PT_AreaRoomInfoTree where id='" + roomid
					+ "'";
			List<CommBase> lists = this.queryEntityBySql(sql, CommBase.class);
			// 加入判断，防止出错
			if (lists.size() > 0) {
				String sql2 = "select id,text,iconCls,leaf,level,parent from V_PT_AreaRoomInfoTree where id='"
						+ lists.get(0).getParent() + "'";
				lists = this.queryEntityBySql(sql2, CommBase.class);

				// 加入判断，防止出错
				if (lists.size() > 0) {
					roomid = lists.get(0).getId();
				} else {
					break;
				}
			}
		}

		List<CommBase> list = null;
		list = findChildren(list, roomid); // 查找此区域下的所有房间

		String[] propertyNames = new String[] { "advParam", "updateUser", "updateTime" };
		Object[] propertyValues = new Object[] { advResult, xm, new Date() };
		for (CommBase cb : list) {
			updateByProperties(new String[] { "termTypeId", "roomId" }, new Object[] { termTypeID, cb.getId() },
					propertyNames, propertyValues);
		}
	}

	public List<CommBase> findChildren(List<CommBase> list, String roomid) {
		if (list == null)
			list = new ArrayList<>();
		String sql = "select id,text,iconCls,leaf,level,parent from  V_PT_AreaRoomInfoTree where parent='" + roomid
				+ "'";
		List<CommBase> lists = this.queryEntityBySql(sql, CommBase.class);
		for (CommBase cb : lists) {
			if (cb.getLeaf().equals("true")) {
				list.add(cb);
			} else {
				findChildren(list, cb.getId());
			}
		}
		return list;
	}

	@Override
	public void doUpdateBaseParam(TLVModel tlvs, String notes, String xm) {
		// TODO Auto-generated method stub
		byte[] baseResult = null;
		baseResult = TLVUtils.encode(tlvs.getTlvs());

		PtTerm perEntity = this.get(tlvs.getId());
		// 将entity中不为空的字段动态加入到perEntity中去。
		perEntity.setUpdateUser(xm);
		perEntity.setUpdateTime(new Date());
		perEntity.setBaseParam(baseResult);
		if ("11".equals(perEntity.getTermTypeId()) || "17".equals(perEntity.getTermTypeId())) {
			perEntity.setNotes(notes);
		}
		this.merge(perEntity);// 执行修改方法
	}

	@Override
	public void doBatchUpdateBaseParam(TLVModel tlvs, String termTypeID, String notes, String areaType, String xm) {
		// TODO Auto-generated method stub
		String uuid = tlvs.getId();
		byte[] baseResult = null;
		baseResult = TLVUtils.encode(tlvs.getTlvs());

		PtTerm term = this.get(uuid);
		String roomid = term.getRoomId();
		int area = Integer.parseInt(areaType);
		// 最多5层，但也可能为4层（无校区的情况）
		for (int level = 5; level > area; level--) {
			String sql = "select id,text,iconCls,leaf,level,parent from  V_PT_AreaRoomInfoTree where id='" + roomid
					+ "'";
			List<CommBase> lists = this.queryEntityBySql(sql, CommBase.class);
			// 加入判断，防止出错
			if (lists.size() > 0) {
				String sql2 = "select id,text,iconCls,leaf,level,parent from  V_PT_AreaRoomInfoTree where id='"
						+ lists.get(0).getParent() + "'";
				lists = this.queryEntityBySql(sql2, CommBase.class);

				// 加入判断，防止出错
				if (lists.size() > 0) {
					roomid = lists.get(0).getId();
				} else {
					break;
				}
			}
		}

		List<CommBase> list = null;
		list = findChildren(list, roomid); // 查找此区域下的所有房间

		String[] propertyNames = null;
		Object[] propertyValues = null;
		if ("11".equals(termTypeID) || "17".equals(termTypeID)) {
			propertyNames = new String[] { "baseParam", "notes", "updateUser", "updateTime" };
			propertyValues = new Object[] { baseResult, notes, xm, new Date() };
		} else {
			propertyNames = new String[] { "baseParam", "updateUser", "updateTime" };
			propertyValues = new Object[] { baseResult, xm, new Date() };
		}

		for (CommBase cb : list) {
			updateByProperties(new String[] { "termTypeId", "roomId" }, new Object[] { termTypeID, cb.getId() },
					propertyNames, propertyValues);
		}
	}

	@Override
	public void doSetPtTerm(String roomId, String uuid, PtUser currentUser) {
		// TODO Auto-generated method stub
		String uuids[] = uuid.split(",");
		String roomIds[] = roomId.split(",");
		PtTerm entity = null;
		for (int i = 0; i < uuids.length; i++) {
			entity = this.get(uuids[i]);
			entity.setRoomId(roomIds[i]);
			entity.setCreateUser(currentUser.getId());
			entity.setUpdateTime(new Date());
			this.merge(entity);
			// thisService.updateByProperties("uuid", uuids[i], "roomId",
			// roomId);
		}
	}
}
