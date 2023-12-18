package org.todo.api.getuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todo.api.AppError;
import org.todo.api.dependencycontainer.RepositoryContainer;
import org.todo.api.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todo/user/")
public class GetUserController {

    private final RepositoryContainer repositoryContainer;

    @Autowired
    public GetUserController(RepositoryContainer repositoryContainer) {
        this.repositoryContainer = repositoryContainer;
    }

    @GetMapping("{query}")
    public ResponseEntity<Object> getUsers(@PathVariable("query") String query) {
        List<User> users = repositoryContainer.getIUserRepository().findAll();
        if(query.equalsIgnoreCase("all")) {
            return  new ResponseEntity<>(new AppError(HttpStatus.OK, LocalDateTime.now(), users.toString()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND, LocalDateTime.now(), "No Users found"), HttpStatus.NOT_FOUND);
    }
}
