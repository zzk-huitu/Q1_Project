package com.yc.q1.core.security;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.system.PtUserService;

//@Component
public class ShiroSecurityRealm extends AuthorizingRealm {

    @Resource
	private PtUserService ptUserService;

 
    public ShiroSecurityRealm() {
        //setName("ShiroSecurityRealm"); // This name must match the name in the
                                       // SysUser class's getPrincipals() method
        //setCredentialsMatcher(new Sha256CredentialsMatcher());
    	
    	//配置认证匹配器，本项目已在xml文件中配置了
        //HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher("SHA-256");
        //credentialsMatcher.setHashIterations(1);	//一次加密， 推荐1024次
        //setCredentialsMatcher(credentialsMatcher);
    }
    
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
    	
		
		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) authcToken;
		
		//2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();
		
		//3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		PtUser user = ptUserService.getByProerties("userName", username);
		  
		//4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if(user==null){
			throw new UnknownAccountException("用户不存在!");
		}
		
		//5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常. 
		if(user.getState()==false){
			throw new LockedAccountException("用户被锁定！");
		}
		
		//6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		//以下信息是从数据库中获取的.
		//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
		Object principal = user.getId();
		//2). credentials: 密码. 
		Object credentials = user.getUserPwd();
		//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		
		//4). 盐值. （由于存在在数据库进行插入密码的方式，所以我们这里就不使用盐值加密方式了）
		//ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		
		//各类型密文值的测试代码
		//Object result = new SimpleHash("MD5", "123456", ByteSource.Util.bytes("user"), 1024);
		//Object result2= new SimpleHash("MD5", "123456",null, 1024);
		//Object result3= new Sha256Hash("123456",null,1);
		//Object result4= new Sha256Hash("123456",credentialsSalt, 1024);
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);	//正常加密
		//info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);	//盐值加密
		
		return info;
    }
    
    
    //此处代码进行鉴权时，才会使用(利用动态的过滤链来完成请求地址需要的角色权限配置：FilterChainDefinitionMapBuilder)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	System.out.println("-----------AuthorizationInfo-------------");
    	//1. 从 PrincipalCollection 中来获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		
		//2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)){
			roles.add("admin");
		}
		
		//3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		//4. 返回 SimpleAuthorizationInfo 对象. 
		return info;	  
    }
    
 

}