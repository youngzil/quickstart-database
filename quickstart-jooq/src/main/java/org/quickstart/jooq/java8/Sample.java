/**
 * 项目名称：quickstart-jooq 
 * 文件名：Sample.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.jooq.java8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.jooq.impl.DSL;
import org.jooq.util.xml.jaxb.Column;
import static org.quickstart.jooq.example.generate.Tables.AUTHOR;

/**
 * Sample 
 *  
 * @author：youngzil@163.com
 * @2018年11月12日 下午4:07:31 
 * @since 1.0
 */
public class Sample {
    
    public static void main(String[] args) {
        String userName = "root";
        String password = "root";
        String url = "jdbc:mysql://10.11.20.83:3306/library";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            String sql = "select schema_name, is_default " +
                    "from information_schema.schemata " +
                    "order by schema_name";

            DSL.using(conn)
            .select(
                    AUTHOR.ID,
                    AUTHOR.FIRST_NAME,
                    AUTHOR.LAST_NAME
            )
            .from(AUTHOR)
            .orderBy(
                    AUTHOR.ID,
                    AUTHOR.FIRST_NAME,
                    AUTHOR.LAST_NAME
            )
            .fetch()  // jOOQ ends here
            .stream() // JDK 8 Streams start here
            .collect(groupingBy(
                r -> r.getValue(AUTHOR.TABLE_NAME),
                LinkedHashMap::new,
                mapping(
                    r -> new Column(
                        r.getValue(COLUMNS.COLUMN_NAME),
                        r.getValue(COLUMNS.TYPE_NAME)
                    ),
                    toList()
                )
            ))
            .forEach(
                (table, columns) -> {
                     // Just emit a CREATE TABLE statement
                     System.out.println(
                         "CREATE TABLE " + table + " (");
          
                     // Map each "Column" type into a String
                     // containing the column specification,
                     // and join them using comma and
                     // newline. Done!
                     System.out.println(
                         columns.stream()
                                .map(col -> "  " + col.name +
                                             " " + col.type)
                                .collect(Collectors.joining(",\n"))
                     );
          
                    System.out.println(");");
                }
            );
         
        }

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
