/**
 * 项目名称：quickstart-h2 
 * 文件名：CreateTable.java
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
 * CreateTable 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年11月11日 上午9:29:30 
 * @since 1.0
 */
public class CreateTable {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.h2.Driver");
            conn = DriverManager. getConnection("jdbc:h2:~/test", "sa", "");
//            conn = DriverManager. getConnection("jdbc:h2:~/test", "sa", "sa");

            stmt = conn.createStatement();

            int result = stmt.executeUpdate("CREATE TABLE t_user (" +
                    "id BIGINT NOT NULL, " +
                    "name VARCHAR(20) NOT NULL," +
                    "password VARCHAR(32) NOT NULL, " +
                    "age SMALLINT, " +
                    "birthday TIMESTAMP," +
                    "PRIMARY KEY (id)" +
                    ");");

            System.out.println("result:"+result);
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
