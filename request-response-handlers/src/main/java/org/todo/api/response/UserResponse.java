package org.todo.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.todo.api.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponse {

    private HttpStatus httpStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    private LocalDateTime localDateTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<User> users;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    public UserResponse(HttpStatus httpStatus, String message, LocalDateTime localDateTime, @Nullable List<User> users, @Nullable User user) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.localDateTime = localDateTime;
        if(users != null) {
            this.users = users;
        }
        if(user != null) {
            this.user = user;
        }
    }
}
