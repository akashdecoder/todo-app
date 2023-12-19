package org.todo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableDiscoveryClient
public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "user-service");
        SpringApplication.run(Main.class);
    }
}