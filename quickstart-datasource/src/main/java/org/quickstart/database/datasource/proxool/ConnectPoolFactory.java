/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：ConnectPoolFactory.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.proxool;

/**
 * ConnectPoolFactory 
 *  
 * @author：youngzil@163.com
 * @2018年4月26日 下午10:05:23 
 * @since 1.0
 */
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;

public class ConnectPoolFactory {

    private static ConnectPoolFactory connectPoolFactory = null;

    private ConnectPoolFactory() {// 构造方法
        init();
    }

    public void init() {// 把properties文件加载到链接对象
        InputStream is = ConnectPoolFactory.class.getResourceAsStream("/proxool.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectPoolFactory getInstance() {// 单例模式
        if (null == connectPoolFactory) {
            connectPoolFactory = new ConnectPoolFactory();
        }

        return connectPoolFactory;
    }

    public Connection getConnect() {
        Connection conn = null;
        try {
            Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");// 加载数据库连接池配备的驱动
            conn = DriverManager.getConnection("proxool.test");// proxool为配置文件名，test为连接池别名
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
