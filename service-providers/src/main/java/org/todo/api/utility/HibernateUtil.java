package org.todo.api.utility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Service;
import org.todo.api.entity.TodoItem;
import org.todo.api.entity.User;

import java.util.Properties;

@Service
public class HibernateUtil {
    private SessionFactory sessionFactory;
    private final String JDBC_URL = "jdbc:postgresql://localhost:5432/";

    public SessionFactory getSessionFactory(String dbName, Object object) {
        try {
            Configuration configuration = new Configuration();
            Properties properties = getProperties(dbName);

            configuration.setProperties(properties);

            if(object == User.class) {
                configuration.addAnnotatedClass(User.class);
            } else {
                configuration.addAnnotatedClass(TodoItem.class);
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sessionFactory;
    }

    private Properties getProperties(String dbName) {
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, JDBC_URL+dbName);
        properties.put(Environment.USER, "admin");
        properties.put(Environment.PASS, "admin");

        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "update");

        return properties;
    }
}
