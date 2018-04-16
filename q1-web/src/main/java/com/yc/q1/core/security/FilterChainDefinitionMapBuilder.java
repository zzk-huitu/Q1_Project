package com.yc.q1.core.security;

import java.util.LinkedHashMap;

/**
 * 动态的设置shiro请求路径的鉴权值（暂时不使用）
 * @author ZZK
 *
 */
public class FilterChainDefinitionMapBuilder {
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
		/*此处数据可从数据库中查询出来，然后组装到各个接口的角色和功能权限*/
		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/user.jsp", "authc,roles[user]");
		map.put("/admin.jsp", "authc,roles[admin]");
		map.put("/list.jsp", "user");

		map.put("/**", "authc");
		
		return map;
	}
}

/**
 * xml中配置方式：
 *  
 *  <!-- 1.配置一个 bean, 该 bean 实际上是一个 Map. 通过实例工厂方法的方式 -->
    <bean id="filterChainDefinitionMap" 
    	factory-bean="filterChainDefinitionMapBuilder" factory-method="buildFilterChainDefinitionMap"></bean>
    
    <bean id="filterChainDefinitionMapBuilder"
    	class="com.atguigu.shiro.factory.FilterChainDefinitionMapBuilder"></bean>
    	
 *  <!-- 2.在shiroFilter配置中使用 -->
 *  <property name="filterChainDefinitionMap" ref="filterChainDefinitionMap"></property>
 *  
 */
