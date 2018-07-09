/**
 * 项目名称：quickstart-database-datasource 
 * 文件名：ProxoolTest2.java
 * 版本信息：
 * 日期：2018年4月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.proxool;

import java.sql.Connection;

/**
 * ProxoolTest2
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月26日 下午10:04:33
 * @since 1.0
 */
public class ProxoolTest2 {

    public static void main(String[] args) {
        ConnectPoolFactory factory = ConnectPoolFactory.getInstance();
        Connection connect = factory.getConnect();
        System.out.println("ProxoolTest.main(conncet): " + connect);
    }

}
