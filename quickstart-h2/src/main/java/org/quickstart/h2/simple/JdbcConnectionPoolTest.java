/**
 * 项目名称：quickstart-h2 
 * 文件名：JdbcConnectionPoolTest.java
 * 版本信息：
 * 日期：2018年11月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.h2.simple;

import java.sql.Connection;

import org.h2.jdbcx.JdbcConnectionPool;

/**
 * JdbcConnectionPoolTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月11日 上午10:32:59
 * @since 1.0
 */
public class JdbcConnectionPoolTest {

    // H2 从内置连接池获取连接比使用DriverManager.getConnection()快两倍左右。
    public static void main(String[] args) throws Exception {
        JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:~/test", "sa", "sa");
        for (int i = 0; i < args.length; i++) {
            Connection conn = cp.getConnection();
            conn.createStatement().execute(args[i]);
            conn.close();
        }
        cp.dispose();
    }
}
