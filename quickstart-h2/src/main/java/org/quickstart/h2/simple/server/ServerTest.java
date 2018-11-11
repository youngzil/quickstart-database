package org.quickstart.h2.simple.server;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.Server;

/**
 * Hello world!
 *
 */
public class ServerTest {
    private static final Logger logger = LogManager.getLogger(ServerTest.class.getName());

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");

    

        try {
            /* 不加任何参数 ，则默认启动三个服务器
            	TCP server running at tcp://192.168.11.125:9092 (only local connections)
            	PG server running at pg://192.168.11.125:5435 (only local connections)
            	Web Console server running at http://192.168.11.125:8082 (only local connections)
             */
            org.h2.tools.Server.main();
            
            // 启动 TCP Server
            Server server = Server.createTcpServer(args).start();
//            ...
            // 关闭 TCP Server
            server.stop();
            
//            可以从另外的程序关闭 TCP 服务器，使用下面命令行：
//            java org.h2.tools.Server -tcpShutdown tcp://localhost:9092
//            在用户应用中关闭服务器，使用:
//            org.h2.tools.Server.shutdownTcpServer("tcp://localhost:9094");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
