package org.todo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.todo.api.dependencycontainer.ServiceContainer;
import org.todo.api.entity.TodoItem;
import org.todo.api.request.TodoItemRequest;
import org.todo.api.request.UserRequest;
import org.todo.api.response.TodoResponse;
import org.todo.api.response.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todo/items")
public class TodoController {
    private String BASE_URL = "http://user-service/todo/user";

    private final WebClient webClient;

    private final ServiceContainer serviceContainer;

    @Autowired
    public TodoController(WebClient webClient, ServiceContainer serviceContainer) {
        this.webClient = webClient;
        this.serviceContainer = serviceContainer;
    }

    @GetMapping
    public ResponseEntity<Object> fetch(@RequestBody TodoItemRequest request) throws Exception {
        List<TodoItem> foundTodoItems = null;
        String query = "";
        query = request.getQ().trim();
        try {
            foundTodoItems = serviceContainer.getJpaTodoItem().fetch(query);
            if(foundTodoItems.isEmpty()) {
                throw new Exception("User not found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new TodoResponse(HttpStatus.NOT_FOUND, e.getMessage(), LocalDateTime.now(), null, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new TodoResponse(HttpStatus.OK, null, LocalDateTime.now(), foundTodoItems, null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> update(@RequestBody TodoItem todoItem) {
        try {
            serviceContainer.getJpaTodoItem().update(todoItem);
        } catch (Exception e) {
            return new ResponseEntity<>(new TodoResponse(HttpStatus.BAD_REQUEST, e.getMessage(), LocalDateTime.now(),null, todoItem), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new TodoResponse(HttpStatus.ACCEPTED, null, LocalDateTime.now(),null, todoItem), HttpStatus.ACCEPTED);
    }
}
