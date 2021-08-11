package org.quickstart.database.datasource.datatranslator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.quickstart.database.datasource.datatranslator.config.DataSourceConfig;
import org.quickstart.database.datasource.datatranslator.config.ParseConfig;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/9/24 22:30
 */
public class DataTranslator {

  // JDBC driver name and database URL
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

  public static void main(String[] args) {

    DataSourceConfig sourceDataSource = ParseConfig.getSourceDataSource();
    DataSourceConfig targetDataSource = ParseConfig.getSourceDataSource();

    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");
      Class.forName("oracle.jdbc.driver.OracleDriver");

      // STEP 2: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(sourceDataSource.getUrl(), sourceDataSource.getUsername(), sourceDataSource.getPassword());

      // STEP 3: Execute a query to create statment with required arguments for RS example.
      System.out.println("Creating statement...");
      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

      String sql = "SELECT * FROM aop_config";
      ResultSet rs = stmt.executeQuery(sql);

      DatabaseMetaData dbmd = conn.getMetaData();
      ResultSet colRet = dbmd.getColumns(conn.getCatalog(), null, "aop_config", null);
      // ResultSet colRet = dbmd.getColumns(null, "%", "aop_config", "%");
      // rs.getString(DATA_TYPE) // java.sql.Types 的 SQL 类型
      // rs.getString(COLUMN_SIZE) //列的大小。对于 char 或 date 类型，列的大小是最大字符数，对于 numeric 和 decimal 类型，列的大小就是精度。
      // rs.getString(DECIMAL_DIGITS) //小数部分的位数

      while (colRet.next()) {
        String columnName = colRet.getString("COLUMN_NAME");
        String columnType = colRet.getString("TYPE_NAME");
        int datasize = colRet.getInt("COLUMN_SIZE");
        int digits = colRet.getInt("DECIMAL_DIGITS");
        int nullable = colRet.getInt("NULLABLE");
        System.out.println(columnName + " " + columnType + " " + datasize + " " + digits + " " + nullable);
      }

      // ResultSetMetaData resultSetMetaData = rs.getMetaData();
      //
      // int count = resultSetMetaData.getColumnCount();
      // for (int i = 1; i <= count; i++) {
      //   System.out.println(i + "=" + resultSetMetaData.getColumnType(i));
      // }

      // STEP 4: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
    } catch (Exception e) {
      // Handle errors for JDBC
      e.printStackTrace();
    } finally {
      // finally block used to close resources
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      } // nothing we can do
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    System.out.println("Goodbye!");
  }
}
