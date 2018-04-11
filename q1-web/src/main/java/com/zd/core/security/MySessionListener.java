package com.zd.core.security;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.yc.q1.base.log.service.UserLoginLogService;
import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.model.storage.log.UserLoginLog;

public class MySessionListener implements SessionListener { 
	
	@Resource
	private UserLoginLogService sysUserLoginLogService;
	    
	
    @Override  
    public void onStart(Session session) {//会话创建时触发  
        //System.out.println("会话创建：" + session.getId());  
    }  
    @Override  
    public void onExpiration(Session session) {//会话过期时触发 
    	//System.out.println("会话过期：" + session.getId());  
    	 
        User sysuser = (User) session.getAttribute("SESSION_SYS_USER");
        // session.getAttribute("kickout"));  
        
        String userId=sysuser.getId();
        String sessionId=(String) session.getId();
     
        String hql="from SysUserLoginLog o where o.userId=? and o.sessionId=? and o.isDelete=0 order by createTime desc";
    	UserLoginLog loginLog = sysUserLoginLogService.getEntityByHql(hql, userId,sessionId);
    	if(loginLog!=null){
    		loginLog.setLastAccessDate(session.getLastAccessTime());
    		loginLog.setOfflineDate(new Date());
    		loginLog.setOfflineIntro("超时退出");
    		sysUserLoginLogService.merge(loginLog);
    	}    	  
    }  
    @Override  
    public void onStop(Session session) {//退出/会话过期时触发    
        //System.out.println("会话停止：" + session.getId());  
             
        User sysuser = (User) session.getAttribute("SESSION_SYS_USER");
              
        String userId=sysuser.getId();
        String sessionId=(String) session.getId();
     
        String hql="from SysUserLoginLog o where o.userId=? and o.sessionId=? and o.isDelete=0 order by createTime desc";
    	UserLoginLog loginLog = sysUserLoginLogService.getEntityByHql(hql, userId,sessionId);
    	if(loginLog!=null){
    		loginLog.setLastAccessDate(session.getLastAccessTime());
    		loginLog.setOfflineDate(new Date());
    		
    		if(session.getAttribute("kickout")!=null)
    			loginLog.setOfflineIntro("异地登录退出");
    		else 
    			loginLog.setOfflineIntro("手动退出");

    		sysUserLoginLogService.merge(loginLog);
    	}   
    }    
}  