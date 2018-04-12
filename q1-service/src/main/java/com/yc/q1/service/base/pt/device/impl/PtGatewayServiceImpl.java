package com.yc.q1.service.base.pt.device.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.core.util.TLVUtils;
import com.yc.q1.model.base.pt.device.PtGateway;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.TLVModel;
import com.yc.q1.service.base.pt.device.PtGatewayService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 网关表
 * 
 * @author hucy
 *
 */
@Service
@Transactional
public class PtGatewayServiceImpl extends BaseServiceImpl<PtGateway> implements PtGatewayService {

	private static Logger logger = Logger.getLogger(PtGatewayServiceImpl.class);

	@Resource(name = "ptGatewayDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtGateway> dao) {
		super.setDao(dao);
	}

	@Resource
	private PrimaryKeyRedisService keyRedisService;

	@Override
	public PtGateway doUpdateEntity(PtGateway entity, PtUser currentUser) {
		// 先拿到已持久化的实体
		PtGateway perEntity = this.get(entity.getId());
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
	public PtGateway doAddEntity(PtGateway entity, PtUser currentUser) {
		try {
			Integer orderIndex = this.getDefaultOrderIndex(entity);
			PtGateway perEntity = new PtGateway();
			perEntity.setCreateUser(currentUser.getId());
			perEntity.setOrderIndex(orderIndex);
			// perEntity.setPriceValue(entity.getPriceValue());
			// perEntity.setPriceStatus(entity.getPriceStatus());
			BeanUtils.copyPropertiesExceptNull(entity, perEntity);
			
			// 持久化到数据库
			entity.setId(keyRedisService.getId(PtGateway.ModuleType));	//手动设置id
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

	/**
	 * 设置网关参数 若设置失败，自动进入异常捕获返回出错信息。
	 */
	@Override
	public void doSetGatewayParam(HttpServletRequest request, TLVModel tlvs, String userCh) {
		// TODO Auto-generated method stub
		byte[] result = null;
		PtGateway perEntity = this.get(tlvs.getId());
		result = TLVUtils.encode(tlvs.getTlvs());
		perEntity.setNetParam(result);
		perEntity.setGatewayIP(request.getParameter("gatewayIp"));
		perEntity.setNetGatewayIp(request.getParameter("netGatewayIp"));
		perEntity.setGatewayMask(request.getParameter("gatewayMask"));
		perEntity.setGatewayMac(request.getParameter("gatewayMac"));

		// 不能更改服务器参数
		// perEntity.setFrontServerIP(request.getParameter("frontServerIP"));
		// perEntity.setFrontServerPort(Integer.parseInt(request.getParameter("frontServerPort")));
		// perEntity.setFrontServerStatus(Integer.parseInt(request.getParameter("frontServerStatus")));

		perEntity.setUpdateUser(userCh);
		perEntity.setUpdateTime(new Date());
		this.merge(perEntity);
	}

	/**
	 * 处理单个网关数据
	 */
	@Override
	public void doUpdateBaseHighParam(TLVModel tlvs, String xm) {
		// TODO Auto-generated method stub

		byte[] baseResult = null;
		baseResult = TLVUtils.encode(tlvs.getTlvs().subList(0, 2));
		byte[] advResult = null;
		advResult = TLVUtils.encode(tlvs.getTlvs().subList(2, 3));

		PtGateway perEntity = this.get(tlvs.getId());

		// 将entity中不为空的字段动态加入到perEntity中去。
		perEntity.setUpdateUser(xm);
		perEntity.setUpdateTime(new Date());
		perEntity.setBaseParam(baseResult);
		perEntity.setAdvParam(advResult);
		this.merge(perEntity);// 执行修改方法
	}

	/**
	 * 批量处理勾选的网关列表
	 */
	@Override
	public void doUpdateBaseHighParamToIds(TLVModel tlvs, String gatewayIds, String xm) {
		if (StringUtils.isEmpty(gatewayIds)) {
			gatewayIds = tlvs.getId();
		} else if (!gatewayIds.contains(tlvs.getId())) {
			gatewayIds = tlvs.getId() + "," + gatewayIds;
		}

		byte[] baseResult = TLVUtils.encode(tlvs.getTlvs().subList(0, 2));
		byte[] advResult = TLVUtils.encode(tlvs.getTlvs().subList(2, 3));

		String hql = "update Gateway a set a.baseParam = ?,a.advParam=?,a.updateTime=?,a.updateUser=? where a.id in ('"
				+ gatewayIds.replace(",", "','") + "')";
		Query query = this.getSession().createQuery(hql);
		query.setBinary(0, baseResult);
		query.setBinary(1, advResult);
		query.setDate(2, new Date());
		query.setString(3, xm);
		query.executeUpdate();

		// for(int ){
		// // TODO Auto-generated method stub
		// PtGateway perEntity = this.get(tlvs.getId());
		//
		// // 将entity中不为空的字段动态加入到perEntity中去。
		// perEntity.setUpdateUser(xm);
		// perEntity.setUpdateTime(new Date());
		//
		//
		// perEntity.setBaseParam(baseResult);
		// perEntity.setAdvParam(advResult);
		// this.merge(perEntity);// 执行修改方法
		// }

	}

	/**
	 * 批量处理当前服务器下所有网关数据
	 */
	@Override
	public void doUpdateBaseHighParamToAll(TLVModel tlvs, String xm) {
		// TODO Auto-generated method stub
		PtGateway perEntity = this.get(tlvs.getId());
		String frontServerId = perEntity.getFrontServerId();

		byte[] baseResult = TLVUtils.encode(tlvs.getTlvs().subList(0, 2));
		byte[] advResult = TLVUtils.encode(tlvs.getTlvs().subList(2, 3));

		String hql = "update Gateway a set a.baseParam = ?,a.advParam=?,a.updateTime=?,a.updateUser=? where a.isDelete=0 and a.frontServerId=?";
		Query query = this.getSession().createQuery(hql);
		query.setBinary(0, baseResult);
		query.setBinary(1, advResult);
		query.setDate(2, new Date());
		query.setString(3, xm);
		query.setString(4, frontServerId);
		query.executeUpdate();
	}

	/**
	 * 批量设置前置服务器
	 */
	@Override
	public void doUpdateBatchFront(PtGateway entity, String xm) {
		// TODO Auto-generated method stub
		String uuids[] = entity.getId().split(",");

		String hql = "update Gateway a set a.frontServerId = ?,a.updateTime=?,a.updateUser=? where a.id in (:ids)";
		Query query = this.getSession().createQuery(hql);
		query.setString(0, entity.getFrontServerId());
		query.setDate(1, new Date());
		query.setString(2, xm);
		query.setParameterList("ids", uuids);
		query.executeUpdate();

		// PtGateway ptGateway=null;
		// for (int i = 0; i < uuids.length; i++) {
		// ptGateway=this.get(uuid[i]);
		// ptGateway.setFrontserverId(entity.getFrontserverId());
		// ptGateway.setUpdateUser(xm);
		// ptGateway.setUpdateTime(new Date());
		// this.merge(ptGateway);
		// }
	}
}
