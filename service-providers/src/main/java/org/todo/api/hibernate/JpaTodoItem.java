package org.todo.api.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.todo.api.entity.TodoItem;
import org.todo.api.repository.ITodoItemRepository;
import org.todo.api.utility.SearchOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaTodoItem {

    @Autowired
    private ITodoItemRepository todoItemRepository;

    @Autowired
    private SearchOperations searchOperations;

    public List<TodoItem> fetch(String query) throws Exception {
        List<TodoItem> foundTodoItems = new ArrayList<>();

        List<TodoItem> allTodoItems = todoItemRepository.findAll();

        List<Object> objectTodoItems = allTodoItems.stream()
                .map(todoItem -> (Object) todoItem)
                .collect(Collectors.toList());

        objectTodoItems = searchOperations.searchSubstrings(objectTodoItems, TodoItem.class, query);

        if(!objectTodoItems.isEmpty()) {
            foundTodoItems = objectTodoItems.stream()
                    .map(object -> (TodoItem) object)
                    .collect(Collectors.toList());
        }
        return foundTodoItems;
    }

    public void update(TodoItem todoItem) throws Exception {

        Optional<TodoItem> existingTodoItem = null;
        try {
            List<TodoItem> items = fetch(todoItem.getTitle());
            if(!items.isEmpty()) {
                if(todoItem.getId() != null) {
                    existingTodoItem = todoItemRepository.findById(todoItem.getId());
                    if(existingTodoItem.equals(todoItem)) {
                        throw new Exception("At least update one value.");
                    }
                }
            }
            todoItemRepository.save(todoItem);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }
}
