/**
 * 项目名称：quickstart-h2 
 * 文件名：InsertQuery.java
 * 版本信息：
 * 日期：2018年11月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.h2.simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * InsertQuery 
 *  
 * @author：youngzil@163.com
 * @2018年11月11日 上午9:30:38 
 * @since 1.0
 */
public class InsertQuery {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.h2.Driver");
            conn = DriverManager. getConnection("jdbc:h2:~/test", "sa", "");
//            conn = DriverManager. getConnection("jdbc:h2:~/test", "sa", "sa");

            stmt = conn.createStatement();
            int update = stmt.executeUpdate("INSERT INTO t_user VALUES (1,'ricky', '12345', 28, '1989-09-15 15:00:00');");
            System.out.println(update);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
