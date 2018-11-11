/**
 * 项目名称：quickstart-h2 
 * 文件名：EmbeddedModeTest.java
 * 版本信息：
 * 日期：2018年11月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.h2.simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * EmbeddedModeTest
 * 
 * @author：youngzil@163.com
 * @2018年11月11日 上午9:59:18
 * @since 1.0
 */
public class EmbeddedModeTest {

    public static void main(String args[]) {
        EmbeddedModeTest tt = new EmbeddedModeTest();
        tt.runInsertDelete();
        tt.query("select * from mytable");
    }

    public void runInsertDelete() {

        try {
            String sourceURL = "jdbc:h2:h2/bin/mydb";// H2 database
            String user = "sa";
            String key = "";
            try {
                Class.forName("org.h2.Driver");// H2 Driver
            } catch (Exception e) {
                e.printStackTrace();
            }
            Connection conn = DriverManager.getConnection(sourceURL, user, key);
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE mytable(name VARCHAR(100),sex VARCHAR(10))");
            stmt.executeUpdate("INSERT INTO mytable VALUES('Steven Stander','male')");
            stmt.executeUpdate("INSERT INTO mytable VALUES('Elizabeth Eames','female')");
            stmt.executeUpdate("DELETE FROM mytable WHERE sex=/'male/'");
            stmt.close();
            conn.close();

        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    public void query(String SQL) {
        try {
            String sourceURL = "jdbc:h2:h2/bin/mydb";
            String user = "sa";
            String key = "";

            try {
                Class.forName("org.h2.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Connection conn = DriverManager.getConnection(sourceURL, user, key);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(SQL);
            while (rset.next()) {
                System.out.println(rset.getString("name") + "  " + rset.getString("sex"));
            }
            rset.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

}
