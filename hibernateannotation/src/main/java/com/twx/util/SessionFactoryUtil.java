package com.twx.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by twx on 2017/6/29.
 */
public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static ThreadLocal<SessionFactory> threadLocal = new ThreadLocal<SessionFactory>();

    /**
     * hibernate4 中 buildSessionFactory()方法已经过时了，所以用这个新的api
     * @return
     */
    public static SessionFactory createSessionFactory() {
        sessionFactory = threadLocal.get();
        if (sessionFactory == null) {
            Configuration cfg = new Configuration();
            //默认加载classpath下的hibernate.cfg.xml文件，可以自己指定名称
            cfg.configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            threadLocal.set(sessionFactory);
        }
        return sessionFactory;
    }
}
