/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：DbcpExampleTest.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * DbcpExampleTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月26日 下午10:06:14
 * @since 1.0
 */
public class DbcpExampleTest {

    /** A dummy query for DB. */
    public static final String TEST_QUERY = "SELECT 1 FROM INFORMATION_SCHEMA.SYSTEM_USERS";

    /** Constant. */
    private static final String password = "";
    /** Constant. */
    private static final String username = "sa";
    /** Constant. */
    private static final String url = "jdbc:mock";
    /** Max connections for single. */
    private static final int MAX_CONNECTIONS = 1000000;
    /** Placeholder for all the results. */
    private static List<String> results = new LinkedList<String>();
    /** config setting. */
    public static int threads = 500;
    /** config setting. */
    public static int stepping = 20;
    /** config setting. */
    public static int pool_size = 200;
    /** config setting. */
    private int max_idle_time = 0;
    /** config setting. */
    private int idle_connection_test_period = 0;
    /** config setting. */
    private static int max_statement = 10;
    /** config setting. */
    private int acquire_increment = 5;

    public static void main(String[] args) throws SQLException {
        BasicDataSource cpds = new BasicDataSource();
        cpds.setDriverClassName("com.jolbox.bonecp.MockJDBCDriver");
        cpds.setUrl(url);
        cpds.setUsername(username);
        cpds.setPassword(password);
        cpds.setMaxIdle(-1);
        cpds.setMinIdle(-1);
        cpds.setMaxOpenPreparedStatements(max_statement);
        cpds.setInitialSize(pool_size);
        cpds.setMaxActive(pool_size);
        cpds.getConnection(); // call to initialize possible lazy structures etc

        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            Connection conn = cpds.getConnection();
            conn.close();
        }
        long end = (System.currentTimeMillis() - start);
        // System.out.println("DBCP Single thread benchmark: "+end);

        cpds.close();
        System.out.println("time=" + end);
    }

}
