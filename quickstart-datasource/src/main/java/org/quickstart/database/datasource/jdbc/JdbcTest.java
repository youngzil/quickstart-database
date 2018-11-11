/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：ProxoolTest.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ProxoolTest
 * 
 * @author：youngzil@163.com
 * @2018年4月26日 下午10:00:03
 * @since 1.0
 */
public class JdbcTest {

    private static final String URL = "jdbc:mysql://localhost:3306/demo_jdbc";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");// 数据库的驱动
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student", "root", "root");// 连接的url

        // proxool连接方法：
        Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
        Connection con2 = DriverManager.getConnection("proxool.aaa:com.mysql.jdbc.Driver:jdbc:mysql://127.0.0.1:3306/student", "root", "root");

        // 1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        // 2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        // 3.通过数据库的连接操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select user_name,age from imooc_goddess");// 选择import java.sql.ResultSet;
        while (rs.next()) {// 如果对象中有数据，就会循环打印出来
            System.out.println(rs.getString("user_name") + "," + rs.getInt("age"));
        }

        int lineCount = stmt.executeUpdate("DELETE FROM imooc_goddess");

    }

}
