

package com.yc.q1.base.log.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yc.q1.base.log.dao.OprateLogDao;
import com.yc.q1.base.log.model.OprateLog;
import com.yc.q1.base.log.service.SysOperateLogService;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class SysOperateLogServiceImpl extends BaseServiceImpl<OprateLog> implements SysOperateLogService {

    @Resource
    public void setSysOperateLogDao(OprateLogDao dao) {
        this.dao = dao;
    }
	@Resource
	private RedisTemplate<String, OprateLog> redisTemplate;

	private static Logger logger = Logger.getLogger(SysOperateLogServiceImpl.class);
	
	@Override
	public void multiAddEntity(List<OprateLog> lists) {
		// TODO Auto-generated method stub
		try{	
			//如果你的 hibernate.cache.use_second_level_cache 是 true, 请在会话级别上关闭他      
		    //向（任何一级）缓存中加载大量数据通常也意味着它们很快会被清除出去，这会增加GC开销。    
			this.getSession().setCacheMode(CacheMode.IGNORE);
			OprateLog s=null;
			for(int i=0;i<lists.size();i++){
				s=lists.get(i);
				//s.setUuid(UUID.randomUUID().toString());
				//s.setVersion(0);
				this.persist(s);
				
				if ((i+1)%50 == 0) {	//每50条数据，入一次库
					this.getSession().flush();
					this.getSession().clear();
				}		
			}
		}catch(Exception e){
			logger.error("错误原因：【"+e.getMessage()+"】 出错堆栈跟踪："+ Arrays.toString( e.getStackTrace()));
			
			//当发生异常时，就中断执行了，把数据重新存入redis中
			redisTemplate.opsForList().leftPushAll("SysOperateLog", lists);		
			
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
    
    
}

