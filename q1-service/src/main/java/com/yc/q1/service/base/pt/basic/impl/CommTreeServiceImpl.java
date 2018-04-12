package com.yc.q1.service.base.pt.basic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.pojo.base.pt.CommBase;
import com.yc.q1.pojo.base.pt.CommTree;
import com.yc.q1.service.base.pt.basic.CommTreeService;
import com.zd.core.constant.TreeVeriable;
import com.zd.core.dao.BaseDao;
import com.zd.core.model.BaseEntity;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: FacultyClassitemServiceImpl Function: TODO ADD FUNCTION. Reason:
 * TODO ADD REASON(可选). Description: 数据字典项实体Service接口实现类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class CommTreeServiceImpl extends BaseServiceImpl<BaseEntity> implements CommTreeService {

    @Resource(name = "CommTreeDao") // 将具体的dao注入进来
	public void setDao(BaseDao<BaseEntity> dao) {
		super.setDao(dao);
	}


    @Override
    public List<CommTree> getCommTree(String treeView, String whereSql) {

        String sql = "select id,text,iconCls,leaf,level,parent from " + treeView + " where 1=1 " + whereSql;

        List<CommBase> lists = this.queryEntityBySql(sql, CommBase.class);

        List<CommTree> result = new ArrayList<CommTree>();

        // 构建Tree数据
        createTreeChild(new CommTree(TreeVeriable.ROOT, new ArrayList<CommTree>()), result, lists);

        return result;
    }

    public void createTreeChild(CommTree parentNode, List<CommTree> result, List<CommBase> list) {
        List<CommBase> childs = new ArrayList<CommBase>();
        for (CommBase dic : list) {
            if (dic.getParent().equals(parentNode.getId())) {
                childs.add(dic);
            }
        }

        for (CommBase fc : childs) {
            CommTree child = new CommTree(fc.getId(), fc.getText(), fc.getIconCls(), Boolean.parseBoolean(fc.getLeaf()),
                    fc.getLevel(), "", fc.getParent(),0, new ArrayList<CommTree>());

            if (fc.getParent().equals(TreeVeriable.ROOT)) {
                result.add(child);
            } else {
                List<CommTree> trees = parentNode.getChildren();
                trees.add(child);
                parentNode.setChildren(trees);
            }
            createTreeChild(child, result, list);
        }
    }

  
    
	@Override
	public CommTree getGradeCommTree(String sql, String rootId) {
		List<CommTree> list = this.getCommTreeList(sql);
		CommTree root = new CommTree();
		for (CommTree node : list) {
			if (!(StringUtils.isNotEmpty(node.getParent()) && !node.getId().equals(rootId))) {
				root = node;
				list.remove(node);
				break;
			}
		}
		createGradeTreeChildren(list, root);
		return root;
	}
	
	@Override
	public List<CommTree> getCommTreeList(String sql) {
		List<CommTree> chilrens = new ArrayList<CommTree>();
		CommTree node = null;
		List<Object[]> alist = this.queryObjectBySql(sql);

		for (int i = 0; i < alist.size(); i++) {
			Object[] obj = (Object[]) alist.get(i);
			node = new CommTree();
			node.setId((String) obj[0]);
			node.setText((String) obj[1]);
			node.setIconCls((String) obj[2]);

			if ((Boolean) obj[3]) {
				node.setLeaf(true);
			} else {
				node.setLeaf(false);
			}
			node.setLevel((Integer) obj[4]);
			node.setTreeid((String) obj[5]);
			node.setParent((String) obj[6]);
			node.setOrderIndex((Integer) obj[7]);
			node.setNodeType((String) obj[8]);
			//node.setChecked(false);
			chilrens.add(node);
		}
		return chilrens;
	}
	private void createGradeTreeChildren(List<CommTree> childrens, CommTree root) {
		String parentId = root.getId();
		for (int i = 0; i < childrens.size(); i++) {
			CommTree node = childrens.get(i);
			if (StringUtils.isNotEmpty(node.getParent()) && node.getParent().equals(parentId)) {
				root.getChildren().add(node);
				createGradeTreeChildren(childrens, node);
			}
			if (i == childrens.size() - 1) {
				if (root.getChildren().size() < 1) {
					root.setLeaf(true);
				}
				return;
			}
		}
	}
	
}