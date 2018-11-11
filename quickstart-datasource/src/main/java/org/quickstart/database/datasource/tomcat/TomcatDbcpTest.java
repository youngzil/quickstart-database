/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：TomcatDbcpTest.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.tomcat;

import java.util.LinkedList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * TomcatDbcpTest
 * 
 * @author：youngzil@163.com
 * @2018年4月26日 下午10:09:46
 * @since 1.0
 */
public class TomcatDbcpTest {

    public static void main(String[] args) {
        PoolProperties config = new PoolProperties();
        config.setUrl(url);
        config.setDriverClassName("com.jolbox.bonecp.MockJDBCDriver");
        config.setUsername(username);
        config.setPassword(password);
        config.setMaxIdle(pool_size);
        config.setMaxAge(0);
        config.setInitialSize(pool_size);
        config.setMaxActive(pool_size);
        org.apache.tomcat.jdbc.pool.DataSource dsb = new org.apache.tomcat.jdbc.pool.DataSource();
        dsb.setPoolProperties(config);
    }

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

}
