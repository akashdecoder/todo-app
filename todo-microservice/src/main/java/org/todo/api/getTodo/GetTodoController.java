package org.todo.api.getTodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.todo.api.AppError;

import org.todo.api.dependencycontainer.RepositoryContainer;
import org.todo.api.dependencycontainer.ServiceContainer;
import org.todo.api.entity.TodoItem;
import org.todo.api.request.UserRequest;
import org.todo.api.response.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todo/items")
public class GetTodoController {

    private String BASE_URL = "http://localhost:8081/todo/user";

    private final WebClient webClient;

    private final ServiceContainer serviceContainer;

    private final RepositoryContainer repositoryContainer;

    @Autowired
    public GetTodoController(WebClient webClient, ServiceContainer serviceContainer, RepositoryContainer repositoryContainer) {
        this.webClient = webClient;
        this.serviceContainer = serviceContainer;
        this.repositoryContainer = repositoryContainer;
    }

    @GetMapping("/{q}")
    public ResponseEntity<Object> fetch(@PathVariable("q") String query) {

        List<TodoItem> foundTodoItems = null;

        UserRequest userRequest = new UserRequest();
        userRequest.setQ(query);

        UserResponse userResponse = webClient
                .method(HttpMethod.GET)
                .uri(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(userRequest))
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();



        if(!userResponse.getUsers().isEmpty()) {
            return  new ResponseEntity<>(new UserResponse(HttpStatus.OK, BASE_URL, LocalDateTime.now(), userResponse.getUsers(), null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND, LocalDateTime.now(), BASE_URL), HttpStatus.NOT_FOUND);
    }
}
