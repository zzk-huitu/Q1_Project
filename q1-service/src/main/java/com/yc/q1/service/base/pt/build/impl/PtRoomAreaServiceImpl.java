package com.yc.q1.service.base.pt.build.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.constant.TreeVeriable;
import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.build.PtRoomArea;
import com.yc.q1.pojo.base.pt.RoomAreaTree;
import com.yc.q1.service.base.pt.build.PtRoomAreaService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BuildRoomareaServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 教室区域实体Service接口实现类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtRoomAreaServiceImpl extends BaseServiceImpl<PtRoomArea> implements PtRoomAreaService {

	@Resource(name = "PtRoomAreaDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtRoomArea> dao) {
		super.setDao(dao);
	}
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	public List<RoomAreaTree> getBuildAreaList(String whereSql) {

		String hql = "from RoomArea where 1=1 ";
		if (StringUtils.isNotEmpty(whereSql))
			hql += whereSql;
		hql += " order by orderIndex asc ";
		List<PtRoomArea> lists = this.queryByHql(hql);// 执行查询方法
		List<RoomAreaTree> result = new ArrayList<RoomAreaTree>();

		// 构建Tree数据
		createChild(new RoomAreaTree(TreeVeriable.ROOT, new ArrayList<RoomAreaTree>()), result, lists);

		return result;
	}

	private void createChild(RoomAreaTree parentNode, List<RoomAreaTree> result, List<PtRoomArea> list) {
		List<PtRoomArea> childs = new ArrayList<PtRoomArea>();
		for (PtRoomArea dic : list) {
			if (dic.getParentNode().equals(parentNode.getId())) {
				childs.add(dic);
			}
		}
		// public BuildRoomAreaTree(String id, String text, String iconCls,
		// Boolean leaf, Integer level, String treeid,
		// List<BuildRoomAreaTree> children, String areaCode, String areaType,
		// Integer areaStatu, String areaDesc, String areaAddr,
		// String parentArea, Integer orderIndex, Integer roomCount) {

		for (PtRoomArea dic : childs) {
			RoomAreaTree child = new RoomAreaTree(dic.getId(), dic.getNodeText(), "", dic.getLeaf(), dic.getNodeLevel(),
					dic.getTreeIds(), new ArrayList<RoomAreaTree>(), dic.getAreaCode(), dic.getAreaType(),
					dic.getAreaExplain(), dic.getAreaAddress(), dic.getParentNode(), dic.getOrderIndex(),
					dic.getRoomCount());

			if (dic.getParentNode().equals(TreeVeriable.ROOT)) {
				result.add(child);
			} else {
				List<RoomAreaTree> trees = parentNode.getChildren();
				trees.add(child);
				parentNode.setChildren(trees);
			}
			createChild(child, result, list);
		}
	}

	public Integer getChildCount(String areaId) {

		String hql = " select count(*) from RoomArea where isDelete=0 and parentNode='" + areaId + "'";
		Integer childCount = this.getQueryCountByHql(hql);
		// TODO Auto-generated method stub
		return childCount;
	}

	@Override
	public PtRoomArea doAddEntity(PtRoomArea entity, String operator) {
		// TODO Auto-generated method stub
		String parentNode = entity.getParentNode();
		PtRoomArea perEntity = new PtRoomArea();
		List<String> exclude = new ArrayList<String>();
		exclude.add("id");
		try {
			BeanUtils.copyProperties(perEntity, entity, exclude);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		perEntity.setCreateUser(operator); // 创建人
		perEntity.setLeaf(true);
		if (!parentNode.equals(TreeVeriable.ROOT)) {
			PtRoomArea parEntity = this.get(parentNode);
			parEntity.setLeaf(false);
			this.merge(parEntity);
			perEntity.BuildNode(parEntity);
		} else
			perEntity.BuildNode(null);

		perEntity.setId(keyRedisService.getId(PtRoomArea.ModuleType));	//手动设置id
		perEntity = this.merge(perEntity);

		// perEntity.setParentName(parentName);
		// perEntity.setAreaType(parentType);
		// perEntity.setParentNode(parentNode);

		return perEntity;
	}

	@Override
	public PtRoomArea doUpdateEntity(PtRoomArea entity, String operator, List<String> excludedProp) {
		// TODO Auto-generated method stub

		// 先拿到已持久化的实体
		// entity.getSchoolId()要自己修改成对应的获取主键的方法
		PtRoomArea perEntity = this.get(entity.getId());
		Boolean isLeaf = perEntity.getLeaf();
		// 将entity中不为空的字段动态加入到perEntity中去。
		try {
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(operator); // 设置修改人的中文名
		perEntity.setLeaf(isLeaf);
		perEntity = this.merge(perEntity);// 执行修改方法

		return perEntity;
	}
}
