/**
 * 项目名称：quickstart-datasource 
 * 文件名：SqlDateTime.java
 * 版本信息：
 * 日期：2018年10月15日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.database.datasource.jdbc;

/**
 * SqlDateTime
 * 
 * @author：youngzil@163.com
 * @2018年10月15日 下午9:27:07
 * @since 1.0
 */
public class SqlDateTime {
    public static void main(String[] args) {
        // java.sql.Date类映射到SQL DATE类型，java.sql.Time和java.sql.Timestamp类分别映射到SQL TIME和SQL TIMESTAMP数据类型。
        // Get standard date and time
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();
        System.out.println("The Java Date is:" + javaDate.toString());

        // Get and display SQL DATE
        java.sql.Date sqlDate = new java.sql.Date(javaTime);
        System.out.println("The SQL DATE is: " + sqlDate.toString());

        // Get and display SQL TIME
        java.sql.Time sqlTime = new java.sql.Time(javaTime);
        System.out.println("The SQL TIME is: " + sqlTime.toString());
        // Get and display SQL TIMESTAMP
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
        System.out.println("The SQL TIMESTAMP is: " + sqlTimestamp.toString());
    }// end main
}// end SqlDateTime
