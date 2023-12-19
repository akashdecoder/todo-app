package org.todo.api.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.todo.api.dependencycontainer.ServiceContainer;
import org.todo.api.utility.SearchOperations;

@Configuration
public class ServiceConfig {

    @Autowired
    private SearchOperations searchOperations;

    @Bean
    public ServiceContainer serviceContainer() {
        return new ServiceContainer(searchOperations);
    }
}
