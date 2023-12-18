package org.todo.api.get;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.todo.api.AppError;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/Todo")
public class GetUserService {

    private ResponseEntity<Object> buildResponseEntity(Object object, HttpStatus httpStatus) {
        return new ResponseEntity<>(object, httpStatus);
    }
    @GetMapping("/User")
    public ResponseEntity<Object> getTodoItem() throws Exception {
//        try {
////            repositoryContainer.getIUserRepository().save(user);
//            return "High";
//        } catch (Exception e) {
//            return buildResponseEntity(new AppError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(new AppError(HttpStatus.OK, LocalDateTime.now(), "message"), HttpStatus.OK);

    }
}
