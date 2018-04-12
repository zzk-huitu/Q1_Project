package com.yc.q1.core.util;

import org.hibernate.SessionFactory;

public interface DynamicSessionFactory extends SessionFactory {
    
    public SessionFactory getHibernateSessionFactory();
}
 