package com.yc.q1.base.pt.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.pt.system.dao.DataDictItemDao;
import com.yc.q1.base.pt.system.model.DataDict;
import com.yc.q1.base.pt.system.model.DataDictItem;
import com.yc.q1.base.pt.system.service.DataDictService;
import com.yc.q1.base.pt.system.service.DataDictItemService;
import com.yc.q1.base.redis.service.DicItemRedisService;
import com.zd.core.constant.StatuVeriable;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 
 * ClassName: BaseDicitemServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 数据字典项实体Service接口实现类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class DataDictItemServiceImpl extends BaseServiceImpl<DataDictItem> implements DataDictItemService {

	@Resource(name = "DataDictItemDao") // 将具体的dao注入进来
	public void setDao(BaseDao<DataDictItem> dao) {
		super.setDao(dao);
	}

	@Resource
	private DataDictService dictionaryService;

	@Resource
	private DicItemRedisService dicItemRedisService;

	@Override
	public DataDictItem doAdd(DataDictItem entity, String xm) {
		// TODO Auto-generated method stub

		// 当前节点
		entity = this.doAddEntity(entity, xm);

		if (entity != null) {
			// 删除reids中的此数据字典缓存，以至于下次请求时重新从库中获取
			DataDict baseDic = dictionaryService.get(entity.getDictId());
			
			dicItemRedisService.deleteByDicCode( baseDic.getDicCode());
			
		}
		return entity;
	}

	@Override
	public DataDictItem doUpdate(DataDictItem entity, String xm) {
		// TODO Auto-generated method stub

		// 先拿到已持久化的实体
		entity = this.doUpdateEntity(entity, xm, null);
		
		if (entity != null) {
			// 删除reids中的此数据字典缓存，以至于下次请求时重新从库中获取
			dicItemRedisService.deleteByDicCode( entity.getDicCode());
			
		}
		return entity;

	}

	@Override
	public boolean doDeleteOrRestore(String delIds, String isdelete, String xm) {
		boolean flag = this.doLogicDelOrRestore(delIds, StatuVeriable.ISDELETE,xm);
       
		if(delIds.length()>0){
			DataDictItem baseDicItem=this.get(delIds.split(",")[0]);
			if(baseDicItem!=null){
				//删除reids中的此数据字典缓存，以至于下次请求时重新从库中获取
				dicItemRedisService.deleteByDicCode( baseDicItem.getDicCode());
				
			}
		}
		
		return flag;
	}

}