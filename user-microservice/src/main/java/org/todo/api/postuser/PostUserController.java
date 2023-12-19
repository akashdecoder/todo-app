package org.todo.api.postuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.todo.api.AppError;
import org.todo.api.dependencycontainer.RepositoryContainer;
import org.todo.api.entity.User;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/todo/user")
public class PostUserController {

    private final RepositoryContainer repositoryContainer;

    @Autowired
    public PostUserController(RepositoryContainer repositoryContainer) {
        this.repositoryContainer = repositoryContainer;
    }

    // Validation needs to be done in the validation phase.
    @PostMapping("")
    public ResponseEntity<Object> update(@RequestBody User user) {
        repositoryContainer.getIUserRepository().save(user);
        return new ResponseEntity<>(new AppError(HttpStatus.ACCEPTED, LocalDateTime.now(), user.toString() + " is created"), HttpStatus.ACCEPTED);
    }
}
