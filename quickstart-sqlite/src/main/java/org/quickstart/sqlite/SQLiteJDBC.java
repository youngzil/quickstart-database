/**
 * 项目名称：quickstart-sqlite 
 * 文件名：SQLiteJDBC.java
 * 版本信息：
 * 日期：2018年11月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * SQLiteJDBC
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月11日 上午11:01:30
 * @since 1.0
 */
public class SQLiteJDBC {

    public static void main(String args[]) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println(c.getMetaData());
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " + //
                    "(ID INT PRIMARY KEY     NOT NULL," + //
                    " NAME           TEXT    NOT NULL, " + //
                    " AGE            INT     NOT NULL, " + //
                    " ADDRESS        CHAR(50), " + //
                    " SALARY         REAL)"; //
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");

    }
}
