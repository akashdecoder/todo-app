package org.todo.api.getTodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todo.api.AppError;
import org.todo.api.dependencycontainer.RepositoryContainer;
import org.todo.api.entity.TodoItem;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todo/items/")
public class GetTodoController {

    private final RepositoryContainer repositoryContainer;

    @Autowired
    public GetTodoController(RepositoryContainer repositoryContainer) {
        this.repositoryContainer = repositoryContainer;
    }

    @GetMapping("{query}")
    public ResponseEntity<Object> getTodoItems(@PathVariable("query") String query) {
        List<TodoItem> todoItems = repositoryContainer.getITodoItemRepository().findAll();
        if(query.equalsIgnoreCase("all")) {
            return  new ResponseEntity<>(new AppError(HttpStatus.OK, LocalDateTime.now(), todoItems.toString()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND, LocalDateTime.now(), "No Todo Items found"), HttpStatus.NOT_FOUND);
    }
}
