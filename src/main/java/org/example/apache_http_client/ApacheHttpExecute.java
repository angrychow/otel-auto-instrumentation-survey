package org.example.apache_http_client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.util.Random;

public class ApacheHttpExecute {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String[] urls = {
                "http://localhost:8000/1",
                "http://localhost:8000/2",
                "http://localhost:8000/3",
                "http://localhost:8000/4",
                "http://localhost:8000/5",
                "http://localhost:8000/6",
                "http://localhost:8000/7",
                "http://localhost:8000/8",
                "http://localhost:8000/9",
                "http://localhost:8000/a"
        };
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            String url = urls[random.nextInt(urls.length)];
            boolean isGetRequest = random.nextBoolean();

            if (isGetRequest) {
                HttpGet request = new HttpGet(url);
                try (CloseableHttpResponse response = client.execute(request)) {
                    System.out.println("GET " + url);
                    System.out.println("Response Code: " + response.getCode());
                    String responseBody = EntityUtils.toString(response.getEntity());
                    System.out.println("Response Body: " + responseBody);
                }
            } else {
                HttpPost request = new HttpPost(url);
                try (CloseableHttpResponse response = client.execute(request)) {
                    System.out.println("POST " + url);
                    System.out.println("Response Code: " + response.getCode());
                    String responseBody = EntityUtils.toString(response.getEntity());
                    System.out.println("Response Body: " + responseBody);
                }
            }
        }

        client.close();
    }
}
