package org.todo.api.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todo.api.entity.TodoItem;
import org.todo.api.utility.HibernateUtil;
import org.todo.api.utility.SearchOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HibernateTodoItem {

    @Autowired
    private HibernateUtil hibernateUtil;

    @Autowired
    private SearchOperations searchOperations;

    private static final String TODO_DB = "microservice-todo";

    private Session getCurrentSession() {
        return hibernateUtil.getSessionFactory(TODO_DB, TodoItem.class).openSession();
    }

    public List<TodoItem> fetch(String q) throws Exception {
        Session currentSession = null;
        Transaction transaction = null;
        List<TodoItem> allTodoItems = new ArrayList<>();
        List<Object> objectTodoItems = new ArrayList<>();
        List<TodoItem> foundTodoItems = new ArrayList<>();

        try {
            currentSession = getCurrentSession();
            allTodoItems = currentSession.createQuery("from todo", TodoItem.class).list();

            objectTodoItems = allTodoItems.stream()
                    .map(todoItem -> (Object) todoItem)
                    .collect(Collectors.toList());

            objectTodoItems = searchOperations.searchSubstrings(objectTodoItems, TodoItem.class, q);

            if(!objectTodoItems.isEmpty()) {
                foundTodoItems = objectTodoItems.stream()
                        .map(object -> (TodoItem) object)
                        .collect(Collectors.toList());
            }

            currentSession.close();
            return foundTodoItems;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void update(TodoItem todoItem) throws Exception {

        Session currentSession = null;
        Transaction transaction = null;
        TodoItem existingTodoItem = null;

        try {
            currentSession = getCurrentSession();
            transaction = currentSession.beginTransaction();
            if(todoItem.getId() != null) {
                existingTodoItem = currentSession.byId(TodoItem.class).load(todoItem.getId());
                if(existingTodoItem != null && !existingTodoItem.equals(todoItem)) {
                    currentSession.merge(todoItem);
                } else {
                    throw new Exception("Please update at least one value.");
                }
            } else {
                currentSession.persist(todoItem);
            }
            transaction.commit();
            currentSession.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
