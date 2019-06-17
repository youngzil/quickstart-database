/**
 * 项目名称：quickstart-mongodb 
 * 文件名：MongoDBClusterJDBC.java
 * 版本信息：
 * 日期：2019年6月17日
 * Copyright yangzl Corporation 2019
 * 版权所有 *
 */
package org.quickstart.mongodb.jdbc.old;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * MongoDBClusterJDBC
 * 
 * @author：youngzil@163.com
 * @2019年6月17日 下午9:50:15
 * @version 2.0
 */
public class MongoDBClusterJDBC {

    public static void main(String[] args) {
        try {
            // 连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            // ServerAddress()两个参数分别为 服务器地址 和 端口
            ServerAddress serverAddress = new ServerAddress("localhost", 27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);

            // MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);

            // 通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs, credentials);

            MongoClientURI connectionString = new MongoClientURI("mongodb://hostOne:27017,hostTwo:27017");
            MongoClient mongoClient2 = new MongoClient(connectionString);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

}
