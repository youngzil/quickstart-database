/**
 * 项目名称：quickstart-derby 文件名：HelloJavaDb.java 版本信息： 日期：2019年7月1日 Copyright yangzl Corporation 2019 版权所有 *
 */
package org.quickstart.derby.example;

/**
 * HelloJavaDb
 * 
 * @author：youngzil@163.com
 * @2019年7月1日 下午3:15:26
 * @version 2.0
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJavaDb {
  Connection conn;

  public static void main(String[] args) throws SQLException {
    HelloJavaDb app = new HelloJavaDb();

    app.connectionToDerby();
    app.normalDbUsage();
  }

  public void connectionToDerby() throws SQLException {
    // -------------------------------------------
    // URL format is
    // jdbc:derby:<local directory to save data>
    // -------------------------------------------
    String dbUrl = "jdbc:derby:MyDerbyDB\\demo;create=true";
    // jdbc:derby:memory:demo;create=true
    // jdbc:derby:c:\Users\shengw\MyDB\demo;create=true
    // jdbc:derby:c:\\Users\\shengw\\MyDB\\demo;create=true
    conn = DriverManager.getConnection(dbUrl);
  }

  public void normalDbUsage() throws SQLException {
    Statement stmt = conn.createStatement();

    // drop table
    // stmt.executeUpdate("Drop Table users");

    // create table
    stmt.executeUpdate("Create table users (id int primary key, name varchar(30))");

    // insert 2 rows
    stmt.executeUpdate("insert into users values (1,'tom')");
    stmt.executeUpdate("insert into users values (2,'peter')");

    // query
    ResultSet rs = stmt.executeQuery("SELECT * FROM users");

    // print out query result
    while (rs.next()) {
      System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
    }
  }
}
