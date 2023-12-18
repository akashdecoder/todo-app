package org.todo.api.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todo.api.AppError;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/Todo")
public class AddUserService {

//    @Autowired
//    private ValidationService validationService;
//
//    private final RepositoryContainer repositoryContainer;
//
//    @Autowired
//    public AddUserService(RepositoryContainer repositoryContainer) {
//        this.repositoryContainer = repositoryContainer;
//    }

    private ResponseEntity<Object> buildResponseEntity(Object object, HttpStatus httpStatus) {
        return new ResponseEntity<>(object, httpStatus);
    }

    @PostMapping("/User")
    public ResponseEntity<Object> addTodoItem() throws Exception {
//        try {
////            repositoryContainer.getIUserRepository().save(user);
//            return "High";
//        } catch (Exception e) {
//            return buildResponseEntity(new AppError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(new AppError(HttpStatus.ACCEPTED, LocalDateTime.now(), "message"), HttpStatus.ACCEPTED);

    }
}
