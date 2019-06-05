/**
 * 项目名称：quickstart-sqlite 
 * 文件名：SQLiteJDBCInsert.java
 * 版本信息：
 * 日期：2018年11月11日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * SQLiteJDBCInsert 
 *  
 * @author：youngzil@163.com
 * @2018年11月11日 上午11:05:05 
 * @since 1.0
 */
public class SQLiteJDBCInsert {
    public static void main( String args[] )
    {
      Connection c = null;
      Statement stmt = null;
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:test.db");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                     "VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
        stmt.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
              "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
        stmt.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
              "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );"; 
        stmt.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
              "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
        stmt.executeUpdate(sql);

        stmt.close();
        c.commit();
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
      System.out.println("Records created successfully");
    }
}
