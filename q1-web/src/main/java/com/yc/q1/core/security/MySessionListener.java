package com.yc.q1.core.security;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.storage.log.LogUserLogin;
import com.yc.q1.service.storage.log.LogUserLoginService;

public class MySessionListener implements SessionListener { 
	
	@Resource
	private LogUserLoginService sysUserLoginLogService;
	    
	
    @Override  
    public void onStart(Session session) {//会话创建时触发  
        //System.out.println("会话创建：" + session.getId());  当用户未登录前，此会话id就已生成
    }  
    @Override  
    public void onExpiration(Session session) {//会话过期时触发 
    	//System.out.println("会话过期：" + session.getId());  
    	 
        PtUser sysuser = (PtUser) session.getAttribute("SESSION_SYS_USER");
        // session.getAttribute("kickout"));  
        
        String userId=sysuser.getId();
        String sessionId=(String) session.getId();
     
        String hql="from LogUserLogin o where o.userId=? and o.sessionId=? and o.isDelete=0 order by createTime desc";
    	LogUserLogin loginLog = sysUserLoginLogService.getEntityByHql(hql, userId,sessionId);
    	if(loginLog!=null){
    		loginLog.setLastAccessDate(session.getLastAccessTime());
    		loginLog.setOfflineDate(new Date());
    		loginLog.setOfflineIntro("超时退出");
    		sysUserLoginLogService.persist(loginLog);
    	}    	  
    }  
    @Override  
    public void onStop(Session session) {//退出/会话过期时触发    
        //System.out.println("会话停止：" + session.getId());  
             
        PtUser sysuser = (PtUser) session.getAttribute("SESSION_SYS_USER");
              
        String userId=sysuser.getId();
        String sessionId=(String) session.getId();
     
        String hql="from LogUserLogin o where o.userId=? and o.sessionId=? and o.isDelete=0 order by createTime desc";
        LogUserLogin loginLog = sysUserLoginLogService.getEntityByHql(hql, userId,sessionId);
    	if(loginLog!=null){
    		loginLog.setLastAccessDate(session.getLastAccessTime());
    		loginLog.setOfflineDate(new Date());
    		
    		if(session.getAttribute("kickout")!=null)
    			loginLog.setOfflineIntro("异地登录退出");
    		else 
    			loginLog.setOfflineIntro("手动退出");

    		sysUserLoginLogService.persist(loginLog);
    	}   
    }    
}  