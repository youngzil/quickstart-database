/**
 * 项目名称：quickstart-sqlite 文件名：Sample.java 版本信息： 日期：2018年11月11日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.sqlite.SQLiteConfig;

/**
 * Sample
 * 
 * @author：youngzil@163.com
 * @2018年11月11日 上午11:09:05
 * @since 1.0
 */
public class Sample {
  public static void main(String[] args) {
    Connection connection = null;
    try {

      SQLiteConfig config = new SQLiteConfig();
      // config.setReadOnly(true);
      config.setSharedCache(true);
      config.enableRecursiveTriggers(true);
      // ... other configuration can be set via SQLiteConfig object
      // connection = DriverManager.getConnection("jdbc:sqlite:sample.db", config.toProperties());

      // READ_UNCOMMITTED mode works only in shared_cache mode.
      Properties prop = new Properties();
      prop.setProperty("shared_cache", "true");
      // connection = DriverManager.getConnection("jdbc:sqlite:sample.db", prop));
      // connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30); // set timeout to 30 sec.

      statement.executeUpdate("drop table if exists person");
      statement.executeUpdate("create table person (id integer, name string)");
      statement.executeUpdate("insert into person values(1, 'leo')");
      statement.executeUpdate("insert into person values(2, 'yui')");
      ResultSet rs = statement.executeQuery("select * from person");
      while (rs.next()) {
        // read the result set
        System.out.println("name = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("id"));
      }

      rs.close();
      statement.close();
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    } finally {
      try {

        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        // connection close failed.
        System.err.println(e.getMessage());
      }
    }
  }
}
