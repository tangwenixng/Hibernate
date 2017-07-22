package com.twx.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.twx.po.Students;

public class StudentsTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		Configuration configure = new Configuration().configure();
		ServiceRegistry buildServiceRegistry = new ServiceRegistryBuilder().applySettings(configure.getProperties()).buildServiceRegistry();
		sessionFactory = configure.buildSessionFactory(buildServiceRegistry);
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	@Test
	public void testStudents(){
		Students s = new Students(1,"twx","ÄÐ",new Date());
		session.save(s);
	}
	@After
	public void destroy(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
