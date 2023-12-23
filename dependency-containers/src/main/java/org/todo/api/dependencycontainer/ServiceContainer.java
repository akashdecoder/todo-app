package org.todo.api.dependencycontainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.todo.api.hibernate.JpaTodoItem;
import org.todo.api.hibernate.JpaUser;
import org.todo.api.hibernate.HibernateTodoItem;
import org.todo.api.hibernate.HibernateUser;
import org.todo.api.utility.SearchOperations;

@AllArgsConstructor
@Data
public class ServiceContainer {
    private final SearchOperations searchOperations;
    private final JpaUser jpaUser;
    private final JpaTodoItem jpaTodoItem;
    private final HibernateUser hibernateUser;
    private final HibernateTodoItem hibernateTodoItem;
}
