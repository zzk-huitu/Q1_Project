package com.yc.q1.base.pt.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.system.dao.DataDictDao;
import com.yc.q1.base.pt.system.service.DataDictService;
import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.model.base.pt.system.PtDataDict;
import com.yc.q1.pojo.base.pt.DataDictTree;
import com.zd.core.constant.TreeVeriable;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;

/**
 * 
 * ClassName: BaseDicServiceImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 数据字典实体Service接口实现类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class DataDictServiceImpl extends BaseServiceImpl<PtDataDict> implements DataDictService {

	@Resource(name = "DataDictDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtDataDict> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
    @Override
    public List<DataDictTree> getDicTreeList(String whereSql) {

        String hql = "from DataDict where 1=1 " + whereSql + " order by orderIndex asc ";
        List<PtDataDict> lists = this.queryByHql(hql);// 执行查询方法
        List<DataDictTree> result = new ArrayList<DataDictTree>();

        // 构建Tree数据
        createChild(new DataDictTree(TreeVeriable.ROOT, new ArrayList<DataDictTree>()), result, lists);

        return result;
    }

    private void createChild(DataDictTree parentNode, List<DataDictTree> result, List<PtDataDict> list) {
        List<PtDataDict> childs = new ArrayList<PtDataDict>();
        for (PtDataDict dic : list) {
            if (dic.getParentNode().equals(parentNode.getId())) {
                childs.add(dic);
            }
        }

        for (PtDataDict dic : childs) {
        	DataDictTree child = new DataDictTree(dic.getId(), dic.getNodeText(), "", dic.getLeaf(), dic.getNodeLevel(),
                    dic.getTreeIds(), new ArrayList<DataDictTree>(), dic.getDicCode(), dic.getDicType(), dic.getParentNode(), dic.getOrderIndex());

            if (dic.getParentNode().equals(TreeVeriable.ROOT)) {
                result.add(child);
            } else {
                List<DataDictTree> trees = parentNode.getChildren();
                trees.add(child);
                parentNode.setChildren(trees);
            }
            createChild(child, result, list);
        }
    }

	@Override
	public PtDataDict doAdd(PtDataDict entity, String xm) {
		// TODO Auto-generated method stub

        String parentNode = entity.getParentNode();
        
		 //当前节点
        PtDataDict saveEntity = new PtDataDict();
        List<String> excludedProp = new ArrayList<>();
		excludedProp.add("id");
		entity.setId(keyRedisService.getId(PtDataDict.ModuleType));
		try {
			BeanUtils.copyProperties(saveEntity, entity, excludedProp);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

        // 增加时要设置创建人
		saveEntity.setCreateUser(xm); // 创建人
		saveEntity.setLeaf(true);

        //BaseDic parEntity = thisService.get(parentNode);
        if (!parentNode.equals(TreeVeriable.ROOT)) {
        	PtDataDict parEntity = this.get(parentNode);
            parEntity.setLeaf(false);
            this.merge(parEntity);

            saveEntity.BuildNode(parEntity);
        } else
        	saveEntity.BuildNode(null);

        // 持久化到数据库
        entity = this.merge(saveEntity);
        
		return entity;
	}


}