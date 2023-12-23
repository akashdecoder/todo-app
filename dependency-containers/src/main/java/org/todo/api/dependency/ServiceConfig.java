package org.todo.api.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.todo.api.dependencycontainer.ServiceContainer;
import org.todo.api.hibernate.HibernateTodoItem;
import org.todo.api.hibernate.HibernateUser;
import org.todo.api.hibernate.JpaTodoItem;
import org.todo.api.hibernate.JpaUser;
import org.todo.api.utility.SearchOperations;

@Configuration
public class ServiceConfig {

    @Autowired
    private SearchOperations searchOperations;

    @Autowired
    private JpaUser jpaUser;

    @Autowired
    private JpaTodoItem jpaTodoItem;

    @Autowired
    private HibernateUser hibernateUser;

    @Autowired
    private HibernateTodoItem hibernateTodoItem;

    @Bean
    public ServiceContainer serviceContainer() {
        return new ServiceContainer(searchOperations, jpaUser, jpaTodoItem, hibernateUser, hibernateTodoItem);
    }
}
