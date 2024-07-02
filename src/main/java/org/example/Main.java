package org.example;


import org.example.apache_http_client.ApacheHttpExecute;
import org.example.dubbo.DubboClientApplication;
import org.example.dubbo.DubboServerApplication;
import org.example.grpc.GreeterClient;
import org.example.grpc.GrpcServer;
import org.example.jedis.JedisExecute;
import org.example.jetty_servlet.JettyServer;
import org.example.kafka_consumer.KafkaConsumerExecute;
import org.example.kafka_consumer.KafkaProducerExecute;
import org.example.mongo.MongoDB;
import org.example.mysql.MySQLJDBCExample;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws Exception {
//        ApacheHttpExecute.main(null);

//        var l = new Thread(() -> KafkaConsumerExecute.main(null));
//        for(int i = 0; i < 100; i ++) {
//            KafkaProducerExecute.main(null);
//        }
//        l.interrupt();

//        JedisExecute.main(null);
//
//        DubboServerApplication.main(null);
//        for(int i = 0; i < 1000; i++) {
//            DubboClientApplication.main(null);
//        }
//        JettyServer.main(null);
//        GreeterClient.main(null);
//        GrpcServer.main(null);
//        MongoDB.main(null);
        MySQLJDBCExample.main(null);
    }

}