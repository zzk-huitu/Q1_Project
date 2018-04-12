package com.yc.q1.service.base.pt.system.impl;

import java.text.MessageFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.constant.StatuVeriable;
import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.system.PtRole;
import com.yc.q1.service.base.pt.system.PtRoleService;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BaseTRoleServiceImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 角色管理实体Service接口实现类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtRoleServiceImpl extends BaseServiceImpl<PtRole> implements PtRoleService {

	@Resource(name = "PtRoleDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtRole> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	@Resource
	private PtUserService ptUserService;

	@Override
	public boolean doDelete(String delIds, String isdelete, String xm) {
		// TODO Auto-generated method stub

		// 先调用删除用户菜单数据的方法
		String[] roleIds = delIds.split(",");
		ptUserService.deleteUserMenuTreeRedis(roleIds);

		// 再设置逻辑删除
		boolean flag = this.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE, xm);

		return flag;
	}

	// @Autowired
	// SysRoleDao sysRoleDao;
	//
	// public List<SysRole> doQueryForIn(String hql, Integer start, Integer
	// limit,Object[] objs){
	//
	// return sysRoleDao.doQueryForIn(hql, start, limit, objs);
	// }

	@Override
	public Boolean doDeleteRoleUser(String ids, String userId) {
		String[] userIds = userId.split(",");
		String temp = userId.replace(",", "','");
		String sql = " delete from T_PT_RoleUser where roleId=''{0}'' and userId in (''{1}'')";
		sql = MessageFormat.format(sql, ids, temp);
		Integer executeCount = this.doExecuteCountBySql(sql);
		if (executeCount > 0) {
			/* 删除用户的redis数据，以至于下次刷新或请求时，可以加载最新数据 */
			ptUserService.deleteUserRoleRedis(userIds);
			return true;
		} else
			return false;

	}

	@Override
	public Boolean doAddRoleUser(String ids, String userId) {
		String[] userIds = userId.split(",");
		StringBuilder sb = new StringBuilder();
		String sql = " insert into T_PT_RoleUser(roleId,userId) values(''{0}'',''{1}'') ";
		int userCount = userIds.length;
		for (int i = 0; i < userCount; i++) {
			sb.append(MessageFormat.format(sql, ids, userIds[i]));
		}
		Integer executeCount = this.doExecuteCountBySql(sb.toString());

		if (executeCount > 0) {
			/* 删除用户的redis数据，以至于下次刷新或请求时，可以加载最新数据 */
			ptUserService.deleteUserRoleRedis(userIds);
			return true;
		} else
			return false;

	}

	@Override
	public Boolean doDeleteRole(String ids, Map hashMap, String xm) {
		Boolean flag = false;
		String[] delIds = ids.split(",");
		PtRole sysrole = null;
		StringBuffer roleName = new StringBuffer();
		for (int i = 0; i < delIds.length; i++) {
			// 判断这些角色是否正在被其他用户使用
			String hql = "select u from User as u inner join fetch u.sysRoles as r where r.id='" + delIds[i]
					+ "' and r.isDelete=0 and u.isDelete=0 ";
			int count = ptUserService.queryByHql(hql).size();
			if (count > 0) {// 该角色关联着用户
				sysrole = this.get(delIds[i]);
				roleName.append(sysrole.getRoleName() + ",");
				flag = false;
				hashMap.put("flag", flag);
				continue;
			} else {
				flag = this.doDelete(delIds[i], StatuVeriable.ISDELETE, xm);
				if (!flag) {
					hashMap.put("sign", "1");
				}
			}
		}
		hashMap.put("roleName", roleName);
		return flag;
	}
}