package org.quickstart.hibernate.xml;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quickstart.hibernate.xml.model.User;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-27 16:59
 */


public class UserTest {

  private SessionFactory sessionFactory;
  private Session session;
  private Transaction transaction;

  @Before
  public void init() {
    //创建配置对象
    Configuration config = new Configuration().configure();
    //创建会话工厂对象
    sessionFactory = config.buildSessionFactory();
    //创建会话对象
    session = sessionFactory.openSession();
    //开启事务
    transaction = session.beginTransaction();
  }

  @After
  public void destory() {
    //提交事务
    transaction.commit();
    //关闭会话
    session.close();
    //关闭会话工厂
    sessionFactory.close();
  }


  @Test
  public void saveNewUser() throws Exception {
    User s = new User(1, "张三", 12, "男", new Date(), "上海市");
    session.save(s);
  }

}