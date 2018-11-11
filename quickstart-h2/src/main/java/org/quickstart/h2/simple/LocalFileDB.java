/**
 * 项目名称：quickstart-h2 
 * 文件名：FileDB.java
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
 * FileDB
 * 
 * @author：youngzil@163.com
 * @2018年11月11日 上午9:26:07
 * @since 1.0
 */
public class LocalFileDB {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            
            
            String url = "jdbc:h2:~/test;CIPHER=AES";//Connecting to an Encrypted Database
            String url2 = "jdbc:h2:~/test;FILE_LOCK=SOCKET";//使用“socket”锁定方法打开数据库:
            String url3 = "jdbc:h2:/data/sample;IFEXISTS=TRUE";//Opening a Database Only if it Already Exists
            String url4 = "jdbc:h2:~/test;DB_CLOSE_ON_EXIT=FALSE";//Don't Close a Database when the VM Exits
//            有时，特别是对于内存数据库，当客户机连接到数据库时，能够自动执行DDL或DML命令非常有用。这个功能是通过INIT属性启用的。注意，可以向INIT传递多个命令，但是分号分隔符必须转义，如下例所示。
            String url5 = "jdbc:h2:mem:test;INIT=runscript from '~/create.sql'\\;runscript from '~/init.sql'";
//            <property name="url" value="jdbc:h2:mem:test;INIT=create schema if not exists test\;runscript from '~/sql/init.sql'" />
          //通常，数据库使用访问模式rw打开数据库文件，这意味着读写(除了使用模式r的只读数据库)。
            String url6 = "jdbc:h2:~/test;ACCESS_MODE_DATA=rws";
            String url7 = "jdbc:h2:~/test;AUTO_RECONNECT=TRUE";//;AUTO_RECONNECT=TRUE

            
//            多个进程可以访问同一个数据库，而无需手动启动服务器。要做到这一点，可以对数据库URL追加;AUTO_SERVER=TRUE。您可以使用与数据库是否已打开无关的相同的数据库URL。该特性不适用于内存中的数据库。
            String url8 = "jdbc:h2:/data/test;AUTO_SERVER=TRUE";

            
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            // conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

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
