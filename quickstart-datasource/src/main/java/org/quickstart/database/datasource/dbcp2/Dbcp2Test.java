/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：Dbcp2Test.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.dbcp2;

import java.sql.Connection;
import java.util.Arrays;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Dbcp2Test
 * 
 * @author：youngzil@163.com
 * @2018年4月26日 下午9:53:47
 * @since 1.0
 */
public class Dbcp2Test {

    public static void main(String[] args) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.apache.commons.dbcp2.TesterDriver");
        ds.setUrl("jdbc:apache:commons:testdriver");
        ds.setMaxTotal(20);
        ds.setMaxWaitMillis(1000);
        ds.setDefaultAutoCommit(Boolean.TRUE);
        ds.setDefaultReadOnly(Boolean.FALSE);
        ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        ds.setDefaultCatalog("");
        ds.setUsername("username");
        ds.setPassword("password");
        ds.setMaxIdle(-1);
        ds.setMinIdle(-1);
        ds.setMaxOpenPreparedStatements(10);
        ds.setInitialSize(10);
        ds.setValidationQuery("SELECT DUMMY FROM DUAL");
        ds.setConnectionInitSqls(Arrays.asList(new String[] {"SELECT 1", "SELECT 2"}));
        // ds.setDriverClassLoader();
        ds.setJmxName("org.apache.commons.dbcp2:name=test");
    }

}
