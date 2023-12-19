package org.todo.api.getuser;

import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.todo.api.AppError;
import org.todo.api.dependencycontainer.RepositoryContainer;
import org.todo.api.dependencycontainer.ServiceContainer;
import org.todo.api.entity.User;
import org.todo.api.response.UserResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo/user/")
public class GetUserController {

    private final RepositoryContainer repositoryContainer;
    private final ServiceContainer serviceContainer;

    @Autowired
    public GetUserController(RepositoryContainer repositoryContainer, ServiceContainer serviceContainer) {
        this.repositoryContainer = repositoryContainer;
        this.serviceContainer = serviceContainer;
    }

    // fetch should support all kinds of search be it
    // query -> all, ar, username: akash, id: 23. devise an optimized and a separate hibernate solution for this
    // now supporting only all and username substrings.
    @GetMapping("{query}")
    public ResponseEntity<Object> fetch(@RequestParam String query) {

        List<Object> users = repositoryContainer.getIUserRepository().findAll()
                .stream()
                .map(user -> (Object) user)
                .collect(Collectors.toList());

        query = query.trim();

        List<Object> foundUsers = serviceContainer.getSearchOperations().searchSubstrings(users, User.class, query);

        if(foundUsers != null) {
            return new ResponseEntity<>(new UserResponse(HttpStatus.OK, "Users listed down", LocalDateTime.now(), foundUsers), HttpStatus.OK);
        }

        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND, LocalDateTime.now(), "User not found"), HttpStatus.NOT_FOUND);
    }
}
