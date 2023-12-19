package org.todo.api.getTodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.todo.api.AppError;
import org.todo.api.dependencycontainer.RepositoryContainer;
import org.todo.api.entity.TodoItem;
import org.todo.api.entity.User;
import org.todo.api.response.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todo/items/")
public class GetTodoController {

    private String BASE_URL = "http://localhost:8081/todo/user/";
    private final RepositoryContainer repositoryContainer;

    private final RestTemplate restTemplate;

    @Autowired
    public GetTodoController(RepositoryContainer repositoryContainer, RestTemplate restTemplate) {
        this.repositoryContainer = repositoryContainer;
        this.restTemplate = restTemplate;
    }

    @GetMapping("{query}")
    public ResponseEntity<Object> fetch(@PathVariable("query") String query) {

        List<TodoItem> foundTodoItems = null;

        String requestUrl = BASE_URL+query;

        List<TodoItem> todoItems = repositoryContainer.getITodoItemRepository().findAll();

        ResponseEntity<UserResponse> usersResponseEntity = restTemplate.getForEntity(requestUrl, UserResponse.class);
        List<Object> users = usersResponseEntity.getBody().getUsers();

        if(users.size() != 0) {
            return  new ResponseEntity<>(new UserResponse(HttpStatus.OK, requestUrl, LocalDateTime.now(), users), HttpStatus.OK);
        }

        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND, LocalDateTime.now(), requestUrl), HttpStatus.NOT_FOUND);
    }
}
