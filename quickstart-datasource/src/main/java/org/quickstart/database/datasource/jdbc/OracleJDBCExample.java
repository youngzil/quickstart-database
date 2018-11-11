/**
 * 项目名称：quickstart-datasource 
 * 文件名：OracleJDBCExample.java
 * 版本信息：
 * 日期：2018年10月15日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * OracleJDBCExample
 * 
 * @author：youngzil@163.com
 * @2018年10月15日 下午8:55:11
 * @since 1.0
 */
public class OracleJDBCExample {

    public static void main(String[] argv) throws ClassNotFoundException {

        // 方法一：注册驱动程序最常见的方法是使用Java的Class.forName()方法，将驱动程序的类文件动态加载到内存中，并将其自动注册。
        System.out.println("-------- Oracle JDBC Connection Testing ------");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        }

        // 使用getInstance()方法来解决不合规的JVM，但是必须编写两个额外的异常，如下所示：
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            // System.exit(1);
        } catch (IllegalAccessException ex) {
            System.out.println("Error: access problem while loading!");
            // System.exit(2);
        } catch (InstantiationException ex) {
            System.out.println("Error: unable to instantiate driver!");
            // System.exit(3);
        }

        // 第二种方法是使用静态DriverManager.registerDriver()方法来注册驱动程序。如果使用的是非JDK兼容的JVM(如Microsoft提供的)，则应使用registerDriver()方法。
        try {
            Driver myDriver = new oracle.jdbc.driver.OracleDriver();
            DriverManager.registerDriver(myDriver);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Oracle JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");
            // 使用具有用户名和密码的数据库URL
            // connection = DriverManager.getConnection("jdbc:oracle:thin:username/password@192.168.0.10:1521:EMP");

            String URL = "jdbc:oracle:thin:@amrood:1521:EMP";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "password12321");
            // connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", info);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}
