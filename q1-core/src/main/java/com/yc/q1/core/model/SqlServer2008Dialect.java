package com.yc.q1.core.model;

import java.sql.Types;

import org.hibernate.dialect.SQLServer2008Dialect;

public class SqlServer2008Dialect extends SQLServer2008Dialect{
	public SqlServer2008Dialect() {  
        super();  
        registerHibernateType(Types.CHAR, "string");  
        registerHibernateType(Types.NVARCHAR, "string");  
        registerHibernateType(Types.LONGNVARCHAR, "string");
    }  
}
