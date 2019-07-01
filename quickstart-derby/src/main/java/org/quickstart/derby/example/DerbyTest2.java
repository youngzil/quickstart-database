package org.quickstart.derby.example;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-01 14:41
 */

import java.sql.*;

public class DerbyTest2 {
  private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
  private static String protocol = "jdbc:derby:";
  static String dbName = "~/derby/mydb";

  public static void main(String[] args) {
    String connectionURL = protocol + dbName + ";create=true";
    // String connectionURL = protocol + dbName + ";user=root;password=root;create=true";

    String createString = "CREATE TABLE Employee2 (NAME VARCHAR(32) NOT NULL, ADDRESS VARCHAR(50) NOT NULL)";
    try {
      // Class.forName(driver).newInstance();
      Class.forName(driver);
    } catch (java.lang.ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      Connection conn = DriverManager.getConnection(connectionURL);
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(createString);
      PreparedStatement psInsert = conn.prepareStatement("insert into Employee2 values (?,?)");
      psInsert.setString(1, "123");
      psInsert.setString(2, "321");
      psInsert.executeUpdate();

      ResultSet rs = stmt.executeQuery("select * from Employee2");
      int num = 0;
      while (rs.next()) {

        System.out.println(rs.getString(1));
        System.out.println(rs.getString(2));

        System.out.println(++num + ": Name: " + rs.getString(1) + "/n Address" + rs.getString(2));
      }

      rs.close();
      stmt.close();
      conn.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
