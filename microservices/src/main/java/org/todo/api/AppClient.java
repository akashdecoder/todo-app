package org.todo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppClient {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "app-server");
        SpringApplication.run(AppClient.class);
    }
}