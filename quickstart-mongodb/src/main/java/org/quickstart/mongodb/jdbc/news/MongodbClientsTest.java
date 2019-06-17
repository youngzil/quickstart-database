/**
 * 项目名称：quickstart-mongodb 
 * 文件名：MongodbClientsTest.java
 * 版本信息：
 * 日期：2019年6月17日
 * Copyright yangzl Corporation 2019
 * 版权所有 *
 */
package org.quickstart.mongodb.jdbc.news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

/**
 * MongodbClientsTest
 * 
 * @author：youngzil@163.com
 * @2019年6月17日 下午10:10:46
 * @version 2.0
 */
public class MongodbClientsTest {

    public static void main(String[] args) {

        // 连接到 mongodb 服务
        MongoClient mongoClient = MongoClients.create();// localhost:27017

        MongoClient mongoClient2 = MongoClients.create(//
                MongoClientSettings.builder()//
                        .applyToClusterSettings(builder -> //
                        builder.hosts(Arrays.asList(new ServerAddress("localhost"))))
                        .build());
        MongoClient mongoClient3 = MongoClients.create(//
                MongoClientSettings.builder()//
                        .applyToClusterSettings(builder -> //
                        builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
                        .build());

        // MongoClient mongoClient4 = MongoClients.create("mongodb://hostOne:27017,hostTwo:27018");

        // 连接到数据库
        MongoDatabase database = mongoClient.getDatabase("local");
        System.out.println("Connect to database successfully");

        MongoCollection<Document> collection = database.getCollection("test");

        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));

        collection.insertOne(doc);

        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; i < 100; i++) {
            documents.add(new Document("i", i));
        }
        collection.insertMany(documents);

        System.out.println(collection.countDocuments());

        // 查询集合
        // MongoDB保留以内部开头"_"和"$"内部使用的字段名称 。
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());

        // 查找所有
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }

        // 虽然允许以下迭代习惯用法，但请避免使用它，因为如果循环提前终止，应用程序可能会泄漏游标：
        for (Document cur : collection.find()) {
            System.out.println(cur.toJson());
        }

        // 获取与筛选器匹配的单个文档
        myDoc = collection.find(eq("i", 71)).first();
        System.out.println(myDoc.toJson());

        // 获取与筛选器匹配的所有文档
        // 以下示例返回并打印所有文档"i" > 50：
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        collection.find(gt("i", 50)).forEach(printBlock);

        // 要指定范围的过滤器，例如50 < i <= 100，您可以使用and帮助程序：
        collection.find(and(gt("i", 50), lte("i", 100))).forEach(printBlock);

        // 以下示例更新满足过滤器i等于的第一个文档，10并将值设置i为110：
        collection.updateOne(eq("i", 10), new Document("$set", new Document("i", 110)));

        // 以下示例为= 小于的所有文档递增iby 的值：100i100
        UpdateResult updateResult = collection.updateMany(lt("i", 100), inc("i", 100));
        System.out.println(updateResult.getModifiedCount());

        // 以下示例最多删除一个满足过滤器i等于的文档110：
        collection.deleteOne(eq("i", 110));

        // 以下示例删除i大于或等于的所有文档100：
        DeleteResult deleteResult = collection.deleteMany(gte("i", 100));
        System.out.println(deleteResult.getDeletedCount());

        // 创建索引
        // 对于上升的指数型，指定1为<type>。
        // 对于降序索引类型，指定-1的<type>。
        // 以下示例在i字段上创建升序索引：
        collection.createIndex(new Document("i", 1));

    }

}
