package com.yc.q1.base.pt.build.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.build.dao.RoomAreaDao;
import com.yc.q1.base.pt.build.model.RoomArea;
import com.yc.q1.base.pt.build.service.RoomAreaService;
import com.yc.q1.base.pt.pojo.RoomAreaTree;
import com.zd.core.constant.TreeVeriable;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.StringUtils;

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
public class RoomAreaServiceImpl extends BaseServiceImpl<RoomArea> implements RoomAreaService {

	@Resource(name = "RoomAreaDao") // 将具体的dao注入进来
	public void setDao(BaseDao<RoomArea> dao) {
		super.setDao(dao);
	}

	public List<RoomAreaTree> getBuildAreaList(String whereSql) {

		String hql = "from RoomArea where 1=1 ";
		if (StringUtils.isNotEmpty(whereSql))
			hql += whereSql;
		hql += " order by orderIndex asc ";
		List<RoomArea> lists = this.queryByHql(hql);// 执行查询方法
		List<RoomAreaTree> result = new ArrayList<RoomAreaTree>();

		// 构建Tree数据
		createChild(new RoomAreaTree(TreeVeriable.ROOT, new ArrayList<RoomAreaTree>()), result, lists);

		return result;
	}

	private void createChild(RoomAreaTree parentNode, List<RoomAreaTree> result, List<RoomArea> list) {
		List<RoomArea> childs = new ArrayList<RoomArea>();
		for (RoomArea dic : list) {
			if (dic.getParentNode().equals(parentNode.getId())) {
				childs.add(dic);
			}
		}
		// public BuildRoomAreaTree(String id, String text, String iconCls,
		// Boolean leaf, Integer level, String treeid,
		// List<BuildRoomAreaTree> children, String areaCode, String areaType,
		// Integer areaStatu, String areaDesc, String areaAddr,
		// String parentArea, Integer orderIndex, Integer roomCount) {

		for (RoomArea dic : childs) {
			RoomAreaTree child = new RoomAreaTree(dic.getId(), dic.getNodeText(), "", dic.getLeaf(),
					dic.getNodeLevel(), dic.getTreeIds(), new ArrayList<RoomAreaTree>(), dic.getAreaCode(),
					dic.getAreaType(),  dic.getAreaExplain(), dic.getAreaAddress(), dic.getParentNode(),
					dic.getOrderIndex(), dic.getRoomCount());

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
	public RoomArea doAddEntity(RoomArea entity, String operator) {
		// TODO Auto-generated method stub
		String parentNode = entity.getParentNode();
		RoomArea perEntity = new RoomArea();
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
			RoomArea parEntity = this.get(parentNode);
			parEntity.setLeaf(false);
			this.merge(parEntity);
			perEntity.BuildNode(parEntity);
		} else
			perEntity.BuildNode(null);

		perEntity = this.merge(perEntity);

//		perEntity.setParentName(parentName);
//		perEntity.setAreaType(parentType);
//		perEntity.setParentNode(parentNode);

		return perEntity;
	}
	
	@Override
	public RoomArea doUpdateEntity(RoomArea entity, String operator, List<String> excludedProp) {
		// TODO Auto-generated method stub

        //先拿到已持久化的实体
        //entity.getSchoolId()要自己修改成对应的获取主键的方法
		RoomArea perEntity = this.get(entity.getId());
        Boolean isLeaf = perEntity.getLeaf();
        //将entity中不为空的字段动态加入到perEntity中去。
        try {
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        perEntity.setUpdateTime(new Date()); //设置修改时间
        perEntity.setUpdateUser(operator); //设置修改人的中文名
        perEntity.setLeaf(isLeaf);
        perEntity = this.merge(perEntity);//执行修改方法

        return perEntity;
	}
}
