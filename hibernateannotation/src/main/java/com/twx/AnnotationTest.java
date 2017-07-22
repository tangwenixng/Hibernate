package com.twx;

import com.twx.beans.Customer;
import com.twx.beans.MyMessage;
import com.twx.beans.Orders;
import com.twx.beans.Person;
import com.twx.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Created by twx on 2017/6/29.
 */
public class AnnotationTest {
    public static void main(String[] args) {
        //1、加载hibernate配置文件hibernat.cfg.xml
        Session session = SessionFactoryUtil.createSessionFactory().getCurrentSession();
        //2、开启事务
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("hdsjfhds");

        Orders order1 = new Orders();
        order1.setOrderNum("201706404");
        order1.setCustomer(customer);

        session.save(order1);
        //提交事务
//        transaction.commit();

        int id = order1.getId();
        System.out.println("id "+id);

//        transaction = session.beginTransaction();
        order1 = (Orders) session.get(Orders.class, id);
        transaction.commit();

        if (order1 != null) {
            System.out.println("customer: "+order1.getCustomer().getName());
            System.out.println("order num "+order1.getOrderNum());
        }
    }
}
