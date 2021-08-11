/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：C3P0ExampleTest.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.c3p0;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

/**
 * C3P0ExampleTest
 * 
 * @author：youngzil@163.com
 * @2018年4月26日 下午9:16:25
 * @since 1.0
 */
public class C3P0ExampleTest {

    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://10.11.20.83:3306/test2.0";
    private static String USERNAME = "root";
    private static String PASSWORD = "root";

    private static String sql = "select count(*) as cnt from msg_alarm_config";

    public static void main(String[] args) throws SQLException {

        DataSource unpooled = DataSources.unpooledDataSource(JDBC_URL, USERNAME, PASSWORD);
        DataSource ds = DataSources.pooledDataSource(unpooled);

        /* InitialContext ctx = new InitialContext();
        ctx.rebind( jndiName, pooled );
        
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup( jndiName );*/

        Connection con = ds.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next())
            System.out.println(rs.getString(1));

    }

}
