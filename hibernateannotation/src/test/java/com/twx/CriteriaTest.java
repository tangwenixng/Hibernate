package com.twx;

import com.twx.beans.Customer;
import com.twx.beans.Orders;
import com.twx.util.SessionFactoryUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * Created by twx on 2017/6/29.
 */

public class CriteriaTest {

    @Test
    public void testRestriction() {
        //1、加载hibernate配置文件hibernat.cfg.xml
        Session session = SessionFactoryUtil.createSessionFactory().getCurrentSession();
        //2、开启事务
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Customer.class);
//        criteria.add(Restrictions.eq("id", 2));
//        criteria.setFirstResult(3);
//        criteria.setMaxResults(1);
        criteria.addOrder(Order.desc("id"));

        List<Customer> list = criteria.list();
        for (Customer customer: list)
            System.out.println("Customer name--》》》》》 "+customer.getName());
        tx.commit();
    }

    @Test
    public void testHQL() {
        //1、加载hibernate配置文件hibernat.cfg.xml
        Session session = SessionFactoryUtil.createSessionFactory().getCurrentSession();
        //2、开启事务
        Transaction tx = session.beginTransaction();
        String hql = "delete from Orders where id = :Id";

        Query query = session.createQuery(hql);
        query.setInteger("Id", 3);
        query.executeUpdate();
//        List<Object[]> list = query.list();
//        for (Object[] line: list)
//            System.out.println("Customer name--》》》》》 "+line[1]+" id---->>>>>"+line[0]);
        tx.commit();
    }

}
