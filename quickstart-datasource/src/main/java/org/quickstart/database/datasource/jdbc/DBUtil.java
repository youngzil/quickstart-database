/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：DBUtil.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * DBUtil
 * 
 * @author：youngzil@163.com
 * @2018年4月26日 下午10:03:23
 * @since 1.0
 */
public class DBUtil {
    static {
        InputStream is = DBUtil.class.getResourceAsStream("/proxool.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * 
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
            conn = DriverManager.getConnection("proxool.test");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获取sql语句发送器
     * 
     * @param conn
     * @return
     */
    public static Statement getStmt(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    /**
     * 获取预处理发送器
     * 
     * @param conn
     * @param sql
     * @return
     */
    public static PreparedStatement getPstmt(Connection conn, String sql) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * 动态绑定参数
     * 
     * @param pstmt
     * @param params
     */
    public static void bindParam(PreparedStatement pstmt, Object... params) {
        for (int i = 0; i < params.length; i++) {
            try {
                pstmt.setObject(i + 1, params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭资源
     * 
     * @param autoCloseables 实现了AutoCloseable接口的实现类对象
     */
    public static void closeAll(AutoCloseable... autoCloseables) {
        for (AutoCloseable autoCloseable : autoCloseables) {
            if (autoCloseable != null) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
