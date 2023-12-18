package org.todo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TodoAppServiceRegistration {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "registration-server");
        SpringApplication.run(TodoAppServiceRegistration.class, args);
    }
}