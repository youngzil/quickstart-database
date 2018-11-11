/**
 * 项目名称：quickstart-h2 
 * 文件名：SelectQuery.java
 * 版本信息：
 * 日期：2018年11月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.h2.simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SelectQuery
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月11日 上午9:31:22
 * @since 1.0
 */
public class SelectQuery {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            Class.forName("org.h2.Driver");

            // conn = DriverManager. getConnection("jdbc:h2:~/test", "sa", "sa");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

            stmt = conn.prepareStatement("SELECT id, name, password, age, birthday FROM t_user WHERE id=?");
            stmt.setLong(1, 1);

            result = stmt.executeQuery();
            while (result.next()) {
                System.out.println(
                        result.getInt("id") + " | " + result.getString("name") + " | " + result.getString("password") + " | " + result.getShort("age") + " | " + result.getTimestamp("birthday"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
