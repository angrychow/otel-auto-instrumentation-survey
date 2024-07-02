package org.example.jedis;

import redis.clients.jedis.Jedis;
import java.util.Random;
import java.util.UUID;

public class JedisExecute {
    public static void main(String[] args) {
        String[] keys = new String[50];
        for (int i = 0; i < 50; i++) {
            keys[i] = "key" + i;
        }

        Random random = new Random();

        try (Jedis jedis = new Jedis("localhost", 6379)) {
            for (int i = 0; i < 1000; i++) {
                // 随机选择一个 key
                String key = keys[random.nextInt(keys.length)];

                // 生成随机 value
                String value = UUID.randomUUID().toString();

                // 设置键值对
                jedis.set(key, value);

                // 获取键值对
                String retrievedValue = jedis.get(key);

                // 输出结果
                System.out.println("The value of '" + key + "' is: " + retrievedValue);
            }
        }
    }
}
