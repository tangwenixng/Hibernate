package com.twx;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static  org.junit.Assert.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by twx on 2017/7/1.
 */
public class JdbcTest {
    @Test
    public void testDBCP() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BasicDataSource dataSource = (BasicDataSource) context.getBean("dataSource");
        Connection conn = dataSource.getConnection();
        if (conn != null) {
            System.out.println("获取成功");
        }else {
            System.out.println("error");
        }
    }
    @Test
    public void testJdbcTemplate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        String sql = "select * from customer where id = ?";
        jt.query(sql, new Integer[]{1}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                System.out.println(resultSet.getInt("id")+" "+resultSet.getString("name"));
            }
        });
    }

    @Test
    public void testSessionFactory() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        HibernateTemplate hibernateTemplate= (HibernateTemplate) context.getBean("hibernateTemplate");
        SessionFactory sf = hibernateTemplate.getSessionFactory();
        assertNotNull(sf);
       /* Session session = sf.getCurrentSession();
        if (session != null) {

            System.out.println("获取成功");
        } else {
            System.out.println("error");
        }*/
    }
}
