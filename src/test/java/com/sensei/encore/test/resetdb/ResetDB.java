package com.sensei.encore.test.resetdb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@SpringBootTest
public class ResetDB {

    @Test
    public void resetDd() {
        try {
            String dbName = "trishatestdb";
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://encore-demo.senseitech.com/encoretest-automation/api/runScript?dbName="+dbName))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            log.info("Status Code: {}", response.statusCode());
            log.info("Response Body: {}", response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
