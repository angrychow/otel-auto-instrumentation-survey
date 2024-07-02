package org.example.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Random;

import static com.mongodb.client.model.Filters.eq;

public class MongoDB {

    public static void main(String[] args) {
        // 连接到 MongoDB 服务器
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // 访问数据库
        MongoDatabase database = mongoClient.getDatabase("testdb");

        // 访问集合
        MongoCollection<Document> collection = database.getCollection("testcollection");

        Random random = new Random();
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};

        for (int i = 0; i < 1000; i++) {
            int operation = random.nextInt(3); // 0: 插入, 1: 查询, 2: 删除
            String name = names[random.nextInt(names.length)];

            switch (operation) {
                case 0:
                    // 插入文档
                    Document insertDoc = new Document("name", name)
                            .append("age", random.nextInt(100))
                            .append("city", "City" + random.nextInt(100));
                    collection.insertOne(insertDoc);
                    System.out.println("Inserted: " + insertDoc.toJson());
                    break;
                case 1:
                    // 查询文档
                    Document queryDoc = collection.find(eq("name", name)).first();
                    if (queryDoc != null) {
                        System.out.println("Found: " + queryDoc.toJson());
                    } else {
                        System.out.println("No document found with name: " + name);
                    }
                    break;
                case 2:
                    // 删除文档
                    Document deleteDoc = collection.findOneAndDelete(eq("name", name));
                    if (deleteDoc != null) {
                        System.out.println("Deleted: " + deleteDoc.toJson());
                    } else {
                        System.out.println("No document found to delete with name: " + name);
                    }
                    break;
            }
        }

        // 关闭连接
        mongoClient.close();
    }
}
