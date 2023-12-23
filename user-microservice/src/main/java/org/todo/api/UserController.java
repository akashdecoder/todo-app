package org.todo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todo.api.dependencycontainer.ServiceContainer;
import org.todo.api.entity.User;
import org.todo.api.request.UserRequest;
import org.todo.api.response.UserResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todo/user")
public class UserController {

    private final ServiceContainer serviceContainer;

    @Autowired
    public UserController(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }

    @GetMapping
    public ResponseEntity<Object> fetch(@RequestBody UserRequest request) {
        List<User> foundUsers = new ArrayList<>();
        String query = "";

        query = request.getQ().trim();
        try {
            foundUsers = serviceContainer.getJpaUser().fetch(query);
            if(foundUsers.isEmpty()) {
                throw new Exception("User not found");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(HttpStatus.NOT_FOUND, e.getMessage(), LocalDateTime.now(), null, null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserResponse(HttpStatus.OK, null, LocalDateTime.now(), foundUsers, null), HttpStatus.OK);
    }

    // Validation needs to be done in the validation phase.
    @PostMapping
    public ResponseEntity<Object> update(@RequestBody UserRequest request) throws Exception {
        User user = request.getUser();
        try {
            serviceContainer.getJpaUser().update(user);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(HttpStatus.ALREADY_REPORTED, e.getMessage(), LocalDateTime.now(), null, null), HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>(new UserResponse(HttpStatus.ACCEPTED, null, LocalDateTime.now(), null, user), HttpStatus.ACCEPTED);
    }
}
