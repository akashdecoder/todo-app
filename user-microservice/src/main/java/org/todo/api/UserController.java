package org.todo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/todo/user")
public class UserController {

    private final ServiceContainer serviceContainer;

    @Autowired
    public UserController(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }

    @GetMapping
    public ResponseEntity<Object> fetchFromObject(@RequestBody Optional<UserRequest> requestBody, @RequestParam Optional<String> requestParam) {
        List<User> foundUsers = new ArrayList<>();
        AtomicReference<String> query = new AtomicReference<>("");

        requestBody.ifPresent(body -> query.set(requestBody.get().getQ().trim()));

        requestParam.ifPresent(body -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                UserRequest userRequest = objectMapper.readValue(body, UserRequest.class);
                query.set(userRequest.getQ());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            foundUsers = serviceContainer.getJpaUser().fetch(query.get());
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
