/**
 * 项目名称：quickstart-h2 
 * 文件名：MemDB.java
 * 版本信息：
 * 日期：2018年11月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.h2.simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MemDB
 * 
 * @author：youngzil@163.com
 * @2018年11月11日 上午9:26:58
 * @since 1.0
 */
public class MemDB {
    
//  使用内存模式（关闭后内容全部消失，速度非常快）：

    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:mem:test_mem", "sa", "sa");
            System.out.println(conn.getMetaData());
            // do your business

        } catch (Exception e) {
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
