package org.todo.api.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todo.api.entity.User;
import org.todo.api.utility.HibernateUtil;
import org.todo.api.utility.SearchOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HibernateUser {

    @Autowired
    private HibernateUtil hibernateUtil;

    @Autowired
    private SearchOperations searchOperations;

    private static final String USER_DB = "microservice-user";

    private Session getCurrentSession() {
        return hibernateUtil.getSessionFactory(USER_DB, User.class).openSession();
    }

    public List<User> fetch(String q) throws Exception {
        Session currentSession = null;
        List<User> allUsers = new ArrayList<>();
        List<Object> objectUsers = new ArrayList<>();
        List<User> foundUsers = new ArrayList<>();
        try {
            currentSession = getCurrentSession();
            allUsers = currentSession.createQuery("from users", User.class).list();

            objectUsers = allUsers.stream()
                    .map(user -> (Object) user)
                    .collect(Collectors.toList());

            objectUsers = searchOperations.searchSubstrings(objectUsers, User.class, q);

            if(!objectUsers.isEmpty()) {
                foundUsers = objectUsers.stream()
                        .map(object -> (User) object)
                        .collect(Collectors.toList());
            }
            currentSession.close();
            return foundUsers;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void update(User user) throws Exception {
        Session currentSession = null;
        Transaction transaction = null;
        User existingUser = null;
        try {
            List<User> users = fetch(user.getUserName());
            currentSession = getCurrentSession();
            transaction = currentSession.beginTransaction();
            if(users.isEmpty()) {
                currentSession.persist(user);
            } else {
                if(user.getUserId() != null) {
                    existingUser = currentSession.byId(User.class).load(user.getUserId());
                    if(existingUser != null && !existingUser.equals(user)) {
                        currentSession.merge(user);
                    } else {
                        throw new Exception("Please update at least one value.");
                    }
                } else {
                    throw new Exception("User already exists");
                }
            }
            transaction.commit();
            currentSession.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
