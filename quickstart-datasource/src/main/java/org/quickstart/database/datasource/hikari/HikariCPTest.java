/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：HikariCPTest.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.hikari;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * HikariCPTest
 * 
 * @author：youngzil@163.com
 * @2018年4月26日 下午4:44:03
 * @since 1.0
 */
public class HikariCPTest {

    private static String sql = "select count(*) as cnt from msg_alarm_config";
    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://10.11.20.83:3306/msgframe2.0";
    // private static String DRIVER_NAME = "org.trafodion.jdbc.t4.T4Driver";
    // private static String JDBC_URL = "jdbc:t4jdbc://192.168.0.16:23400/:";
    private static String USERNAME = "root";
    private static String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {
        // create a connection pool. The pool usually is a global variable.
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(60000);
        config.setDriverClassName(DRIVER_NAME);
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // config.setConnectionTestQuery("values(1)");
        HikariDataSource pool = new HikariDataSource(config);
        // get a connection from connection pool
        Connection conn = pool.getConnection();
        // execute query
        // PreparedStatement ps = conn.prepareStatement(sql);
        // ResultSet rs = ps.executeQuery();

        Statement statement = conn.createStatement();
        PreparedStatement pStatement = conn.prepareStatement(sql);
        CallableStatement cStatement = conn.prepareCall(sql);

        ResultSet rs = pStatement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("cnt"));
        }

        // conn will return back to pool
        conn.close();

    }

}
