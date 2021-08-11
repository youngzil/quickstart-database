/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：ViburExampleTest.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.vibur;

import static org.junit.Assert.assertEquals;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.vibur.dbcp.ViburDBCPDataSource;

/**
 * ViburExampleTest
 * 
 * @author：youngzil@163.com
 * @2018年4月26日 下午8:36:25
 * @since 1.0
 */
public class ViburExampleTest {

    protected static final String PROPERTIES_FILE = "src/test/resources/vibur-dbcp-test.properties";
    protected static final int POOL_INITIAL_SIZE = 2;
    protected static final int POOL_MAX_SIZE = 10;
    protected static final int CONNECTION_TIMEOUT_MS = 5000;

    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static String jdbcUrl = "jdbc:mysql://10.11.20.83:3306/test2.0";
    private static String username = "root";
    private static String password = "root";

    public static void main(String[] args) throws SQLException {
        ViburDBCPDataSource dataSource = new ViburDBCPDataSource();

        // ViburDBCPDataSource ds = new ViburDBCPDataSource("vibur-dbcp-test.properties");
        // ViburDBCPDataSource ds = new ViburDBCPDataSource("vibur-dbcp-test.xml");

        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setPoolInitialSize(POOL_INITIAL_SIZE);
        dataSource.setPoolMaxSize(POOL_MAX_SIZE);
        dataSource.setConnectionTimeoutInMs(CONNECTION_TIMEOUT_MS);

        dataSource.setConnectionIdleLimitInSeconds(120);

        dataSource.setLogQueryExecutionLongerThanMs(0);
        dataSource.setLogConnectionLongerThanMs(0);
        dataSource.setLogLargeResultSet(2);

        dataSource.setValidateTimeoutInSeconds(1);
        dataSource.setClearSQLWarnings(true);

        dataSource.start();

        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();
        PreparedStatement pStatement = connection.prepareStatement("select count(*) as cnt from msg_alarm_config");
        CallableStatement cStatement = connection.prepareCall("select count(*) from msg_alarm_config");

        ResultSet rs = pStatement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("cnt"));
        }

        ResultSet resultSet = statement.executeQuery("select * from actor where first_name = 'CHRISTIAN'");

    }

}
