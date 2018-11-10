package org.quickstart.druid.druid.test;

import java.sql.SQLException;

import org.quickstart.druid.druid.model.TableOperator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDruidTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        TableOperator test = (TableOperator) beanFactory.getBean("BigTableTestBean");
        try {
            test.createTable();
            test.insert();

            System.out.println("测试成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                test.tearDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
