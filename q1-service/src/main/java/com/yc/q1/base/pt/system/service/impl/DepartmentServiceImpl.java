package com.yc.q1.base.pt.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yc.q1.base.pt.basic.model.BaseCourse;
import com.yc.q1.base.pt.basic.model.Grade;
import com.yc.q1.base.pt.basic.model.GradeClass;
import com.yc.q1.base.pt.basic.service.BaseCourseService;
import com.yc.q1.base.pt.basic.service.GradeService;
import com.yc.q1.base.pt.basic.service.GradeClassService;
import com.yc.q1.base.pt.pojo.CommTree;
import com.yc.q1.base.pt.pojo.DepartmentTree;
import com.yc.q1.base.pt.system.dao.DepartmentDao;
import com.yc.q1.base.pt.system.model.Department;
import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.pt.system.service.DepartmentService;
import com.yc.q1.base.pt.system.service.UserService;
import com.yc.q1.base.redis.service.DeptRedisService;
import com.zd.core.constant.Constant;
import com.zd.core.constant.TreeVeriable;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;
import com.zd.core.util.BeanUtils;
import com.zd.core.util.ModelUtil;
import com.zd.core.util.StringUtils;

/**
 * 
 * ClassName: BaseOrgServiceImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: BASE_T_ORG实体Service接口实现类. date: 2016-07-26
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

	@Resource(name = "DepartmentDao") // 将具体的dao注入进来
	public void setDao(BaseDao<Department> dao) {
		super.setDao(dao);
	}
	
	@Autowired
	private  HttpServletRequest request;
	
	@Resource
	private DeptRedisService deptRedisService;
	
	@Resource
	private GradeService gradeService; // 年级的service

	@Resource
	private GradeClassService classService; // 班级的service


	@Resource
	private BaseCourseService courseService; // 基础学科service
	
	@Resource
	private UserService sysUserService; // 人员service

	@Override
	public List<DepartmentTree> getOrgTreeList(String whereSql, String orderSql, User currentUser) {

		
		String hql = " from Department where isDelete = 0 order by parentNode,orderIndex";
		List<DepartmentTree> result = new ArrayList<DepartmentTree>();
		List<Department> lists = this.queryByHql(hql);
		// for (BaseOrg baseOrg : rightDept) {
		// maps.put(baseOrg.getId(), baseOrg);
		// }
		// 构建Tree数据
		createChild(new DepartmentTree(TreeVeriable.ROOT, new ArrayList<DepartmentTree>()), result, lists);

		return result;
	}

	private void createChild(DepartmentTree parentNode, List<DepartmentTree> result, List<Department> list) {
		
		List<Department> childs = new ArrayList<Department>();
		String isRight = "1";
		for (Department org : list) {
			if (org.getParentNode().equals(parentNode.getId())) {
				childs.add(org);
			}
		}
		// public BaseOrgTree(String id, String text, String iconCls, Boolean
		// leaf, Integer level, String treeid,
		// List<BaseOrgTree> children, String mainLeader, String viceLeader,
		// String outPhone, String inPhone,
		// String fax, Integer isSystem, String remark, String code, String
		// parent, Integer orderIndex,
		// String deptType, String deptTypeName, String mainLeaderName, String
		// viceLeaderName)
		for (Department org : childs) {
			// if (maps.get(org.getId()) == null) {
			// isRight = "1";
			// } else {
			isRight = "0";
			// }
			DepartmentTree child = new DepartmentTree(org.getId(), org.getNodeText(), "", org.getLeaf(), org.getNodeLevel(),
					org.getTreeIds(),org.getParentNode(),org.getOrderIndex(), new ArrayList<DepartmentTree>(),
					org.getOutPhone(), org.getInPhone(), org.getFax(), org.getIsSystem(), org.getRemark(),
					org.getNodeCode(), org.getDeptType(), org.getParentType(),
					org.getMainLeaderName(), org.getSuperJob(),org.getSuperJobName(), 
					isRight,org.getGrade(),org.getSectionCode());

			if (org.getParentNode().equals(TreeVeriable.ROOT)) {
				result.add(child);
			} else {
				List<DepartmentTree> trees = parentNode.getChildren();
				trees.add(child);
				parentNode.setChildren(trees);
			}
			createChild(child, result, list);
		}
	}

	@Override
	public String delOrg(String delIds, User currentUser) {
		// 删除班级
		//boolean flag = gradeService.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE,currentUser.getId());
		// 删除年级
		//flag = classService.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE,currentUser.getId());
		// 删除部门
		//flag = this.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE,currentUser.getId());

		// 检查删除的部门的上级部门是否还有子部门
		// 如果没有子部门了要设置上级部门为叶节点
	
		String[] delUuid = delIds.split(",");
		for (String id : delUuid) {
			Department org = this.get(id);
			String hql = "select count(*) from Department where parentNode='" + org.getParentNode() + "' and isDelete=0";
			Integer count = this.getQueryCountByHql(hql);
			if (count.equals(0)) {
				Department parentOrg = this.get(org.getParentNode());
				parentOrg.setLeaf(true);
				parentOrg.setUpdateUser(currentUser.getId());
				parentOrg.setUpdateTime(new Date());
				this.merge(parentOrg);
			}
			String deptType = org.getDeptType();
			List<User> deptUser = sysUserService.getUserByDeptId(id);
			if (deptUser != null && deptUser.size() > 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return org.getNodeText() + "部门下存在人员,请删除后重试";
			}
			switch (deptType) {
			case "04": // 年级
				Grade grade = gradeService.get(id);
				hql = "from GradeClass where gradeId='" + id + "'";
				List<GradeClass> gradeclasses = classService.queryByHql(hql);
				// 检查年级下的班级是否存在人员
				for (GradeClass jwTGradeclass : gradeclasses) {
					hql = "select count(*) from ClassStudent where isDelete=0 and classId='" + jwTGradeclass.getId()
							+ "'";
					count = this.getQueryCountByHql(hql);
					if (count > 0) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return jwTGradeclass.getClassName() + "班级下存在人员,请删除后重试";
					}
					//JwClassRoomAllot 已经不存在

				/*	hql = "select count(*) from JwClassRoomAllot where isDelete=0 and claiId='"
							+ jwTGradeclass.getId() + "'";
					count = this.getQueryCountByHql(hql);
					if (count > 0) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return jwTGradeclass.getClassName() + "班级下存在教室,请删除后重试";
					}*/

					hql = "select count(*) from ClassDormAllot where isDelete=0 and classId='"
							+ jwTGradeclass.getId() + "'";
					count = this.getQueryCountByHql(hql);
					if (count > 0) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return jwTGradeclass.getClassName() + "班级下存在宿舍,请删除后重试";
					}
				/*	hql = "select count(*) from Term where isDelete=0 and roomId in("
							+ "select roomId from JwClassRoomAllot where isDelete=0 and claiId='"
							+ jwTGradeclass.getId() + "')";
					count = this.getQueryCountByHql(hql);
					if (count > 0) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return jwTGradeclass.getClassName() + "班级下存在设备,请删除后重试";
					}*/
					hql = "select count(*) from Term where isDelete=0 and roomId in("
							+ "select dormId from ClassDormAllot where isDelete=0 and classId='"
							+ jwTGradeclass.getId() + "')";
					count = this.getQueryCountByHql(hql);
					if (count > 0) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return jwTGradeclass.getClassName() + "班级下存在设备,请删除后重试";
					}
				}
				for (GradeClass jwTGradeclass : gradeclasses) {
					jwTGradeclass.setIsDelete(1);
					classService.merge(jwTGradeclass);
				}
				grade.setIsDelete(1);
				gradeService.merge(grade);
				break;
			case "05": // 班级
				GradeClass jwTGradeclass = classService.get(id);
				hql = "select count(*) from ClassStudent where isDelete=0 and classId='" + jwTGradeclass.getId()
						+ "'";
				count = this.getQueryCountByHql(hql);
				if (count > 0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return jwTGradeclass.getClassName() + "班级下存在人员,请删除后重试";
				}
				/*hql = "select count(*) from JwClassRoomAllot where isDelete=0 and claiId='" + jwTGradeclass.getId()
						+ "'";
				count = this.getQueryCountByHql(hql);
				if (count > 0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return jwTGradeclass.getClassName() + "班级下存在教室,请删除后重试";
				}*/
				hql = "select count(*) from ClassDormAllot where isDelete=0 and classId='" + jwTGradeclass.getId()
						+ "'";
				count = this.getQueryCountByHql(hql);
				if (count > 0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return jwTGradeclass.getClassName() + "班级下存在宿舍,请删除后重试";
				}

				/*hql = "select count(*) from PtTerm where isDelete=0 and roomId in("
						+ "select roomId from JwClassRoomAllot where isDelete=0 and claiId='" + jwTGradeclass.getId()
						+ "')";
				count = this.getQueryCountByHql(hql);
				if (count > 0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return jwTGradeclass.getClassName() + "班级下存在设备,请删除后重试";
				}*/
				hql = "select count(*) from Term where isDelete=0 and roomId in("
						+ "select dormId from ClassDormAllot where isDelete=0 and classId='" + jwTGradeclass.getId()
						+ "')";
				count = this.getQueryCountByHql(hql);
				if (count > 0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return jwTGradeclass.getClassName() + "班级下存在设备,请删除后重试";
				}
				jwTGradeclass.setIsDelete(1);
				classService.merge(jwTGradeclass);
				break;
			default:
				break;
			}
			org.setIsDelete(1);
			this.merge(org);
		}
		
		//删除所有redis部门缓存数据，以免产生误会
		deptRedisService.deleteDeptTreeAll();
				
		return "1";
	}

	/**
	 * 
	 * TODO 增加新部门,当增加的部门是年级或班级时，需要将数据同步至年级信息表和班级信息表.
	 * 关于添加学科：预先添加好课程信息，然后再添加学科时，才能把课程绑定到学科中。
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * 
	 * @see com.yc.q1.base.pt.system.service.DepartmentService#addOrg(com.zd.school.plartform.baseset.model.BaseOrg,
	 *      com.zd.school.plartform.system.model.SysUser)
	 */
	@Override
	public Department addOrg(Department entity, User currentUser)
			throws IllegalAccessException, InvocationTargetException {
		String parentNode = entity.getParentNode();
		String parentName = entity.getParentName();
		String nodeText = entity.getNodeText();
		Integer orderIndex = entity.getOrderIndex();
		String deptType = entity.getDeptType();
		String parentType = entity.getParentType();

		// 插入部门数据
		// BaseOrg saveEntity = null;
		String courseId = null;
		if (deptType.equals("06")) {
			BaseCourse course = courseService.getByProerties("courseName", nodeText);
			courseId = course.getId();
		}

		Department saveEntity = new Department();
		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("id");
		BeanUtils.copyProperties(saveEntity, entity,excludedProp);	
		
		saveEntity.setCreateUser(currentUser.getId()); // 创建人
		saveEntity.setLeaf(true);
		saveEntity.setIsSystem(false);
//		saveEntity.setExtField01(courseId); // 对于部门是学科时，绑定已有学科对应的ID
		saveEntity.setCourseId(courseId);
		
		if (!parentNode.equals(TreeVeriable.ROOT)) {
			Department parEntity = this.get(parentNode);
			parEntity.setLeaf(false);
			this.merge(parEntity);
			saveEntity.BuildNode(parEntity);		
			saveEntity.setAllDeptName(parEntity.getAllDeptName() + "/" + nodeText);			
		} else
			saveEntity.BuildNode(null);
		
		
		//添加副ID
		//查询当前部门下最大的副ID,以及父Id
		//暂时不编写了，因为比较麻烦，还是让用户手动点击同步比较可靠
//		BaseOrg fuIds=this.getCurrentFuId(parentNode);
//		saveEntity.setExtField04(fuIds.getExtField04());
//		saveEntity.setExtField05(fuIds.getExtField05());
		
		// 持久化到数据库
		entity = this.merge(saveEntity);
		
		
		// 插入年级数据或班级数据
		String orgId = entity.getId();
		switch (deptType) {
		case "04": // 年级
			Grade grade = new Grade(orgId);
			grade.setGradeName(entity.getNodeText());
			grade.setCreateUser(currentUser.getId());
			grade.setOrderIndex(entity.getOrderIndex());
			grade.setIsDelete(0);
			grade.setNj(entity.getGrade());
			grade.setSectionCode(entity.getSectionCode());
			grade.setGradeCode(entity.getSectionCode() + entity.getGrade());
			gradeService.merge(grade);
			break;
		case "05": // 班级
			Grade gradea=gradeService.get(parentNode);
			GradeClass gradeclass = new GradeClass(orgId);
			gradeclass.setClassName(entity.getNodeText());
			gradeclass.setOrderIndex(entity.getOrderIndex());
			gradeclass.setIsDelete(0);
			gradeclass.setGradeId(parentNode);
			gradeclass.setCreateUser(currentUser.getId());
			gradeclass.setGradeCode(gradea.getGradeCode());
			classService.merge(gradeclass);
			break;
		default:
			break;
		}
		
		//删除所有redis部门缓存数据，以免产生误会
		deptRedisService.deleteDeptTreeAll();
		return entity;
	}

	@Override
	public List<Department> getOrgList(String whereSql, String orderSql, User currentUser) {

		StringBuffer hql = new StringBuffer(" from Department where 1=1 and isDelete=0 ");
		hql.append(whereSql);
		hql.append(orderSql);
		List<Department> lists = this.queryByHql(hql.toString());// 执行查询方法

		// 当前登录人的部门信息
		// String deptId = currentUser.getDeptId();
		// String treeIds = "";
		// if (!deptId.equals("ROOT")) {
		// BaseOrg selfDept = this.get(deptId);
		// treeIds = selfDept.getTreeIds(); //当前部门的树形结点ID
		// }
		// //获取当前登录用户的部门范围权限
		// String rightType = "2";
		// String[] propName = { "userId", "entityName", "displayMode" };
		// String[] propValue = { currentUser.getId(), "BaseOrg", "0" };
		// SysDatapermission datapermission =
		// dataPeimissService.getByProerties(propName, propValue);
		// if (ModelUtil.isNotNull(datapermission)) {
		// rightType = datapermission.getRightType();
		// }
		//
		// String sql = "";
		// List<Object[]> addList = new ArrayList<>();
		// //需要添加的权限过滤条件
		// List<Object> filterList = new ArrayList<Object>();
		// //最后要组装的查询语句
		// StringBuffer hql = new StringBuffer(" from BaseOrg where 1=1 ");
		// switch (rightType) {
		// case "0":
		// //全部数据
		// break;
		// case "1":
		// //本单元数据
		// filterList.add(deptId);
		// break;
		// case "2":
		// //本单元及子单元数据
		// sql = "select DEPT_ID from BASE_T_ORG WHERE ISDELETE=0 AND TREE_IDS
		// like '" + treeIds + "%'";
		// addList = this.queryObjectBySql(sql);
		// filterList.addAll(addList);
		// break;
		// case "3":
		// //指定的数据
		// break;
		// default:
		// break;
		// }
		// List<BaseOrg> lists = new ArrayList<BaseOrg>();
		// if (filterList.size() > 0) {
		// if (StringUtils.isNotEmpty(whereSql)) {
		// hql.append(whereSql);
		// }
		// hql.append(" and uuid in(:depts)");
		// if (StringUtils.isNotEmpty(orderSql)) {
		// hql.append(orderSql);
		// }
		// lists = this.queryByHql(hql.toString(), "depts", filterList.toArray());
		// } else {
		// if (StringUtils.isNotEmpty(whereSql)) {
		// hql.append(whereSql);
		// }
		// if (StringUtils.isNotEmpty(orderSql)) {
		// hql.append(orderSql);
		// }
		// lists = this.queryByHql(hql.toString());// 执行查询方法
		// }

		return lists;
	}

	@Override
	public List<Department> getOrgAndChildList(String deptId, String orderSql, User currentUser, Boolean isRight) {

		String treeIds = "";
		String sql = "";
		StringBuffer rightDeptIds = new StringBuffer();
		Department selfDept = this.get(deptId);
		List<Department> rightList = new ArrayList<Department>();
		List<Department> reList = new ArrayList<Department>();
		if (ModelUtil.isNotNull(selfDept)) {
			treeIds = selfDept.getTreeIds();
			sql = " from Department WHERE isDelete=0 AND treeIds like '" + treeIds + "%'";
		} else {
			sql = " from Department WHERE isDelete=0 ";
		}
		if (isRight) {
			rightList = this.getOrgList("", "", currentUser);
			List<Object> filterList = new ArrayList<Object>();
			if (rightList.size() > 0) {
				for (Department bg : rightList) {
					filterList.add(bg.getId());
				}
				sql += " and id in (:depts)";
				reList = this.queryByHql(sql, "depts", filterList.toArray());
			}
		} else {
			reList = this.queryByHql(sql);
		}
		// List<BaseOrg> reList = this.queryEntityBySql(sql);
		return reList;
	}

	@Override
	public Integer getChildCount(String deptId) {
		String hql = " select count(*) from Department where isDelete=0 and parentNode='" + deptId + "'";
		Integer childCount = this.getQueryCountByHql(hql);
		// TODO Auto-generated method stub
		return childCount;
	}

	@Override
	public List<DepartmentTree> getOrgTreeList(String whereSql, String orderSql, String deptId, User currentUser) {

		// 先查询出当前用户有权限的部门数据
		List<Department> listOrg = this.getOrgList(whereSql, orderSql, currentUser);
		// 根据部门数据生成带checkbox的树

		List<DepartmentTree> result = new ArrayList<DepartmentTree>();
		if (deptId.equals(TreeVeriable.ROOT)) {
			createChild(new DepartmentTree(TreeVeriable.ROOT, new ArrayList<DepartmentTree>()), result, listOrg);
		}else{//当传进来的根节点不是root时 ，是部门id时
			createDeptChildTree(new DepartmentTree(deptId, new ArrayList<DepartmentTree>()), result, listOrg,deptId);
		}
	
		
		return result;
	}

	private void createDeptChildTree(DepartmentTree parentNode, List<DepartmentTree> result, List<Department> list,String deptId) {
		List<Department> childs = new ArrayList<Department>();
		for (Department org : list) {
			if (org.getId().equals(deptId)) {
				childs.add(org);
				continue;
			}else{
				if (org.getParentNode().equals(parentNode.getId())) {
					childs.add(org);
				}
			}
		}
		
		for (Department org : childs) {
			DepartmentTree child = new DepartmentTree(org.getId(), org.getNodeText(), "", org.getLeaf(), org.getNodeLevel(),
					org.getTreeIds(),org.getParentNode(),org.getOrderIndex(), new ArrayList<DepartmentTree>(),
					org.getOutPhone(), org.getInPhone(), org.getFax(), org.getIsSystem(), org.getRemark(),
					org.getNodeCode(), org.getDeptType(), org.getParentType(),
					org.getMainLeaderName(), org.getSuperJob(),org.getSuperJobName(), 
					"0",org.getGrade(),org.getSectionCode());
			
			if (org.getId().equals(deptId)) {
				result.add(child);
			} else {
				List<DepartmentTree> trees = parentNode.getChildren();
				trees.add(child);
				parentNode.setChildren(trees);
			}
			createDeptChildTree(child, result, list,"");
		}
	}
	@Override
	public String getOrgChildIds(String orgId, boolean isSelf) {
		String treeIds = "";
		String hql = "";
		StringBuffer sbOrgIds = new StringBuffer();
		Department selfDept = this.get(orgId);

		if (ModelUtil.isNotNull(selfDept)) {
			treeIds = selfDept.getTreeIds();
			hql = " from Department WHERE isDelete=0 AND treeIds like '" + treeIds + "%'";
		} else {
			hql = " from Department WHERE isDelete=0 ";
		}
		List<Department> reList = this.queryByHql(hql);
		if (!isSelf) {
			reList.remove(selfDept);
		}
		for (Department baseOrg : reList) {
			sbOrgIds.append(baseOrg.getId() + ",");
		}

		if (sbOrgIds.length() > 0)
			treeIds = StringUtils.trimLast(sbOrgIds.toString());
		else
			treeIds = "";
		// TODO Auto-generated method stub
		return treeIds;
	}

	@Override
	public Map<String, Department> getOrgChildMaps(String orgId, boolean isSelf) {
		String treeIds = "";
		String hql = "";
		StringBuffer sbOrgIds = new StringBuffer();
		Department selfDept = this.get(orgId);
		Map<String, Department> maps = new HashMap<String, Department>();
		if (ModelUtil.isNotNull(selfDept)) {
			treeIds = selfDept.getTreeIds();
			hql = " from Department WHERE isDelete=0 AND treeIds like '" + treeIds + "%'";
		} else {
			hql = " from Department WHERE isDelete=0 ";
		}
		List<Department> reList = this.queryByHql(hql);
		if (!isSelf) {
			reList.remove(selfDept);
		}
		for (Department baseOrg : reList) {
			maps.put(baseOrg.getId(), baseOrg);
		}
		// TODO Auto-generated method stub
		return maps;
	}



	@Override
	public Department doUpdate(Department entity, String userId) {
		String parentNode = entity.getParentNode();	
		String nodeText = entity.getNodeText();
		String uuid = entity.getId();
		String deptType = entity.getDeptType();
		
		// 先拿到已持久化的实体
		Department perEntity = this.get(uuid);
		Boolean isLeaf = perEntity.getLeaf();
		String oldDeptName = perEntity.getNodeText();
		//String OldParentNode=perEntity.getParentNode();
		
		// 将entity中不为空的字段动态加入到perEntity中去。
		try {
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(userId); // 设置修改人的id
		perEntity.setLeaf(isLeaf);


		// 更新父节点的是否叶节点的标记
		Department parentOrg = this.get(parentNode);
		if(parentOrg!=null){
			parentOrg.setUpdateTime(new Date()); // 设置修改时间
			parentOrg.setUpdateUser(userId); // 设置修改人的中文名
			parentOrg.setLeaf(false);
			this.merge(parentOrg);// 执行修改方法
			
			perEntity.BuildNode(parentOrg);
			perEntity.setAllDeptName(parentOrg.getAllDeptName()+"/"+nodeText);
		}
		
		//当父部门发生变化时,就更新副ID
		//查询当前部门下最大的副ID,以及父Id
		//暂时不编写了，因为比较麻烦，还是让用户手动点击同步比较可靠
//		if(!OldParentNode.equals(parentNode)){
//			BaseOrg fuIds=this.getCurrentFuId(parentNode);
//			perEntity.setExtField04(fuIds.getExtField04());
//			perEntity.setExtField05(fuIds.getExtField05());
//		}				
		entity = this.merge(perEntity);// 执行修改方法
		
		if (deptType.equals("04")) { // 年级
			Grade grade = gradeService.get(uuid);
			grade.setGradeName(nodeText);
			grade.setUpdateUser(userId);
			grade.setOrderIndex(entity.getOrderIndex());
			grade.setIsDelete(0);
			grade.setNj(entity.getGrade());
			grade.setSectionCode(entity.getSectionCode());
			grade.setGradeCode(entity.getSectionCode()+entity.getGrade());
			gradeService.merge(grade);
		} else if (deptType.equals("05")) { // 班级
			GradeClass gradeclass = classService.get(uuid);
			gradeclass.setClassName(nodeText);
			gradeclass.setUpdateUser(userId);
			gradeclass.setOrderIndex(entity.getOrderIndex());
			gradeclass.setIsDelete(0);
			gradeclass.setGradeId(parentNode);
			classService.merge(gradeclass);
		}
		
		if(!oldDeptName.equals(nodeText)){
			//再更新其他地方的名称		
			this.setDeptName(nodeText,uuid);
			if(parentOrg!=null&&!parentNode.equals("ROOT"))
				this.setChildAllDeptName(entity,parentOrg.getAllDeptName());
			else
				this.setChildAllDeptName(entity,"ROOT");
		}
		
		//删除所有redis部门缓存数据，以免产生误会
		deptRedisService.deleteDeptTreeAll();
				
		return entity;
	}

	@Override
	public Integer getDeptJobCount(String uuid) {
		String hql = " select count(*) from DeptJob where isDelete=0 and (deptId='" + uuid + "' or parentDeptId='"+uuid+"')";
		Integer childCount = this.getQueryCountByHql(hql);
		// TODO Auto-generated method stub
		return childCount;
	}
	
	@Override
	public void setDeptName(String deptName,String uuid){	
		String updateHql1="update Department a set a.superdeptName='"+deptName+"' where a.superDept='"+uuid+"'";
		String updateHql2="update DeptJob a set a.deptName='"+deptName+"' where a.deptId='"+uuid+"'";
		String updateHql3="update DeptJob a set a.parentDeptName='"+deptName +"' where a.parentDeptId='"+uuid+"'";
		this.doExecuteCountByHql(updateHql1);
		this.doExecuteCountByHql(updateHql2);
		this.doExecuteCountByHql(updateHql3);	
	}
	@Override
	public void setChildAllDeptName(Department dept,String parentAllDeptName){	
		//1.设置当前类的全部门名
		String currentAllName="";
		if(!"ROOT".equals(parentAllDeptName))
			currentAllName=parentAllDeptName+"/"+dept.getNodeText();
		else
			currentAllName=dept.getNodeText();
		
		dept.setAllDeptName(currentAllName);	
		this.merge(dept);
		
		//2.设置相应的部门岗位的部门全名
		String updateHql="update DeptJob a set a.allDeptName='"+currentAllName +"' where a.deptId='"+dept.getId()+"'";
		this.doExecuteCountByHql(updateHql);	
		
		//3.递归遍历设置子部门的全部门名
		List<Department> childDepts = this.queryByProerties(new String[]{"isDelete","parentNode"}, new Object[]{0,dept.getId()});
		for(int i=0;i<childDepts.size();i++){
			this.setChildAllDeptName(childDepts.get(i),currentAllName);
		}
		
	}


	
	
	/*临时弃用
	//获取当前的副Id
	private BaseOrg getCurrentFuId(String parentNode){
		BaseOrg result=new BaseOrg();
		
		//1.先查询此同级部门下是否存在部门
		String hql="from BaseOrg where parentNode='"+parentNode+"' and isDelete=0 order by EXT_FIELD04 desc";
		List<BaseOrg> baseOrgs=this.queryByHql(hql, 0, 1);
		
		//2.若不存在，则直接查询副部门，
		if(baseOrgs.size()==0){
			BaseOrg baseOrg=this.get(parentNode);	
			result.setExtField05(baseOrg.getExtField04());
			result.setExtField04(baseOrg.getExtField04()+"001");
		}else{
			result.setExtField05(baseOrgs.get(0).getExtField05());
			Long temp=Long.parseLong(baseOrgs.get(0).getExtField04())+1;
			result.setExtField04(temp.toString());
		}
		
		return result;
	}
	*/
	/**
	 * 新版本不需要副Id了
	 */
//	@Override
//	public void doCreateFuId() {
//		// TODO Auto-generated method stub
//		//1.查询第一层的部门
//		String hql="from Department where nodeLevel=1 and isDelete=0 order by orderIndex asc,nodeText asc";
//		List<Department>  lists = this.queryByHql(hql);
//		Department temp=null;
//		long initValue=100;
//		for(int i=0;i<lists.size();i++){
//			temp=lists.get(i);
//			temp.setExtField05("001");
//			
//			long fuId=initValue+i+1;
//			temp.setExtField04(String.valueOf(fuId));
//			this.merge(temp);
//			
//			//递归查询子部门
//			this.createChildFuId(temp.getId(), fuId*1000, String.valueOf(fuId));
//			
//		}
//		
//	}
//	
//	private void createChildFuId(String parentNodeId,long initValue,String parentFuId){
//		String hql="from Department where parentNode='"+parentNodeId+"' and isDelete=0 order by orderIndex asc,nodeText asc";
//		List<Department>  lists = this.queryByHql(hql);
//		Department temp=null;
//		for(int i=0;i<lists.size();i++){
//			temp=lists.get(i);
//			temp.setExtField05(parentFuId);
//			
//			long fuId=initValue+i+1;
//			temp.setExtField04(String.valueOf(fuId));
//			this.merge(temp);
//			
//			//递归查询子部门
//			this.createChildFuId(temp.getId(), fuId*1000, String.valueOf(fuId));		
//		}
//	}
	

	@Override
	public List<Department> getUserRightDeptList(User currentUser) {
		String userId = currentUser.getId();
		String rightType = currentUser.getRightType();
		String hql = "";
		List<Department> list = new ArrayList<>();
		if ("0".equals(rightType )) {
			// 有所有部门权限
			hql = " from Department WHERE isDelete=0 order by parentNode,orderIndex asc ";
			list = this.queryByHql(hql);

			return list;
		} else {
			// 指定部门、所在部门及主管的部门
			String sql = MessageFormat.format(
					"SELECT DEPT_ID ,CREATE_TIME ,CREATE_USER ,EXT_FIELD01 ,EXT_FIELD02 ,EXT_FIELD03 ,EXT_FIELD04 ,EXT_FIELD05 ,ISDELETE ,ORDER_INDEX ,UPDATE_TIME ,UPDATE_USER ,VERSION ,ISLEAF ,NODE_CODE ,NODE_LEVEL ,NODE_TEXT ,PARENT_NODE ,TREE_IDS ,DEPT_TYPE ,FAX ,IN_PHONE ,ISSYSTEM ,MAIN_LEADER ,OUT_PHONE ,REMARK ,VICE_LEADER ,SUPER_JOB ,SUPER_DEPT ,ALL_DEPTNAME ,SUPERDEPT_NAME ,SUPERJOB_NAME FROM V_PT_UserRightDept WHERE USER_ID=''{0}'' ORDER BY PARENT_NODE,ORDER_INDEX ASC",
					userId);
			List<?> alist = this.querySql(sql);
			Department dept = null;
			Integer length = alist.size();
			for (int i = 0; i < length; i++) {
				Object[] obj = (Object[]) alist.get(i);
				dept = new Department();
				dept.setId((String) obj[0]);
				dept.setIsDelete((Integer) obj[8]);
				dept.setOrderIndex((Integer) obj[9]);
				dept.setVersion((Integer) obj[12]);
				dept.setLeaf((Boolean) obj[13]);
				dept.setNodeLevel((Integer) obj[15]);
				dept.setNodeText((String) obj[16]);
				dept.setParentNode((String) obj[17]);
				dept.setTreeIds((String) obj[18]);
				dept.setDeptType((String) obj[19]);
				dept.setFax((String) obj[20]);
				dept.setInPhone((String) obj[21]);
				dept.setIsSystem((Boolean) obj[22]);
				dept.setMainLeaderName((String) obj[23]);
				dept.setOutPhone((String) obj[24]);
				dept.setRemark((String) obj[25]);
//				dept.setViceLeader((String) obj[26]);
				dept.setSuperJob((String) obj[27]);
				dept.setSuperDept((String) obj[28]);
				dept.setAllDeptName((String) obj[29]);
				dept.setSuperDeptName((String) obj[30]);
				dept.setSuperJobName((String) obj[31]);

				list.add(dept);
			}
			return list;
		}
	}
	
	
	@Transactional(readOnly=true)
	@Override
	public DepartmentTree getUserRightDeptTree(User currentUser, String rootId) {
		//1.查询部门的数据，并封装到实体类中
		List<DepartmentTree> list = getUserRightDeptTreeList(currentUser);
		
		//2.找到根节点
		DepartmentTree root = new DepartmentTree();
		for (DepartmentTree node : list) {			
			//if (!(StringUtils.isNotEmpty(node.getParent()) && !node.getId().equals(rootId))) {
			if ( StringUtils.isEmpty(node.getParent()) || node.getId().equals(rootId)) {
				root = node;
				list.remove(node);
				break;
			}
		}
		
		//3.递归组装children
		createTreeChildren(list, root);
		return root;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<DepartmentTree> getUserRightDeptTreeList(User currentUser) {
		String userId = currentUser.getId();
		String rightType = currentUser.getRightType();
		
		
		//先从redis中取数据	
		Object userRightDeptTree = deptRedisService.getRightDeptTreeByUser(userId);
		if (userRightDeptTree != null) { // 若存在,则直接返回redis数据
			return (List<DepartmentTree>)userRightDeptTree;	
		}
		
		//若当前用户是超级管理员或学校管理员，那就直接查询所有部门
		Integer isAdmin=(Integer)request.getSession().getAttribute(Constant.SESSION_SYS_ISADMIN);
		Integer isSchoolAdmin = (Integer) request.getSession().getAttribute(Constant.SESSION_SYS_ISSCHOOLADMIN);
		if(isAdmin==1||isSchoolAdmin==1)
			rightType="0";
		
		
		String sql = MessageFormat.format("EXECUTE SYS_P_GETUSERRIGHTDEPTTREE ''{0}'',{1}", userId, rightType);
		List<DepartmentTree> chilrens = new ArrayList<DepartmentTree>();
		List<?> alist = this.queryObjectBySql(sql);
		for (int i = 0; i < alist.size(); i++) {
			Object[] obj = (Object[]) alist.get(i);
			DepartmentTree node = new DepartmentTree();
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
			node.setDeptType((String) obj[8]);		
			node.setMainLeaderName((String) obj[9]);
			node.setOutPhone((String) obj[10]);
			node.setRemark((String) obj[11]);
			//node.setViceLeader((String) obj[12]);
			node.setSuperDept((String) obj[13]);
			node.setSuperJob((String) obj[14]);
			node.setAllDeptName((String) obj[15]);
			node.setSuperdeptName((String) obj[16]);
			node.setSuperjobName((String) obj[17]);
			node.setIsRight((String) obj[18]);
			node.setChecked(false);
			chilrens.add(node);
		}
		
		//若不存在，则存入到redis中
		deptRedisService.setRightDeptTreeByUser(userId, chilrens);
		
		return chilrens;
	}
	
	private void createTreeChildren(List<DepartmentTree> childrens, DepartmentTree root) {
		String parentId = root.getId();
		for (int i = 0; i < childrens.size(); i++) {
			DepartmentTree node = childrens.get(i);
			if (StringUtils.isNotEmpty(node.getParent()) && node.getParent().equals(parentId)) {
				root.getChildren().add(node);
				createTreeChildren(childrens, node);
			}
			if (i == childrens.size() - 1) {
				if (root.getChildren().size() < 1) {
					root.setLeaf(true);
				}
				return;
			}
		}
	}
	
	
	/**
	 * 获取用户有权限的部门班级树
	 */
	@Transactional(readOnly=true)
	@Override
	public List<CommTree> getUserRightDeptClassTreeList(User currentUser) {
				
		String userId = currentUser.getId();
		String rightType = currentUser.getRightType();
		
		//先从redis中取数据
		Object userRightDeptClassTree = deptRedisService.getRightDeptClassTreeByUser(userId);
		if (userRightDeptClassTree != null) { // 若存在,则直接返回redis数据
			return (List<CommTree>)userRightDeptClassTree;
		}
		
		//若当前用户是超级管理员或学校管理员，那就直接查询所有部门
		Integer isAdmin=(Integer)request.getSession().getAttribute(Constant.SESSION_SYS_ISADMIN);
		Integer isSchoolAdmin = (Integer) request.getSession().getAttribute(Constant.SESSION_SYS_ISSCHOOLADMIN);
		if(isAdmin==1||isSchoolAdmin==1)
			rightType="0";
		
		String sql = MessageFormat.format("EXECUTE SYS_P_GETUSERRIGHTGRADCLASSTREE ''{0}'',{1},''{2}''", userId,
				rightType, "05");
		
		
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
			node.setChecked(false);
			chilrens.add(node);
		}
		
		//若不存在，则存入到redis中
		deptRedisService.setRightDeptClassTreeByUser(userId, chilrens);
		return chilrens;
	}
	
	/**
	 * 获取用户有权限的部门学科树
	 */
	@Transactional(readOnly=true)
	@Override
	public List<CommTree> getUserRightDeptDisciplineTreeList(User currentUser) {
		String userId = currentUser.getId();
		String rightType = currentUser.getRightType();
		
		//先从redis中取数据
		Object userRightDeptDisciplineTree = deptRedisService.getRightDeptDisciplineTreeByUser(userId); 	
		if (userRightDeptDisciplineTree != null) { // 若存在,则直接返回redis数据
			return (List<CommTree>)userRightDeptDisciplineTree;
		}
		
		//若当前用户是超级管理员或学校管理员，那就直接查询所有部门
		Integer isAdmin=(Integer)request.getSession().getAttribute(Constant.SESSION_SYS_ISADMIN);
		Integer isSchoolAdmin = (Integer) request.getSession().getAttribute(Constant.SESSION_SYS_ISSCHOOLADMIN);
		if(isAdmin==1||isSchoolAdmin==1)
			rightType="0";
		
		String sql = MessageFormat.format("EXECUTE SYS_P_GETUSERRIGHTGRADCLASSTREE ''{0}'',{1},''{2}''", userId,
				rightType, "06");	//06是学科
		
		
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
			node.setChecked(false);
			chilrens.add(node);
		}
		
		//若不存在，则存入到redis中
		deptRedisService.setRightDeptDisciplineTreeByUser(userId, chilrens);
		return chilrens;
	}
	

	
}