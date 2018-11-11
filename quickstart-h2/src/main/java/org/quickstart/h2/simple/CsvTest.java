/**
 * 项目名称：quickstart-h2 
 * 文件名：CsvTest.java
 * 版本信息：
 * 日期：2018年11月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.h2.simple;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

import org.h2.tools.Csv;
import org.h2.tools.SimpleResultSet;

/**
 * CsvTest 
 *  
 * @author：youngzil@163.com
 * @2018年11月11日 上午10:30:23 
 * @since 1.0
 */
public class CsvTest {
    
//    CSV (Comma Separated Values) 支持
//    CSV（逗号分隔文件）文件在数据库系统中支持CSVREAD和CSVWRITE方法，也可以把它作为数据库之外的一个工具来使用。 将数据库查询结果写成CSV文件

    
    public static void writeCsv() throws Exception {
        SimpleResultSet rs = new SimpleResultSet();
        rs.addColumn("NAME", Types.VARCHAR, 255, 0);
        rs.addColumn("EMAIL", Types.VARCHAR, 255, 0);
        rs.addRow("Bob Meier", "bob.meier@abcde.abc");
        rs.addRow("John Jones", "john.jones@abcde.abc");
        new Csv().write("data/test.csv", rs, null);
    }
    
    public static void readCsv() throws Exception {
        ResultSet rs = new Csv().read("data/test.csv", null, null);
        ResultSetMetaData meta = rs.getMetaData();
        while (rs.next()) {
            for (int i = 0; i < meta.getColumnCount(); i++) {
                System.out.println(
                    meta.getColumnLabel(i + 1) + ": " +
                    rs.getString(i + 1));
            }
            System.out.println();
        }
        rs.close();
    }
    
    
}
