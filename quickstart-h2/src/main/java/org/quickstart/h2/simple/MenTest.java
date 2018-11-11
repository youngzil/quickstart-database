/**
 * 项目名称：quickstart-h2 
 * 文件名：MemInsertDelete.java
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
 * MemInsertDelete
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月11日 上午9:57:24
 * @since 1.0
 */
public class MenTest {

    // 使用内存模式（关闭后内容全部消失，速度非常快）：

    public void runInsertDelete() {
        try {
            String sourceURL = "jdbc:h2:tcp://localhost/mem:testmemdb";// H2DB mem mode
            String user = "sa";
            String key = "";
            try {
                Class.forName("org.h2.Driver");// HSQLDB Driver
            } catch (Exception e) {
                e.printStackTrace();
            }
            Connection conn = DriverManager.getConnection(sourceURL, user, key);// 把驱动放入连接
            Statement stmt = conn.createStatement();
            // 创建一个 Statement 对象来将 SQL 语句发送到数据库。
            // stmt.executeUpdate("DELETE FROM mytable WHERE name=/'NO.2/'");
            // 执行方法找到一个与 methodName 属性同名的方法，并在目标上调用该方法。
            // stmt.execute("CREATE TABLE idtable(id INT,name VARCHAR(100));");
            stmt.execute("INSERT INTO idtable VALUES(1,/'MuSoft/')");
            stmt.execute("INSERT INTO idtable VALUES(2,/'StevenStander/')");
            stmt.close();
            conn.close();

        } catch (SQLException sqle) {
            System.out.println("SQL ERROR!");
        }

    }

    public void query(String SQL) {
        try {
            String sourceURL = "jdbc:h2:tcp://localhost/mem:testmemdb";
            String user = "sa";
            String key = "";

            try {
                Class.forName("org.h2.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Connection conn = DriverManager.getConnection(sourceURL, user, key);// 把驱动放入连接
            Statement stmt = conn.createStatement();// 创建一个 Statement 对象来将 SQL 语句发送到数据库。
            ResultSet rset = stmt.executeQuery(SQL);// 执行方法找到一个与 methodName 属性同名的方法，并在目标上调用该方法。
            while (rset.next()) {
                System.out.println(rset.getInt("id") + "  " + rset.getString("name"));
            }
            rset.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    public static void main(String args[]) {
        MenTest mt = new MenTest();
        mt.runInsertDelete();
        mt.query("SELECT * FROM idtable");
    }

}
