package org.todo.api.postTodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.todo.api.AppError;
import org.todo.api.dependencycontainer.RepositoryContainer;
import org.todo.api.entity.TodoItem;
import org.todo.api.entity.User;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/todo/items")
public class PostTodoController {

    private final RepositoryContainer repositoryContainer;

    @Autowired
    public PostTodoController(RepositoryContainer repositoryContainer) {
        this.repositoryContainer = repositoryContainer;
    }

    @PostMapping("")
    public ResponseEntity<Object> addTodoItem(@RequestBody TodoItem todoItem) {
        System.out.println(todoItem.toString());
        repositoryContainer.getITodoItemRepository().save(todoItem);
        return new ResponseEntity<>(new AppError(HttpStatus.ACCEPTED, LocalDateTime.now(), todoItem.toString() + " is created"), HttpStatus.ACCEPTED);
    }
}

