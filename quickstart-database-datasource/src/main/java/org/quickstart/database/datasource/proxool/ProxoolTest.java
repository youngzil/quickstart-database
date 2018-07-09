/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：ProxoolTest.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.proxool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ProxoolTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月26日 下午10:00:03
 * @since 1.0
 */
public class ProxoolTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //
        // Class.forName("com.mysql.jdbc.Driver");// 数据库的驱动
        // Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student", "root", "root");// 连接的url

        // proxool连接方法：
        Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
        Connection con2 = DriverManager.getConnection("proxool.aaa:com.mysql.jdbc.Driver:jdbc:mysql://127.0.0.1:3306/student", "root", "root");

    }

}
